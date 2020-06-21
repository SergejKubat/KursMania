package com.kursmania.controllers;

import com.kursmania.jpa.entities.Komentar;
import com.kursmania.jpa.entities.Korisnik;
import com.kursmania.jpa.entities.Kurs;
import com.kursmania.jpa.entities.Ocena;
import com.kursmania.jpa.entities.Tag;
import com.kursmania.sessions.KomentarFacade;
import com.kursmania.sessions.KursFacade;
import com.kursmania.sessions.OcenaFacade;
import com.kursmania.sessions.TagFacade;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AJAXControllerServlet extends HttpServlet {

    @EJB
    private KursFacade kursFacade;

    @EJB
    private TagFacade tagFacade;

    @EJB
    private KomentarFacade komentarFacade;

    @EJB
    private OcenaFacade ocenaFacade;

    private List<Tag> tagovi;

    private List<Kurs> kursevi;

    private List<String> reci;

    Korisnik korisnik;

    @Override
    public void init() throws ServletException {
        tagovi = tagFacade.findAll();
        kursevi = kursFacade.findAll();
        reci = new ArrayList<>();
        tagovi.forEach((t) -> {
            reci.add(t.getTagIme());
        });
        kursevi.forEach((k) -> {
            reci.add(k.getKursIme());
        });
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String q = (String) request.getParameter("q");
        List<String> preporuke = reci.stream().filter(e -> e.toLowerCase().contains(q.toLowerCase())).collect(Collectors.toList());
        if (preporuke.size() > 5) {
            preporuke = preporuke.subList(0, 5);
        }

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        for (String preporuka : preporuke) {
            response.getWriter().write(preporuka + "|");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String putanja = req.getServletPath();

        if (putanja.equals("/obrisiKomentar")) {

            int komentarId = Integer.parseInt((String) req.getParameter("id"));
            Komentar komentar = komentarFacade.find(komentarId);
            komentarFacade.remove(komentar);

        } else if (putanja.equals("/obrisiOcenu")) {

            int ocenaId = Integer.parseInt((String) req.getParameter("id"));
            Ocena ocena = ocenaFacade.find(ocenaId);
            ocenaFacade.remove(ocena);

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String putanja = req.getServletPath();
        HttpSession session = req.getSession();

        if (putanja.equals("/dodavanjeKomentara")) {

            String sadrzaj = req.getParameter("komentar");
            int kursId = Integer.parseInt((String) req.getParameter("kursId"));
            Kurs kurs = kursFacade.find(kursId);
            korisnik = (Korisnik) session.getAttribute("korisnik");

            LocalDateTime now = LocalDateTime.now();

            int g = now.getYear();
            int m = now.getMonthValue();
            int d = now.getDayOfMonth();
            int s = now.getHour();
            int min = now.getMinute();
            
            String mesec = m > 9 ? String.valueOf(m) : "0" + String.valueOf(m);
            String dan = d > 9 ? String.valueOf(d) : "0" + String.valueOf(d);
            String sat = s > 9 ? String.valueOf(s) : "0" + String.valueOf(s);
            String minut = min > 9 ? String.valueOf(min) : "0" + String.valueOf(min);

            Komentar komentar = new Komentar();
            komentar.setKorisnikId(korisnik);
            komentar.setKursId(kurs);
            komentar.setKomentarSadrzaj(sadrzaj);
            komentar.setKomentarDatum(mesec + "-" + dan + "-" + g);
            komentar.setKomentarVreme(sat + ":" + minut);

            komentarFacade.create(komentar);
        }
    }
}
