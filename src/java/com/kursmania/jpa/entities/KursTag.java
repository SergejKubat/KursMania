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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "kurstag")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KursTag.findAll", query = "SELECT k FROM KursTag k")
    , @NamedQuery(name = "KursTag.findByKtId", query = "SELECT k FROM KursTag k WHERE k.ktId = :ktId")})
public class KursTag implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "KT_ID")
    private Integer ktId;
    @JoinColumn(name = "KURS_ID", referencedColumnName = "KURS_ID")
    @ManyToOne
    private Kurs kursId;
    @JoinColumn(name = "TAG_ID", referencedColumnName = "TAG_ID")
    @ManyToOne
    private Tag tagId;

    public KursTag() {
    }

    public KursTag(Integer ktId) {
        this.ktId = ktId;
    }

    public Integer getKtId() {
        return ktId;
    }

    public void setKtId(Integer ktId) {
        this.ktId = ktId;
    }

    public Kurs getKursId() {
        return kursId;
    }

    public void setKursId(Kurs kursId) {
        this.kursId = kursId;
    }

    public Tag getTagId() {
        return tagId;
    }

    public void setTagId(Tag tagId) {
        this.tagId = tagId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ktId != null ? ktId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KursTag)) {
            return false;
        }
        KursTag other = (KursTag) object;
        if ((this.ktId == null && other.ktId != null) || (this.ktId != null && !this.ktId.equals(other.ktId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kursmania.entiteti.KursTag[ ktId=" + ktId + " ]";
    }
    
}
