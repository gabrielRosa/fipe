package com.dbc.prova.service.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.OptionalDouble;

public class CurrencyUtil {

    public static OptionalDouble toDouble(final String amount, final Locale locale){
        final NumberFormat format = NumberFormat.getNumberInstance(locale);

        try {
            return OptionalDouble.of(format.parse(amount.replaceAll("[^\\d.,]","")).doubleValue());
        } catch (ParseException e) {
            return OptionalDouble.empty();
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }

}
