package com.kursmania.servleti;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Pocetna extends HttpServlet {
    
    /*@EJB
    private RolaFacade rolaFacade;*/

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String putanja = request.getServletPath();
        
        if (putanja.equals("/pretraga")) {
            
        }
        
        else if(putanja.equals("/prijava")) {
            
        }
        
        else if(putanja.equals("/registracija")) {
            
        }
        
        else if(putanja.equals("/kurs")) {
            
        }
        
        else if(putanja.equals("/instruktor")) {
            
        }
        
        else if(putanja.equals("/nalog")) {
            
        }
        
        else if(putanja.equals("/korpa")) {
            
        }
    }
    
}
