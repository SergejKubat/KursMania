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
import com.kursmania.entities.Jezik;
import com.kursmania.entities.Kategorija;
import com.kursmania.entities.Korisnik;
import com.kursmania.entities.Komentar;
import java.util.ArrayList;
import java.util.Collection;
import com.kursmania.entities.Korpa;
import com.kursmania.entities.Ocena;
import com.kursmania.entities.Sekcija;
import com.kursmania.entities.KursTag;
import com.kursmania.entities.Evidencija;
import com.kursmania.entities.Kupon;
import com.kursmania.entities.Kurs;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Andrej Kubat
 */
public class KursJpaController implements Serializable {

    public KursJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Kurs kurs) throws RollbackFailureException, Exception {
        if (kurs.getKomentarCollection() == null) {
            kurs.setKomentarCollection(new ArrayList<Komentar>());
        }
        if (kurs.getKorpaCollection() == null) {
            kurs.setKorpaCollection(new ArrayList<Korpa>());
        }
        if (kurs.getOcenaCollection() == null) {
            kurs.setOcenaCollection(new ArrayList<Ocena>());
        }
        if (kurs.getSekcijaCollection() == null) {
            kurs.setSekcijaCollection(new ArrayList<Sekcija>());
        }
        if (kurs.getKursTagCollection() == null) {
            kurs.setKursTagCollection(new ArrayList<KursTag>());
        }
        if (kurs.getEvidencijaCollection() == null) {
            kurs.setEvidencijaCollection(new ArrayList<Evidencija>());
        }
        if (kurs.getKuponCollection() == null) {
            kurs.setKuponCollection(new ArrayList<Kupon>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Jezik jezikId = kurs.getJezikId();
            if (jezikId != null) {
                jezikId = em.getReference(jezikId.getClass(), jezikId.getJezikId());
                kurs.setJezikId(jezikId);
            }
            Kategorija kategorijaId = kurs.getKategorijaId();
            if (kategorijaId != null) {
                kategorijaId = em.getReference(kategorijaId.getClass(), kategorijaId.getKategorijaId());
                kurs.setKategorijaId(kategorijaId);
            }
            Korisnik korisnikId = kurs.getKorisnikId();
            if (korisnikId != null) {
                korisnikId = em.getReference(korisnikId.getClass(), korisnikId.getKorisnikId());
                kurs.setKorisnikId(korisnikId);
            }
            Collection<Komentar> attachedKomentarCollection = new ArrayList<Komentar>();
            for (Komentar komentarCollectionKomentarToAttach : kurs.getKomentarCollection()) {
                komentarCollectionKomentarToAttach = em.getReference(komentarCollectionKomentarToAttach.getClass(), komentarCollectionKomentarToAttach.getKomentarId());
                attachedKomentarCollection.add(komentarCollectionKomentarToAttach);
            }
            kurs.setKomentarCollection(attachedKomentarCollection);
            Collection<Korpa> attachedKorpaCollection = new ArrayList<Korpa>();
            for (Korpa korpaCollectionKorpaToAttach : kurs.getKorpaCollection()) {
                korpaCollectionKorpaToAttach = em.getReference(korpaCollectionKorpaToAttach.getClass(), korpaCollectionKorpaToAttach.getKorpaId());
                attachedKorpaCollection.add(korpaCollectionKorpaToAttach);
            }
            kurs.setKorpaCollection(attachedKorpaCollection);
            Collection<Ocena> attachedOcenaCollection = new ArrayList<Ocena>();
            for (Ocena ocenaCollectionOcenaToAttach : kurs.getOcenaCollection()) {
                ocenaCollectionOcenaToAttach = em.getReference(ocenaCollectionOcenaToAttach.getClass(), ocenaCollectionOcenaToAttach.getOcenaId());
                attachedOcenaCollection.add(ocenaCollectionOcenaToAttach);
            }
            kurs.setOcenaCollection(attachedOcenaCollection);
            Collection<Sekcija> attachedSekcijaCollection = new ArrayList<Sekcija>();
            for (Sekcija sekcijaCollectionSekcijaToAttach : kurs.getSekcijaCollection()) {
                sekcijaCollectionSekcijaToAttach = em.getReference(sekcijaCollectionSekcijaToAttach.getClass(), sekcijaCollectionSekcijaToAttach.getSekcijaId());
                attachedSekcijaCollection.add(sekcijaCollectionSekcijaToAttach);
            }
            kurs.setSekcijaCollection(attachedSekcijaCollection);
            Collection<KursTag> attachedKursTagCollection = new ArrayList<KursTag>();
            for (KursTag kursTagCollectionKursTagToAttach : kurs.getKursTagCollection()) {
                kursTagCollectionKursTagToAttach = em.getReference(kursTagCollectionKursTagToAttach.getClass(), kursTagCollectionKursTagToAttach.getKtId());
                attachedKursTagCollection.add(kursTagCollectionKursTagToAttach);
            }
            kurs.setKursTagCollection(attachedKursTagCollection);
            Collection<Evidencija> attachedEvidencijaCollection = new ArrayList<Evidencija>();
            for (Evidencija evidencijaCollectionEvidencijaToAttach : kurs.getEvidencijaCollection()) {
                evidencijaCollectionEvidencijaToAttach = em.getReference(evidencijaCollectionEvidencijaToAttach.getClass(), evidencijaCollectionEvidencijaToAttach.getEvidencijaId());
                attachedEvidencijaCollection.add(evidencijaCollectionEvidencijaToAttach);
            }
            kurs.setEvidencijaCollection(attachedEvidencijaCollection);
            Collection<Kupon> attachedKuponCollection = new ArrayList<Kupon>();
            for (Kupon kuponCollectionKuponToAttach : kurs.getKuponCollection()) {
                kuponCollectionKuponToAttach = em.getReference(kuponCollectionKuponToAttach.getClass(), kuponCollectionKuponToAttach.getKuponId());
                attachedKuponCollection.add(kuponCollectionKuponToAttach);
            }
            kurs.setKuponCollection(attachedKuponCollection);
            em.persist(kurs);
            if (jezikId != null) {
                jezikId.getKursCollection().add(kurs);
                jezikId = em.merge(jezikId);
            }
            if (kategorijaId != null) {
                kategorijaId.getKursCollection().add(kurs);
                kategorijaId = em.merge(kategorijaId);
            }
            if (korisnikId != null) {
                korisnikId.getKursCollection().add(kurs);
                korisnikId = em.merge(korisnikId);
            }
            for (Komentar komentarCollectionKomentar : kurs.getKomentarCollection()) {
                Kurs oldKursIdOfKomentarCollectionKomentar = komentarCollectionKomentar.getKursId();
                komentarCollectionKomentar.setKursId(kurs);
                komentarCollectionKomentar = em.merge(komentarCollectionKomentar);
                if (oldKursIdOfKomentarCollectionKomentar != null) {
                    oldKursIdOfKomentarCollectionKomentar.getKomentarCollection().remove(komentarCollectionKomentar);
                    oldKursIdOfKomentarCollectionKomentar = em.merge(oldKursIdOfKomentarCollectionKomentar);
                }
            }
            for (Korpa korpaCollectionKorpa : kurs.getKorpaCollection()) {
                Kurs oldKursIdOfKorpaCollectionKorpa = korpaCollectionKorpa.getKursId();
                korpaCollectionKorpa.setKursId(kurs);
                korpaCollectionKorpa = em.merge(korpaCollectionKorpa);
                if (oldKursIdOfKorpaCollectionKorpa != null) {
                    oldKursIdOfKorpaCollectionKorpa.getKorpaCollection().remove(korpaCollectionKorpa);
                    oldKursIdOfKorpaCollectionKorpa = em.merge(oldKursIdOfKorpaCollectionKorpa);
                }
            }
            for (Ocena ocenaCollectionOcena : kurs.getOcenaCollection()) {
                Kurs oldKursIdOfOcenaCollectionOcena = ocenaCollectionOcena.getKursId();
                ocenaCollectionOcena.setKursId(kurs);
                ocenaCollectionOcena = em.merge(ocenaCollectionOcena);
                if (oldKursIdOfOcenaCollectionOcena != null) {
                    oldKursIdOfOcenaCollectionOcena.getOcenaCollection().remove(ocenaCollectionOcena);
                    oldKursIdOfOcenaCollectionOcena = em.merge(oldKursIdOfOcenaCollectionOcena);
                }
            }
            for (Sekcija sekcijaCollectionSekcija : kurs.getSekcijaCollection()) {
                Kurs oldKursIdOfSekcijaCollectionSekcija = sekcijaCollectionSekcija.getKursId();
                sekcijaCollectionSekcija.setKursId(kurs);
                sekcijaCollectionSekcija = em.merge(sekcijaCollectionSekcija);
                if (oldKursIdOfSekcijaCollectionSekcija != null) {
                    oldKursIdOfSekcijaCollectionSekcija.getSekcijaCollection().remove(sekcijaCollectionSekcija);
                    oldKursIdOfSekcijaCollectionSekcija = em.merge(oldKursIdOfSekcijaCollectionSekcija);
                }
            }
            for (KursTag kursTagCollectionKursTag : kurs.getKursTagCollection()) {
                Kurs oldKursIdOfKursTagCollectionKursTag = kursTagCollectionKursTag.getKursId();
                kursTagCollectionKursTag.setKursId(kurs);
                kursTagCollectionKursTag = em.merge(kursTagCollectionKursTag);
                if (oldKursIdOfKursTagCollectionKursTag != null) {
                    oldKursIdOfKursTagCollectionKursTag.getKursTagCollection().remove(kursTagCollectionKursTag);
                    oldKursIdOfKursTagCollectionKursTag = em.merge(oldKursIdOfKursTagCollectionKursTag);
                }
            }
            for (Evidencija evidencijaCollectionEvidencija : kurs.getEvidencijaCollection()) {
                Kurs oldKursIdOfEvidencijaCollectionEvidencija = evidencijaCollectionEvidencija.getKursId();
                evidencijaCollectionEvidencija.setKursId(kurs);
                evidencijaCollectionEvidencija = em.merge(evidencijaCollectionEvidencija);
                if (oldKursIdOfEvidencijaCollectionEvidencija != null) {
                    oldKursIdOfEvidencijaCollectionEvidencija.getEvidencijaCollection().remove(evidencijaCollectionEvidencija);
                    oldKursIdOfEvidencijaCollectionEvidencija = em.merge(oldKursIdOfEvidencijaCollectionEvidencija);
                }
            }
            for (Kupon kuponCollectionKupon : kurs.getKuponCollection()) {
                Kurs oldKursIdOfKuponCollectionKupon = kuponCollectionKupon.getKursId();
                kuponCollectionKupon.setKursId(kurs);
                kuponCollectionKupon = em.merge(kuponCollectionKupon);
                if (oldKursIdOfKuponCollectionKupon != null) {
                    oldKursIdOfKuponCollectionKupon.getKuponCollection().remove(kuponCollectionKupon);
                    oldKursIdOfKuponCollectionKupon = em.merge(oldKursIdOfKuponCollectionKupon);
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

    public void edit(Kurs kurs) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Kurs persistentKurs = em.find(Kurs.class, kurs.getKursId());
            Jezik jezikIdOld = persistentKurs.getJezikId();
            Jezik jezikIdNew = kurs.getJezikId();
            Kategorija kategorijaIdOld = persistentKurs.getKategorijaId();
            Kategorija kategorijaIdNew = kurs.getKategorijaId();
            Korisnik korisnikIdOld = persistentKurs.getKorisnikId();
            Korisnik korisnikIdNew = kurs.getKorisnikId();
            Collection<Komentar> komentarCollectionOld = persistentKurs.getKomentarCollection();
            Collection<Komentar> komentarCollectionNew = kurs.getKomentarCollection();
            Collection<Korpa> korpaCollectionOld = persistentKurs.getKorpaCollection();
            Collection<Korpa> korpaCollectionNew = kurs.getKorpaCollection();
            Collection<Ocena> ocenaCollectionOld = persistentKurs.getOcenaCollection();
            Collection<Ocena> ocenaCollectionNew = kurs.getOcenaCollection();
            Collection<Sekcija> sekcijaCollectionOld = persistentKurs.getSekcijaCollection();
            Collection<Sekcija> sekcijaCollectionNew = kurs.getSekcijaCollection();
            Collection<KursTag> kursTagCollectionOld = persistentKurs.getKursTagCollection();
            Collection<KursTag> kursTagCollectionNew = kurs.getKursTagCollection();
            Collection<Evidencija> evidencijaCollectionOld = persistentKurs.getEvidencijaCollection();
            Collection<Evidencija> evidencijaCollectionNew = kurs.getEvidencijaCollection();
            Collection<Kupon> kuponCollectionOld = persistentKurs.getKuponCollection();
            Collection<Kupon> kuponCollectionNew = kurs.getKuponCollection();
            if (jezikIdNew != null) {
                jezikIdNew = em.getReference(jezikIdNew.getClass(), jezikIdNew.getJezikId());
                kurs.setJezikId(jezikIdNew);
            }
            if (kategorijaIdNew != null) {
                kategorijaIdNew = em.getReference(kategorijaIdNew.getClass(), kategorijaIdNew.getKategorijaId());
                kurs.setKategorijaId(kategorijaIdNew);
            }
            if (korisnikIdNew != null) {
                korisnikIdNew = em.getReference(korisnikIdNew.getClass(), korisnikIdNew.getKorisnikId());
                kurs.setKorisnikId(korisnikIdNew);
            }
            Collection<Komentar> attachedKomentarCollectionNew = new ArrayList<Komentar>();
            for (Komentar komentarCollectionNewKomentarToAttach : komentarCollectionNew) {
                komentarCollectionNewKomentarToAttach = em.getReference(komentarCollectionNewKomentarToAttach.getClass(), komentarCollectionNewKomentarToAttach.getKomentarId());
                attachedKomentarCollectionNew.add(komentarCollectionNewKomentarToAttach);
            }
            komentarCollectionNew = attachedKomentarCollectionNew;
            kurs.setKomentarCollection(komentarCollectionNew);
            Collection<Korpa> attachedKorpaCollectionNew = new ArrayList<Korpa>();
            for (Korpa korpaCollectionNewKorpaToAttach : korpaCollectionNew) {
                korpaCollectionNewKorpaToAttach = em.getReference(korpaCollectionNewKorpaToAttach.getClass(), korpaCollectionNewKorpaToAttach.getKorpaId());
                attachedKorpaCollectionNew.add(korpaCollectionNewKorpaToAttach);
            }
            korpaCollectionNew = attachedKorpaCollectionNew;
            kurs.setKorpaCollection(korpaCollectionNew);
            Collection<Ocena> attachedOcenaCollectionNew = new ArrayList<Ocena>();
            for (Ocena ocenaCollectionNewOcenaToAttach : ocenaCollectionNew) {
                ocenaCollectionNewOcenaToAttach = em.getReference(ocenaCollectionNewOcenaToAttach.getClass(), ocenaCollectionNewOcenaToAttach.getOcenaId());
                attachedOcenaCollectionNew.add(ocenaCollectionNewOcenaToAttach);
            }
            ocenaCollectionNew = attachedOcenaCollectionNew;
            kurs.setOcenaCollection(ocenaCollectionNew);
            Collection<Sekcija> attachedSekcijaCollectionNew = new ArrayList<Sekcija>();
            for (Sekcija sekcijaCollectionNewSekcijaToAttach : sekcijaCollectionNew) {
                sekcijaCollectionNewSekcijaToAttach = em.getReference(sekcijaCollectionNewSekcijaToAttach.getClass(), sekcijaCollectionNewSekcijaToAttach.getSekcijaId());
                attachedSekcijaCollectionNew.add(sekcijaCollectionNewSekcijaToAttach);
            }
            sekcijaCollectionNew = attachedSekcijaCollectionNew;
            kurs.setSekcijaCollection(sekcijaCollectionNew);
            Collection<KursTag> attachedKursTagCollectionNew = new ArrayList<KursTag>();
            for (KursTag kursTagCollectionNewKursTagToAttach : kursTagCollectionNew) {
                kursTagCollectionNewKursTagToAttach = em.getReference(kursTagCollectionNewKursTagToAttach.getClass(), kursTagCollectionNewKursTagToAttach.getKtId());
                attachedKursTagCollectionNew.add(kursTagCollectionNewKursTagToAttach);
            }
            kursTagCollectionNew = attachedKursTagCollectionNew;
            kurs.setKursTagCollection(kursTagCollectionNew);
            Collection<Evidencija> attachedEvidencijaCollectionNew = new ArrayList<Evidencija>();
            for (Evidencija evidencijaCollectionNewEvidencijaToAttach : evidencijaCollectionNew) {
                evidencijaCollectionNewEvidencijaToAttach = em.getReference(evidencijaCollectionNewEvidencijaToAttach.getClass(), evidencijaCollectionNewEvidencijaToAttach.getEvidencijaId());
                attachedEvidencijaCollectionNew.add(evidencijaCollectionNewEvidencijaToAttach);
            }
            evidencijaCollectionNew = attachedEvidencijaCollectionNew;
            kurs.setEvidencijaCollection(evidencijaCollectionNew);
            Collection<Kupon> attachedKuponCollectionNew = new ArrayList<Kupon>();
            for (Kupon kuponCollectionNewKuponToAttach : kuponCollectionNew) {
                kuponCollectionNewKuponToAttach = em.getReference(kuponCollectionNewKuponToAttach.getClass(), kuponCollectionNewKuponToAttach.getKuponId());
                attachedKuponCollectionNew.add(kuponCollectionNewKuponToAttach);
            }
            kuponCollectionNew = attachedKuponCollectionNew;
            kurs.setKuponCollection(kuponCollectionNew);
            kurs = em.merge(kurs);
            if (jezikIdOld != null && !jezikIdOld.equals(jezikIdNew)) {
                jezikIdOld.getKursCollection().remove(kurs);
                jezikIdOld = em.merge(jezikIdOld);
            }
            if (jezikIdNew != null && !jezikIdNew.equals(jezikIdOld)) {
                jezikIdNew.getKursCollection().add(kurs);
                jezikIdNew = em.merge(jezikIdNew);
            }
            if (kategorijaIdOld != null && !kategorijaIdOld.equals(kategorijaIdNew)) {
                kategorijaIdOld.getKursCollection().remove(kurs);
                kategorijaIdOld = em.merge(kategorijaIdOld);
            }
            if (kategorijaIdNew != null && !kategorijaIdNew.equals(kategorijaIdOld)) {
                kategorijaIdNew.getKursCollection().add(kurs);
                kategorijaIdNew = em.merge(kategorijaIdNew);
            }
            if (korisnikIdOld != null && !korisnikIdOld.equals(korisnikIdNew)) {
                korisnikIdOld.getKursCollection().remove(kurs);
                korisnikIdOld = em.merge(korisnikIdOld);
            }
            if (korisnikIdNew != null && !korisnikIdNew.equals(korisnikIdOld)) {
                korisnikIdNew.getKursCollection().add(kurs);
                korisnikIdNew = em.merge(korisnikIdNew);
            }
            for (Komentar komentarCollectionOldKomentar : komentarCollectionOld) {
                if (!komentarCollectionNew.contains(komentarCollectionOldKomentar)) {
                    komentarCollectionOldKomentar.setKursId(null);
                    komentarCollectionOldKomentar = em.merge(komentarCollectionOldKomentar);
                }
            }
            for (Komentar komentarCollectionNewKomentar : komentarCollectionNew) {
                if (!komentarCollectionOld.contains(komentarCollectionNewKomentar)) {
                    Kurs oldKursIdOfKomentarCollectionNewKomentar = komentarCollectionNewKomentar.getKursId();
                    komentarCollectionNewKomentar.setKursId(kurs);
                    komentarCollectionNewKomentar = em.merge(komentarCollectionNewKomentar);
                    if (oldKursIdOfKomentarCollectionNewKomentar != null && !oldKursIdOfKomentarCollectionNewKomentar.equals(kurs)) {
                        oldKursIdOfKomentarCollectionNewKomentar.getKomentarCollection().remove(komentarCollectionNewKomentar);
                        oldKursIdOfKomentarCollectionNewKomentar = em.merge(oldKursIdOfKomentarCollectionNewKomentar);
                    }
                }
            }
            for (Korpa korpaCollectionOldKorpa : korpaCollectionOld) {
                if (!korpaCollectionNew.contains(korpaCollectionOldKorpa)) {
                    korpaCollectionOldKorpa.setKursId(null);
                    korpaCollectionOldKorpa = em.merge(korpaCollectionOldKorpa);
                }
            }
            for (Korpa korpaCollectionNewKorpa : korpaCollectionNew) {
                if (!korpaCollectionOld.contains(korpaCollectionNewKorpa)) {
                    Kurs oldKursIdOfKorpaCollectionNewKorpa = korpaCollectionNewKorpa.getKursId();
                    korpaCollectionNewKorpa.setKursId(kurs);
                    korpaCollectionNewKorpa = em.merge(korpaCollectionNewKorpa);
                    if (oldKursIdOfKorpaCollectionNewKorpa != null && !oldKursIdOfKorpaCollectionNewKorpa.equals(kurs)) {
                        oldKursIdOfKorpaCollectionNewKorpa.getKorpaCollection().remove(korpaCollectionNewKorpa);
                        oldKursIdOfKorpaCollectionNewKorpa = em.merge(oldKursIdOfKorpaCollectionNewKorpa);
                    }
                }
            }
            for (Ocena ocenaCollectionOldOcena : ocenaCollectionOld) {
                if (!ocenaCollectionNew.contains(ocenaCollectionOldOcena)) {
                    ocenaCollectionOldOcena.setKursId(null);
                    ocenaCollectionOldOcena = em.merge(ocenaCollectionOldOcena);
                }
            }
            for (Ocena ocenaCollectionNewOcena : ocenaCollectionNew) {
                if (!ocenaCollectionOld.contains(ocenaCollectionNewOcena)) {
                    Kurs oldKursIdOfOcenaCollectionNewOcena = ocenaCollectionNewOcena.getKursId();
                    ocenaCollectionNewOcena.setKursId(kurs);
                    ocenaCollectionNewOcena = em.merge(ocenaCollectionNewOcena);
                    if (oldKursIdOfOcenaCollectionNewOcena != null && !oldKursIdOfOcenaCollectionNewOcena.equals(kurs)) {
                        oldKursIdOfOcenaCollectionNewOcena.getOcenaCollection().remove(ocenaCollectionNewOcena);
                        oldKursIdOfOcenaCollectionNewOcena = em.merge(oldKursIdOfOcenaCollectionNewOcena);
                    }
                }
            }
            for (Sekcija sekcijaCollectionOldSekcija : sekcijaCollectionOld) {
                if (!sekcijaCollectionNew.contains(sekcijaCollectionOldSekcija)) {
                    sekcijaCollectionOldSekcija.setKursId(null);
                    sekcijaCollectionOldSekcija = em.merge(sekcijaCollectionOldSekcija);
                }
            }
            for (Sekcija sekcijaCollectionNewSekcija : sekcijaCollectionNew) {
                if (!sekcijaCollectionOld.contains(sekcijaCollectionNewSekcija)) {
                    Kurs oldKursIdOfSekcijaCollectionNewSekcija = sekcijaCollectionNewSekcija.getKursId();
                    sekcijaCollectionNewSekcija.setKursId(kurs);
                    sekcijaCollectionNewSekcija = em.merge(sekcijaCollectionNewSekcija);
                    if (oldKursIdOfSekcijaCollectionNewSekcija != null && !oldKursIdOfSekcijaCollectionNewSekcija.equals(kurs)) {
                        oldKursIdOfSekcijaCollectionNewSekcija.getSekcijaCollection().remove(sekcijaCollectionNewSekcija);
                        oldKursIdOfSekcijaCollectionNewSekcija = em.merge(oldKursIdOfSekcijaCollectionNewSekcija);
                    }
                }
            }
            for (KursTag kursTagCollectionOldKursTag : kursTagCollectionOld) {
                if (!kursTagCollectionNew.contains(kursTagCollectionOldKursTag)) {
                    kursTagCollectionOldKursTag.setKursId(null);
                    kursTagCollectionOldKursTag = em.merge(kursTagCollectionOldKursTag);
                }
            }
            for (KursTag kursTagCollectionNewKursTag : kursTagCollectionNew) {
                if (!kursTagCollectionOld.contains(kursTagCollectionNewKursTag)) {
                    Kurs oldKursIdOfKursTagCollectionNewKursTag = kursTagCollectionNewKursTag.getKursId();
                    kursTagCollectionNewKursTag.setKursId(kurs);
                    kursTagCollectionNewKursTag = em.merge(kursTagCollectionNewKursTag);
                    if (oldKursIdOfKursTagCollectionNewKursTag != null && !oldKursIdOfKursTagCollectionNewKursTag.equals(kurs)) {
                        oldKursIdOfKursTagCollectionNewKursTag.getKursTagCollection().remove(kursTagCollectionNewKursTag);
                        oldKursIdOfKursTagCollectionNewKursTag = em.merge(oldKursIdOfKursTagCollectionNewKursTag);
                    }
                }
            }
            for (Evidencija evidencijaCollectionOldEvidencija : evidencijaCollectionOld) {
                if (!evidencijaCollectionNew.contains(evidencijaCollectionOldEvidencija)) {
                    evidencijaCollectionOldEvidencija.setKursId(null);
                    evidencijaCollectionOldEvidencija = em.merge(evidencijaCollectionOldEvidencija);
                }
            }
            for (Evidencija evidencijaCollectionNewEvidencija : evidencijaCollectionNew) {
                if (!evidencijaCollectionOld.contains(evidencijaCollectionNewEvidencija)) {
                    Kurs oldKursIdOfEvidencijaCollectionNewEvidencija = evidencijaCollectionNewEvidencija.getKursId();
                    evidencijaCollectionNewEvidencija.setKursId(kurs);
                    evidencijaCollectionNewEvidencija = em.merge(evidencijaCollectionNewEvidencija);
                    if (oldKursIdOfEvidencijaCollectionNewEvidencija != null && !oldKursIdOfEvidencijaCollectionNewEvidencija.equals(kurs)) {
                        oldKursIdOfEvidencijaCollectionNewEvidencija.getEvidencijaCollection().remove(evidencijaCollectionNewEvidencija);
                        oldKursIdOfEvidencijaCollectionNewEvidencija = em.merge(oldKursIdOfEvidencijaCollectionNewEvidencija);
                    }
                }
            }
            for (Kupon kuponCollectionOldKupon : kuponCollectionOld) {
                if (!kuponCollectionNew.contains(kuponCollectionOldKupon)) {
                    kuponCollectionOldKupon.setKursId(null);
                    kuponCollectionOldKupon = em.merge(kuponCollectionOldKupon);
                }
            }
            for (Kupon kuponCollectionNewKupon : kuponCollectionNew) {
                if (!kuponCollectionOld.contains(kuponCollectionNewKupon)) {
                    Kurs oldKursIdOfKuponCollectionNewKupon = kuponCollectionNewKupon.getKursId();
                    kuponCollectionNewKupon.setKursId(kurs);
                    kuponCollectionNewKupon = em.merge(kuponCollectionNewKupon);
                    if (oldKursIdOfKuponCollectionNewKupon != null && !oldKursIdOfKuponCollectionNewKupon.equals(kurs)) {
                        oldKursIdOfKuponCollectionNewKupon.getKuponCollection().remove(kuponCollectionNewKupon);
                        oldKursIdOfKuponCollectionNewKupon = em.merge(oldKursIdOfKuponCollectionNewKupon);
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
                Integer id = kurs.getKursId();
                if (findKurs(id) == null) {
                    throw new NonexistentEntityException("The kurs with id " + id + " no longer exists.");
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
            Kurs kurs;
            try {
                kurs = em.getReference(Kurs.class, id);
                kurs.getKursId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The kurs with id " + id + " no longer exists.", enfe);
            }
            Jezik jezikId = kurs.getJezikId();
            if (jezikId != null) {
                jezikId.getKursCollection().remove(kurs);
                jezikId = em.merge(jezikId);
            }
            Kategorija kategorijaId = kurs.getKategorijaId();
            if (kategorijaId != null) {
                kategorijaId.getKursCollection().remove(kurs);
                kategorijaId = em.merge(kategorijaId);
            }
            Korisnik korisnikId = kurs.getKorisnikId();
            if (korisnikId != null) {
                korisnikId.getKursCollection().remove(kurs);
                korisnikId = em.merge(korisnikId);
            }
            Collection<Komentar> komentarCollection = kurs.getKomentarCollection();
            for (Komentar komentarCollectionKomentar : komentarCollection) {
                komentarCollectionKomentar.setKursId(null);
                komentarCollectionKomentar = em.merge(komentarCollectionKomentar);
            }
            Collection<Korpa> korpaCollection = kurs.getKorpaCollection();
            for (Korpa korpaCollectionKorpa : korpaCollection) {
                korpaCollectionKorpa.setKursId(null);
                korpaCollectionKorpa = em.merge(korpaCollectionKorpa);
            }
            Collection<Ocena> ocenaCollection = kurs.getOcenaCollection();
            for (Ocena ocenaCollectionOcena : ocenaCollection) {
                ocenaCollectionOcena.setKursId(null);
                ocenaCollectionOcena = em.merge(ocenaCollectionOcena);
            }
            Collection<Sekcija> sekcijaCollection = kurs.getSekcijaCollection();
            for (Sekcija sekcijaCollectionSekcija : sekcijaCollection) {
                sekcijaCollectionSekcija.setKursId(null);
                sekcijaCollectionSekcija = em.merge(sekcijaCollectionSekcija);
            }
            Collection<KursTag> kursTagCollection = kurs.getKursTagCollection();
            for (KursTag kursTagCollectionKursTag : kursTagCollection) {
                kursTagCollectionKursTag.setKursId(null);
                kursTagCollectionKursTag = em.merge(kursTagCollectionKursTag);
            }
            Collection<Evidencija> evidencijaCollection = kurs.getEvidencijaCollection();
            for (Evidencija evidencijaCollectionEvidencija : evidencijaCollection) {
                evidencijaCollectionEvidencija.setKursId(null);
                evidencijaCollectionEvidencija = em.merge(evidencijaCollectionEvidencija);
            }
            Collection<Kupon> kuponCollection = kurs.getKuponCollection();
            for (Kupon kuponCollectionKupon : kuponCollection) {
                kuponCollectionKupon.setKursId(null);
                kuponCollectionKupon = em.merge(kuponCollectionKupon);
            }
            em.remove(kurs);
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

    public List<Kurs> findKursEntities() {
        return findKursEntities(true, -1, -1);
    }

    public List<Kurs> findKursEntities(int maxResults, int firstResult) {
        return findKursEntities(false, maxResults, firstResult);
    }

    private List<Kurs> findKursEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Kurs.class));
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

    public Kurs findKurs(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Kurs.class, id);
        } finally {
            em.close();
        }
    }

    public int getKursCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Kurs> rt = cq.from(Kurs.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
