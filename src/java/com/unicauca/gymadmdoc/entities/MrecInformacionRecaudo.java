/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "mrec_informacion_recaudo")
@XmlRootElement
@NamedQueries({
   @NamedQuery(name = "MrecInformacionRecaudo.findAll", query = "SELECT m FROM MrecInformacionRecaudo m"),
   @NamedQuery(name = "MrecInformacionRecaudo.findByIrecId", query = "SELECT m FROM MrecInformacionRecaudo m WHERE m.irecId = :irecId"),
   @NamedQuery(name = "MrecInformacionRecaudo.findByRpagReferencia", query = "SELECT m FROM MrecInformacionRecaudo m WHERE m.rpagReferencia = :rpagReferencia"),
   @NamedQuery(name = "MrecInformacionRecaudo.findByUsuIdentificacion", query = "SELECT m FROM MrecInformacionRecaudo m WHERE m.usuIdentificacion = :usuIdentificacion"),
   @NamedQuery(name = "MrecInformacionRecaudo.findByIrecEstadoRecaudo", query = "SELECT m FROM MrecInformacionRecaudo m WHERE m.irecEstadoRecaudo = :irecEstadoRecaudo"),
   @NamedQuery(name = "MrecInformacionRecaudo.findByIrecMes", query = "SELECT m FROM MrecInformacionRecaudo m WHERE m.irecMes = :irecMes"),
   @NamedQuery(name = "MrecInformacionRecaudo.findByIrecAnio", query = "SELECT m FROM MrecInformacionRecaudo m WHERE m.irecAnio = :irecAnio"),
   @NamedQuery(name = "MrecInformacionRecaudo.findByIrecFechaLimite", query = "SELECT m FROM MrecInformacionRecaudo m WHERE m.irecFechaLimite = :irecFechaLimite")})

public class MrecInformacionRecaudo implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Basic(optional = false)
   @Column(name = "IREC_ID")
   private Long irecId;
   @Column(name = "RPAG_REFERENCIA")
   private BigInteger rpagReferencia;
   @Basic(optional = false)
   @NotNull
   @Column(name = "USU_IDENTIFICACION")
   private long usuIdentificacion;
   @Basic(optional = false)
   @NotNull
   @Size(min = 1, max = 50)
   @Column(name = "IREC_ESTADO_RECAUDO")
   private String irecEstadoRecaudo;
   @Basic(optional = false)
   @NotNull
   @Column(name = "IREC_MES")
   private int irecMes;
   @Basic(optional = false)
   @NotNull
   @Column(name = "IREC_ANIO")
   private int irecAnio;
   @Basic(optional = false)
   @NotNull
   @Column(name = "IREC_FECHA_LIMITE")
   @Temporal(TemporalType.DATE)
   private Date irecFechaLimite;

   public MrecInformacionRecaudo() {
   }

   public MrecInformacionRecaudo(Long irecId) {
      this.irecId = irecId;
   }

   public MrecInformacionRecaudo(Long irecId, long usuIdentificacion, String irecEstadoRecaudo, int irecMes, int irecAnio, Date irecFechaLimite) {
      this.irecId = irecId;
      this.usuIdentificacion = usuIdentificacion;
      this.irecEstadoRecaudo = irecEstadoRecaudo;
      this.irecMes = irecMes;
      this.irecAnio = irecAnio;
      this.irecFechaLimite = irecFechaLimite;
   }

   public Long getIrecId() {
      return irecId;
   }

   public void setIrecId(Long irecId) {
      this.irecId = irecId;
   }

   public BigInteger getRpagReferencia() {
      return rpagReferencia;
   }

   public void setRpagReferencia(BigInteger rpagReferencia) {
      this.rpagReferencia = rpagReferencia;
   }

   public long getUsuIdentificacion() {
      return usuIdentificacion;
   }

   public void setUsuIdentificacion(long usuIdentificacion) {
      this.usuIdentificacion = usuIdentificacion;
   }

   public String getIrecEstadoRecaudo() {
      return irecEstadoRecaudo;
   }

   public void setIrecEstadoRecaudo(String irecEstadoRecaudo) {
      this.irecEstadoRecaudo = irecEstadoRecaudo;
   }

   public int getIrecMes() {
      return irecMes;
   }

   public void setIrecMes(int irecMes) {
      this.irecMes = irecMes;
   }

   public int getIrecAnio() {
      return irecAnio;
   }

   public void setIrecAnio(int irecAnio) {
      this.irecAnio = irecAnio;
   }

   public Date getIrecFechaLimite() {
      return irecFechaLimite;
   }

   public void setIrecFechaLimite(Date irecFechaLimite) {
      this.irecFechaLimite = irecFechaLimite;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (irecId != null ? irecId.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof MrecInformacionRecaudo)) {
         return false;
      }
      MrecInformacionRecaudo other = (MrecInformacionRecaudo) object;
      if ((this.irecId == null && other.irecId != null) || (this.irecId != null && !this.irecId.equals(other.irecId))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "co.edu.unicauca.gymadmdoc.entities.MrecInformacionRecaudo[ irecId=" + irecId + " ]";
   }
   
}
