package com.kursmania.controllers;

import com.kursmania.jpa.entities.Komentar;
import com.kursmania.jpa.entities.Korisnik;
import com.kursmania.jpa.entities.Kupon;
import com.kursmania.jpa.entities.Kurs;
import com.kursmania.jpa.entities.Ocena;
import com.kursmania.jpa.entities.Tag;
import com.kursmania.sessions.KomentarFacade;
import com.kursmania.sessions.KuponFacade;
import com.kursmania.sessions.KursFacade;
import com.kursmania.sessions.OcenaFacade;
import com.kursmania.sessions.TagFacade;
import com.kursmania.utils.Validation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
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

    @EJB
    private KuponFacade kuponFacade;

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
        String putanja = request.getServletPath();

        if (putanja.equals("/preporuke")) {

            String q = request.getParameter("q");
            List<String> preporuke = reci.stream().filter(e -> e.toLowerCase().contains(q.toLowerCase())).collect(Collectors.toList());
            if (preporuke.size() > 5) {
                preporuke = preporuke.subList(0, 5);
            }

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            for (String preporuka : preporuke) {
                response.getWriter().write(preporuka + "|");
            }

        } else if (putanja.equals("/proveraKupona")) {

            String sifra = request.getParameter("sifra");
            String id = request.getParameter("kurs");

            if (Validation.proveriKupon(sifra)) {
                int kursId = Integer.parseInt(id);
                Kurs kurs = kursFacade.find(kursId);
                Collection<Kupon> kuponi = kurs.getKuponCollection();
                
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                
                if (kuponi != null) {
                    Kupon kupon = null;
                    for (Kupon k : kuponi) {
                        if (sifra.equals(k.getKuponKod()) && k.getKuponIsIskoriscen() == 0) {
                            kupon = k;
                        }
                    }
                    if (kupon != null) {
                        response.getWriter().write(kupon.getKuponPopust().toString());
                    } else {
                        response.getWriter().write("0");
                    }
                } else {
                    response.getWriter().write("0");
                }
            } else {
                response.getWriter().write("0");
            }
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
        String putanja = req.getServletPath();
        HttpSession session = req.getSession();

        if (putanja.equals("/promenaOcene")) {
            BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));

            String podaci = br.readLine();

            String[] parametri = podaci.split("&");

            int ocenaId = Integer.parseInt(parametri[0].split("=")[1]);
            int ocenaVrednost = Integer.parseInt(parametri[1].split("=")[1]);
            int kursId = Integer.parseInt(parametri[2].split("=")[1]);

            Kurs kurs = kursFacade.find(kursId);
            korisnik = (Korisnik) session.getAttribute("korisnik");

            Ocena ocena = new Ocena();
            ocena.setOcenaId(ocenaId);
            ocena.setKorisnikId(korisnik);
            ocena.setKursId(kurs);
            ocena.setOcenaVrednost(ocenaVrednost);

            ocenaFacade.edit(ocena);
        }
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
        } else if (putanja.equals("/dodavanjeOcene")) {

            int ocenaVrednost = Integer.parseInt((String) req.getParameter("ocena"));
            int kursId = Integer.parseInt((String) req.getParameter("kursId"));
            Kurs kurs = kursFacade.find(kursId);
            korisnik = (Korisnik) session.getAttribute("korisnik");

            Ocena ocena = new Ocena();
            ocena.setKorisnikId(korisnik);
            ocena.setKursId(kurs);
            ocena.setOcenaVrednost(ocenaVrednost);

            ocenaFacade.create(ocena);
        }
    }
}
