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
import com.kursmania.jpa.entities.Lekcija;
import com.kursmania.jpa.entities.Video;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Andrej Kubat
 */
public class VideoJpaController implements Serializable {

    public VideoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Video video) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Lekcija lekcijaId = video.getLekcijaId();
            if (lekcijaId != null) {
                lekcijaId = em.getReference(lekcijaId.getClass(), lekcijaId.getLekcijaId());
                video.setLekcijaId(lekcijaId);
            }
            em.persist(video);
            if (lekcijaId != null) {
                lekcijaId.getVideoCollection().add(video);
                lekcijaId = em.merge(lekcijaId);
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

    public void edit(Video video) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Video persistentVideo = em.find(Video.class, video.getVideoId());
            Lekcija lekcijaIdOld = persistentVideo.getLekcijaId();
            Lekcija lekcijaIdNew = video.getLekcijaId();
            if (lekcijaIdNew != null) {
                lekcijaIdNew = em.getReference(lekcijaIdNew.getClass(), lekcijaIdNew.getLekcijaId());
                video.setLekcijaId(lekcijaIdNew);
            }
            video = em.merge(video);
            if (lekcijaIdOld != null && !lekcijaIdOld.equals(lekcijaIdNew)) {
                lekcijaIdOld.getVideoCollection().remove(video);
                lekcijaIdOld = em.merge(lekcijaIdOld);
            }
            if (lekcijaIdNew != null && !lekcijaIdNew.equals(lekcijaIdOld)) {
                lekcijaIdNew.getVideoCollection().add(video);
                lekcijaIdNew = em.merge(lekcijaIdNew);
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
                Integer id = video.getVideoId();
                if (findVideo(id) == null) {
                    throw new NonexistentEntityException("The video with id " + id + " no longer exists.");
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
            Video video;
            try {
                video = em.getReference(Video.class, id);
                video.getVideoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The video with id " + id + " no longer exists.", enfe);
            }
            Lekcija lekcijaId = video.getLekcijaId();
            if (lekcijaId != null) {
                lekcijaId.getVideoCollection().remove(video);
                lekcijaId = em.merge(lekcijaId);
            }
            em.remove(video);
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

    public List<Video> findVideoEntities() {
        return findVideoEntities(true, -1, -1);
    }

    public List<Video> findVideoEntities(int maxResults, int firstResult) {
        return findVideoEntities(false, maxResults, firstResult);
    }

    private List<Video> findVideoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Video.class));
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

    public Video findVideo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Video.class, id);
        } finally {
            em.close();
        }
    }

    public int getVideoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Video> rt = cq.from(Video.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
