
package com.unicauca.gymadmdoc.managedbeans;

import com.unicauca.gymadmdoc.cifrado.Cifrar;
import com.unicauca.gymadmdoc.entities.MuUsuario;
import com.unicauca.gymadmdoc.sessionbeans.MuUsuarioFacade;
import com.unicauca.gymadmdoc.validadores.ValidarEdicionUsuarios;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import com.unicauca.gymadmdoc.utilidades.RedimensionadorImagenes;
import com.unicauca.gymadmdoc.utilidades.Utilidades;

/**
 *
 * @author Geovanny
 * Maneja los eventos de la vista de administrador/usuarios/perfilAdministrador.xhtml y de /usuario/usuarios/perfilUsuario.xhtml
 */
@ManagedBean
@SessionScoped
public class perfilUsuarioController implements Serializable {

    @EJB
    private MuUsuarioFacade usuarioEJB;
    //private String rutaFoto;
    private MuUsuario usuario;
    private String tipo;
    private UploadedFile uploadedFileFoto;
    private boolean estudiante;
    private boolean funcionario;
    private boolean familiar;
    private boolean mostrarFoto;
    private boolean mostrarFecNac;
    private boolean mostrarTelefono;
    private boolean mostrarCelular;    
    private boolean mostrarNombres;
    private boolean mostrarApellidos;
    private boolean mostrarIdentificacion;
    private boolean mostrarEmail;
    private boolean mostrarSexo;
    private boolean mostrarContrasena;
    
    private String nombres;
    private String apellidos;
    private String sexo;
    private String email;
    private Long identificacion;
    
    private String telefono;
    private Date fechaNacimiento;
    private SimpleDateFormat sdf;
    private String contrasena;
    private String confirmarContrasena;
    

    private ValidarEdicionUsuarios validarEdicionUsuario;
    private String celular;

    public perfilUsuarioController() {
        this.sdf = new SimpleDateFormat("yyyy-MM-dd");
    }

    @PostConstruct
    private void init() {
        this.buscarUsuario();
        this.inicializarCampos();

    }

    /**
     * Recupera de la bd la imagen
     *
     * @return el flujo de bytes de la imagen
     */
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    private void buscarUsuario() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        if (req.getUserPrincipal() != null) {
            System.out.println("Usuario: "+req.getUserPrincipal().getName());
            this.usuario = this.usuarioEJB.buscarUsuarioPorNombreUsuario(req.getUserPrincipal().getName()).get(0);
        }
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getConfirmarContrasena() {
        return confirmarContrasena;
    }

    public void setConfirmarContrasena(String confirmarContrasena) {
        this.confirmarContrasena = confirmarContrasena;
    }

    public boolean isMostrarContrasena() {
        return mostrarContrasena;
    }

    public void setMostrarContrasena(boolean mostrarContrasena) {
        this.mostrarContrasena = mostrarContrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isMostrarCelular() {
        return mostrarCelular;
    }

    public void setMostrarCelular(boolean mostrarCelular) {
        this.mostrarCelular = mostrarCelular;
    }

    public boolean isMostrarFecNac() {
        return mostrarFecNac;
    }

    public void setMostrarFecNac(boolean mostrarFecNac) {
        this.mostrarFecNac = mostrarFecNac;
    }

    public boolean isMostrarTelefono() {
        return mostrarTelefono;
    }

    public void setMostrarTelefono(boolean mostrarTelefono) {
        this.mostrarTelefono = mostrarTelefono;
    }

    public UploadedFile getFoto() {
        return uploadedFileFoto;
    }

    public void setFoto(UploadedFile foto) {
        this.uploadedFileFoto = foto;
    }

    public boolean isMostrarFoto() {
        return mostrarFoto;
    }

    public void setMostrarFoto(boolean mostrarFoto) {
        this.mostrarFoto = mostrarFoto;
    }

    public boolean isEstudiante() {
        return estudiante;
    }

    public void setEstudiante(boolean estudiante) {
        this.estudiante = estudiante;
    }

    public boolean isFuncionario() {
        return funcionario;
    }

    public void setFuncionario(boolean funcionario) {
        this.funcionario = funcionario;
    }

    public boolean isFamiliar() {
        return familiar;
    }

    public void setFamiliar(boolean familiar) {
        this.familiar = familiar;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public MuUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(MuUsuario usuario) {
        this.usuario = usuario;
    }



    private void definirSexo() {
        if (this.usuario.getUsuGenero().equals("M")) {
            this.sexo = "M";
        } else {
            this.sexo = "F";
        }
    }

    public boolean isMostrarNombres() {
        return mostrarNombres;
    }

    public void setMostrarNombres(boolean mostrarNombres) {
        this.mostrarNombres = mostrarNombres;
    }

    public boolean isMostrarApellidos() {
        return mostrarApellidos;
    }

    public void setMostrarApellidos(boolean mostrarApellidos) {
        this.mostrarApellidos = mostrarApellidos;
    }

    public boolean isMostrarIdentificacion() {
        return mostrarIdentificacion;
    }

    public void setMostrarIdentificacion(boolean mostrarIdentificacion) {
        this.mostrarIdentificacion = mostrarIdentificacion;
    }

    public boolean isMostrarEmail() {
        return mostrarEmail;
    }

    public void setMostrarEmail(boolean mostrarEmail) {
        this.mostrarEmail = mostrarEmail;
    }

    public boolean isMostrarSexo() {
        return mostrarSexo;
    }

    public void setMostrarSexo(boolean mostrarSexo) {
        this.mostrarSexo = mostrarSexo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Long identificacion) {
        this.identificacion = identificacion;
    }

   
    private void inicializarCampos() {
        this.mostrarFoto = true;
        this.mostrarNombres = true; 
        this.mostrarApellidos = true; 
        this.mostrarEmail = true; 
        this.mostrarSexo = true; 
        this.mostrarIdentificacion = true;
        this.mostrarTelefono = true;
        this.mostrarCelular = true;
        this.mostrarContrasena = true;
        this.mostrarFecNac = true;
    }

    public void cargarFoto(FileUploadEvent event) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.uploadedFileFoto = event.getFile();
        requestContext.update("formularioPerfilFotoUsuario");

    }

    public void mostraSubirFoto() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarFoto = false;
        requestContext.update("formularioPerfilFotoUsuario");
        requestContext.update("formularioEditarFoto");
    }

    public void actualizarFoto() throws InterruptedException {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.uploadedFileFoto != null) {
            this.mostrarFoto = true;

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
        requestContext.update("formularioPerfilFotoUsuario");
        requestContext.update("formularioEditarFoto");
    }

    public void cancelarSubirFoto() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarFoto = true;
        this.uploadedFileFoto = null;
        requestContext.update("formularioPerfilFotoUsuario");
        requestContext.update("formularioEditarFoto");

    }
    public void mostrarModificarIdentificacion() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarIdentificacion = false;
        this.identificacion = this.usuario.getUsuIdentificacion();
        requestContext.update("formularioPerfilDatosPersonales");
    }

    public void mostrarModificarNombres() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarNombres = false;
        if (this.usuario.getUsuNombres()!= null) {
            this.nombres = this.usuario.getUsuNombres()+ "";
        }
        requestContext.update("formularioPerfilDatosPersonales");
    }
    public void mostrarModificarApellidos() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarApellidos = false;
        if (this.usuario.getUsuApellido1()!= null) {
            this.apellidos = this.usuario.getUsuApellido1()+ "";
        }
        requestContext.update("formularioPerfilDatosPersonales");
    }
    public void mostrarModificarEmail() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarEmail = false;
        if (this.usuario.getUsuEmail()!= null) {
            this.email = this.usuario.getUsuEmail()+ "";
        }
        requestContext.update("formularioPerfilDatosPersonales");
    }

    public void mostrarModificarTelefono() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarTelefono = false;
        if (this.usuario.getUsuTelefono() != null) {
            this.telefono = this.usuario.getUsuTelefono() + "";
        }
        requestContext.update("formularioPerfilDatosPersonales");
    }
public void mostrarModificarCelular() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarCelular = false;
        if (this.usuario.getUsuCelular() != null) {
            this.celular = this.usuario.getUsuCelular() + "";
        }
        requestContext.update("formularioPerfilDatosPersonales");
    }
    public void mostrarModificarFecNac() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarFecNac = false;
        this.fechaNacimiento = new Date(this.usuario.getUsuFechaNacimiento().getYear(), this.usuario.getUsuFechaNacimiento().getMonth(), this.usuario.getUsuFechaNacimiento().getDate());
        requestContext.update("formularioPerfilDatosPersonales");
    }
    public void mostrarModificarSexo() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarSexo = false;
        if (this.usuario.getUsuGenero()!= null) {
            this.sexo = this.usuario.getUsuGenero();
        }
        requestContext.update("formularioPerfilDatosPersonales");
    }
    
    public void cancelarActualizarIdentificacion() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarIdentificacion = true;
        this.identificacion = 0L;
        requestContext.update("formularioPerfilDatosPersonales");
    }
    public void cancelarActualizarNombres() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarNombres = true;
        this.nombres = "";
        requestContext.update("formularioPerfilDatosPersonales");
    }
    public void cancelarActualizarApellidos() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarApellidos = true;
        this.apellidos = "";
        requestContext.update("formularioPerfilDatosPersonales");
    }
    public void cancelarActualizarEmail() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarEmail = true;
        this.email = "";
        requestContext.update("formularioPerfilDatosPersonales");
    }

    public void cancelarActualizarTelefono() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarTelefono = true;
        this.telefono = "";
        requestContext.update("formularioPerfilDatosPersonales");
    }
    
    public void cancelarActualizarCelular() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarCelular = true;
        this.celular = "";
        requestContext.update("formularioPerfilDatosPersonales");
    }

    public void cancelarActualizarFecNac() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarFecNac = true;
        this.fechaNacimiento = new Date();
        requestContext.update("formularioPerfilDatosPersonales");
    }
    public void actualizarIdentificacion() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarNumeroIdentificacion(this.identificacion+"", this.usuarioEJB)) {
            this.mostrarIdentificacion = true;
            this.usuario.setUsuIdentificacion(this.identificacion);
            this.usuarioEJB.edit(this.usuario);
            this.identificacion = 0L;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Número de identificación actualizado con éxito.", ""));
        }
        requestContext.update("formularioPerfilDatosPersonales");
    }
    public void cancelarActualizarSexo() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarSexo = true;
        this.sexo = "M";
        requestContext.update("formularioPerfilDatosPersonales");
    }
    
    public void actualizarFecNac() {
        
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarFechaNacimiento(this.fechaNacimiento)) {
            this.mostrarFecNac = true;
            this.usuario.setUsuFechaNacimiento(this.fechaNacimiento);
            this.usuarioEJB.edit(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fecha nacimiento actualizada con éxito", ""));
        }
        requestContext.update("formularioPerfilDatosPersonales");
      
        
    }
    public void actualizarNombres() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarNombres(this.nombres)) {
            this.mostrarNombres = true;
            this.usuario.setUsuNombres(this.nombres);
            this.usuarioEJB.edit(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nombres actualizados con éxito.", ""));
        }
        requestContext.update("formularioPerfilDatosPersonales");
    }
    public void actualizarApellidos() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarApellidos(this.apellidos)) {
            this.mostrarApellidos = true;
            this.usuario.setUsuApellido1(this.apellidos);
            this.usuarioEJB.edit(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Apellidos actualizados con éxito.", ""));
        }
        requestContext.update("formularioPerfilDatosPersonales");
    }
    public void actualizarEmail() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarCorreo(this.email, this.usuarioEJB)) {
            this.mostrarEmail = true;
            this.usuario.setUsuEmail(this.email);
            this.usuarioEJB.edit(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correo electrónico actualizado con éxito.", ""));
        }
        requestContext.update("formularioPerfilDatosPersonales");
    }
    
    public void actualizarTelefono() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarTelefono(this.telefono)) {
            this.mostrarTelefono = true;
            if (!this.telefono.isEmpty()) {
                
                this.usuario.setUsuTelefono(this.telefono);
            } else {
                this.usuario.setUsuTelefono(null);
            }
            this.usuarioEJB.edit(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Campo teléfono actualizado.", ""));
        }
        requestContext.update("formularioPerfilDatosPersonales");
    }
    public void actualizarCelular() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarTelefono(this.celular)) {
            this.mostrarCelular = true;
            if (!this.celular.isEmpty()) {
                
                this.usuario.setUsuCelular(this.celular);
            } else {
                this.usuario.setUsuCelular(null);
            }
            this.usuarioEJB.edit(this.usuario);
            
        }
        requestContext.update("formularioPerfilDatosPersonales");
    }
    public void actualizarSexo() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarGenero(this.sexo)) {
            this.mostrarSexo = true;
            this.usuario.setUsuGenero(this.sexo);
            this.usuarioEJB.edit(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sexo actualizado con éxito.", ""));
        }
        requestContext.update("formularioPerfilDatosPersonales");
    }

    public void mostrarModificarContrasena() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarContrasena = false;
        requestContext.update("formularioPerfilDatosPersonales");
    }

    public void cancelarActualizarContrasena() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarContrasena = true;
        this.contrasena = "";
        this.confirmarContrasena = "";
        requestContext.update("formularioPerfilDatosPersonales");
    }

    public void cambiarContrasena() {
        this.validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.validarEdicionUsuario.validarContrasenaConConfirmacion(this.contrasena, this.confirmarContrasena)) {
            this.usuario.setUsuContrasena(Cifrar.sha256(this.contrasena));
            this.usuarioEJB.edit(this.usuario);
            this.mostrarContrasena = true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Se cambio la contraseña correctamente.", ""));

        }
        requestContext.update("formularioPerfilDatosPersonales");

    }
}
