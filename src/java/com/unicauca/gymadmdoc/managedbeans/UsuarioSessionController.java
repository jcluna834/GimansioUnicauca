package com.unicauca.gymadmdoc.managedbeans;

import com.unicauca.gymadmdoc.entities.MuUsuario;
import com.unicauca.gymadmdoc.entities.MuUsuariogrupo;
import com.unicauca.gymadmdoc.sessionbeans.MuUsuarioFacade;
import com.unicauca.gymadmdoc.sessionbeans.MuUsuariogrupoFacade;
import com.unicauca.gymadmdoc.utilidades.Utilidades;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author geovanny
 */
@ManagedBean
@SessionScoped
public class UsuarioSessionController implements Serializable {

    @EJB
    private MuUsuariogrupoFacade usuarioGrupoEJB;
    @EJB
    private MuUsuarioFacade usuarioEJB;
    String nombreDeUsuario;
    String contrasena;

    private Boolean haySesion;
    private boolean errorSesion;
    private Long identificacion;
    private MuUsuario usuario;

    public UsuarioSessionController() {
        haySesion = false;
    }

    public Boolean getHaySesion() {
        return haySesion;
    }

    public void setHaySesion(Boolean haySesion) {
        this.haySesion = haySesion;
    }

    public boolean isErrorSesion() {
        return errorSesion;
    }

    public void setErrorSesion(boolean errorSesion) {
        this.errorSesion = errorSesion;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public Long getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Long identificacion) {
        this.identificacion = identificacion;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public MuUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(MuUsuario usuario) {
        this.usuario = usuario;
    }

    public void login() throws IOException, ServletException {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        if (req.getUserPrincipal() == null) {
            try {
                req.login(this.nombreDeUsuario, this.contrasena);
                req.getServletContext().log("Autenticacion exitosa");
                haySesion = true;
                this.errorSesion=false;
                if (this.usuarioGrupoEJB.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getMuUsuariogrupoPK().getGruId().equals("user")) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/GymAdmDoc/faces/usuarioestandar/estandarMain.xhtml");
                    identificacion = this.usuarioGrupoEJB.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getMuUsuario().getUsuIdentificacion();
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/GymAdmDoc/faces/usuario/usuarioMain.xhtml");
                    identificacion = this.usuarioGrupoEJB.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getMuUsuario().getUsuIdentificacion();
                }
            } catch (ServletException e) {

                this.errorSesion=true;

            }
        } else if (this.usuarioGrupoEJB.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getMuUsuariogrupoPK().getGruId().equals("user")) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/GymAdmDoc/faces/usuarioestandar/estandarMain.xhtml");
            identificacion = this.usuarioGrupoEJB.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getMuUsuario().getUsuIdentificacion();
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/GymAdmDoc/faces/usuario/usuarioMain.xhtml");
            identificacion = this.usuarioGrupoEJB.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getMuUsuario().getUsuIdentificacion();
        }
    }

    public StreamedContent getImagenFlujo() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String id = context.getExternalContext().getRequestParameterMap().get("id");
            //System.out.println("ident: " + id);
            MuUsuario usu = usuarioEJB.buscarPorIdUsuario(Long.valueOf(id)).get(0);
            if (usu.getUsuFoto() == null) {
                return Utilidades.getImagenPorDefecto("foto");
            } else {
                return new DefaultStreamedContent(new ByteArrayInputStream(usu.getUsuFoto()));
            }
        }
    }

    public void logout() throws IOException, ServletException {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        try {
            req.logout();
            req.getSession().invalidate();
            fc.getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/GymAdmDoc/");

        } catch (ServletException e) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FAILED", "Logout failed on backend"));
        }

    }

    public void ventanaInicioSession() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
        this.contrasena = null;
        this.nombreDeUsuario = null;
        requestContext.update("formularioInicioSession");
        requestContext.execute("PF('IniciarSesion').show()");
    }

    public boolean esusuarioSinSession() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        if (req.getUserPrincipal() == null) {
            return true;
        }
        return false;
    }

    public boolean esusuarioConSession() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        if (req.getUserPrincipal() == null) {
            return false;

        } else {
            /*if(this.usuarioGrupoEJB.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getMuUsuariogrupoPK().getGruId().equals(2))
            {
                return true;
            }*/
            return false;
        }

    }

    public boolean esAdministrador() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        if (req.getUserPrincipal() == null) {
            return false;

        } else {
            /* if(this.usuarioGrupoEJB.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getUsuariogrupoPK().getGruid().equals("admin"))
            {
                return true;
            }*/
            return false;
        }

    }

    public String nombreUsuario() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        if (req.getUserPrincipal() == null) {
            return "";
        } else {
            return req.getUserPrincipal().getName();
        }
    }
}
