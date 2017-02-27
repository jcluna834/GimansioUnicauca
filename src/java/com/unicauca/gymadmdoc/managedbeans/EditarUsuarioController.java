package com.unicauca.gymadmdoc.managedbeans;


import com.unicauca.gymadmdoc.cifrado.Cifrar;
import com.unicauca.gymadmdoc.entities.MuFacultadDependencia;
import com.unicauca.gymadmdoc.entities.MuOcupacion;
import com.unicauca.gymadmdoc.entities.MuUsuario;
import com.unicauca.gymadmdoc.sessionbeans.MuFacultadDependenciaFacade;
import com.unicauca.gymadmdoc.sessionbeans.MuOcupacionFacade;
import com.unicauca.gymadmdoc.sessionbeans.MuUsuarioFacade;
import com.unicauca.gymadmdoc.sessionbeans.MuUsuariogrupoFacade;
import com.unicauca.gymadmdoc.utilidades.RedimensionadorImagenes;
import com.unicauca.gymadmdoc.utilidades.Utilidades;
import com.unicauca.gymadmdoc.validadores.ValidarEdicionUsuarios;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;


/**
 *
 * @author geovanny
 */
@ManagedBean
@SessionScoped
public class EditarUsuarioController implements Serializable {

    @EJB
    private MuUsuarioFacade usuarioEJB;
    @EJB
    private MuOcupacionFacade ocupacionEJB;
    @EJB
    private MuFacultadDependenciaFacade facultadDependenciaEJB;
    @EJB
    private MuUsuariogrupoFacade usuarioGrupoEJB;
    private MostrarUsuariosController mostraUsuariosController;
    private MuUsuario usuario;
    private boolean camposFuncionario;
    private boolean camposEstudiante;
    private boolean camposFamiliar;
    private boolean campoFoto;
    private boolean campoFechaNacimiento;
    private boolean campoIdentificacion;
    private boolean campoCodigo;
    private boolean campoNombre;
    private boolean campoCorreo;
    private boolean campoGenero;
    private boolean campoNombreUsuario;
    private boolean campoContrasena;
    private boolean modificarDatosAcademicos;
    private boolean aceptarCancelarModificarDatosAcademicos;
    private boolean modificarDatosFuncionario;
    private boolean modificarDatosEstudiante;
    private boolean modificarDatosFamiliar;
    private String usuarioSeleccionado;
    private UploadedFile uploadedFileFoto;
    private String identificacion;
    private String nombres;
    private Date fechaNacimiento;
    private SimpleDateFormat sdf;
    private String correo;
    private String telefono;
    private String genero;
    private String nombreUsuario;
    private String contrasena;
    private String tipoUsuario;
    private String celular;
    private List<MuOcupacion> listaOcupaciones;
    private List<MuFacultadDependencia> listaFacultadesYDependencias;
    private Long idOcupacion;
    private Long idUnidadAcademica;
    private MuUsuario funcionarioFamiliar;
    private List<MuUsuario> listaFuncionarios;
    private String nombreOApellidos;

    private ValidarEdicionUsuarios validarEdicionUsuario;
    private String codigo;
    private String apellido1;
    private boolean campoApellido1;
    private boolean campoApellido2;
    private String apellido2;
    private boolean campoTelefono;
    private boolean campoCelular;
    private MuOcupacion ocupacion;
    private boolean campoOcupacion;
    private String ocupacionDes;
    private boolean campoFacultadDependencia;
    private String facultadDependenciaDes;
    private MuFacultadDependencia facultadDependencia;

    public EditarUsuarioController() {
        this.sdf = new SimpleDateFormat("yyyy-MM-dd");
    }
    /**
     * Recupera de la bd la imagen
     * @return el flujo de bytes de la imagen
     */
    public StreamedContent getImagenFlujo() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {

            String id = context.getExternalContext().getRequestParameterMap().get("idUsu");
            MuUsuario usu = usuarioEJB.buscarPorIdUsuario(Long.valueOf(id)).get(0);
            if (usuario.getUsuFoto()==null)
                return Utilidades.getImagenPorDefecto("foto");
            else
                return new DefaultStreamedContent(new ByteArrayInputStream(usu.getUsuFoto()));
        }
    }

    public String getNombreOApellidos() {
        return nombreOApellidos;
    }

    public void setNombreOApellidos(String nombreOApellidos) {
        this.nombreOApellidos = nombreOApellidos;
    }

    public List<MuUsuario> getListaFuncionarios() {
        return listaFuncionarios;
    }

    public void setListaFuncionarios(List<MuUsuario> listaFuncionarios) {
        this.listaFuncionarios = listaFuncionarios;
    }

    public MuUsuario getFuncionarioFamiliar() {
        return funcionarioFamiliar;
    }

    public void setFuncionarioFamiliar(MuUsuario funcionarioFamiliar) {
        this.funcionarioFamiliar = funcionarioFamiliar;
    }

    public boolean isModificarDatosFuncionario() {
        return modificarDatosFuncionario;
    }

    public boolean isCampoCodigo() {
        return campoCodigo;
    }

    public void setCampoCodigo(boolean campoCodigo) {
        this.campoCodigo = campoCodigo;
    }
    
    public void setModificarDatosFuncionario(boolean modificarDatosFuncionario) {
        this.modificarDatosFuncionario = modificarDatosFuncionario;
    }

    public boolean isModificarDatosEstudiante() {
        return modificarDatosEstudiante;
    }

    public void setModificarDatosEstudiante(boolean modificarDatosEstudiante) {
        this.modificarDatosEstudiante = modificarDatosEstudiante;
    }

    public boolean isModificarDatosFamiliar() {
        return modificarDatosFamiliar;
    }

    public void setModificarDatosFamiliar(boolean modificarDatosFamiliar) {
        this.modificarDatosFamiliar = modificarDatosFamiliar;
    }

    public Long getIdOcupacion() {
        return idOcupacion;
    }

    public void setIdOcupacion(Long idOcupacion) {
        this.idOcupacion = idOcupacion;
    }

    public Long getIdUnidadAcademica() {
        return idUnidadAcademica;
    }

    public void setIdUnidadAcademica(Long idUnidadAcademica) {
        this.idUnidadAcademica = idUnidadAcademica;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public List<MuOcupacion> getListaOcupaciones() {
        return listaOcupaciones;
    }

    public void setListaOcupaciones(List<MuOcupacion> listaOcupaciones) {
        this.listaOcupaciones = listaOcupaciones;
    }

    public List<MuFacultadDependencia> getListaFacultadesYDependencias() {
        return listaFacultadesYDependencias;
    }

    public void setFacultadesYDependencias(List<MuFacultadDependencia> listaFacultadesYDependencias) {
        this.listaFacultadesYDependencias = listaFacultadesYDependencias;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public boolean isModificarDatosAcademicos() {
        return modificarDatosAcademicos;
    }

    public void setModificarDatosAcademicos(boolean modificarDatosAcademicos) {
        this.modificarDatosAcademicos = modificarDatosAcademicos;
    }

    public boolean isAceptarCancelarModificarDatosAcademicos() {
        return aceptarCancelarModificarDatosAcademicos;
    }

    public void setAceptarCancelarModificarDatosAcademicos(boolean aceptarCancelarModificarDatosAcademicos) {
        this.aceptarCancelarModificarDatosAcademicos = aceptarCancelarModificarDatosAcademicos;
    }

    public boolean isCampoContrasena() {
        return campoContrasena;
    }

    public void setCampoContrasena(boolean campoContrasena) {
        this.campoContrasena = campoContrasena;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isCampoNombreUsuario() {
        return campoNombreUsuario;
    }

    public void setCampoNombreUsuario(boolean campoNombreUsuario) {
        this.campoNombreUsuario = campoNombreUsuario;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isCampoGenero() {
        return campoGenero;
    }

    public void setCampoGenero(boolean campoGenero) {
        this.campoGenero = campoGenero;
    }

    public boolean isCampoCorreo() {
        return campoCorreo;
    }

    public void setCampoCorreo(boolean campoCorreo) {
        this.campoCorreo = campoCorreo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isCampoFechaNacimiento() {
        return campoFechaNacimiento;
    }

    public void setCampoFechaNacimiento(boolean campoFechaNacimiento) {
        this.campoFechaNacimiento = campoFechaNacimiento;
    }

    

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public boolean isCampoIdentificacion() {
        return campoIdentificacion;
    }

    public void setCampoIdentificacion(boolean campoIdentificacion) {
        this.campoIdentificacion = campoIdentificacion;
    }

    public boolean isCampoNombre() {
        return campoNombre;
    }

    public void setCampoNombre(boolean campoNombre) {
        this.campoNombre = campoNombre;
    }

    public boolean isCampoFoto() {
        return campoFoto;
    }

    public void setCampoFoto(boolean campoFoto) {
        this.campoFoto = campoFoto;
    }

    public UploadedFile getUploadedFileFoto() {
        return uploadedFileFoto;
    }

    public void setUploadedFileFoto(UploadedFile uploadedFileFoto) {
        this.uploadedFileFoto = uploadedFileFoto;
    }
     
    public UploadedFile getFoto() {
        return uploadedFileFoto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setFoto(UploadedFile foto) {
        this.uploadedFileFoto = foto;
    }

    public boolean isCamposFuncionario() {
        return camposFuncionario;
    }

    public void setCamposFuncionario(boolean camposFuncionario) {
        this.camposFuncionario = camposFuncionario;
    }

    public boolean isCamposEstudiante() {
        return camposEstudiante;
    }

    public void setCamposEstudiante(boolean camposEstudiante) {
        this.camposEstudiante = camposEstudiante;
    }

    public boolean isCamposFamiliar() {
        return camposFamiliar;
    }

    public void setCamposFamiliar(boolean camposFamiliar) {
        this.camposFamiliar = camposFamiliar;
    }

    public MuUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(MuUsuario usuario) {
        this.usuario = usuario;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public boolean isCampoApellido1() {
        return campoApellido1;
    }

    public void setCampoApellido1(boolean campoApellido1) {
        this.campoApellido1 = campoApellido1;
    }

    public boolean isCampoApellido2() {
        return campoApellido2;
    }

    public void setCampoApellido2(boolean campoApellido2) {
        this.campoApellido2 = campoApellido2;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public boolean isCampoTelefono() {
        return campoTelefono;
    }

    public void setCampoTelefono(boolean campoTelefono) {
        this.campoTelefono = campoTelefono;
    }

    public boolean isCampoFacultadDependencia() {
        return campoFacultadDependencia;
    }

    public void setCampoFacultadDependencia(boolean campoFacultadDependencia) {
        this.campoFacultadDependencia = campoFacultadDependencia;
    }

    public String getFacultadDependenciaDes() {
        return facultadDependenciaDes;
    }

    public void setFacultadDependenciaDes(String facultadDependenciaDes) {
        this.facultadDependenciaDes = facultadDependenciaDes;
    }

    public MuFacultadDependencia getFacultadDependencia() {
        return facultadDependencia;
    }

    public void setFacultadDependencia(MuFacultadDependencia facultadDependencia) {
        this.facultadDependencia = facultadDependencia;
    }

    public boolean isCampoCelular() {
        return campoCelular;
    }

    public void setCampoCelular(boolean campoCelular) {
        this.campoCelular = campoCelular;
    }
    public MuOcupacion getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(MuOcupacion ocupacion) {
        this.ocupacion = ocupacion;
    }
    
    public String getOcupacionDes() {
        return ocupacionDes;
    }

    public void setOcupacionDes(String ocupacionDes) {
        this.ocupacionDes = ocupacionDes;
    }

    public boolean isCampoOcupacion() {
        return campoOcupacion;
    }

    public void setCampoOcupacion(boolean campoOcupacion) {
        this.campoOcupacion = campoOcupacion;
    }
 
    
    public void usuarioSeleccionado(MuUsuario usuario, MostrarUsuariosController mgb) {
        this.mostraUsuariosController = mgb;
        RequestContext requestContext = RequestContext.getCurrentInstance();

        this.camposEstudiante = this.mostraUsuariosController.isHabilitarEstudiantes();
        this.camposFamiliar = this.mostraUsuariosController.isHabilitarFamiliares();
        this.camposFuncionario = this.mostraUsuariosController.isHabilitarFuncionarios();

        this.usuario = usuario;
        this.campoFoto = true;
        this.campoNombre = true;
        this.campoIdentificacion = true;
        this.campoApellido1 = true;
        this.campoApellido2 = true;
        this.campoFechaNacimiento = true;
        this.campoCorreo = true;
        this.campoTelefono = true;
        this.campoGenero = true;
        this.campoNombreUsuario = true;
        this.campoContrasena = true;
        this.modificarDatosAcademicos = true;
        this.campoCelular=true;
        this.campoCodigo=true;
        this.campoOcupacion=true;
        this.campoFacultadDependencia=true;
        
        this.aceptarCancelarModificarDatosAcademicos = false;
        
        requestContext.update("editarInformacionUsuario");
        requestContext.execute("PF('editarUsuario').show()");
    }

    public void cargarFoto(FileUploadEvent event) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.uploadedFileFoto = event.getFile();
        requestContext.update("formularioFoto");

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
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread.sleep(2000);
            this.uploadedFileFoto = null;

        } 
        
        requestContext.update("editarFoto");
    }

    public void cancelarSubirFoto() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoFoto = true;
        this.uploadedFileFoto = null;

        requestContext.update("editarFoto");

    }

    public boolean validarEstado(){
        return usuario.getUsuEstado().equalsIgnoreCase("Activo");
    }
    public void mostraSubirFoto() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoFoto = false;
        requestContext.update("editarFoto");
        
    }

    public void mostrarModificarNombre() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoNombre = false;
        this.nombres = this.usuario.getUsuNombres();
        requestContext.update("editarInformacionUsuario");
    }

    public void cancelarActualizarNombre() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoNombre = true;
        this.nombres = "";
        requestContext.update("editarInformacionUsuario");
    }

    public void actualizarNombre() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarNombres(this.nombres)) {
            this.campoNombre = true;
            this.usuario.setUsuNombres(nombres);
            this.usuarioEJB.edit(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo Nombre Actualizado.", ""));
        }
        requestContext.update("editarInformacionUsuario");
    }

    public void mostrarModificarIdentificacion() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoIdentificacion = false;
        this.identificacion = this.usuario.getUsuIdentificacion() + "";
        requestContext.update("editarInformacionUsuario");
    }

    public void cancelarActualizarIdentificacion() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoIdentificacion = true;
        this.identificacion = "";
        requestContext.update("editarInformacionUsuario");
    }    
    public void actualizarIdentificacion() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarNumeroIdentificacion(this.identificacion, this.usuarioEJB)) {
            this.campoIdentificacion = true;
            this.usuario.setUsuIdentificacion(Long.parseLong(this.identificacion));
            this.usuarioEJB.edit(this.usuario);
            this.identificacion = "";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo número de identificación Actualizado.", ""));
        }
        requestContext.update("editarInformacionUsuario");
    }

    public void mostrarModificarCodigo() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoCodigo = false;
        this.codigo = this.usuario.getUsuCodigo()+ "";
        requestContext.update("editarInformacionUsuario");
    }

    public void cancelarActualizarCodigo() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoCodigo = true;
        this.codigo = "";
        requestContext.update("editarInformacionUsuario");
    }

    
    public void actualizarCodigo() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarCodigo(this.identificacion, this.usuarioEJB)) {
            this.campoCodigo = true;
            this.usuario.setUsuIdentificacion(Long.parseLong(this.codigo));
            this.usuarioEJB.edit(this.usuario);
            this.codigo = "";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo codigo Actualizado.", ""));
        }
        requestContext.update("formularioDatosPersonales");
    }
    public void mostrarModificarApellido1() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoApellido1 = false;
        this.apellido1 = this.usuario.getUsuApellido1();
        requestContext.update("editarInformacionUsuario");
    }

    public void cancelarActualizarApellido1() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoApellido1 = true;
        this.apellido1 = "";
        requestContext.update("editarInformacionUsuario");
    }

    public void actualizarApellido1() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarApellidos(this.apellido1)) {
            this.campoApellido1 = true;
            this.usuario.setUsuApellido1(this.apellido1);
            this.usuarioEJB.edit(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo apellidos actualizado.", ""));
        }
        requestContext.update("editarInformacionUsuario");
        
    }
public void mostrarModificarApellido2() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoApellido2 = false;
        this.apellido2 = this.usuario.getUsuApellido2();
        requestContext.update("editarInformacionUsuario");
    }

    public void cancelarActualizarApellido2() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoApellido2 = true;
        this.apellido2 = "";
        requestContext.update("editarInformacionUsuario");
    }

    public void actualizarApellido2() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarApellidos(this.apellido2)) {
            this.campoApellido2 = true;
            this.usuario.setUsuApellido2(this.apellido2);
            this.usuarioEJB.edit(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo apellidos actualizado.", ""));
        }
        requestContext.update("editarInformacionUsuario");
        
    }
    public void mostrarModificarFechaNacimiento() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoFechaNacimiento = false;
        this.fechaNacimiento = new Date(this.usuario.getUsuFechaNacimiento().getYear(), this.usuario.getUsuFechaNacimiento().getMonth(), this.usuario.getUsuFechaNacimiento().getDate());
        requestContext.update("editarInformacionUsuario");
    }

    public void cancelarActualizarFechaNacimiento() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoFechaNacimiento = true;
        this.fechaNacimiento = new Date();
        requestContext.update("editarInformacionUsuario");
    }

    public void actualizarFechaNacimiento() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarFechaNacimiento(this.fechaNacimiento)) {
            this.campoFechaNacimiento = true;
            this.usuario.setUsuFechaNacimiento(this.fechaNacimiento);
            this.usuarioEJB.edit(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo fecha nacimiento actualizado.", ""));
        }
        requestContext.update("editarInformacionUsuario");
        
    }

    public void mostrarModificarCorreo() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoCorreo = false;
        this.correo = this.usuario.getUsuEmail();
        requestContext.update("editarInformacionUsuario");
    }

    public void cancelarActualizarCorreo() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoCorreo = true;
        this.correo = "";
        requestContext.update("editarInformacionUsuario");
    }

    public void actualizarCorreo() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarCorreo(this.correo, this.usuarioEJB)) {
            this.campoCorreo = true;
            this.usuario.setUsuEmail(this.correo);
            this.usuarioEJB.edit(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo correo electrónico actualizado.", ""));
        }
        requestContext.update("editarInformacionUsuario");
    }

    public void mostrarModificarTelefono() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoTelefono = false;
        if (this.usuario.getUsuTelefono() != null) {
            this.telefono = this.usuario.getUsuTelefono() + "";
        }
        requestContext.update("editarInformacionUsuario");
    }
    public void cancelarActualizarTelefono() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoTelefono = true;
        this.telefono = "";
        requestContext.update("editarInformacionUsuario");
    }

    public void mostrarModificarGenero() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoGenero = false;
        if (this.usuario.getUsuGenero() != null) {
            this.genero = this.usuario.getUsuGenero();
        }
        requestContext.update("editarInformacionUsuario");
    }

    
    
    public void cancelarActualizarGenero() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoGenero = true;
        this.genero = "M";
        requestContext.update("editarInformacionUsuario");
    }

    public void actualizarTelefono() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarTelefono(this.telefono)) {
            this.campoTelefono = true;
            if (!this.telefono.isEmpty()) {
                
                this.usuario.setUsuTelefono(this.telefono);
            } else {
                this.usuario.setUsuTelefono(null);
            }
            this.usuarioEJB.edit(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo teléfono actualizado.", ""));
        }
        requestContext.update("editarInformacionUsuario");
    }
    
     
    public void actualizarCelular() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarTelefono(this.celular)) {
            this.campoCelular = true;
            if (!this.celular.isEmpty()) {
                
                this.usuario.setUsuCelular(this.celular);
            } else {
                this.usuario.setUsuCelular(null);
            }
            this.usuarioEJB.edit(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo teléfono actualizado.", ""));
        }
        requestContext.update("editarInformacionUsuario");
    }
    public void mostrarModificarCelular() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoCelular = false;
        if (this.usuario.getUsuCelular()!= null) {
            this.celular = this.usuario.getUsuCelular()+ "";
        }
        requestContext.update("editarInformacionUsuario");
    }
public void cancelarActualizarCelular() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoCelular = true;
        this.celular = "";
        requestContext.update("editarInformacionUsuario");
    }
    public void actualizarGenero() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarGenero(this.genero)) {
            this.campoGenero = true;
            this.usuario.setUsuGenero(this.genero);
            this.usuarioEJB.edit(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo genero actualizado.", ""));
        }
        requestContext.update("editarInformacionUsuario");
    }

    public void mostrarModificarNombreUsuario() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoNombreUsuario = false;
        this.nombreUsuario = this.usuario.getUsuNombreUsuario();
        requestContext.update("editarInformacionUsuario");
    }

    public void cancelarActualizarNombreUsuario() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoNombreUsuario = true;
        this.nombreUsuario = "";
        requestContext.update("editarInformacionUsuario");
    }
    public void mostrarModificarOcupacion() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoOcupacion = false;
        this.ocupacionDes = this.usuario.getOcuId().getOcuDescripcion();
        this.listaOcupaciones = ocupacionEJB.findAll();
        requestContext.update("editarInformacionUsuario");
    }
    
    public void cancelarActualizarOcupacion() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoOcupacion = true;
        this.ocupacionDes = "";
        requestContext.update("editarInformacionUsuario");
    }
    
    public void actualizarOcupacion() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoOcupacion = true;
        this.usuario.setOcuId(ocupacion);
        this.usuarioEJB.edit(this.usuario);
        requestContext.update("editarInformacionUsuario");
    }
    
    public void mostrarModificarFacultadDependencia() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoFacultadDependencia = false;
        this.facultadDependenciaDes = this.usuario.getFacDepId().getFacDepNombre();
        this.listaFacultadesYDependencias = facultadDependenciaEJB.findAll();
        requestContext.update("editarInformacionUsuario");
    }
    
    
    public void cancelarActualizarFacultadDependencia() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoFacultadDependencia = true;
        this.facultadDependenciaDes = "";
        requestContext.update("editarInformacionUsuario");
    }
    
    public void actualizarFacultadDependencia() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoFacultadDependencia= true;
        this.usuario.setFacDepId(this.facultadDependencia);
        this.usuarioEJB.edit(this.usuario);
        requestContext.update("editarInformacionUsuario");
    }
    
    public void actualizarNombreUsuario() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarNombreUsuario(this.nombreUsuario, this.usuarioEJB)) {
            this.campoNombreUsuario = true;
            this.usuario.setUsuNombreUsuario(this.nombreUsuario);
            this.usuarioEJB.edit(this.usuario);
            this.usuarioGrupoEJB.actualizarNombreUsuario("user", this.usuario.getUsuIdentificacion(), this.usuario.getUsuNombreUsuario());
        }
        requestContext.update("editarInformacionUsuario");
    }

    public void mostrarModificarContrasena() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoContrasena = false;
        requestContext.update("editarInformacionUsuario");
    }

    public void cancelarActualizarContrasena() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoContrasena = true;
        this.contrasena = "";
        requestContext.update("editarInformacionUsuario");
    }

    public void actualizarContrasena() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarContrasena(this.contrasena)) {
            this.campoContrasena = true;
            this.usuario.setUsuContrasena(Cifrar.sha256(this.contrasena));
            this.usuarioEJB.edit(this.usuario);
        }
        requestContext.update("editarInformacionUsuario");
    }

    public void buscarPorNombreFuncionario() {

        this.listaFuncionarios = usuarioEJB.busacarPorNombreFuncionario(this.nombreOApellidos.toLowerCase());

    }


    
}