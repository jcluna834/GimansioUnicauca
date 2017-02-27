package com.unicauca.gymadmdoc.managedbeans;

import com.unicauca.gymadmdoc.entities.MaDetalleasistencia;
import com.unicauca.gymadmdoc.managedbeans.util.JsfUtil;
import com.unicauca.gymadmdoc.managedbeans.util.PaginationHelper;
import com.unicauca.gymadmdoc.sessionbeans.MaDetalleasistenciaFacade;

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

@Named("maDetalleasistenciaController")
@SessionScoped
public class MaDetalleasistenciaController implements Serializable {

   private MaDetalleasistencia current;
   private DataModel items = null;
   @EJB
   private com.unicauca.gymadmdoc.sessionbeans.MaDetalleasistenciaFacade ejbFacade;
   private PaginationHelper pagination;
   private int selectedItemIndex;

   public MaDetalleasistenciaController() {
   }

   public MaDetalleasistencia getSelected() {
      if (current == null) {
         current = new MaDetalleasistencia();
         current.setMaDetalleasistenciaPK(new com.unicauca.gymadmdoc.entities.MaDetalleasistenciaPK());
         selectedItemIndex = -1;
      }
      return current;
   }

   private MaDetalleasistenciaFacade getFacade() {
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
      current = (MaDetalleasistencia) getItems().getRowData();
      selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
      return "View";
   }

   public String prepareCreate() {
      current = new MaDetalleasistencia();
      current.setMaDetalleasistenciaPK(new com.unicauca.gymadmdoc.entities.MaDetalleasistenciaPK());
      selectedItemIndex = -1;
      return "Create";
   }

   public String create() {
      try {
         getFacade().create(current);
         JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MaDetalleasistenciaCreated"));
         return prepareCreate();
      } catch (Exception e) {
         JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
         return null;
      }
   }

   public String prepareEdit() {
      current = (MaDetalleasistencia) getItems().getRowData();
      selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
      return "Edit";
   }

   public String update() {
      try {
         getFacade().edit(current);
         JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MaDetalleasistenciaUpdated"));
         return "View";
      } catch (Exception e) {
         JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
         return null;
      }
   }

   public String destroy() {
      current = (MaDetalleasistencia) getItems().getRowData();
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
         JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MaDetalleasistenciaDeleted"));
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

   public MaDetalleasistencia getMaDetalleasistencia(com.unicauca.gymadmdoc.entities.MaDetalleasistenciaPK id) {
      return ejbFacade.find(id);
   }

   @FacesConverter(forClass = MaDetalleasistencia.class)
   public static class MaDetalleasistenciaControllerConverter implements Converter {

      private static final String SEPARATOR = "#";
      private static final String SEPARATOR_ESCAPED = "\\#";

      @Override
      public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
         if (value == null || value.length() == 0) {
            return null;
         }
         MaDetalleasistenciaController controller = (MaDetalleasistenciaController) facesContext.getApplication().getELResolver().
                 getValue(facesContext.getELContext(), null, "maDetalleasistenciaController");
         return controller.getMaDetalleasistencia(getKey(value));
      }

        com.unicauca.gymadmdoc.entities.MaDetalleasistenciaPK getKey(String value) {
            com.unicauca.gymadmdoc.entities.MaDetalleasistenciaPK key;
         String values[] = value.split(SEPARATOR_ESCAPED);
         key = new com.unicauca.gymadmdoc.entities.MaDetalleasistenciaPK();
         key.setAsiid(Long.parseLong(values[0]));
         key.setJorid(Long.parseLong(values[1]));
         key.setUsuIdentificacion(Long.parseLong(values[2]));
         return key;
      }

      String getStringKey(com.unicauca.gymadmdoc.entities.MaDetalleasistenciaPK value) {
         StringBuilder sb = new StringBuilder();
         sb.append(value.getAsiid());
         sb.append(SEPARATOR);
         sb.append(value.getJorid());
         sb.append(SEPARATOR);
         sb.append(value.getUsuIdentificacion());
         return sb.toString();
      }

      @Override
      public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
         if (object == null) {
            return null;
         }
         if (object instanceof MaDetalleasistencia) {
            MaDetalleasistencia o = (MaDetalleasistencia) object;
            return getStringKey(o.getMaDetalleasistenciaPK());
         } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + MaDetalleasistencia.class.getName());
         }
      }

   }

}
