package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Fund;

import java.util.List;

@Repository
public interface FundRepository extends JpaRepository<Fund, Long> {

    @Query("SELECT f FROM Fund f WHERE f.ISIN = :ISIN")
    public Fund findByISIN(@Param("ISIN") String ISIN);

    @Query("DELETE FROM Fund f WHERE f.ISIN = :ISIN")
    public void deleteByISIN(@Param("ISIN") String ISIN);

    public List<Fund> findAllByClientName(String clientName);

}