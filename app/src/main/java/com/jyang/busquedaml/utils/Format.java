package com.jyang.busquedaml.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Format {

    public static String formatDecimalSignal(Double number){
        if(number < 0.0)
            return "-$" + Format.formatDecimal(number * -1);
        else
            return "$" + Format.formatDecimal(number);
    }

    public static String formatDecimal(Double number){
        DecimalFormat decim = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.GERMAN));
        return decim.format(number);
    }
}
