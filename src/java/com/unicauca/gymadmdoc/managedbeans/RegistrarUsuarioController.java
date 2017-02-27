/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.managedbeans;

import com.unicauca.gymadmdoc.cifrado.Cifrar;
import com.unicauca.gymadmdoc.entities.MuFacultadDependencia;
import com.unicauca.gymadmdoc.entities.MuOcupacion;
import com.unicauca.gymadmdoc.entities.MuUsuario;
import com.unicauca.gymadmdoc.entities.MuUsuariogrupo;
import com.unicauca.gymadmdoc.entities.MuUsuariogrupoPK;
import com.unicauca.gymadmdoc.sessionbeans.MuFacultadDependenciaFacade;
import com.unicauca.gymadmdoc.sessionbeans.MuOcupacionFacade;
import com.unicauca.gymadmdoc.sessionbeans.MuUsuarioFacade;
import com.unicauca.gymadmdoc.sessionbeans.MuUsuariogrupoFacade;
import com.unicauca.gymadmdoc.utilidades.RedimensionadorImagenes;
import com.unicauca.gymadmdoc.utilidades.Utilidades;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
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
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class RegistrarUsuarioController implements Serializable {

    @EJB
    private MuFacultadDependenciaFacade facultadDependenciaEJB;
    @EJB
    private MuUsuarioFacade usuarioEJB;
    @EJB
    private MuOcupacionFacade ocupacionEJB;
    @EJB
    private MuUsuariogrupoFacade usuarioGrupoEJB;

    private List<String> listaTipo;

    private boolean camposRegistroFamiliar;
    private boolean campoFoto;
    private List<MuFacultadDependencia> listaFacultadesYDependencias;

    private List<MuOcupacion> listaOcupaciones;
    private MuUsuario usuario;
    private MuFacultadDependencia facultadDependencia;
    private boolean ocupacionSeleccionada;

    private MuOcupacion ocupacion;
    private String contrasena;
    private String repetircontrasena;
    private String numeroIdentificacion;
    private String codigo;
    private List<MuUsuario> listaUsuarios;
    private MuUsuario funcionario;
    private String nombreOApellidos;
    private boolean usuarioSeleccionado;
    private UploadedFile uploadedFileFoto;

    public RegistrarUsuarioController() {
        this.cargarListaTipo();
        this.inicializarCamposUsuarioEspecificos();
        this.usuario = new MuUsuario();
        this.usuario.setUsuGenero("M");
        this.usuario.setUsuEstado("Activo");
    }

    @PostConstruct
    private void init() {
        this.ocupacion = new MuOcupacion();
        this.cargarListaOcupaciones();
        this.listarUsuarios();
    }

    public boolean isCampoFoto() {
        return campoFoto;
    }

    public void setCampoFoto(boolean campoFoto) {
        this.campoFoto = campoFoto;
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

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public UploadedFile getUploadedFileFoto() {
        return uploadedFileFoto;
    }

    public void setUploadedFileFoto(UploadedFile uploadedFileFoto) {
        this.uploadedFileFoto = uploadedFileFoto;
    }

    public boolean isOcupacionSeleccionada() {
        return ocupacionSeleccionada;
    }

    public void setOcupacionSeleccionada(boolean ocupacionSeleccionada) {
        this.ocupacionSeleccionada = ocupacionSeleccionada;
    }

    public MuUsuario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(MuUsuario funcionario) {
        this.funcionario = funcionario;
    }

    public List<MuUsuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<MuUsuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
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

    public List<MuOcupacion> getListaOcupaciones() {
        return listaOcupaciones;
    }

    public void setListaCargos(List<MuOcupacion> listaOcupaciones) {
        this.listaOcupaciones = listaOcupaciones;
    }

    public MuFacultadDependencia getFacultadDependencia() {
        return facultadDependencia;
    }

    public void setFacultadDependencia(MuFacultadDependencia facultadDependencia) {
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

    public void setListaFacultadesYDependencias(List<MuFacultadDependencia> listaFacultadesYDependencias) {
        this.listaFacultadesYDependencias = listaFacultadesYDependencias;
    }

    public boolean isCamposRegistroFamiliar() {
        return camposRegistroFamiliar;
    }

    public void setCamposRegistroFamiliar(boolean camposRegistroFamiliar) {
        this.camposRegistroFamiliar = camposRegistroFamiliar;
    }

    public List<String> getListaTipo() {
        return listaTipo;
    }

    public void setListaTipo(List<String> listaTipo) {
        this.listaTipo = listaTipo;
    }

    private void cargarListaFacultadesYDependencias() {
        this.listaFacultadesYDependencias = facultadDependenciaEJB.findAll();
    }

    private void cargarListaOcupaciones() {
        this.listaOcupaciones = ocupacionEJB.findAll();
    }

    private void inicializarCamposUsuarioEspecificos() {
        this.ocupacionSeleccionada = false;
    }

    private void cargarListaTipo() {
        listaTipo = new ArrayList();
        listaTipo.add("Estudiante");
        listaTipo.add("Familiar");
        listaTipo.add("Funcionario");

    }

    private void cargarListaFuncionarios() {
        this.listaUsuarios = usuarioEJB.buscarPorFuncionario();
    }

    public void buscarPorNombresOApellidos() {
        this.listaUsuarios = usuarioEJB.buscarPorNombresApellidos(this.nombreOApellidos.toLowerCase());
    }

    public void buscarPorIdentificacion() {
        this.listaUsuarios = usuarioEJB.buscarPorIdUsuario(Long.parseLong(numeroIdentificacion));
    }

    public void abrirVentanaRegistrarUsuario() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
        requestContext.update("form:panel");
        requestContext.update("seleccionarUsuarios");
        requestContext.update("tablasUsuarios");
        requestContext.execute("PF('RegistrarUsuario').show()");
    }

    public void cambiarOcupacionUsuario(ValueChangeEvent e) {
        String ocupacionSelect = e.getNewValue().toString();

        this.ocupacionSeleccionada = false;
        this.listaFacultadesYDependencias = null;
        this.facultadDependencia = null;

        if (ocupacionSelect.equals("1") || ocupacionSelect.equals("6") || ocupacionSelect.equals("11") || ocupacionSelect.equals("12") || ocupacionSelect.equals("13")) {
            this.facultadDependencia = new MuFacultadDependencia();
            this.ocupacionSeleccionada = true;
            this.cargarListaFacultadesYDependencias();
        }

    }

    public List<MuFacultadDependencia> listarFacultades() {
        return facultadDependenciaEJB.findAll();
    }

    public void buscarUsuarios() {
        this.listaUsuarios = usuarioEJB.buscarTodos();
    }

    public void listarUsuarios() {
        this.listaUsuarios = usuarioEJB.buscarTodos();
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

    public void seleccionarFuncionario(MuUsuario usuario) {
        /*RequestContext requestContext = RequestContext.getCurrentInstance();
           requestContext.execute("PF('seleccionarUsuario').hide()");
           this.usuario=usuario;
           this.usuarioSeleccionado=false;
           requestContext.update("form:datosIngreso"); 
           requestContext.update("form:registroDatosIngreso");       */
    }

    protected void setEmbeddableKeys() {
    }

    public void cargarFoto(FileUploadEvent event) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.uploadedFileFoto = event.getFile();
        requestContext.update("formularioFoto");

    }

    public void cancelarSubirFoto() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoFoto = false;
        this.uploadedFileFoto = null;
        requestContext.update("formularioFoto");
    }

    public StreamedContent getImagenFlujo() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {

            String id = context.getExternalContext().getRequestParameterMap().get("idUsu");
            MuUsuario usu = usuarioEJB.buscarPorIdUsuario(Long.valueOf(id)).get(0);
            if (usuario.getUsuFoto() == null) {
                return Utilidades.getImagenPorDefecto("foto");
            } else {
                return new DefaultStreamedContent(new ByteArrayInputStream(usu.getUsuFoto()));
            }
        }
    }

    public void mostraSubirFoto() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoFoto = false;
        requestContext.update("formularioFoto");
    }

    public void registrarUsuario() {
        /*System.out.println("nombre: "+usuario.getUsuNombres());
            System.out.println("apellido 1: "+usuario.getUsuApellido1());
            System.out.println("apellido 2: "+usuario.getUsuApellido2());
            System.out.println("cedula "+usuario.getUsuIdentificacion());
            System.out.println("codigo "+usuario.getUsuCodigo());
            System.out.println("fecha "+usuario.getUsuFechaNacimiento());
            System.out.println("correo "+usuario.getUsuEmail());
            System.out.println("telefono "+usuario.getUsuTelefono());
            System.out.println("cel "+usuario.getUsuCelular());
            System.out.println("usuario "+usuario.getUsuNombreUsuario());
            System.out.println("ocupacion "+usuario.getOcuId());
            System.out.println("facultad "+usuario.getFacDepId());
            System.out.println("genero "+usuario.getUsuGenero());
            System.out.println("estado "+usuario.getUsuEstado());*/

        if (this.ocupacion != null) {
            this.usuario.setOcuId(this.ocupacion);
        }
        if (this.facultadDependencia != null) {
            this.usuario.setFacDepId(this.facultadDependencia);
        }
        this.usuario.setUsuContrasena(Cifrar.sha256(this.contrasena));
        this.usuarioEJB.create(this.usuario);
        try {
            agregarFoto();
        } catch (InterruptedException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al cargar la foto", "Error foto."));
        }
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
        this.cargarListaFacultadesYDependencias();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Registro Exitoso."));
        requestContext.execute("PF('mensajeRegistroExitoso').show()");
    }

    public void actualizarFoto() throws InterruptedException {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.uploadedFileFoto != null) {
            this.campoFoto = true;
            try {
                InputStream fi = uploadedFileFoto.getInputstream();
                byte[] buffer = RedimensionadorImagenes.redimensionar(fi, 200);
                usuario.setUsuFoto(buffer);
                this.usuarioEJB.edit(usuario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Foto Actualizada exitosamente. Fresione F5 para refrescarla", "Foto Actualizada."));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread.sleep(2000);
            this.uploadedFileFoto = null;

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se ha cargado una foto.", "No se ha cargado una foto"));
        }
        requestContext.update("formularioFoto");
    }

    public void agregarFoto() throws InterruptedException {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.uploadedFileFoto != null) {
            this.campoFoto = true;
            try {
                InputStream fi = uploadedFileFoto.getInputstream();
                byte[] buffer = RedimensionadorImagenes.redimensionar(fi, 200);
                usuario.setUsuFoto(buffer);
                this.usuarioEJB.edit(usuario);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread.sleep(2000);
            this.uploadedFileFoto = null;

        }
        requestContext.update("formularioFoto");
    }

    public void cambiarCargoFuncionario(ValueChangeEvent e) {
        String ocupacionSelect = e.getNewValue().toString();
        this.camposRegistroFamiliar = false;

        if (ocupacionSelect.equals("Docente")) {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("funcionarios");
            this.camposRegistroFamiliar = true;
        }
        if (ocupacionSelect.equals("Estudiante")) {
            this.camposRegistroFamiliar = false;
        }
    }
}
