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
@Table(name = "kupon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kupon.findAll", query = "SELECT k FROM Kupon k")
    , @NamedQuery(name = "Kupon.findByKuponId", query = "SELECT k FROM Kupon k WHERE k.kuponId = :kuponId")
    , @NamedQuery(name = "Kupon.findByKuponKod", query = "SELECT k FROM Kupon k WHERE k.kuponKod = :kuponKod")
    , @NamedQuery(name = "Kupon.findByKuponPopust", query = "SELECT k FROM Kupon k WHERE k.kuponPopust = :kuponPopust")
    , @NamedQuery(name = "Kupon.findByKuponIsIskoriscen", query = "SELECT k FROM Kupon k WHERE k.kuponIsIskoriscen = :kuponIsIskoriscen")})
public class Kupon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "KUPON_ID")
    private Integer kuponId;
    @Size(max = 10)
    @Column(name = "KUPON_KOD")
    private String kuponKod;
    @Column(name = "KUPON_POPUST")
    private Integer kuponPopust;
    @Column(name = "KUPON_IS_ISKORISCEN")
    private Short kuponIsIskoriscen;
    @JoinColumn(name = "KURS_ID", referencedColumnName = "KURS_ID")
    @ManyToOne
    private Kurs kursId;

    public Kupon() {
    }

    public Kupon(Integer kuponId) {
        this.kuponId = kuponId;
    }

    public Integer getKuponId() {
        return kuponId;
    }

    public void setKuponId(Integer kuponId) {
        this.kuponId = kuponId;
    }

    public String getKuponKod() {
        return kuponKod;
    }

    public void setKuponKod(String kuponKod) {
        this.kuponKod = kuponKod;
    }

    public Integer getKuponPopust() {
        return kuponPopust;
    }

    public void setKuponPopust(Integer kuponPopust) {
        this.kuponPopust = kuponPopust;
    }

    public Short getKuponIsIskoriscen() {
        return kuponIsIskoriscen;
    }

    public void setKuponIsIskoriscen(Short kuponIsIskoriscen) {
        this.kuponIsIskoriscen = kuponIsIskoriscen;
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
        hash += (kuponId != null ? kuponId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kupon)) {
            return false;
        }
        Kupon other = (Kupon) object;
        if ((this.kuponId == null && other.kuponId != null) || (this.kuponId != null && !this.kuponId.equals(other.kuponId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kursmania.jpa.entities.Kupon[ kuponId=" + kuponId + " ]";
    }
    
}
