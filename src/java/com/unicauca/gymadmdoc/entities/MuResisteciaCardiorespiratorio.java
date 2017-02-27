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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ROED26
 */
@Entity
@Table(name = "mu_resistecia_cardiorespiratorio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuResisteciaCardiorespiratorio.findAll", query = "SELECT m FROM MuResisteciaCardiorespiratorio m"),
    @NamedQuery(name = "MuResisteciaCardiorespiratorio.findByRescaId", query = "SELECT m FROM MuResisteciaCardiorespiratorio m WHERE m.rescaId = :rescaId"),
    @NamedQuery(name = "MuResisteciaCardiorespiratorio.findByRescaFrecuenciaReposo", query = "SELECT m FROM MuResisteciaCardiorespiratorio m WHERE m.rescaFrecuenciaReposo = :rescaFrecuenciaReposo"),
    @NamedQuery(name = "MuResisteciaCardiorespiratorio.findByRescaFrecuenciaMin", query = "SELECT m FROM MuResisteciaCardiorespiratorio m WHERE m.rescaFrecuenciaMin = :rescaFrecuenciaMin"),
    @NamedQuery(name = "MuResisteciaCardiorespiratorio.findByRescaClasificacion", query = "SELECT m FROM MuResisteciaCardiorespiratorio m WHERE m.rescaClasificacion = :rescaClasificacion")})
public class MuResisteciaCardiorespiratorio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RESCA_ID")
    private Integer rescaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RESCA_FRECUENCIA_REPOSO")
    private float rescaFrecuenciaReposo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RESCA_FRECUENCIA_MIN")
    private float rescaFrecuenciaMin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "RESCA_CLASIFICACION")
    private String rescaClasificacion;
    @JoinColumn(name = "EVAL_ID", referencedColumnName = "EVAL_ID")
    @ManyToOne(optional = false)
    private MuEvaluacion evalId;

    public MuResisteciaCardiorespiratorio() {
    }

    public MuResisteciaCardiorespiratorio(Integer rescaId) {
        this.rescaId = rescaId;
    }

    public MuResisteciaCardiorespiratorio(Integer rescaId, float rescaFrecuenciaReposo, float rescaFrecuenciaMin, String rescaClasificacion) {
        this.rescaId = rescaId;
        this.rescaFrecuenciaReposo = rescaFrecuenciaReposo;
        this.rescaFrecuenciaMin = rescaFrecuenciaMin;
        this.rescaClasificacion = rescaClasificacion;
    }

    public Integer getRescaId() {
        return rescaId;
    }

    public void setRescaId(Integer rescaId) {
        this.rescaId = rescaId;
    }

    public float getRescaFrecuenciaReposo() {
        return rescaFrecuenciaReposo;
    }

    public void setRescaFrecuenciaReposo(float rescaFrecuenciaReposo) {
        this.rescaFrecuenciaReposo = rescaFrecuenciaReposo;
    }

    public float getRescaFrecuenciaMin() {
        return rescaFrecuenciaMin;
    }

    public void setRescaFrecuenciaMin(float rescaFrecuenciaMin) {
        this.rescaFrecuenciaMin = rescaFrecuenciaMin;
    }

    public String getRescaClasificacion() {
        return rescaClasificacion;
    }

    public void setRescaClasificacion(String rescaClasificacion) {
        this.rescaClasificacion = rescaClasificacion;
    }

    public MuEvaluacion getEvalId() {
        return evalId;
    }

    public void setEvalId(MuEvaluacion evalId) {
        this.evalId = evalId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rescaId != null ? rescaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuResisteciaCardiorespiratorio)) {
            return false;
        }
        MuResisteciaCardiorespiratorio other = (MuResisteciaCardiorespiratorio) object;
        if ((this.rescaId == null && other.rescaId != null) || (this.rescaId != null && !this.rescaId.equals(other.rescaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.gymadmdoc.entities.MuResisteciaCardiorespiratorio[ rescaId=" + rescaId + " ]";
    }
    
}
