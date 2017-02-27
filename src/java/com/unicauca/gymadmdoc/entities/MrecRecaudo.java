/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ricardo
 */
public class MrecRecaudo {
   
   SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
   
   private long id;
   private MuUsuario usuario;
   private String estado;
   private Date fechaLimite;
   private int mes;
   private int anio;
   private String strFechaLimite;
   private MrecReciboPago recibo;

   public MrecRecaudo() {
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public MuUsuario getUsuario() {
      return usuario;
   }

   public void setUsuario(MuUsuario usuario) {
      this.usuario = usuario;
   }

   public String getEstado() {
      return estado;
   }

   public void setEstado(String estado) {
      this.estado = estado;
   }

   public int getMes() {
      return mes;
   }

   public void setMes(int mes) {      
      this.mes = mes;
   }

   public int getAnio() {
      return anio;
   }

   public void setAnio(int anio) {
      this.anio = anio;
   }
   
   public Date getFechaLimite() {
      return fechaLimite;
   }

   public void setFechaLimite(Date fechaLimite) {
      this.fechaLimite = fechaLimite;
      this.strFechaLimite = formateador.format(fechaLimite);
   }

   public MrecReciboPago getRecibo() {
      return recibo;
   }

   public void setRecibo(MrecReciboPago recibo) {
      this.recibo = recibo;
   }

   public String getStrFechaLimite() {
      return strFechaLimite;
   }
}