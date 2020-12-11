package pl.coderslab.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Fund;
import pl.coderslab.repository.FundRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FundServiceImpl implements FundService {

    @Autowired
    protected FundRepository fundRepository;

    @Autowired
    public FundServiceImpl(FundRepository fundRepository) {
        log.trace("constructing FundServiceImpl");
        this.fundRepository = fundRepository;
    }

    @Override
    public void save(Fund fund) {
        fundRepository.save(fund);
    }

    @Override
    public Optional<Fund> findById(Long Id) {
        return fundRepository.findById(Id);
    }

    @Override
    public Fund findByISIN(String ISIN) {
        return fundRepository.findByISIN(ISIN);
    }

    @Override
    public void deleteByISIN(String ISIN) {
        fundRepository.deleteByISIN(ISIN);
    }

    @Override
    public List<Fund> findAllFunds() {
        return fundRepository.findAll();
    }

    @Override
    public List<Fund> findAllByClient(String clientName) {
        return fundRepository.findAllByClientName(clientName);
    }
}
