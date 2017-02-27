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
@Table(name = "mu_respuesta_tipob")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuRespuestaTipob.findAll", query = "SELECT m FROM MuRespuestaTipob m"),
    @NamedQuery(name = "MuRespuestaTipob.findByRetbId", query = "SELECT m FROM MuRespuestaTipob m WHERE m.retbId = :retbId"),
    @NamedQuery(name = "MuRespuestaTipob.findByRetbRespuesta", query = "SELECT m FROM MuRespuestaTipob m WHERE m.retbRespuesta = :retbRespuesta")})
public class MuRespuestaTipob implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RETB_ID")
    private Integer retbId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "RETB_RESPUESTA")
    private String retbRespuesta;
    @JoinColumn(name = "ANTB_ID", referencedColumnName = "ANTB_ID")
    @ManyToOne(optional = false)
    private MuAntecedentesTipob antbId;

    public MuRespuestaTipob() {
    }

    public MuRespuestaTipob(Integer retbId) {
        this.retbId = retbId;
    }

    public MuRespuestaTipob(Integer retbId, String retbRespuesta) {
        this.retbId = retbId;
        this.retbRespuesta = retbRespuesta;
    }

    public Integer getRetbId() {
        return retbId;
    }

    public void setRetbId(Integer retbId) {
        this.retbId = retbId;
    }

    public String getRetbRespuesta() {
        return retbRespuesta;
    }

    public void setRetbRespuesta(String retbRespuesta) {
        this.retbRespuesta = retbRespuesta;
    }

    public MuAntecedentesTipob getAntbId() {
        return antbId;
    }

    public void setAntbId(MuAntecedentesTipob antbId) {
        this.antbId = antbId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (retbId != null ? retbId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuRespuestaTipob)) {
            return false;
        }
        MuRespuestaTipob other = (MuRespuestaTipob) object;
        if ((this.retbId == null && other.retbId != null) || (this.retbId != null && !this.retbId.equals(other.retbId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.gymadmdoc.entities.MuRespuestaTipob[ retbId=" + retbId + " ]";
    }
    
}
