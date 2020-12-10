package pl.coderslab.fixtures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.entity.Security;
import pl.coderslab.entity.SecurityType;
import pl.coderslab.service.SecurityService;

import java.util.Arrays;
import java.util.List;

@Component
public class SecurityFixture {

    private SecurityService securityService;

    private List<Security> securityList = Arrays.asList(
            new Security(null, "US1234123412", "Amazon", SecurityType.stock, 200f,null),
            new Security(null, "US5465784568", "Microsoft", SecurityType.stock, 20f,null),
            new Security(null, "US8968426246", "Tesla", SecurityType.stock, 150f,null),
            new Security(null, "US0249786383", "Facebook", SecurityType.stock, 120f,null),
            new Security(null, "CH8016670137", "Nestle", SecurityType.stock, 40f,null),
            new Security(null, "GB1438963984", "Diageo", SecurityType.stock, 57f,null),
            new Security(null, "GB4389674195", "Rio Tinto", SecurityType.stock, 88f,null),
            new Security(null, "DE4761437876", "BMW", SecurityType.stock, 67f,null),
            new Security(null, "DE4786914576", "Siemens", SecurityType.stock, 34f,null),
            new Security(null, "DE5678238518", "SAP", SecurityType.stock, 64f,null),
            new Security(null, "DE8972452985", "Germany 12.2023 1.2%", SecurityType.bond, 102f,null),
            new Security(null, "GB1345194505", "London 04.2027 0.8%", SecurityType.bond, 101.4f,null),
            new Security(null, "US1895791045", "USA 05.2021 0.75%", SecurityType.bond, 100.2f,null),
            new Security(null, "US4343095891", "Fannie Mae 10.2041 1.1%", SecurityType.bond, 99.8f,null),
            new Security(null, "CH1456892584", "Zurich 05.2025 0.37%", SecurityType.bond, 102.8f,null),
            new Security(null, "US2435423642", "Dow Jones 03.2021", SecurityType.future, 30000f,null),
            new Security(null, "US2345795314", "NASDAQ 03.2021", SecurityType.future, 12400f,null),
            new Security(null, "US9879874629", "CL 01.2021", SecurityType.future, 45f,null),
            new Security(null, "GB8756395236", "FTSE 03.2021", SecurityType.future, 6600f,null),
            new Security(null, "JP3348542574", "Nikkei 03.2021", SecurityType.future, 26800f,null)
            );

    @Autowired
    public SecurityFixture(SecurityService securityService) {
        this.securityService = securityService;
    }

    public void loadIntoDB() {
        for (Security security : securityList) {
            securityService.save(security);
        }
    }
}
