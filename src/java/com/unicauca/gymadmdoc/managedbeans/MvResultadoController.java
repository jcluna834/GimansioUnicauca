package com.unicauca.gymadmdoc.managedbeans;

import com.unicauca.gymadmdoc.entities.MvResultado;
import com.unicauca.gymadmdoc.managedbeans.util.JsfUtil;
import com.unicauca.gymadmdoc.managedbeans.util.JsfUtil.PersistAction;
import com.unicauca.gymadmdoc.sessionbeans.MvResultadoFacade;

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


@Named("mvResultadoController")
@SessionScoped
public class MvResultadoController implements Serializable {
    @EJB private com.unicauca.gymadmdoc.sessionbeans.MvResultadoFacade ejbFacade;
    private List<MvResultado> items = null;
    private MvResultado selected;

    public MvResultadoController() { }

    public MvResultado getSelected() {
        return selected;
    }

    public void setSelected(MvResultado selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() { }

    protected void initializeEmbeddableKey() { }

    private MvResultadoFacade getFacade() {
        return ejbFacade;
    }

    public MvResultado prepareCreate() {
        selected = new MvResultado();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/mvMensajes").getString("MvResultadoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/mvMensajes").getString("MvResultadoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/mvMensajes").getString("MvResultadoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MvResultado> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    if (persistAction != PersistAction.CREATE) {
                        getFacade().edit(selected);
                    } else {
                        getFacade().create(selected);
                        actualizarSelected();
                    }
                } else {
                    getFacade().remove(selected);
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/mvMensajes").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/mvMensajes").getString("PersistenceErrorOccured"));
            }
        }
    }
    
    void actualizarSelected() {
        getFacade().flush();
    }

    public MvResultado getMvResultado(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<MvResultado> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MvResultado> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass=MvResultado.class)
    public static class MvResultadoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MvResultadoController controller = (MvResultadoController)facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mvResultadoController");
            return controller.getMvResultado(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MvResultado) {
                MvResultado o = (MvResultado) object;
                return getStringKey(o.getResId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MvResultado.class.getName()});
                return null;
            }
        }
    }
}