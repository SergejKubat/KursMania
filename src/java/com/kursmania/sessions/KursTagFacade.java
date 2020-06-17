package com.kursmania.sessions;

import com.kursmania.jpa.entities.KursTag;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class KursTagFacade extends AbstractFacade<KursTag> {

    @PersistenceContext(unitName = "KursManiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KursTagFacade() {
        super(KursTag.class);
    }
    
}
