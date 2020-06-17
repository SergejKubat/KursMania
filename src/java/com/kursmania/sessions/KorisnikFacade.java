package com.kursmania.sessions;

import com.kursmania.jpa.entities.Korisnik;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class KorisnikFacade extends AbstractFacade<Korisnik> {

    @PersistenceContext(unitName = "KursManiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KorisnikFacade() {
        super(Korisnik.class);
    }
    
}
