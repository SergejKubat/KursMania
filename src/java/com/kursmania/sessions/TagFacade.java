package com.kursmania.sessions;

import com.kursmania.jpa.entities.Tag;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TagFacade extends AbstractFacade<Tag> {

    @PersistenceContext(unitName = "KursManiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TagFacade() {
        super(Tag.class);
    }
    
}
