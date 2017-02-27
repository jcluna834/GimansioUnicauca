/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Migdress
 */
@Entity
@Table(name = "mru_variaciondepeso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MruVariaciondepeso.findAll", query = "SELECT m FROM MruVariaciondepeso m"),
    @NamedQuery(name = "MruVariaciondepeso.findByVpId", query = "SELECT m FROM MruVariaciondepeso m WHERE m.vpId = :vpId"),
    @NamedQuery(name = "MruVariaciondepeso.findByVpNombre", query = "SELECT m FROM MruVariaciondepeso m WHERE m.vpNombre = :vpNombre"),
    @NamedQuery(name = "MruVariaciondepeso.findByVpDescrip", query = "SELECT m FROM MruVariaciondepeso m WHERE m.vpDescrip = :vpDescrip")})
public class MruVariaciondepeso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "VP_ID")
    private Integer vpId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "VP_NOMBRE")
    private String vpNombre;
    @Size(max = 200)
    @Column(name = "VP_DESCRIP")
    private String vpDescrip;

    public MruVariaciondepeso() {
    }

    public MruVariaciondepeso(Integer vpId) {
        this.vpId = vpId;
    }

    public MruVariaciondepeso(Integer vpId, String vpNombre) {
        this.vpId = vpId;
        this.vpNombre = vpNombre;
    }

    public Integer getVpId() {
        return vpId;
    }

    public void setVpId(Integer vpId) {
        this.vpId = vpId;
    }

    public String getVpNombre() {
        return vpNombre;
    }

    public void setVpNombre(String vpNombre) {
        this.vpNombre = vpNombre;
    }

    public String getVpDescrip() {
        return vpDescrip;
    }

    public void setVpDescrip(String vpDescrip) {
        this.vpDescrip = vpDescrip;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vpId != null ? vpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MruVariaciondepeso)) {
            return false;
        }
        MruVariaciondepeso other = (MruVariaciondepeso) object;
        if ((this.vpId == null && other.vpId != null) || (this.vpId != null && !this.vpId.equals(other.vpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return vpId + " - "+ vpNombre;
    }
    
}
