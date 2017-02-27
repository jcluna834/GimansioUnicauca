/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Ricardo
 */
@Named(value = "dateService")
@ApplicationScoped
public class DateService {

   /**
    * Creates a new instance of dateService
    */
   String[] months = {"Enero", "Febrero", "Marzo", 
                      "Abril", "Mayo", "Junio",
                      "Julio", "Agosto", "Septiembre", 
                      "Octubre", "Noviembre", "Diciembre"};
   
   public DateService() {
      
   }
   
   public List<String> getMonths(int year){
      List<String> meses = new ArrayList<>();
      Calendar c = Calendar.getInstance();
      int anio =c.get(Calendar.YEAR);
      int maxMonth = 11;
      
      if(anio == year){
         maxMonth = c.get(Calendar.MONTH);
      }
     
      for(int i=maxMonth; i>=0; i--){
         meses.add(months[i]);
      }
      return meses;
   }
   
   public List<Integer> getMonthsInt(int year){
      List<Integer> meses = new ArrayList<>();
      Calendar c = Calendar.getInstance();
      int anio =c.get(Calendar.YEAR);
      int maxMonth = 11;
      
      if(anio == year){
         maxMonth = c.get(Calendar.MONTH);
      }
     
      for(int i=maxMonth; i>=0; i--){
         meses.add(i+1);
      }
      return meses;
   }
   
   public List<Integer> getYears(){
      List<Integer> anios = new ArrayList<>();
      
      int anioInicial = 2000; // Deberia calcularse con la base de datos
      Calendar c = Calendar.getInstance();
      int anioFinal =c.get(Calendar.YEAR);
      for(int i=anioFinal; i>=anioInicial; i--){
         anios.add(i);
      }
      
      return anios;
   }
}
