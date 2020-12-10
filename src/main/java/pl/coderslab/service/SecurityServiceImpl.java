package pl.coderslab.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.entity.Security;
import pl.coderslab.entity.SecurityType;
import pl.coderslab.repository.SecurityRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    protected SecurityRepository securityRepository;

    @Autowired
    public SecurityServiceImpl(SecurityRepository securityRepository) {
        log.trace("constructing SecurityServiceImpl");
        this.securityRepository = securityRepository;
    }

    @Override
    public void save(Security security) {
        securityRepository.save(security);
    }

    @Override
    public Optional<Security> findById(Long Id) {
        return securityRepository.findById(Id);
    }

    @Override
    public Security findByISIN(String ISIN) {
        return securityRepository.findByISIN(ISIN);
    }

    @Override
    public Security findByName(String securityName) {
        return securityRepository.findBySecurityName(securityName);
    }

    @Override
    public void deleteByISIN(String ISIN) {
        securityRepository.deleteByISIN(ISIN);
    }

    @Override
    public List<Security> findAllSecurities() {
        return securityRepository.findAll();
    }

    @Override
    public List<Security> findAllByType(SecurityType securityType) {
        return securityRepository.findAllBySecurityType(securityType);
    }
}
