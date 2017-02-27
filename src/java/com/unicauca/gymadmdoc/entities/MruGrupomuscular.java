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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author Migdress
 */
@Entity
@Table(name = "mru_grupomuscular")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MruGrupomuscular.findAll", query = "SELECT m FROM MruGrupomuscular m"),
    @NamedQuery(name = "MruGrupomuscular.findByGmId", query = "SELECT m FROM MruGrupomuscular m WHERE m.gmId = :gmId"),
    @NamedQuery(name = "MruGrupomuscular.findByGmNombre", query = "SELECT m FROM MruGrupomuscular m WHERE m.gmNombre = :gmNombre")})
public class MruGrupomuscular implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "GM_ID")
    private Integer gmId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "GM_NOMBRE")
    private String gmNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mruGrupomuscular")
    private Collection<GrupomuscRealizaEjercicio> grupomuscRealizaEjercicioCollection;
    @JoinColumn(name = "DIA_ID", referencedColumnName = "DIA_ID")
    @ManyToOne(optional = false)
    private MruDia diaId;

    public MruGrupomuscular() {
    }

    public MruGrupomuscular(Integer gmId) {
        this.gmId = gmId;
    }

    public MruGrupomuscular(Integer gmId, String gmNombre) {
        this.gmId = gmId;
        this.gmNombre = gmNombre;
    }

    public Integer getGmId() {
        return gmId;
    }

    public void setGmId(Integer gmId) {
        this.gmId = gmId;
    }

    public String getGmNombre() {
        return gmNombre;
    }

    public void setGmNombre(String gmNombre) {
        this.gmNombre = gmNombre;
    }

    @XmlTransient
    public Collection<GrupomuscRealizaEjercicio> getGrupomuscRealizaEjercicioCollection() {
        return grupomuscRealizaEjercicioCollection;
    }

    public void setGrupomuscRealizaEjercicioCollection(Collection<GrupomuscRealizaEjercicio> grupomuscRealizaEjercicioCollection) {
        this.grupomuscRealizaEjercicioCollection = grupomuscRealizaEjercicioCollection;
    }

    public MruDia getDiaId() {
        return diaId;
    }

    public void setDiaId(MruDia diaId) {
        this.diaId = diaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gmId != null ? gmId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MruGrupomuscular)) {
            return false;
        }
        MruGrupomuscular other = (MruGrupomuscular) object;
        if ((this.gmId == null && other.gmId != null) || (this.gmId != null && !this.gmId.equals(other.gmId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unicauca.gymadmdoc.entities.MruGrupomuscular[ gmId=" + gmId + " ]";
    }
    
}
