package com.unicauca.gymadmdoc.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CristianCamilo
 */
@Entity
@Table(name = "mv_siguientevaloracion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MvSiguientevaloracion.findAll", query = "SELECT m FROM MvSiguientevaloracion m"),
    @NamedQuery(name = "MvSiguientevaloracion.findBySigId", query = "SELECT m FROM MvSiguientevaloracion m WHERE m.sigId = :sigId"),
    @NamedQuery(name = "MvSiguientevaloracion.findBySigCliente", query = "SELECT m FROM MvSiguientevaloracion m WHERE m.sigCliente = :sigCliente"),
    @NamedQuery(name = "MvSiguientevaloracion.findBySigFecha", query = "SELECT m FROM MvSiguientevaloracion m WHERE m.sigFecha = :sigFecha")})
public class MvSiguientevaloracion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SIG_ID")
    private Integer sigId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SIG_CLIENTE")
    private int sigCliente;
    @Column(name = "SIG_FECHA")
    @Temporal(TemporalType.DATE)
    private Date sigFecha;

    public MvSiguientevaloracion() {
    }

    public MvSiguientevaloracion(Integer sigId) {
        this.sigId = sigId;
    }

    public MvSiguientevaloracion(Integer sigId, int sigCliente) {
        this.sigId = sigId;
        this.sigCliente = sigCliente;
    }

    public Integer getSigId() {
        return sigId;
    }

    public void setSigId(Integer sigId) {
        this.sigId = sigId;
    }

    public int getSigCliente() {
        return sigCliente;
    }

    public void setSigCliente(int sigCliente) {
        this.sigCliente = sigCliente;
    }

    public Date getSigFecha() {
        return sigFecha;
    }

    public void setSigFecha(Date sigFecha) {
        this.sigFecha = sigFecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sigId != null ? sigId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MvSiguientevaloracion)) {
            return false;
        }
        MvSiguientevaloracion other = (MvSiguientevaloracion) object;
        if ((this.sigId == null && other.sigId != null) || (this.sigId != null && !this.sigId.equals(other.sigId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SiguienteValoracion[" + sigId + "]";
    }
    
}
