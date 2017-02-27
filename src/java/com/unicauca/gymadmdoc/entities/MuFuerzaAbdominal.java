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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "mu_fuerza_abdominal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuFuerzaAbdominal.findAll", query = "SELECT m FROM MuFuerzaAbdominal m"),
    @NamedQuery(name = "MuFuerzaAbdominal.findByFuabId", query = "SELECT m FROM MuFuerzaAbdominal m WHERE m.fuabId = :fuabId"),
    @NamedQuery(name = "MuFuerzaAbdominal.findByFuabSitUp", query = "SELECT m FROM MuFuerzaAbdominal m WHERE m.fuabSitUp = :fuabSitUp"),
    @NamedQuery(name = "MuFuerzaAbdominal.findByFuabPlancha", query = "SELECT m FROM MuFuerzaAbdominal m WHERE m.fuabPlancha = :fuabPlancha"),
    @NamedQuery(name = "MuFuerzaAbdominal.findByFuabClasificacion", query = "SELECT m FROM MuFuerzaAbdominal m WHERE m.fuabClasificacion = :fuabClasificacion")})
public class MuFuerzaAbdominal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FUAB_ID")
    private Integer fuabId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FUAB_SIT_UP")
    private float fuabSitUp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FUAB_PLANCHA")
    private float fuabPlancha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "FUAB_CLASIFICACION")
    private String fuabClasificacion;
    @JoinColumn(name = "EVAL_ID", referencedColumnName = "EVAL_ID")
    @ManyToOne(optional = false)
    private MuEvaluacion evalId;

    public MuFuerzaAbdominal() {
    }

    public MuFuerzaAbdominal(Integer fuabId) {
        this.fuabId = fuabId;
    }

    public MuFuerzaAbdominal(Integer fuabId, float fuabSitUp, float fuabPlancha, String fuabClasificacion) {
        this.fuabId = fuabId;
        this.fuabSitUp = fuabSitUp;
        this.fuabPlancha = fuabPlancha;
        this.fuabClasificacion = fuabClasificacion;
    }

    public Integer getFuabId() {
        return fuabId;
    }

    public void setFuabId(Integer fuabId) {
        this.fuabId = fuabId;
    }

    public float getFuabSitUp() {
        return fuabSitUp;
    }

    public void setFuabSitUp(float fuabSitUp) {
        this.fuabSitUp = fuabSitUp;
    }

    public float getFuabPlancha() {
        return fuabPlancha;
    }

    public void setFuabPlancha(float fuabPlancha) {
        this.fuabPlancha = fuabPlancha;
    }

    public String getFuabClasificacion() {
        return fuabClasificacion;
    }

    public void setFuabClasificacion(String fuabClasificacion) {
        this.fuabClasificacion = fuabClasificacion;
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
        hash += (fuabId != null ? fuabId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuFuerzaAbdominal)) {
            return false;
        }
        MuFuerzaAbdominal other = (MuFuerzaAbdominal) object;
        if ((this.fuabId == null && other.fuabId != null) || (this.fuabId != null && !this.fuabId.equals(other.fuabId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.gymadmdoc.entities.MuFuerzaAbdominal[ fuabId=" + fuabId + " ]";
    }
    
}
