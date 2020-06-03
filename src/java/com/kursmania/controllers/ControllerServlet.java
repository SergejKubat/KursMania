package com.kursmania.controllers;

import com.kursmania.jpa.entities.Kurs;
import com.kursmania.sessions.JezikFacade;
import com.kursmania.sessions.KategorijaFacade;
import com.kursmania.sessions.KorisnikFacade;
import com.kursmania.sessions.KursFacade;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
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
    
    @EJB
    private JezikFacade jezikFacade;

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String putanja = request.getServletPath();
        HttpSession session = request.getSession();
        response.setCharacterEncoding("UTF-8");

        if (putanja.equals("/pretraga")) {
            String q = (String) request.getParameter("q");
            request.setAttribute("kursevi", kursFacade.findAll().stream().filter(e -> e.getKursIme().toLowerCase().contains(q.toLowerCase())).collect(Collectors.toList()));
            request.setAttribute("najpopKursevi", 1);
        } else if (putanja.equals("/prijava")) {

        } else if (putanja.equals("/registracija")) {

        } else if (putanja.equals("/kurs")) {
            int kursId = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("kurs", kursFacade.find(kursId));
        } else if (putanja.equals("/kursevi")) {
            List<Kurs> kursevi = kursFacade.findAll();
            Kurs istaknut = null;
            int max = 0;
            for (Kurs k : kursevi) {
                if (k.getEvidencijaCollection().size() > max) {
                    istaknut = k;
                    max = k.getEvidencijaCollection().size();
                }
            }
            kursevi.remove(istaknut);
            request.setAttribute("istaknut", istaknut);
            request.setAttribute("kursevi", kursevi);
        } else if (putanja.equals("/instruktori")) {
            request.setAttribute("instruktori", korisnikFacade.findAll().stream().filter(e -> e.getRolaId().getRolaId() == 2).collect(Collectors.toList()));
        } else if (putanja.equals("/instruktor")) {     
            int instruktorId = Integer.parseInt((String) request.getParameter("id"));
            request.setAttribute("instruktor", korisnikFacade.find(instruktorId));
            request.setAttribute("kursevi", korisnikFacade.find(instruktorId).getKursCollection());
            Collection<Kurs> kursevi = korisnikFacade.find(instruktorId).getKursCollection();
            int brSt = 0, brO = 0;
            brSt = kursevi.stream().map((k) -> k.getEvidencijaCollection().size()).reduce(brSt, Integer::sum);
            brO = kursevi.stream().map((k) -> k.getOcenaCollection().size()).reduce(brO, Integer::sum);
            request.setAttribute("brojStudenata", brSt);
            request.setAttribute("brojOcena", brO);
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
            request.setAttribute("brojKurseva", kursFacade.findAll().size());
            request.setAttribute("brojStudenata", korisnikFacade.findAll().stream().filter(e -> e.getRolaId().getRolaId() == 1).collect(Collectors.toList()).size());
            request.setAttribute("brojInstruktora", korisnikFacade.findAll().stream().filter(e -> e.getRolaId().getRolaId() == 2).collect(Collectors.toList()).size());
            request.setAttribute("brojJezika", jezikFacade.findAll().size());
            request.setAttribute("instruktori", korisnikFacade.findAll().stream().filter(e -> e.getRolaId().getRolaId() == 2).collect(Collectors.toList()));
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
