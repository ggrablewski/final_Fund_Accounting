package pl.coderslab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import static java.time.temporal.ChronoUnit.DAYS;

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

//  NAV fields
    private Date lastValuationDate;
    private Float totalShares;

// EXPENSES - in % of the base

    // Trade charges
    private Float purchaseFee;
    private Float redemptionFee;

    // TNA charges
    private Float managementFee;
    private Float performanceFee;
    private Float accountFee;
    private Float distributionFee;
    private Float serviceFee;

    private Float totalExpenseCap;

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

// constructor with initial fields

    public Fund(Long id, String ISIN, String fundName, String clientName, Portfolio portfolio, Date lastValuationDate,
                Float totalShares, Float startingCapital) {
        this.id = id;
        this.ISIN = ISIN;
        this.fundName = fundName;
        this.clientName = clientName;
        this.portfolio = portfolio;
        this.lastValuationDate = lastValuationDate;
        this.totalShares = totalShares;
        this.cashAndEquivalents = startingCapital;
        this.shareCapital = startingCapital;
    }

// NAV methods

    public Float calculateAssets() {
        return  financialAssets +
                financialAssetsCollateral +
                dueFromBrokers +
                otherReceivables +
                marginAccounts +
                cashAndEquivalents;
    }

    public Float calculateLiabilities() {
        return  financialLiabilities +
                dueToBrokers +
                accruedExpenses;
    }

    public Float accrueTnaExpenses (Date date) {
        Float TNA = calculateAssets() - calculateLiabilities();
        long days = DAYS.between(lastValuationDate.toLocalDate(), date.toLocalDate());
        Float expenseRate = Math.min(totalExpenseCap,
                        managementFee +
                        performanceFee +
                        accountFee +
                        distributionFee +
                        serviceFee);
        return TNA * expenseRate * days / 365;
    }

    public Float calculateNAV(Date date) {
// calculate expenses
        Float currentExpenses = accrueTnaExpenses(date);
// **** BOOK the expenses
        accruedExpenses += currentExpenses;
        retainedEarnings -= currentExpenses;
// calculate NAV
        Float nav = ( calculateAssets() - calculateLiabilities() ) / totalShares;
// set new last valuation date
        lastValuationDate = date;
// return result
        return nav;
    }

    @Override
    public String toString() {
        return "Fund " + fundName + "\n" +
                "ISIN " + ISIN + "\n" +
                "client " + clientName + "\n" +
                "last valuation date " + lastValuationDate.toLocalDate().toString() + "\n" +
                "last NAV " + calculateNAV(lastValuationDate) + "\n" +
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