package com.unicauca.gymadmdoc.managedbeans;

import com.unicauca.gymadmdoc.cifrado.Cifrar;
import com.unicauca.gymadmdoc.entities.MuUsuario;
import com.unicauca.gymadmdoc.managedbeans.util.JsfUtil;
import com.unicauca.gymadmdoc.managedbeans.util.JsfUtil.PersistAction;
import com.unicauca.gymadmdoc.sessionbeans.MuUsuarioFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("muUsuarioController")
@SessionScoped
public class MuUsuarioController implements Serializable {

    @EJB
    private com.unicauca.gymadmdoc.sessionbeans.MuUsuarioFacade ejbFacade;
    private List<MuUsuario> items = null;
    private MuUsuario usuario;

    public MuUsuarioController() {
     usuario= new MuUsuario();
    }

    public MuUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(MuUsuario usuario) {
        this.usuario = usuario;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MuUsuarioFacade getFacade() {
        return ejbFacade;
    }

    public MuUsuario prepareCreate() {
        usuario = new MuUsuario();
        initializeEmbeddableKey();
        return usuario;
    }

    public void create() {
        
        this.usuario.setUsuContrasena(Cifrar.sha256(usuario.getUsuContrasena()));
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MuUsuarioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MuUsuarioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MuUsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            usuario = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MuUsuario> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (usuario != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(usuario);
                } else {
                    getFacade().remove(usuario);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public MuUsuario getMuUsuario(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<MuUsuario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MuUsuario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = MuUsuario.class)
    public static class MuUsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MuUsuarioController controller = (MuUsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "muUsuarioController");
            return controller.getMuUsuario(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MuUsuario) {
                MuUsuario o = (MuUsuario) object;
                return getStringKey(o.getUsuIdentificacion());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MuUsuario.class.getName()});
                return null;
            }
        }

    }

}
