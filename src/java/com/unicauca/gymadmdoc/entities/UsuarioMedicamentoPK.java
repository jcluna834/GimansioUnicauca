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
public class UsuarioMedicamentoPK implements Serializable {

   @Basic(optional = false)
   @NotNull
   @Column(name = "USU_IDENTIFICACION")
   private long usuIdentificacion;
   @Basic(optional = false)
   @NotNull
   @Column(name = "MED_ID")
   private int medId;

   public UsuarioMedicamentoPK() {
   }

   public UsuarioMedicamentoPK(long usuIdentificacion, int medId) {
      this.usuIdentificacion = usuIdentificacion;
      this.medId = medId;
   }

   public long getUsuIdentificacion() {
      return usuIdentificacion;
   }

   public void setUsuIdentificacion(long usuIdentificacion) {
      this.usuIdentificacion = usuIdentificacion;
   }

   public int getMedId() {
      return medId;
   }

   public void setMedId(int medId) {
      this.medId = medId;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (int) usuIdentificacion;
      hash += (int) medId;
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof UsuarioMedicamentoPK)) {
         return false;
      }
      UsuarioMedicamentoPK other = (UsuarioMedicamentoPK) object;
      if (this.usuIdentificacion != other.usuIdentificacion) {
         return false;
      }
      if (this.medId != other.medId) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "co.edu.unicauca.gymadmdoc.entities.UsuarioMedicamentoPK[ usuIdentificacion=" + usuIdentificacion + ", medId=" + medId + " ]";
   }
   
}
