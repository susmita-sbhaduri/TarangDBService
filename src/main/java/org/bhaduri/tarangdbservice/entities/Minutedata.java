/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.bhaduri.tarangdbservice.entities;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 *
 * @author bhaduri
 */
@Entity
@Table(name = "minutedata")
@NamedQueries({
    @NamedQuery(name = "Minutedata.findAll", query = "SELECT m FROM Minutedata m"),
    @NamedQuery(name = "Minutedata.findByScripid", query = "SELECT m FROM Minutedata m WHERE m.minutedataPK.scripid = :scripid"),
    @NamedQuery(name = "Minutedata.findByLastupdateminute", query = "SELECT m FROM Minutedata m WHERE m.minutedataPK.lastupdateminute = :lastupdateminute"),
    @NamedQuery(name = "Minutedata.findByOpenprice", query = "SELECT m FROM Minutedata m WHERE m.openprice = :openprice"),
    @NamedQuery(name = "Minutedata.findByDaylastprice", query = "SELECT m FROM Minutedata m WHERE m.daylastprice = :daylastprice"),
    @NamedQuery(name = "Minutedata.findByDayhighprice", query = "SELECT m FROM Minutedata m WHERE m.dayhighprice = :dayhighprice"),
    @NamedQuery(name = "Minutedata.findByDaylowprice", query = "SELECT m FROM Minutedata m WHERE m.daylowprice = :daylowprice"),
    @NamedQuery(name = "Minutedata.findByPrevcloseprice", query = "SELECT m FROM Minutedata m WHERE m.prevcloseprice = :prevcloseprice"),
    @NamedQuery(name = "Minutedata.findByTotaltradedvolume", query = "SELECT m FROM Minutedata m WHERE m.totaltradedvolume = :totaltradedvolume")})
public class Minutedata implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MinutedataPK minutedataPK;
    @Basic(optional = false)
    @Column(name = "openprice")
    private double openprice;
    @Basic(optional = false)
    @Column(name = "daylastprice")
    private double daylastprice;
    @Basic(optional = false)
    @Column(name = "dayhighprice")
    private double dayhighprice;
    @Basic(optional = false)
    @Column(name = "daylowprice")
    private double daylowprice;
    @Basic(optional = false)
    @Column(name = "prevcloseprice")
    private double prevcloseprice;
    @Basic(optional = false)
    @Column(name = "totaltradedvolume")
    private double totaltradedvolume;

    public Minutedata() {
    }

    public Minutedata(MinutedataPK minutedataPK) {
        this.minutedataPK = minutedataPK;
    }

    public Minutedata(MinutedataPK minutedataPK, double openprice, double daylastprice, double dayhighprice, double daylowprice, double prevcloseprice, double totaltradedvolume) {
        this.minutedataPK = minutedataPK;
        this.openprice = openprice;
        this.daylastprice = daylastprice;
        this.dayhighprice = dayhighprice;
        this.daylowprice = daylowprice;
        this.prevcloseprice = prevcloseprice;
        this.totaltradedvolume = totaltradedvolume;
    }

    public Minutedata(String scripid, Date lastupdateminute) {
        this.minutedataPK = new MinutedataPK(scripid, lastupdateminute);
    }

    public MinutedataPK getMinutedataPK() {
        return minutedataPK;
    }

    public void setMinutedataPK(MinutedataPK minutedataPK) {
        this.minutedataPK = minutedataPK;
    }

    public double getOpenprice() {
        return openprice;
    }

    public void setOpenprice(double openprice) {
        this.openprice = openprice;
    }

    public double getDaylastprice() {
        return daylastprice;
    }

    public void setDaylastprice(double daylastprice) {
        this.daylastprice = daylastprice;
    }

    public double getDayhighprice() {
        return dayhighprice;
    }

    public void setDayhighprice(double dayhighprice) {
        this.dayhighprice = dayhighprice;
    }

    public double getDaylowprice() {
        return daylowprice;
    }

    public void setDaylowprice(double daylowprice) {
        this.daylowprice = daylowprice;
    }

    public double getPrevcloseprice() {
        return prevcloseprice;
    }

    public void setPrevcloseprice(double prevcloseprice) {
        this.prevcloseprice = prevcloseprice;
    }

    public double getTotaltradedvolume() {
        return totaltradedvolume;
    }

    public void setTotaltradedvolume(double totaltradedvolume) {
        this.totaltradedvolume = totaltradedvolume;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (minutedataPK != null ? minutedataPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Minutedata)) {
            return false;
        }
        Minutedata other = (Minutedata) object;
        if ((this.minutedataPK == null && other.minutedataPK != null) || (this.minutedataPK != null && !this.minutedataPK.equals(other.minutedataPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.bhaduri.tarangdbservice.entities.Minutedata[ minutedataPK=" + minutedataPK + " ]";
    }
    
}
