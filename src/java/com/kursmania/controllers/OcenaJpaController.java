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
import com.kursmania.entities.Korisnik;
import com.kursmania.entities.Kurs;
import com.kursmania.entities.Ocena;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Andrej Kubat
 */
public class OcenaJpaController implements Serializable {

    public OcenaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ocena ocena) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Korisnik korisnikId = ocena.getKorisnikId();
            if (korisnikId != null) {
                korisnikId = em.getReference(korisnikId.getClass(), korisnikId.getKorisnikId());
                ocena.setKorisnikId(korisnikId);
            }
            Kurs kursId = ocena.getKursId();
            if (kursId != null) {
                kursId = em.getReference(kursId.getClass(), kursId.getKursId());
                ocena.setKursId(kursId);
            }
            em.persist(ocena);
            if (korisnikId != null) {
                korisnikId.getOcenaCollection().add(ocena);
                korisnikId = em.merge(korisnikId);
            }
            if (kursId != null) {
                kursId.getOcenaCollection().add(ocena);
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

    public void edit(Ocena ocena) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Ocena persistentOcena = em.find(Ocena.class, ocena.getOcenaId());
            Korisnik korisnikIdOld = persistentOcena.getKorisnikId();
            Korisnik korisnikIdNew = ocena.getKorisnikId();
            Kurs kursIdOld = persistentOcena.getKursId();
            Kurs kursIdNew = ocena.getKursId();
            if (korisnikIdNew != null) {
                korisnikIdNew = em.getReference(korisnikIdNew.getClass(), korisnikIdNew.getKorisnikId());
                ocena.setKorisnikId(korisnikIdNew);
            }
            if (kursIdNew != null) {
                kursIdNew = em.getReference(kursIdNew.getClass(), kursIdNew.getKursId());
                ocena.setKursId(kursIdNew);
            }
            ocena = em.merge(ocena);
            if (korisnikIdOld != null && !korisnikIdOld.equals(korisnikIdNew)) {
                korisnikIdOld.getOcenaCollection().remove(ocena);
                korisnikIdOld = em.merge(korisnikIdOld);
            }
            if (korisnikIdNew != null && !korisnikIdNew.equals(korisnikIdOld)) {
                korisnikIdNew.getOcenaCollection().add(ocena);
                korisnikIdNew = em.merge(korisnikIdNew);
            }
            if (kursIdOld != null && !kursIdOld.equals(kursIdNew)) {
                kursIdOld.getOcenaCollection().remove(ocena);
                kursIdOld = em.merge(kursIdOld);
            }
            if (kursIdNew != null && !kursIdNew.equals(kursIdOld)) {
                kursIdNew.getOcenaCollection().add(ocena);
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
                Integer id = ocena.getOcenaId();
                if (findOcena(id) == null) {
                    throw new NonexistentEntityException("The ocena with id " + id + " no longer exists.");
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
            Ocena ocena;
            try {
                ocena = em.getReference(Ocena.class, id);
                ocena.getOcenaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ocena with id " + id + " no longer exists.", enfe);
            }
            Korisnik korisnikId = ocena.getKorisnikId();
            if (korisnikId != null) {
                korisnikId.getOcenaCollection().remove(ocena);
                korisnikId = em.merge(korisnikId);
            }
            Kurs kursId = ocena.getKursId();
            if (kursId != null) {
                kursId.getOcenaCollection().remove(ocena);
                kursId = em.merge(kursId);
            }
            em.remove(ocena);
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

    public List<Ocena> findOcenaEntities() {
        return findOcenaEntities(true, -1, -1);
    }

    public List<Ocena> findOcenaEntities(int maxResults, int firstResult) {
        return findOcenaEntities(false, maxResults, firstResult);
    }

    private List<Ocena> findOcenaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ocena.class));
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

    public Ocena findOcena(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ocena.class, id);
        } finally {
            em.close();
        }
    }

    public int getOcenaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ocena> rt = cq.from(Ocena.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
