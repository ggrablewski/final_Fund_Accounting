package pl.coderslab.fixtures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.entity.Fund;
import pl.coderslab.entity.Security;
import pl.coderslab.entity.Trade;
import pl.coderslab.service.FundService;
import pl.coderslab.service.SecurityService;
import pl.coderslab.service.TradeService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class TradeFixture {

    private TradeService tradeService;
    private FundService fundService;
    private SecurityService securityService;

    private List<Trade> tradeList = new ArrayList<>();

    @Autowired
    public TradeFixture(TradeService tradeService, FundService fundService, SecurityService securityService) {
        this.tradeService = tradeService;
        this.fundService = fundService;
        this.securityService = securityService;
    }

    private Trade generateRandomTrade(Date date, List<Fund> funds, List<Security> securities) {
        Random rand = new Random();

        Fund fund = funds.get(rand.nextInt(funds.size()));
        Security security = securities.get(rand.nextInt(securities.size()));
        Float amount = (float) rand.nextInt(10000);
        Float price = security.getPrice() * (1f + rand.nextFloat() * 0.1f - 0.05f);
        Float transactionCostRate = rand.nextFloat() * 0.05f;

        return new Trade(date, fund, security, amount, price,amount * price * transactionCostRate);
    }

    public void loadIntoDB() {

        List<Fund> funds = fundService.findAllFunds();
        List<Security> securities = securityService.findAllSecurities();

        for (int i = 0; i < 5; i++) {
            tradeList.add(generateRandomTrade(Date.valueOf("2020-12-10"), funds, securities));
        }

        for (int i = 0; i < 10; i++) {
            tradeList.add(generateRandomTrade(Date.valueOf("2020-12-11"), funds, securities));
        }

        for (Trade trade : tradeList) {
            tradeService.save(trade);
        }
    }
}