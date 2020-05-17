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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andrej Kubat
 */
@Entity
@Table(name = "kategorija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kategorija.findAll", query = "SELECT k FROM Kategorija k")
    , @NamedQuery(name = "Kategorija.findByKategorijaId", query = "SELECT k FROM Kategorija k WHERE k.kategorijaId = :kategorijaId")
    , @NamedQuery(name = "Kategorija.findByKategorijaNaziv", query = "SELECT k FROM Kategorija k WHERE k.kategorijaNaziv = :kategorijaNaziv")})
public class Kategorija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "KATEGORIJA_ID")
    private Integer kategorijaId;
    @Size(max = 100)
    @Column(name = "KATEGORIJA_NAZIV")
    private String kategorijaNaziv;
    @OneToMany(mappedBy = "nadKategorijaId")
    private Collection<Kategorija> kategorijaCollection;
    @JoinColumn(name = "NAD_KATEGORIJA_ID", referencedColumnName = "KATEGORIJA_ID")
    @ManyToOne
    private Kategorija nadKategorijaId;
    @OneToMany(mappedBy = "kategorijaId")
    private Collection<Kurs> kursCollection;

    public Kategorija() {
    }

    public Kategorija(Integer kategorijaId) {
        this.kategorijaId = kategorijaId;
    }

    public Integer getKategorijaId() {
        return kategorijaId;
    }

    public void setKategorijaId(Integer kategorijaId) {
        this.kategorijaId = kategorijaId;
    }

    public String getKategorijaNaziv() {
        return kategorijaNaziv;
    }

    public void setKategorijaNaziv(String kategorijaNaziv) {
        this.kategorijaNaziv = kategorijaNaziv;
    }

    @XmlTransient
    public Collection<Kategorija> getKategorijaCollection() {
        return kategorijaCollection;
    }

    public void setKategorijaCollection(Collection<Kategorija> kategorijaCollection) {
        this.kategorijaCollection = kategorijaCollection;
    }

    public Kategorija getNadKategorijaId() {
        return nadKategorijaId;
    }

    public void setNadKategorijaId(Kategorija nadKategorijaId) {
        this.nadKategorijaId = nadKategorijaId;
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
        hash += (kategorijaId != null ? kategorijaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kategorija)) {
            return false;
        }
        Kategorija other = (Kategorija) object;
        if ((this.kategorijaId == null && other.kategorijaId != null) || (this.kategorijaId != null && !this.kategorijaId.equals(other.kategorijaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kursmania.entiteti.Kategorija[ kategorijaId=" + kategorijaId + " ]";
    }
    
}
