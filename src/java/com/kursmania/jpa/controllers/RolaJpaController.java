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
import com.kursmania.jpa.entities.Korisnik;
import com.kursmania.jpa.entities.Rola;
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
public class RolaJpaController implements Serializable {

    public RolaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rola rola) throws RollbackFailureException, Exception {
        if (rola.getKorisnikCollection() == null) {
            rola.setKorisnikCollection(new ArrayList<>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Korisnik> attachedKorisnikCollection = new ArrayList<>();
            for (Korisnik korisnikCollectionKorisnikToAttach : rola.getKorisnikCollection()) {
                korisnikCollectionKorisnikToAttach = em.getReference(korisnikCollectionKorisnikToAttach.getClass(), korisnikCollectionKorisnikToAttach.getKorisnikId());
                attachedKorisnikCollection.add(korisnikCollectionKorisnikToAttach);
            }
            rola.setKorisnikCollection(attachedKorisnikCollection);
            em.persist(rola);
            for (Korisnik korisnikCollectionKorisnik : rola.getKorisnikCollection()) {
                Rola oldRolaIdOfKorisnikCollectionKorisnik = korisnikCollectionKorisnik.getRolaId();
                korisnikCollectionKorisnik.setRolaId(rola);
                korisnikCollectionKorisnik = em.merge(korisnikCollectionKorisnik);
                if (oldRolaIdOfKorisnikCollectionKorisnik != null) {
                    oldRolaIdOfKorisnikCollectionKorisnik.getKorisnikCollection().remove(korisnikCollectionKorisnik);
                    oldRolaIdOfKorisnikCollectionKorisnik = em.merge(oldRolaIdOfKorisnikCollectionKorisnik);
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

    public void edit(Rola rola) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Rola persistentRola = em.find(Rola.class, rola.getRolaId());
            Collection<Korisnik> korisnikCollectionOld = persistentRola.getKorisnikCollection();
            Collection<Korisnik> korisnikCollectionNew = rola.getKorisnikCollection();
            Collection<Korisnik> attachedKorisnikCollectionNew = new ArrayList<Korisnik>();
            for (Korisnik korisnikCollectionNewKorisnikToAttach : korisnikCollectionNew) {
                korisnikCollectionNewKorisnikToAttach = em.getReference(korisnikCollectionNewKorisnikToAttach.getClass(), korisnikCollectionNewKorisnikToAttach.getKorisnikId());
                attachedKorisnikCollectionNew.add(korisnikCollectionNewKorisnikToAttach);
            }
            korisnikCollectionNew = attachedKorisnikCollectionNew;
            rola.setKorisnikCollection(korisnikCollectionNew);
            rola = em.merge(rola);
            for (Korisnik korisnikCollectionOldKorisnik : korisnikCollectionOld) {
                if (!korisnikCollectionNew.contains(korisnikCollectionOldKorisnik)) {
                    korisnikCollectionOldKorisnik.setRolaId(null);
                    korisnikCollectionOldKorisnik = em.merge(korisnikCollectionOldKorisnik);
                }
            }
            for (Korisnik korisnikCollectionNewKorisnik : korisnikCollectionNew) {
                if (!korisnikCollectionOld.contains(korisnikCollectionNewKorisnik)) {
                    Rola oldRolaIdOfKorisnikCollectionNewKorisnik = korisnikCollectionNewKorisnik.getRolaId();
                    korisnikCollectionNewKorisnik.setRolaId(rola);
                    korisnikCollectionNewKorisnik = em.merge(korisnikCollectionNewKorisnik);
                    if (oldRolaIdOfKorisnikCollectionNewKorisnik != null && !oldRolaIdOfKorisnikCollectionNewKorisnik.equals(rola)) {
                        oldRolaIdOfKorisnikCollectionNewKorisnik.getKorisnikCollection().remove(korisnikCollectionNewKorisnik);
                        oldRolaIdOfKorisnikCollectionNewKorisnik = em.merge(oldRolaIdOfKorisnikCollectionNewKorisnik);
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
                Integer id = rola.getRolaId();
                if (findRola(id) == null) {
                    throw new NonexistentEntityException("The rola with id " + id + " no longer exists.");
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
            Rola rola;
            try {
                rola = em.getReference(Rola.class, id);
                rola.getRolaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rola with id " + id + " no longer exists.", enfe);
            }
            Collection<Korisnik> korisnikCollection = rola.getKorisnikCollection();
            for (Korisnik korisnikCollectionKorisnik : korisnikCollection) {
                korisnikCollectionKorisnik.setRolaId(null);
                korisnikCollectionKorisnik = em.merge(korisnikCollectionKorisnik);
            }
            em.remove(rola);
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

    public List<Rola> findRolaEntities() {
        return findRolaEntities(true, -1, -1);
    }

    public List<Rola> findRolaEntities(int maxResults, int firstResult) {
        return findRolaEntities(false, maxResults, firstResult);
    }

    private List<Rola> findRolaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rola.class));
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

    public Rola findRola(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rola.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rola> rt = cq.from(Rola.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
