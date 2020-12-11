package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Fund;
import pl.coderslab.entity.Security;
import pl.coderslab.entity.SecurityType;
import pl.coderslab.entity.Trade;

import java.sql.Date;
import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

    @Override
    @Query("SELECT t From Trade t JOIN t.fund f JOIN t.security s")
    public List<Trade> findAll();

    @Query("SELECT t From Trade t JOIN t.fund f JOIN t.security s WHERE t.date = :date")
    public List<Trade> findAllByDate(@Param("date") Date date);

    @Query("SELECT t From Trade t JOIN t.fund f JOIN t.security s WHERE t.fund = :fund")
    public List<Trade> findAllByFund(@Param("fund") Fund fund);

    @Query("SELECT t From Trade t JOIN t.fund f JOIN t.security s WHERE t.security = :security")
    public List<Trade> findAllBySecurity(@Param("security") Security security);

    public List<Trade> findAllByDateAndSecurity(@Param("date") Date date,
                                                    @Param("security") Security security);

    public List<Trade> findAllByDateAndFund(@Param("date") Date date, @Param("fund") Fund fund);

    public List<Trade> findAllByFundAndSecurity(@Param("fund") Fund fund,
                                                    @Param("security") Security security);

    public List<Trade> findAllByFundAndDateAndSecurity(@Param("fund") Fund fund, @Param("date") Date date,
                                                           @Param("security") Security security);

}
