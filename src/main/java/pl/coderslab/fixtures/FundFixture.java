package pl.coderslab.fixtures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.entity.Fund;
import pl.coderslab.service.FundService;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Component
public class FundFixture {

    private FundService fundService;

    private List<Fund> fundList = Arrays.asList(
            new Fund(null, "LU1234987293", "BlackRock Stability Fund", "BlackRock",
                    null, "EUR", Date.valueOf("2020-12-09"), 54334567f, 837346843f),
            new Fund(null, "GB4235246245", "BlackRock Stability UK Fund", "BlackRock",
                    null, "GBP", Date.valueOf("2020-12-09"), 43792934f, 467789960f),
            new Fund(null, "US1234987293", "BlackRock US Mortgage Fund", "BlackRock",
                    null, "USD", Date.valueOf("2020-12-09"), 126257245f, 957874675f),
            new Fund(null, "LU5546978954", "BlackRock Emerging Markets Fund", "BlackRock",
                    null, "EUR", Date.valueOf("2020-12-09"), 74386834f, 1234567890f),
            new Fund(null, "LU4759419140", "JPMorgan Stability Fund", "JPMorgan",
                    null, "EUR", Date.valueOf("2020-12-09"), 73489586f, 928274658f),
            new Fund(null, "LU3157694594", "JPMorgan Alpha Fund", "JPMorgan",
                    null, "EUR", Date.valueOf("2020-12-09"), 52718113f, 293847493f),
            new Fund(null, "DE2374681903", "Allianz Global Fund", "Allianz",
                    null, "EUR", Date.valueOf("2020-12-09"), 23683278f, 892390482f),
            new Fund(null, "DE4352376835", "Allianz Futures Aggressive Fund", "Allianz",
                    null, "EUR", Date.valueOf("2020-12-09"), 34667834f, 411345513f),
            new Fund(null, "GB1234987293", "Allianz UK Fund", "Allianz",
                    null, "GBP", Date.valueOf("2020-12-09"), 42352546f, 245656745f),
            new Fund(null, "US0940534853", "PIMCO New Technologies Fund", "PIMCO",
                    null, "USD", Date.valueOf("2020-12-09"), 123445645f, 1349340954f)
    );

    @Autowired
    public FundFixture(FundService fundService) {
        this.fundService = fundService;
    }

    public void loadIntoDB() {
        for (Fund fund : fundList) {
            fundService.save(fund);
        }
    }
}