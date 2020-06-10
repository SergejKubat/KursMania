package com.kursmania.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    private static final String REGEX_IME = "[a-zA-Z0-9]{3,32}";
    private static final String REGEX_LOZINKA = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$";
    private static final String REGEX_EMAIL = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    private static final String REGEX_BROJ_TELEFONA = "[0-9]{3}-[0-9]{3}-[0-9]{3,4}";

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
}
