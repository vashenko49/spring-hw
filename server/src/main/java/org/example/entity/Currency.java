package org.example.entity;

import java.io.Serializable;

public enum Currency implements Serializable {
    USD(1.0),
    EUR(0.85),
    UAH(27.74),
    CHF(0.92),
    GBP(0.78);

    private Double currencyRatePeggedToTheUSD;

    Currency(Double currencyRatePeggedToTheUSD) {
        this.currencyRatePeggedToTheUSD = currencyRatePeggedToTheUSD;
    }

    public Double getCurrencyRatePeggedToTheUSD() {
        return currencyRatePeggedToTheUSD;
    }

    public Double currencyConversion(Double sum, Currency to) {
        return (to.getCurrencyRatePeggedToTheUSD() * sum) / this.getCurrencyRatePeggedToTheUSD();
    }
}
