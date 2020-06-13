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
@Table(name = "rola")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rola.findAll", query = "SELECT r FROM Rola r")
    , @NamedQuery(name = "Rola.findByRolaId", query = "SELECT r FROM Rola r WHERE r.rolaId = :rolaId")
    , @NamedQuery(name = "Rola.findByRolaNaziv", query = "SELECT r FROM Rola r WHERE r.rolaNaziv = :rolaNaziv")})
public class Rola implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ROLA_ID")
    private Integer rolaId;
    @Size(max = 50)
    @Column(name = "ROLA_NAZIV")
    private String rolaNaziv;
    @OneToMany(mappedBy = "rolaId")
    private Collection<Korisnik> korisnikCollection;

    public Rola() {
    }

    public Rola(Integer rolaId) {
        this.rolaId = rolaId;
    }

    public Integer getRolaId() {
        return rolaId;
    }

    public void setRolaId(Integer rolaId) {
        this.rolaId = rolaId;
    }

    public String getRolaNaziv() {
        return rolaNaziv;
    }

    public void setRolaNaziv(String rolaNaziv) {
        this.rolaNaziv = rolaNaziv;
    }

    @XmlTransient
    public Collection<Korisnik> getKorisnikCollection() {
        return korisnikCollection;
    }

    public void setKorisnikCollection(Collection<Korisnik> korisnikCollection) {
        this.korisnikCollection = korisnikCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolaId != null ? rolaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rola)) {
            return false;
        }
        Rola other = (Rola) object;
        if ((this.rolaId == null && other.rolaId != null) || (this.rolaId != null && !this.rolaId.equals(other.rolaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kursmania.jpa.entities.Rola[ rolaId=" + rolaId + " ]";
    }
    
}
