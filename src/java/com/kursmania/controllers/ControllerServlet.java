package com.kursmania.controllers;

import com.kursmania.sessions.KategorijaFacade;
import com.kursmania.sessions.KursFacade;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerServlet extends HttpServlet {

    @EJB
    private KursFacade kursFacade;

    @EJB
    private KategorijaFacade kategorijaFacade;

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String putanja = request.getServletPath();

        if (putanja.equals("/pretraga")) {

        } else if (putanja.equals("/prijava")) {

        } else if (putanja.equals("/registracija")) {

        } else if (putanja.equals("/kurs")) {

        } else if (putanja.equals("/kursevi")) {
            request.setAttribute("kursevi", kursFacade.findAll());
        } else if (putanja.equals("/instruktori")) {

        } else if (putanja.equals("/instruktor")) {

        } else if (putanja.equals("/nalog")) {

        } else if (putanja.equals("/korpa")) {

        } else if (putanja.equals("/kupovina")) {

        } else if (putanja.equals("/potvrda")) {

        } else if (putanja.equals("/kategorija")) {
            int kategorijaId = Integer.parseInt((String) request.getParameter("id"));
            request.setAttribute("kat", kategorijaFacade.find(kategorijaId));
            request.setAttribute("kursevi", kursFacade.findAll().stream().filter(e -> e.getKategorijaId().getKategorijaId() == kategorijaId).collect(Collectors.toList()));

        } else if (putanja.equals("/jezik")) {

        } else if (putanja.equals("/kontakt")) {

        } else if (putanja.equals("/onama")) {

        }
        
        request.setAttribute("kategorije", kategorijaFacade.findAll());

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
