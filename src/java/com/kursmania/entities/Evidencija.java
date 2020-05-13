/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kursmania.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andrej Kubat
 */
@Entity
@Table(name = "evidencija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evidencija.findAll", query = "SELECT e FROM Evidencija e")
    , @NamedQuery(name = "Evidencija.findByEvidencijaId", query = "SELECT e FROM Evidencija e WHERE e.evidencijaId = :evidencijaId")
    , @NamedQuery(name = "Evidencija.findByEvidencijaDatum", query = "SELECT e FROM Evidencija e WHERE e.evidencijaDatum = :evidencijaDatum")})
public class Evidencija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EVIDENCIJA_ID")
    private Integer evidencijaId;
    @Column(name = "EVIDENCIJA_DATUM")
    @Temporal(TemporalType.DATE)
    private Date evidencijaDatum;
    @JoinColumn(name = "KARTICA_ID", referencedColumnName = "KARTICA_ID")
    @ManyToOne
    private Kartica karticaId;
    @JoinColumn(name = "KORISNIK_ID", referencedColumnName = "KORISNIK_ID")
    @ManyToOne
    private Korisnik korisnikId;
    @JoinColumn(name = "KURS_ID", referencedColumnName = "KURS_ID")
    @ManyToOne
    private Kurs kursId;

    public Evidencija() {
    }

    public Evidencija(Integer evidencijaId) {
        this.evidencijaId = evidencijaId;
    }

    public Integer getEvidencijaId() {
        return evidencijaId;
    }

    public void setEvidencijaId(Integer evidencijaId) {
        this.evidencijaId = evidencijaId;
    }

    public Date getEvidencijaDatum() {
        return evidencijaDatum;
    }

    public void setEvidencijaDatum(Date evidencijaDatum) {
        this.evidencijaDatum = evidencijaDatum;
    }

    public Kartica getKarticaId() {
        return karticaId;
    }

    public void setKarticaId(Kartica karticaId) {
        this.karticaId = karticaId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evidencijaId != null ? evidencijaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evidencija)) {
            return false;
        }
        Evidencija other = (Evidencija) object;
        if ((this.evidencijaId == null && other.evidencijaId != null) || (this.evidencijaId != null && !this.evidencijaId.equals(other.evidencijaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kursmania.entiteti.Evidencija[ evidencijaId=" + evidencijaId + " ]";
    }
    
}
