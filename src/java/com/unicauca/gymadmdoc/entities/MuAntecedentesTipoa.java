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
 * @author Alvaro Lasso
 */
@Entity
@Table(name = "mu_antecedentes_tipoa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuAntecedentesTipoa.findAll", query = "SELECT m FROM MuAntecedentesTipoa m"),
    @NamedQuery(name = "MuAntecedentesTipoa.findByAntaId", query = "SELECT m FROM MuAntecedentesTipoa m WHERE m.antaId = :antaId"),
    @NamedQuery(name = "MuAntecedentesTipoa.findByAntaPregunta", query = "SELECT m FROM MuAntecedentesTipoa m WHERE m.antaPregunta = :antaPregunta"),
    @NamedQuery(name = "MuAntecedentesTipoa.findByAntaRespuesta", query = "SELECT m FROM MuAntecedentesTipoa m WHERE m.antaRespuesta = :antaRespuesta"),
    @NamedQuery(name = "MuAntecedentesTipoa.findByAntaDescripcion", query = "SELECT m FROM MuAntecedentesTipoa m WHERE m.antaDescripcion = :antaDescripcion")})
public class MuAntecedentesTipoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ANTA_ID")
    private Integer antaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ANTA_PREGUNTA")
    private String antaPregunta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANTA_RESPUESTA")
    private boolean antaRespuesta;
    @Size(max = 100)
    @Column(name = "ANTA_DESCRIPCION")
    private String antaDescripcion;
    @JoinColumn(name = "ANSA_ID", referencedColumnName = "ANSA_ID")
    @ManyToOne(optional = false)
    private MuAntecedenteSalud ansaId;

    public MuAntecedentesTipoa() {
    }
    
    
  /*  private String antaDescripcion1;
       
    private String antaDescripcion2;

    public String getAntaDescripcion1() {
        return antaDescripcion1;
    }

    public void setAntaDescripcion1(String antaDescripcion1) {
        this.antaDescripcion1 = antaDescripcion1;
    }

    public String getAntaDescripcion2() {
        return antaDescripcion2;
    }

    public void setAntaDescripcion2(String antaDescripcion2) {
        this.antaDescripcion2 = antaDescripcion2;
    }*/
    
    
    

    public MuAntecedentesTipoa(Integer antaId) {
        this.antaId = antaId;
    }

    public MuAntecedentesTipoa(Integer antaId, String antaPregunta, boolean antaRespuesta) {
        this.antaId = antaId;
        this.antaPregunta = antaPregunta;
        this.antaRespuesta = antaRespuesta;
    }

    public Integer getAntaId() {
        return antaId;
    }

    public void setAntaId(Integer antaId) {
        this.antaId = antaId;
    }

    public String getAntaPregunta() {
        return antaPregunta;
    }

    public void setAntaPregunta(String antaPregunta) {
        this.antaPregunta = antaPregunta;
    }

    public boolean getAntaRespuesta() {
        return antaRespuesta;
    }

    public void setAntaRespuesta(boolean antaRespuesta) {
        this.antaRespuesta = antaRespuesta;
    }

    public String getAntaDescripcion() {
        return antaDescripcion;
    }

    public void setAntaDescripcion(String antaDescripcion) {
        this.antaDescripcion = antaDescripcion;
    }

    public MuAntecedenteSalud getAnsaId() {
        return ansaId;
    }

    public void setAnsaId(MuAntecedenteSalud ansaId) {
        
        
        this.ansaId = ansaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (antaId != null ? antaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuAntecedentesTipoa)) {
            return false;
        }
        MuAntecedentesTipoa other = (MuAntecedentesTipoa) object;
        if ((this.antaId == null && other.antaId != null) || (this.antaId != null && !this.antaId.equals(other.antaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.unicauca.gymdam.entidades.MuAntecedentesTipoa[ antaId=" + antaId + " ]";
    }
    
}
