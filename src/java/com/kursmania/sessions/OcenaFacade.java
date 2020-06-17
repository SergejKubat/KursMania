package com.kursmania.sessions;

import com.kursmania.jpa.entities.Ocena;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OcenaFacade extends AbstractFacade<Ocena> {

    @PersistenceContext(unitName = "KursManiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OcenaFacade() {
        super(Ocena.class);
    }
    
}
