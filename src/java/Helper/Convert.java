/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 *
 * @author PCMSI
 */
public class Convert {
    public static String convertToVietnameseDong(float amount) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        Currency vietnameseDong = Currency.getInstance("VND");
        currencyFormatter.setCurrency(vietnameseDong);
        return currencyFormatter.format(amount);
    }
    public static String convertUSDtoVND(float amount) {
        DecimalFormat decimalFormat = new DecimalFormat("#");
        double priceVND = amount * 23000;
        String roundedValue = decimalFormat.format(priceVND);
        return roundedValue;
    }
}
