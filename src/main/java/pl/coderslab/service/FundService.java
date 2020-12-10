package pl.coderslab.service;

import pl.coderslab.entity.Fund;

import java.util.List;
import java.util.Optional;

public interface FundService {

    void save(Fund fund);
    Optional<Fund> findById(Long Id);
    Fund findByISIN(String ISIN);
    void deleteByISIN(String ISIN);
    List<Fund> findAllFunds();
    List<Fund> findAllByClient(String clientName);

}