package com.kursmania.jpa.controllers;

import com.kursmania.jpa.controllers.exceptions.NonexistentEntityException;
import com.kursmania.jpa.controllers.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.kursmania.jpa.entities.Rola;
import com.kursmania.jpa.entities.Komentar;
import java.util.ArrayList;
import java.util.Collection;
import com.kursmania.jpa.entities.Ocena;
import com.kursmania.jpa.entities.Evidencija;
import com.kursmania.jpa.entities.Korisnik;
import com.kursmania.jpa.entities.Kurs;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

public class KorisnikJpaController implements Serializable {

    public KorisnikJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Korisnik korisnik) throws RollbackFailureException, Exception {
        if (korisnik.getKomentarCollection() == null) {
            korisnik.setKomentarCollection(new ArrayList<Komentar>());
        }
        if (korisnik.getOcenaCollection() == null) {
            korisnik.setOcenaCollection(new ArrayList<Ocena>());
        }
        if (korisnik.getEvidencijaCollection() == null) {
            korisnik.setEvidencijaCollection(new ArrayList<Evidencija>());
        }
        if (korisnik.getKursCollection() == null) {
            korisnik.setKursCollection(new ArrayList<Kurs>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Rola rolaId = korisnik.getRolaId();
            if (rolaId != null) {
                rolaId = em.getReference(rolaId.getClass(), rolaId.getRolaId());
                korisnik.setRolaId(rolaId);
            }
            Collection<Komentar> attachedKomentarCollection = new ArrayList<Komentar>();
            for (Komentar komentarCollectionKomentarToAttach : korisnik.getKomentarCollection()) {
                komentarCollectionKomentarToAttach = em.getReference(komentarCollectionKomentarToAttach.getClass(), komentarCollectionKomentarToAttach.getKomentarId());
                attachedKomentarCollection.add(komentarCollectionKomentarToAttach);
            }
            korisnik.setKomentarCollection(attachedKomentarCollection);
            Collection<Ocena> attachedOcenaCollection = new ArrayList<Ocena>();
            for (Ocena ocenaCollectionOcenaToAttach : korisnik.getOcenaCollection()) {
                ocenaCollectionOcenaToAttach = em.getReference(ocenaCollectionOcenaToAttach.getClass(), ocenaCollectionOcenaToAttach.getOcenaId());
                attachedOcenaCollection.add(ocenaCollectionOcenaToAttach);
            }
            korisnik.setOcenaCollection(attachedOcenaCollection);
            Collection<Evidencija> attachedEvidencijaCollection = new ArrayList<Evidencija>();
            for (Evidencija evidencijaCollectionEvidencijaToAttach : korisnik.getEvidencijaCollection()) {
                evidencijaCollectionEvidencijaToAttach = em.getReference(evidencijaCollectionEvidencijaToAttach.getClass(), evidencijaCollectionEvidencijaToAttach.getEvidancijaId());
                attachedEvidencijaCollection.add(evidencijaCollectionEvidencijaToAttach);
            }
            korisnik.setEvidencijaCollection(attachedEvidencijaCollection);
            Collection<Kurs> attachedKursCollection = new ArrayList<Kurs>();
            for (Kurs kursCollectionKursToAttach : korisnik.getKursCollection()) {
                kursCollectionKursToAttach = em.getReference(kursCollectionKursToAttach.getClass(), kursCollectionKursToAttach.getKursId());
                attachedKursCollection.add(kursCollectionKursToAttach);
            }
            korisnik.setKursCollection(attachedKursCollection);
            em.persist(korisnik);
            if (rolaId != null) {
                rolaId.getKorisnikCollection().add(korisnik);
                rolaId = em.merge(rolaId);
            }
            for (Komentar komentarCollectionKomentar : korisnik.getKomentarCollection()) {
                Korisnik oldKorisnikIdOfKomentarCollectionKomentar = komentarCollectionKomentar.getKorisnikId();
                komentarCollectionKomentar.setKorisnikId(korisnik);
                komentarCollectionKomentar = em.merge(komentarCollectionKomentar);
                if (oldKorisnikIdOfKomentarCollectionKomentar != null) {
                    oldKorisnikIdOfKomentarCollectionKomentar.getKomentarCollection().remove(komentarCollectionKomentar);
                    oldKorisnikIdOfKomentarCollectionKomentar = em.merge(oldKorisnikIdOfKomentarCollectionKomentar);
                }
            }
            for (Ocena ocenaCollectionOcena : korisnik.getOcenaCollection()) {
                Korisnik oldKorisnikIdOfOcenaCollectionOcena = ocenaCollectionOcena.getKorisnikId();
                ocenaCollectionOcena.setKorisnikId(korisnik);
                ocenaCollectionOcena = em.merge(ocenaCollectionOcena);
                if (oldKorisnikIdOfOcenaCollectionOcena != null) {
                    oldKorisnikIdOfOcenaCollectionOcena.getOcenaCollection().remove(ocenaCollectionOcena);
                    oldKorisnikIdOfOcenaCollectionOcena = em.merge(oldKorisnikIdOfOcenaCollectionOcena);
                }
            }
            for (Evidencija evidencijaCollectionEvidencija : korisnik.getEvidencijaCollection()) {
                Korisnik oldKorisnikIdOfEvidencijaCollectionEvidencija = evidencijaCollectionEvidencija.getKorisnikId();
                evidencijaCollectionEvidencija.setKorisnikId(korisnik);
                evidencijaCollectionEvidencija = em.merge(evidencijaCollectionEvidencija);
                if (oldKorisnikIdOfEvidencijaCollectionEvidencija != null) {
                    oldKorisnikIdOfEvidencijaCollectionEvidencija.getEvidencijaCollection().remove(evidencijaCollectionEvidencija);
                    oldKorisnikIdOfEvidencijaCollectionEvidencija = em.merge(oldKorisnikIdOfEvidencijaCollectionEvidencija);
                }
            }
            for (Kurs kursCollectionKurs : korisnik.getKursCollection()) {
                Korisnik oldKorisnikIdOfKursCollectionKurs = kursCollectionKurs.getKorisnikId();
                kursCollectionKurs.setKorisnikId(korisnik);
                kursCollectionKurs = em.merge(kursCollectionKurs);
                if (oldKorisnikIdOfKursCollectionKurs != null) {
                    oldKorisnikIdOfKursCollectionKurs.getKursCollection().remove(kursCollectionKurs);
                    oldKorisnikIdOfKursCollectionKurs = em.merge(oldKorisnikIdOfKursCollectionKurs);
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

    public void edit(Korisnik korisnik) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Korisnik persistentKorisnik = em.find(Korisnik.class, korisnik.getKorisnikId());
            Rola rolaIdOld = persistentKorisnik.getRolaId();
            Rola rolaIdNew = korisnik.getRolaId();
            Collection<Komentar> komentarCollectionOld = persistentKorisnik.getKomentarCollection();
            Collection<Komentar> komentarCollectionNew = korisnik.getKomentarCollection();
            Collection<Ocena> ocenaCollectionOld = persistentKorisnik.getOcenaCollection();
            Collection<Ocena> ocenaCollectionNew = korisnik.getOcenaCollection();
            Collection<Evidencija> evidencijaCollectionOld = persistentKorisnik.getEvidencijaCollection();
            Collection<Evidencija> evidencijaCollectionNew = korisnik.getEvidencijaCollection();
            Collection<Kurs> kursCollectionOld = persistentKorisnik.getKursCollection();
            Collection<Kurs> kursCollectionNew = korisnik.getKursCollection();
            if (rolaIdNew != null) {
                rolaIdNew = em.getReference(rolaIdNew.getClass(), rolaIdNew.getRolaId());
                korisnik.setRolaId(rolaIdNew);
            }
            Collection<Komentar> attachedKomentarCollectionNew = new ArrayList<Komentar>();
            for (Komentar komentarCollectionNewKomentarToAttach : komentarCollectionNew) {
                komentarCollectionNewKomentarToAttach = em.getReference(komentarCollectionNewKomentarToAttach.getClass(), komentarCollectionNewKomentarToAttach.getKomentarId());
                attachedKomentarCollectionNew.add(komentarCollectionNewKomentarToAttach);
            }
            komentarCollectionNew = attachedKomentarCollectionNew;
            korisnik.setKomentarCollection(komentarCollectionNew);
            Collection<Ocena> attachedOcenaCollectionNew = new ArrayList<Ocena>();
            for (Ocena ocenaCollectionNewOcenaToAttach : ocenaCollectionNew) {
                ocenaCollectionNewOcenaToAttach = em.getReference(ocenaCollectionNewOcenaToAttach.getClass(), ocenaCollectionNewOcenaToAttach.getOcenaId());
                attachedOcenaCollectionNew.add(ocenaCollectionNewOcenaToAttach);
            }
            ocenaCollectionNew = attachedOcenaCollectionNew;
            korisnik.setOcenaCollection(ocenaCollectionNew);
            Collection<Evidencija> attachedEvidencijaCollectionNew = new ArrayList<Evidencija>();
            for (Evidencija evidencijaCollectionNewEvidencijaToAttach : evidencijaCollectionNew) {
                evidencijaCollectionNewEvidencijaToAttach = em.getReference(evidencijaCollectionNewEvidencijaToAttach.getClass(), evidencijaCollectionNewEvidencijaToAttach.getEvidancijaId());
                attachedEvidencijaCollectionNew.add(evidencijaCollectionNewEvidencijaToAttach);
            }
            evidencijaCollectionNew = attachedEvidencijaCollectionNew;
            korisnik.setEvidencijaCollection(evidencijaCollectionNew);
            Collection<Kurs> attachedKursCollectionNew = new ArrayList<Kurs>();
            for (Kurs kursCollectionNewKursToAttach : kursCollectionNew) {
                kursCollectionNewKursToAttach = em.getReference(kursCollectionNewKursToAttach.getClass(), kursCollectionNewKursToAttach.getKursId());
                attachedKursCollectionNew.add(kursCollectionNewKursToAttach);
            }
            kursCollectionNew = attachedKursCollectionNew;
            korisnik.setKursCollection(kursCollectionNew);
            korisnik = em.merge(korisnik);
            if (rolaIdOld != null && !rolaIdOld.equals(rolaIdNew)) {
                rolaIdOld.getKorisnikCollection().remove(korisnik);
                rolaIdOld = em.merge(rolaIdOld);
            }
            if (rolaIdNew != null && !rolaIdNew.equals(rolaIdOld)) {
                rolaIdNew.getKorisnikCollection().add(korisnik);
                rolaIdNew = em.merge(rolaIdNew);
            }
            for (Komentar komentarCollectionOldKomentar : komentarCollectionOld) {
                if (!komentarCollectionNew.contains(komentarCollectionOldKomentar)) {
                    komentarCollectionOldKomentar.setKorisnikId(null);
                    komentarCollectionOldKomentar = em.merge(komentarCollectionOldKomentar);
                }
            }
            for (Komentar komentarCollectionNewKomentar : komentarCollectionNew) {
                if (!komentarCollectionOld.contains(komentarCollectionNewKomentar)) {
                    Korisnik oldKorisnikIdOfKomentarCollectionNewKomentar = komentarCollectionNewKomentar.getKorisnikId();
                    komentarCollectionNewKomentar.setKorisnikId(korisnik);
                    komentarCollectionNewKomentar = em.merge(komentarCollectionNewKomentar);
                    if (oldKorisnikIdOfKomentarCollectionNewKomentar != null && !oldKorisnikIdOfKomentarCollectionNewKomentar.equals(korisnik)) {
                        oldKorisnikIdOfKomentarCollectionNewKomentar.getKomentarCollection().remove(komentarCollectionNewKomentar);
                        oldKorisnikIdOfKomentarCollectionNewKomentar = em.merge(oldKorisnikIdOfKomentarCollectionNewKomentar);
                    }
                }
            }
            for (Ocena ocenaCollectionOldOcena : ocenaCollectionOld) {
                if (!ocenaCollectionNew.contains(ocenaCollectionOldOcena)) {
                    ocenaCollectionOldOcena.setKorisnikId(null);
                    ocenaCollectionOldOcena = em.merge(ocenaCollectionOldOcena);
                }
            }
            for (Ocena ocenaCollectionNewOcena : ocenaCollectionNew) {
                if (!ocenaCollectionOld.contains(ocenaCollectionNewOcena)) {
                    Korisnik oldKorisnikIdOfOcenaCollectionNewOcena = ocenaCollectionNewOcena.getKorisnikId();
                    ocenaCollectionNewOcena.setKorisnikId(korisnik);
                    ocenaCollectionNewOcena = em.merge(ocenaCollectionNewOcena);
                    if (oldKorisnikIdOfOcenaCollectionNewOcena != null && !oldKorisnikIdOfOcenaCollectionNewOcena.equals(korisnik)) {
                        oldKorisnikIdOfOcenaCollectionNewOcena.getOcenaCollection().remove(ocenaCollectionNewOcena);
                        oldKorisnikIdOfOcenaCollectionNewOcena = em.merge(oldKorisnikIdOfOcenaCollectionNewOcena);
                    }
                }
            }
            for (Evidencija evidencijaCollectionOldEvidencija : evidencijaCollectionOld) {
                if (!evidencijaCollectionNew.contains(evidencijaCollectionOldEvidencija)) {
                    evidencijaCollectionOldEvidencija.setKorisnikId(null);
                    evidencijaCollectionOldEvidencija = em.merge(evidencijaCollectionOldEvidencija);
                }
            }
            for (Evidencija evidencijaCollectionNewEvidencija : evidencijaCollectionNew) {
                if (!evidencijaCollectionOld.contains(evidencijaCollectionNewEvidencija)) {
                    Korisnik oldKorisnikIdOfEvidencijaCollectionNewEvidencija = evidencijaCollectionNewEvidencija.getKorisnikId();
                    evidencijaCollectionNewEvidencija.setKorisnikId(korisnik);
                    evidencijaCollectionNewEvidencija = em.merge(evidencijaCollectionNewEvidencija);
                    if (oldKorisnikIdOfEvidencijaCollectionNewEvidencija != null && !oldKorisnikIdOfEvidencijaCollectionNewEvidencija.equals(korisnik)) {
                        oldKorisnikIdOfEvidencijaCollectionNewEvidencija.getEvidencijaCollection().remove(evidencijaCollectionNewEvidencija);
                        oldKorisnikIdOfEvidencijaCollectionNewEvidencija = em.merge(oldKorisnikIdOfEvidencijaCollectionNewEvidencija);
                    }
                }
            }
            for (Kurs kursCollectionOldKurs : kursCollectionOld) {
                if (!kursCollectionNew.contains(kursCollectionOldKurs)) {
                    kursCollectionOldKurs.setKorisnikId(null);
                    kursCollectionOldKurs = em.merge(kursCollectionOldKurs);
                }
            }
            for (Kurs kursCollectionNewKurs : kursCollectionNew) {
                if (!kursCollectionOld.contains(kursCollectionNewKurs)) {
                    Korisnik oldKorisnikIdOfKursCollectionNewKurs = kursCollectionNewKurs.getKorisnikId();
                    kursCollectionNewKurs.setKorisnikId(korisnik);
                    kursCollectionNewKurs = em.merge(kursCollectionNewKurs);
                    if (oldKorisnikIdOfKursCollectionNewKurs != null && !oldKorisnikIdOfKursCollectionNewKurs.equals(korisnik)) {
                        oldKorisnikIdOfKursCollectionNewKurs.getKursCollection().remove(kursCollectionNewKurs);
                        oldKorisnikIdOfKursCollectionNewKurs = em.merge(oldKorisnikIdOfKursCollectionNewKurs);
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
                Integer id = korisnik.getKorisnikId();
                if (findKorisnik(id) == null) {
                    throw new NonexistentEntityException("The korisnik with id " + id + " no longer exists.");
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
            Korisnik korisnik;
            try {
                korisnik = em.getReference(Korisnik.class, id);
                korisnik.getKorisnikId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The korisnik with id " + id + " no longer exists.", enfe);
            }
            Rola rolaId = korisnik.getRolaId();
            if (rolaId != null) {
                rolaId.getKorisnikCollection().remove(korisnik);
                rolaId = em.merge(rolaId);
            }
            Collection<Komentar> komentarCollection = korisnik.getKomentarCollection();
            for (Komentar komentarCollectionKomentar : komentarCollection) {
                komentarCollectionKomentar.setKorisnikId(null);
                komentarCollectionKomentar = em.merge(komentarCollectionKomentar);
            }
            Collection<Ocena> ocenaCollection = korisnik.getOcenaCollection();
            for (Ocena ocenaCollectionOcena : ocenaCollection) {
                ocenaCollectionOcena.setKorisnikId(null);
                ocenaCollectionOcena = em.merge(ocenaCollectionOcena);
            }
            Collection<Evidencija> evidencijaCollection = korisnik.getEvidencijaCollection();
            for (Evidencija evidencijaCollectionEvidencija : evidencijaCollection) {
                evidencijaCollectionEvidencija.setKorisnikId(null);
                evidencijaCollectionEvidencija = em.merge(evidencijaCollectionEvidencija);
            }
            Collection<Kurs> kursCollection = korisnik.getKursCollection();
            for (Kurs kursCollectionKurs : kursCollection) {
                kursCollectionKurs.setKorisnikId(null);
                kursCollectionKurs = em.merge(kursCollectionKurs);
            }
            em.remove(korisnik);
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

    public List<Korisnik> findKorisnikEntities() {
        return findKorisnikEntities(true, -1, -1);
    }

    public List<Korisnik> findKorisnikEntities(int maxResults, int firstResult) {
        return findKorisnikEntities(false, maxResults, firstResult);
    }

    private List<Korisnik> findKorisnikEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Korisnik.class));
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

    public Korisnik findKorisnik(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Korisnik.class, id);
        } finally {
            em.close();
        }
    }

    public int getKorisnikCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Korisnik> rt = cq.from(Korisnik.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
