package com.kursmania.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogInFilter implements Filter {

    private HttpServletRequest httpRequest;
    private HttpServletResponse httpResponse;

    private static final String[] LOG_IN_REQUIRED_URLS = {
        "/kupovina", "/potvrda", "/nalog", "izmenaNaloga", "lekcija", "odjava", "azuriranjeSlike", "dodavanjeKomentara", "dodavanjeOcene", "promenaOcene", "obrisiKomentar",
        "obrisiOcenu", "dodavanjeKursa", "pregledKursa"
    };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        httpRequest = (HttpServletRequest) request;
        httpResponse = (HttpServletResponse) response;

        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        httpResponse.setHeader("Pragma", "no-cache");
        httpResponse.setDateHeader("Expires", 0);

        String putanja = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("korisnik") != null);

        String loginURI = httpRequest.getContextPath() + "/prijava";
        String registrationURI = httpRequest.getContextPath() + "/registracija";

        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
        boolean isLoginPage = httpRequest.getRequestURI().endsWith("prijava.jsp");
        boolean isRegistrationRequest = httpRequest.getRequestURI().equals(registrationURI);
        boolean isRegistrationPage = httpRequest.getRequestURI().endsWith("registracija.jsp");

        if (isLoggedIn && (isLoginRequest || isLoginPage || isRegistrationRequest || isRegistrationPage)) {
            httpResponse.sendRedirect("nalog");
        } else if (!isLoggedIn && isLoginRequired()) {
            httpResponse.sendRedirect("prijava");
        } else {
            chain.doFilter(request, response);
        }

    }

    private boolean isLoginRequired() {
        String requestURL = httpRequest.getRequestURL().toString();

        for (String loginRequiredURL : LOG_IN_REQUIRED_URLS) {
            if (requestURL.contains(loginRequiredURL)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

}
