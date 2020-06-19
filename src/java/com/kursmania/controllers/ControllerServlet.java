package com.kursmania.controllers;

import com.kursmania.jpa.entities.Evidencija;
import com.kursmania.jpa.entities.Kategorija;
import com.kursmania.jpa.entities.Komentar;
import com.kursmania.jpa.entities.Korisnik;
import com.kursmania.jpa.entities.Kurs;
import com.kursmania.jpa.entities.KursTag;
import com.kursmania.jpa.entities.Lekcija;
import com.kursmania.jpa.entities.Ocena;
import com.kursmania.jpa.entities.Rola;
import com.kursmania.jpa.entities.Sekcija;
import com.kursmania.sessions.JezikFacade;
import com.kursmania.sessions.KategorijaFacade;
import com.kursmania.sessions.KorisnikFacade;
import com.kursmania.sessions.KursFacade;
import com.kursmania.sessions.TagFacade;
import com.kursmania.utils.HashUtil;
import com.kursmania.utils.Utilities;
import com.kursmania.utils.Validation;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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

    private Utilities utilities;

    Korisnik korisnik;

    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("kategorije", kategorijaFacade.findAll());
        getServletContext().setAttribute("brojKurseva", kursFacade.findAll().size());
        getServletContext().setAttribute("brojStudenata", korisnikFacade.findAll().stream().filter(e -> e.getRolaId().getRolaId() == 1).collect(Collectors.toList()).size());
        getServletContext().setAttribute("brojInstruktora", korisnikFacade.findAll().stream().filter(e -> e.getRolaId().getRolaId() == 2).collect(Collectors.toList()).size());
        getServletContext().setAttribute("brojJezika", jezikFacade.findAll().size());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String putanja = request.getServletPath();
        HttpSession session = request.getSession();
        korisnik = (Korisnik) session.getAttribute("korisnik");
        response.setCharacterEncoding("UTF-8");
        utilities = new Utilities();

        if (putanja.equals("/pretraga")) {

            String q = request.getParameter("q");
            if (q != null) {
                initializePage(putanja, request, response);
                request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);
            } else {
                response.sendError(404);
            }

        } else if (putanja.equals("/prijava")) {

            response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
            initializePage(putanja, request, response);
            request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);

        } else if (putanja.equals("/registracija")) {

            initializePage(putanja, request, response);
            request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);

        } else if (putanja.equals("/kurs")) {

            String id = request.getParameter("id");
            if (id != null) {
                try {
                    int kursId = Integer.parseInt(id);
                    initializePage(putanja, request, response);
                    request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);
                } catch (NumberFormatException ex) {
                    response.sendError(404);
                }
            } else {
                response.sendError(404);
            }

        } else if (putanja.equals("/kursevi")) {

            initializePage(putanja, request, response);
            request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);

        } else if (putanja.equals("/instruktori")) {

            initializePage(putanja, request, response);
            request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);

        } else if (putanja.equals("/instruktor")) {

            String id = request.getParameter("id");
            if (id != null) {
                try {
                    int instruktorId = Integer.parseInt(id);
                    initializePage(putanja, request, response);
                    request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);
                } catch (NumberFormatException ex) {
                    response.sendError(404);
                }
            } else {
                response.sendError(404);
            }

        } else if (putanja.equals("/nalog")) {

            if (korisnik != null) {
                initializePage(putanja, request, response);
                request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);
            } else {
                response.sendError(404);
            }

        } else if (putanja.equals("/kupovina")) {

            if (korisnik != null) {
                initializePage(putanja, request, response);
                request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);
            } else {
                response.sendError(404);
            }

        } else if (putanja.equals("/potvrda")) {

        } else if (putanja.equals("/kategorija")) {

            String id = request.getParameter("id");
            if (id != null) {
                try {
                    int kategorijaId = Integer.parseInt(id);
                    initializePage(putanja, request, response);
                    request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);
                } catch (NumberFormatException ex) {
                    response.sendError(404);
                }
            } else {
                response.sendError(404);
            }

        } else if (putanja.equals("/jezik")) {

        } else if (putanja.equals("/kontakt")) {

            initializePage(putanja, request, response);
            request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);

        } else if (putanja.equals("/onama")) {

            initializePage(putanja, request, response);
            request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);

        } else if (putanja.equals("/odjava")) {

            session.invalidate();
            response.sendRedirect("prijava");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String putanja = request.getServletPath();
        HttpSession session = request.getSession();
        korisnik = (Korisnik) session.getAttribute("korisnik");

        if (putanja.equals("/prijava")) {

            initializePage(putanja, request, response);

            String email = request.getParameter("email");
            String lozinka = request.getParameter("lozinka");

            String kursId = request.getParameter("kursId");

            boolean valid = Validation.proveriEmail(email) && Validation.proveriLozinku(lozinka);

            if (valid) {
                Korisnik kor = null;
                List<Korisnik> korisnici = korisnikFacade.findAll();

                for (Korisnik k : korisnici) {
                    if (k.getKorisnikEmail().equals(email) && k.getKorisnikLozinka().equals(HashUtil.getSHA(lozinka))) {
                        kor = k;
                    }
                }

                if (kor != null) {
                    session.setAttribute("korisnik", kor);
                    if (kursId != null) {
                        response.sendRedirect("kupovina?id=" + Integer.parseInt(kursId));
                    } else {
                        response.sendRedirect("nalog");
                    }
                } else {
                    request.setAttribute("poruka", "Nalog sa ovim kredencijalima ne postoji.");
                    request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);
                }
            } else {
                request.setAttribute("poruka", "Podaci koje ste uneli nisu u validnom formatu.");
                request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);
            }

        } else if (putanja.equals("/registracija")) {

            initializePage(putanja, request, response);

            String ime = request.getParameter("ime");
            String prezime = request.getParameter("prezime");
            String email = request.getParameter("email");
            String brojTelefona = request.getParameter("brt");
            String mesto = request.getParameter("mesto");
            String adresa = request.getParameter("adresa");
            String lozinka = request.getParameter("lozinka");
            boolean valid = Validation.proveriIme(ime) && Validation.proveriIme(prezime) && Validation.proveriBrojTelefona(brojTelefona) && Validation.proveriEmail(email);
            if (valid) {
                Korisnik kor = null;
                List<Korisnik> korisnici = korisnikFacade.findAll();

                for (Korisnik k : korisnici) {
                    if (k.getKorisnikEmail().equals(email) || k.getKorisnikBrojTelefona().equals(brojTelefona)) {
                        kor = k;
                    }
                }

                if (kor == null) {
                    kor = new Korisnik();
                    Rola rola = new Rola(1);
                    rola.setRolaNaziv("Student");
                    kor.setRolaId(rola);
                    kor.setKorisnikIme(ime);
                    kor.setKorisnikPrezime(prezime);
                    kor.setKorisnikEmail(email);
                    kor.setKorisnikBrojTelefona(brojTelefona);
                    kor.setKorisnikMesto(mesto);
                    kor.setKorisnikAdresa(adresa);
                    kor.setKorisnikAvatar("resources/img/ostale/default_avatar.png");
                    kor.setKorisnikTitula("Dodajte titulu");
                    kor.setKorisnikOpis("Dodajte opis");

                    Date date = Calendar.getInstance().getTime();
                    DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
                    String datum = dateFormat.format(date);

                    kor.setKorisnikDatumRegistracije(datum);
                    kor.setKorisnikLozinka(HashUtil.getSHA(lozinka));
                    kor.setKorisnikIsBlocked(Short.MIN_VALUE);
                    korisnikFacade.create(kor);
                    response.sendRedirect("prijava");
                } else {
                    request.setAttribute("poruka", "Nalog sa ovim mejlom ili brojem telefona vec postoji.");
                    request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);
                }
            } else {
                request.setAttribute("poruka", "Podaci koje ste uneli nisu u validnom formatu.");
                request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);
            }

        } else if (putanja.equals("/kupovina")) {

        } else if (putanja.equals("/kontakt")) {

            initializePage(putanja, request, response);
            request.setAttribute("poruka", true);
            request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);

        } else if (putanja.equals("/azuriranjeSlike")) {

            File file;
            int maxFileSize = 5000 * 1024;
            int maxMemSize = 5000 * 1024;

            String filePath = "resources/img/ostale";

            String contentType = request.getContentType();

            if ((contentType.contains("multipart/form-data"))) {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold(maxMemSize);

                factory.setRepository(new File("C:\\Users\\Andrej Kubat\\Documents\\NetBeansProjects\\KursMania\\web"));

                ServletFileUpload upload = new ServletFileUpload(factory);

                upload.setSizeMax(maxFileSize);

                try {

                    List fileItems = upload.parseRequest(request);

                    Iterator i = fileItems.iterator();

                    while (i.hasNext()) {
                        FileItem fi = (FileItem) i.next();
                        if (!fi.isFormField()) {

                            String fieldName = fi.getFieldName();
                            String fileName = fi.getName();
                            boolean isInMemory = fi.isInMemory();
                            long sizeInBytes = fi.getSize();

                            if (fileName.lastIndexOf("\\") >= 0) {
                                file = new File(filePath
                                        + fileName.substring(fileName.lastIndexOf("\\")));
                            } else {
                                file = new File(filePath
                                        + fileName.substring(fileName.lastIndexOf("\\") + 1));
                            }
                            fi.write(file);
                        }
                    }

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            } else {
                System.out.println("Greska!");
            }

        }
    }

    public void initializePage(String pageName, HttpServletRequest request, HttpServletResponse response) {

        List<String> stilovi = new ArrayList<>();
        List<String> skripte = new ArrayList<>();
        int navigationSelector = 0;

        if (pageName.equals("/pretraga")) {

            stilovi.add("news");
            stilovi.add("news_responsive");
            stilovi.add("courses");
            stilovi.add("courses_responsive");

            navigationSelector = 2;

            String q = request.getParameter("q");
            String oblast = request.getParameter("oblast");

            List<Kurs> kursevi = kursFacade.findAll();
            List<Kurs> filtriraniKursevi;

            if (oblast != null) {
                filtriraniKursevi = kursevi.stream().filter(e -> e.getKategorijaId().getKategorijaNaziv().equals(oblast)).collect(Collectors.toList());
            }

            filtriraniKursevi = kursevi.stream().filter(e -> e.getKursIme().toLowerCase().contains(q.toLowerCase())).collect(Collectors.toList());

            Collections.sort(kursevi);
            if (filtriraniKursevi.isEmpty()) {
                request.setAttribute("poruka", "Nazalost, nije pronadjen ni jedan kurs.");
            } else {
                Collections.sort(filtriraniKursevi);
                request.setAttribute("kursevi", filtriraniKursevi);
            }

            request.setAttribute("najpopularnijiKursevi", kursevi.subList(0, 2));

        } else if (pageName.equals("/prijava")) {

            stilovi.add("contact");
            stilovi.add("contact_responsive");

        } else if (pageName.equals("/registracija")) {

            stilovi.add("contact");
            stilovi.add("contact_responsive");

        } else if (pageName.equals("/kurs")) {

            stilovi.add("shop-item");
            stilovi.add("courses");
            stilovi.add("courses_responsive");
            stilovi.add("contact");
            stilovi.add("contact_responsive");

            skripte.add("video-player");

            navigationSelector = 2;

            String kursId = request.getParameter("id");

            if (kursId != null) {
                Kurs kurs = kursFacade.find(Integer.parseInt(kursId));
                kurs.getKorisnikId().setKorisnikOpis(utilities.getShortenText(kurs.getKorisnikId().getKorisnikOpis(), 50));

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

                request.setAttribute("duzinaKursa", utilities.numberToTime(duzinaKursa));

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

            }
        } else if (pageName.equals("/kursevi")) {

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
            kursevi = utilities.paginationHelper(pageIndex, 9, kursevi);

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

        } else if (pageName.equals("/instruktori")) {

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
            instruktori = utilities.paginationHelper(pageIndex, 9, instruktori);

            request.setAttribute("instruktori", instruktori);
            request.setAttribute("brojStranica", brojStranica);
            request.setAttribute("index", pageIndex);

        } else if (pageName.equals("/instruktor")) {

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

        } else if (pageName.equals("/nalog")) {

            stilovi.add("contact");
            stilovi.add("contact_responsive");

            if (korisnik != null) {
                Collection<Evidencija> evidencija = korisnik.getEvidencijaCollection();
                Collection<Komentar> komentari = korisnik.getKomentarCollection();
                Collection<Ocena> ocene = korisnik.getOcenaCollection();
                List<Kurs> kursevi = new ArrayList<>();

                for (Evidencija e : evidencija) {
                    kursevi.add(kursFacade.find(e.getKursId().getKursId()));
                }

                request.setAttribute("kursevi", kursevi);
                request.setAttribute("komentari", komentari);
                request.setAttribute("ocene", ocene);

            }
        } else if (pageName.equals("/kupovina")) {

            stilovi.add("contact");
            stilovi.add("contact_responsive");
            stilovi.add("payment");

        } else if (pageName.equals("/potvrda")) {

        } else if (pageName.equals("/kategorija")) {

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

                if (kursevi.size() > 0) {
                    int brojKurseva = kursevi.size();
                    kursevi = utilities.paginationHelper(pageIndex, 9, kursevi);

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
                    request.setAttribute("brojStranica", brojKurseva < 9 ? 1 : brojKurseva / 9);
                } else {
                    request.setAttribute("poruka", "Nazalost, nijedan kurs nema trazenu kategoriju.");
                }

                request.setAttribute("kat", kategorija);
                request.setAttribute("kursevi", kursevi);
                request.setAttribute("index", pageIndex);
            }

        } else if (pageName.equals("/kontakt")) {

            stilovi.add("contact");
            stilovi.add("contact_responsive");

            skripte.add("contact");

        } else if (pageName.equals("/onama")) {

            stilovi.add("about");
            stilovi.add("about_responsive");

            navigationSelector = 1;
            request.setAttribute("instruktori", korisnikFacade.findAll().stream().filter(e -> e.getRolaId().getRolaId() == 2).collect(Collectors.toList()));

        }

        request.setAttribute("stilovi", stilovi);
        request.setAttribute("skripte", skripte);
        request.setAttribute("navigationSelector", navigationSelector);
    }
}
