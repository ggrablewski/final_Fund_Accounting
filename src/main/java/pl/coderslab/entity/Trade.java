package pl.coderslab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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

    @NotEmpty
    private Date date;

    @NotNull
    @ManyToOne
    private Portfolio portfolio;

    @NotEmpty
    @ManyToOne
    private Security security;

    @NotEmpty
    private Float amount;

    @NotEmpty
    private Float transactionCost;

    @NotEmpty
    private Float totalValue;

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", date=" + date +
                ", fund=" + portfolio.getFund().getFundName() +
                ", security=" + security.getSecurityName() +
                ", amount=" + amount +
                ", transactionCost=" + transactionCost +
                ", totalValue=" + totalValue +
                '}';
    }
}
