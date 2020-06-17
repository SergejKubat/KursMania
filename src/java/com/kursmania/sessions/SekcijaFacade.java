package com.kursmania.sessions;

import com.kursmania.jpa.entities.Sekcija;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SekcijaFacade extends AbstractFacade<Sekcija> {

    @PersistenceContext(unitName = "KursManiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SekcijaFacade() {
        super(Sekcija.class);
    }
    
}
