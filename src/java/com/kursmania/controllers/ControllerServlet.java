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
import com.kursmania.jpa.entities.Tag;
import com.kursmania.sessions.JezikFacade;
import com.kursmania.sessions.KategorijaFacade;
import com.kursmania.sessions.KorisnikFacade;
import com.kursmania.sessions.KursFacade;
import com.kursmania.sessions.TagFacade;
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

    @EJB
    private TagFacade tagFacade;

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
            /* dodavanje stilova i skripti */

            stilovi.add("news");
            stilovi.add("news_responsive");
            stilovi.add("courses");
            stilovi.add("courses_responsive");

            navigationSelector = 2;

            String q = (String) request.getParameter("q");
            String oblast = (String) request.getParameter("oblast");

            List<Kurs> kursevi = kursFacade.findAll();
            List<Kurs> filtriraniKursevi;

            if (oblast != null) {
                filtriraniKursevi = kursevi.stream().filter(e -> e.getKategorijaId().getKategorijaNaziv().equals(oblast)).collect(Collectors.toList());
            }
            filtriraniKursevi = kursevi.stream().filter(e -> e.getKursIme().toLowerCase().contains(q.toLowerCase())
                    || isContainsTag(q, e.getKursTagCollection())).collect(Collectors.toList());

            Collections.sort(kursevi);

            if (filtriraniKursevi.isEmpty()) {
                request.setAttribute("poruka", "Nazalost, nije pronadjen ni jedan kurs.");
            } else {
                Collections.sort(filtriraniKursevi);
                request.setAttribute("kursevi", filtriraniKursevi);
            }

            request.setAttribute("najpopularnijiKursevi", kursevi);
        } else if (putanja.equals("/prijava")) {
            /* dodavanje stilova i skripti */

            stilovi.add("contact");
            stilovi.add("contact_responsive");
        } else if (putanja.equals("/registracija")) {
            /* dodavanje stilova i skripti */

            stilovi.add("contact");
            stilovi.add("contact_responsive");
        } else if (putanja.equals("/kurs")) {
            /* dodavanje stilova i skripti */

            stilovi.add("shop-item");
            stilovi.add("courses");
            stilovi.add("courses_responsive");

            skripte.add("video-player");

            navigationSelector = 2;

            String kursId = request.getParameter("id");

            if (kursId != null) {
                Kurs kurs = kursFacade.find(Integer.parseInt(kursId));
                kurs.getKorisnikId().setKorisnikOpis(getShortenText(kurs.getKorisnikId().getKorisnikOpis(), 50));

                request.setAttribute("kurs", kurs);

                int instruktorBrojStudenata = 0, instruktorBrojOcena = 0, instruktorZbirOcena = 0, instruktorZvezdice, kursBrojLekcija = 0,
                        duzinaKursa = 0, kursBrojStudenata, kursBrojOcena, kursZbirOcena = 0, kursZvezdice, kursBrojKomentara;
                double instruktorProsecnaOcena, kursProsecnaOcena;
                Collection<Kurs> kursevi = korisnikFacade.find(kurs.getKorisnikId().getKorisnikId()).getKursCollection();

                instruktorBrojStudenata = kursevi.stream().map((k) -> k.getEvidencijaCollection().size()).reduce(instruktorBrojStudenata, Integer::sum);
                instruktorBrojOcena = kursevi.stream().map((k) -> k.getOcenaCollection().size()).reduce(instruktorBrojOcena, Integer::sum);

                for (Kurs k : kursevi) {
                    Collection<Ocena> ocene = k.getOcenaCollection();
                    instruktorZbirOcena = ocene.stream().map((o) -> o.getOcenaVrednost()).reduce(instruktorZbirOcena, Integer::sum);
                }

                instruktorProsecnaOcena = (double) instruktorZbirOcena / instruktorBrojOcena;
                instruktorZvezdice = instruktorZbirOcena / instruktorBrojOcena;

                request.setAttribute("brojStudenata", instruktorBrojStudenata);
                request.setAttribute("brojOcena", instruktorBrojOcena);
                request.setAttribute("prosecnaOcena", String.format("%.2f", instruktorProsecnaOcena));
                request.setAttribute("zvezdice", instruktorZvezdice);

                kursBrojLekcija = kurs.getSekcijaCollection().stream().map((s) -> s.getLekcijaCollection().size()).reduce(kursBrojLekcija, Integer::sum);

                request.setAttribute("brojLekcija", kursBrojLekcija);

                for (Sekcija s : kurs.getSekcijaCollection()) {
                    for (Lekcija l : s.getLekcijaCollection()) {
                        duzinaKursa = l.getVideoCollection().stream().map((v) -> v.getVideoDuzinaTrajanja()).reduce(duzinaKursa, Integer::sum);
                    }
                }

                request.setAttribute("duzinaKursa", numberToTime(duzinaKursa));

                kursBrojStudenata = kurs.getEvidencijaCollection().size();
                kursBrojOcena = kurs.getOcenaCollection().size();
                kursZbirOcena = kurs.getOcenaCollection().stream().map((o) -> o.getOcenaVrednost()).reduce(kursZbirOcena, Integer::sum);

                kursProsecnaOcena = (double) kursZbirOcena / kursBrojOcena;
                kursZvezdice = kursZbirOcena / kursBrojOcena;

                request.setAttribute("kursBrojStudenata", kursBrojStudenata);
                request.setAttribute("kursBrojOcena", kursBrojOcena);
                request.setAttribute("kursProsecnaOcena", String.format("%.2f", kursProsecnaOcena));
                request.setAttribute("kursZvezdice", kursZvezdice);

                Collection<Sekcija> sekcije = kurs.getSekcijaCollection();

                request.setAttribute("sekcije", sekcije);

                Collection<KursTag> kursTag = kurs.getKursTagCollection();

                request.setAttribute("tagovi", kursTag);

                Collection<Komentar> komentari = kurs.getKomentarCollection();

                request.setAttribute("komentari", komentari);

                kursBrojKomentara = komentari.size();
                request.setAttribute("kursBrojKomentara", kursBrojKomentara);

                List<Kurs> preporuceniKursevi = kursFacade.findAll().stream().filter(e -> Objects.equals(e.getKategorijaId().getKategorijaId(), kurs.getKategorijaId().getKategorijaId())).collect(Collectors.toList());
                preporuceniKursevi.remove(kurs);
                if (preporuceniKursevi.size() > 2) {
                    preporuceniKursevi = preporuceniKursevi.subList(0, 2);
                }

                request.setAttribute("preporuceniKursevi", preporuceniKursevi);
            } else {
                putanja = "/kursevi";
            }
        } else if (putanja.equals("/kursevi")) {
            /* dodavanje stilova i skripti */

            stilovi.add("courses");
            stilovi.add("courses_responsive");

            skripte.add("preporuke");

            navigationSelector = 2;

            String page = (String) request.getParameter("page");

            int pageIndex;

            if (page != null) {
                pageIndex = Integer.parseInt(page);
            } else {
                pageIndex = 1;
            }

            int brojKurseva, brojStranica;
            List<Kurs> kursevi = kursFacade.findAll();

            brojKurseva = kursevi.size();
            brojStranica = brojKurseva < 9 ? 1 : brojKurseva / 9;
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

            request.setAttribute("brojStranica", brojStranica);
            request.setAttribute("index", pageIndex);
        } else if (putanja.equals("/instruktori")) {
            /* dodavanje stilova i skripti */

            stilovi.add("shop-item");
            stilovi.add("courses");
            stilovi.add("courses_responsive");

            navigationSelector = 3;

            String page = request.getParameter("page");

            int pageIndex;

            if (page != null) {
                pageIndex = Integer.parseInt(page);
            } else {
                pageIndex = 1;
            }

            int brojInstruktora, brojStranica;
            List<Korisnik> instruktori = korisnikFacade.findAll().stream().filter(e -> e.getRolaId().getRolaId() == 2).collect(Collectors.toList());

            brojInstruktora = instruktori.size();
            brojStranica = brojInstruktora < 9 ? 1 : brojInstruktora / 9;
            instruktori = paginationHelper(pageIndex, 9, instruktori);

            request.setAttribute("instruktori", instruktori);
            request.setAttribute("brojStranica", brojStranica);
            request.setAttribute("index", pageIndex);
        } else if (putanja.equals("/instruktor")) {
            /* dodavanje stilova i skripti */

            stilovi.add("shop-item");
            stilovi.add("courses");
            stilovi.add("courses_responsive");

            navigationSelector = 3;

            String instruktorId = request.getParameter("id");

            if (instruktorId != null) {
                Korisnik instruktor = korisnikFacade.find(Integer.parseInt(instruktorId));
                Collection<Kurs> kursevi = instruktor.getKursCollection();

                request.setAttribute("instruktor", instruktor);
                request.setAttribute("kursevi", kursevi);

                int brojStudenata = 0, brojOcena = 0, zbirOcena = 0, zvezdice;
                double prosecnaOcena;

                brojStudenata = kursevi.stream().map((k) -> k.getEvidencijaCollection().size()).reduce(brojStudenata, Integer::sum);
                brojOcena = kursevi.stream().map((k) -> k.getOcenaCollection().size()).reduce(brojOcena, Integer::sum);

                request.setAttribute("brojStudenata", brojStudenata);
                request.setAttribute("brojOcena", brojOcena);

                for (Kurs k : kursevi) {
                    zbirOcena = k.getOcenaCollection().stream().map((o) -> o.getOcenaVrednost()).reduce(zbirOcena, Integer::sum);
                }

                prosecnaOcena = (double) zbirOcena / brojOcena;
                zvezdice = zbirOcena / brojOcena;

                request.setAttribute("prosecnaOcena", String.format("%.2f", prosecnaOcena));
                request.setAttribute("zvezdice", zvezdice);
            }
        } else if (putanja.equals("/nalog")) {

        } else if (putanja.equals("/korpa")) {

        } else if (putanja.equals("/kupovina")) {
            /* dodavanje stilova i skripti */

            stilovi.add("contact");
            stilovi.add("contact_responsive");
            stilovi.add("payment");

        } else if (putanja.equals("/potvrda")) {

        } else if (putanja.equals("/kategorija")) {
            /* dodavanje stilova i skripti */

            stilovi.add("courses");
            stilovi.add("courses_responsive");

            String kategorijaId = request.getParameter("id");

            if (kategorijaId != null) {
                String page = request.getParameter("page");

                int pageIndex;

                if (page != null) {
                    pageIndex = Integer.parseInt(page);
                } else {
                    pageIndex = 1;
                }

                Kategorija kategorija = kategorijaFacade.find(Integer.parseInt(kategorijaId));
                List<Kurs> kursevi = kursFacade.findAll().stream().filter(e -> e.getKategorijaId().equals(kategorija)).collect(Collectors.toList());

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
            }
        } else if (putanja.equals("/jezik")) {

        } else if (putanja.equals("/kontakt")) {
            /* dodavanje stilova i skripti */

            stilovi.add("contact");
            stilovi.add("contact_responsive");

            skripte.add("contact");

            navigationSelector = 4;
        } else if (putanja.equals("/onama")) {
            /* dodavanje stilova i skripti */

            stilovi.add("about");
            stilovi.add("about_responsive");

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
            } else {

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

    public boolean isContainsTag(String tag, Collection<KursTag> tagovi) {
        boolean contains = false;
        for (KursTag kt : tagovi) {
            Tag t = tagFacade.find(kt.getTagId().getTagId());
            if (t.getTagIme().equals(tag)) {
                contains = true;
            }
        }
        return contains;
    }

}
