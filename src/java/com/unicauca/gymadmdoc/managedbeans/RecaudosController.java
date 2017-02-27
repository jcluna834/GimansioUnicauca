/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.managedbeans;

import com.unicauca.gymadmdoc.entities.MrecRecaudo;
import com.unicauca.gymadmdoc.entities.MrecReciboPago;
import com.unicauca.gymadmdoc.entities.MuUsuario;
import com.unicauca.gymadmdoc.services.DateService;
import com.unicauca.gymadmdoc.services.ExportService;
import com.unicauca.gymadmdoc.services.RecaudoService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;

/**
 *
 * @author Ricardo
 */
@Named(value = "recaudosController")
@SessionScoped
public class RecaudosController implements Serializable {

   // <editor-fold defaultstate="collapsed" desc="Atributos para el manejo de la fecha">

   List<String> monthsES;
   
   private int year;
   public int getYear() {
      return year;
   }
   public void setYear(int year) {
      this.year = year;
   }
   private String month;
   public String getMonth() {
      return month;
   }
   public void setMonth(String month) {
      this.month = month;
   }
   private int monthInt;

   public int getMonthInt() {
      return monthInt;
   }
   public void setMonthInt(int monthInt) {
      this.monthInt = monthInt;
   }
   private int day;
   public int getDay() {
      return day;
   }
   public void setDay(int day) {
      this.day = day;
   }
   private List<Integer> years;
   public List<Integer> getYears() {
      return years;
   }
   private List<String> months;
   public List<String> getMonths() {
      return months;
   }
   private List<Integer> monthsInt;
   public List<Integer> getMonthsInt() {
      return monthsInt;
   }
   
   @Inject
   private DateService service_date;
   public void setService_date(DateService service_date) {
      this.service_date = service_date;
   }
   
   // </editor-fold>
   
   // <editor-fold defaultstate="collapsed" desc="Atributos para el manejo de los recaudos">
   
   MrecRecaudo recaudoSelected;
   public MrecRecaudo getRecaudoSelected() {
      return recaudoSelected;
   }
   public void setRecaudoSelected(MrecRecaudo recaudoSelected) {
      this.usuarioInfo = null;
      this.reciboInfo = null;
      this.recaudoSelected = recaudoSelected;
      if(recaudoSelected.getUsuario()!=null)
         this.usuarioInfo = recaudoSelected.getUsuario();
      if(recaudoSelected.getRecibo()!=null)
         this.reciboInfo = recaudoSelected.getRecibo();
   }
   
   List<MrecRecaudo> recaudos;
   public List<MrecRecaudo> getRecaudos() {
      return recaudos;
   }
   
   List<MrecRecaudo> recaudosFiltered;
   public List<MrecRecaudo> getRecaudosFiltered() {
      return recaudosFiltered;
   }
   public void setRecaudosFiltered(List<MrecRecaudo> recaudosFiltered) {
      this.recaudosFiltered = recaudosFiltered;
   }
   
   private MuUsuario usuarioInfo;
   public MuUsuario getUsuarioInfo() {
      return usuarioInfo;
   }
   
   private MrecReciboPago reciboInfo;
   public MrecReciboPago getReciboInfo() {
      return reciboInfo;
   }
   
   @Inject
   private RecaudoService service_recaudo;
   public void setService_recaudo(RecaudoService service_recaudo) {
      this.service_recaudo = service_recaudo;
   }
   
   @Inject
   private ExportService service_export;
   public void setService_export(ExportService service_export) {
      this.service_export = service_export;
   }
   
   
   
   // </editor-fold>
   
   // <editor-fold defaultstate="collapsed" desc="Atributos para el manejo del Registro de recaudo">
   
   private long idUsuario;
   public long getIdUsuario() {
      return idUsuario;
   }
   public void setIdUsuario(long idUsuario) {
      this.idUsuario = idUsuario;
   }
   
   private String nombreUsuario;
   public String getNombreUsuario() {
      return nombreUsuario;
   }

   private String ocupacionUsuario;
   public String getOcupacionUsuario() {
      return ocupacionUsuario;
   }
   
   private long referencia;
   public long getReferencia() {
      return referencia;
   }
   public void setReferencia(long referencia) {
      this.referencia = referencia;
   }
   
   private Date fechaEspedicion;
   public Date getFechaEspedicion() {
      return fechaEspedicion;
   }
   public void setFechaEspedicion(Date fechaEspedicion) {
      this.fechaEspedicion = fechaEspedicion;
   }
   
   private int sesiones;
   public int getSesiones() {
      return sesiones;
   }
   public void setSesiones(int sesiones) {
      this.sesiones = sesiones;
   }
   
   
   private boolean mensualidad;
   public boolean isMensualidad() {
      return mensualidad;
   }
   public void setMensualidad(boolean mensualidad) {
      this.mensualidad = mensualidad;
   }
   
   // </editor-fold>
   
   // <editor-fold defaultstate="collapsed" desc="Atributos para el manejo de las Estadisticas">
   
   /*private DonutChartModel donutModel;
   public DonutChartModel getDonutModel() {
      return donutModel;
   }*/
   
   private HorizontalBarChartModel barModel;
   public HorizontalBarChartModel getBarModel() {
      return barModel;
   }
   
   private String[] selectedOcupaciones;
   public String[] getSelectedOcupaciones() {
      return selectedOcupaciones;
   }
   public void setSelectedOcupaciones(String[] selectedOcupaciones) {
      this.selectedOcupaciones = selectedOcupaciones;
   }
   
   private List<String> ocupaciones;
   public List<String> getOcupaciones() {
      return ocupaciones;
   }
   
   private Date filterFechaInicio;
   public Date getFilterFechaInicio() {
      return filterFechaInicio;
   }
   public void setFilterFechaInicio(Date filterFechaInicio) {
      this.filterFechaInicio = filterFechaInicio;
   }
   
   private Date filterFechaFin;
   public Date getFilterFechaFin() {
      return filterFechaFin;
   }
   public void setFilterFechaFin(Date filterFechaFin) {
      this.filterFechaFin = filterFechaFin;
   }
   
   private boolean filterMensualidad;
   public boolean isFilterMensualidad() {
      return filterMensualidad;
   }
   public void setFilterMensualidad(boolean filterMensualidad) {
      this.filterMensualidad = filterMensualidad;
   }
   
   private boolean filterSesiones;
   public boolean isFilterSesiones() {
      return filterSesiones;
   }
   public void setFilterSesiones(boolean filterSesiones) {
      this.filterSesiones = filterSesiones;
   }
      
   // </editor-fold>
   
   // <editor-fold defaultstate="collapsed" desc="Atributos para el manejo de la exportación">
   
   /*private DonutChartModel donutModel;
   public DonutChartModel getDonutModel() {
      return donutModel;
   }*/
   private StreamedContent file;
   String nombreTemp;
   
   
   public StreamedContent getFile() {
      nombreTemp = service_export.exportReporteRecaudo(monthInt, year);  
      if(!nombreTemp.isEmpty()){
         File fil = new File("C:/Temp/"+nombreTemp);
         try {
            this.file = new DefaultStreamedContent(new FileInputStream(fil), new MimetypesFileTypeMap().getContentType(fil), nombreTemp);
         } catch (FileNotFoundException ex) {
            Logger.getLogger(RecaudosController.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
      return file;
   }
   
   // </editor-fold>
   
   public RecaudosController(){}
   
   @PostConstruct
   public void init(){
      //updateDatePicker();
      llenarMeses();
      Calendar c = Calendar.getInstance();
      this.year = c.get(Calendar.YEAR);
      this.years = service_date.getYears();
      this.months = service_date.getMonths(year);
      this.month = months.get(0);
      this.monthInt = monthsES.indexOf(month)+1;
      this.day = c.get(Calendar.DATE);
      loadTableInfo();
      loadInfoRecibo();
      buildOcupaciones();
      loadGraphicFilters();
      //this.donutModel = new DonutChartModel();
      this.barModel = new HorizontalBarChartModel();
   }
   
   public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
   
   public void onYearChange(){
      this.months = service_date.getMonths(year);
      this.monthsInt = service_date.getMonthsInt(year);
      this.month = months.get(0);
      this.monthInt = monthsES.indexOf(month)+1;
      loadTableInfo();
   }
   
   public void onMonthChange(){
      this.monthInt = monthsES.indexOf(month)+1;
      loadTableInfo();
   }
   
   public void deleteRecaudo(){
      //Logica para eliminar registro de la base de datos
      service_recaudo.eliminarRecaudo(recaudoSelected);
      addMessage("Correcto", "El recaudo se eliminó de manera correcta.");
   }
   
   public void handleKeyEvent(){
      this.nombreUsuario = service_recaudo.getNombreUsuario(idUsuario);
   }
   
   public void registrarRecaudo(){
      MrecReciboPago rp = buildRecibo();
      service_recaudo.registrarRecaudo(idUsuario, rp);
      loadTableInfo();
   }

   public void registrarPago(){
      service_recaudo.registrarPago(recaudoSelected);
      loadTableInfo();
      addMessage("Correcto", "El pago se registró de manera correcta.");
   }
   
   public void generarRecibo(){
      MrecReciboPago rp = buildRecibo();
      service_recaudo.generarRecibo(recaudoSelected, rp);
      loadTableInfo();
      addMessage("Correcto", "El recibo se generó de manera correcta.");
   }
   
   public void prepareRecaudoView(){
      if(recaudoSelected.getUsuario()!=null)
         this.usuarioInfo = recaudoSelected.getUsuario();
      if(recaudoSelected.getRecibo()!=null)
         this.reciboInfo = recaudoSelected.getRecibo();
   }
   
   private void loadTableInfo(){
      try{
         recaudos = service_recaudo.getRecaudos(year, month);
      }catch(ParseException e){}
   }
   
   public void loadInfoRecibo(){
      this.sesiones = 0;
      this.fechaEspedicion = new Date();
      this.mensualidad = true;
   }

   private MrecReciboPago buildRecibo(){
      MrecReciboPago rp = new MrecReciboPago();
      rp.setRpagReferencia(referencia);
      rp.setRpagFechaExpedicion(fechaEspedicion);
      rp.setRpagNumeroSesiones(sesiones);
      rp.setRpagMensualidad(mensualidad);
      long total = 2000 * sesiones;
      if(mensualidad){
         total+=28000;
      }
      rp.setRpagTotalRecibo(total);
      return rp;
   }
   
   private void buildOcupaciones(){
      this.ocupaciones = service_recaudo.getOcupaciones();
   }

   private void loadGraphicFilters(){
      this.filterMensualidad = true;
      this.filterSesiones = true;
      this.selectedOcupaciones = null;
      Calendar c = Calendar.getInstance();
      this.filterFechaFin = c.getTime();
      c.set(Calendar.DATE, 1);
      this.filterFechaInicio = c.getTime();
   }
   
   public void generarGraficas(){
      if(comprobateFilters()){
         //this.donutModel = service_recaudo.getDonut(filterFechaInicio, 
         //        filterFechaFin, filterMensualidad, filterSesiones, selectedOcupaciones);
         this.barModel = service_recaudo.getBar(filterFechaInicio, 
                 filterFechaFin, filterMensualidad, filterSesiones, selectedOcupaciones);
      }
   }
   
   private boolean comprobateFilters(){
      boolean result = true;
      
      this.barModel.clear();
      //this.donutModel.clear();
      
      if(this.filterFechaInicio == null || 
              this.filterFechaFin == null){
         result = false;
         addMessage("Error", "Debe existir un rango de fechas para generar las graficas.");
      }
      if(!this.filterMensualidad && !this.filterSesiones){
         result = false;
         addMessage("Error", "Seleccione Mensualidad o Sesiones, al menos una.");
      }
      if(this.selectedOcupaciones.length == 0){
         result = false;
         addMessage("Error", "Seleccione al menos una ocupacion.");
      }
      
      return result;
   }
   
   public void exportReporte(){
      
   }
   
   public void downloadReporte(){
      
   }
   
   private void llenarMeses(){
      monthsES = new ArrayList<>();
      monthsES.add("Enero");
      monthsES.add("Febrero");
      monthsES.add("Marzo");
      monthsES.add("Abril");
      monthsES.add("Mayo");
      monthsES.add("Junio");
      monthsES.add("Julio");
      monthsES.add("Agosto");
      monthsES.add("Septiembre");
      monthsES.add("Octubre");
      monthsES.add("Noviembre");
      monthsES.add("Diciembre");
   }
}

