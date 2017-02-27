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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ROED26
 */
@Entity
@Table(name = "mu_fuerza_muscular")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuFuerzaMuscular.findAll", query = "SELECT m FROM MuFuerzaMuscular m"),
    @NamedQuery(name = "MuFuerzaMuscular.findByFumusId", query = "SELECT m FROM MuFuerzaMuscular m WHERE m.fumusId = :fumusId"),
    @NamedQuery(name = "MuFuerzaMuscular.findByFumusBancoPecho", query = "SELECT m FROM MuFuerzaMuscular m WHERE m.fumusBancoPecho = :fumusBancoPecho"),
    @NamedQuery(name = "MuFuerzaMuscular.findByFumusExtMaquina", query = "SELECT m FROM MuFuerzaMuscular m WHERE m.fumusExtMaquina = :fumusExtMaquina"),
    @NamedQuery(name = "MuFuerzaMuscular.findByFumusLatMaquina", query = "SELECT m FROM MuFuerzaMuscular m WHERE m.fumusLatMaquina = :fumusLatMaquina"),
    @NamedQuery(name = "MuFuerzaMuscular.findByFumusCurlFemoral", query = "SELECT m FROM MuFuerzaMuscular m WHERE m.fumusCurlFemoral = :fumusCurlFemoral"),
    @NamedQuery(name = "MuFuerzaMuscular.findByFumusCurlBiceps", query = "SELECT m FROM MuFuerzaMuscular m WHERE m.fumusCurlBiceps = :fumusCurlBiceps"),
    @NamedQuery(name = "MuFuerzaMuscular.findByFumusCalfPantorrilla", query = "SELECT m FROM MuFuerzaMuscular m WHERE m.fumusCalfPantorrilla = :fumusCalfPantorrilla"),
    @NamedQuery(name = "MuFuerzaMuscular.findByFumusTricepsPolea", query = "SELECT m FROM MuFuerzaMuscular m WHERE m.fumusTricepsPolea = :fumusTricepsPolea"),
    @NamedQuery(name = "MuFuerzaMuscular.findByFumusExtGluteo", query = "SELECT m FROM MuFuerzaMuscular m WHERE m.fumusExtGluteo = :fumusExtGluteo")})
public class MuFuerzaMuscular implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FUMUS_ID")
    private Integer fumusId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FUMUS_BANCO_PECHO")
    private float fumusBancoPecho;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FUMUS_EXT_MAQUINA")
    private float fumusExtMaquina;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FUMUS_LAT_MAQUINA")
    private float fumusLatMaquina;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FUMUS_CURL_FEMORAL")
    private float fumusCurlFemoral;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FUMUS_CURL_BICEPS")
    private float fumusCurlBiceps;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FUMUS_CALF_PANTORRILLA")
    private float fumusCalfPantorrilla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FUMUS_TRICEPS_POLEA")
    private float fumusTricepsPolea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FUMUS_EXT_GLUTEO")
    private float fumusExtGluteo;
    @JoinColumn(name = "EVAL_ID", referencedColumnName = "EVAL_ID")
    @ManyToOne(optional = false)
    private MuEvaluacion evalId;

    public MuFuerzaMuscular() {
    }

    public MuFuerzaMuscular(Integer fumusId) {
        this.fumusId = fumusId;
    }

    public MuFuerzaMuscular(Integer fumusId, float fumusBancoPecho, float fumusExtMaquina, float fumusLatMaquina, float fumusCurlFemoral, float fumusCurlBiceps, float fumusCalfPantorrilla, float fumusTricepsPolea, float fumusExtGluteo) {
        this.fumusId = fumusId;
        this.fumusBancoPecho = fumusBancoPecho;
        this.fumusExtMaquina = fumusExtMaquina;
        this.fumusLatMaquina = fumusLatMaquina;
        this.fumusCurlFemoral = fumusCurlFemoral;
        this.fumusCurlBiceps = fumusCurlBiceps;
        this.fumusCalfPantorrilla = fumusCalfPantorrilla;
        this.fumusTricepsPolea = fumusTricepsPolea;
        this.fumusExtGluteo = fumusExtGluteo;
    }

    public Integer getFumusId() {
        return fumusId;
    }

    public void setFumusId(Integer fumusId) {
        this.fumusId = fumusId;
    }

    public float getFumusBancoPecho() {
        return fumusBancoPecho;
    }

    public void setFumusBancoPecho(float fumusBancoPecho) {
        this.fumusBancoPecho = fumusBancoPecho;
    }

    public float getFumusExtMaquina() {
        return fumusExtMaquina;
    }

    public void setFumusExtMaquina(float fumusExtMaquina) {
        this.fumusExtMaquina = fumusExtMaquina;
    }

    public float getFumusLatMaquina() {
        return fumusLatMaquina;
    }

    public void setFumusLatMaquina(float fumusLatMaquina) {
        this.fumusLatMaquina = fumusLatMaquina;
    }

    public float getFumusCurlFemoral() {
        return fumusCurlFemoral;
    }

    public void setFumusCurlFemoral(float fumusCurlFemoral) {
        this.fumusCurlFemoral = fumusCurlFemoral;
    }

    public float getFumusCurlBiceps() {
        return fumusCurlBiceps;
    }

    public void setFumusCurlBiceps(float fumusCurlBiceps) {
        this.fumusCurlBiceps = fumusCurlBiceps;
    }

    public float getFumusCalfPantorrilla() {
        return fumusCalfPantorrilla;
    }

    public void setFumusCalfPantorrilla(float fumusCalfPantorrilla) {
        this.fumusCalfPantorrilla = fumusCalfPantorrilla;
    }

    public float getFumusTricepsPolea() {
        return fumusTricepsPolea;
    }

    public void setFumusTricepsPolea(float fumusTricepsPolea) {
        this.fumusTricepsPolea = fumusTricepsPolea;
    }

    public float getFumusExtGluteo() {
        return fumusExtGluteo;
    }

    public void setFumusExtGluteo(float fumusExtGluteo) {
        this.fumusExtGluteo = fumusExtGluteo;
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
        hash += (fumusId != null ? fumusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuFuerzaMuscular)) {
            return false;
        }
        MuFuerzaMuscular other = (MuFuerzaMuscular) object;
        if ((this.fumusId == null && other.fumusId != null) || (this.fumusId != null && !this.fumusId.equals(other.fumusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.gymadmdoc.entities.MuFuerzaMuscular[ fumusId=" + fumusId + " ]";
    }
    
}
