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
@Table(name = "ma_asistencia")
@XmlRootElement
@NamedQueries({
   @NamedQuery(name = "MaAsistencia.findAll", query = "SELECT m FROM MaAsistencia m"),
   @NamedQuery(name = "MaAsistencia.findByAsiid", query = "SELECT m FROM MaAsistencia m WHERE m.asiid = :asiid"),
   @NamedQuery(name = "MaAsistencia.findByAsifecha", query = "SELECT m FROM MaAsistencia m WHERE m.asifecha = :asifecha")})
public class MaAsistencia implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   @Basic(optional = false)
   @NotNull
   @Column(name = "ASIID")
   private Long asiid;
   @Basic(optional = false)
   @NotNull
   @Column(name = "ASIFECHA")
   @Temporal(TemporalType.TIMESTAMP)
   private Date asifecha;

   public MaAsistencia() {
   }

   public MaAsistencia(Long asiid) {
      this.asiid = asiid;
   }

   public MaAsistencia(Long asiid, Date asifecha) {
      this.asiid = asiid;
      this.asifecha = asifecha;
   }

   public Long getAsiid() {
      return asiid;
   }

   public void setAsiid(Long asiid) {
      this.asiid = asiid;
   }

   public Date getAsifecha() {
      return asifecha;
   }

   public void setAsifecha(Date asifecha) {
      this.asifecha = asifecha;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (asiid != null ? asiid.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof MaAsistencia)) {
         return false;
      }
      MaAsistencia other = (MaAsistencia) object;
      if ((this.asiid == null && other.asiid != null) || (this.asiid != null && !this.asiid.equals(other.asiid))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "co.edu.unicauca.gymadmdoc.entities.MaAsistencia[ asiid=" + asiid + " ]";
   }
   
}
