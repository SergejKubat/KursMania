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
import javax.persistence.Lob;
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
@Table(name = "sekcija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sekcija.findAll", query = "SELECT s FROM Sekcija s")
    , @NamedQuery(name = "Sekcija.findBySekcijaId", query = "SELECT s FROM Sekcija s WHERE s.sekcijaId = :sekcijaId")})
public class Sekcija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEKCIJA_ID")
    private Integer sekcijaId;
    @Lob
    @Size(max = 16777215)
    @Column(name = "SEKCIJA_NASLOV")
    private String sekcijaNaslov;
    @OneToMany(mappedBy = "sekcijaId")
    private Collection<Lekcija> lekcijaCollection;
    @JoinColumn(name = "KURS_ID", referencedColumnName = "KURS_ID")
    @ManyToOne
    private Kurs kursId;

    public Sekcija() {
    }

    public Sekcija(Integer sekcijaId) {
        this.sekcijaId = sekcijaId;
    }

    public Integer getSekcijaId() {
        return sekcijaId;
    }

    public void setSekcijaId(Integer sekcijaId) {
        this.sekcijaId = sekcijaId;
    }

    public String getSekcijaNaslov() {
        return sekcijaNaslov;
    }

    public void setSekcijaNaslov(String sekcijaNaslov) {
        this.sekcijaNaslov = sekcijaNaslov;
    }

    @XmlTransient
    public Collection<Lekcija> getLekcijaCollection() {
        return lekcijaCollection;
    }

    public void setLekcijaCollection(Collection<Lekcija> lekcijaCollection) {
        this.lekcijaCollection = lekcijaCollection;
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
        hash += (sekcijaId != null ? sekcijaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sekcija)) {
            return false;
        }
        Sekcija other = (Sekcija) object;
        if ((this.sekcijaId == null && other.sekcijaId != null) || (this.sekcijaId != null && !this.sekcijaId.equals(other.sekcijaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kursmania.entiteti.Sekcija[ sekcijaId=" + sekcijaId + " ]";
    }
    
}
