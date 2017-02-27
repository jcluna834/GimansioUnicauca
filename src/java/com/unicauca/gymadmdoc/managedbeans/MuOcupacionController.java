package com.unicauca.gymadmdoc.managedbeans;

import com.unicauca.gymadmdoc.entities.MuOcupacion;
import com.unicauca.gymadmdoc.managedbeans.util.JsfUtil;
import com.unicauca.gymadmdoc.managedbeans.util.JsfUtil.PersistAction;
import com.unicauca.gymadmdoc.sessionbeans.MuOcupacionFacade;

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

@Named("muOcupacionController")
@SessionScoped
public class MuOcupacionController implements Serializable {

    @EJB
    private com.unicauca.gymadmdoc.sessionbeans.MuOcupacionFacade ejbFacade;
    private List<MuOcupacion> items = null;
    private MuOcupacion selected;

    public MuOcupacionController() {
    }

    public MuOcupacion getSelected() {
        return selected;
    }

    public void setSelected(MuOcupacion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MuOcupacionFacade getFacade() {
        return ejbFacade;
    }

    public MuOcupacion prepareCreate() {
        selected = new MuOcupacion();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MuOcupacionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MuOcupacionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MuOcupacionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MuOcupacion> getItems() {
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
                    getFacade().edit(selected);
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public MuOcupacion getMuOcupacion(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<MuOcupacion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MuOcupacion> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = MuOcupacion.class)
    public static class MuOcupacionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MuOcupacionController controller = (MuOcupacionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "muOcupacionController");
            return controller.getMuOcupacion(getKey(value));
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
            if (object instanceof MuOcupacion) {
                MuOcupacion o = (MuOcupacion) object;
                return getStringKey(o.getOcuId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MuOcupacion.class.getName()});
                return null;
            }
        }

    }

}
