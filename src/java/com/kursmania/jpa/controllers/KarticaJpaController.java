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
import com.kursmania.jpa.entities.Evidencija;
import com.kursmania.jpa.entities.Kartica;
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
public class KarticaJpaController implements Serializable {

    public KarticaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Kartica kartica) throws RollbackFailureException, Exception {
        if (kartica.getEvidencijaCollection() == null) {
            kartica.setEvidencijaCollection(new ArrayList<Evidencija>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Korisnik korisnikId = kartica.getKorisnikId();
            if (korisnikId != null) {
                korisnikId = em.getReference(korisnikId.getClass(), korisnikId.getKorisnikId());
                kartica.setKorisnikId(korisnikId);
            }
            Collection<Evidencija> attachedEvidencijaCollection = new ArrayList<Evidencija>();
            for (Evidencija evidencijaCollectionEvidencijaToAttach : kartica.getEvidencijaCollection()) {
                evidencijaCollectionEvidencijaToAttach = em.getReference(evidencijaCollectionEvidencijaToAttach.getClass(), evidencijaCollectionEvidencijaToAttach.getEvidencijaId());
                attachedEvidencijaCollection.add(evidencijaCollectionEvidencijaToAttach);
            }
            kartica.setEvidencijaCollection(attachedEvidencijaCollection);
            em.persist(kartica);
            if (korisnikId != null) {
                korisnikId.getKarticaCollection().add(kartica);
                korisnikId = em.merge(korisnikId);
            }
            for (Evidencija evidencijaCollectionEvidencija : kartica.getEvidencijaCollection()) {
                Kartica oldKarticaIdOfEvidencijaCollectionEvidencija = evidencijaCollectionEvidencija.getKarticaId();
                evidencijaCollectionEvidencija.setKarticaId(kartica);
                evidencijaCollectionEvidencija = em.merge(evidencijaCollectionEvidencija);
                if (oldKarticaIdOfEvidencijaCollectionEvidencija != null) {
                    oldKarticaIdOfEvidencijaCollectionEvidencija.getEvidencijaCollection().remove(evidencijaCollectionEvidencija);
                    oldKarticaIdOfEvidencijaCollectionEvidencija = em.merge(oldKarticaIdOfEvidencijaCollectionEvidencija);
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

    public void edit(Kartica kartica) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Kartica persistentKartica = em.find(Kartica.class, kartica.getKarticaId());
            Korisnik korisnikIdOld = persistentKartica.getKorisnikId();
            Korisnik korisnikIdNew = kartica.getKorisnikId();
            Collection<Evidencija> evidencijaCollectionOld = persistentKartica.getEvidencijaCollection();
            Collection<Evidencija> evidencijaCollectionNew = kartica.getEvidencijaCollection();
            if (korisnikIdNew != null) {
                korisnikIdNew = em.getReference(korisnikIdNew.getClass(), korisnikIdNew.getKorisnikId());
                kartica.setKorisnikId(korisnikIdNew);
            }
            Collection<Evidencija> attachedEvidencijaCollectionNew = new ArrayList<Evidencija>();
            for (Evidencija evidencijaCollectionNewEvidencijaToAttach : evidencijaCollectionNew) {
                evidencijaCollectionNewEvidencijaToAttach = em.getReference(evidencijaCollectionNewEvidencijaToAttach.getClass(), evidencijaCollectionNewEvidencijaToAttach.getEvidencijaId());
                attachedEvidencijaCollectionNew.add(evidencijaCollectionNewEvidencijaToAttach);
            }
            evidencijaCollectionNew = attachedEvidencijaCollectionNew;
            kartica.setEvidencijaCollection(evidencijaCollectionNew);
            kartica = em.merge(kartica);
            if (korisnikIdOld != null && !korisnikIdOld.equals(korisnikIdNew)) {
                korisnikIdOld.getKarticaCollection().remove(kartica);
                korisnikIdOld = em.merge(korisnikIdOld);
            }
            if (korisnikIdNew != null && !korisnikIdNew.equals(korisnikIdOld)) {
                korisnikIdNew.getKarticaCollection().add(kartica);
                korisnikIdNew = em.merge(korisnikIdNew);
            }
            for (Evidencija evidencijaCollectionOldEvidencija : evidencijaCollectionOld) {
                if (!evidencijaCollectionNew.contains(evidencijaCollectionOldEvidencija)) {
                    evidencijaCollectionOldEvidencija.setKarticaId(null);
                    evidencijaCollectionOldEvidencija = em.merge(evidencijaCollectionOldEvidencija);
                }
            }
            for (Evidencija evidencijaCollectionNewEvidencija : evidencijaCollectionNew) {
                if (!evidencijaCollectionOld.contains(evidencijaCollectionNewEvidencija)) {
                    Kartica oldKarticaIdOfEvidencijaCollectionNewEvidencija = evidencijaCollectionNewEvidencija.getKarticaId();
                    evidencijaCollectionNewEvidencija.setKarticaId(kartica);
                    evidencijaCollectionNewEvidencija = em.merge(evidencijaCollectionNewEvidencija);
                    if (oldKarticaIdOfEvidencijaCollectionNewEvidencija != null && !oldKarticaIdOfEvidencijaCollectionNewEvidencija.equals(kartica)) {
                        oldKarticaIdOfEvidencijaCollectionNewEvidencija.getEvidencijaCollection().remove(evidencijaCollectionNewEvidencija);
                        oldKarticaIdOfEvidencijaCollectionNewEvidencija = em.merge(oldKarticaIdOfEvidencijaCollectionNewEvidencija);
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
                Integer id = kartica.getKarticaId();
                if (findKartica(id) == null) {
                    throw new NonexistentEntityException("The kartica with id " + id + " no longer exists.");
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
            Kartica kartica;
            try {
                kartica = em.getReference(Kartica.class, id);
                kartica.getKarticaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The kartica with id " + id + " no longer exists.", enfe);
            }
            Korisnik korisnikId = kartica.getKorisnikId();
            if (korisnikId != null) {
                korisnikId.getKarticaCollection().remove(kartica);
                korisnikId = em.merge(korisnikId);
            }
            Collection<Evidencija> evidencijaCollection = kartica.getEvidencijaCollection();
            for (Evidencija evidencijaCollectionEvidencija : evidencijaCollection) {
                evidencijaCollectionEvidencija.setKarticaId(null);
                evidencijaCollectionEvidencija = em.merge(evidencijaCollectionEvidencija);
            }
            em.remove(kartica);
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

    public List<Kartica> findKarticaEntities() {
        return findKarticaEntities(true, -1, -1);
    }

    public List<Kartica> findKarticaEntities(int maxResults, int firstResult) {
        return findKarticaEntities(false, maxResults, firstResult);
    }

    private List<Kartica> findKarticaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Kartica.class));
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

    public Kartica findKartica(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Kartica.class, id);
        } finally {
            em.close();
        }
    }

    public int getKarticaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Kartica> rt = cq.from(Kartica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
