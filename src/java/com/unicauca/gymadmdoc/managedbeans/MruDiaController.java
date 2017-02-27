package com.unicauca.gymadmdoc.managedbeans;

import com.unicauca.gymadmdoc.entities.MruDia;
import com.unicauca.gymadmdoc.entities.MruRutina;
import com.unicauca.gymadmdoc.entities.MuUsuario;
import com.unicauca.gymadmdoc.sessionbeans.DiasDeRutinaBean;
import com.unicauca.gymadmdoc.managedbeans.util.JsfUtil;
import com.unicauca.gymadmdoc.managedbeans.util.JsfUtil.PersistAction;
import com.unicauca.gymadmdoc.sessionbeans.MruDiaFacade;
import com.unicauca.gymadmdoc.sessionbeans.RutinasDeUsuarioBean;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("mruDiaController")
@SessionScoped
public class MruDiaController implements Serializable {

    @EJB
    private MruDiaFacade ejbFacade;
    private List<MruDia> items = null;
    private List<MruDia> dias = null;
    private MruDia selected;
    
    
    @EJB
    DiasDeRutinaBean miSesionBean;

    private MruRutina selectedRutina;
    
    public MruDiaController() {
        dias = null;
        this.prepareCreate();    
    }
    
    public List<MruDia> getDias() {
        return dias;
    }

    public void setDias(List<MruDia> dias) {
        this.dias = dias;
    }

    public MruDia getSelected() {
        return selected;
    }

    public void setSelected(MruDia selected) {
        this.selected = selected;
    }
    
    

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MruDiaFacade getFacade() {
        return ejbFacade;
    }

    public MruDia prepareCreate() {
        selected = new MruDia();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MruDiaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        clearFields();
    }
    
    public void consultarDiaPorRutina(){
        dias = miSesionBean.consultarDiaPorRutina(selectedRutina.getRuId());
    }
    
    public void clearFields(){
        selected = new MruDia();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful",  "Los campos han sido limpiados") );
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MruDiaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MruDiaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MruDia> getItems() {
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

    public MruDia getMruDia(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<MruDia> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MruDia> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public MruRutina getSelectedRutina() {
        return selectedRutina;
    }

    public void setSelectedRutina(MruRutina selectedRutina) {
        this.selectedRutina = selectedRutina;
    }

    @FacesConverter(forClass = MruDia.class)
    public static class MruDiaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MruDiaController controller = (MruDiaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mruDiaController");
            return controller.getMruDia(getKey(value));
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
            if (object instanceof MruDia) {
                MruDia o = (MruDia) object;
                return getStringKey(o.getDiaId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MruDia.class.getName()});
                return null;
            }
        }

    }

}
