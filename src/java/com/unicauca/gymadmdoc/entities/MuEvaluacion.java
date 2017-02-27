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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ROED26
 */
@Entity
@Table(name = "mu_evaluacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuEvaluacion.findAll", query = "SELECT m FROM MuEvaluacion m"),
    @NamedQuery(name = "MuEvaluacion.findByEvalId", query = "SELECT m FROM MuEvaluacion m WHERE m.evalId = :evalId"),
    @NamedQuery(name = "MuEvaluacion.findByEvalObservacion", query = "SELECT m FROM MuEvaluacion m WHERE m.evalObservacion = :evalObservacion")})
public class MuEvaluacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EVAL_ID")
    private Long evalId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "EVAL_OBSERVACION")
    private String evalObservacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evalId")
    private Collection<MuResisteciaCardiorespiratorio> muResisteciaCardiorespiratorioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evalId")
    private Collection<MuFuerzaMuscular> muFuerzaMuscularCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evalId")
    private Collection<MuFlexibilidad> muFlexibilidadCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evalId")
    private Collection<MuTestPropiocepcion> muTestPropiocepcionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evalId")
    private Collection<MuFuerzaAbdominal> muFuerzaAbdominalCollection;
    @JoinColumn(name = "USU_IDENTIFICACION", referencedColumnName = "USU_IDENTIFICACION")
    @ManyToOne(optional = false)
    private MuUsuario usuIdentificacion;

    public MuEvaluacion() {
    }

    public MuEvaluacion(Long evalId) {
        this.evalId = evalId;
    }

    public MuEvaluacion(Long evalId, String evalObservacion) {
        this.evalId = evalId;
        this.evalObservacion = evalObservacion;
    }

    public Long getEvalId() {
        return evalId;
    }

    public void setEvalId(Long evalId) {
        this.evalId = evalId;
    }

    public String getEvalObservacion() {
        return evalObservacion;
    }

    public void setEvalObservacion(String evalObservacion) {
        this.evalObservacion = evalObservacion;
    }

    @XmlTransient
    public Collection<MuResisteciaCardiorespiratorio> getMuResisteciaCardiorespiratorioCollection() {
        return muResisteciaCardiorespiratorioCollection;
    }

    public void setMuResisteciaCardiorespiratorioCollection(Collection<MuResisteciaCardiorespiratorio> muResisteciaCardiorespiratorioCollection) {
        this.muResisteciaCardiorespiratorioCollection = muResisteciaCardiorespiratorioCollection;
    }

    @XmlTransient
    public Collection<MuFuerzaMuscular> getMuFuerzaMuscularCollection() {
        return muFuerzaMuscularCollection;
    }

    public void setMuFuerzaMuscularCollection(Collection<MuFuerzaMuscular> muFuerzaMuscularCollection) {
        this.muFuerzaMuscularCollection = muFuerzaMuscularCollection;
    }

    @XmlTransient
    public Collection<MuFlexibilidad> getMuFlexibilidadCollection() {
        return muFlexibilidadCollection;
    }

    public void setMuFlexibilidadCollection(Collection<MuFlexibilidad> muFlexibilidadCollection) {
        this.muFlexibilidadCollection = muFlexibilidadCollection;
    }

    @XmlTransient
    public Collection<MuTestPropiocepcion> getMuTestPropiocepcionCollection() {
        return muTestPropiocepcionCollection;
    }

    public void setMuTestPropiocepcionCollection(Collection<MuTestPropiocepcion> muTestPropiocepcionCollection) {
        this.muTestPropiocepcionCollection = muTestPropiocepcionCollection;
    }

    @XmlTransient
    public Collection<MuFuerzaAbdominal> getMuFuerzaAbdominalCollection() {
        return muFuerzaAbdominalCollection;
    }

    public void setMuFuerzaAbdominalCollection(Collection<MuFuerzaAbdominal> muFuerzaAbdominalCollection) {
        this.muFuerzaAbdominalCollection = muFuerzaAbdominalCollection;
    }

    public MuUsuario getUsuIdentificacion() {
        return usuIdentificacion;
    }

    public void setUsuIdentificacion(MuUsuario usuIdentificacion) {
        this.usuIdentificacion = usuIdentificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evalId != null ? evalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuEvaluacion)) {
            return false;
        }
        MuEvaluacion other = (MuEvaluacion) object;
        if ((this.evalId == null && other.evalId != null) || (this.evalId != null && !this.evalId.equals(other.evalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.gymadmdoc.entities.MuEvaluacion[ evalId=" + evalId + " ]";
    }
    
}
