package com.kursmania.sessions;

import com.kursmania.jpa.entities.Jezik;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class JezikFacade extends AbstractFacade<Jezik> {

    @PersistenceContext(unitName = "KursManiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JezikFacade() {
        super(Jezik.class);
    }
    
}
