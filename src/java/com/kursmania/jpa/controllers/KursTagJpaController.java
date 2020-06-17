package com.kursmania.jpa.controllers;

import com.kursmania.jpa.controllers.exceptions.NonexistentEntityException;
import com.kursmania.jpa.controllers.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.kursmania.jpa.entities.Kurs;
import com.kursmania.jpa.entities.KursTag;
import com.kursmania.jpa.entities.Tag;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

public class KursTagJpaController implements Serializable {

    public KursTagJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(KursTag kursTag) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Kurs kursId = kursTag.getKursId();
            if (kursId != null) {
                kursId = em.getReference(kursId.getClass(), kursId.getKursId());
                kursTag.setKursId(kursId);
            }
            Tag tagId = kursTag.getTagId();
            if (tagId != null) {
                tagId = em.getReference(tagId.getClass(), tagId.getTagId());
                kursTag.setTagId(tagId);
            }
            em.persist(kursTag);
            if (kursId != null) {
                kursId.getKursTagCollection().add(kursTag);
                kursId = em.merge(kursId);
            }
            if (tagId != null) {
                tagId.getKursTagCollection().add(kursTag);
                tagId = em.merge(tagId);
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

    public void edit(KursTag kursTag) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            KursTag persistentKursTag = em.find(KursTag.class, kursTag.getKtId());
            Kurs kursIdOld = persistentKursTag.getKursId();
            Kurs kursIdNew = kursTag.getKursId();
            Tag tagIdOld = persistentKursTag.getTagId();
            Tag tagIdNew = kursTag.getTagId();
            if (kursIdNew != null) {
                kursIdNew = em.getReference(kursIdNew.getClass(), kursIdNew.getKursId());
                kursTag.setKursId(kursIdNew);
            }
            if (tagIdNew != null) {
                tagIdNew = em.getReference(tagIdNew.getClass(), tagIdNew.getTagId());
                kursTag.setTagId(tagIdNew);
            }
            kursTag = em.merge(kursTag);
            if (kursIdOld != null && !kursIdOld.equals(kursIdNew)) {
                kursIdOld.getKursTagCollection().remove(kursTag);
                kursIdOld = em.merge(kursIdOld);
            }
            if (kursIdNew != null && !kursIdNew.equals(kursIdOld)) {
                kursIdNew.getKursTagCollection().add(kursTag);
                kursIdNew = em.merge(kursIdNew);
            }
            if (tagIdOld != null && !tagIdOld.equals(tagIdNew)) {
                tagIdOld.getKursTagCollection().remove(kursTag);
                tagIdOld = em.merge(tagIdOld);
            }
            if (tagIdNew != null && !tagIdNew.equals(tagIdOld)) {
                tagIdNew.getKursTagCollection().add(kursTag);
                tagIdNew = em.merge(tagIdNew);
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
                Integer id = kursTag.getKtId();
                if (findKursTag(id) == null) {
                    throw new NonexistentEntityException("The kursTag with id " + id + " no longer exists.");
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
            KursTag kursTag;
            try {
                kursTag = em.getReference(KursTag.class, id);
                kursTag.getKtId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The kursTag with id " + id + " no longer exists.", enfe);
            }
            Kurs kursId = kursTag.getKursId();
            if (kursId != null) {
                kursId.getKursTagCollection().remove(kursTag);
                kursId = em.merge(kursId);
            }
            Tag tagId = kursTag.getTagId();
            if (tagId != null) {
                tagId.getKursTagCollection().remove(kursTag);
                tagId = em.merge(tagId);
            }
            em.remove(kursTag);
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

    public List<KursTag> findKursTagEntities() {
        return findKursTagEntities(true, -1, -1);
    }

    public List<KursTag> findKursTagEntities(int maxResults, int firstResult) {
        return findKursTagEntities(false, maxResults, firstResult);
    }

    private List<KursTag> findKursTagEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(KursTag.class));
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

    public KursTag findKursTag(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(KursTag.class, id);
        } finally {
            em.close();
        }
    }

    public int getKursTagCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<KursTag> rt = cq.from(KursTag.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
