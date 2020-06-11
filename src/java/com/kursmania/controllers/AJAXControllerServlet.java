package com.kursmania.controllers;

import com.kursmania.jpa.entities.Kurs;
import com.kursmania.jpa.entities.Tag;
import com.kursmania.sessions.KursFacade;
import com.kursmania.sessions.TagFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AJAXControllerServlet extends HttpServlet {

    @EJB
    private TagFacade tagFacade;
    
    @EJB
    private KursFacade kursFacade;

    private List<Tag> tagovi;
    
    private List<Kurs> kursevi;
    
    private List<String> reci;

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
        super.doDelete(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
