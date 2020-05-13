/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kursmania.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "jezik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jezik.findAll", query = "SELECT j FROM Jezik j")
    , @NamedQuery(name = "Jezik.findByJezikId", query = "SELECT j FROM Jezik j WHERE j.jezikId = :jezikId")
    , @NamedQuery(name = "Jezik.findByJezikNaziv", query = "SELECT j FROM Jezik j WHERE j.jezikNaziv = :jezikNaziv")})
public class Jezik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "JEZIK_ID")
    private Integer jezikId;
    @Size(max = 50)
    @Column(name = "JEZIK_NAZIV")
    private String jezikNaziv;
    @OneToMany(mappedBy = "jezikId")
    private Collection<Kurs> kursCollection;

    public Jezik() {
    }

    public Jezik(Integer jezikId) {
        this.jezikId = jezikId;
    }

    public Integer getJezikId() {
        return jezikId;
    }

    public void setJezikId(Integer jezikId) {
        this.jezikId = jezikId;
    }

    public String getJezikNaziv() {
        return jezikNaziv;
    }

    public void setJezikNaziv(String jezikNaziv) {
        this.jezikNaziv = jezikNaziv;
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
        hash += (jezikId != null ? jezikId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jezik)) {
            return false;
        }
        Jezik other = (Jezik) object;
        if ((this.jezikId == null && other.jezikId != null) || (this.jezikId != null && !this.jezikId.equals(other.jezikId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kursmania.entiteti.Jezik[ jezikId=" + jezikId + " ]";
    }
    
}
