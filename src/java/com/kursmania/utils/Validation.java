package com.kursmania.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    private static final String REGEX_IME = "[a-zA-Z]{3,32}";
    private static final String REGEX_LOZINKA = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$";
    private static final String REGEX_EMAIL = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    private static final String REGEX_BROJ_TELEFONA = "[0-9]{3}-[0-9]{3}-[0-9]{3,4}";
    private static final String REGEX_BROJ_KARTICE = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$";
    private static final String REGEX_CVV = "[0-9]{3,4}";
    private static final String REGEX_KUPON = "[A-Z0-9]{8}";

    public static boolean proveriIme(String ime) {
        return Pattern.matches(REGEX_IME, ime);
    }

    public static boolean proveriLozinku(String lozinka) {
        return Pattern.matches(REGEX_LOZINKA, lozinka);
    }

    public static boolean proveriEmail(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(REGEX_EMAIL, Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    public static boolean proveriBrojTelefona(String brojTelefona) {
        return Pattern.matches(REGEX_BROJ_TELEFONA, brojTelefona);
    }
    
    public static boolean proveriBrojKartice(String brojKartice) {
        return Pattern.matches(REGEX_BROJ_KARTICE, brojKartice);
    }
    
    public static boolean proveriCVV(String cvv) {
        return Pattern.matches(REGEX_CVV, cvv);
    }
    
    public static boolean proveriKupon(String kupon) {
        return Pattern.matches(REGEX_KUPON, kupon);
    }
}
