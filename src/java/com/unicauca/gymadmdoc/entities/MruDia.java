/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Migdress
 */
@Entity
@Table(name = "mru_dia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MruDia.findAll", query = "SELECT m FROM MruDia m"),
    @NamedQuery(name = "MruDia.findByRuId", query = "SELECT m FROM MruDia m WHERE m.ruId.ruId = :ruId"),//Aquí se usa ruId.ruId porque si no recibe el objeto y no el id del objeto
    @NamedQuery(name = "MruDia.findByDiaId", query = "SELECT m FROM MruDia m WHERE m.diaId = :diaId"),
    @NamedQuery(name = "MruDia.findByDiaNumero", query = "SELECT m FROM MruDia m WHERE m.diaNumero = :diaNumero"),
    @NamedQuery(name = "MruDia.findByDiaTiempomin", query = "SELECT m FROM MruDia m WHERE m.diaTiempomin = :diaTiempomin")})
public class MruDia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIA_ID")
    private Integer diaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DIA_NUMERO")
    private String diaNumero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIA_TIEMPOMIN")
    @Temporal(TemporalType.TIME)
    private Date diaTiempomin;
    @JoinColumn(name = "RU_ID", referencedColumnName = "RU_ID")
    @ManyToOne(optional = false)
    private MruRutina ruId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diaId")
    private Collection<MruGrupomuscular> mruGrupomuscularCollection;

    public MruDia() {
        
    }

    public MruDia(Integer diaId) {
        this.diaId = diaId;
    }

    public MruDia(Integer diaId, String diaNumero, Date diaTiempomin) {
        this.diaId = diaId;
        this.diaNumero = diaNumero;
        this.diaTiempomin = diaTiempomin;
    }

    public Integer getDiaId() {
        return diaId;
    }

    public void setDiaId(Integer diaId) {
        this.diaId = diaId;
    }

    public String getDiaNumero() {
        return diaNumero;
    }

    public void setDiaNumero(String diaNumero) {
        this.diaNumero = diaNumero;
    }

    public Date getDiaTiempomin() {
        return diaTiempomin;
    }

    public void setDiaTiempomin(Date diaTiempomin) {
        this.diaTiempomin = diaTiempomin;
    }

    public MruRutina getRuId() {
        return ruId;
    }

    public void setRuId(MruRutina ruId) {
        this.ruId = ruId;
    }

    @XmlTransient
    public Collection<MruGrupomuscular> getMruGrupomuscularCollection() {
        return mruGrupomuscularCollection;
    }

    public void setMruGrupomuscularCollection(Collection<MruGrupomuscular> mruGrupomuscularCollection) {
        this.mruGrupomuscularCollection = mruGrupomuscularCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diaId != null ? diaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MruDia)) {
            return false;
        }
        MruDia other = (MruDia) object;
        if ((this.diaId == null && other.diaId != null) || (this.diaId != null && !this.diaId.equals(other.diaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unicauca.gymadmdoc.entities.MruDia[ diaId=" + diaId + " ]";
    }

}
