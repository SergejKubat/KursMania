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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

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
        if (video.getLekcijaCollection() == null) {
            video.setLekcijaCollection(new ArrayList<Lekcija>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Lekcija lekcijaId = video.getLekcijaId();
            if (lekcijaId != null) {
                lekcijaId = em.getReference(lekcijaId.getClass(), lekcijaId.getLekcijaId());
                video.setLekcijaId(lekcijaId);
            }
            Collection<Lekcija> attachedLekcijaCollection = new ArrayList<Lekcija>();
            for (Lekcija lekcijaCollectionLekcijaToAttach : video.getLekcijaCollection()) {
                lekcijaCollectionLekcijaToAttach = em.getReference(lekcijaCollectionLekcijaToAttach.getClass(), lekcijaCollectionLekcijaToAttach.getLekcijaId());
                attachedLekcijaCollection.add(lekcijaCollectionLekcijaToAttach);
            }
            video.setLekcijaCollection(attachedLekcijaCollection);
            em.persist(video);
            if (lekcijaId != null) {
                Video oldVideoIdOfLekcijaId = lekcijaId.getVideoId();
                if (oldVideoIdOfLekcijaId != null) {
                    oldVideoIdOfLekcijaId.setLekcijaId(null);
                    oldVideoIdOfLekcijaId = em.merge(oldVideoIdOfLekcijaId);
                }
                lekcijaId.setVideoId(video);
                lekcijaId = em.merge(lekcijaId);
            }
            for (Lekcija lekcijaCollectionLekcija : video.getLekcijaCollection()) {
                Video oldVideoIdOfLekcijaCollectionLekcija = lekcijaCollectionLekcija.getVideoId();
                lekcijaCollectionLekcija.setVideoId(video);
                lekcijaCollectionLekcija = em.merge(lekcijaCollectionLekcija);
                if (oldVideoIdOfLekcijaCollectionLekcija != null) {
                    oldVideoIdOfLekcijaCollectionLekcija.getLekcijaCollection().remove(lekcijaCollectionLekcija);
                    oldVideoIdOfLekcijaCollectionLekcija = em.merge(oldVideoIdOfLekcijaCollectionLekcija);
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

    public void edit(Video video) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Video persistentVideo = em.find(Video.class, video.getVideoId());
            Lekcija lekcijaIdOld = persistentVideo.getLekcijaId();
            Lekcija lekcijaIdNew = video.getLekcijaId();
            Collection<Lekcija> lekcijaCollectionOld = persistentVideo.getLekcijaCollection();
            Collection<Lekcija> lekcijaCollectionNew = video.getLekcijaCollection();
            if (lekcijaIdNew != null) {
                lekcijaIdNew = em.getReference(lekcijaIdNew.getClass(), lekcijaIdNew.getLekcijaId());
                video.setLekcijaId(lekcijaIdNew);
            }
            Collection<Lekcija> attachedLekcijaCollectionNew = new ArrayList<Lekcija>();
            for (Lekcija lekcijaCollectionNewLekcijaToAttach : lekcijaCollectionNew) {
                lekcijaCollectionNewLekcijaToAttach = em.getReference(lekcijaCollectionNewLekcijaToAttach.getClass(), lekcijaCollectionNewLekcijaToAttach.getLekcijaId());
                attachedLekcijaCollectionNew.add(lekcijaCollectionNewLekcijaToAttach);
            }
            lekcijaCollectionNew = attachedLekcijaCollectionNew;
            video.setLekcijaCollection(lekcijaCollectionNew);
            video = em.merge(video);
            if (lekcijaIdOld != null && !lekcijaIdOld.equals(lekcijaIdNew)) {
                lekcijaIdOld.setVideoId(null);
                lekcijaIdOld = em.merge(lekcijaIdOld);
            }
            if (lekcijaIdNew != null && !lekcijaIdNew.equals(lekcijaIdOld)) {
                Video oldVideoIdOfLekcijaId = lekcijaIdNew.getVideoId();
                if (oldVideoIdOfLekcijaId != null) {
                    oldVideoIdOfLekcijaId.setLekcijaId(null);
                    oldVideoIdOfLekcijaId = em.merge(oldVideoIdOfLekcijaId);
                }
                lekcijaIdNew.setVideoId(video);
                lekcijaIdNew = em.merge(lekcijaIdNew);
            }
            for (Lekcija lekcijaCollectionOldLekcija : lekcijaCollectionOld) {
                if (!lekcijaCollectionNew.contains(lekcijaCollectionOldLekcija)) {
                    lekcijaCollectionOldLekcija.setVideoId(null);
                    lekcijaCollectionOldLekcija = em.merge(lekcijaCollectionOldLekcija);
                }
            }
            for (Lekcija lekcijaCollectionNewLekcija : lekcijaCollectionNew) {
                if (!lekcijaCollectionOld.contains(lekcijaCollectionNewLekcija)) {
                    Video oldVideoIdOfLekcijaCollectionNewLekcija = lekcijaCollectionNewLekcija.getVideoId();
                    lekcijaCollectionNewLekcija.setVideoId(video);
                    lekcijaCollectionNewLekcija = em.merge(lekcijaCollectionNewLekcija);
                    if (oldVideoIdOfLekcijaCollectionNewLekcija != null && !oldVideoIdOfLekcijaCollectionNewLekcija.equals(video)) {
                        oldVideoIdOfLekcijaCollectionNewLekcija.getLekcijaCollection().remove(lekcijaCollectionNewLekcija);
                        oldVideoIdOfLekcijaCollectionNewLekcija = em.merge(oldVideoIdOfLekcijaCollectionNewLekcija);
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
                lekcijaId.setVideoId(null);
                lekcijaId = em.merge(lekcijaId);
            }
            Collection<Lekcija> lekcijaCollection = video.getLekcijaCollection();
            for (Lekcija lekcijaCollectionLekcija : lekcijaCollection) {
                lekcijaCollectionLekcija.setVideoId(null);
                lekcijaCollectionLekcija = em.merge(lekcijaCollectionLekcija);
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
