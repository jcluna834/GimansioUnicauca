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
 * @author ROED26
 */
@Entity
@Table(name = "mu_flexibilidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuFlexibilidad.findAll", query = "SELECT m FROM MuFlexibilidad m"),
    @NamedQuery(name = "MuFlexibilidad.findByFlexId", query = "SELECT m FROM MuFlexibilidad m WHERE m.flexId = :flexId")})
public class MuFlexibilidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FLEX_ID")
    private Integer flexId;
    @JoinColumn(name = "EVAL_ID", referencedColumnName = "EVAL_ID")
    @ManyToOne(optional = false)
    private MuEvaluacion evalId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flexId")
    private Collection<MuTestHombro> muTestHombroCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flexId")
    private Collection<MuTestWells> muTestWellsCollection;

    public MuFlexibilidad() {
    }

    public MuFlexibilidad(Integer flexId) {
        this.flexId = flexId;
    }

    public Integer getFlexId() {
        return flexId;
    }

    public void setFlexId(Integer flexId) {
        this.flexId = flexId;
    }

    public MuEvaluacion getEvalId() {
        return evalId;
    }

    public void setEvalId(MuEvaluacion evalId) {
        this.evalId = evalId;
    }

    @XmlTransient
    public Collection<MuTestHombro> getMuTestHombroCollection() {
        return muTestHombroCollection;
    }

    public void setMuTestHombroCollection(Collection<MuTestHombro> muTestHombroCollection) {
        this.muTestHombroCollection = muTestHombroCollection;
    }

    @XmlTransient
    public Collection<MuTestWells> getMuTestWellsCollection() {
        return muTestWellsCollection;
    }

    public void setMuTestWellsCollection(Collection<MuTestWells> muTestWellsCollection) {
        this.muTestWellsCollection = muTestWellsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flexId != null ? flexId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuFlexibilidad)) {
            return false;
        }
        MuFlexibilidad other = (MuFlexibilidad) object;
        if ((this.flexId == null && other.flexId != null) || (this.flexId != null && !this.flexId.equals(other.flexId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.gymadmdoc.entities.MuFlexibilidad[ flexId=" + flexId + " ]";
    }
    
}
