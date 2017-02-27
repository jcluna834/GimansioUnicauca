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
import javax.validation.constraints.Size;

/**
 *
 * @author ROED26
 */
@Embeddable
public class MuUsuariogrupoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "GRU_ID")
    private String gruId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USU_IDENTIFICACION")
    private long usuIdentificacion;

    public MuUsuariogrupoPK() {
    }

    public MuUsuariogrupoPK(String gruId, long usuIdentificacion) {
        this.gruId = gruId;
        this.usuIdentificacion = usuIdentificacion;
    }

    public String getGruId() {
        return gruId;
    }

    public void setGruId(String gruId) {
        this.gruId = gruId;
    }

    public long getUsuIdentificacion() {
        return usuIdentificacion;
    }

    public void setUsuIdentificacion(long usuIdentificacion) {
        this.usuIdentificacion = usuIdentificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gruId != null ? gruId.hashCode() : 0);
        hash += (int) usuIdentificacion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuUsuariogrupoPK)) {
            return false;
        }
        MuUsuariogrupoPK other = (MuUsuariogrupoPK) object;
        if ((this.gruId == null && other.gruId != null) || (this.gruId != null && !this.gruId.equals(other.gruId))) {
            return false;
        }
        if (this.usuIdentificacion != other.usuIdentificacion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unicauca.gymadmdoc.entities.MuUsuariogrupoPK[ gruId=" + gruId + ", usuIdentificacion=" + usuIdentificacion + " ]";
    }
    
}
