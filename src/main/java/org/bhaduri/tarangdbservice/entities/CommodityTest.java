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
@Table(name = "commodity_test")
@NamedQueries({
    @NamedQuery(name = "CommodityTest.findAll", query = "SELECT c FROM CommodityTest c"),
    @NamedQuery(name = "CommodityTest.findByScripid", query = "SELECT c FROM CommodityTest c WHERE c.commodityTestPK.scripid = :scripid"),
    @NamedQuery(name = "CommodityTest.findByLastupdateminute", query = "SELECT c FROM CommodityTest c WHERE c.commodityTestPK.lastupdateminute = :lastupdateminute"),
    @NamedQuery(name = "CommodityTest.findByDaylastprice", query = "SELECT c FROM CommodityTest c WHERE c.daylastprice = :daylastprice"),
    @NamedQuery(name = "CommodityTest.findByDayhighprice", query = "SELECT c FROM CommodityTest c WHERE c.dayhighprice = :dayhighprice"),
    @NamedQuery(name = "CommodityTest.findByDaylowprice", query = "SELECT c FROM CommodityTest c WHERE c.daylowprice = :daylowprice"),
    @NamedQuery(name = "CommodityTest.findByPrevcloseprice", query = "SELECT c FROM CommodityTest c WHERE c.prevcloseprice = :prevcloseprice")})
public class CommodityTest implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommodityTestPK commodityTestPK;
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

    public CommodityTest() {
    }

    public CommodityTest(CommodityTestPK commodityTestPK) {
        this.commodityTestPK = commodityTestPK;
    }

    public CommodityTest(CommodityTestPK commodityTestPK, double daylastprice, double dayhighprice, double daylowprice, double prevcloseprice) {
        this.commodityTestPK = commodityTestPK;
        this.daylastprice = daylastprice;
        this.dayhighprice = dayhighprice;
        this.daylowprice = daylowprice;
        this.prevcloseprice = prevcloseprice;
    }

    public CommodityTest(String scripid, Date lastupdateminute) {
        this.commodityTestPK = new CommodityTestPK(scripid, lastupdateminute);
    }

    public CommodityTestPK getCommodityTestPK() {
        return commodityTestPK;
    }

    public void setCommodityTestPK(CommodityTestPK commodityTestPK) {
        this.commodityTestPK = commodityTestPK;
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
        hash += (commodityTestPK != null ? commodityTestPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommodityTest)) {
            return false;
        }
        CommodityTest other = (CommodityTest) object;
        if ((this.commodityTestPK == null && other.commodityTestPK != null) || (this.commodityTestPK != null && !this.commodityTestPK.equals(other.commodityTestPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.bhaduri.tarangdbservice.entities.CommodityTest[ commodityTestPK=" + commodityTestPK + " ]";
    }
    
}
