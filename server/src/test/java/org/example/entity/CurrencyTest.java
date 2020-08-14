package org.example.entity;

import org.example.entity.enums.Currency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CurrencyTest {
    @Test
    public void testCorrectConvertCurrency() {
        //given
        Double sum = 50.0;
        Currency from = Currency.GBP;
        Currency to = Currency.CHF;
        //when
        Double result = from.currencyConversion(sum, to);
        //than
        assertTrue(result > 58 && result < 60);
    }


}