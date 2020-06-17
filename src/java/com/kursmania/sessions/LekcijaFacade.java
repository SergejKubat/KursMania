package com.kursmania.sessions;

import com.kursmania.jpa.entities.Lekcija;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class LekcijaFacade extends AbstractFacade<Lekcija> {

    @PersistenceContext(unitName = "KursManiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LekcijaFacade() {
        super(Lekcija.class);
    }
    
}
