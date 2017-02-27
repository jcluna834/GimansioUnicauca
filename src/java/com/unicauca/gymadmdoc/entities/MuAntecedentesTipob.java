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
 * @author Alvaro Lasso
 */
@Entity
@Table(name = "mu_antecedentes_tipob")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuAntecedentesTipob.findAll", query = "SELECT m FROM MuAntecedentesTipob m"),
    @NamedQuery(name = "MuAntecedentesTipob.findByAntbId", query = "SELECT m FROM MuAntecedentesTipob m WHERE m.antbId = :antbId"),
    @NamedQuery(name = "MuAntecedentesTipob.findByAntbPregunta", query = "SELECT m FROM MuAntecedentesTipob m WHERE m.antbPregunta = :antbPregunta"),
    @NamedQuery(name = "MuAntecedentesTipob.findByantb_id", query = "SELECT m FROM MuAntecedentesTipob m WHERE m.ansaId = :id") })
public class MuAntecedentesTipob implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ANTB_ID")
    private Integer antbId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ANTB_PREGUNTA")
    private String antbPregunta;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Res_tipob")
    private String resTipob;
    
    @JoinColumn(name = "ANSA_ID", referencedColumnName = "ANSA_ID")
    @ManyToOne(optional = false)
    private MuAntecedenteSalud ansaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "antbId")
    private Collection<MuRespuestaTipob> muRespuestaTipobCollection;
    

    public MuAntecedentesTipob() {
    }

    public MuAntecedentesTipob(Integer antbId) {
        this.antbId = antbId;
    }

    public MuAntecedentesTipob(Integer antbId, String antbPregunta, String resTipob) {
        this.antbId = antbId;
        this.antbPregunta = antbPregunta;
        this.resTipob = resTipob;
    }

    
    
    public Integer getAntbId() {
        return antbId;
    }

    public void setAntbId(Integer antbId) {
        this.antbId = antbId;
    }

    public String getAntbPregunta() {
        return antbPregunta;
    }

    public void setAntbPregunta(String antbPregunta) {
        this.antbPregunta = antbPregunta;
    }

    public MuAntecedenteSalud getAnsaId() {
        return ansaId;
    }

    public void setAnsaId(MuAntecedenteSalud ansaId) {
        this.ansaId = ansaId;
    }

    public String getResTipob() {
        return resTipob;
    }

    public void setResTipob(String resTipob) {
        this.resTipob = resTipob;
    }
    
    

    @XmlTransient
    public Collection<MuRespuestaTipob> getMuRespuestaTipobCollection() {
        return muRespuestaTipobCollection;
    }

    public void setMuRespuestaTipobCollection(Collection<MuRespuestaTipob> muRespuestaTipobCollection) {
        this.muRespuestaTipobCollection = muRespuestaTipobCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (antbId != null ? antbId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuAntecedentesTipob)) {
            return false;
        }
        MuAntecedentesTipob other = (MuAntecedentesTipob) object;
        if ((this.antbId == null && other.antbId != null) || (this.antbId != null && !this.antbId.equals(other.antbId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.unicauca.gymdam.entidades.MuAntecedentesTipob[ antbId=" + antbId + " ]";
    }
    
}
