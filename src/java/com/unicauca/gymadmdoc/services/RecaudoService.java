/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.services;

import com.unicauca.gymadmdoc.entities.MrecInformacionRecaudo;
import com.unicauca.gymadmdoc.entities.MrecRecaudo;
import com.unicauca.gymadmdoc.entities.MrecReciboPago;
import com.unicauca.gymadmdoc.entities.MruRutina;
import com.unicauca.gymadmdoc.entities.MuOcupacion;
import com.unicauca.gymadmdoc.entities.MuUsuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import com.unicauca.gymadmdoc.sessionbeans.MrecInformacionRecaudoFacade;
import com.unicauca.gymadmdoc.sessionbeans.MrecReciboPagoFacade;
import com.unicauca.gymadmdoc.sessionbeans.MuOcupacionFacade;
import com.unicauca.gymadmdoc.sessionbeans.MuUsuarioFacade;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.ejb.EJBException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;

/**
 *
 * @author Ricardo
 */
@Named(value = "recaudoService")
@ApplicationScoped
public class RecaudoService {
   
   @PersistenceContext(unitName = "Gym_Adm_DocPU")
   private EntityManager em;
   @EJB
   private MrecInformacionRecaudoFacade ejbInfoRecaudo;
   @EJB
   private MrecReciboPagoFacade ejbReciboPago;
   @EJB
   private MuUsuarioFacade ejbUsuario;
   @EJB
   private MuOcupacionFacade ejbOcupacion;
   
   String[] months = {"Enero", "Febrero", "Marzo", 
                      "Abril", "Mayo", "Junio",
                      "Julio", "Agosto", "Septiembre", 
                      "Octubre", "Noviembre", "Diciembre"};
   
   public RecaudoService() {
   }
   
   public List<MrecRecaudo> getRecaudos(int year, String month) throws ParseException{
      
      List<MrecInformacionRecaudo> lstInfoRecaudo = ejbInfoRecaudo.findAll();
      List<MrecRecaudo> data = new ArrayList<>();
      
      lstInfoRecaudo.stream().forEach((ir) -> {
         String m = months[ir.getIrecMes()-1];
         int y = ir.getIrecAnio();
         if(y == year && m.equals(month)){
            MrecRecaudo r = new MrecRecaudo();
            r.setId(ir.getIrecId());
            r.setAnio(ir.getIrecAnio());
            r.setMes(ir.getIrecMes());
            r.setFechaLimite(ir.getIrecFechaLimite());
            BigInteger ref = ir.getRpagReferencia();
            if(ref != null){
               MrecReciboPago rp = ejbReciboPago.find(ref.longValue());
               r.setRecibo(rp);
            }
            MuUsuario u = ejbUsuario.find(ir.getUsuIdentificacion());
            r.setUsuario(u);
            r.setEstado(ir.getIrecEstadoRecaudo());
            data.add(r);
         }
      });
      return data;
   }
   
   public String getNombreUsuario(long id){
      MuUsuario u = ejbUsuario.find(id);
      String result = "";
      if(u!=null){
         result += u.getUsuNombres()+" "+u.getUsuApellido1()+" "+u.getUsuApellido2();
      }
      return result;
   }
   
   public void registrarRecaudo(long usr, MrecReciboPago rp){
      ejbReciboPago.create(rp);
      MrecInformacionRecaudo ir = new MrecInformacionRecaudo();
      BigInteger ref = BigInteger.valueOf(rp.getRpagReferencia());
      ir.setRpagReferencia(ref);
      ir.setUsuIdentificacion(usr);
      ir.setIrecEstadoRecaudo("Por pagar");
      Calendar c = Calendar.getInstance();
      ir.setIrecMes(c.get(Calendar.MONTH)+1);
      ir.setIrecAnio(c.get(Calendar.YEAR));
      c.setTime(rp.getRpagFechaExpedicion());
      c.add(Calendar.DAY_OF_YEAR, 2);
      ir.setIrecFechaLimite(c.getTime());
      ejbInfoRecaudo.create(ir);
   }

   public void eliminarRecaudo(MrecRecaudo r){
      MrecReciboPago rp = r.getRecibo();
      ejbReciboPago.remove(rp);
      MrecInformacionRecaudo ir = putInfo(r);
      ejbInfoRecaudo.remove(ir);
   }
   
   public void registrarPago(MrecRecaudo r){
      MrecInformacionRecaudo ir = putInfo(r);
      ir.setIrecEstadoRecaudo("Pagado");
      ejbInfoRecaudo.edit(ir);
   }
   
   public void generarRecibo(MrecRecaudo r, MrecReciboPago rp){
      MrecInformacionRecaudo ir = putInfo(r);
      ejbReciboPago.create(rp);
      BigInteger ref = BigInteger.valueOf(rp.getRpagReferencia());
      ir.setRpagReferencia(ref);
      ir.setIrecEstadoRecaudo("Por pagar");
      ejbInfoRecaudo.edit(ir);
   }
  
   private MrecInformacionRecaudo putInfo(MrecRecaudo r){
      MrecInformacionRecaudo ir = new MrecInformacionRecaudo();
      ir.setIrecId(r.getId());
      if(r.getRecibo()!=null){
         BigInteger ref = BigInteger.valueOf(r.getRecibo().getRpagReferencia());
         ir.setRpagReferencia(ref);
      }
      ir.setUsuIdentificacion(r.getUsuario().getUsuIdentificacion());
      ir.setIrecEstadoRecaudo(r.getEstado());
      ir.setIrecAnio(r.getAnio());
      ir.setIrecMes(r.getMes());
      ir.setIrecFechaLimite(r.getFechaLimite());
      return ir;
   }

   public List<String> getOcupaciones(){
      List<String> data = new ArrayList<>();
      
      List<MuOcupacion> rta = ejbOcupacion.findAll();
      rta.stream().forEach((o) -> {
         data.add(o.getOcuDescripcion());
      });
      
      return data;
   }
   
   /*public DonutChartModel getDonut(Date ini, Date fin, boolean mensualidad, boolean sesiones,
                                   String[] ocupaciones){
      
      String m = " AND rp.rpagMensualidad = 0 ";
      String s = " AND rp.rpagNumeroSesiones = 0 ";
      
      if(mensualidad){
         m = " AND rp.rpagMensualidad = 1 ";
      }
      if(sesiones){
         s = " AND rp.rpagNumeroSesiones > 0 ";
      }
      
      DonutChartModel donut = new DonutChartModel();
      Map<String, Number> hombres = new LinkedHashMap<>();
      Map<String, Number> mujeres = new LinkedHashMap<>();
      
      for (String o : ocupaciones) {
         
         Query queryM = getEm().createQuery("SELECT rp FROM MrecInformacionRecaudo ir, MrecReciboPago rp, MuUsuario us, MuOcupacion oc WHERE rp.rpagReferencia = ir.rpagReferencia AND us.usuIdentificacion = ir.usuIdentificacion AND oc.ocuId = us.ocuId.ocuId AND us.usuGenero = :genero AND oc.ocuDescripcion = :ocupacion AND rp.rpagFechaExpedicion BETWEEN :fini AND :ffin"+m+s);
         queryM.setParameter("genero", "M");
         queryM.setParameter("ocupacion", o);
         queryM.setParameter("fini", ini);
         queryM.setParameter("ffin", fin);
         
         Query queryF = getEm().createQuery("SELECT rp FROM MrecInformacionRecaudo ir, MrecReciboPago rp, MuUsuario us, MuOcupacion oc WHERE rp.rpagReferencia = ir.rpagReferencia AND us.usuIdentificacion = ir.usuIdentificacion AND oc.ocuId = us.ocuId.ocuId AND us.usuGenero = :genero AND oc.ocuDescripcion = :ocupacion AND rp.rpagFechaExpedicion BETWEEN :fini AND :ffin"+m+s);
         queryF.setParameter("genero", "F");
         queryF.setParameter("ocupacion", o);
         queryF.setParameter("fini", ini);
         queryF.setParameter("ffin", fin);
         
         List<MrecReciboPago> lstRtaM = queryM.getResultList();
         int rtaM = lstRtaM.size();
         
         List<MrecReciboPago> lstRtaF = queryF.getResultList();
         int rtaF = lstRtaF.size();
         
         hombres.put(o, ((Number)rtaM).intValue());
         mujeres.put(o, ((Number)rtaF).intValue());
         
      }
      donut.addCircle(mujeres);
      donut.addCircle(hombres);
      
      donut.setTitle("Circulo interior (Mujeres) - Circulo exterior (Hombres)");
      donut.setLegendPosition("e");
      donut.setSliceMargin(5);
      donut.setShowDataLabels(true);
      donut.setDataFormat("value");
      
      return donut;
   }*/
   
   public HorizontalBarChartModel getBar(Date ini, Date fin, boolean mensualidad, boolean sesiones,
                                   String[] ocupaciones){
      HorizontalBarChartModel bar = new HorizontalBarChartModel();
      ChartSeries hombres = new ChartSeries();
      hombres.setLabel("Hombres");
      ChartSeries mujeres = new ChartSeries();
      mujeres.setLabel("Mujeres");
      int maxData = 0;
      
      String conceptos;
      
      if(mensualidad && sesiones){
         conceptos = " AND rp.rpagMensualidad = 1 AND rp.rpagNumeroSesiones > 0";
      }else if(mensualidad && !sesiones){
         conceptos = " AND rp.rpagMensualidad = 1";
      }else if(!mensualidad && sesiones){
         conceptos = " AND rp.rpagNumeroSesiones > 0 ";
      }else{
         conceptos = " AND rp.rpagMensualidad = 0 AND rp.rpagNumeroSesiones = 0 ";
      }
      
      for (String o : ocupaciones) {
         
         Query queryM = getEm().createQuery("SELECT rp FROM MrecInformacionRecaudo ir, MrecReciboPago rp, MuUsuario us, MuOcupacion oc WHERE rp.rpagReferencia = ir.rpagReferencia AND us.usuIdentificacion = ir.usuIdentificacion AND oc.ocuId = us.ocuId.ocuId AND us.usuGenero = :genero AND oc.ocuDescripcion = :ocupacion AND rp.rpagFechaExpedicion BETWEEN :fini AND :ffin"+conceptos);
         queryM.setParameter("genero", "M");
         queryM.setParameter("ocupacion", o);
         queryM.setParameter("fini", ini);
         queryM.setParameter("ffin", fin);
         
         Query queryF = getEm().createQuery("SELECT rp FROM MrecInformacionRecaudo ir, MrecReciboPago rp, MuUsuario us, MuOcupacion oc WHERE rp.rpagReferencia = ir.rpagReferencia AND us.usuIdentificacion = ir.usuIdentificacion AND oc.ocuId = us.ocuId.ocuId AND us.usuGenero = :genero AND oc.ocuDescripcion = :ocupacion AND rp.rpagFechaExpedicion BETWEEN :fini AND :ffin"+conceptos);
         queryF.setParameter("genero", "F");
         queryF.setParameter("ocupacion", o);
         queryF.setParameter("fini", ini);
         queryF.setParameter("ffin", fin);
         
         List<MrecReciboPago> lstRtaM = queryM.getResultList();
         int rtaM = lstRtaM.size();
         if(rtaM>maxData){
            maxData=rtaM+5;
         }
         List<MrecReciboPago> lstRtaF = queryF.getResultList();
         int rtaF = lstRtaF.size();
         if(rtaF>maxData){
            maxData=rtaF+5;
         }
         hombres.set(o, ((Number)rtaM).intValue());
         mujeres.set(o, ((Number)rtaF).intValue());
         
      }
      
      bar.addSeries(hombres);
      bar.addSeries(mujeres);
      
      bar.setTitle("Horizontal Bar");
      bar.setLegendPosition("e");
      bar.setStacked(true);
      
      Axis xAxis = bar.getAxis(AxisType.X);
      xAxis.setLabel("Cantidad de Personas por ocupacion");
      xAxis.setMin(0);
      xAxis.setMax(maxData);
      xAxis.setTickFormat("%d");
      
      Axis yAxis = bar.getAxis(AxisType.Y);
      yAxis.setLabel("Ocupacion");
      return bar;
   }
   
   private EntityManager getEm() {
        return em;
    }
   
}
