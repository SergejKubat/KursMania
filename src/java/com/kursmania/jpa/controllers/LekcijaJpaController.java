package com.kursmania.jpa.controllers;

import com.kursmania.jpa.controllers.exceptions.NonexistentEntityException;
import com.kursmania.jpa.controllers.exceptions.RollbackFailureException;
import com.kursmania.jpa.entities.Lekcija;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.kursmania.jpa.entities.Sekcija;
import com.kursmania.jpa.entities.Video;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

public class LekcijaJpaController implements Serializable {

    public LekcijaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Lekcija lekcija) throws RollbackFailureException, Exception {
        if (lekcija.getVideoCollection() == null) {
            lekcija.setVideoCollection(new ArrayList<Video>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Sekcija sekcijaId = lekcija.getSekcijaId();
            if (sekcijaId != null) {
                sekcijaId = em.getReference(sekcijaId.getClass(), sekcijaId.getSekcijaId());
                lekcija.setSekcijaId(sekcijaId);
            }
            Video videoId = lekcija.getVideoId();
            if (videoId != null) {
                videoId = em.getReference(videoId.getClass(), videoId.getVideoId());
                lekcija.setVideoId(videoId);
            }
            Collection<Video> attachedVideoCollection = new ArrayList<Video>();
            for (Video videoCollectionVideoToAttach : lekcija.getVideoCollection()) {
                videoCollectionVideoToAttach = em.getReference(videoCollectionVideoToAttach.getClass(), videoCollectionVideoToAttach.getVideoId());
                attachedVideoCollection.add(videoCollectionVideoToAttach);
            }
            lekcija.setVideoCollection(attachedVideoCollection);
            em.persist(lekcija);
            if (sekcijaId != null) {
                sekcijaId.getLekcijaCollection().add(lekcija);
                sekcijaId = em.merge(sekcijaId);
            }
            if (videoId != null) {
                videoId.getLekcijaCollection().add(lekcija);
                videoId = em.merge(videoId);
            }
            for (Video videoCollectionVideo : lekcija.getVideoCollection()) {
                Lekcija oldLekcijaIdOfVideoCollectionVideo = videoCollectionVideo.getLekcijaId();
                videoCollectionVideo.setLekcijaId(lekcija);
                videoCollectionVideo = em.merge(videoCollectionVideo);
                if (oldLekcijaIdOfVideoCollectionVideo != null) {
                    oldLekcijaIdOfVideoCollectionVideo.getVideoCollection().remove(videoCollectionVideo);
                    oldLekcijaIdOfVideoCollectionVideo = em.merge(oldLekcijaIdOfVideoCollectionVideo);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Lekcija lekcija) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Lekcija persistentLekcija = em.find(Lekcija.class, lekcija.getLekcijaId());
            Sekcija sekcijaIdOld = persistentLekcija.getSekcijaId();
            Sekcija sekcijaIdNew = lekcija.getSekcijaId();
            Video videoIdOld = persistentLekcija.getVideoId();
            Video videoIdNew = lekcija.getVideoId();
            Collection<Video> videoCollectionOld = persistentLekcija.getVideoCollection();
            Collection<Video> videoCollectionNew = lekcija.getVideoCollection();
            if (sekcijaIdNew != null) {
                sekcijaIdNew = em.getReference(sekcijaIdNew.getClass(), sekcijaIdNew.getSekcijaId());
                lekcija.setSekcijaId(sekcijaIdNew);
            }
            if (videoIdNew != null) {
                videoIdNew = em.getReference(videoIdNew.getClass(), videoIdNew.getVideoId());
                lekcija.setVideoId(videoIdNew);
            }
            Collection<Video> attachedVideoCollectionNew = new ArrayList<Video>();
            for (Video videoCollectionNewVideoToAttach : videoCollectionNew) {
                videoCollectionNewVideoToAttach = em.getReference(videoCollectionNewVideoToAttach.getClass(), videoCollectionNewVideoToAttach.getVideoId());
                attachedVideoCollectionNew.add(videoCollectionNewVideoToAttach);
            }
            videoCollectionNew = attachedVideoCollectionNew;
            lekcija.setVideoCollection(videoCollectionNew);
            lekcija = em.merge(lekcija);
            if (sekcijaIdOld != null && !sekcijaIdOld.equals(sekcijaIdNew)) {
                sekcijaIdOld.getLekcijaCollection().remove(lekcija);
                sekcijaIdOld = em.merge(sekcijaIdOld);
            }
            if (sekcijaIdNew != null && !sekcijaIdNew.equals(sekcijaIdOld)) {
                sekcijaIdNew.getLekcijaCollection().add(lekcija);
                sekcijaIdNew = em.merge(sekcijaIdNew);
            }
            if (videoIdOld != null && !videoIdOld.equals(videoIdNew)) {
                videoIdOld.getLekcijaCollection().remove(lekcija);
                videoIdOld = em.merge(videoIdOld);
            }
            if (videoIdNew != null && !videoIdNew.equals(videoIdOld)) {
                videoIdNew.getLekcijaCollection().add(lekcija);
                videoIdNew = em.merge(videoIdNew);
            }
            for (Video videoCollectionOldVideo : videoCollectionOld) {
                if (!videoCollectionNew.contains(videoCollectionOldVideo)) {
                    videoCollectionOldVideo.setLekcijaId(null);
                    videoCollectionOldVideo = em.merge(videoCollectionOldVideo);
                }
            }
            for (Video videoCollectionNewVideo : videoCollectionNew) {
                if (!videoCollectionOld.contains(videoCollectionNewVideo)) {
                    Lekcija oldLekcijaIdOfVideoCollectionNewVideo = videoCollectionNewVideo.getLekcijaId();
                    videoCollectionNewVideo.setLekcijaId(lekcija);
                    videoCollectionNewVideo = em.merge(videoCollectionNewVideo);
                    if (oldLekcijaIdOfVideoCollectionNewVideo != null && !oldLekcijaIdOfVideoCollectionNewVideo.equals(lekcija)) {
                        oldLekcijaIdOfVideoCollectionNewVideo.getVideoCollection().remove(videoCollectionNewVideo);
                        oldLekcijaIdOfVideoCollectionNewVideo = em.merge(oldLekcijaIdOfVideoCollectionNewVideo);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = lekcija.getLekcijaId();
                if (findLekcija(id) == null) {
                    throw new NonexistentEntityException("The lekcija with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Lekcija lekcija;
            try {
                lekcija = em.getReference(Lekcija.class, id);
                lekcija.getLekcijaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lekcija with id " + id + " no longer exists.", enfe);
            }
            Sekcija sekcijaId = lekcija.getSekcijaId();
            if (sekcijaId != null) {
                sekcijaId.getLekcijaCollection().remove(lekcija);
                sekcijaId = em.merge(sekcijaId);
            }
            Video videoId = lekcija.getVideoId();
            if (videoId != null) {
                videoId.getLekcijaCollection().remove(lekcija);
                videoId = em.merge(videoId);
            }
            Collection<Video> videoCollection = lekcija.getVideoCollection();
            for (Video videoCollectionVideo : videoCollection) {
                videoCollectionVideo.setLekcijaId(null);
                videoCollectionVideo = em.merge(videoCollectionVideo);
            }
            em.remove(lekcija);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Lekcija> findLekcijaEntities() {
        return findLekcijaEntities(true, -1, -1);
    }

    public List<Lekcija> findLekcijaEntities(int maxResults, int firstResult) {
        return findLekcijaEntities(false, maxResults, firstResult);
    }

    private List<Lekcija> findLekcijaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lekcija.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Lekcija findLekcija(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lekcija.class, id);
        } finally {
            em.close();
        }
    }

    public int getLekcijaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lekcija> rt = cq.from(Lekcija.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
