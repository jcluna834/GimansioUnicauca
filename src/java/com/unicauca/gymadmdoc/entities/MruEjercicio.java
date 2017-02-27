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
 * @author Migdress
 */
@Entity
@Table(name = "mru_ejercicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MruEjercicio.findAll", query = "SELECT m FROM MruEjercicio m"),
    @NamedQuery(name = "MruEjercicio.findByEjId", query = "SELECT m FROM MruEjercicio m WHERE m.ejId = :ejId"),
    @NamedQuery(name = "MruEjercicio.findByEjNombre", query = "SELECT m FROM MruEjercicio m WHERE m.ejNombre = :ejNombre"),
    @NamedQuery(name = "MruEjercicio.findByEjDescrip", query = "SELECT m FROM MruEjercicio m WHERE m.ejDescrip = :ejDescrip"),
    @NamedQuery(name = "MruEjercicio.findByEjImagen", query = "SELECT m FROM MruEjercicio m WHERE m.ejImagen = :ejImagen")})
public class MruEjercicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EJ_ID")
    private Integer ejId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "EJ_NOMBRE")
    private String ejNombre;
    @Size(max = 500)
    @Column(name = "EJ_DESCRIP")
    private String ejDescrip;
    @Size(max = 200)
    @Column(name = "EJ_IMAGEN")
    private String ejImagen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mruEjercicio")
    private Collection<GrupomuscRealizaEjercicio> grupomuscRealizaEjercicioCollection;

    public MruEjercicio() {
    }

    public MruEjercicio(Integer ejId) {
        this.ejId = ejId;
    }

    public MruEjercicio(Integer ejId, String ejNombre) {
        this.ejId = ejId;
        this.ejNombre = ejNombre;
    }

    public Integer getEjId() {
        return ejId;
    }

    public void setEjId(Integer ejId) {
        this.ejId = ejId;
    }

    public String getEjNombre() {
        return ejNombre;
    }

    public void setEjNombre(String ejNombre) {
        this.ejNombre = ejNombre;
    }

    public String getEjDescrip() {
        return ejDescrip;
    }

    public void setEjDescrip(String ejDescrip) {
        this.ejDescrip = ejDescrip;
    }

    public String getEjImagen() {
        return ejImagen;
    }

    public void setEjImagen(String ejImagen) {
        this.ejImagen = ejImagen;
    }

    @XmlTransient
    public Collection<GrupomuscRealizaEjercicio> getGrupomuscRealizaEjercicioCollection() {
        return grupomuscRealizaEjercicioCollection;
    }

    public void setGrupomuscRealizaEjercicioCollection(Collection<GrupomuscRealizaEjercicio> grupomuscRealizaEjercicioCollection) {
        this.grupomuscRealizaEjercicioCollection = grupomuscRealizaEjercicioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ejId != null ? ejId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MruEjercicio)) {
            return false;
        }
        MruEjercicio other = (MruEjercicio) object;
        if ((this.ejId == null && other.ejId != null) || (this.ejId != null && !this.ejId.equals(other.ejId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unicauca.gymadmdoc.entities.MruEjercicio[ ejId=" + ejId + " ]";
    }
    
}
