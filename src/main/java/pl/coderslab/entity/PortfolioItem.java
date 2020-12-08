package pl.coderslab.entity;

import lombok.Data;

@Data
public class PortfolioItem {

    private Security security;
    private Float amount;
    private Float value;

}
