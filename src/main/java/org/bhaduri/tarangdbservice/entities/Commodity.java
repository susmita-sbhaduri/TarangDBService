/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.bhaduri.tarangdbservice.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author sb
 */
@Entity
@Table(name = "commodity")
@NamedQueries({
    @NamedQuery(name = "Commodity.findAll", query = "SELECT c FROM Commodity c"),
    @NamedQuery(name = "Commodity.findByScripid", query = "SELECT c FROM Commodity c WHERE c.commodityPK.scripid = :scripid"),
    @NamedQuery(name = "Commodity.findByLastupdateminute", query = "SELECT c FROM Commodity c WHERE c.commodityPK.lastupdateminute = :lastupdateminute"),
    @NamedQuery(name = "Commodity.findByDaylastprice", query = "SELECT c FROM Commodity c WHERE c.daylastprice = :daylastprice"),
    @NamedQuery(name = "Commodity.findByDayhighprice", query = "SELECT c FROM Commodity c WHERE c.dayhighprice = :dayhighprice"),
    @NamedQuery(name = "Commodity.findByDaylowprice", query = "SELECT c FROM Commodity c WHERE c.daylowprice = :daylowprice"),
    @NamedQuery(name = "Commodity.findByPrevcloseprice", query = "SELECT c FROM Commodity c WHERE c.prevcloseprice = :prevcloseprice")})
public class Commodity implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommodityPK commodityPK;
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

    public Commodity() {
    }

    public Commodity(CommodityPK commodityPK) {
        this.commodityPK = commodityPK;
    }

    public Commodity(CommodityPK commodityPK, double daylastprice, double dayhighprice, double daylowprice, double prevcloseprice) {
        this.commodityPK = commodityPK;
        this.daylastprice = daylastprice;
        this.dayhighprice = dayhighprice;
        this.daylowprice = daylowprice;
        this.prevcloseprice = prevcloseprice;
    }

    public Commodity(String scripid, Date lastupdateminute) {
        this.commodityPK = new CommodityPK(scripid, lastupdateminute);
    }

    public CommodityPK getCommodityPK() {
        return commodityPK;
    }

    public void setCommodityPK(CommodityPK commodityPK) {
        this.commodityPK = commodityPK;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commodityPK != null ? commodityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commodity)) {
            return false;
        }
        Commodity other = (Commodity) object;
        if ((this.commodityPK == null && other.commodityPK != null) || (this.commodityPK != null && !this.commodityPK.equals(other.commodityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.bhaduri.tarangdbservice.entities.Commodity[ commodityPK=" + commodityPK + " ]";
    }
    
}
