package com.kursmania.sessions;

import com.kursmania.jpa.entities.Rola;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RolaFacade extends AbstractFacade<Rola> {

    @PersistenceContext(unitName = "KursManiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolaFacade() {
        super(Rola.class);
    }
    
}
