/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kursmania.controllers;

import com.kursmania.controllers.exceptions.NonexistentEntityException;
import com.kursmania.controllers.exceptions.RollbackFailureException;
import com.kursmania.entities.Evidencija;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.kursmania.entities.Kartica;
import com.kursmania.entities.Korisnik;
import com.kursmania.entities.Kurs;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Andrej Kubat
 */
public class EvidencijaJpaController implements Serializable {

    public EvidencijaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Evidencija evidencija) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Kartica karticaId = evidencija.getKarticaId();
            if (karticaId != null) {
                karticaId = em.getReference(karticaId.getClass(), karticaId.getKarticaId());
                evidencija.setKarticaId(karticaId);
            }
            Korisnik korisnikId = evidencija.getKorisnikId();
            if (korisnikId != null) {
                korisnikId = em.getReference(korisnikId.getClass(), korisnikId.getKorisnikId());
                evidencija.setKorisnikId(korisnikId);
            }
            Kurs kursId = evidencija.getKursId();
            if (kursId != null) {
                kursId = em.getReference(kursId.getClass(), kursId.getKursId());
                evidencija.setKursId(kursId);
            }
            em.persist(evidencija);
            if (karticaId != null) {
                karticaId.getEvidencijaCollection().add(evidencija);
                karticaId = em.merge(karticaId);
            }
            if (korisnikId != null) {
                korisnikId.getEvidencijaCollection().add(evidencija);
                korisnikId = em.merge(korisnikId);
            }
            if (kursId != null) {
                kursId.getEvidencijaCollection().add(evidencija);
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

    public void edit(Evidencija evidencija) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Evidencija persistentEvidencija = em.find(Evidencija.class, evidencija.getEvidencijaId());
            Kartica karticaIdOld = persistentEvidencija.getKarticaId();
            Kartica karticaIdNew = evidencija.getKarticaId();
            Korisnik korisnikIdOld = persistentEvidencija.getKorisnikId();
            Korisnik korisnikIdNew = evidencija.getKorisnikId();
            Kurs kursIdOld = persistentEvidencija.getKursId();
            Kurs kursIdNew = evidencija.getKursId();
            if (karticaIdNew != null) {
                karticaIdNew = em.getReference(karticaIdNew.getClass(), karticaIdNew.getKarticaId());
                evidencija.setKarticaId(karticaIdNew);
            }
            if (korisnikIdNew != null) {
                korisnikIdNew = em.getReference(korisnikIdNew.getClass(), korisnikIdNew.getKorisnikId());
                evidencija.setKorisnikId(korisnikIdNew);
            }
            if (kursIdNew != null) {
                kursIdNew = em.getReference(kursIdNew.getClass(), kursIdNew.getKursId());
                evidencija.setKursId(kursIdNew);
            }
            evidencija = em.merge(evidencija);
            if (karticaIdOld != null && !karticaIdOld.equals(karticaIdNew)) {
                karticaIdOld.getEvidencijaCollection().remove(evidencija);
                karticaIdOld = em.merge(karticaIdOld);
            }
            if (karticaIdNew != null && !karticaIdNew.equals(karticaIdOld)) {
                karticaIdNew.getEvidencijaCollection().add(evidencija);
                karticaIdNew = em.merge(karticaIdNew);
            }
            if (korisnikIdOld != null && !korisnikIdOld.equals(korisnikIdNew)) {
                korisnikIdOld.getEvidencijaCollection().remove(evidencija);
                korisnikIdOld = em.merge(korisnikIdOld);
            }
            if (korisnikIdNew != null && !korisnikIdNew.equals(korisnikIdOld)) {
                korisnikIdNew.getEvidencijaCollection().add(evidencija);
                korisnikIdNew = em.merge(korisnikIdNew);
            }
            if (kursIdOld != null && !kursIdOld.equals(kursIdNew)) {
                kursIdOld.getEvidencijaCollection().remove(evidencija);
                kursIdOld = em.merge(kursIdOld);
            }
            if (kursIdNew != null && !kursIdNew.equals(kursIdOld)) {
                kursIdNew.getEvidencijaCollection().add(evidencija);
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
                Integer id = evidencija.getEvidencijaId();
                if (findEvidencija(id) == null) {
                    throw new NonexistentEntityException("The evidencija with id " + id + " no longer exists.");
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
            Evidencija evidencija;
            try {
                evidencija = em.getReference(Evidencija.class, id);
                evidencija.getEvidencijaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The evidencija with id " + id + " no longer exists.", enfe);
            }
            Kartica karticaId = evidencija.getKarticaId();
            if (karticaId != null) {
                karticaId.getEvidencijaCollection().remove(evidencija);
                karticaId = em.merge(karticaId);
            }
            Korisnik korisnikId = evidencija.getKorisnikId();
            if (korisnikId != null) {
                korisnikId.getEvidencijaCollection().remove(evidencija);
                korisnikId = em.merge(korisnikId);
            }
            Kurs kursId = evidencija.getKursId();
            if (kursId != null) {
                kursId.getEvidencijaCollection().remove(evidencija);
                kursId = em.merge(kursId);
            }
            em.remove(evidencija);
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

    public List<Evidencija> findEvidencijaEntities() {
        return findEvidencijaEntities(true, -1, -1);
    }

    public List<Evidencija> findEvidencijaEntities(int maxResults, int firstResult) {
        return findEvidencijaEntities(false, maxResults, firstResult);
    }

    private List<Evidencija> findEvidencijaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Evidencija.class));
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

    public Evidencija findEvidencija(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Evidencija.class, id);
        } finally {
            em.close();
        }
    }

    public int getEvidencijaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Evidencija> rt = cq.from(Evidencija.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
