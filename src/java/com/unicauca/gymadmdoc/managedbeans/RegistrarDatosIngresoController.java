/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.managedbeans;

import static com.sun.faces.facelets.util.Path.context;
import com.unicauca.gymadmdoc.cifrado.Cifrar;
import com.unicauca.gymadmdoc.entities.MuExamenFisico;
import com.unicauca.gymadmdoc.entities.MuFacultadDependencia;
import com.unicauca.gymadmdoc.entities.MuMedicamento;

import com.unicauca.gymadmdoc.entities.MuOcupacion;
import com.unicauca.gymadmdoc.entities.MuUsuario;
import com.unicauca.gymadmdoc.entities.MuUsuariogrupo;
import com.unicauca.gymadmdoc.entities.MuUsuariogrupoPK;
import com.unicauca.gymadmdoc.sessionbeans.MuExamenFisicoFacade;
import com.unicauca.gymadmdoc.sessionbeans.MuFacultadDependenciaFacade;
import com.unicauca.gymadmdoc.sessionbeans.MuMedicamentoFacade;
import com.unicauca.gymadmdoc.sessionbeans.MuOcupacionFacade;
import com.unicauca.gymadmdoc.sessionbeans.MuUsuarioFacade;
import com.unicauca.gymadmdoc.sessionbeans.MuUsuariogrupoFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class RegistrarDatosIngresoController implements Serializable {

    @EJB
    private MuFacultadDependenciaFacade facultadDependenciaEJB;
    @EJB
    private MuUsuarioFacade usuarioEJB;
    @EJB
    private MuOcupacionFacade ocupacionEJB;
    @EJB
    private MuUsuariogrupoFacade usuarioGrupoEJB;
    @EJB
    private MuMedicamentoFacade medicamentoEJB;
    @EJB
    private MuExamenFisicoFacade examenFisicoEJB;

    private List<String> listaTipo;
    private boolean camposRegistroEstudiante;
    private boolean camposRegistroFuncionario;
    private boolean camposRegistroFamiliar;
    private boolean camposRegistroFuncionarioDocente;
    private boolean camposRegistroFuncionarioAdministrativo;
    private boolean consumeMedicanmentos;
    private float peso;
    private float talla;
    private float fc;
    private float fcm;
    private float ta;
    private float fr;
    private float imc;
    private float icc;
    private Date fecha;
    private String consume;
    private List<MuFacultadDependencia> listaFacultadesYDependencias;
    private List<MuOcupacion> listaOcupaciones;
    private MuUsuario usuario;
    private MuFacultadDependencia facultadDependencia;
    private MuOcupacion ocupacion;
    private MuMedicamento medicamento;
    private MuExamenFisico examenFisico;
    private String contrasena;
    private String repetircontrasena;
    private String numeroIdentificacion;
    private String codigo;
    private List<MuUsuario> listaFuncionarios;
    private MuUsuario funcionario;
    private String nombreOApellidos;
    private boolean usuarioSeleccionado;

    public RegistrarDatosIngresoController() {
        this.cargarListaTipo();
        this.inicializarCamposUsuarioEspecificos();
        this.usuario = new MuUsuario();
        this.usuario.setUsuGenero("M");

    }

    @PostConstruct
    private void init() {
        this.facultadDependencia = new MuFacultadDependencia();
        this.cargarListaFacultades();
    }

    public String getNombreOApellidos() {
        return nombreOApellidos;
    }

    public void setNombreOApellidos(String nombreOApellidos) {
        this.nombreOApellidos = nombreOApellidos;
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean isUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(boolean usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public MuUsuario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(MuUsuario funcionario) {
        this.funcionario = funcionario;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getTalla() {
        return talla;
    }

    public void setTalla(float talla) {
        this.talla = talla;
    }

    public float getFc() {
        return fc;
    }

    public void setFc(float fc) {
        this.fc = fc;
    }

    public float getFcm() {
        return fcm;
    }

    public void setFcm(float fcm) {
        this.fcm = fcm;
    }

    public float getTa() {
        return ta;
    }

    public void setTa(float ta) {
        this.ta = ta;
    }

    public float getFr() {
        return fr;
    }

    public void setFr(float fr) {
        this.fr = fr;
    }

    public float getImc() {
        return imc;
    }

    public void setImc(float imc) {
        this.imc = imc;
    }

    public float getIcc() {
        return icc;
    }

    public void setIcc(float icc) {
        this.icc = icc;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isConsumeMedicanmentos() {
        return consumeMedicanmentos;
    }

    public void setConsumeMedicanmentos(boolean consumeMedicanmentos) {
        this.consumeMedicanmentos = consumeMedicanmentos;
    }

    public String getConsume() {
        return consume;
    }

    public void setConsume(String consume) {
        this.consume = consume;
    }

    public List<MuUsuario> getListaFuncionarios() {
        return listaFuncionarios;
    }

    public void setListaFuncionarios(List<MuUsuario> listaFuncionarios) {
        this.listaFuncionarios = listaFuncionarios;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRepetircontrasena() {
        return repetircontrasena;
    }

    public void setRepetircontrasena(String repetircontrasena) {
        this.repetircontrasena = repetircontrasena;
    }

    public MuOcupacion getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(MuOcupacion ocupacion) {
        this.ocupacion = ocupacion;
    }

    public boolean isCamposRegistroFuncionarioDocente() {
        return camposRegistroFuncionarioDocente;
    }

    public void setCamposRegistroFuncionarioDocente(boolean camposRegistroFuncionarioDocente) {
        this.camposRegistroFuncionarioDocente = camposRegistroFuncionarioDocente;
    }

    public boolean isCamposRegistroFuncionarioAdministrativo() {
        return camposRegistroFuncionarioAdministrativo;
    }

    public void setCamposRegistroFuncionarioAdministrativo(boolean camposRegistroFuncionarioAdministrativo) {
        this.camposRegistroFuncionarioAdministrativo = camposRegistroFuncionarioAdministrativo;
    }

    public List<MuOcupacion> getListaOcupaciones() {
        return listaOcupaciones;
    }

    public void setListaCargos(List<MuOcupacion> listaOcupaciones) {
        this.listaOcupaciones = listaOcupaciones;
    }

    public MuFacultadDependencia getFacultadDependencia() {
        return facultadDependencia;
    }

    public void setFacultad(MuFacultadDependencia facultadDependencia) {
        this.facultadDependencia = facultadDependencia;
    }

    public MuUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(MuUsuario usuario) {
        this.usuario = usuario;
    }

    public List<MuFacultadDependencia> getListaFacultadesYDependencias() {
        return listaFacultadesYDependencias;
    }

    public void setListaFacultades(List<MuFacultadDependencia> listaFacultadesYDependencias) {
        this.listaFacultadesYDependencias = listaFacultadesYDependencias;
    }

    public boolean isCamposRegistroFuncionario() {
        return camposRegistroFuncionario;
    }

    public void setCamposRegistroFuncionario(boolean camposRegistroFuncionario) {
        this.camposRegistroFuncionario = camposRegistroFuncionario;
    }

    public boolean isCamposRegistroFamiliar() {
        return camposRegistroFamiliar;
    }

    public void setCamposRegistroFamiliar(boolean camposRegistroFamiliar) {
        this.camposRegistroFamiliar = camposRegistroFamiliar;
    }

    public boolean isCamposRegistroEstudiante() {
        return camposRegistroEstudiante;
    }

    public void setCamposRegistroEstudiante(boolean camposRegistroEstudiante) {
        this.camposRegistroEstudiante = camposRegistroEstudiante;
    }

    public List<String> getListaTipo() {
        return listaTipo;
    }

    public void setListaTipo(List<String> listaTipo) {
        this.listaTipo = listaTipo;
    }

    private void cargarListaFacultades() {
        this.listaFacultadesYDependencias = facultadDependenciaEJB.findAll();
    }

    private void cargarListaOcupaciones() {
        this.listaOcupaciones = ocupacionEJB.findAll();
    }

    private void inicializarCamposUsuarioEspecificos() {
        this.camposRegistroEstudiante = true;
        this.camposRegistroFamiliar = false;
        this.camposRegistroFuncionario = false;
        this.camposRegistroFuncionarioDocente = false;
        this.camposRegistroFuncionarioAdministrativo = false;
        this.usuarioSeleccionado = false;
        this.consumeMedicanmentos = false;
    }

    private void cargarListaTipo() {
        listaTipo = new ArrayList();
        listaTipo.add("Estudiante");
        listaTipo.add("Familiar");
        listaTipo.add("Funcionario");
    }

    private void cargarListaFuncionarios() {
        this.listaFuncionarios = usuarioEJB.buscarPorFuncionario();
    }

    public void respuesta(ValueChangeEvent e) {
        System.out.println("dato consume: " + e.getNewValue());
        if (this.consume.equalsIgnoreCase("Si")) {
            consumeMedicanmentos = true;
        } else {
            consumeMedicanmentos = false;
        }
        

    }

    public void abrirVentanaExamenFisico() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
        requestContext.update("form:panel");
        requestContext.update("form");
        requestContext.execute("PF('RegistrarExamenFisico').show()");
    }

    public void abrirVentanaMedicamentos() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
        requestContext.update("form:panel");
        requestContext.update("form");
        requestContext.execute("PF('RegistrarMedicamento').show()");
    }

    public void abrirVentanaEvaluacion() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
        requestContext.update("form:panel");
        requestContext.update("form");
        requestContext.execute("PF('RegistrarEvaluacion').show()");
    }

    public void cambiarTipoUsuario(ValueChangeEvent e) {
        String tipo = e.getNewValue().toString();
        this.camposRegistroEstudiante = false;
        this.camposRegistroFamiliar = false;
        this.camposRegistroFuncionario = false;
        this.camposRegistroFuncionarioDocente = false;
        this.camposRegistroFuncionarioAdministrativo = false;
        this.listaFacultadesYDependencias = null;
        this.facultadDependencia = null;
        this.ocupacion = null;
        this.listaFuncionarios = null;
        this.funcionario = null;
        if (tipo.equals("Estudiante")) {
            this.facultadDependencia = new MuFacultadDependencia();
            this.camposRegistroEstudiante = true;
            this.cargarListaFacultades();
        }
        if (tipo.equals("Funcionario")) {
            this.ocupacion = new MuOcupacion();
            this.facultadDependencia = new MuFacultadDependencia();
            this.cargarListaFacultades();
            this.camposRegistroFuncionarioDocente = true;
            this.camposRegistroFuncionario = true;
            this.cargarListaOcupaciones();
        }
        if (tipo.equals("Familiar")) {
            this.funcionario = new MuUsuario();
            this.funcionario.setFacDepId(new MuFacultadDependencia());
            this.cargarListaFuncionarios();
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("funcionarios");
            this.camposRegistroFamiliar = true;
        }
    }

    public void cambiarOcupacionFuncionario(ValueChangeEvent e) {
        String tipo = e.getNewValue().toString();
        this.camposRegistroFuncionarioDocente = false;
        this.camposRegistroFuncionarioAdministrativo = false;
        this.listaFacultadesYDependencias = null;

        this.facultadDependencia = null;

        if (tipo.equals("1")) {
            this.facultadDependencia = new MuFacultadDependencia();
            this.cargarListaFacultades();
            this.camposRegistroFuncionarioDocente = true;
        }

    }

    public List<MuFacultadDependencia> listarFacultades() {
        return facultadDependenciaEJB.findAll();
    }

    public void buscarPorNombreFuncionario() {

        this.listaFuncionarios = usuarioEJB.busacarPorNombreFuncionario(this.nombreOApellidos.toLowerCase());

    }

    public void validateContrasena(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {

        this.contrasena = String.valueOf(arg2);
    }

    public void validateRepitaContrasena(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
        String texto = String.valueOf(arg2);
        if (!(texto.equals(this.contrasena))) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Las contraseñas no coinciden."));

        }

    }

    public void seleccionarUsuario(MuUsuario usuario) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('seleccionarUsuario').hide()");
        this.usuario = usuario;
        this.usuarioSeleccionado = true;
        System.out.println("Nombre: " + usuario.getUsuNombres());
        requestContext.update("usuarioSeleccionado");
        requestContext.update("datosIngreso");
        requestContext.update("registroDatosIngreso");
    }

    public void registrarUsuario() {

        if (this.funcionario != null) {
            if (this.funcionario.getUsuNombres() == null) {
                this.usuarioSeleccionado = true;
            } else {
                this.usuario.setUsuIdentificacion(Long.parseLong(this.numeroIdentificacion));
                this.usuario.setUsuContrasena(Cifrar.sha256(this.contrasena));
                //this.usuario.setConyugeid(this.funcionario);
                this.usuarioEJB.create(this.usuario);
                MuUsuariogrupo usuarioGrupo = new MuUsuariogrupo();
                MuUsuariogrupoPK usuarioGrupoPK = new MuUsuariogrupoPK();
                usuarioGrupoPK.setGruId("user");
                usuarioGrupoPK.setUsuIdentificacion(this.usuario.getUsuIdentificacion());
                usuarioGrupo.setMuUsuariogrupoPK(usuarioGrupoPK);
                usuarioGrupo.setUsuNombreUsuario(this.usuario.getUsuNombreUsuario());
                this.usuarioGrupoEJB.create(usuarioGrupo);

                RequestContext requestContext = RequestContext.getCurrentInstance();
                FacesContext context = FacesContext.getCurrentInstance();
                Application application = context.getApplication();
                ViewHandler viewHandler = application.getViewHandler();
                UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
                context.setViewRoot(viewRoot);
                context.renderResponse();
                this.usuario = new MuUsuario();
                this.cargarListaTipo();
                this.inicializarCamposUsuarioEspecificos();
                this.facultadDependencia = new MuFacultadDependencia();
                this.cargarListaFacultades();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Registro Exitoso."));
                requestContext.execute("PF('mensajeRegistroExitoso').show()");
            }
        } else {
            if (this.ocupacion != null) {
                this.usuario.setOcuId(this.ocupacion);
            }
            if (this.facultadDependencia != null) {
                this.usuario.setFacDepId(this.facultadDependencia);
            }

            this.usuario.setUsuIdentificacion(Long.parseLong(this.numeroIdentificacion));
            this.usuario.setUsuContrasena(Cifrar.sha256(this.contrasena));
            this.usuarioEJB.create(this.usuario);
            MuUsuariogrupo usuarioGrupo = new MuUsuariogrupo();
            MuUsuariogrupoPK usuarioGrupoPK = new MuUsuariogrupoPK();
            usuarioGrupoPK.setGruId("user");
            usuarioGrupoPK.setUsuIdentificacion(this.usuario.getUsuIdentificacion());
            usuarioGrupo.setMuUsuariogrupoPK(usuarioGrupoPK);
            usuarioGrupo.setUsuNombreUsuario(this.usuario.getUsuNombreUsuario());
            this.usuarioGrupoEJB.create(usuarioGrupo);

            RequestContext requestContext = RequestContext.getCurrentInstance();
            FacesContext context = FacesContext.getCurrentInstance();
            Application application = context.getApplication();
            ViewHandler viewHandler = application.getViewHandler();
            UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
            context.setViewRoot(viewRoot);
            context.renderResponse();
            this.usuario = new MuUsuario();
            this.cargarListaTipo();
            this.inicializarCamposUsuarioEspecificos();
            this.facultadDependencia = new MuFacultadDependencia();
            this.cargarListaFacultades();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Registro Exitoso."));
            requestContext.execute("PF('mensajeRegistroExitoso').show()");
        }
    }

    public void registrarExamenFisico() {

        this.examenFisico.setEfisFecha(fecha);
        this.examenFisico.setEfisPeso(peso);
        this.examenFisico.setEfisTalla(talla);
        this.examenFisico.setEfisFc(fc);
        this.examenFisico.setEfisFcm(fcm);
        this.examenFisico.setEfisTa(ta);
        this.examenFisico.setEfisFr(fr);
        this.examenFisico.setEfisIcc(icc);
        this.examenFisico.setEfisImc(imc);
        this.examenFisico.setUsuIdentificacion(usuario);
        this.examenFisicoEJB.create(examenFisico);

        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Registro Exitoso."));
        requestContext.execute("PF('mensajeRegistroExitoso').show()");

    }

}
