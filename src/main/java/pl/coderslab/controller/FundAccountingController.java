package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.User;
import pl.coderslab.repository.FundRepository;
import pl.coderslab.service.UserService;
import pl.coderslab.service.UserServiceImpl;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class FundAccountingController {

    //@TODO logger nie tworzy pliku

    @Autowired
    protected FundRepository fundRepository;

    @Autowired
    Validator validator;

    @Autowired
    public FundAccountingController (Validator validator) {
        this.validator = validator;
    }

    @GetMapping("")
    public String welcome(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody  // to zakomentować, jak już będzie gotowe mainmenu
    public String login(User user, Model model) {

        //logowanie

        // wyszukać usera
        UserServiceImpl userService = new UserServiceImpl(new UserDao());
        User readUser = userService.findByComitId(user.getComitId());
        //@TODO błąd - brakuje EntityManagera

        // sprawdzić hasło
        boolean passwordMatches = userService.passwordMatches(readUser, user.getPassword());

        //@TODO zapisać w sesji poziom uprawnień usera (readUser.getUserGroup())

        return passwordMatches ? "mainmenu " + readUser.getUserGroup() : "wrong password";
    }

}
