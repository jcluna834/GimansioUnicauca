/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Migdress
 */
@Entity
@Table(name = "grupomusc_realiza_ejercicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupomuscRealizaEjercicio.findAll", query = "SELECT g FROM GrupomuscRealizaEjercicio g"),
    @NamedQuery(name = "GrupomuscRealizaEjercicio.findByGmId", query = "SELECT g FROM GrupomuscRealizaEjercicio g WHERE g.grupomuscRealizaEjercicioPK.gmId = :gmId"),
    @NamedQuery(name = "GrupomuscRealizaEjercicio.findByEjId", query = "SELECT g FROM GrupomuscRealizaEjercicio g WHERE g.grupomuscRealizaEjercicioPK.ejId = :ejId"),
    @NamedQuery(name = "GrupomuscRealizaEjercicio.findByGreRepeticiones", query = "SELECT g FROM GrupomuscRealizaEjercicio g WHERE g.greRepeticiones = :greRepeticiones"),
    @NamedQuery(name = "GrupomuscRealizaEjercicio.findByGreTiempo", query = "SELECT g FROM GrupomuscRealizaEjercicio g WHERE g.greTiempo = :greTiempo"),
    @NamedQuery(name = "GrupomuscRealizaEjercicio.findByVariacionpeso", query = "SELECT g FROM GrupomuscRealizaEjercicio g WHERE g.variacionpeso = :variacionpeso")})
public class GrupomuscRealizaEjercicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GrupomuscRealizaEjercicioPK grupomuscRealizaEjercicioPK;
    @Column(name = "GRE_REPETICIONES")
    private Integer greRepeticiones;
    @Column(name = "GRE_TIEMPO")
    @Temporal(TemporalType.TIME)
    private Date greTiempo;
    @Size(max = 20)
    @Column(name = "VARIACIONPESO")
    private String variacionpeso;
    @JoinColumn(name = "EJ_ID", referencedColumnName = "EJ_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MruEjercicio mruEjercicio;
    @JoinColumn(name = "GM_ID", referencedColumnName = "GM_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MruGrupomuscular mruGrupomuscular;

    public GrupomuscRealizaEjercicio() {
    }

    public GrupomuscRealizaEjercicio(GrupomuscRealizaEjercicioPK grupomuscRealizaEjercicioPK) {
        this.grupomuscRealizaEjercicioPK = grupomuscRealizaEjercicioPK;
    }

    public GrupomuscRealizaEjercicio(int gmId, int ejId) {
        this.grupomuscRealizaEjercicioPK = new GrupomuscRealizaEjercicioPK(gmId, ejId);
    }

    public GrupomuscRealizaEjercicioPK getGrupomuscRealizaEjercicioPK() {
        return grupomuscRealizaEjercicioPK;
    }

    public void setGrupomuscRealizaEjercicioPK(GrupomuscRealizaEjercicioPK grupomuscRealizaEjercicioPK) {
        this.grupomuscRealizaEjercicioPK = grupomuscRealizaEjercicioPK;
    }

    public Integer getGreRepeticiones() {
        return greRepeticiones;
    }

    public void setGreRepeticiones(Integer greRepeticiones) {
        this.greRepeticiones = greRepeticiones;
    }

    public Date getGreTiempo() {
        return greTiempo;
    }

    public void setGreTiempo(Date greTiempo) {
        this.greTiempo = greTiempo;
    }

    public String getVariacionpeso() {
        return variacionpeso;
    }

    public void setVariacionpeso(String variacionpeso) {
        this.variacionpeso = variacionpeso;
    }

    public MruEjercicio getMruEjercicio() {
        return mruEjercicio;
    }

    public void setMruEjercicio(MruEjercicio mruEjercicio) {
        this.mruEjercicio = mruEjercicio;
    }

    public MruGrupomuscular getMruGrupomuscular() {
        return mruGrupomuscular;
    }

    public void setMruGrupomuscular(MruGrupomuscular mruGrupomuscular) {
        this.mruGrupomuscular = mruGrupomuscular;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grupomuscRealizaEjercicioPK != null ? grupomuscRealizaEjercicioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupomuscRealizaEjercicio)) {
            return false;
        }
        GrupomuscRealizaEjercicio other = (GrupomuscRealizaEjercicio) object;
        if ((this.grupomuscRealizaEjercicioPK == null && other.grupomuscRealizaEjercicioPK != null) || (this.grupomuscRealizaEjercicioPK != null && !this.grupomuscRealizaEjercicioPK.equals(other.grupomuscRealizaEjercicioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unicauca.gymadmdoc.entities.GrupomuscRealizaEjercicio[ grupomuscRealizaEjercicioPK=" + grupomuscRealizaEjercicioPK + " ]";
    }
    
}
