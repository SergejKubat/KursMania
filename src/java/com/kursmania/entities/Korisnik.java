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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andrej Kubat
 */
@Entity
@Table(name = "korisnik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Korisnik.findAll", query = "SELECT k FROM Korisnik k")
    , @NamedQuery(name = "Korisnik.findByKorisnikId", query = "SELECT k FROM Korisnik k WHERE k.korisnikId = :korisnikId")
    , @NamedQuery(name = "Korisnik.findByKorisnikIme", query = "SELECT k FROM Korisnik k WHERE k.korisnikIme = :korisnikIme")
    , @NamedQuery(name = "Korisnik.findByKorisnikPrezime", query = "SELECT k FROM Korisnik k WHERE k.korisnikPrezime = :korisnikPrezime")
    , @NamedQuery(name = "Korisnik.findByKorisnikEmail", query = "SELECT k FROM Korisnik k WHERE k.korisnikEmail = :korisnikEmail")
    , @NamedQuery(name = "Korisnik.findByKorisnikBrojTelefona", query = "SELECT k FROM Korisnik k WHERE k.korisnikBrojTelefona = :korisnikBrojTelefona")
    , @NamedQuery(name = "Korisnik.findByKorisnikMesto", query = "SELECT k FROM Korisnik k WHERE k.korisnikMesto = :korisnikMesto")
    , @NamedQuery(name = "Korisnik.findByKorisnikAdresa", query = "SELECT k FROM Korisnik k WHERE k.korisnikAdresa = :korisnikAdresa")
    , @NamedQuery(name = "Korisnik.findByKorisnikDatumRegistracije", query = "SELECT k FROM Korisnik k WHERE k.korisnikDatumRegistracije = :korisnikDatumRegistracije")
    , @NamedQuery(name = "Korisnik.findByKorisnikLozinka", query = "SELECT k FROM Korisnik k WHERE k.korisnikLozinka = :korisnikLozinka")
    , @NamedQuery(name = "Korisnik.findByKorisnikIsBlocked", query = "SELECT k FROM Korisnik k WHERE k.korisnikIsBlocked = :korisnikIsBlocked")})
public class Korisnik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "KORISNIK_ID")
    private Integer korisnikId;
    @Size(max = 50)
    @Column(name = "KORISNIK_IME")
    private String korisnikIme;
    @Size(max = 50)
    @Column(name = "KORISNIK_PREZIME")
    private String korisnikPrezime;
    @Size(max = 100)
    @Column(name = "KORISNIK_EMAIL")
    private String korisnikEmail;
    @Size(max = 15)
    @Column(name = "KORISNIK_BROJ_TELEFONA")
    private String korisnikBrojTelefona;
    @Size(max = 50)
    @Column(name = "KORISNIK_MESTO")
    private String korisnikMesto;
    @Size(max = 50)
    @Column(name = "KORISNIK_ADRESA")
    private String korisnikAdresa;
    @Lob
    @Size(max = 16777215)
    @Column(name = "KORISNIK_AVATAR")
    private String korisnikAvatar;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "KORISNIK_TITULA")
    private String korisnikTitula;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "KORISNIK_OPIS")
    private String korisnikOpis;
    @Column(name = "KORISNIK_DATUM_REGISTRACIJE")
    @Temporal(TemporalType.DATE)
    private Date korisnikDatumRegistracije;
    @Size(max = 100)
    @Column(name = "KORISNIK_LOZINKA")
    private String korisnikLozinka;
    @Column(name = "KORISNIK_IS_BLOCKED")
    private Short korisnikIsBlocked;
    @OneToMany(mappedBy = "korisnikId")
    private Collection<Komentar> komentarCollection;
    @OneToMany(mappedBy = "korisnikId")
    private Collection<Korpa> korpaCollection;
    @OneToMany(mappedBy = "korisnikId")
    private Collection<Ocena> ocenaCollection;
    @JoinColumn(name = "ROLA_ID", referencedColumnName = "ROLA_ID")
    @ManyToOne
    private Rola rolaId;
    @OneToMany(mappedBy = "korisnikId")
    private Collection<Kartica> karticaCollection;
    @OneToMany(mappedBy = "korisnikId")
    private Collection<Evidencija> evidencijaCollection;
    @OneToMany(mappedBy = "korisnikId")
    private Collection<Kurs> kursCollection;

    public Korisnik() {
    }

    public Korisnik(Integer korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Korisnik(Integer korisnikId, String korisnikTitula, String korisnikOpis) {
        this.korisnikId = korisnikId;
        this.korisnikTitula = korisnikTitula;
        this.korisnikOpis = korisnikOpis;
    }

    public Integer getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Integer korisnikId) {
        this.korisnikId = korisnikId;
    }

    public String getKorisnikIme() {
        return korisnikIme;
    }

    public void setKorisnikIme(String korisnikIme) {
        this.korisnikIme = korisnikIme;
    }

    public String getKorisnikPrezime() {
        return korisnikPrezime;
    }

    public void setKorisnikPrezime(String korisnikPrezime) {
        this.korisnikPrezime = korisnikPrezime;
    }

    public String getKorisnikEmail() {
        return korisnikEmail;
    }

    public void setKorisnikEmail(String korisnikEmail) {
        this.korisnikEmail = korisnikEmail;
    }

    public String getKorisnikBrojTelefona() {
        return korisnikBrojTelefona;
    }

    public void setKorisnikBrojTelefona(String korisnikBrojTelefona) {
        this.korisnikBrojTelefona = korisnikBrojTelefona;
    }

    public String getKorisnikMesto() {
        return korisnikMesto;
    }

    public void setKorisnikMesto(String korisnikMesto) {
        this.korisnikMesto = korisnikMesto;
    }

    public String getKorisnikAdresa() {
        return korisnikAdresa;
    }

    public void setKorisnikAdresa(String korisnikAdresa) {
        this.korisnikAdresa = korisnikAdresa;
    }

    public String getKorisnikAvatar() {
        return korisnikAvatar;
    }

    public void setKorisnikAvatar(String korisnikAvatar) {
        this.korisnikAvatar = korisnikAvatar;
    }

    public String getKorisnikTitula() {
        return korisnikTitula;
    }

    public void setKorisnikTitula(String korisnikTitula) {
        this.korisnikTitula = korisnikTitula;
    }

    public String getKorisnikOpis() {
        return korisnikOpis;
    }

    public void setKorisnikOpis(String korisnikOpis) {
        this.korisnikOpis = korisnikOpis;
    }

    public Date getKorisnikDatumRegistracije() {
        return korisnikDatumRegistracije;
    }

    public void setKorisnikDatumRegistracije(Date korisnikDatumRegistracije) {
        this.korisnikDatumRegistracije = korisnikDatumRegistracije;
    }

    public String getKorisnikLozinka() {
        return korisnikLozinka;
    }

    public void setKorisnikLozinka(String korisnikLozinka) {
        this.korisnikLozinka = korisnikLozinka;
    }

    public Short getKorisnikIsBlocked() {
        return korisnikIsBlocked;
    }

    public void setKorisnikIsBlocked(Short korisnikIsBlocked) {
        this.korisnikIsBlocked = korisnikIsBlocked;
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

    public Rola getRolaId() {
        return rolaId;
    }

    public void setRolaId(Rola rolaId) {
        this.rolaId = rolaId;
    }

    @XmlTransient
    public Collection<Kartica> getKarticaCollection() {
        return karticaCollection;
    }

    public void setKarticaCollection(Collection<Kartica> karticaCollection) {
        this.karticaCollection = karticaCollection;
    }

    @XmlTransient
    public Collection<Evidencija> getEvidencijaCollection() {
        return evidencijaCollection;
    }

    public void setEvidencijaCollection(Collection<Evidencija> evidencijaCollection) {
        this.evidencijaCollection = evidencijaCollection;
    }

    @XmlTransient
    public Collection<Kurs> getKursCollection() {
        return kursCollection;
    }

    public void setKursCollection(Collection<Kurs> kursCollection) {
        this.kursCollection = kursCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (korisnikId != null ? korisnikId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Korisnik)) {
            return false;
        }
        Korisnik other = (Korisnik) object;
        if ((this.korisnikId == null && other.korisnikId != null) || (this.korisnikId != null && !this.korisnikId.equals(other.korisnikId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kursmania.entiteti.Korisnik[ korisnikId=" + korisnikId + " ]";
    }
    
}
