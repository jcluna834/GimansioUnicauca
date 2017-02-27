/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "mu_medicamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuMedicamento.findAll", query = "SELECT m FROM MuMedicamento m"),
    @NamedQuery(name = "MuMedicamento.findByMedId", query = "SELECT m FROM MuMedicamento m WHERE m.medId = :medId"),
    @NamedQuery(name = "MuMedicamento.findByMedNombre", query = "SELECT m FROM MuMedicamento m WHERE m.medNombre = :medNombre")})
public class MuMedicamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MED_ID")
    private Integer medId;
    @Size(max = 20)
    @Column(name = "MED_NOMBRE")
    private String medNombre;
    @ManyToMany(mappedBy = "muMedicamentoCollection")
    private Collection<MuUsuario> muUsuarioCollection;

    public MuMedicamento() {
    }

    public MuMedicamento(Integer medId) {
        this.medId = medId;
    }

    public Integer getMedId() {
        return medId;
    }

    public void setMedId(Integer medId) {
        this.medId = medId;
    }

    public String getMedNombre() {
        return medNombre;
    }

    public void setMedNombre(String medNombre) {
        this.medNombre = medNombre;
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
        hash += (medId != null ? medId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuMedicamento)) {
            return false;
        }
        MuMedicamento other = (MuMedicamento) object;
        if ((this.medId == null && other.medId != null) || (this.medId != null && !this.medId.equals(other.medId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.gymadmdoc.entities.MuMedicamento[ medId=" + medId + " ]";
    }
    
}
