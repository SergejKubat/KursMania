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
import com.kursmania.jpa.entities.Korpa;
import com.kursmania.jpa.entities.Kurs;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Andrej Kubat
 */
public class KorpaJpaController implements Serializable {

    public KorpaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Korpa korpa) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Korisnik korisnikId = korpa.getKorisnikId();
            if (korisnikId != null) {
                korisnikId = em.getReference(korisnikId.getClass(), korisnikId.getKorisnikId());
                korpa.setKorisnikId(korisnikId);
            }
            Kurs kursId = korpa.getKursId();
            if (kursId != null) {
                kursId = em.getReference(kursId.getClass(), kursId.getKursId());
                korpa.setKursId(kursId);
            }
            em.persist(korpa);
            if (korisnikId != null) {
                korisnikId.getKorpaCollection().add(korpa);
                korisnikId = em.merge(korisnikId);
            }
            if (kursId != null) {
                kursId.getKorpaCollection().add(korpa);
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

    public void edit(Korpa korpa) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Korpa persistentKorpa = em.find(Korpa.class, korpa.getKorpaId());
            Korisnik korisnikIdOld = persistentKorpa.getKorisnikId();
            Korisnik korisnikIdNew = korpa.getKorisnikId();
            Kurs kursIdOld = persistentKorpa.getKursId();
            Kurs kursIdNew = korpa.getKursId();
            if (korisnikIdNew != null) {
                korisnikIdNew = em.getReference(korisnikIdNew.getClass(), korisnikIdNew.getKorisnikId());
                korpa.setKorisnikId(korisnikIdNew);
            }
            if (kursIdNew != null) {
                kursIdNew = em.getReference(kursIdNew.getClass(), kursIdNew.getKursId());
                korpa.setKursId(kursIdNew);
            }
            korpa = em.merge(korpa);
            if (korisnikIdOld != null && !korisnikIdOld.equals(korisnikIdNew)) {
                korisnikIdOld.getKorpaCollection().remove(korpa);
                korisnikIdOld = em.merge(korisnikIdOld);
            }
            if (korisnikIdNew != null && !korisnikIdNew.equals(korisnikIdOld)) {
                korisnikIdNew.getKorpaCollection().add(korpa);
                korisnikIdNew = em.merge(korisnikIdNew);
            }
            if (kursIdOld != null && !kursIdOld.equals(kursIdNew)) {
                kursIdOld.getKorpaCollection().remove(korpa);
                kursIdOld = em.merge(kursIdOld);
            }
            if (kursIdNew != null && !kursIdNew.equals(kursIdOld)) {
                kursIdNew.getKorpaCollection().add(korpa);
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
                Integer id = korpa.getKorpaId();
                if (findKorpa(id) == null) {
                    throw new NonexistentEntityException("The korpa with id " + id + " no longer exists.");
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
            Korpa korpa;
            try {
                korpa = em.getReference(Korpa.class, id);
                korpa.getKorpaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The korpa with id " + id + " no longer exists.", enfe);
            }
            Korisnik korisnikId = korpa.getKorisnikId();
            if (korisnikId != null) {
                korisnikId.getKorpaCollection().remove(korpa);
                korisnikId = em.merge(korisnikId);
            }
            Kurs kursId = korpa.getKursId();
            if (kursId != null) {
                kursId.getKorpaCollection().remove(korpa);
                kursId = em.merge(kursId);
            }
            em.remove(korpa);
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

    public List<Korpa> findKorpaEntities() {
        return findKorpaEntities(true, -1, -1);
    }

    public List<Korpa> findKorpaEntities(int maxResults, int firstResult) {
        return findKorpaEntities(false, maxResults, firstResult);
    }

    private List<Korpa> findKorpaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Korpa.class));
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

    public Korpa findKorpa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Korpa.class, id);
        } finally {
            em.close();
        }
    }

    public int getKorpaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Korpa> rt = cq.from(Korpa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
