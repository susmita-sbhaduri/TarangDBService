/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.bhaduri.tarangdbservice.entities;

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
@Table(name = "validatecall")
@NamedQueries({
    @NamedQuery(name = "Validatecall.findAll", query = "SELECT v FROM Validatecall v"),
    @NamedQuery(name = "Validatecall.findByScripid", query = "SELECT v FROM Validatecall v WHERE v.validatecallPK.scripid = :scripid"),
    @NamedQuery(name = "Validatecall.findByLastupdateminute", query = "SELECT v FROM Validatecall v WHERE v.validatecallPK.lastupdateminute = :lastupdateminute"),
    @NamedQuery(name = "Validatecall.findByPrice", query = "SELECT v FROM Validatecall v WHERE v.price = :price"),
    @NamedQuery(name = "Validatecall.findByCallone", query = "SELECT v FROM Validatecall v WHERE v.callone = :callone"),
    @NamedQuery(name = "Validatecall.findByCalltwo", query = "SELECT v FROM Validatecall v WHERE v.calltwo = :calltwo"),
    @NamedQuery(name = "Validatecall.findByMarginone", query = "SELECT v FROM Validatecall v WHERE v.marginone = :marginone"),
    @NamedQuery(name = "Validatecall.findByOutcomeone", query = "SELECT v FROM Validatecall v WHERE v.outcomeone = :outcomeone"),
    @NamedQuery(name = "Validatecall.findByMargintwo", query = "SELECT v FROM Validatecall v WHERE v.margintwo = :margintwo"),
    @NamedQuery(name = "Validatecall.findByOutcometwo", query = "SELECT v FROM Validatecall v WHERE v.outcometwo = :outcometwo")})
public class Validatecall implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ValidatecallPK validatecallPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @Column(name = "callone")
    private String callone;
    @Column(name = "calltwo")
    private String calltwo;
    @Column(name = "marginone")
    private Double marginone;
    @Column(name = "outcomeone")
    private String outcomeone;
    @Column(name = "margintwo")
    private Double margintwo;
    @Column(name = "outcometwo")
    private String outcometwo;

    public Validatecall() {
    }

    public Validatecall(ValidatecallPK validatecallPK) {
        this.validatecallPK = validatecallPK;
    }

    public Validatecall(String scripid, Date lastupdateminute) {
        this.validatecallPK = new ValidatecallPK(scripid, lastupdateminute);
    }

    public ValidatecallPK getValidatecallPK() {
        return validatecallPK;
    }

    public void setValidatecallPK(ValidatecallPK validatecallPK) {
        this.validatecallPK = validatecallPK;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCallone() {
        return callone;
    }

    public void setCallone(String callone) {
        this.callone = callone;
    }

    public String getCalltwo() {
        return calltwo;
    }

    public void setCalltwo(String calltwo) {
        this.calltwo = calltwo;
    }

    public Double getMarginone() {
        return marginone;
    }

    public void setMarginone(Double marginone) {
        this.marginone = marginone;
    }

    public String getOutcomeone() {
        return outcomeone;
    }

    public void setOutcomeone(String outcomeone) {
        this.outcomeone = outcomeone;
    }

    public Double getMargintwo() {
        return margintwo;
    }

    public void setMargintwo(Double margintwo) {
        this.margintwo = margintwo;
    }

    public String getOutcometwo() {
        return outcometwo;
    }

    public void setOutcometwo(String outcometwo) {
        this.outcometwo = outcometwo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (validatecallPK != null ? validatecallPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Validatecall)) {
            return false;
        }
        Validatecall other = (Validatecall) object;
        if ((this.validatecallPK == null && other.validatecallPK != null) || (this.validatecallPK != null && !this.validatecallPK.equals(other.validatecallPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.bhaduri.tarangdbservice.entities.Validatecall[ validatecallPK=" + validatecallPK + " ]";
    }
    
}
