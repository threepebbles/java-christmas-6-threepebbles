package christmas.utils;

public class Converter {

    public static String intToLocaleString(int i) {
        return String.format("%,d", i);
    }
}