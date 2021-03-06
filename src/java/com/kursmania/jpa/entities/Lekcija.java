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

@Entity
@Table(name = "lekcija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lekcija.findAll", query = "SELECT l FROM Lekcija l")
    , @NamedQuery(name = "Lekcija.findByLekcijaId", query = "SELECT l FROM Lekcija l WHERE l.lekcijaId = :lekcijaId")
    , @NamedQuery(name = "Lekcija.findByLekcijaIme", query = "SELECT l FROM Lekcija l WHERE l.lekcijaIme = :lekcijaIme")})
public class Lekcija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LEKCIJA_ID")
    private Integer lekcijaId;
    @Size(max = 50)
    @Column(name = "LEKCIJA_IME")
    private String lekcijaIme;
    @Lob
    @Size(max = 16777215)
    @Column(name = "LEKCIJA_OPIS")
    private String lekcijaOpis;
    @JoinColumn(name = "SEKCIJA_ID", referencedColumnName = "SEKCIJA_ID")
    @ManyToOne
    private Sekcija sekcijaId;
    @JoinColumn(name = "VIDEO_ID", referencedColumnName = "VIDEO_ID")
    @ManyToOne
    private Video videoId;
    @OneToMany(mappedBy = "lekcijaId")
    private Collection<Video> videoCollection;

    public Lekcija() {
    }

    public Lekcija(Integer lekcijaId) {
        this.lekcijaId = lekcijaId;
    }

    public Integer getLekcijaId() {
        return lekcijaId;
    }

    public void setLekcijaId(Integer lekcijaId) {
        this.lekcijaId = lekcijaId;
    }

    public String getLekcijaIme() {
        return lekcijaIme;
    }

    public void setLekcijaIme(String lekcijaIme) {
        this.lekcijaIme = lekcijaIme;
    }

    public String getLekcijaOpis() {
        return lekcijaOpis;
    }

    public void setLekcijaOpis(String lekcijaOpis) {
        this.lekcijaOpis = lekcijaOpis;
    }

    public Sekcija getSekcijaId() {
        return sekcijaId;
    }

    public void setSekcijaId(Sekcija sekcijaId) {
        this.sekcijaId = sekcijaId;
    }

    public Video getVideoId() {
        return videoId;
    }

    public void setVideoId(Video videoId) {
        this.videoId = videoId;
    }

    @XmlTransient
    public Collection<Video> getVideoCollection() {
        return videoCollection;
    }

    public void setVideoCollection(Collection<Video> videoCollection) {
        this.videoCollection = videoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lekcijaId != null ? lekcijaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Lekcija)) {
            return false;
        }
        Lekcija other = (Lekcija) object;
        if ((this.lekcijaId == null && other.lekcijaId != null) || (this.lekcijaId != null && !this.lekcijaId.equals(other.lekcijaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kursmania.jpa.entities.Lekcija[ lekcijaId=" + lekcijaId + " ]";
    }
    
}
