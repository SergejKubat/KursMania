/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kursmania.jpa.entities;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
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
    , @NamedQuery(name = "Evidencija.findByEvidancijaId", query = "SELECT e FROM Evidencija e WHERE e.evidancijaId = :evidancijaId")
    , @NamedQuery(name = "Evidencija.findByEvidencijaDatum", query = "SELECT e FROM Evidencija e WHERE e.evidencijaDatum = :evidencijaDatum")})
public class Evidencija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EVIDANCIJA_ID")
    private Integer evidancijaId;
    @Size(max = 50)
    @Column(name = "EVIDENCIJA_DATUM")
    private String evidencijaDatum;
    @JoinColumn(name = "KORISNIK_ID", referencedColumnName = "KORISNIK_ID")
    @ManyToOne
    private Korisnik korisnikId;
    @JoinColumn(name = "KURS_ID", referencedColumnName = "KURS_ID")
    @ManyToOne
    private Kurs kursId;

    public Evidencija() {
    }

    public Evidencija(Integer evidancijaId) {
        this.evidancijaId = evidancijaId;
    }

    public Integer getEvidancijaId() {
        return evidancijaId;
    }

    public void setEvidancijaId(Integer evidancijaId) {
        this.evidancijaId = evidancijaId;
    }

    public String getEvidencijaDatum() {
        return evidencijaDatum;
    }

    public void setEvidencijaDatum(String evidencijaDatum) {
        this.evidencijaDatum = evidencijaDatum;
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
        hash += (evidancijaId != null ? evidancijaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evidencija)) {
            return false;
        }
        Evidencija other = (Evidencija) object;
        if ((this.evidancijaId == null && other.evidancijaId != null) || (this.evidancijaId != null && !this.evidancijaId.equals(other.evidancijaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kursmania.jpa.entities.Evidencija[ evidancijaId=" + evidancijaId + " ]";
    }
    
}
