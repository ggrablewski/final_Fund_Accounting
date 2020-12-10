package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.repository.FundRepository;

public class FundServiceImpl implements FundService {

    @Autowired
    protected FundRepository fundRepository;


}
