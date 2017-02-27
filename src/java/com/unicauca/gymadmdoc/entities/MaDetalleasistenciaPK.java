/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ricardo
 */
@Embeddable
public class MaDetalleasistenciaPK implements Serializable {

   @Basic(optional = false)
   @NotNull
   @Column(name = "ASIID")
   private long asiid;
   @Basic(optional = false)
   @NotNull
   @Column(name = "JORID")
   private long jorid;
   @Basic(optional = false)
   @NotNull
   @Column(name = "USU_IDENTIFICACION")
   private long usuIdentificacion;

   public MaDetalleasistenciaPK() {
   }

   public MaDetalleasistenciaPK(long asiid, long jorid, long usuIdentificacion) {
      this.asiid = asiid;
      this.jorid = jorid;
      this.usuIdentificacion = usuIdentificacion;
   }

   public long getAsiid() {
      return asiid;
   }

   public void setAsiid(long asiid) {
      this.asiid = asiid;
   }

   public long getJorid() {
      return jorid;
   }

   public void setJorid(long jorid) {
      this.jorid = jorid;
   }

   public long getUsuIdentificacion() {
      return usuIdentificacion;
   }

   public void setUsuIdentificacion(long usuIdentificacion) {
      this.usuIdentificacion = usuIdentificacion;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (int) asiid;
      hash += (int) jorid;
      hash += (int) usuIdentificacion;
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof MaDetalleasistenciaPK)) {
         return false;
      }
      MaDetalleasistenciaPK other = (MaDetalleasistenciaPK) object;
      if (this.asiid != other.asiid) {
         return false;
      }
      if (this.jorid != other.jorid) {
         return false;
      }
      if (this.usuIdentificacion != other.usuIdentificacion) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "co.edu.unicauca.gymadmdoc.entities.MaDetalleasistenciaPK[ asiid=" + asiid + ", jorid=" + jorid + ", usuIdentificacion=" + usuIdentificacion + " ]";
   }
   
}
