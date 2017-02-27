/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Migdress
 */
@Embeddable
public class GrupomuscRealizaEjercicioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "GM_ID")
    private int gmId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EJ_ID")
    private int ejId;

    public GrupomuscRealizaEjercicioPK() {
    }

    public GrupomuscRealizaEjercicioPK(int gmId, int ejId) {
        this.gmId = gmId;
        this.ejId = ejId;
    }

    public int getGmId() {
        return gmId;
    }

    public void setGmId(int gmId) {
        this.gmId = gmId;
    }

    public int getEjId() {
        return ejId;
    }

    public void setEjId(int ejId) {
        this.ejId = ejId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) gmId;
        hash += (int) ejId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupomuscRealizaEjercicioPK)) {
            return false;
        }
        GrupomuscRealizaEjercicioPK other = (GrupomuscRealizaEjercicioPK) object;
        if (this.gmId != other.gmId) {
            return false;
        }
        if (this.ejId != other.ejId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unicauca.gymadmdoc.entities.GrupomuscRealizaEjercicioPK[ gmId=" + gmId + ", ejId=" + ejId + " ]";
    }
    
}
