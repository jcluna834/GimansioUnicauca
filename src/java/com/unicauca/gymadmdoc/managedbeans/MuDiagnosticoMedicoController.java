package com.unicauca.gymadmdoc.managedbeans;

import com.unicauca.gymadmdoc.entities.MuDiagnosticoMedico;
import com.unicauca.gymadmdoc.managedbeans.util.JsfUtil;
import com.unicauca.gymadmdoc.managedbeans.util.PaginationHelper;
import com.unicauca.gymadmdoc.sessionbeans.MuDiagnosticoMedicoFacade;

import java.io.Serializable;
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

@Named("muDiagnosticoMedicoController")
@SessionScoped
public class MuDiagnosticoMedicoController implements Serializable {

   private MuDiagnosticoMedico current;
   private DataModel items = null;
   @EJB
   private com.unicauca.gymadmdoc.sessionbeans.MuDiagnosticoMedicoFacade ejbFacade;
   private PaginationHelper pagination;
   private int selectedItemIndex;

   public MuDiagnosticoMedicoController() {
   }

   public MuDiagnosticoMedico getSelected() {
      if (current == null) {
         current = new MuDiagnosticoMedico();
         selectedItemIndex = -1;
      }
      return current;
   }

   private MuDiagnosticoMedicoFacade getFacade() {
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
      current = (MuDiagnosticoMedico) getItems().getRowData();
      selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
      return "View";
   }

   public String prepareCreate() {
      current = new MuDiagnosticoMedico();
      selectedItemIndex = -1;
      return "Create";
   }

   public String create() {
      try {
         getFacade().create(current);
         JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MuDiagnosticoMedicoCreated"));
         return prepareCreate();
      } catch (Exception e) {
         JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
         return null;
      }
   }

   public String prepareEdit() {
      current = (MuDiagnosticoMedico) getItems().getRowData();
      selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
      return "Edit";
   }

   public String update() {
      try {
         getFacade().edit(current);
         JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MuDiagnosticoMedicoUpdated"));
         return "View";
      } catch (Exception e) {
         JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
         return null;
      }
   }

   public String destroy() {
      current = (MuDiagnosticoMedico) getItems().getRowData();
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
         JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MuDiagnosticoMedicoDeleted"));
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

   public MuDiagnosticoMedico getMuDiagnosticoMedico(java.lang.Long id) {
      return ejbFacade.find(id);
   }

   @FacesConverter(forClass = MuDiagnosticoMedico.class)
   public static class MuDiagnosticoMedicoControllerConverter implements Converter {

      @Override
      public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
         if (value == null || value.length() == 0) {
            return null;
         }
         MuDiagnosticoMedicoController controller = (MuDiagnosticoMedicoController) facesContext.getApplication().getELResolver().
                 getValue(facesContext.getELContext(), null, "muDiagnosticoMedicoController");
         return controller.getMuDiagnosticoMedico(getKey(value));
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
         if (object instanceof MuDiagnosticoMedico) {
            MuDiagnosticoMedico o = (MuDiagnosticoMedico) object;
            return getStringKey(o.getDimedId());
         } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + MuDiagnosticoMedico.class.getName());
         }
      }

   }

}
