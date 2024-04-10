/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.bhaduri.tarangdbservice.entities;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author bhaduri
 */
@Embeddable
public class CalltablePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "scripid")
    private String scripid;
    @Basic(optional = false)
    @Column(name = "lastupdateminute")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastupdateminute;

    public CalltablePK() {
    }

    public CalltablePK(String scripid, Date lastupdateminute) {
        this.scripid = scripid;
        this.lastupdateminute = lastupdateminute;
    }

    public String getScripid() {
        return scripid;
    }

    public void setScripid(String scripid) {
        this.scripid = scripid;
    }

    public Date getLastupdateminute() {
        return lastupdateminute;
    }

    public void setLastupdateminute(Date lastupdateminute) {
        this.lastupdateminute = lastupdateminute;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scripid != null ? scripid.hashCode() : 0);
        hash += (lastupdateminute != null ? lastupdateminute.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalltablePK)) {
            return false;
        }
        CalltablePK other = (CalltablePK) object;
        if ((this.scripid == null && other.scripid != null) || (this.scripid != null && !this.scripid.equals(other.scripid))) {
            return false;
        }
        if ((this.lastupdateminute == null && other.lastupdateminute != null) || (this.lastupdateminute != null && !this.lastupdateminute.equals(other.lastupdateminute))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.bhaduri.tarangdbservice.entities.CalltablePK[ scripid=" + scripid + ", lastupdateminute=" + lastupdateminute + " ]";
    }
    
}
