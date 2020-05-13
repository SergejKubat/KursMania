/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kursmania.controllers;

import com.kursmania.controllers.exceptions.NonexistentEntityException;
import com.kursmania.controllers.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.kursmania.entities.KursTag;
import com.kursmania.entities.Tag;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Andrej Kubat
 */
public class TagJpaController implements Serializable {

    public TagJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tag tag) throws RollbackFailureException, Exception {
        if (tag.getKursTagCollection() == null) {
            tag.setKursTagCollection(new ArrayList<KursTag>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<KursTag> attachedKursTagCollection = new ArrayList<KursTag>();
            for (KursTag kursTagCollectionKursTagToAttach : tag.getKursTagCollection()) {
                kursTagCollectionKursTagToAttach = em.getReference(kursTagCollectionKursTagToAttach.getClass(), kursTagCollectionKursTagToAttach.getKtId());
                attachedKursTagCollection.add(kursTagCollectionKursTagToAttach);
            }
            tag.setKursTagCollection(attachedKursTagCollection);
            em.persist(tag);
            for (KursTag kursTagCollectionKursTag : tag.getKursTagCollection()) {
                Tag oldTagIdOfKursTagCollectionKursTag = kursTagCollectionKursTag.getTagId();
                kursTagCollectionKursTag.setTagId(tag);
                kursTagCollectionKursTag = em.merge(kursTagCollectionKursTag);
                if (oldTagIdOfKursTagCollectionKursTag != null) {
                    oldTagIdOfKursTagCollectionKursTag.getKursTagCollection().remove(kursTagCollectionKursTag);
                    oldTagIdOfKursTagCollectionKursTag = em.merge(oldTagIdOfKursTagCollectionKursTag);
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

    public void edit(Tag tag) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Tag persistentTag = em.find(Tag.class, tag.getTagId());
            Collection<KursTag> kursTagCollectionOld = persistentTag.getKursTagCollection();
            Collection<KursTag> kursTagCollectionNew = tag.getKursTagCollection();
            Collection<KursTag> attachedKursTagCollectionNew = new ArrayList<KursTag>();
            for (KursTag kursTagCollectionNewKursTagToAttach : kursTagCollectionNew) {
                kursTagCollectionNewKursTagToAttach = em.getReference(kursTagCollectionNewKursTagToAttach.getClass(), kursTagCollectionNewKursTagToAttach.getKtId());
                attachedKursTagCollectionNew.add(kursTagCollectionNewKursTagToAttach);
            }
            kursTagCollectionNew = attachedKursTagCollectionNew;
            tag.setKursTagCollection(kursTagCollectionNew);
            tag = em.merge(tag);
            for (KursTag kursTagCollectionOldKursTag : kursTagCollectionOld) {
                if (!kursTagCollectionNew.contains(kursTagCollectionOldKursTag)) {
                    kursTagCollectionOldKursTag.setTagId(null);
                    kursTagCollectionOldKursTag = em.merge(kursTagCollectionOldKursTag);
                }
            }
            for (KursTag kursTagCollectionNewKursTag : kursTagCollectionNew) {
                if (!kursTagCollectionOld.contains(kursTagCollectionNewKursTag)) {
                    Tag oldTagIdOfKursTagCollectionNewKursTag = kursTagCollectionNewKursTag.getTagId();
                    kursTagCollectionNewKursTag.setTagId(tag);
                    kursTagCollectionNewKursTag = em.merge(kursTagCollectionNewKursTag);
                    if (oldTagIdOfKursTagCollectionNewKursTag != null && !oldTagIdOfKursTagCollectionNewKursTag.equals(tag)) {
                        oldTagIdOfKursTagCollectionNewKursTag.getKursTagCollection().remove(kursTagCollectionNewKursTag);
                        oldTagIdOfKursTagCollectionNewKursTag = em.merge(oldTagIdOfKursTagCollectionNewKursTag);
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
                Integer id = tag.getTagId();
                if (findTag(id) == null) {
                    throw new NonexistentEntityException("The tag with id " + id + " no longer exists.");
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
            Tag tag;
            try {
                tag = em.getReference(Tag.class, id);
                tag.getTagId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tag with id " + id + " no longer exists.", enfe);
            }
            Collection<KursTag> kursTagCollection = tag.getKursTagCollection();
            for (KursTag kursTagCollectionKursTag : kursTagCollection) {
                kursTagCollectionKursTag.setTagId(null);
                kursTagCollectionKursTag = em.merge(kursTagCollectionKursTag);
            }
            em.remove(tag);
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

    public List<Tag> findTagEntities() {
        return findTagEntities(true, -1, -1);
    }

    public List<Tag> findTagEntities(int maxResults, int firstResult) {
        return findTagEntities(false, maxResults, firstResult);
    }

    private List<Tag> findTagEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tag.class));
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

    public Tag findTag(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tag.class, id);
        } finally {
            em.close();
        }
    }

    public int getTagCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tag> rt = cq.from(Tag.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
