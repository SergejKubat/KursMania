package com.kursmania.controllers;

import com.kursmania.sessions.KategorijaFacade;
import com.kursmania.sessions.KorisnikFacade;
import com.kursmania.sessions.KursFacade;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControllerServlet extends HttpServlet {

    @EJB
    private KursFacade kursFacade;

    @EJB
    private KategorijaFacade kategorijaFacade;
    
    @EJB
    private KorisnikFacade korisnikFacade;

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String putanja = request.getServletPath();
        HttpSession session = request.getSession();

        if (putanja.equals("/pretraga")) {
            String q = (String) request.getParameter("q");
            System.out.println(kursFacade.findAll().stream().filter(e -> e.getKursIme().contains(q)).collect(Collectors.toList()));
            request.setAttribute("kursevi", kursFacade.findAll().stream().filter(e -> e.getKursIme().toLowerCase().contains(q.toLowerCase())).collect(Collectors.toList()));
        } else if (putanja.equals("/prijava")) {

        } else if (putanja.equals("/registracija")) {

        } else if (putanja.equals("/kurs")) {

        } else if (putanja.equals("/kursevi")) {
            request.setAttribute("kursevi", kursFacade.findAll());
        } else if (putanja.equals("/instruktori")) {
            request.setAttribute("instruktori", korisnikFacade.findAll().stream().filter(e -> e.getRolaId().getRolaId() == 2).collect(Collectors.toList()));
        } else if (putanja.equals("/instruktor")) {     
            int instruktorId = Integer.parseInt((String) request.getParameter("id"));
            request.setAttribute("instruktor", korisnikFacade.find(instruktorId));
            request.setAttribute("kursevi", korisnikFacade.find(instruktorId).getKursCollection());

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
        HttpSession session = request.getSession();

        if (putanja.equals("prijava")) {
            
        } else if (putanja.equals("registracija")) {
            String ime = request.getParameter("ime");
            String prezime = request.getParameter("prezime");
            String email = request.getParameter("email");
            String brojTelefona = request.getParameter("brt");
            String mesto = request.getParameter("mesto");
            String adresa = request.getParameter("adresa");
            String lozinka = request.getParameter("lozinka");
            System.out.println("korisnik: " + ime + " " + prezime + " " + email + " " + lozinka);
        } else if (putanja.equals("kupovina")) {
            
        }

        String url = "/WEB-INF/view" + putanja + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (IOException | ServletException ex) {
            System.out.println(ex.toString());
        }
    }

}
