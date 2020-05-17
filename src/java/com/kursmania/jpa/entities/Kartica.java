/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kursmania.jpa.entities;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andrej Kubat
 */
@Entity
@Table(name = "kartica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kartica.findAll", query = "SELECT k FROM Kartica k")
    , @NamedQuery(name = "Kartica.findByKarticaId", query = "SELECT k FROM Kartica k WHERE k.karticaId = :karticaId")
    , @NamedQuery(name = "Kartica.findByKarticaBroj", query = "SELECT k FROM Kartica k WHERE k.karticaBroj = :karticaBroj")
    , @NamedQuery(name = "Kartica.findByKarticaStanje", query = "SELECT k FROM Kartica k WHERE k.karticaStanje = :karticaStanje")})
public class Kartica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "KARTICA_ID")
    private Integer karticaId;
    @Column(name = "KARTICA_BROJ")
    private Integer karticaBroj;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "KARTICA_STANJE")
    private Float karticaStanje;
    @JoinColumn(name = "KORISNIK_ID", referencedColumnName = "KORISNIK_ID")
    @ManyToOne
    private Korisnik korisnikId;
    @OneToMany(mappedBy = "karticaId")
    private Collection<Evidencija> evidencijaCollection;

    public Kartica() {
    }

    public Kartica(Integer karticaId) {
        this.karticaId = karticaId;
    }

    public Integer getKarticaId() {
        return karticaId;
    }

    public void setKarticaId(Integer karticaId) {
        this.karticaId = karticaId;
    }

    public Integer getKarticaBroj() {
        return karticaBroj;
    }

    public void setKarticaBroj(Integer karticaBroj) {
        this.karticaBroj = karticaBroj;
    }

    public Float getKarticaStanje() {
        return karticaStanje;
    }

    public void setKarticaStanje(Float karticaStanje) {
        this.karticaStanje = karticaStanje;
    }

    public Korisnik getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Korisnik korisnikId) {
        this.korisnikId = korisnikId;
    }

    @XmlTransient
    public Collection<Evidencija> getEvidencijaCollection() {
        return evidencijaCollection;
    }

    public void setEvidencijaCollection(Collection<Evidencija> evidencijaCollection) {
        this.evidencijaCollection = evidencijaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (karticaId != null ? karticaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kartica)) {
            return false;
        }
        Kartica other = (Kartica) object;
        if ((this.karticaId == null && other.karticaId != null) || (this.karticaId != null && !this.karticaId.equals(other.karticaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kursmania.entiteti.Kartica[ karticaId=" + karticaId + " ]";
    }
    
}
