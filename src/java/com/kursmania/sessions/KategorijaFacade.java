package com.kursmania.sessions;

import com.kursmania.jpa.entities.Kategorija;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class KategorijaFacade extends AbstractFacade<Kategorija> {

    @PersistenceContext(unitName = "KursManiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KategorijaFacade() {
        super(Kategorija.class);
    }
    
}
