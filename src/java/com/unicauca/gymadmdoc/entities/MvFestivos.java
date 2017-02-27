package com.unicauca.gymadmdoc.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "mv_festivos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MvFestivos.findAll", query = "SELECT m FROM MvFestivos m"),
    @NamedQuery(name = "MvFestivos.findByDia", query = "SELECT m FROM MvFestivos m WHERE m.dia = :dia")})
public class MvFestivos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIA")
    @Temporal(TemporalType.DATE)
    private Date dia;

    public MvFestivos() {}

    public MvFestivos(Date dia) {
        this.dia = dia;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dia != null ? dia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MvFestivos)) {
            return false;
        }
        MvFestivos other = (MvFestivos) object;
        if ((this.dia == null && other.dia != null) || (this.dia != null && !this.dia.equals(other.dia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Festivo[" + dia + "]";
    }
    
}
