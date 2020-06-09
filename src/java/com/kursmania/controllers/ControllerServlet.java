package com.kursmania.controllers;

import com.kursmania.jpa.entities.Kategorija;
import com.kursmania.jpa.entities.Komentar;
import com.kursmania.jpa.entities.Korisnik;
import com.kursmania.jpa.entities.Kurs;
import com.kursmania.jpa.entities.KursTag;
import com.kursmania.jpa.entities.Lekcija;
import com.kursmania.jpa.entities.Ocena;
import com.kursmania.jpa.entities.Rola;
import com.kursmania.jpa.entities.Sekcija;
import com.kursmania.jpa.entities.Video;
import com.kursmania.sessions.JezikFacade;
import com.kursmania.sessions.KategorijaFacade;
import com.kursmania.sessions.KorisnikFacade;
import com.kursmania.sessions.KursFacade;
import com.kursmania.utils.HashUtil;
import com.kursmania.utils.Validation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
        
        List<String> stilovi = new ArrayList<>();
        List<String> skripte = new ArrayList<>();
        int navigationSelector = 0;

        if (putanja.equals("/pretraga")) {
            String q = (String) request.getParameter("q");
            String oblast = (String) request.getParameter("oblast");
            List<Kurs> kursevi = kursFacade.findAll();
            List<Kurs> filtriraniKursevi;
            if (oblast == null) {
                filtriraniKursevi = kursevi.stream().filter(e -> e.getKursIme().toLowerCase().contains(q.toLowerCase())).collect(Collectors.toList());
            } else {
                filtriraniKursevi = kursevi.stream().filter(e -> e.getKursIme().toLowerCase().contains(q.toLowerCase())
                        && e.getKategorijaId().getKategorijaNaziv().equals(oblast)).collect(Collectors.toList());
            }
            Collections.sort(kursevi);
            if (filtriraniKursevi.isEmpty()) {
                request.setAttribute("poruka", "Nazalost, nije pronadjen ni jedan kurs.");
            } else {
                Collections.sort(filtriraniKursevi);
                request.setAttribute("kursevi", filtriraniKursevi);
            }
            request.setAttribute("najpopularnijiKursevi", kursevi);
        } else if (putanja.equals("/prijava")) {

        } else if (putanja.equals("/registracija")) {

        } else if (putanja.equals("/kurs")) {
            navigationSelector = 2;
            int kursId = Integer.parseInt(request.getParameter("id"));
            Kurs kurs = kursFacade.find(kursId);
            kurs.getKorisnikId().setKorisnikOpis(getShortenText(kurs.getKorisnikId().getKorisnikOpis(), 50));
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
            request.setAttribute("brojOcena", brO);
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
            Collection<KursTag> kursTag = kurs.getKursTagCollection();
            request.setAttribute("tagovi", kursTag);
            Collection<Komentar> komentari = kurs.getKomentarCollection();
            request.setAttribute("komentari", komentari);
            //preporuceni kursevi
            List<Kurs> preporuceniKursevi = kursFacade.findAll().stream().filter(e -> Objects.equals(e.getKategorijaId().getKategorijaId(), kurs.getKategorijaId().getKategorijaId())).collect(Collectors.toList());
            preporuceniKursevi.remove(kurs);
            if (preporuceniKursevi.size() > 2) {
                preporuceniKursevi = preporuceniKursevi.subList(0, 2);
            }
            request.setAttribute("preporuceniKursevi", preporuceniKursevi);
        } else if (putanja.equals("/kursevi")) {
            navigationSelector = 2;
            String page = (String) request.getParameter("page");
            int pageIndex;
            try {
                pageIndex = Integer.parseInt(page);
            } catch (NumberFormatException ex) {
                pageIndex = 1;
            }
            List<Kurs> kursevi = kursFacade.findAll();
            int brojKurseva = kursevi.size();
            kursevi = paginationHelper(pageIndex, 9, kursevi);
            if (kursevi != null) {
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
            } else {
                request.setAttribute("poruka", "Nije pronadjen ni jedan kurs na ovoj stranici!");
            }
            request.setAttribute("brojStranica", brojKurseva < 9 ? 1 : brojKurseva / 9);
            request.setAttribute("index", pageIndex);
        } else if (putanja.equals("/instruktori")) {
            navigationSelector = 3;
            int pageIndex;
            try {
                pageIndex = Integer.parseInt((String) request.getAttribute("page"));
            } catch (NumberFormatException ex) {
                pageIndex = 1;
            }
            List<Korisnik> instruktori = korisnikFacade.findAll().stream().filter(e -> e.getRolaId().getRolaId() == 2).collect(Collectors.toList());
            int brojInstruktora = instruktori.size();
            instruktori = paginationHelper(pageIndex, 9, instruktori);
            request.setAttribute("instruktori", instruktori);
            request.setAttribute("brojStranica", brojInstruktora < 9 ? 1 : brojInstruktora / 9);
            request.setAttribute("index", pageIndex);
        } else if (putanja.equals("/instruktor")) {
            navigationSelector = 3;
            int instruktorId = Integer.parseInt((String) request.getParameter("id"));
            request.setAttribute("instruktor", korisnikFacade.find(instruktorId));
            request.setAttribute("kursevi", korisnikFacade.find(instruktorId).getKursCollection());
            Collection<Kurs> kursevi = korisnikFacade.find(instruktorId).getKursCollection();
            int brSt = 0, brO = 0;
            brSt = kursevi.stream().map((k) -> k.getEvidencijaCollection().size()).reduce(brSt, Integer::sum);
            brO = kursevi.stream().map((k) -> k.getOcenaCollection().size()).reduce(brO, Integer::sum);
            request.setAttribute("brojStudenata", brSt);
            request.setAttribute("brojOcena", brO);
            //zvezdice
            int zbirOcena = 0;
            for (Kurs k : kursevi) {
                for (Ocena o : k.getOcenaCollection()) {
                    zbirOcena += o.getOcenaVrednost();
                }
            }
            request.setAttribute("prosecnaOcena", String.format("%.2f", (double) zbirOcena / brO));
            request.setAttribute("zvezdice", zbirOcena / brO);
        } else if (putanja.equals("/nalog")) {

        } else if (putanja.equals("/korpa")) {

        } else if (putanja.equals("/kupovina")) {

        } else if (putanja.equals("/potvrda")) {

        } else if (putanja.equals("/kategorija")) {
            int kategorijaId = Integer.parseInt((String) request.getParameter("id"));
            int pageIndex;
            try {
                pageIndex = Integer.parseInt((String) request.getAttribute("page"));
            } catch (NumberFormatException ex) {
                pageIndex = 1;
            }
            Kategorija kategorija = kategorijaFacade.find(kategorijaId);
            List<Kurs> kursevi = kursFacade.findAll().stream().filter(e -> e.getKategorijaId().getKategorijaId() == kategorijaId).collect(Collectors.toList());
            int brojKurseva = kursevi.size();
            kursevi = paginationHelper(pageIndex, 9, kursevi);
            Kurs istaknut = null;
            int max = 0;
            for (Kurs k : kursevi) {
                if (k.getEvidencijaCollection().size() > max) {
                    istaknut = k;
                    max = k.getEvidencijaCollection().size();
                }
            }
            kursevi.remove(istaknut);
            request.setAttribute("kat", kategorija);
            request.setAttribute("istaknut", istaknut);
            request.setAttribute("kursevi", kursevi);
            request.setAttribute("brojStranica", brojKurseva < 9 ? 1 : brojKurseva / 9);
            request.setAttribute("index", pageIndex);
        } else if (putanja.equals("/jezik")) {

        } else if (putanja.equals("/kontakt")) {
            navigationSelector = 4;
        } else if (putanja.equals("/onama")) {
            navigationSelector = 1;
            request.setAttribute("brojKurseva", kursFacade.findAll().size());
            request.setAttribute("brojStudenata", korisnikFacade.findAll().stream().filter(e -> e.getRolaId().getRolaId() == 1).collect(Collectors.toList()).size());
            request.setAttribute("brojInstruktora", korisnikFacade.findAll().stream().filter(e -> e.getRolaId().getRolaId() == 2).collect(Collectors.toList()).size());
            request.setAttribute("brojJezika", jezikFacade.findAll().size());
            request.setAttribute("instruktori", korisnikFacade.findAll().stream().filter(e -> e.getRolaId().getRolaId() == 2).collect(Collectors.toList()));
        }

        request.setAttribute("kategorije", kategorijaFacade.findAll());
        request.setAttribute("stilovi", stilovi);
        request.setAttribute("skripte", skripte);
        request.setAttribute("navigationSelector", navigationSelector);

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

        if (putanja.equals("/prijava")) {
            String email = request.getParameter("email");
            String lozinka = request.getParameter("lozinka");
            System.out.println("korisnik:" + email + " " + lozinka);
        } else if (putanja.equals("/registracija")) {
            String ime = request.getParameter("ime");
            String prezime = request.getParameter("prezime");
            String email = request.getParameter("email");
            String brojTelefona = request.getParameter("brt");
            String mesto = request.getParameter("mesto");
            String adresa = request.getParameter("adresa");
            String lozinka = request.getParameter("lozinka");
            boolean valid = Validation.proveriIme(ime) && Validation.proveriIme(prezime) && Validation.proveriBrojTelefona(brojTelefona) && Validation.proveriEmail(email);
            if (valid) {
                Korisnik korisnik = new Korisnik();
                Rola rola = new Rola(1);
                rola.setRolaNaziv("Student");
                korisnik.setRolaId(rola);
                korisnik.setKorisnikIme(ime);
                korisnik.setKorisnikPrezime(prezime);
                korisnik.setKorisnikEmail(email);
                korisnik.setKorisnikBrojTelefona(brojTelefona);
                korisnik.setKorisnikMesto(mesto);
                korisnik.setKorisnikAdresa(adresa);
                korisnik.setKorisnikLozinka(HashUtil.getSecurePassword(lozinka, "SHA-256"));
                korisnikFacade.create(korisnik);
            }
            else {
                
            }
        } else if (putanja.equals("/kupovina")) {

        } else if (putanja.equals("/kontakt")) {
            request.setAttribute("poruka", true);
        }

        String url = "/WEB-INF/view" + putanja + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (IOException | ServletException ex) {
            System.out.println(ex.toString());
        }
    }

    public static String getShortenText(String text, int numOfWords) {
        String[] reci = text.split(" ");
        if (reci.length < numOfWords) {
            return text;
        }
        StringBuilder noviText = new StringBuilder();
        for (int i = 0; i < numOfWords; i++) {
            noviText.append(reci[i]).append(" ");
        }
        noviText.deleteCharAt(noviText.length() - 1);
        return noviText.append("...").toString();
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
            } else {
                time.append(hours).append(":");
            }
        }
        if (minutes > 0) {
            if (minutes < 10) {
                time.append("0").append(minutes).append(":");
            } else {
                time.append(minutes).append(":");
            }
        }
        if (seconds > 0) {
            if (seconds < 10) {
                time.append("0").append(seconds);
            } else {
                time.append(seconds);
            }
        }
        return time.toString();
    }

    public <T> List<T> paginationHelper(int pageIndex, int itemsPerPage, List<T> list) {
        int startIndex, endIndex;
        startIndex = (pageIndex - 1) * itemsPerPage;
        endIndex = pageIndex * itemsPerPage;
        if (startIndex >= list.size()) {
            return null;
        }
        if (endIndex >= list.size()) {
            endIndex = list.size();
        }
        return list.subList(startIndex, endIndex);
    }

}
