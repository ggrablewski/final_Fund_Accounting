package pl.coderslab.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;

import javax.validation.Valid;
import javax.validation.Validator;

@Controller
@RequestMapping("/")
@SessionAttributes("userGroup")
@Slf4j
public class FundAccountingController {

    private UserService userService;
    private Validator validator;

    @Autowired
    public FundAccountingController(
            UserService userService,
            Validator validator
    ) {
        this.userService = userService;
        this.validator = validator;
    }

    @GetMapping("")
    public String welcome(Model model) {
        log.info("login screen");
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            log.info("wrong comitId at login");
            return "login";
        }

        User readUser = userService.findByComitId(user.getComitId());
        boolean passwordMatches = userService.passwordMatches(readUser, user.getPassword());

        log.info("password check " + passwordMatches);

        if (passwordMatches) {
            model.addAttribute("userGroup", readUser.getUserGroup());
            return "mainmenu";
        } else {
            return "login";
        }
    }

}
