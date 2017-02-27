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
@Table(name = "usuario_medicamento")
@XmlRootElement
@NamedQueries({
   @NamedQuery(name = "UsuarioMedicamento.findAll", query = "SELECT u FROM UsuarioMedicamento u"),
   @NamedQuery(name = "UsuarioMedicamento.findByUsuIdentificacion", query = "SELECT u FROM UsuarioMedicamento u WHERE u.usuarioMedicamentoPK.usuIdentificacion = :usuIdentificacion"),
   @NamedQuery(name = "UsuarioMedicamento.findByMedId", query = "SELECT u FROM UsuarioMedicamento u WHERE u.usuarioMedicamentoPK.medId = :medId")})
public class UsuarioMedicamento implements Serializable {

   private static final long serialVersionUID = 1L;
   @EmbeddedId
   protected UsuarioMedicamentoPK usuarioMedicamentoPK;

   public UsuarioMedicamento() {
   }

   public UsuarioMedicamento(UsuarioMedicamentoPK usuarioMedicamentoPK) {
      this.usuarioMedicamentoPK = usuarioMedicamentoPK;
   }

   public UsuarioMedicamento(long usuIdentificacion, int medId) {
      this.usuarioMedicamentoPK = new UsuarioMedicamentoPK(usuIdentificacion, medId);
   }

   public UsuarioMedicamentoPK getUsuarioMedicamentoPK() {
      return usuarioMedicamentoPK;
   }

   public void setUsuarioMedicamentoPK(UsuarioMedicamentoPK usuarioMedicamentoPK) {
      this.usuarioMedicamentoPK = usuarioMedicamentoPK;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (usuarioMedicamentoPK != null ? usuarioMedicamentoPK.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof UsuarioMedicamento)) {
         return false;
      }
      UsuarioMedicamento other = (UsuarioMedicamento) object;
      if ((this.usuarioMedicamentoPK == null && other.usuarioMedicamentoPK != null) || (this.usuarioMedicamentoPK != null && !this.usuarioMedicamentoPK.equals(other.usuarioMedicamentoPK))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "co.edu.unicauca.gymadmdoc.entities.UsuarioMedicamento[ usuarioMedicamentoPK=" + usuarioMedicamentoPK + " ]";
   }
   
}
