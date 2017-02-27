package com.unicauca.gymadmdoc.managedbeans;


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
public class VerUsuarioController implements Serializable {

    @EJB
    private MuUsuarioFacade usuarioEJB;
    
    private MuUsuario usuario;
    private boolean campoFoto;
    
    private boolean estado;
    private UploadedFile uploadedFileFoto;
    private Date fechaNacimiento;
    private SimpleDateFormat sdf;
    
    private List<MuOcupacion> listaCargo;
    private List<MuFacultadDependencia> listaUnidadAcademica;
    private Long idCargo;
    private Long idUnidadAcademica;
    private MuUsuario funcionarioFamiliar;
    private List<MuUsuario> listaFuncionarios;
    private String fechaDeNacimiento;

    public VerUsuarioController() {
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

    public Long getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Long idCargo) {
        this.idCargo = idCargo;
    }

    public Long getIdUnidadAcademica() {
        return idUnidadAcademica;
    }

    public void setIdUnidadAcademica(Long idUnidadAcademica) {
        this.idUnidadAcademica = idUnidadAcademica;
    }

    public List<MuOcupacion> getListaCargo() {
        return listaCargo;
    }

    public void setListaCargo(List<MuOcupacion> listaCargo) {
        this.listaCargo = listaCargo;
    }

    public List<MuFacultadDependencia> getListaUnidadAcademica() {
        return listaUnidadAcademica;
    }

    public void setUnidadAcademica(List<MuFacultadDependencia> listaUnidadAcademica) {
        this.listaUnidadAcademica = listaUnidadAcademica;
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

    public boolean isCampoFoto() {
        return campoFoto;
    }

    public void setCampoFoto(boolean campoFoto) {
        this.campoFoto = campoFoto;
    }

    public UploadedFile getFoto() {
        return uploadedFileFoto;
    }

    public void setFoto(UploadedFile foto) {
        this.uploadedFileFoto = foto;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public MuUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(MuUsuario usuario) {
        this.usuario = usuario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void usuarioSeleccionado(MuUsuario usuario, MostrarUsuariosController mgb) {
        
        RequestContext requestContext = RequestContext.getCurrentInstance();

        this.usuario = usuario;
        if(usuario.getUsuEstado().equalsIgnoreCase("Activo")){
            estado=true;
        }else{
            estado=false;
        }
        this.fechaDeNacimiento=sdf.format(usuario.getUsuFechaNacimiento());
        this.campoFoto = true;
        
        requestContext.update("informacionUsuario");
        requestContext.execute("PF('verUsuario').show()");

    }

    public void cargarFoto(FileUploadEvent event) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.uploadedFileFoto = event.getFile();
        requestContext.update("formularioFoto");
        requestContext.update("formularioEditarFoto");

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
        requestContext.update("formularioEditarFoto");
    }

    public void cancelarSubirFoto() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoFoto = true;
        this.uploadedFileFoto = null;
        requestContext.update("formularioFoto");
        requestContext.update("formularioEditarFoto");

    }
 
    public void mostraSubirFoto() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campoFoto = false;
        requestContext.update("formularioFoto");
        requestContext.update("formularioEditarFoto");
    }



}
