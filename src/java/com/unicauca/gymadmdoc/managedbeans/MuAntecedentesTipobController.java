package com.unicauca.gymadmdoc.managedbeans;
/*

import com.unicauca.gymadmdoc.entities.MuAntecedentesTipoa;
import com.unicauca.gymadmdoc.managedbeans.util.JsfUtil;
import com.unicauca.gymadmdoc.managedbeans.util.PaginationHelper;
import com.unicauca.gymadmdoc.sessionbeans.MuAntecedentesTipoaFacade;
import com.unicauca.gymadmdoc.entities.MuAntecedenteSalud;
import com.unicauca.gymadmdoc.entities.MuUsuario;

*/
import com.unicauca.gymadmdoc.entities.MuAntecedenteSalud;
import com.unicauca.gymadmdoc.jpacontrollers.*;
import com.unicauca.gymadmdoc.entities.MuAntecedentesTipob;
import com.unicauca.gymadmdoc.entities.MuUsuario;
import com.unicauca.gymadmdoc.managedbeans.util.JsfUtil;
import com.unicauca.gymadmdoc.managedbeans.util.PaginationHelper;
import com.unicauca.gymadmdoc.sessionbeans.MuAntecedentesTipobFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@Named("muAntecedentesTipobController")
@SessionScoped
public class MuAntecedentesTipobController implements Serializable {

    private MuAntecedentesTipob current;
    private DataModel items = null;
    @EJB
    private com.unicauca.gymadmdoc.sessionbeans.MuAntecedentesTipobFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    private long idsalud;

    public long getIdsalud() {
        return idsalud;
    }
    
        private String[] retbRespuesta1;
        private String[] retbRespuesta2;
        private String[] retbRespuesta3;
        private String[] retbRespuesta4;
    
     MuAntecedenteSalud u = new MuAntecedenteSalud();
     
       public void setIdsalud(long idsalud, int n) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Gym_Adm_DocPU");
        EntityManager em = emf.createEntityManager();

        Query query = em.createNamedQuery("MuAntecedenteSalud.findByusuIdentificacion");
        System.out.println("-*--*-->" + idsalud);
        u.setUsuIdentificacion(new MuUsuario(idsalud));
        query.setParameter("id", u.getUsuIdentificacion());

        List<MuAntecedenteSalud> resultados = query.getResultList();

       // System.out.println(p.getAnsaId() + "--+--->" + resultados.get(0).getAnsaId());

        if (n == 1) {
            getSelected().setAntbPregunta("4. ¿Tiene dolores frecuentes y no ha consultado al medico?.");
            getSelected().setResTipob(pegar(getRetbRespuesta1()));
        }
        if (n == 2) {
            getSelected().setAntbPregunta("5. ¿Ha tenido operaciones graves?");
           getSelected().setResTipob(pegar(getRetbRespuesta2()));
        }
        if (n == 3) {
            getSelected().setAntbPregunta("7. ¿Es usted?");
            getSelected().setResTipob(pegar(getRetbRespuesta3()));
        }
        if (n == 4) {
            getSelected().setAntbPregunta("10. Indique si ha tenido alguno de estos síntomas al realizar esfuerzos o ejercicio fisico: ");
            getSelected().setResTipob(pegar(getRetbRespuesta4()));
        }
        getSelected().setAnsaId(new MuAntecedenteSalud(resultados.get(0).getAnsaId()));

    //    getSelected().setResTipob("bebesote");
        System.out.println("-#######-Crear ->");
        create();
        //   getSelected().setAntaDescripcion("-");
     //   idsalud=resultados.get(0).getAnsaId();
     
    // MuRespuestaTipobController k = new MuRespuestaTipobController();
     idsalud=resultados.get(0).getAnsaId();
     
        this.idsalud = idsalud;
     //   k.setCrearresp("sol", (int)idsalud);
    }////se usa de esta forma para obtenr idasaid
       
       public String pegar(String [] miarray){
           String cadena = "";
           
        for (String miarray1 : miarray) {
            cadena = miarray1 +" , " +  cadena;
        }
           
           return cadena;
       }

    public String[] getRetbRespuesta1() {
        return retbRespuesta1;
    }

    public void setRetbRespuesta1(String[] retbRespuesta1) {
        this.retbRespuesta1 = retbRespuesta1;
    }

    public String[] getRetbRespuesta2() {
        return retbRespuesta2;
    }

    public void setRetbRespuesta2(String[] retbRespuesta2) {
        this.retbRespuesta2 = retbRespuesta2;
    }

    public String[] getRetbRespuesta3() {
        return retbRespuesta3;
    }

    public void setRetbRespuesta3(String[] retbRespuesta3) {
        this.retbRespuesta3 = retbRespuesta3;
    }

    public String[] getRetbRespuesta4() {
        return retbRespuesta4;
    }

    public void setRetbRespuesta4(String[] retbRespuesta4) {
        this.retbRespuesta4 = retbRespuesta4;
    }

    
    

    public MuAntecedentesTipobController() {
    }

    public MuAntecedentesTipob getSelected() {
        if (current == null) {
            current = new MuAntecedentesTipob();
            selectedItemIndex = -1;
        }
        return current;
    }

    private MuAntecedentesTipobFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (MuAntecedentesTipob) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new MuAntecedentesTipob();
        selectedItemIndex = -1;
        return "";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MuAntecedentesTipobCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (MuAntecedentesTipob) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MuAntecedentesTipobUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (MuAntecedentesTipob) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MuAntecedentesTipobDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public MuAntecedentesTipob getMuAntecedentesTipob(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = MuAntecedentesTipob.class)
    public static class MuAntecedentesTipobControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MuAntecedentesTipobController controller = (MuAntecedentesTipobController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "muAntecedentesTipobController");
            return controller.getMuAntecedentesTipob(getKey(value));
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
            if (object instanceof MuAntecedentesTipob) {
                MuAntecedentesTipob o = (MuAntecedentesTipob) object;
                return getStringKey(o.getAntbId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + MuAntecedentesTipob.class.getName());
            }
        }

    }

}
