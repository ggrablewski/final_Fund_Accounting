package pl.coderslab.service;

import pl.coderslab.entity.Fund;
import pl.coderslab.entity.Security;
import pl.coderslab.entity.Trade;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface TradeService {

    void save(Trade trade);
    Optional<Trade> findById(Long id);
    void deleteById(Long id);
    List<Trade> findAll();
    List<Trade> findAllByDate(Date date);
    List<Trade> findAllByFund(Fund fund);
    List<Trade> findAllBySecurity(Security security);
    List<Trade> findAllByDateAndSecurity(Date date, Security security);
    List<Trade> findAllByDateAndFund(Date date, Fund fund);
    List<Trade> findAllByFundAndSecurity(Fund fund, Security security);
    List<Trade> findAllByFundAndDateAndSecurity(Fund fund, Date date, Security security);

}
