/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kursmania.jpa.controllers;

import com.kursmania.jpa.controllers.exceptions.NonexistentEntityException;
import com.kursmania.jpa.controllers.exceptions.RollbackFailureException;
import com.kursmania.jpa.entities.Komentar;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.kursmania.jpa.entities.Korisnik;
import com.kursmania.jpa.entities.Kurs;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Andrej Kubat
 */
public class KomentarJpaController implements Serializable {

    public KomentarJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Komentar komentar) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Korisnik korisnikId = komentar.getKorisnikId();
            if (korisnikId != null) {
                korisnikId = em.getReference(korisnikId.getClass(), korisnikId.getKorisnikId());
                komentar.setKorisnikId(korisnikId);
            }
            Kurs kursId = komentar.getKursId();
            if (kursId != null) {
                kursId = em.getReference(kursId.getClass(), kursId.getKursId());
                komentar.setKursId(kursId);
            }
            em.persist(komentar);
            if (korisnikId != null) {
                korisnikId.getKomentarCollection().add(komentar);
                korisnikId = em.merge(korisnikId);
            }
            if (kursId != null) {
                kursId.getKomentarCollection().add(komentar);
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

    public void edit(Komentar komentar) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Komentar persistentKomentar = em.find(Komentar.class, komentar.getKomentarId());
            Korisnik korisnikIdOld = persistentKomentar.getKorisnikId();
            Korisnik korisnikIdNew = komentar.getKorisnikId();
            Kurs kursIdOld = persistentKomentar.getKursId();
            Kurs kursIdNew = komentar.getKursId();
            if (korisnikIdNew != null) {
                korisnikIdNew = em.getReference(korisnikIdNew.getClass(), korisnikIdNew.getKorisnikId());
                komentar.setKorisnikId(korisnikIdNew);
            }
            if (kursIdNew != null) {
                kursIdNew = em.getReference(kursIdNew.getClass(), kursIdNew.getKursId());
                komentar.setKursId(kursIdNew);
            }
            komentar = em.merge(komentar);
            if (korisnikIdOld != null && !korisnikIdOld.equals(korisnikIdNew)) {
                korisnikIdOld.getKomentarCollection().remove(komentar);
                korisnikIdOld = em.merge(korisnikIdOld);
            }
            if (korisnikIdNew != null && !korisnikIdNew.equals(korisnikIdOld)) {
                korisnikIdNew.getKomentarCollection().add(komentar);
                korisnikIdNew = em.merge(korisnikIdNew);
            }
            if (kursIdOld != null && !kursIdOld.equals(kursIdNew)) {
                kursIdOld.getKomentarCollection().remove(komentar);
                kursIdOld = em.merge(kursIdOld);
            }
            if (kursIdNew != null && !kursIdNew.equals(kursIdOld)) {
                kursIdNew.getKomentarCollection().add(komentar);
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
                Integer id = komentar.getKomentarId();
                if (findKomentar(id) == null) {
                    throw new NonexistentEntityException("The komentar with id " + id + " no longer exists.");
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
            Komentar komentar;
            try {
                komentar = em.getReference(Komentar.class, id);
                komentar.getKomentarId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The komentar with id " + id + " no longer exists.", enfe);
            }
            Korisnik korisnikId = komentar.getKorisnikId();
            if (korisnikId != null) {
                korisnikId.getKomentarCollection().remove(komentar);
                korisnikId = em.merge(korisnikId);
            }
            Kurs kursId = komentar.getKursId();
            if (kursId != null) {
                kursId.getKomentarCollection().remove(komentar);
                kursId = em.merge(kursId);
            }
            em.remove(komentar);
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

    public List<Komentar> findKomentarEntities() {
        return findKomentarEntities(true, -1, -1);
    }

    public List<Komentar> findKomentarEntities(int maxResults, int firstResult) {
        return findKomentarEntities(false, maxResults, firstResult);
    }

    private List<Komentar> findKomentarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Komentar.class));
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

    public Komentar findKomentar(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Komentar.class, id);
        } finally {
            em.close();
        }
    }

    public int getKomentarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Komentar> rt = cq.from(Komentar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
