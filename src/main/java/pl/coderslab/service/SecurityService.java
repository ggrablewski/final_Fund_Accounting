package pl.coderslab.service;

import pl.coderslab.entity.Security;
import pl.coderslab.entity.SecurityType;

import java.util.List;
import java.util.Optional;

public interface SecurityService {

    void save(Security security);
    Optional<Security> findById(Long Id);
    Security findByISIN(String ISIN);
    Security findByName(String securityName);
    void deleteByISIN(String ISIN);
    List<Security> findAllSecurities();
    List<Security> findAllByType(SecurityType securityType);

}
