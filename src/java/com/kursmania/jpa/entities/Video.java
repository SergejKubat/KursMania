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
import javax.persistence.Lob;
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
@Table(name = "video")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v")
    , @NamedQuery(name = "Video.findByVideoId", query = "SELECT v FROM Video v WHERE v.videoId = :videoId")
    , @NamedQuery(name = "Video.findByVideoIme", query = "SELECT v FROM Video v WHERE v.videoIme = :videoIme")
    , @NamedQuery(name = "Video.findByVideoDuzinaTrajanja", query = "SELECT v FROM Video v WHERE v.videoDuzinaTrajanja = :videoDuzinaTrajanja")})
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "VIDEO_ID")
    private Integer videoId;
    @Size(max = 100)
    @Column(name = "VIDEO_IME")
    private String videoIme;
    @Lob
    @Size(max = 16777215)
    @Column(name = "VIDEO_ADRESA")
    private String videoAdresa;
    @Column(name = "VIDEO_DUZINA_TRAJANJA")
    private Integer videoDuzinaTrajanja;
    @JoinColumn(name = "LEKCIJA_ID", referencedColumnName = "LEKCIJA_ID")
    @ManyToOne
    private Lekcija lekcijaId;

    public Video() {
    }

    public Video(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoIme() {
        return videoIme;
    }

    public void setVideoIme(String videoIme) {
        this.videoIme = videoIme;
    }

    public String getVideoAdresa() {
        return videoAdresa;
    }

    public void setVideoAdresa(String videoAdresa) {
        this.videoAdresa = videoAdresa;
    }

    public Integer getVideoDuzinaTrajanja() {
        return videoDuzinaTrajanja;
    }

    public void setVideoDuzinaTrajanja(Integer videoDuzinaTrajanja) {
        this.videoDuzinaTrajanja = videoDuzinaTrajanja;
    }

    public Lekcija getLekcijaId() {
        return lekcijaId;
    }

    public void setLekcijaId(Lekcija lekcijaId) {
        this.lekcijaId = lekcijaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (videoId != null ? videoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Video)) {
            return false;
        }
        Video other = (Video) object;
        if ((this.videoId == null && other.videoId != null) || (this.videoId != null && !this.videoId.equals(other.videoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kursmania.entiteti.Video[ videoId=" + videoId + " ]";
    }
    
}
