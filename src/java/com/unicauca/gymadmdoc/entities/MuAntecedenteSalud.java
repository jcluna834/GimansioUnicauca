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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alvaro Lasso
 */
@Entity
@Table(name = "mu_antecedente_salud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuAntecedenteSalud.findAll", query = "SELECT m FROM MuAntecedenteSalud m"),
    @NamedQuery(name = "MuAntecedenteSalud.findByAnsaId", query = "SELECT m FROM MuAntecedenteSalud m WHERE m.ansaId = :ansaId"),
    @NamedQuery(name = "MuAntecedenteSalud.findByusuIdentificacion", query = "SELECT m FROM MuAntecedenteSalud m WHERE m.usuIdentificacion = :id")})
public class MuAntecedenteSalud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ANSA_ID")
    private Integer ansaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ansaId")
    private Collection<MuAntecedentesTipob> muAntecedentesTipobCollection;
    @JoinColumn(name = "USU_IDENTIFICACION", referencedColumnName = "USU_IDENTIFICACION")
    @ManyToOne(optional = false)
    private MuUsuario usuIdentificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ansaId")
    private Collection<MuAntecedentesTipoa> muAntecedentesTipoaCollection;

    public MuAntecedenteSalud() {
    }

    public MuAntecedenteSalud(Integer ansaId) {
        this.ansaId = ansaId;
    }

    public Integer getAnsaId() {
        return ansaId;
    }

    public void setAnsaId(Integer ansaId) {
        this.ansaId = ansaId;
    }

    @XmlTransient
    public Collection<MuAntecedentesTipob> getMuAntecedentesTipobCollection() {
        return muAntecedentesTipobCollection;
    }

    public void setMuAntecedentesTipobCollection(Collection<MuAntecedentesTipob> muAntecedentesTipobCollection) {
        this.muAntecedentesTipobCollection = muAntecedentesTipobCollection;
    }

    public MuUsuario getUsuIdentificacion() {
        return usuIdentificacion;
    }

    public void setUsuIdentificacion(MuUsuario usuIdentificacion) {
        this.usuIdentificacion = usuIdentificacion;
    }

    @XmlTransient
    public Collection<MuAntecedentesTipoa> getMuAntecedentesTipoaCollection() {
        return muAntecedentesTipoaCollection;
    }

    public void setMuAntecedentesTipoaCollection(Collection<MuAntecedentesTipoa> muAntecedentesTipoaCollection) {
        this.muAntecedentesTipoaCollection = muAntecedentesTipoaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ansaId != null ? ansaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuAntecedenteSalud)) {
            return false;
        }
        MuAntecedenteSalud other = (MuAntecedenteSalud) object;
        if ((this.ansaId == null && other.ansaId != null) || (this.ansaId != null && !this.ansaId.equals(other.ansaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.unicauca.gymdam.entidades.MuAntecedenteSalud[ ansaId=" + ansaId + " ]";
    }
    
}
