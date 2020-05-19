package com.kursmania.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerServlet extends HttpServlet {

    /*@EJB
    private RolaFacade rolaFacade;*/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String putanja = request.getServletPath();

        if (putanja.equals("/pretraga")) {
            // TODO: nesto
        } else if (putanja.equals("/prijava")) {

        } else if (putanja.equals("/registracija")) {

        } else if (putanja.equals("/kurs")) {

        } else if (putanja.equals("/instruktor")) {

        } else if (putanja.equals("/nalog")) {

        } else if (putanja.equals("/korpa")) {

        } else if (putanja.equals("/potvrda")) {

        } else if (putanja.equals("/jezik")) {

        } else if (putanja.equals("/kontakt")) {

        }
        else if (putanja.equals("/onama")) {

        }

        String url = "/WEB-INF/view" + putanja + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (IOException | ServletException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String putanja = request.getServletPath();

        if (putanja.equals("/dodajUKorpu")) {

        } else if (putanja.equals("/azurirajKorpu")) {

        } else if (putanja.equals("/kupovina")) {
            putanja = "potvrda";
        }

        String url = "/WEB-INF/view" + putanja + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (IOException | ServletException ex) {
            System.out.println(ex.toString());
        }
    }

}
