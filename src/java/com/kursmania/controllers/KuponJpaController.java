/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kursmania.controllers;

import com.kursmania.controllers.exceptions.NonexistentEntityException;
import com.kursmania.controllers.exceptions.RollbackFailureException;
import com.kursmania.entities.Kupon;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.kursmania.entities.Kurs;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Andrej Kubat
 */
public class KuponJpaController implements Serializable {

    public KuponJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Kupon kupon) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Kurs kursId = kupon.getKursId();
            if (kursId != null) {
                kursId = em.getReference(kursId.getClass(), kursId.getKursId());
                kupon.setKursId(kursId);
            }
            em.persist(kupon);
            if (kursId != null) {
                kursId.getKuponCollection().add(kupon);
                kursId = em.merge(kursId);
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

    public void edit(Kupon kupon) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Kupon persistentKupon = em.find(Kupon.class, kupon.getKuponId());
            Kurs kursIdOld = persistentKupon.getKursId();
            Kurs kursIdNew = kupon.getKursId();
            if (kursIdNew != null) {
                kursIdNew = em.getReference(kursIdNew.getClass(), kursIdNew.getKursId());
                kupon.setKursId(kursIdNew);
            }
            kupon = em.merge(kupon);
            if (kursIdOld != null && !kursIdOld.equals(kursIdNew)) {
                kursIdOld.getKuponCollection().remove(kupon);
                kursIdOld = em.merge(kursIdOld);
            }
            if (kursIdNew != null && !kursIdNew.equals(kursIdOld)) {
                kursIdNew.getKuponCollection().add(kupon);
                kursIdNew = em.merge(kursIdNew);
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
                Integer id = kupon.getKuponId();
                if (findKupon(id) == null) {
                    throw new NonexistentEntityException("The kupon with id " + id + " no longer exists.");
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
            Kupon kupon;
            try {
                kupon = em.getReference(Kupon.class, id);
                kupon.getKuponId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The kupon with id " + id + " no longer exists.", enfe);
            }
            Kurs kursId = kupon.getKursId();
            if (kursId != null) {
                kursId.getKuponCollection().remove(kupon);
                kursId = em.merge(kursId);
            }
            em.remove(kupon);
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

    public List<Kupon> findKuponEntities() {
        return findKuponEntities(true, -1, -1);
    }

    public List<Kupon> findKuponEntities(int maxResults, int firstResult) {
        return findKuponEntities(false, maxResults, firstResult);
    }

    private List<Kupon> findKuponEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Kupon.class));
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

    public Kupon findKupon(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Kupon.class, id);
        } finally {
            em.close();
        }
    }

    public int getKuponCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Kupon> rt = cq.from(Kupon.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
