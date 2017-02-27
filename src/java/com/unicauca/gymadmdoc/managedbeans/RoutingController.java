/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.managedbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Ricardo
 */
@Named(value = "routingController")
@SessionScoped
public class RoutingController implements Serializable {

    /*
    //INICIO NO TOCAR, MODULO RUTINAS
    @ManagedProperty("#{param.usuarioIdentificacion}")
    String usuarioIdentificacion;
    //FIN NO TOCAR MODULO RUTINAS
    */
    
    private String ruta;

    public String getRuta() {
        return ruta;
    }

    public RoutingController() {
        this.ruta = "/administrador/mrec/estadisticas.xhtml";
    }

    public void goToReporteMensualMREC() {
        this.ruta = "/administrador/mrec/reporte_mensual.xhtml";
    }

    public void goToNuevoRecaudoMREC() {
        this.ruta = "/administrador/mrec/nuevo_recaudo.xhtml";
    }

    public void goToEstadisticasMREC() {
        this.ruta = "/administrador/mrec/estadisticas.xhtml";
    }

    public void irValoracion() {
        this.ruta = "/administrador/mv/mvValoracion/List.xhtml";
    }

    public void irVerResultado() {
        this.ruta = "/administrador/mv/mvValoracion/verResultado.xhtml";
    }

    public void irSiguientesValoraciones() {
        this.ruta = "/administrador/mv/mvSiguientevaloracion/List.xhtml";
    }

    public void irDiasFestivos() {
        this.ruta = "/administrador/mv/mvFestivos/List.xhtml";
    }

    public void goToNuevaRutina() {
     
        this.ruta = "/administrador/mru/nuevaRutina.xhtml";
    }

    public void goToNuevoDia() {
        this.ruta = "/administrador/mru/nuevoDia.xhtml";
    }

    public void goToUsuarios_Rutinas() {
        this.ruta = "/administrador/mru/muUsuario/ListarUsuariariosRutinas.xhtml";
    }

    public void goToVerRutinasDeUsuario() {
        this.ruta = "/administrador/mru/ListarRutinas.xhtml";
    }

    public void goToListarRutinasDeUsuario() {
        this.ruta = "/administrador/mru/ListarRutinasDeUsuario.xhtml";
    }

    public void goToEjercicios() {
        this.ruta = "/administrador/mru/mruEjercicio/ListEjercicio.xhtml";
    }

    public void irGestionUsuario() {
        this.ruta = "/administrador/mu/gestionUsuario.xhtml";
    }

    public void irRegistroIngreso() {
        this.ruta = "/administrador/mu/registroIngreso.xhtml";
    }

    public void irPerfil() {
        this.ruta = "/usuariosestandar/mu/perfilUsuario.xhtml";
    }

    public void goToGP() {
        this.ruta = "/administrador/mru/mruGrupoMuscular/ListarGruposMusculares.xhtml";
    }
    public void goToRelacionarEjercicios() {
        this.ruta = "/administrador/mru/grupomuscRealizaEjercicio/ListarAsignaciones.xhtml";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
    //NO TOCAR MODULO RUTINAS
    public String getUsuarioIdentificacion() {
        return usuarioIdentificacion;
    }

    public void setUsuarioIdentificacion(String usuarioIdentificacion) {
        this.usuarioIdentificacion = usuarioIdentificacion;
    }
    //NO TOCAR MODULO RUTINAS
    */
    
    

}
