/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kursmania.jpa.controllers;

import com.kursmania.jpa.controllers.exceptions.NonexistentEntityException;
import com.kursmania.jpa.controllers.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.kursmania.jpa.entities.Sekcija;
import com.kursmania.jpa.entities.Komentar;
import com.kursmania.jpa.entities.Lekcija;
import java.util.ArrayList;
import java.util.Collection;
import com.kursmania.jpa.entities.Video;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Andrej Kubat
 */
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
        if (lekcija.getKomentarCollection() == null) {
            lekcija.setKomentarCollection(new ArrayList<Komentar>());
        }
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
            Collection<Komentar> attachedKomentarCollection = new ArrayList<Komentar>();
            for (Komentar komentarCollectionKomentarToAttach : lekcija.getKomentarCollection()) {
                komentarCollectionKomentarToAttach = em.getReference(komentarCollectionKomentarToAttach.getClass(), komentarCollectionKomentarToAttach.getKomentarId());
                attachedKomentarCollection.add(komentarCollectionKomentarToAttach);
            }
            lekcija.setKomentarCollection(attachedKomentarCollection);
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
            for (Komentar komentarCollectionKomentar : lekcija.getKomentarCollection()) {
                Lekcija oldLekcijaIdOfKomentarCollectionKomentar = komentarCollectionKomentar.getLekcijaId();
                komentarCollectionKomentar.setLekcijaId(lekcija);
                komentarCollectionKomentar = em.merge(komentarCollectionKomentar);
                if (oldLekcijaIdOfKomentarCollectionKomentar != null) {
                    oldLekcijaIdOfKomentarCollectionKomentar.getKomentarCollection().remove(komentarCollectionKomentar);
                    oldLekcijaIdOfKomentarCollectionKomentar = em.merge(oldLekcijaIdOfKomentarCollectionKomentar);
                }
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
            Collection<Komentar> komentarCollectionOld = persistentLekcija.getKomentarCollection();
            Collection<Komentar> komentarCollectionNew = lekcija.getKomentarCollection();
            Collection<Video> videoCollectionOld = persistentLekcija.getVideoCollection();
            Collection<Video> videoCollectionNew = lekcija.getVideoCollection();
            if (sekcijaIdNew != null) {
                sekcijaIdNew = em.getReference(sekcijaIdNew.getClass(), sekcijaIdNew.getSekcijaId());
                lekcija.setSekcijaId(sekcijaIdNew);
            }
            Collection<Komentar> attachedKomentarCollectionNew = new ArrayList<Komentar>();
            for (Komentar komentarCollectionNewKomentarToAttach : komentarCollectionNew) {
                komentarCollectionNewKomentarToAttach = em.getReference(komentarCollectionNewKomentarToAttach.getClass(), komentarCollectionNewKomentarToAttach.getKomentarId());
                attachedKomentarCollectionNew.add(komentarCollectionNewKomentarToAttach);
            }
            komentarCollectionNew = attachedKomentarCollectionNew;
            lekcija.setKomentarCollection(komentarCollectionNew);
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
            for (Komentar komentarCollectionOldKomentar : komentarCollectionOld) {
                if (!komentarCollectionNew.contains(komentarCollectionOldKomentar)) {
                    komentarCollectionOldKomentar.setLekcijaId(null);
                    komentarCollectionOldKomentar = em.merge(komentarCollectionOldKomentar);
                }
            }
            for (Komentar komentarCollectionNewKomentar : komentarCollectionNew) {
                if (!komentarCollectionOld.contains(komentarCollectionNewKomentar)) {
                    Lekcija oldLekcijaIdOfKomentarCollectionNewKomentar = komentarCollectionNewKomentar.getLekcijaId();
                    komentarCollectionNewKomentar.setLekcijaId(lekcija);
                    komentarCollectionNewKomentar = em.merge(komentarCollectionNewKomentar);
                    if (oldLekcijaIdOfKomentarCollectionNewKomentar != null && !oldLekcijaIdOfKomentarCollectionNewKomentar.equals(lekcija)) {
                        oldLekcijaIdOfKomentarCollectionNewKomentar.getKomentarCollection().remove(komentarCollectionNewKomentar);
                        oldLekcijaIdOfKomentarCollectionNewKomentar = em.merge(oldLekcijaIdOfKomentarCollectionNewKomentar);
                    }
                }
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
            Collection<Komentar> komentarCollection = lekcija.getKomentarCollection();
            for (Komentar komentarCollectionKomentar : komentarCollection) {
                komentarCollectionKomentar.setLekcijaId(null);
                komentarCollectionKomentar = em.merge(komentarCollectionKomentar);
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
