package com.kursmania.sessions;

import com.kursmania.jpa.entities.Komentar;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class KomentarFacade extends AbstractFacade<Komentar> {

    @PersistenceContext(unitName = "KursManiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KomentarFacade() {
        super(Komentar.class);
    }
    
}
