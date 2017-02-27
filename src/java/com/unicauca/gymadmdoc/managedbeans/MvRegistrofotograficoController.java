package com.unicauca.gymadmdoc.managedbeans;

import com.unicauca.gymadmdoc.entities.MvRegistrofotografico;
import com.unicauca.gymadmdoc.entities.MvValoracion;
import com.unicauca.gymadmdoc.managedbeans.util.JsfUtil;
import com.unicauca.gymadmdoc.managedbeans.util.JsfUtil.PersistAction;
import com.unicauca.gymadmdoc.sessionbeans.MvRegistrofotograficoFacade;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

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
import javax.faces.event.PhaseId;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@Named("mvRegistrofotograficoController")
@SessionScoped
public class MvRegistrofotograficoController implements Serializable {
    @EJB
    private com.unicauca.gymadmdoc.sessionbeans.MvRegistrofotograficoFacade registroFacade;
    private List<MvRegistrofotografico> items = null;
    private MvRegistrofotografico selected;
    private MvValoracion valoracion;

    public MvRegistrofotograficoController() {}
    
    private byte[] inputStreamToByteArray(UploadedFile file) {
        byte[] imagen = null;
        if(file != null) {
            try {
                ByteArrayOutputStream output;
                try (InputStream input = file.getInputstream()) {
                    byte[] buffer = new byte[1024];
                    int length;
                    output = new ByteArrayOutputStream();
                    while ((length = input.read(buffer)) != -1) output.write(buffer, 0, length);
                }
                imagen = output.toByteArray();
            } catch (Exception ex) {}
        }
        return imagen;
    }
    
    public StreamedContent getImageFront() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() != PhaseId.RENDER_RESPONSE) {
            String ide = context.getExternalContext().getRequestParameterMap().get("image");
            if(ide != null && !ide.isEmpty()) {
                MvRegistrofotografico reg = registroFacade.find(Integer.parseInt(ide));
                if(reg.getImgFront() != null)
                    return new DefaultStreamedContent(new ByteArrayInputStream(reg.getImgFront()));
            }
        }
        return new DefaultStreamedContent();
    }
    
    public StreamedContent getImagePost() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() != PhaseId.RENDER_RESPONSE) {
            String ide = context.getExternalContext().getRequestParameterMap().get("image");
            if(ide != null && !ide.isEmpty()) {
                MvRegistrofotografico reg = registroFacade.find(Integer.parseInt(ide));
                if(reg.getImgPost() != null)
                    return new DefaultStreamedContent(new ByteArrayInputStream(reg.getImgPost()));
            }
        }
        return new DefaultStreamedContent();
    }
    
    public StreamedContent getImageLatDer() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() != PhaseId.RENDER_RESPONSE) {
            String ide = context.getExternalContext().getRequestParameterMap().get("image");
            if(ide != null && !ide.isEmpty()) {
                MvRegistrofotografico reg = registroFacade.find(Integer.parseInt(ide));
                if(reg.getImgLatDer() != null)
                    return new DefaultStreamedContent(new ByteArrayInputStream(reg.getImgLatDer()));
            }
        }
        return new DefaultStreamedContent();
    }
    
    public StreamedContent getImageLatIzq() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() != PhaseId.RENDER_RESPONSE) {
            String ide = context.getExternalContext().getRequestParameterMap().get("image");
            if(ide != null && !ide.isEmpty()) {
                MvRegistrofotografico reg = registroFacade.find(Integer.parseInt(ide));
                if(reg.getImgLatIzq() != null)
                    return new DefaultStreamedContent(new ByteArrayInputStream(reg.getImgLatIzq()));
            }
        }
        return new DefaultStreamedContent();
    }
    
    public void uploadLatIzq(FileUploadEvent event) {
        if(selected == null)
            selected = new MvRegistrofotografico();
        UploadedFile file  = event.getFile();
        selected.setImgLatIzq(inputStreamToByteArray(file));
        if(selected.getImgLatIzq() != null)
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Succesful", "La imagen lateral izquierda ha sido subida correctamente"));
        else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Error al subir la imagen lateral izquierda"));
    }
    
    public void uploadLatDer(FileUploadEvent event) {
        if(selected == null)
            selected = new MvRegistrofotografico();
        UploadedFile file  = event.getFile();
        selected.setImgLatDer(inputStreamToByteArray(file));
        if(selected.getImgLatDer() != null)
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Succesful", "La imagen lateral derecha ha sido subida correctamente"));
        else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Error al subir la imagen lateral derecha"));
    }
    
    public void uploadFront(FileUploadEvent event) {
        if(selected == null)
            selected = new MvRegistrofotografico();
        UploadedFile file  = event.getFile();
        selected.setImgFront(inputStreamToByteArray(file));
        if(selected.getImgFront() != null)
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Succesful", "La imagen frontal ha sido subida correctamente"));
        else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Error al subir la imagen frontal"));
    }

    public void uploadPost(FileUploadEvent event) {
        if(selected == null)
            selected = new MvRegistrofotografico();
        UploadedFile file  = event.getFile();
        selected.setImgPost(inputStreamToByteArray(file));
        if(selected.getImgPost() != null)
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Succesful", "La imagen posterior ha sido subida correctamente"));
        else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Error al subir la imagen posterior"));
    }
    
    public MvRegistrofotografico getSelected() {
        return selected;
    }

    public void setSelected(MvRegistrofotografico selected) {
        this.selected = selected;
    }
    
    public MvValoracion getValoracion() {
        return valoracion;
    }

    public void setValoracion(MvValoracion valoracion) {
        this.valoracion = valoracion;
        if(existeRegistro(this.valoracion))
            selected = getFacade().getRegistro(valoracion);
    }

    protected void setEmbeddableKeys() {}

    protected void initializeEmbeddableKey() { }

    private MvRegistrofotograficoFacade getFacade() {
        return registroFacade;
    }

    public MvRegistrofotografico prepareCreate() {
        selected = new MvRegistrofotografico();
        initializeEmbeddableKey();
        return selected;
    }
    
    public boolean existeRegistro(MvValoracion valoracion) {
        if(valoracion != null) {
            MvRegistrofotografico last = registroFacade.getRegistro(valoracion);
            return (last != null && last.getImgId() != null && last.getImgId() != 0);
        }
        return false;
    }
    
    public void agregarRegistro(MvValoracion valoracion) {
        this.valoracion = valoracion;
        MvRegistrofotografico last = registroFacade.getRegistro(this.valoracion);
        if(last != null && last.getImgId() != null && last.getImgId() != 0)
            selected = last;
        else {
            selected = new MvRegistrofotografico();
            initializeEmbeddableKey();
            selected.setValId(valoracion);
        }
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/mvMensajes").getString("MvRegistrofotograficoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/mvMensajes").getString("MvRegistrofotograficoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            selected = null;
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/mvMensajes").getString("MvRegistrofotograficoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/mvMensajes").getString("MvRegistrofotograficoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MvRegistrofotografico> getItems() {
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
                    if(persistAction != PersistAction.UPDATE) {
                        if(valoracion == null)
                            throw new EJBException("La valoracion es nula!");
                        selected.setValId(valoracion);
                    }
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/mvMensajes").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/mvMensajes").getString("PersistenceErrorOccured"));
            }
        }
    }

    public MvRegistrofotografico getMvRegistrofotografico(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<MvRegistrofotografico> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MvRegistrofotografico> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = MvRegistrofotografico.class)
    public static class MvRegistrofotograficoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MvRegistrofotograficoController controller = (MvRegistrofotograficoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mvRegistrofotograficoController");
            return controller.getMvRegistrofotografico(getKey(value));
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
            if (object instanceof MvRegistrofotografico) {
                MvRegistrofotografico o = (MvRegistrofotografico) object;
                return getStringKey(o.getImgId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MvRegistrofotografico.class.getName()});
                return null;
            }
        }

    }

}
