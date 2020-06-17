package com.kursmania.sessions;

import com.kursmania.jpa.entities.Evidencija;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EvidencijaFacade extends AbstractFacade<Evidencija> {

    @PersistenceContext(unitName = "KursManiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EvidencijaFacade() {
        super(Evidencija.class);
    }
    
}
