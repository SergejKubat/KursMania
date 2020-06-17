package com.kursmania.sessions;

import com.kursmania.jpa.entities.Kurs;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class KursFacade extends AbstractFacade<Kurs> {

    @PersistenceContext(unitName = "KursManiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KursFacade() {
        super(Kurs.class);
    }
    
}
