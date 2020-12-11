package pl.coderslab.controller;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Fund;
import pl.coderslab.service.FundService;
import pl.coderslab.service.SecurityService;
import pl.coderslab.service.TradeService;
import pl.coderslab.service.UserService;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.awt.print.Book;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/fund")
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

    @GetMapping("/add")
    public String addFund(Model model) {
        log.info("adding fund - get");
        model.addAttribute("fund", new Fund());
        model.addAttribute("lastDate", LocalDate.now().minusDays(1));
        return "addFundForm";
    }

    @PostMapping("/add")
    public String addFund(Fund fund, Model model) {

        log.info("adding fund - post");
        Set<ConstraintViolation<Fund>> errors = validator.validate(fund);

        if (errors.isEmpty()) {
            Fund newFund = new Fund(null , fund.getISIN(), fund.getFundName(), fund.getClientName(), null,
                    fund.getCurrency(), fund.getLastValuationDate(), fund.getTotalShares(), fund.getShareCapital());
            fundService.save(newFund);
        } else {
            List<String> messageList = new ArrayList<>();
            for (ConstraintViolation<Fund> constraintViolation : errors) {
                messageList.add("Errors:");
                messageList.add(constraintViolation.getPropertyPath() + " "
                        + constraintViolation.getMessage());
            }
            model.addAttribute(messageList);
            return "redirect:/fund/add";
        }

        log.info("fund added " + fund.getISIN() + " " + fund.getFundName());
        return "redirect:/menu";
    }

    @GetMapping("/list")
    @Transactional
    public String fundList(Model model) {
        log.info("fund list");
        List<Fund> fundList = fundService.findAllFunds();
        for (Fund fund : fundList) {
            Hibernate.initialize(fund.getTrades());
        }
        model.addAttribute("fundList", fundList);
        return "fundList";
    }

    @GetMapping("/details/{id}")
    @Transactional
    public String fundDetails(@PathVariable Long id, Model model) {
        log.info("fund details");
        Fund fund = fundService.findById(id).get();
        model.addAttribute("fund", fund);
        model.addAttribute("NAV", fund.calculateNAV(Date.valueOf(LocalDate.now())));
        return "fundDetails";
    }

}
