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
import com.kursmania.entities.Kategorija;
import java.util.ArrayList;
import java.util.Collection;
import com.kursmania.entities.Kurs;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Andrej Kubat
 */
public class KategorijaJpaController implements Serializable {

    public KategorijaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Kategorija kategorija) throws RollbackFailureException, Exception {
        if (kategorija.getKategorijaCollection() == null) {
            kategorija.setKategorijaCollection(new ArrayList<Kategorija>());
        }
        if (kategorija.getKursCollection() == null) {
            kategorija.setKursCollection(new ArrayList<Kurs>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Kategorija nadKategorijaId = kategorija.getNadKategorijaId();
            if (nadKategorijaId != null) {
                nadKategorijaId = em.getReference(nadKategorijaId.getClass(), nadKategorijaId.getKategorijaId());
                kategorija.setNadKategorijaId(nadKategorijaId);
            }
            Collection<Kategorija> attachedKategorijaCollection = new ArrayList<Kategorija>();
            for (Kategorija kategorijaCollectionKategorijaToAttach : kategorija.getKategorijaCollection()) {
                kategorijaCollectionKategorijaToAttach = em.getReference(kategorijaCollectionKategorijaToAttach.getClass(), kategorijaCollectionKategorijaToAttach.getKategorijaId());
                attachedKategorijaCollection.add(kategorijaCollectionKategorijaToAttach);
            }
            kategorija.setKategorijaCollection(attachedKategorijaCollection);
            Collection<Kurs> attachedKursCollection = new ArrayList<Kurs>();
            for (Kurs kursCollectionKursToAttach : kategorija.getKursCollection()) {
                kursCollectionKursToAttach = em.getReference(kursCollectionKursToAttach.getClass(), kursCollectionKursToAttach.getKursId());
                attachedKursCollection.add(kursCollectionKursToAttach);
            }
            kategorija.setKursCollection(attachedKursCollection);
            em.persist(kategorija);
            if (nadKategorijaId != null) {
                nadKategorijaId.getKategorijaCollection().add(kategorija);
                nadKategorijaId = em.merge(nadKategorijaId);
            }
            for (Kategorija kategorijaCollectionKategorija : kategorija.getKategorijaCollection()) {
                Kategorija oldNadKategorijaIdOfKategorijaCollectionKategorija = kategorijaCollectionKategorija.getNadKategorijaId();
                kategorijaCollectionKategorija.setNadKategorijaId(kategorija);
                kategorijaCollectionKategorija = em.merge(kategorijaCollectionKategorija);
                if (oldNadKategorijaIdOfKategorijaCollectionKategorija != null) {
                    oldNadKategorijaIdOfKategorijaCollectionKategorija.getKategorijaCollection().remove(kategorijaCollectionKategorija);
                    oldNadKategorijaIdOfKategorijaCollectionKategorija = em.merge(oldNadKategorijaIdOfKategorijaCollectionKategorija);
                }
            }
            for (Kurs kursCollectionKurs : kategorija.getKursCollection()) {
                Kategorija oldKategorijaIdOfKursCollectionKurs = kursCollectionKurs.getKategorijaId();
                kursCollectionKurs.setKategorijaId(kategorija);
                kursCollectionKurs = em.merge(kursCollectionKurs);
                if (oldKategorijaIdOfKursCollectionKurs != null) {
                    oldKategorijaIdOfKursCollectionKurs.getKursCollection().remove(kursCollectionKurs);
                    oldKategorijaIdOfKursCollectionKurs = em.merge(oldKategorijaIdOfKursCollectionKurs);
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

    public void edit(Kategorija kategorija) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Kategorija persistentKategorija = em.find(Kategorija.class, kategorija.getKategorijaId());
            Kategorija nadKategorijaIdOld = persistentKategorija.getNadKategorijaId();
            Kategorija nadKategorijaIdNew = kategorija.getNadKategorijaId();
            Collection<Kategorija> kategorijaCollectionOld = persistentKategorija.getKategorijaCollection();
            Collection<Kategorija> kategorijaCollectionNew = kategorija.getKategorijaCollection();
            Collection<Kurs> kursCollectionOld = persistentKategorija.getKursCollection();
            Collection<Kurs> kursCollectionNew = kategorija.getKursCollection();
            if (nadKategorijaIdNew != null) {
                nadKategorijaIdNew = em.getReference(nadKategorijaIdNew.getClass(), nadKategorijaIdNew.getKategorijaId());
                kategorija.setNadKategorijaId(nadKategorijaIdNew);
            }
            Collection<Kategorija> attachedKategorijaCollectionNew = new ArrayList<Kategorija>();
            for (Kategorija kategorijaCollectionNewKategorijaToAttach : kategorijaCollectionNew) {
                kategorijaCollectionNewKategorijaToAttach = em.getReference(kategorijaCollectionNewKategorijaToAttach.getClass(), kategorijaCollectionNewKategorijaToAttach.getKategorijaId());
                attachedKategorijaCollectionNew.add(kategorijaCollectionNewKategorijaToAttach);
            }
            kategorijaCollectionNew = attachedKategorijaCollectionNew;
            kategorija.setKategorijaCollection(kategorijaCollectionNew);
            Collection<Kurs> attachedKursCollectionNew = new ArrayList<Kurs>();
            for (Kurs kursCollectionNewKursToAttach : kursCollectionNew) {
                kursCollectionNewKursToAttach = em.getReference(kursCollectionNewKursToAttach.getClass(), kursCollectionNewKursToAttach.getKursId());
                attachedKursCollectionNew.add(kursCollectionNewKursToAttach);
            }
            kursCollectionNew = attachedKursCollectionNew;
            kategorija.setKursCollection(kursCollectionNew);
            kategorija = em.merge(kategorija);
            if (nadKategorijaIdOld != null && !nadKategorijaIdOld.equals(nadKategorijaIdNew)) {
                nadKategorijaIdOld.getKategorijaCollection().remove(kategorija);
                nadKategorijaIdOld = em.merge(nadKategorijaIdOld);
            }
            if (nadKategorijaIdNew != null && !nadKategorijaIdNew.equals(nadKategorijaIdOld)) {
                nadKategorijaIdNew.getKategorijaCollection().add(kategorija);
                nadKategorijaIdNew = em.merge(nadKategorijaIdNew);
            }
            for (Kategorija kategorijaCollectionOldKategorija : kategorijaCollectionOld) {
                if (!kategorijaCollectionNew.contains(kategorijaCollectionOldKategorija)) {
                    kategorijaCollectionOldKategorija.setNadKategorijaId(null);
                    kategorijaCollectionOldKategorija = em.merge(kategorijaCollectionOldKategorija);
                }
            }
            for (Kategorija kategorijaCollectionNewKategorija : kategorijaCollectionNew) {
                if (!kategorijaCollectionOld.contains(kategorijaCollectionNewKategorija)) {
                    Kategorija oldNadKategorijaIdOfKategorijaCollectionNewKategorija = kategorijaCollectionNewKategorija.getNadKategorijaId();
                    kategorijaCollectionNewKategorija.setNadKategorijaId(kategorija);
                    kategorijaCollectionNewKategorija = em.merge(kategorijaCollectionNewKategorija);
                    if (oldNadKategorijaIdOfKategorijaCollectionNewKategorija != null && !oldNadKategorijaIdOfKategorijaCollectionNewKategorija.equals(kategorija)) {
                        oldNadKategorijaIdOfKategorijaCollectionNewKategorija.getKategorijaCollection().remove(kategorijaCollectionNewKategorija);
                        oldNadKategorijaIdOfKategorijaCollectionNewKategorija = em.merge(oldNadKategorijaIdOfKategorijaCollectionNewKategorija);
                    }
                }
            }
            for (Kurs kursCollectionOldKurs : kursCollectionOld) {
                if (!kursCollectionNew.contains(kursCollectionOldKurs)) {
                    kursCollectionOldKurs.setKategorijaId(null);
                    kursCollectionOldKurs = em.merge(kursCollectionOldKurs);
                }
            }
            for (Kurs kursCollectionNewKurs : kursCollectionNew) {
                if (!kursCollectionOld.contains(kursCollectionNewKurs)) {
                    Kategorija oldKategorijaIdOfKursCollectionNewKurs = kursCollectionNewKurs.getKategorijaId();
                    kursCollectionNewKurs.setKategorijaId(kategorija);
                    kursCollectionNewKurs = em.merge(kursCollectionNewKurs);
                    if (oldKategorijaIdOfKursCollectionNewKurs != null && !oldKategorijaIdOfKursCollectionNewKurs.equals(kategorija)) {
                        oldKategorijaIdOfKursCollectionNewKurs.getKursCollection().remove(kursCollectionNewKurs);
                        oldKategorijaIdOfKursCollectionNewKurs = em.merge(oldKategorijaIdOfKursCollectionNewKurs);
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
                Integer id = kategorija.getKategorijaId();
                if (findKategorija(id) == null) {
                    throw new NonexistentEntityException("The kategorija with id " + id + " no longer exists.");
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
            Kategorija kategorija;
            try {
                kategorija = em.getReference(Kategorija.class, id);
                kategorija.getKategorijaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The kategorija with id " + id + " no longer exists.", enfe);
            }
            Kategorija nadKategorijaId = kategorija.getNadKategorijaId();
            if (nadKategorijaId != null) {
                nadKategorijaId.getKategorijaCollection().remove(kategorija);
                nadKategorijaId = em.merge(nadKategorijaId);
            }
            Collection<Kategorija> kategorijaCollection = kategorija.getKategorijaCollection();
            for (Kategorija kategorijaCollectionKategorija : kategorijaCollection) {
                kategorijaCollectionKategorija.setNadKategorijaId(null);
                kategorijaCollectionKategorija = em.merge(kategorijaCollectionKategorija);
            }
            Collection<Kurs> kursCollection = kategorija.getKursCollection();
            for (Kurs kursCollectionKurs : kursCollection) {
                kursCollectionKurs.setKategorijaId(null);
                kursCollectionKurs = em.merge(kursCollectionKurs);
            }
            em.remove(kategorija);
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

    public List<Kategorija> findKategorijaEntities() {
        return findKategorijaEntities(true, -1, -1);
    }

    public List<Kategorija> findKategorijaEntities(int maxResults, int firstResult) {
        return findKategorijaEntities(false, maxResults, firstResult);
    }

    private List<Kategorija> findKategorijaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Kategorija.class));
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

    public Kategorija findKategorija(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Kategorija.class, id);
        } finally {
            em.close();
        }
    }

    public int getKategorijaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Kategorija> rt = cq.from(Kategorija.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
