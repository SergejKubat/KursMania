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
import com.kursmania.jpa.entities.Kurs;
import com.kursmania.jpa.entities.Lekcija;
import com.kursmania.jpa.entities.Sekcija;
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
public class SekcijaJpaController implements Serializable {

    public SekcijaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sekcija sekcija) throws RollbackFailureException, Exception {
        if (sekcija.getLekcijaCollection() == null) {
            sekcija.setLekcijaCollection(new ArrayList<Lekcija>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Kurs kursId = sekcija.getKursId();
            if (kursId != null) {
                kursId = em.getReference(kursId.getClass(), kursId.getKursId());
                sekcija.setKursId(kursId);
            }
            Collection<Lekcija> attachedLekcijaCollection = new ArrayList<Lekcija>();
            for (Lekcija lekcijaCollectionLekcijaToAttach : sekcija.getLekcijaCollection()) {
                lekcijaCollectionLekcijaToAttach = em.getReference(lekcijaCollectionLekcijaToAttach.getClass(), lekcijaCollectionLekcijaToAttach.getLekcijaId());
                attachedLekcijaCollection.add(lekcijaCollectionLekcijaToAttach);
            }
            sekcija.setLekcijaCollection(attachedLekcijaCollection);
            em.persist(sekcija);
            if (kursId != null) {
                kursId.getSekcijaCollection().add(sekcija);
                kursId = em.merge(kursId);
            }
            for (Lekcija lekcijaCollectionLekcija : sekcija.getLekcijaCollection()) {
                Sekcija oldSekcijaIdOfLekcijaCollectionLekcija = lekcijaCollectionLekcija.getSekcijaId();
                lekcijaCollectionLekcija.setSekcijaId(sekcija);
                lekcijaCollectionLekcija = em.merge(lekcijaCollectionLekcija);
                if (oldSekcijaIdOfLekcijaCollectionLekcija != null) {
                    oldSekcijaIdOfLekcijaCollectionLekcija.getLekcijaCollection().remove(lekcijaCollectionLekcija);
                    oldSekcijaIdOfLekcijaCollectionLekcija = em.merge(oldSekcijaIdOfLekcijaCollectionLekcija);
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

    public void edit(Sekcija sekcija) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Sekcija persistentSekcija = em.find(Sekcija.class, sekcija.getSekcijaId());
            Kurs kursIdOld = persistentSekcija.getKursId();
            Kurs kursIdNew = sekcija.getKursId();
            Collection<Lekcija> lekcijaCollectionOld = persistentSekcija.getLekcijaCollection();
            Collection<Lekcija> lekcijaCollectionNew = sekcija.getLekcijaCollection();
            if (kursIdNew != null) {
                kursIdNew = em.getReference(kursIdNew.getClass(), kursIdNew.getKursId());
                sekcija.setKursId(kursIdNew);
            }
            Collection<Lekcija> attachedLekcijaCollectionNew = new ArrayList<Lekcija>();
            for (Lekcija lekcijaCollectionNewLekcijaToAttach : lekcijaCollectionNew) {
                lekcijaCollectionNewLekcijaToAttach = em.getReference(lekcijaCollectionNewLekcijaToAttach.getClass(), lekcijaCollectionNewLekcijaToAttach.getLekcijaId());
                attachedLekcijaCollectionNew.add(lekcijaCollectionNewLekcijaToAttach);
            }
            lekcijaCollectionNew = attachedLekcijaCollectionNew;
            sekcija.setLekcijaCollection(lekcijaCollectionNew);
            sekcija = em.merge(sekcija);
            if (kursIdOld != null && !kursIdOld.equals(kursIdNew)) {
                kursIdOld.getSekcijaCollection().remove(sekcija);
                kursIdOld = em.merge(kursIdOld);
            }
            if (kursIdNew != null && !kursIdNew.equals(kursIdOld)) {
                kursIdNew.getSekcijaCollection().add(sekcija);
                kursIdNew = em.merge(kursIdNew);
            }
            for (Lekcija lekcijaCollectionOldLekcija : lekcijaCollectionOld) {
                if (!lekcijaCollectionNew.contains(lekcijaCollectionOldLekcija)) {
                    lekcijaCollectionOldLekcija.setSekcijaId(null);
                    lekcijaCollectionOldLekcija = em.merge(lekcijaCollectionOldLekcija);
                }
            }
            for (Lekcija lekcijaCollectionNewLekcija : lekcijaCollectionNew) {
                if (!lekcijaCollectionOld.contains(lekcijaCollectionNewLekcija)) {
                    Sekcija oldSekcijaIdOfLekcijaCollectionNewLekcija = lekcijaCollectionNewLekcija.getSekcijaId();
                    lekcijaCollectionNewLekcija.setSekcijaId(sekcija);
                    lekcijaCollectionNewLekcija = em.merge(lekcijaCollectionNewLekcija);
                    if (oldSekcijaIdOfLekcijaCollectionNewLekcija != null && !oldSekcijaIdOfLekcijaCollectionNewLekcija.equals(sekcija)) {
                        oldSekcijaIdOfLekcijaCollectionNewLekcija.getLekcijaCollection().remove(lekcijaCollectionNewLekcija);
                        oldSekcijaIdOfLekcijaCollectionNewLekcija = em.merge(oldSekcijaIdOfLekcijaCollectionNewLekcija);
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
                Integer id = sekcija.getSekcijaId();
                if (findSekcija(id) == null) {
                    throw new NonexistentEntityException("The sekcija with id " + id + " no longer exists.");
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
            Sekcija sekcija;
            try {
                sekcija = em.getReference(Sekcija.class, id);
                sekcija.getSekcijaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sekcija with id " + id + " no longer exists.", enfe);
            }
            Kurs kursId = sekcija.getKursId();
            if (kursId != null) {
                kursId.getSekcijaCollection().remove(sekcija);
                kursId = em.merge(kursId);
            }
            Collection<Lekcija> lekcijaCollection = sekcija.getLekcijaCollection();
            for (Lekcija lekcijaCollectionLekcija : lekcijaCollection) {
                lekcijaCollectionLekcija.setSekcijaId(null);
                lekcijaCollectionLekcija = em.merge(lekcijaCollectionLekcija);
            }
            em.remove(sekcija);
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

    public List<Sekcija> findSekcijaEntities() {
        return findSekcijaEntities(true, -1, -1);
    }

    public List<Sekcija> findSekcijaEntities(int maxResults, int firstResult) {
        return findSekcijaEntities(false, maxResults, firstResult);
    }

    private List<Sekcija> findSekcijaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sekcija.class));
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

    public Sekcija findSekcija(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sekcija.class, id);
        } finally {
            em.close();
        }
    }

    public int getSekcijaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sekcija> rt = cq.from(Sekcija.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
