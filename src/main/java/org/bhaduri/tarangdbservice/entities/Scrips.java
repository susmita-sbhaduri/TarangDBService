/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.bhaduri.tarangdbservice.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author sb
 */
@Entity
@Table(name = "scrips")
@NamedQueries({
    @NamedQuery(name = "Scrips.findAll", query = "SELECT s FROM Scrips s"),
    @NamedQuery(name = "Scrips.findByScripid", query = "SELECT s FROM Scrips s WHERE s.scripid = :scripid")})
public class Scrips implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "scripid")
    private String scripid;

    public Scrips() {
    }

    public Scrips(String scripid) {
        this.scripid = scripid;
    }

    public String getScripid() {
        return scripid;
    }

    public void setScripid(String scripid) {
        this.scripid = scripid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scripid != null ? scripid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Scrips)) {
            return false;
        }
        Scrips other = (Scrips) object;
        if ((this.scripid == null && other.scripid != null) || (this.scripid != null && !this.scripid.equals(other.scripid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.bhaduri.tarangdbservice.entities.Scrips[ scripid=" + scripid + " ]";
    }
    
}
