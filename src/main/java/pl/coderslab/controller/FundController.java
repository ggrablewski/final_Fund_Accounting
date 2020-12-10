package pl.coderslab.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Fund;
import pl.coderslab.service.FundService;
import pl.coderslab.service.SecurityService;
import pl.coderslab.service.TradeService;
import pl.coderslab.service.UserService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("")
@Slf4j
public class FundController {

    private UserService userService;
    private FundService fundService;
    private SecurityService securityService;
    private TradeService tradeService;
    private Validator validator;

    @Autowired
    public FundController(
            UserService userService,
            FundService fundService,
            SecurityService securityService,
            TradeService tradeService,
            Validator validator
    ) {
        this.userService = userService;
        this.fundService = fundService;
        this.securityService = securityService;
        this.tradeService = tradeService;
        this.validator = validator;
    }

    @GetMapping("/ta/fund/add")
    public String addFund(Model model) {
        model.addAttribute("fund", new Fund());
        model.addAttribute("lastDate", LocalDate.now().minusDays(1));
        return "addFundForm";
    }

    @PostMapping("/ta/fund/add")
    public String addFund(Fund fund, Model model) {

        Set<ConstraintViolation<Fund>> errors = validator.validate(fund);

        if (errors.isEmpty()) {
            List<String> fundData = new ArrayList<>();
            fundService.save(fund);
        } else {
            List<String> messageList = new ArrayList<>();
            for (ConstraintViolation<Fund> constraintViolation : errors) {
                messageList.add("Errors:");
                messageList.add(constraintViolation.getPropertyPath() + " "
                        + constraintViolation.getMessage());
            }
            model.addAttribute(messageList);
            return "redirect:/ta/fund/add";
        }

        log.info("fund added " + fund.getISIN() + " " + fund.getFundName());
        return "redirect:menu";
    }
}
