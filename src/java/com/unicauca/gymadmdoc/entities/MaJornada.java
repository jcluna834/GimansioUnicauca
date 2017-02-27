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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "ma_jornada")
@XmlRootElement
@NamedQueries({
   @NamedQuery(name = "MaJornada.findAll", query = "SELECT m FROM MaJornada m"),
   @NamedQuery(name = "MaJornada.findByJorid", query = "SELECT m FROM MaJornada m WHERE m.jorid = :jorid"),
   @NamedQuery(name = "MaJornada.findByJorinicio", query = "SELECT m FROM MaJornada m WHERE m.jorinicio = :jorinicio"),
   @NamedQuery(name = "MaJornada.findByJorfin", query = "SELECT m FROM MaJornada m WHERE m.jorfin = :jorfin"),
   @NamedQuery(name = "MaJornada.findByJortipo", query = "SELECT m FROM MaJornada m WHERE m.jortipo = :jortipo")})
public class MaJornada implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   @Basic(optional = false)
   @NotNull
   @Column(name = "JORID")
   private Long jorid;
   @Basic(optional = false)
   @NotNull
   @Column(name = "JORINICIO")
   @Temporal(TemporalType.TIME)
   private Date jorinicio;
   @Basic(optional = false)
   @NotNull
   @Column(name = "JORFIN")
   @Temporal(TemporalType.TIME)
   private Date jorfin;
   @Size(max = 10)
   @Column(name = "JORTIPO")
   private String jortipo;

   public MaJornada() {
   }

   public MaJornada(Long jorid) {
      this.jorid = jorid;
   }

   public MaJornada(Long jorid, Date jorinicio, Date jorfin) {
      this.jorid = jorid;
      this.jorinicio = jorinicio;
      this.jorfin = jorfin;
   }

   public Long getJorid() {
      return jorid;
   }

   public void setJorid(Long jorid) {
      this.jorid = jorid;
   }

   public Date getJorinicio() {
      return jorinicio;
   }

   public void setJorinicio(Date jorinicio) {
      this.jorinicio = jorinicio;
   }

   public Date getJorfin() {
      return jorfin;
   }

   public void setJorfin(Date jorfin) {
      this.jorfin = jorfin;
   }

   public String getJortipo() {
      return jortipo;
   }

   public void setJortipo(String jortipo) {
      this.jortipo = jortipo;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (jorid != null ? jorid.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof MaJornada)) {
         return false;
      }
      MaJornada other = (MaJornada) object;
      if ((this.jorid == null && other.jorid != null) || (this.jorid != null && !this.jorid.equals(other.jorid))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "co.edu.unicauca.gymadmdoc.entities.MaJornada[ jorid=" + jorid + " ]";
   }
   
}
