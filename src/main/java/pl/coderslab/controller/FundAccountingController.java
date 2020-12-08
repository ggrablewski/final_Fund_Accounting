package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.User;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class FundAccountingController {

    @Autowired
    Validator validator;

    @Autowired
    public FundAccountingController (Validator validator) {
        this.validator = validator;
    }

//    @ModelAttribute("countryList")
//    public List<String> countryList() {
//        return Arrays.asList("Polska", "Niemcy", "Francja", "Rosja", "Ukraina");
//    }

    @GetMapping("")
    public String welcome(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody  // to zakomentować, jak już będzie gotowe mainmenu
    public String login(User user, Model model) {

        //@TODO logowanie
        // wyszukać usera
        // sprawdzić hasło
        // zapisać w sesji poziom uprawnień usera

        return "mainmenu";
    }

}
