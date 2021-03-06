package com.kursmania.controllers;

import com.kursmania.jpa.entities.Evidencija;
import com.kursmania.jpa.entities.Jezik;
import com.kursmania.jpa.entities.Kategorija;
import com.kursmania.jpa.entities.Komentar;
import com.kursmania.jpa.entities.Korisnik;
import com.kursmania.jpa.entities.Kupon;
import com.kursmania.jpa.entities.Kurs;
import com.kursmania.jpa.entities.KursTag;
import com.kursmania.jpa.entities.Lekcija;
import com.kursmania.jpa.entities.Ocena;
import com.kursmania.jpa.entities.Rola;
import com.kursmania.jpa.entities.Sekcija;
import com.kursmania.sessions.EvidencijaFacade;
import com.kursmania.sessions.JezikFacade;
import com.kursmania.sessions.KategorijaFacade;
import com.kursmania.sessions.KorisnikFacade;
import com.kursmania.sessions.KuponFacade;
import com.kursmania.sessions.KursFacade;
import com.kursmania.sessions.LekcijaFacade;
import com.kursmania.sessions.SekcijaFacade;
import com.kursmania.sessions.TagFacade;
import com.kursmania.utils.HashUtil;
import com.kursmania.utils.Utilities;
import com.kursmania.utils.Validation;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
    private EvidencijaFacade evidencijaFacade;

    @EJB
    private KategorijaFacade kategorijaFacade;

    @EJB
    private KorisnikFacade korisnikFacade;

    @EJB
    private JezikFacade jezikFacade;

    @EJB
    private TagFacade tagFacade;

    @EJB
    private LekcijaFacade lekcijaFacade;

    @EJB
    private SekcijaFacade sekcijaFacade;

    @EJB
    private KuponFacade kuponFacade;

    private Utilities utilities;

    Korisnik korisnik;

    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("kategorije", kategorijaFacade.findAll());
        getServletContext().setAttribute("brojKurseva", kursFacade.findAll().size());
        getServletContext().setAttribute("brojStudenata", korisnikFacade.findAll().stream().filter(e -> e.getRolaId().getRolaId() == 1).collect(Collectors.toList()).size());
        getServletContext().setAttribute("brojInstruktora", korisnikFacade.findAll().stream().filter(e -> e.getRolaId().getRolaId() == 2).collect(Collectors.toList()).size());
        getServletContext().setAttribute("brojJezika", jezikFacade.findAll().size());
        
        List<Kurs> kursevi = kursFacade.findAll();        
        Collections.sort(kursevi);
        if (kursevi.size() > 6) {
            kursevi.subList(0, 5);
        }
        getServletContext().setAttribute("najpopularnijiKurs", kursevi.get(kursevi.size() - 1));
        kursevi.remove(kursevi.size() - 1);
        getServletContext().setAttribute("promoKursevi", kursevi);
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

            initializePage(putanja, request, response);
            request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);

        } else if (putanja.equals("/registracija")) {

            initializePage(putanja, request, response);
            request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);

        } else if (putanja.equals("/kurs")) {

            String id = request.getParameter("id");
            boolean reqValid = false;

            if (id != null) {
                try {
                    int kursId = Integer.parseInt(id);
                    Kurs kurs = kursFacade.find(kursId);
                    if (kurs != null) {
                        reqValid = true;
                    }
                } catch (NumberFormatException ex) {
                    response.sendError(404);
                }
            }

            if (reqValid) {
                initializePage(putanja, request, response);
                request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);
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
            boolean reqValid = false;

            if (id != null) {
                try {
                    int instruktorId = Integer.parseInt(id);
                    Korisnik instruktor = korisnikFacade.find(instruktorId);
                    if (instruktor != null) {
                        reqValid = true;
                    }
                } catch (NumberFormatException ex) {
                    response.sendError(404);
                }
            }

            if (reqValid) {
                initializePage(putanja, request, response);
                request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);
            } else {
                response.sendError(404);
            }

        } else if (putanja.equals("/nalog")) {

            initializePage(putanja, request, response);
            request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);

        } else if (putanja.equals("/izmenaNaloga")) {

            initializePage(putanja, request, response);
            request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);

        } else if (putanja.equals("/kupovina")) {

            String id = request.getParameter("id");

            if (id != null) {
                try {
                    int kursId = Integer.parseInt(id);
                    Kurs kurs = kursFacade.find(kursId);

                    if (kurs != null) {
                        boolean kupljen = false;
                        for (Evidencija e : kurs.getEvidencijaCollection()) {
                            if (Objects.equals(korisnik.getKorisnikId(), e.getKorisnikId().getKorisnikId())) {
                                kupljen = true;
                            }
                        }
                        if (!kupljen) {
                            initializePage(putanja, request, response);
                            request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);
                        } else {
                            response.sendRedirect("kurs?id=" + id);
                        }
                    }

                } catch (NumberFormatException ex) {
                    response.sendError(404);
                }
            } else {
                response.sendError(404);
            }

        } else if (putanja.equals("/potvrda")) {

            String id = request.getParameter("id");
            boolean reqValid = false;

            if (id != null) {
                try {
                    int kursId = Integer.parseInt(id);
                    Kurs kurs = kursFacade.find(kursId);
                    if (kurs != null) {
                        reqValid = true;
                    }
                } catch (NumberFormatException ex) {
                    response.sendError(404);
                }
            }

            if (reqValid) {
                initializePage(putanja, request, response);
                request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);
            } else {
                response.sendError(404);
            }

        } else if (putanja.equals("/lekcija")) {

            String id = request.getParameter("id");

            if (id != null) {
                try {
                    int lekcijaId = Integer.parseInt(id);
                    Lekcija lekcija = lekcijaFacade.find(lekcijaId);

                    if (lekcija != null) {

                        Kurs kurs = lekcija.getSekcijaId().getKursId();
                        boolean dostupna = false;

                        for (Evidencija e : kurs.getEvidencijaCollection()) {
                            if (Objects.equals(korisnik.getKorisnikId(), e.getKorisnikId().getKorisnikId())) {
                                dostupna = true;
                            }
                        }

                        if (dostupna) {
                            initializePage(putanja, request, response);
                            request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);
                        } else {
                            response.sendError(404);
                        }

                    }
                } catch (NumberFormatException ex) {
                    response.sendError(404);
                }
            } else {
                response.sendError(404);
            }

        } else if (putanja.equals("/kategorija")) {

            String id = request.getParameter("id");
            boolean reqValid = false;

            if (id != null) {
                try {
                    int kategorijaId = Integer.parseInt(id);
                    Kategorija kategorija = kategorijaFacade.find(kategorijaId);
                    if (kategorija != null) {
                        reqValid = true;
                    }
                } catch (NumberFormatException ex) {
                    response.sendError(404);
                }
            }

            if (reqValid) {
                initializePage(putanja, request, response);
                request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);
            } else {
                response.sendError(404);
            }

        } else if (putanja.equals("/kontakt")) {

            initializePage(putanja, request, response);
            request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);

        } else if (putanja.equals("/onama")) {

            initializePage(putanja, request, response);
            request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);

        } else if (putanja.equals("/dodavanjeKursa")) {

            if (korisnik.getRolaId().getRolaId() == 2) {

                initializePage(putanja, request, response);
                request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);

            } else {
                response.sendError(404);
            }

        } else if (putanja.equals("/pregledKursa")) {

            if (korisnik.getRolaId().getRolaId() == 2) {

                String id = request.getParameter("id");

                if (id != null) {

                    try {
                        int kursId = Integer.parseInt(id);
                        Kurs kurs = kursFacade.find(kursId);

                        if (kurs != null) {

                            initializePage(putanja, request, response);
                            request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);

                        } else {
                            response.sendError(404);
                        }
                    } catch (NumberFormatException ex) {
                        response.sendError(404);
                    }
                } else {
                    response.sendError(404);
                }

            } else {
                response.sendError(404);
            }

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
                        Kurs kurs = kursFacade.find(Integer.parseInt(kursId));
                        boolean kupljen = false;
                        for (Evidencija e : kurs.getEvidencijaCollection()) {
                            if (Objects.equals(kor.getKorisnikId(), e.getKorisnikId().getKorisnikId())) {
                                kupljen = true;
                            }
                        }
                        if (!kupljen) {
                            response.sendRedirect("kupovina?id=" + Integer.parseInt(kursId));
                        } else {
                            response.sendRedirect("nalog");
                        }
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

            initializePage(putanja, request, response);

            String ime = request.getParameter("ime");
            String brojKartice = request.getParameter("brojKartice");
            String mesec = request.getParameter("mesec");
            String godina = request.getParameter("godina");
            String cvv = request.getParameter("cvv");
            String kuponSifra = request.getParameter("kupon");
            int kursId = Integer.parseInt(request.getParameter("id"));

            boolean valid = Validation.proveriIme(ime) && Validation.proveriBrojKartice(brojKartice) && Validation.proveriCVV(cvv);

            if (valid) {

                Kurs kurs = kursFacade.find(kursId);

                LocalDateTime now = LocalDateTime.now();

                int g = now.getYear();
                int m = now.getMonthValue();
                int d = now.getDayOfMonth();

                String mesec2 = m > 9 ? String.valueOf(m) : "0" + String.valueOf(m);
                String dan = d > 9 ? String.valueOf(d) : "0" + String.valueOf(d);

                Evidencija evidencija = new Evidencija();
                evidencija.setKursId(kurs);
                evidencija.setKorisnikId(korisnik);
                evidencija.setEvidencijaDatum(g + "-" + mesec2 + "-" + dan);

                evidencijaFacade.create(evidencija);

                Kupon kupon = null;

                if (Validation.proveriKupon(kuponSifra)) {
                    Collection<Kupon> kuponi = kurs.getKuponCollection();

                    if (kuponi != null) {
                        for (Kupon k : kuponi) {
                            if (kuponSifra.equals(k.getKuponKod()) && k.getKuponIsIskoriscen() == 0) {
                                kupon = k;
                            }
                        }
                    }
                }

                if (kupon != null) {
                    kupon.setKuponIsIskoriscen(Short.parseShort("1"));
                    kuponFacade.edit(kupon);
                }

                String kuponParam = kupon != null ? "&kupon=" + String.valueOf(kupon.getKuponId()) : "";

                response.sendRedirect("potvrda?id=" + kurs.getKursId() + kuponParam);
            } else {

                request.setAttribute("poruka", "Podaci koji su uneti nisu validni!");
                request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);

            }

        } else if (putanja.equals("/izmenaNaloga")) {

            initializePage(putanja, request, response);

            if (korisnik != null) {
                String ime = request.getParameter("ime");
                String prezime = request.getParameter("prezime");
                String email = request.getParameter("email");
                String brojTelefona = request.getParameter("brt");
                String mesto = request.getParameter("mesto");
                String adresa = request.getParameter("adresa");
                String titula = request.getParameter("titula");
                String opis = request.getParameter("opis");

                boolean valid = Validation.proveriIme(ime) && Validation.proveriIme(prezime) && Validation.proveriBrojTelefona(brojTelefona) && Validation.proveriEmail(email);

                if (valid) {

                    Korisnik izmenjeniKorisnik = korisnik;

                    izmenjeniKorisnik.setKorisnikIme(ime);
                    izmenjeniKorisnik.setKorisnikPrezime(prezime);
                    izmenjeniKorisnik.setKorisnikEmail(email);
                    izmenjeniKorisnik.setKorisnikBrojTelefona(brojTelefona);
                    izmenjeniKorisnik.setKorisnikMesto(mesto);
                    izmenjeniKorisnik.setKorisnikAdresa(adresa);
                    izmenjeniKorisnik.setKorisnikTitula(titula);
                    izmenjeniKorisnik.setKorisnikOpis(opis);

                    korisnikFacade.edit(izmenjeniKorisnik);
                    response.sendRedirect("nalog");

                } else {

                    request.setAttribute("poruka", "Podaci koji su uneti nisu validni!");
                    request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);

                }

            } else {
                response.sendError(404);
            }

        } else if (putanja.equals("/kontakt")) {

            initializePage(putanja, request, response);
            request.setAttribute("poruka", true);
            request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);

        } else if (putanja.equals("/dodavanjeKursa")) {
            
            String ime = request.getParameter("ime");
            String opis = request.getParameter("opis");
            String zahtevi = request.getParameter("zahtevi");
            String kategorijaParam = request.getParameter("kategorija");
            String jezikParam = request.getParameter("jezik");
            String cena = request.getParameter("cena");
            
            int kategorijaId = Integer.parseInt(kategorijaParam);
            int jezikId = Integer.parseInt(jezikParam);
            float cenaVrednost = Float.parseFloat(cena);
            
            Kategorija kategorija = kategorijaFacade.find(kategorijaId);
            Jezik jezik = jezikFacade.find(jezikId);
            
            LocalDateTime now = LocalDateTime.now();
            
            int g = now.getYear();
            int m = now.getMonthValue();
            int d = now.getDayOfMonth();
            
            String mesec = m > 9 ? String.valueOf(m) : "0" + String.valueOf(m);
            String dan = d > 9 ? String.valueOf(d) : "0" + String.valueOf(d);
            
            String datumObjavljivanja = mesec + "-" + dan + "-" + g;
            String datumPoslednjePromene = mesec + "-" + dan + "-" + g;
            
            Kurs kurs = new Kurs();
            kurs.setKorisnikId(korisnik);
            kurs.setKategorijaId(kategorija);
            kurs.setJezikId(jezik);
            kurs.setKursIme(ime);
            kurs.setKursOpis(opis);
            kurs.setKursZahtevi(zahtevi);
            kurs.setKursDatumObjavljivanja(datumObjavljivanja);
            kurs.setDatumPoslednjePromene(datumPoslednjePromene);
            kurs.setKursCena(cenaVrednost);
            kurs.setKursSlika("resources/img/website/course_4.jpg");
            kurs.setKursVideo("https://youtu.be/5_MRXyYjHDk");
            kurs.setKursPregledi(0);
            kurs.setKursJavan(Short.parseShort("0"));
            
            kursFacade.create(kurs);
            
            initializePage(putanja, request, response);
            request.setAttribute("poruka", "Kurs je uspesno kreiran. Ukoliko zelite da dodajete sekcije ili lekcija, idite na vas nalog i odaberite kurs.");
            request.getRequestDispatcher("/WEB-INF/view" + putanja + ".jsp").forward(request, response);
            
        } else if (putanja.equals("/pregledKursa")) {

        } else if (putanja.equals("/azuriranjeSlike")) {

            File file;
            int maxFileSize = 5000 * 1024;
            int maxMemSize = 5000 * 1024;
            String filePath = "C:\\Users\\Andrej Kubat\\Documents\\NetBeansProjects\\KursMania\\web\\resources\\img\\ostale\\";

            String contentType = request.getContentType();

            if ((contentType.contains("multipart/form-data"))) {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold(maxMemSize);

                factory.setRepository(new File("c:\\temp"));

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
                            System.out.println("Uploaded Filename: " + filePath + "\\" + fileName + "<br>");
                            korisnik.setKorisnikAvatar("resources/img/ostale/" + fileName);
                            korisnikFacade.edit(korisnik);
                            response.sendRedirect("nalog");
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                    response.sendError(500);
                }
            } else {
                response.sendError(500);
            }

        }
    }

    public void initializePage(String pageName, HttpServletRequest request, HttpServletResponse response) {

        int navigationSelector = 0;

        if (pageName.equals("/pretraga")) {

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
            Collections.reverse(kursevi);
            if (filtriraniKursevi.isEmpty()) {
                request.setAttribute("poruka", "Nazalost, nije pronadjen ni jedan kurs.");
            } else {
                Collections.sort(filtriraniKursevi);
                request.setAttribute("kursevi", filtriraniKursevi);
            }

            request.setAttribute("najpopularnijiKursevi", kursevi.subList(0, 2));

        } else if (pageName.equals("/prijava")) {

        } else if (pageName.equals("/registracija")) {

        } else if (pageName.equals("/kurs")) {

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

                List<Kurs> preporuceniKursevi = kursFacade.findAll().stream().filter(e -> Objects.equals(e.getKorisnikId().getKorisnikId(), kurs.getKorisnikId().getKorisnikId())).collect(Collectors.toList());
                if (preporuceniKursevi.isEmpty()) {
                    kursFacade.findAll().stream().filter(e -> Objects.equals(e.getKategorijaId().getKategorijaId(), kurs.getKategorijaId().getKategorijaId())).collect(Collectors.toList());
                }
                preporuceniKursevi.remove(kurs);
                if (preporuceniKursevi.size() > 2) {
                    preporuceniKursevi = preporuceniKursevi.subList(0, 1);
                }

                request.setAttribute("preporuceniKursevi", preporuceniKursevi);

                int ocenaId = -1, ocenaVrednost = -1;

                if (korisnik != null) {

                    for (Ocena o : korisnik.getOcenaCollection()) {
                        if (o.getKursId().getKursId() == Integer.parseInt(kursId)) {
                            ocenaVrednost = o.getOcenaVrednost();
                            ocenaId = o.getOcenaId();
                        }
                    }

                    request.setAttribute("ocenaVrednost", ocenaVrednost);
                    request.setAttribute("ocenaId", ocenaId);

                    int korisnikId = korisnik.getKorisnikId();
                    boolean kupljen = false;
                    kurs.getEvidencijaCollection().stream().filter((e) -> (korisnikId == e.getKorisnikId().getKorisnikId())).forEachOrdered((_item) -> {
                        request.setAttribute("kupljen", !kupljen);
                    });
                }

            }
        } else if (pageName.equals("/kursevi")) {

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

            if (korisnik != null) {
                Collection<Evidencija> evidencija = korisnik.getEvidencijaCollection();
                Collection<Komentar> komentari = korisnik.getKomentarCollection();
                Collection<Ocena> ocene = korisnik.getOcenaCollection();
                List<Kurs> kursevi = new ArrayList<>();
                List<Kurs> mojiKursevi = new ArrayList<>();

                evidencija.forEach((e) -> {
                    kursevi.add(kursFacade.find(e.getKursId().getKursId()));
                });

                if (korisnik.getRolaId().getRolaId() == 2 && !korisnik.getKursCollection().isEmpty()) {
                    korisnik.getKursCollection().forEach((k) -> {
                        mojiKursevi.add(k);
                    });
                    request.setAttribute("mojiKursevi", mojiKursevi);
                }

                request.setAttribute("kursevi", kursevi);
                request.setAttribute("komentari", komentari);
                request.setAttribute("ocene", ocene);

            }
        } else if (pageName.equals("/izmenaNaloga")) {

        } else if (pageName.equals("/kupovina")) {

            int kursId = Integer.parseInt((String) request.getParameter("id"));
            Kurs kurs = kursFacade.find(kursId);

            int brojStudenata = kurs.getEvidencijaCollection().size();

            int brojSekcija = kurs.getSekcijaCollection().size();

            int brojLekcija = 0;

            brojLekcija = kurs.getSekcijaCollection().stream().map((s) -> s.getLekcijaCollection().size()).reduce(brojLekcija, Integer::sum);

            request.setAttribute("kurs", kurs);
            request.setAttribute("brojStudenata", brojStudenata);
            request.setAttribute("brojSekcija", brojSekcija);
            request.setAttribute("brojLekcija", brojLekcija);

        } else if (pageName.equals("/potvrda")) {

            int kursId = Integer.parseInt((String) request.getParameter("id"));
            String kuponSifra = request.getParameter("kupon");
            int kuponId = -1;
            Kupon kupon = null;
            Kurs kurs = kursFacade.find(kursId);

            if (kuponSifra != null) {
                try {
                    kuponId = Integer.parseInt(kuponSifra);
                } catch (NumberFormatException ex) {
                    System.out.println(ex);
                }
            }

            if (kuponId != -1) {
                kupon = kuponFacade.find(kuponId);
                request.setAttribute("kupon", kupon);
                double krajnjaCena = kurs.getKursCena() - ((kurs.getKursCena() / 100) * kupon.getKuponPopust());
                request.setAttribute("krajnjaCena", String.format("%.2f", krajnjaCena));
            }

            int brojStudenata = kurs.getEvidencijaCollection().size();

            int brojSekcija = kurs.getSekcijaCollection().size();

            int brojLekcija = 0;

            brojLekcija = kurs.getSekcijaCollection().stream().map((s) -> s.getLekcijaCollection().size()).reduce(brojLekcija, Integer::sum);

            request.setAttribute("kurs", kurs);
            request.setAttribute("brojStudenata", brojStudenata);
            request.setAttribute("brojSekcija", brojSekcija);
            request.setAttribute("brojLekcija", brojLekcija);

        } else if (pageName.equals("/lekcija")) {

            int lekcijaId = Integer.parseInt((String) request.getParameter("id"));

            Lekcija lekcija = lekcijaFacade.find(lekcijaId);
            Sekcija sekcija = sekcijaFacade.find(lekcija.getSekcijaId().getSekcijaId());

            int sledecaPozicija = -1;
            boolean pronadjen = false;

            for (Lekcija lekc : sekcija.getLekcijaCollection()) {
                if (pronadjen) {
                    sledecaPozicija = lekc.getLekcijaId();
                    pronadjen = false;
                }
                if (lekcijaId == lekc.getLekcijaId()) {
                    pronadjen = true;
                }
            }

            Lekcija sledecaLekcija = lekcijaFacade.find(sledecaPozicija);

            if (sledecaLekcija != null) {
                request.setAttribute("sledecaLekcija", sledecaLekcija);
            }

            request.setAttribute("lekcija", lekcija);

        } else if (pageName.equals("/kategorija")) {

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

        } else if (pageName.equals("/onama")) {

            navigationSelector = 1;
            request.setAttribute("instruktori", korisnikFacade.findAll().stream().filter(e -> e.getRolaId().getRolaId() == 2).collect(Collectors.toList()));

        } else if (pageName.equals("/dodavanjeKursa")) {

            request.setAttribute("jezici", jezikFacade.findAll());

        } else if (pageName.equals("/pregledKursa")) {

            int kursId = Integer.parseInt((String) request.getParameter("id"));
            Kurs kurs = kursFacade.find(kursId);

            request.setAttribute("kurs", kurs);

            int kursBrojOcena, kursZbirOcena = 0, kursZvezdice;
            double kursProsecnaOcena;

            kursBrojOcena = kurs.getOcenaCollection().size();
            kursZbirOcena = kurs.getOcenaCollection().stream().map((o) -> o.getOcenaVrednost()).reduce(kursZbirOcena, Integer::sum);

            kursProsecnaOcena = (double) kursZbirOcena / kursBrojOcena;
            kursZvezdice = kursZbirOcena / kursBrojOcena;

            request.setAttribute("kursBrojOcena", kursBrojOcena);
            request.setAttribute("kursProsecnaOcena", String.format("%.2f", kursProsecnaOcena));
            request.setAttribute("kursZvezdice", kursZvezdice);

            int ukupanBrojOcena, brojJedinica, brojDvojki, brojTrojki, brojCetvorki, brojPetica;
            double procenatJedinica, procenatDvojki, procenatTrojki, procenatCetvorki, procenatPetica;

            Collection<Ocena> ocene = kurs.getOcenaCollection();

            ukupanBrojOcena = ocene.size();

            brojJedinica = ocene.stream().filter(e -> e.getOcenaVrednost() == 1).collect(Collectors.toList()).size();
            brojDvojki = ocene.stream().filter(e -> e.getOcenaVrednost() == 2).collect(Collectors.toList()).size();
            brojTrojki = ocene.stream().filter(e -> e.getOcenaVrednost() == 3).collect(Collectors.toList()).size();
            brojCetvorki = ocene.stream().filter(e -> e.getOcenaVrednost() == 4).collect(Collectors.toList()).size();
            brojPetica = ocene.stream().filter(e -> e.getOcenaVrednost() == 5).collect(Collectors.toList()).size();

            procenatJedinica = (brojJedinica * 1.0 / ukupanBrojOcena) * 100;
            procenatDvojki = (brojDvojki * 1.0 / ukupanBrojOcena) * 100;
            procenatTrojki = (brojTrojki * 1.0 / ukupanBrojOcena) * 100;
            procenatCetvorki = (brojCetvorki * 1.0 / ukupanBrojOcena) * 100;
            procenatPetica = (brojPetica * 1.0 / ukupanBrojOcena) * 100;

            request.setAttribute("procenatJedinica", (int) procenatJedinica);
            request.setAttribute("procenatDvojki", (int) procenatDvojki);
            request.setAttribute("procenatTrojki", (int) procenatTrojki);
            request.setAttribute("procenatCetvorki", (int) procenatCetvorki);
            request.setAttribute("procenatPetica", (int) procenatPetica);

            int kursBrojLekcija = 0;

            kursBrojLekcija = kurs.getSekcijaCollection().stream().map((s) -> s.getLekcijaCollection().size()).reduce(kursBrojLekcija, Integer::sum);

            request.setAttribute("brojLekcija", kursBrojLekcija);

            int duzinaKursa = 0;

            for (Sekcija s : kurs.getSekcijaCollection()) {
                for (Lekcija l : s.getLekcijaCollection()) {
                    duzinaKursa = l.getVideoCollection().stream().map((v) -> v.getVideoDuzinaTrajanja()).reduce(duzinaKursa, Integer::sum);
                }
            }

            request.setAttribute("duzinaKursa", utilities.numberToTime(duzinaKursa));

        }

        request.setAttribute("navigationSelector", navigationSelector);
    }
}
