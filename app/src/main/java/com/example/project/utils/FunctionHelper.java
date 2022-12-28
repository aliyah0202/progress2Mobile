package com.example.project.utils;
import java.text.DecimalFormat;

public class FunctionHelper {
    public static String rupiahFormat (int price){
        DecimalFormat formatter = new DecimalFormat("#,###");
        return "RP " + formatter.format(price).replaceAll(",", ".");
    }
}
