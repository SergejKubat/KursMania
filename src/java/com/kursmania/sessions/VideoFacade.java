package com.kursmania.sessions;

import com.kursmania.jpa.entities.Video;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class VideoFacade extends AbstractFacade<Video> {

    @PersistenceContext(unitName = "KursManiaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VideoFacade() {
        super(Video.class);
    }
    
}
