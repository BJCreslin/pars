package ru.bjcreslin.pars.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Class for String manipulation
 */

public class StringConroller {

    /**
     * @param inText String   - text in format "dd ddd.d0 ₽"
     * @return BigDecimal
     */

    public static BigDecimal stroyparkCostToBigDecimal(String inText) {
        BigDecimal solution;
        String tempText = inText.replaceAll("[\\s\\u20BD]", "");
        try {
            solution = BigDecimal.valueOf(Double.parseDouble(tempText)).setScale(2, RoundingMode.CEILING);
        } catch (NumberFormatException nfe) {
            solution = BigDecimal.ZERO;
        }

        return solution;
    }
}
