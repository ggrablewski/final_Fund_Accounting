package pl.coderslab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "trades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trade implements EntityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date date;

//@TODO    Portfolio feature to be added in future

//    @NotNull
    @ManyToOne
    private Portfolio portfolio;

    @NotNull
    @ManyToOne
    private Fund fund;

    @NotNull
    @ManyToOne
    private Security security;

    @NotNull
    private Float amount;

    @NotNull
    private Float purchasePrice;

    @NotNull
    private Float transactionCost;

// Trade handling methods

    public Trade(Date date, Fund fund, Security security, Float amount, Float purchasePrice, Float transactionCost) {

// Setup part
        this.date = date;
        this.fund = fund;
        this.security = security;
        this.amount = amount;
        this.purchasePrice = purchasePrice;
        this.transactionCost = transactionCost;

// Booking part
        Float totalCost = amount * purchasePrice + transactionCost;
        switch (this.security.getSecurityType()) {

            case future:
            case forward:
            case derivative:
                this.fund.setFinancialAssetsCollateral(this.fund.getFinancialAssetsCollateral() + totalCost);
                this.fund.setCashAndEquivalents(this.fund.getCashAndEquivalents() - totalCost);
                this.fund.setDueToBrokers(this.fund.getDueToBrokers() + transactionCost);
                this.fund.setRetainedEarnings(this.fund.getRetainedEarnings() - transactionCost);
                break;

            case currency:
                this.fund.setOtherReceivables(this.fund.getOtherReceivables() + totalCost);
                this.fund.setCashAndEquivalents(this.fund.getCashAndEquivalents() - totalCost);
                this.fund.setDueToBrokers(this.fund.getDueToBrokers() + transactionCost);
                this.fund.setRetainedEarnings(this.fund.getRetainedEarnings() - transactionCost);
                break;

            default:
                this.fund.setFinancialAssets(this.fund.getFinancialAssets() + totalCost);
                this.fund.setCashAndEquivalents(this.fund.getCashAndEquivalents() - totalCost);
                this.fund.setDueToBrokers(this.fund.getDueToBrokers() + transactionCost);
                this.fund.setRetainedEarnings(this.fund.getRetainedEarnings() - transactionCost);
                break;
        }
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", date=" + date +
                ", fund=" + fund.getFundName() +
//                ", fund=" + portfolio.getFund().getFundName() +
                ", security=" + security.getSecurityName() +
                ", amount=" + amount +
                ", purchasePrice=" + purchasePrice +
                ", transactionCost=" + transactionCost +
                ", totalValue=" + amount * purchasePrice + transactionCost +
                '}';
    }
}
