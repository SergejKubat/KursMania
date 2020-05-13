/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kursmania.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andrej Kubat
 */
@Entity
@Table(name = "kurs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kurs.findAll", query = "SELECT k FROM Kurs k")
    , @NamedQuery(name = "Kurs.findByKursId", query = "SELECT k FROM Kurs k WHERE k.kursId = :kursId")
    , @NamedQuery(name = "Kurs.findByKursIme", query = "SELECT k FROM Kurs k WHERE k.kursIme = :kursIme")
    , @NamedQuery(name = "Kurs.findByKursDatumObjavljivanja", query = "SELECT k FROM Kurs k WHERE k.kursDatumObjavljivanja = :kursDatumObjavljivanja")
    , @NamedQuery(name = "Kurs.findByDatumPoslednjePromene", query = "SELECT k FROM Kurs k WHERE k.datumPoslednjePromene = :datumPoslednjePromene")
    , @NamedQuery(name = "Kurs.findByKursCena", query = "SELECT k FROM Kurs k WHERE k.kursCena = :kursCena")
    , @NamedQuery(name = "Kurs.findByKursPregledi", query = "SELECT k FROM Kurs k WHERE k.kursPregledi = :kursPregledi")})
public class Kurs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "KURS_ID")
    private Integer kursId;
    @Size(max = 100)
    @Column(name = "KURS_IME")
    private String kursIme;
    @Lob
    @Size(max = 16777215)
    @Column(name = "KURS_OPIS")
    private String kursOpis;
    @Lob
    @Size(max = 16777215)
    @Column(name = "KURS_ZAHTEVI")
    private String kursZahtevi;
    @Column(name = "KURS_DATUM_OBJAVLJIVANJA")
    @Temporal(TemporalType.DATE)
    private Date kursDatumObjavljivanja;
    @Column(name = "DATUM_POSLEDNJE_PROMENE")
    @Temporal(TemporalType.DATE)
    private Date datumPoslednjePromene;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "KURS_CENA")
    private Float kursCena;
    @Lob
    @Size(max = 16777215)
    @Column(name = "KURS_SLIKA")
    private String kursSlika;
    @Lob
    @Size(max = 16777215)
    @Column(name = "KURS_VIDEO")
    private String kursVideo;
    @Column(name = "KURS_PREGLEDI")
    private Integer kursPregledi;
    @OneToMany(mappedBy = "kursId")
    private Collection<Komentar> komentarCollection;
    @OneToMany(mappedBy = "kursId")
    private Collection<Korpa> korpaCollection;
    @OneToMany(mappedBy = "kursId")
    private Collection<Ocena> ocenaCollection;
    @OneToMany(mappedBy = "kursId")
    private Collection<Sekcija> sekcijaCollection;
    @OneToMany(mappedBy = "kursId")
    private Collection<KursTag> kursTagCollection;
    @OneToMany(mappedBy = "kursId")
    private Collection<Evidencija> evidencijaCollection;
    @JoinColumn(name = "JEZIK_ID", referencedColumnName = "JEZIK_ID")
    @ManyToOne
    private Jezik jezikId;
    @JoinColumn(name = "KATEGORIJA_ID", referencedColumnName = "KATEGORIJA_ID")
    @ManyToOne
    private Kategorija kategorijaId;
    @JoinColumn(name = "KORISNIK_ID", referencedColumnName = "KORISNIK_ID")
    @ManyToOne
    private Korisnik korisnikId;
    @OneToMany(mappedBy = "kursId")
    private Collection<Kupon> kuponCollection;

    public Kurs() {
    }

    public Kurs(Integer kursId) {
        this.kursId = kursId;
    }

    public Integer getKursId() {
        return kursId;
    }

    public void setKursId(Integer kursId) {
        this.kursId = kursId;
    }

    public String getKursIme() {
        return kursIme;
    }

    public void setKursIme(String kursIme) {
        this.kursIme = kursIme;
    }

    public String getKursOpis() {
        return kursOpis;
    }

    public void setKursOpis(String kursOpis) {
        this.kursOpis = kursOpis;
    }

    public String getKursZahtevi() {
        return kursZahtevi;
    }

    public void setKursZahtevi(String kursZahtevi) {
        this.kursZahtevi = kursZahtevi;
    }

    public Date getKursDatumObjavljivanja() {
        return kursDatumObjavljivanja;
    }

    public void setKursDatumObjavljivanja(Date kursDatumObjavljivanja) {
        this.kursDatumObjavljivanja = kursDatumObjavljivanja;
    }

    public Date getDatumPoslednjePromene() {
        return datumPoslednjePromene;
    }

    public void setDatumPoslednjePromene(Date datumPoslednjePromene) {
        this.datumPoslednjePromene = datumPoslednjePromene;
    }

    public Float getKursCena() {
        return kursCena;
    }

    public void setKursCena(Float kursCena) {
        this.kursCena = kursCena;
    }

    public String getKursSlika() {
        return kursSlika;
    }

    public void setKursSlika(String kursSlika) {
        this.kursSlika = kursSlika;
    }

    public String getKursVideo() {
        return kursVideo;
    }

    public void setKursVideo(String kursVideo) {
        this.kursVideo = kursVideo;
    }

    public Integer getKursPregledi() {
        return kursPregledi;
    }

    public void setKursPregledi(Integer kursPregledi) {
        this.kursPregledi = kursPregledi;
    }

    @XmlTransient
    public Collection<Komentar> getKomentarCollection() {
        return komentarCollection;
    }

    public void setKomentarCollection(Collection<Komentar> komentarCollection) {
        this.komentarCollection = komentarCollection;
    }

    @XmlTransient
    public Collection<Korpa> getKorpaCollection() {
        return korpaCollection;
    }

    public void setKorpaCollection(Collection<Korpa> korpaCollection) {
        this.korpaCollection = korpaCollection;
    }

    @XmlTransient
    public Collection<Ocena> getOcenaCollection() {
        return ocenaCollection;
    }

    public void setOcenaCollection(Collection<Ocena> ocenaCollection) {
        this.ocenaCollection = ocenaCollection;
    }

    @XmlTransient
    public Collection<Sekcija> getSekcijaCollection() {
        return sekcijaCollection;
    }

    public void setSekcijaCollection(Collection<Sekcija> sekcijaCollection) {
        this.sekcijaCollection = sekcijaCollection;
    }

    @XmlTransient
    public Collection<KursTag> getKursTagCollection() {
        return kursTagCollection;
    }

    public void setKursTagCollection(Collection<KursTag> kursTagCollection) {
        this.kursTagCollection = kursTagCollection;
    }

    @XmlTransient
    public Collection<Evidencija> getEvidencijaCollection() {
        return evidencijaCollection;
    }

    public void setEvidencijaCollection(Collection<Evidencija> evidencijaCollection) {
        this.evidencijaCollection = evidencijaCollection;
    }

    public Jezik getJezikId() {
        return jezikId;
    }

    public void setJezikId(Jezik jezikId) {
        this.jezikId = jezikId;
    }

    public Kategorija getKategorijaId() {
        return kategorijaId;
    }

    public void setKategorijaId(Kategorija kategorijaId) {
        this.kategorijaId = kategorijaId;
    }

    public Korisnik getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Korisnik korisnikId) {
        this.korisnikId = korisnikId;
    }

    @XmlTransient
    public Collection<Kupon> getKuponCollection() {
        return kuponCollection;
    }

    public void setKuponCollection(Collection<Kupon> kuponCollection) {
        this.kuponCollection = kuponCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kursId != null ? kursId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kurs)) {
            return false;
        }
        Kurs other = (Kurs) object;
        if ((this.kursId == null && other.kursId != null) || (this.kursId != null && !this.kursId.equals(other.kursId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kursmania.entiteti.Kurs[ kursId=" + kursId + " ]";
    }
    
}
