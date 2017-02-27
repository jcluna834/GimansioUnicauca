/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ROED26
 */
@Entity
@Table(name = "mu_examen_fisico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuExamenFisico.findAll", query = "SELECT m FROM MuExamenFisico m"),
    @NamedQuery(name = "MuExamenFisico.findByEfisId", query = "SELECT m FROM MuExamenFisico m WHERE m.efisId = :efisId"),
    @NamedQuery(name = "MuExamenFisico.findByEfisFecha", query = "SELECT m FROM MuExamenFisico m WHERE m.efisFecha = :efisFecha"),
    @NamedQuery(name = "MuExamenFisico.findByEfisPeso", query = "SELECT m FROM MuExamenFisico m WHERE m.efisPeso = :efisPeso"),
    @NamedQuery(name = "MuExamenFisico.findByEfisTalla", query = "SELECT m FROM MuExamenFisico m WHERE m.efisTalla = :efisTalla"),
    @NamedQuery(name = "MuExamenFisico.findByEfisFc", query = "SELECT m FROM MuExamenFisico m WHERE m.efisFc = :efisFc"),
    @NamedQuery(name = "MuExamenFisico.findByEfisFcm", query = "SELECT m FROM MuExamenFisico m WHERE m.efisFcm = :efisFcm"),
    @NamedQuery(name = "MuExamenFisico.findByEfisTa", query = "SELECT m FROM MuExamenFisico m WHERE m.efisTa = :efisTa"),
    @NamedQuery(name = "MuExamenFisico.findByEfisFr", query = "SELECT m FROM MuExamenFisico m WHERE m.efisFr = :efisFr"),
    @NamedQuery(name = "MuExamenFisico.findByEfisImc", query = "SELECT m FROM MuExamenFisico m WHERE m.efisImc = :efisImc"),
    @NamedQuery(name = "MuExamenFisico.findByEfisIcc", query = "SELECT m FROM MuExamenFisico m WHERE m.efisIcc = :efisIcc")})
public class MuExamenFisico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EFIS_ID")
    private Long efisId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EFIS_FECHA")
    @Temporal(TemporalType.DATE)
    private Date efisFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EFIS_PESO")
    private float efisPeso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EFIS_TALLA")
    private float efisTalla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EFIS_FC")
    private float efisFc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EFIS_FCM")
    private float efisFcm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EFIS_TA")
    private float efisTa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EFIS_FR")
    private float efisFr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EFIS_IMC")
    private float efisImc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EFIS_ICC")
    private float efisIcc;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "EFIS_FOTO_CUERPO_COMPLETO_FRENTE")
    private byte[] efisFotoCuerpoCompletoFrente;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "EFIS_FOTO_CUERPO_COMPLETO_ESPALDA")
    private byte[] efisFotoCuerpoCompletoEspalda;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "EFIS_FOTO_CUERPO_COMPLETO_PERFIL_DER")
    private byte[] efisFotoCuerpoCompletoPerfilDer;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "EFIS_FOTO_CUERPO_COMPLETO_PERFIL_IZQ")
    private byte[] efisFotoCuerpoCompletoPerfilIzq;
    @JoinColumn(name = "USU_IDENTIFICACION", referencedColumnName = "USU_IDENTIFICACION")
    @ManyToOne(optional = false)
    private MuUsuario usuIdentificacion;

    public MuExamenFisico() {
    }

    public MuExamenFisico(Long efisId) {
        this.efisId = efisId;
    }

    public MuExamenFisico(Long efisId, Date efisFecha, float efisPeso, float efisTalla, float efisFc, float efisFcm, float efisTa, float efisFr, float efisImc, float efisIcc, byte[] efisFotoCuerpoCompletoFrente, byte[] efisFotoCuerpoCompletoEspalda, byte[] efisFotoCuerpoCompletoPerfilDer, byte[] efisFotoCuerpoCompletoPerfilIzq) {
        this.efisId = efisId;
        this.efisFecha = efisFecha;
        this.efisPeso = efisPeso;
        this.efisTalla = efisTalla;
        this.efisFc = efisFc;
        this.efisFcm = efisFcm;
        this.efisTa = efisTa;
        this.efisFr = efisFr;
        this.efisImc = efisImc;
        this.efisIcc = efisIcc;
        this.efisFotoCuerpoCompletoFrente = efisFotoCuerpoCompletoFrente;
        this.efisFotoCuerpoCompletoEspalda = efisFotoCuerpoCompletoEspalda;
        this.efisFotoCuerpoCompletoPerfilDer = efisFotoCuerpoCompletoPerfilDer;
        this.efisFotoCuerpoCompletoPerfilIzq = efisFotoCuerpoCompletoPerfilIzq;
    }

    public Long getEfisId() {
        return efisId;
    }

    public void setEfisId(Long efisId) {
        this.efisId = efisId;
    }

    public Date getEfisFecha() {
        return efisFecha;
    }

    public void setEfisFecha(Date efisFecha) {
        this.efisFecha = efisFecha;
    }

    public float getEfisPeso() {
        return efisPeso;
    }

    public void setEfisPeso(float efisPeso) {
        this.efisPeso = efisPeso;
    }

    public float getEfisTalla() {
        return efisTalla;
    }

    public void setEfisTalla(float efisTalla) {
        this.efisTalla = efisTalla;
    }

    public float getEfisFc() {
        return efisFc;
    }

    public void setEfisFc(float efisFc) {
        this.efisFc = efisFc;
    }

    public float getEfisFcm() {
        return efisFcm;
    }

    public void setEfisFcm(float efisFcm) {
        this.efisFcm = efisFcm;
    }

    public float getEfisTa() {
        return efisTa;
    }

    public void setEfisTa(float efisTa) {
        this.efisTa = efisTa;
    }

    public float getEfisFr() {
        return efisFr;
    }

    public void setEfisFr(float efisFr) {
        this.efisFr = efisFr;
    }

    public float getEfisImc() {
        return efisImc;
    }

    public void setEfisImc(float efisImc) {
        this.efisImc = efisImc;
    }

    public float getEfisIcc() {
        return efisIcc;
    }

    public void setEfisIcc(float efisIcc) {
        this.efisIcc = efisIcc;
    }

    public byte[] getEfisFotoCuerpoCompletoFrente() {
        return efisFotoCuerpoCompletoFrente;
    }

    public void setEfisFotoCuerpoCompletoFrente(byte[] efisFotoCuerpoCompletoFrente) {
        this.efisFotoCuerpoCompletoFrente = efisFotoCuerpoCompletoFrente;
    }

    public byte[] getEfisFotoCuerpoCompletoEspalda() {
        return efisFotoCuerpoCompletoEspalda;
    }

    public void setEfisFotoCuerpoCompletoEspalda(byte[] efisFotoCuerpoCompletoEspalda) {
        this.efisFotoCuerpoCompletoEspalda = efisFotoCuerpoCompletoEspalda;
    }

    public byte[] getEfisFotoCuerpoCompletoPerfilDer() {
        return efisFotoCuerpoCompletoPerfilDer;
    }

    public void setEfisFotoCuerpoCompletoPerfilDer(byte[] efisFotoCuerpoCompletoPerfilDer) {
        this.efisFotoCuerpoCompletoPerfilDer = efisFotoCuerpoCompletoPerfilDer;
    }

    public byte[] getEfisFotoCuerpoCompletoPerfilIzq() {
        return efisFotoCuerpoCompletoPerfilIzq;
    }

    public void setEfisFotoCuerpoCompletoPerfilIzq(byte[] efisFotoCuerpoCompletoPerfilIzq) {
        this.efisFotoCuerpoCompletoPerfilIzq = efisFotoCuerpoCompletoPerfilIzq;
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
        hash += (efisId != null ? efisId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuExamenFisico)) {
            return false;
        }
        MuExamenFisico other = (MuExamenFisico) object;
        if ((this.efisId == null && other.efisId != null) || (this.efisId != null && !this.efisId.equals(other.efisId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.gymadmdoc.entities.MuExamenFisico[ efisId=" + efisId + " ]";
    }
    
}
