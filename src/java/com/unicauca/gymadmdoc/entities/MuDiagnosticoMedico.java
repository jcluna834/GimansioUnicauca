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
@Table(name = "mu_diagnostico_medico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuDiagnosticoMedico.findAll", query = "SELECT m FROM MuDiagnosticoMedico m"),
    @NamedQuery(name = "MuDiagnosticoMedico.findByDimedId", query = "SELECT m FROM MuDiagnosticoMedico m WHERE m.dimedId = :dimedId"),
    @NamedQuery(name = "MuDiagnosticoMedico.findByDimedDescripcion", query = "SELECT m FROM MuDiagnosticoMedico m WHERE m.dimedDescripcion = :dimedDescripcion")})
public class MuDiagnosticoMedico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIMED_ID")
    private Long dimedId;
    @Size(max = 250)
    @Column(name = "DIMED_DESCRIPCION")
    private String dimedDescripcion;
    @JoinColumn(name = "USU_IDENTIFICACION", referencedColumnName = "USU_IDENTIFICACION")
    @ManyToOne(optional = false)
    private MuUsuario usuIdentificacion;

    public MuDiagnosticoMedico() {
    }

    public MuDiagnosticoMedico(Long dimedId) {
        this.dimedId = dimedId;
    }

    public Long getDimedId() {
        return dimedId;
    }

    public void setDimedId(Long dimedId) {
        this.dimedId = dimedId;
    }

    public String getDimedDescripcion() {
        return dimedDescripcion;
    }

    public void setDimedDescripcion(String dimedDescripcion) {
        this.dimedDescripcion = dimedDescripcion;
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
        hash += (dimedId != null ? dimedId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuDiagnosticoMedico)) {
            return false;
        }
        MuDiagnosticoMedico other = (MuDiagnosticoMedico) object;
        if ((this.dimedId == null && other.dimedId != null) || (this.dimedId != null && !this.dimedId.equals(other.dimedId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.gymadmdoc.entities.MuDiagnosticoMedico[ dimedId=" + dimedId + " ]";
    }
    
}
