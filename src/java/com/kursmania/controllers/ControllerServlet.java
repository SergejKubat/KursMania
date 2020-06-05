package com.kursmania.controllers;

import com.kursmania.jpa.entities.Kurs;
import com.kursmania.jpa.entities.Lekcija;
import com.kursmania.jpa.entities.Ocena;
import com.kursmania.jpa.entities.Sekcija;
import com.kursmania.jpa.entities.Video;
import com.kursmania.sessions.JezikFacade;
import com.kursmania.sessions.KategorijaFacade;
import com.kursmania.sessions.KorisnikFacade;
import com.kursmania.sessions.KursFacade;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
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
            List<Kurs> kursevi = kursFacade.findAll();
            Collections.sort(kursevi);
            request.setAttribute("kursevi", kursevi.stream().filter(e -> e.getKursIme().toLowerCase().contains(q.toLowerCase())).collect(Collectors.toList()));
            request.setAttribute("najpopularnijiKursevi", kursevi);
        } else if (putanja.equals("/prijava")) {

        } else if (putanja.equals("/registracija")) {

        } else if (putanja.equals("/kurs")) {
            int kursId = Integer.parseInt(request.getParameter("id"));
            Kurs kurs = kursFacade.find(kursId);
            kurs.getKorisnikId().setKorisnikOpis(getShortenText(kurs.getKorisnikId().getKorisnikOpis()));
            request.setAttribute("kurs", kurs);
            Collection<Kurs> kursevi = korisnikFacade.find(kurs.getKorisnikId().getKorisnikId()).getKursCollection();
            int brSt = 0, brO = 0, zbirO = 0, brLekcija = 0, duzinaKursa = 0;
            brSt = kursevi.stream().map((k) -> k.getEvidencijaCollection().size()).reduce(brSt, Integer::sum);
            brO = kursevi.stream().map((k) -> k.getOcenaCollection().size()).reduce(brO, Integer::sum);
            for (Kurs k : kursevi) {
                Collection<Ocena> ocene = k.getOcenaCollection();
                for (Ocena o : ocene) {
                    zbirO += o.getOcenaVrednost();
                }
            }
            request.setAttribute("brojStudenata", brSt);
            request.setAttribute("prosecnaOcena", String.format("%.2f", (double) zbirO / brO));
            request.setAttribute("zvezdice", zbirO / brO);
            brLekcija = kurs.getSekcijaCollection().stream().map((s) -> s.getLekcijaCollection().size()).reduce(brLekcija, Integer::sum);
            request.setAttribute("brojLekcija", brLekcija);
            for (Sekcija s : kurs.getSekcijaCollection()) {
                for (Lekcija l : s.getLekcijaCollection()) {
                    for (Video v : l.getVideoCollection()) {
                        duzinaKursa += v.getVideoDuzinaTrajanja();
                    }
                }
            }
            request.setAttribute("duzinaKursa", numberToTime(duzinaKursa));
            int kursBrojOcena, kursZbirOcena = 0;
            kursBrojOcena = kurs.getOcenaCollection().size();
            kursZbirOcena = kurs.getOcenaCollection().stream().map((o) -> o.getOcenaVrednost()).reduce(kursZbirOcena, Integer::sum);
            request.setAttribute("kursBrojOcena", kursBrojOcena);
            request.setAttribute("kursProsecnaOcena", String.format("%.2f", (double) kursZbirOcena / kursBrojOcena));
            request.setAttribute("kursZvezdice", kursZbirOcena / kursBrojOcena);
            Collection<Sekcija> sekcije = kurs.getSekcijaCollection();
            request.setAttribute("sekcije", sekcije);
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

        } else if (putanja.equals("kontakt")) {
            request.setAttribute("poruka", true);
        }

        String url = "/WEB-INF/view" + putanja + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (IOException | ServletException ex) {
            System.out.println(ex.toString());
        }
    }

    public String getShortenText(String text) {
        String[] reci = text.split(" ");
        if (reci.length > 50) {
            return text;
        }
        StringBuilder noviText = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            noviText.append(reci[i]);
        }
        return noviText.toString();
    }
    
    public String numberToTime(int number) {
        int hours, minutes, seconds;
        StringBuilder time = new StringBuilder();
        hours = number / 3600;
        minutes = (number % 3600) / 60;
        seconds = number - (hours * 3600 + minutes * 60);
        if (hours > 0) {
            if (hours < 10) {
                time.append("0").append(hours).append(":");
            }
            else {
                time.append(hours).append(":");
            }
        }
        if (minutes > 0) {
            if (minutes < 10) {
                time.append("0").append(minutes).append(":");
            }
            else {
                time.append(minutes).append(":");
            }
        }
        if (seconds > 0) {
            if (seconds < 10) {
                time.append("0").append(seconds);
            }
            else {
                time.append(seconds);
            }
        }
        return time.toString();
    }

}
