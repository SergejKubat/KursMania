/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kursmania.jpa.entities;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andrej Kubat
 */
@Entity
@Table(name = "komentar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Komentar.findAll", query = "SELECT k FROM Komentar k")
    , @NamedQuery(name = "Komentar.findByKomentarId", query = "SELECT k FROM Komentar k WHERE k.komentarId = :komentarId")
    , @NamedQuery(name = "Komentar.findByKomentarDatum", query = "SELECT k FROM Komentar k WHERE k.komentarDatum = :komentarDatum")
    , @NamedQuery(name = "Komentar.findByKomentarVreme", query = "SELECT k FROM Komentar k WHERE k.komentarVreme = :komentarVreme")})
public class Komentar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "KOMENTAR_ID")
    private Integer komentarId;
    @Lob
    @Size(max = 16777215)
    @Column(name = "KOMENTAR_SADRZAJ")
    private String komentarSadrzaj;
    @Column(name = "KOMENTAR_DATUM")
    @Temporal(TemporalType.DATE)
    private Date komentarDatum;
    @Size(max = 50)
    @Column(name = "KOMENTAR_VREME")
    private String komentarVreme;
    @JoinColumn(name = "KORISNIK_ID", referencedColumnName = "KORISNIK_ID")
    @ManyToOne
    private Korisnik korisnikId;
    @JoinColumn(name = "KURS_ID", referencedColumnName = "KURS_ID")
    @ManyToOne
    private Kurs kursId;
    @JoinColumn(name = "LEKCIJA_ID", referencedColumnName = "LEKCIJA_ID")
    @ManyToOne
    private Lekcija lekcijaId;

    public Komentar() {
    }

    public Komentar(Integer komentarId) {
        this.komentarId = komentarId;
    }

    public Integer getKomentarId() {
        return komentarId;
    }

    public void setKomentarId(Integer komentarId) {
        this.komentarId = komentarId;
    }

    public String getKomentarSadrzaj() {
        return komentarSadrzaj;
    }

    public void setKomentarSadrzaj(String komentarSadrzaj) {
        this.komentarSadrzaj = komentarSadrzaj;
    }

    public Date getKomentarDatum() {
        return komentarDatum;
    }

    public void setKomentarDatum(Date komentarDatum) {
        this.komentarDatum = komentarDatum;
    }

    public String getKomentarVreme() {
        return komentarVreme;
    }

    public void setKomentarVreme(String komentarVreme) {
        this.komentarVreme = komentarVreme;
    }

    public Korisnik getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Korisnik korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Kurs getKursId() {
        return kursId;
    }

    public void setKursId(Kurs kursId) {
        this.kursId = kursId;
    }

    public Lekcija getLekcijaId() {
        return lekcijaId;
    }

    public void setLekcijaId(Lekcija lekcijaId) {
        this.lekcijaId = lekcijaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (komentarId != null ? komentarId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Komentar)) {
            return false;
        }
        Komentar other = (Komentar) object;
        if ((this.komentarId == null && other.komentarId != null) || (this.komentarId != null && !this.komentarId.equals(other.komentarId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kursmania.entiteti.Komentar[ komentarId=" + komentarId + " ]";
    }
    
}
