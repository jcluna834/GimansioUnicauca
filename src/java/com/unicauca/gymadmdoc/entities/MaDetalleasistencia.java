/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "ma_detalleasistencia")
@XmlRootElement
@NamedQueries({
   @NamedQuery(name = "MaDetalleasistencia.findAll", query = "SELECT m FROM MaDetalleasistencia m"),
   @NamedQuery(name = "MaDetalleasistencia.findByAsiid", query = "SELECT m FROM MaDetalleasistencia m WHERE m.maDetalleasistenciaPK.asiid = :asiid"),
   @NamedQuery(name = "MaDetalleasistencia.findByJorid", query = "SELECT m FROM MaDetalleasistencia m WHERE m.maDetalleasistenciaPK.jorid = :jorid"),
   @NamedQuery(name = "MaDetalleasistencia.findByUsuIdentificacion", query = "SELECT m FROM MaDetalleasistencia m WHERE m.maDetalleasistenciaPK.usuIdentificacion = :usuIdentificacion")})
public class MaDetalleasistencia implements Serializable {

   private static final long serialVersionUID = 1L;
   @EmbeddedId
   protected MaDetalleasistenciaPK maDetalleasistenciaPK;

   public MaDetalleasistencia() {
   }

   public MaDetalleasistencia(MaDetalleasistenciaPK maDetalleasistenciaPK) {
      this.maDetalleasistenciaPK = maDetalleasistenciaPK;
   }

   public MaDetalleasistencia(long asiid, long jorid, long usuIdentificacion) {
      this.maDetalleasistenciaPK = new MaDetalleasistenciaPK(asiid, jorid, usuIdentificacion);
   }

   public MaDetalleasistenciaPK getMaDetalleasistenciaPK() {
      return maDetalleasistenciaPK;
   }

   public void setMaDetalleasistenciaPK(MaDetalleasistenciaPK maDetalleasistenciaPK) {
      this.maDetalleasistenciaPK = maDetalleasistenciaPK;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (maDetalleasistenciaPK != null ? maDetalleasistenciaPK.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof MaDetalleasistencia)) {
         return false;
      }
      MaDetalleasistencia other = (MaDetalleasistencia) object;
      if ((this.maDetalleasistenciaPK == null && other.maDetalleasistenciaPK != null) || (this.maDetalleasistenciaPK != null && !this.maDetalleasistenciaPK.equals(other.maDetalleasistenciaPK))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "co.edu.unicauca.gymadmdoc.entities.MaDetalleasistencia[ maDetalleasistenciaPK=" + maDetalleasistenciaPK + " ]";
   }
   
}
