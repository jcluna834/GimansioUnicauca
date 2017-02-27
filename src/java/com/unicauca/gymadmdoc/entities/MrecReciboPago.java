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
 * @author Ricardo
 */
@Entity
@Table(name = "mrec_recibo_pago")
@XmlRootElement
@NamedQueries({
   @NamedQuery(name = "MrecReciboPago.findAll", query = "SELECT m FROM MrecReciboPago m"),
   @NamedQuery(name = "MrecReciboPago.findByRpagReferencia", query = "SELECT m FROM MrecReciboPago m WHERE m.rpagReferencia = :rpagReferencia"),
   @NamedQuery(name = "MrecReciboPago.findByRpagFechaExpedicion", query = "SELECT m FROM MrecReciboPago m WHERE m.rpagFechaExpedicion = :rpagFechaExpedicion"),
   @NamedQuery(name = "MrecReciboPago.findByRpagMensualidad", query = "SELECT m FROM MrecReciboPago m WHERE m.rpagMensualidad = :rpagMensualidad"),
   @NamedQuery(name = "MrecReciboPago.findByRpagNumeroSesiones", query = "SELECT m FROM MrecReciboPago m WHERE m.rpagNumeroSesiones = :rpagNumeroSesiones"),
   @NamedQuery(name = "MrecReciboPago.findByRpagTotalRecibo", query = "SELECT m FROM MrecReciboPago m WHERE m.rpagTotalRecibo = :rpagTotalRecibo")})
public class MrecReciboPago implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   @Basic(optional = false)
   @NotNull
   @Column(name = "RPAG_REFERENCIA")
   private Long rpagReferencia;
   @Basic(optional = false)
   @NotNull
   @Column(name = "RPAG_FECHA_EXPEDICION")
   @Temporal(TemporalType.DATE)
   private Date rpagFechaExpedicion;
   @Basic(optional = false)
   @NotNull
   @Column(name = "RPAG_MENSUALIDAD")
   private boolean rpagMensualidad;
   @Basic(optional = false)
   @NotNull
   @Column(name = "RPAG_NUMERO_SESIONES")
   private int rpagNumeroSesiones;
   @Basic(optional = false)
   @NotNull
   @Column(name = "RPAG_TOTAL_RECIBO")
   private long rpagTotalRecibo;

   public MrecReciboPago() {
   }

   public MrecReciboPago(Long rpagReferencia) {
      this.rpagReferencia = rpagReferencia;
   }

   public MrecReciboPago(Long rpagReferencia, Date rpagFechaExpedicion, boolean rpagMensualidad, int rpagNumeroSesiones, long rpagTotalRecibo) {
      this.rpagReferencia = rpagReferencia;
      this.rpagFechaExpedicion = rpagFechaExpedicion;
      this.rpagMensualidad = rpagMensualidad;
      this.rpagNumeroSesiones = rpagNumeroSesiones;
      this.rpagTotalRecibo = rpagTotalRecibo;
   }

   public Long getRpagReferencia() {
      return rpagReferencia;
   }

   public void setRpagReferencia(Long rpagReferencia) {
      this.rpagReferencia = rpagReferencia;
   }

   public Date getRpagFechaExpedicion() {
      return rpagFechaExpedicion;
   }

   public void setRpagFechaExpedicion(Date rpagFechaExpedicion) {
      this.rpagFechaExpedicion = rpagFechaExpedicion;
   }

   public boolean getRpagMensualidad() {
      return rpagMensualidad;
   }

   public void setRpagMensualidad(boolean rpagMensualidad) {
      this.rpagMensualidad = rpagMensualidad;
   }

   public int getRpagNumeroSesiones() {
      return rpagNumeroSesiones;
   }

   public void setRpagNumeroSesiones(int rpagNumeroSesiones) {
      this.rpagNumeroSesiones = rpagNumeroSesiones;
   }

   public long getRpagTotalRecibo() {
      return rpagTotalRecibo;
   }

   public void setRpagTotalRecibo(long rpagTotalRecibo) {
      this.rpagTotalRecibo = rpagTotalRecibo;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (rpagReferencia != null ? rpagReferencia.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof MrecReciboPago)) {
         return false;
      }
      MrecReciboPago other = (MrecReciboPago) object;
      if ((this.rpagReferencia == null && other.rpagReferencia != null) || (this.rpagReferencia != null && !this.rpagReferencia.equals(other.rpagReferencia))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "co.edu.unicauca.gymadmdoc.entities.MrecReciboPago[ rpagReferencia=" + rpagReferencia + " ]";
   }
   
}
