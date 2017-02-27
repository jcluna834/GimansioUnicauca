/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ROED26
 */
@Entity
@Table(name = "mu_ocupacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuOcupacion.findAll", query = "SELECT m FROM MuOcupacion m"),
    @NamedQuery(name = "MuOcupacion.findByOcuId", query = "SELECT m FROM MuOcupacion m WHERE m.ocuId = :ocuId"),
    @NamedQuery(name = "MuOcupacion.findByOcuDescripcion", query = "SELECT m FROM MuOcupacion m WHERE m.ocuDescripcion = :ocuDescripcion")})
public class MuOcupacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "OCU_ID")
    private Integer ocuId;
    @Size(max = 30)
    @Column(name = "OCU_DESCRIPCION")
    private String ocuDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ocuId")
    private Collection<MuUsuario> muUsuarioCollection;

    public MuOcupacion() {
    }

    public MuOcupacion(Integer ocuId) {
        this.ocuId = ocuId;
    }

    public Integer getOcuId() {
        return ocuId;
    }

    public void setOcuId(Integer ocuId) {
        this.ocuId = ocuId;
    }

    public String getOcuDescripcion() {
        return ocuDescripcion;
    }

    public void setOcuDescripcion(String ocuDescripcion) {
        this.ocuDescripcion = ocuDescripcion;
    }

    @XmlTransient
    public Collection<MuUsuario> getMuUsuarioCollection() {
        return muUsuarioCollection;
    }

    public void setMuUsuarioCollection(Collection<MuUsuario> muUsuarioCollection) {
        this.muUsuarioCollection = muUsuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ocuId != null ? ocuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuOcupacion)) {
            return false;
        }
        MuOcupacion other = (MuOcupacion) object;
        if ((this.ocuId == null && other.ocuId != null) || (this.ocuId != null && !this.ocuId.equals(other.ocuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.gymadmdoc.entities.MuOcupacion[ ocuId=" + ocuId + " ]";
    }
    
}
