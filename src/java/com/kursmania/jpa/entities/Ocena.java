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

/**
 *
 * @author Andrej Kubat
 */
@Entity
@Table(name = "ocena")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ocena.findAll", query = "SELECT o FROM Ocena o")
    , @NamedQuery(name = "Ocena.findByOcenaId", query = "SELECT o FROM Ocena o WHERE o.ocenaId = :ocenaId")
    , @NamedQuery(name = "Ocena.findByOcenaVrednost", query = "SELECT o FROM Ocena o WHERE o.ocenaVrednost = :ocenaVrednost")})
public class Ocena implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "OCENA_ID")
    private Integer ocenaId;
    @Column(name = "OCENA_VREDNOST")
    private Integer ocenaVrednost;
    @JoinColumn(name = "KORISNIK_ID", referencedColumnName = "KORISNIK_ID")
    @ManyToOne
    private Korisnik korisnikId;
    @JoinColumn(name = "KURS_ID", referencedColumnName = "KURS_ID")
    @ManyToOne
    private Kurs kursId;

    public Ocena() {
    }

    public Ocena(Integer ocenaId) {
        this.ocenaId = ocenaId;
    }

    public Integer getOcenaId() {
        return ocenaId;
    }

    public void setOcenaId(Integer ocenaId) {
        this.ocenaId = ocenaId;
    }

    public Integer getOcenaVrednost() {
        return ocenaVrednost;
    }

    public void setOcenaVrednost(Integer ocenaVrednost) {
        this.ocenaVrednost = ocenaVrednost;
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
        hash += (ocenaId != null ? ocenaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Ocena)) {
            return false;
        }
        Ocena other = (Ocena) object;
        if ((this.ocenaId == null && other.ocenaId != null) || (this.ocenaId != null && !this.ocenaId.equals(other.ocenaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kursmania.jpa.entities.Ocena[ ocenaId=" + ocenaId + " ]";
    }
    
}
