/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kursmania.controllers;

import com.kursmania.controllers.exceptions.NonexistentEntityException;
import com.kursmania.controllers.exceptions.RollbackFailureException;
import com.kursmania.entities.Jezik;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.kursmania.entities.Kurs;
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
public class JezikJpaController implements Serializable {

    public JezikJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Jezik jezik) throws RollbackFailureException, Exception {
        if (jezik.getKursCollection() == null) {
            jezik.setKursCollection(new ArrayList<Kurs>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Kurs> attachedKursCollection = new ArrayList<Kurs>();
            for (Kurs kursCollectionKursToAttach : jezik.getKursCollection()) {
                kursCollectionKursToAttach = em.getReference(kursCollectionKursToAttach.getClass(), kursCollectionKursToAttach.getKursId());
                attachedKursCollection.add(kursCollectionKursToAttach);
            }
            jezik.setKursCollection(attachedKursCollection);
            em.persist(jezik);
            for (Kurs kursCollectionKurs : jezik.getKursCollection()) {
                Jezik oldJezikIdOfKursCollectionKurs = kursCollectionKurs.getJezikId();
                kursCollectionKurs.setJezikId(jezik);
                kursCollectionKurs = em.merge(kursCollectionKurs);
                if (oldJezikIdOfKursCollectionKurs != null) {
                    oldJezikIdOfKursCollectionKurs.getKursCollection().remove(kursCollectionKurs);
                    oldJezikIdOfKursCollectionKurs = em.merge(oldJezikIdOfKursCollectionKurs);
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

    public void edit(Jezik jezik) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Jezik persistentJezik = em.find(Jezik.class, jezik.getJezikId());
            Collection<Kurs> kursCollectionOld = persistentJezik.getKursCollection();
            Collection<Kurs> kursCollectionNew = jezik.getKursCollection();
            Collection<Kurs> attachedKursCollectionNew = new ArrayList<Kurs>();
            for (Kurs kursCollectionNewKursToAttach : kursCollectionNew) {
                kursCollectionNewKursToAttach = em.getReference(kursCollectionNewKursToAttach.getClass(), kursCollectionNewKursToAttach.getKursId());
                attachedKursCollectionNew.add(kursCollectionNewKursToAttach);
            }
            kursCollectionNew = attachedKursCollectionNew;
            jezik.setKursCollection(kursCollectionNew);
            jezik = em.merge(jezik);
            for (Kurs kursCollectionOldKurs : kursCollectionOld) {
                if (!kursCollectionNew.contains(kursCollectionOldKurs)) {
                    kursCollectionOldKurs.setJezikId(null);
                    kursCollectionOldKurs = em.merge(kursCollectionOldKurs);
                }
            }
            for (Kurs kursCollectionNewKurs : kursCollectionNew) {
                if (!kursCollectionOld.contains(kursCollectionNewKurs)) {
                    Jezik oldJezikIdOfKursCollectionNewKurs = kursCollectionNewKurs.getJezikId();
                    kursCollectionNewKurs.setJezikId(jezik);
                    kursCollectionNewKurs = em.merge(kursCollectionNewKurs);
                    if (oldJezikIdOfKursCollectionNewKurs != null && !oldJezikIdOfKursCollectionNewKurs.equals(jezik)) {
                        oldJezikIdOfKursCollectionNewKurs.getKursCollection().remove(kursCollectionNewKurs);
                        oldJezikIdOfKursCollectionNewKurs = em.merge(oldJezikIdOfKursCollectionNewKurs);
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
                Integer id = jezik.getJezikId();
                if (findJezik(id) == null) {
                    throw new NonexistentEntityException("The jezik with id " + id + " no longer exists.");
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
            Jezik jezik;
            try {
                jezik = em.getReference(Jezik.class, id);
                jezik.getJezikId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The jezik with id " + id + " no longer exists.", enfe);
            }
            Collection<Kurs> kursCollection = jezik.getKursCollection();
            for (Kurs kursCollectionKurs : kursCollection) {
                kursCollectionKurs.setJezikId(null);
                kursCollectionKurs = em.merge(kursCollectionKurs);
            }
            em.remove(jezik);
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

    public List<Jezik> findJezikEntities() {
        return findJezikEntities(true, -1, -1);
    }

    public List<Jezik> findJezikEntities(int maxResults, int firstResult) {
        return findJezikEntities(false, maxResults, firstResult);
    }

    private List<Jezik> findJezikEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Jezik.class));
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

    public Jezik findJezik(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Jezik.class, id);
        } finally {
            em.close();
        }
    }

    public int getJezikCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Jezik> rt = cq.from(Jezik.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
