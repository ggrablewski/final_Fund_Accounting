package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Security;
import pl.coderslab.entity.SecurityType;

import java.util.List;

@Repository
public interface SecurityRepository extends JpaRepository<Security, Long> {

    public Security findByISIN(String ISIN);

    public Security findBySecurityName(String securityName);

    @Query("DELETE FROM Security s WHERE s.ISIN = :ISIN")
    public void deleteByISIN(@Param("ISIN") String ISIN);

    public List<Security> findAllBySecurityType(SecurityType securityType);

}
