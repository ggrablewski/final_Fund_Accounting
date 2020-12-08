package pl.coderslab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "funds")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fund implements EntityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[A-Z]{2}[0-9]{10}")
    private String ISIN;

    @NotEmpty
    private String fundName;

    @NotEmpty
    private String clientName;     // name of the asset management company

    @OneToOne(mappedBy = "fund")
    private Portfolio portfolio;

// EXPENSES - in % of the base

    private Float purchaseFee;
    private Float redemptionFee;
    private Float managementFee;
    private Float performanceFee;
    private Float accountFee;
    private Float distributionFee;
    private Float serviceFee;

// ACCOUNTS

// ASSETS

    private Float financialAssets;
    private Float financialAssetsCollateral;   // derivatives, which according to IFRS need to be presented separately
    private Float dueFromBrokers;
    private Float otherReceivables;
    private Float marginAccounts;
    private Float cashAndEquivalents;

// CAPITAL

    private Float shareCapital;
    private Float sharePremium;
    private Float retainedEarnings;

// LIABILITIES

    private Float financialLiabilities;
    private Float dueToBrokers;
    private Float accruedExpenses;


    @Override
    public String toString() {
        return "Fund " + fundName + "\n" +
                "ISIN " + ISIN + "\n" +
                "client " + clientName + "\n" +
                "ASSETS \n"+
                "Financial assets at fair value through profit or loss " + financialAssets + "\n" +
                "Financial assets at fair value through profit or loss pledged as collateral " + financialAssetsCollateral + "\n" +
                "Due from brokers " + dueFromBrokers + "\n" +
                "Other receivables " + otherReceivables + "\n" +
                "Margin accounts " + marginAccounts + "\n" +
                "Cash and cash equivalents " + cashAndEquivalents + "\n" +
                "CAPITAL \n"+
                "Share capital " + shareCapital + "\n" +
                "Share premium " + sharePremium + "\n" +
                "Retained earnings " + retainedEarnings + "\n" +
                "LIABILITIES \n"+
                "Financial liabilities at fair value through profit or loss " + financialLiabilities + "\n" +
                "Due to brokers " + dueToBrokers + "\n" +
                "Accrued expenses " + accruedExpenses + "\n";
    }

}