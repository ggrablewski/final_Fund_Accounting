package pl.coderslab.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Fund;
import pl.coderslab.entity.Security;
import pl.coderslab.entity.Trade;
import pl.coderslab.repository.TradeRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TradeServiceImpl implements TradeService {

    @Autowired
    protected TradeRepository tradeRepository;

    @Autowired
    public TradeServiceImpl(TradeRepository tradeRepository) {
        log.trace("constructing TradeServiceImpl");
        this.tradeRepository = tradeRepository;
    }

    @Override
    public void save(Trade trade) {
        tradeRepository.save(trade);
    }

    @Override
    public Optional<Trade> findById(Long id) {
        return tradeRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        tradeRepository.deleteById(id);
    }

    @Override
    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    @Override
    public List<Trade> findAllByDate(Date date) {
        return tradeRepository.findAllByDate(date);
    }

    @Override
    public List<Trade> findAllByFund(Fund fund) {
        return tradeRepository.findAllByFund(fund);
    }

    @Override
    public List<Trade> findAllBySecurity(Security security) {
        return tradeRepository.findAllBySecurity(security);
    }

    @Override
    public List<Trade> findAllByDateAndSecurity(Date date, Security security) {
        return tradeRepository.findAllByDateAndSecurity(date, security);
    }

    @Override
    public List<Trade> findAllByDateAndFund(Date date, Fund fund) {
        return tradeRepository.findAllByDateAndFund(date, fund);
    }

    @Override
    public List<Trade> findAllByFundAndSecurity(Fund fund, Security security) {
        return tradeRepository.findAllByFundAndSecurity(fund, security);
    }

    @Override
    public List<Trade> findAllByFundAndDateAndSecurity(Fund fund, Date date, Security security) {
        return tradeRepository.findAllByFundAndDateAndSecurity(fund, date, security);
    }
}
