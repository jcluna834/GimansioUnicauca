package com.unicauca.gymadmdoc.managedbeans;

import com.unicauca.gymadmdoc.jpacontrollers.*;

import com.unicauca.gymadmdoc.entities.MuAntecedentesTipoa;
import com.unicauca.gymadmdoc.managedbeans.util.JsfUtil;
import com.unicauca.gymadmdoc.managedbeans.util.PaginationHelper;
import com.unicauca.gymadmdoc.sessionbeans.MuAntecedentesTipoaFacade;
import com.unicauca.gymadmdoc.entities.MuAntecedenteSalud;
import com.unicauca.gymadmdoc.entities.MuUsuario;

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
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Named("muAntecedentesTipoaController")
@SessionScoped
public class MuAntecedentesTipoaController implements Serializable {

    private MuAntecedentesTipoa current;
    private DataModel items = null;
    @EJB
    private com.unicauca.gymadmdoc.sessionbeans.MuAntecedentesTipoaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    private long idsalud;
    private String pegunta;
        
    private String antaDescripcion1;   
    private String antaDescripcion2;      
    private String antaDescripcion3;     
    private String antaDescripcion4;

    public String getAntaDescripcion1() {
        return antaDescripcion1;
    }

    public void setAntaDescripcion1(String antaDescripcion1) {
        this.antaDescripcion1 = antaDescripcion1;
    }

    public String getAntaDescripcion2() {
        return antaDescripcion2;
    }

    public void setAntaDescripcion2(String antaDescripcion2) {
        this.antaDescripcion2 = antaDescripcion2;
    }

    public String getAntaDescripcion3() {
        return antaDescripcion3;
    }

    public void setAntaDescripcion3(String antaDescripcion3) {
        this.antaDescripcion3 = antaDescripcion3;
    }

    public String getAntaDescripcion4() {
        return antaDescripcion4;
    }

    public void setAntaDescripcion4(String antaDescripcion4) {
        this.antaDescripcion4 = antaDescripcion4;
    }
    
    
    
    
    

    public String getPegunta() {
        return pegunta;
    }

    public void setPegunta(String pegunta) {
        this.pegunta = pegunta;
    }
    
        public long getIdsalud() {
        return idsalud;
    }

    protected EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    MuAntecedentesTipoa p = new MuAntecedentesTipoa();

    MuAntecedenteSalud u = new MuAntecedenteSalud();
    int i = 0;

    public void setIdsalud(long idsalud, int n) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Gym_Adm_DocPU");
        EntityManager em = emf.createEntityManager();

        Query query = em.createNamedQuery("MuAntecedenteSalud.findByusuIdentificacion");
        System.out.println("---*-->" + idsalud);
        u.setUsuIdentificacion(new MuUsuario(idsalud));
        query.setParameter("id", u.getUsuIdentificacion());

        List<MuAntecedenteSalud> resultados = query.getResultList();

        System.out.println(p.getAnsaId() + "--+--->" + resultados.get(0).getAnsaId());

        if (n == 1) {
            getSelected().setAntaPregunta("1. ¿Ha tenido o tiene alguna lesión osea?. En caso afirmativo, descríbalo brevemente.");
            getSelected().setAntaDescripcion(getAntaDescripcion1());
        }
        if (n == 2) {
            getSelected().setAntaPregunta("2. ¿Ha tenido o tiene alguna lesión muscular?. En caso afirmativo, descríbalo brevemente.");
            getSelected().setAntaDescripcion(getAntaDescripcion2());
        }
        if (n == 3) {
            getSelected().setAntaPregunta("3. ¿Padece usted de alguna enfermedad cardiovascular?. En caso afirmativo, descríbalo brevemente.");
            getSelected().setAntaDescripcion(getAntaDescripcion3());
        }
        if (n == 4) {
            getSelected().setAntaPregunta("6. ¿Se afixia con facilidad al realizar ejercicio físico?");
            getSelected().setAntaDescripcion("");
        }
        if (n == 5) {
            getSelected().setAntaPregunta("8. ¿Está usted embarazada o sospecha estarlo? (Sólo mujeres)");
            getSelected().setAntaDescripcion("");
        }
        if (n == 6) {
            getSelected().setAntaPregunta("9. ¿Padece de anemia en la actualidad?");
            getSelected().setAntaDescripcion("");
        }
        if (n == 7) {
            getSelected().setAntaPregunta("11. ¿Ha estado inscrito en algún gimnasio o instalación deportiva?");
            getSelected().setAntaDescripcion("");
        }
        if (n == 8) {
            getSelected().setAntaPregunta("12. ¿Tiene varices prominentes?");
            getSelected().setAntaDescripcion("");
        }
        if (n == 9) {
            getSelected().setAntaPregunta("13. ¿Tiene celulitis muy marcada?");
            getSelected().setAntaDescripcion("");
        }
        getSelected().setAnsaId(new MuAntecedenteSalud(resultados.get(0).getAnsaId()));

        // System.out.println("-#######-->"+getIdsalud());
        create();
        //   getSelected().setAntaDescripcion("-");
         idsalud=resultados.get(0).getAnsaId();
        this.idsalud = idsalud;

    }////se usa de esta forma para obtenr idasaid


    public MuAntecedentesTipoaController() {
    }

    public MuAntecedentesTipoa getSelected() {
        if (current == null) {
            current = new MuAntecedentesTipoa();
            selectedItemIndex = -1;
        }
        return current;
    }

    private MuAntecedentesTipoaFacade getFacade() {
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
        current = (MuAntecedentesTipoa) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
       // current = new MuAntecedentesTipoa();
        selectedItemIndex = -1;
        
        
        return "Create";
    }
    
    public String prepareCreate1() {
       // current = new MuAntecedentesTipoa();
        selectedItemIndex = -1;
        
        
        return "";
    }


    public String create() {   
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MuAntecedentesTipoaCreated"));
            return prepareCreate1();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (MuAntecedentesTipoa) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MuAntecedentesTipoaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (MuAntecedentesTipoa) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MuAntecedentesTipoaDeleted"));
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

    public MuAntecedentesTipoa getMuAntecedentesTipoa(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = MuAntecedentesTipoa.class)
    public static class MuAntecedentesTipoaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MuAntecedentesTipoaController controller = (MuAntecedentesTipoaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "muAntecedentesTipoaController");
            return controller.getMuAntecedentesTipoa(getKey(value));
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
            if (object instanceof MuAntecedentesTipoa) {
                MuAntecedentesTipoa o = (MuAntecedentesTipoa) object;
                return getStringKey(o.getAntaId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + MuAntecedentesTipoa.class.getName());
            }
        }

    }

}
