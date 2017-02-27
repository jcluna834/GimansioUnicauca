package com.unicauca.gymadmdoc.managedbeans;

import com.unicauca.gymadmdoc.entities.MvResultado;
import com.unicauca.gymadmdoc.entities.MvValoracion;
import com.unicauca.gymadmdoc.interfaces.IControladorUsuarioCliente;
import com.unicauca.gymadmdoc.interfaces.IControladorUsuarioValorador;
import com.unicauca.gymadmdoc.managedbeans.util.JsfUtil;
import com.unicauca.gymadmdoc.managedbeans.util.JsfUtil.PersistAction;
import com.unicauca.gymadmdoc.sessionbeans.MvValoracionFacade;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;
import test.*;

@Named("mvValoracionController")
@SessionScoped
public class MvValoracionController implements Serializable {

    @EJB
    private com.unicauca.gymadmdoc.sessionbeans.MvValoracionFacade ejbFacade;
    @EJB
    private com.unicauca.gymadmdoc.sessionbeans.MvResultadoFacade resultadoFacade;

    private IControladorUsuarioCliente cliente = new ClienteJulio();
    private IControladorUsuarioValorador valorador = new ValoradorPablo();
    private LineChartModel lineModel;
    private LineChartModel lineModelComparation;
    private PieChartModel pieModel;
    private BarChartModel barModel;
    private List<MvValoracion> items = null;
    private MvValoracion selected;
    private MvResultado result;
    private double imc;
    private String txtPorcentajeAgua;
    private double pesoCumplido;
    private double porcentajeGrasaCumplido;
    private double masaCorporalCumplido;
    private String somototipo;
    private List<MvValoracion> valoracionesUsuario;
    private MvValoracion comparar;
    private DecimalFormat decformat;
    private DateFormat dateFormat;
    
    public MvValoracionController() {
    }
    
    public DecimalFormat getDecformat() {
        return new DecimalFormat("#.##");
    }
    
    public DateFormat getDateformat() {
        return new SimpleDateFormat("dd/MM/YYYY");
    }
    
    public MvValoracion getSelected() {
        result = null;
        return selected;
    }

    public void setSelected(MvValoracion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MvValoracionFacade getFacade() {
        return ejbFacade;
    }

    public double getPesoCumplido() {
        return pesoCumplido;
    }

    public double getPorcentajeGrasaCumplido() {
        return porcentajeGrasaCumplido;
    }

    public double getMasaCorporalCumplido() {
        return masaCorporalCumplido;
    }

    public String getTxtPorcentajeAgua() {
        return txtPorcentajeAgua;
    }

    public double getImc() {
        return imc;
    }

    public String getSomototipo() {
        return somototipo;
    }

    public MvValoracion prepareCreate() {
        selected = new MvValoracion();
        initializeEmbeddableKey();
        return selected;
    }

    public MvResultado getResult() {
        if (selected != null) {
            result = selected.getResId();
        }
        return result;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/mvMensajes").getString("MvValoracionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            result = selected.getResId();
            initChart();
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/mvMensajes").getString("MvValoracionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/mvMensajes").getString("MvValoracionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            result = null;
        }
    }

    public List<MvValoracion> getItems() {
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
                    if (PersistAction.CREATE == persistAction) {
                        selected.setValCliente((int) cliente.getIdentificacion());
                        selected.setValValorador((int) valorador.getIdentificacion());
                        selected.setValId(null);
                        selected.setValFechavaloracion(new Date());
                        if (selected.getValVo2max() == null) {
                            selected.setValVo2max(0.0);
                        }
                        getFacade().create(selected);//guarda la valoracion
                        actualizarSelected();
                        result = resultadoFacade.find(llenarResultados());
                        selected.setResId(result);
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

    private void actualizarSelected() {
        getFacade().flush();
    }

    public MvValoracion getMvValoracion(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<MvValoracion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MvValoracion> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = MvValoracion.class)
    public static class MvValoracionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MvValoracionController controller = (MvValoracionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mvValoracionController");
            return controller.getMvValoracion(getKey(value));
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
            if (object instanceof MvValoracion) {
                MvValoracion o = (MvValoracion) object;
                return getStringKey(o.getValId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MvValoracion.class.getName()});
                return null;
            }
        }

    }

    private double Yuhanz(MvResultado result) {
        if (cliente.getGenero().equalsIgnoreCase("FEMENINO")) {
            return (result.getResSumatoria6pliegues() * 0.1429) + 4.56;
        } else {
            return (result.getResSumatoria6pliegues() * 0.097) + 3.64;
        }
    }

    private double Jackson(MvResultado result) {
        if (cliente.getGenero().equalsIgnoreCase("FEMENINO")) {
            return (4.95 / (1.0994921 - (0.0009929 * (sumTSiMa())) + (0.0000023 * Math.pow((sumTSiMa()), 2)) - (0.0001392 * selected.getValEdaddecimal())) - 4.5) * 100;
        } else {
            return (4.95 / (1.10938 - (0.0008267 * (sumPAMa())) + (0.0000016 * Math.pow((sumPAMa()), 2)) - (0.0002574 * selected.getValEdaddecimal())) - 4.5) * 100;
        }
    }

    private double sumPAMa() {
        return selected.getValPectoral() + selected.getValAbdominal() + selected.getValMusloanterior();
    }

    private double sumTSiMa() {
        return selected.getValTricepts() + selected.getValSupraiiatico() + selected.getValMusloanterior();
    }

    private double Endomorfia(MvResultado result) {
        return (-0.7182 + (0.1451 * (sumTSeSi()) * (170.18 / (cliente.getTalla() * 100))) - (0.00068 * Math.pow((sumTSeSi()) * (170.18 / (cliente.getTalla() * 100)), 2)) + (0.0000014 * ((sumTSeSi()) * Math.pow(170.18 / (cliente.getTalla() * 100), 3))));
    }

    private double sumTSeSi() {
        return selected.getValTricepts() + selected.getValSubescapular() + selected.getValSupraiiatico();
    }

    private double Mesomorfia(MvResultado result) {
        return ((0.858 * selected.getValBiepicondilarhumeral()) + (0.601 * selected.getValBiepicondilarfemoral())) + ((selected.getValBicepscontraidoder() - (selected.getValTricepts() / 10)) * 0.188) + ((selected.getValPantorrillader() - (selected.getValMedialpierna() / 10)) * 0.161) - (0.131 * (cliente.getTalla() * 100)) + 4.5;
    }

    private double Ectomorfia(MvResultado result) {
        if ((cliente.getTalla() * 100) / Math.pow(cliente.getPeso(), 0.33333333333) <= 40.75) {
            return ((cliente.getTalla() * 100) / Math.pow(cliente.getPeso(), 0.33333333333) * 0.463) - 17.63;
        } else {
            return ((cliente.getTalla() * 100) / Math.pow(cliente.getPeso(), 0.33333333333) * 0.732) - 28.58;
        }
    }

    private String EstadoFemenino(MvResultado result) {
        if (result.getResPorcentajegrasacorporaltotal() <= 14) {
            return "Atletico";
        } else if (result.getResPorcentajegrasacorporaltotal() >= 14 && result.getResPorcentajegrasacorporaltotal() <= 17) {
            return "Bueno";
        } else if (result.getResPorcentajegrasacorporaltotal() >= 17 && result.getResPorcentajegrasacorporaltotal() <= 22) {
            return "Aceptable";
        } else if (result.getResPorcentajegrasacorporaltotal() >= 22 && result.getResPorcentajegrasacorporaltotal() <= 29) {
            return "Muy gordo";
        } else {
            return "Obeso";
        }
    }

    private String EstadoMasculino(MvResultado result) {
        if (result.getResPorcentajegrasacorporaltotal() <= 9) {
            return "Atletico";
        } else if (result.getResPorcentajegrasacorporaltotal() >= 9 && result.getResPorcentajegrasacorporaltotal() <= 13) {
            return "Bueno";
        } else if (result.getResPorcentajegrasacorporaltotal() >= 13 && result.getResPorcentajegrasacorporaltotal() <= 16) {
            return "Aceptable";
        } else if (result.getResPorcentajegrasacorporaltotal() >= 16 && result.getResPorcentajegrasacorporaltotal() <= 19) {
            return "Muy gordo";
        } else {
            return "Obeso";
        }
    }

    private String EnfermedadCoronariaFemenino(MvResultado result) {
        if (result.getResRelacioncinturacadera() <= 0.75) {
            return "Excelente";
        } else if (result.getResRelacioncinturacadera() >= 0.75 && result.getResRelacioncinturacadera() <= 0.8) {
            return "Bueno";
        } else if (result.getResRelacioncinturacadera() >= 0.8 && result.getResRelacioncinturacadera() <= 0.85) {
            return "Promedio";
        } else if (result.getResRelacioncinturacadera() >= 0.85 && result.getResRelacioncinturacadera() <= 0.9) {
            return "Riesgo alto";
        } else {
            return "Riesgo extremo";
        }
    }

    private String EnfermedadCoronariaMasculino(MvResultado result) {
        if (result.getResRelacioncinturacadera() <= 0.85) {
            return "Excelente";
        } else if (result.getResRelacioncinturacadera() >= 0.85 && result.getResRelacioncinturacadera() <= 0.9) {
            return "Bueno";
        } else if (result.getResRelacioncinturacadera() >= 0.9 && result.getResRelacioncinturacadera() <= 0.95) {
            return "Promedio";
        } else if (result.getResRelacioncinturacadera() >= 0.95 && result.getResRelacioncinturacadera() <= 1) {
            return "Riesgo alto";
        } else {
            return "Riesgo extremo";
        }
    }

    public String textoPorcentajeAgua(MvValoracion selected) {
        if (selected.getValPorcentajeaguacorporal() >= 60) {
            return "No requiere reponer líquidos";
        } else if (selected.getValPorcentajeaguacorporal() >= 55 && selected.getValPorcentajeaguacorporal() <= 59) {
            return "Requiere reponer líquidos con agua";
        } else {
            return "Requiere reponer líquidos con bebida hidratante";
        }
    }

    public double cumplidoPeso(MvResultado result) {
        result = selected.getResId();
        if (result.getResPesoanterior() == null) {
            return 0.0;
        } else {
            return (result.getResPesoanterior() - result.getResPesototal()) / (result.getResPesoanterior() - result.getResPesoideal());
        }
    }

    public double cumplidoPorcentajeGrasa(MvResultado result) {
        result = selected.getResId();
        if (result.getResPorcentajegrasaanterior() == null) {
            return 0.0;
        } else {
            return (result.getResPorcentajegrasaanterior() - result.getResPorcentajegrasacorporaltotal()) / (result.getResPorcentajegrasaanterior() - result.getResPorcentajegrasaideal());
        }
    }

    public double cumplidoMasaCorporal(MvResultado result) {
        result = selected.getResId();
        if (result.getResPesomuscularanterior() == null) {
            return 0.0;
        } else {
            return (Double.parseDouble(result.getResPesomuscularanterior()) - result.getResPesomuscular()) / (Double.parseDouble(result.getResPesomuscularanterior()) - result.getResPesomuscularideal());
        }
    }

    public String valorSomatotipo(MvResultado result) {
        result = selected.getResId();
        if (result.getResEndomorfia() > result.getResMesomorfia() && result.getResEndomorfia() > result.getResEctomorfia()) {
            if (result.getResEctomorfia() > result.getResMesomorfia()) {
                return "ENDOMORFO-ECTOMORFO";
            } else {
                return "ENDOMORFO-MESOMORFO";
            }
        } else if (result.getResEctomorfia() > result.getResMesomorfia() && result.getResEctomorfia() > result.getResEndomorfia()) {
            if (result.getResEndomorfia() > result.getResMesomorfia()) {
                return "ECTOMORFO-ENDOMORFO";
            } else {
                return "ECTOMORFO-MESOMORFO";
            }
        } else if (result.getResMesomorfia() > result.getResEctomorfia() && result.getResMesomorfia() > result.getResEndomorfia()) {
            if (result.getResEndomorfia() > result.getResEctomorfia()) {
                return "MESOMORFO-ENDOMORFO";
            } else {
                return "MESOMORFO-ECTOMORFO";
            }
        } else {
            return "SOMOTOTIPO PENDIENTE";
        }
    }

    public double valorImc() {
        return cliente.getPeso() / (Math.pow(cliente.getTalla(), 2));
    }

    private int llenarResultados() {
        Calendar c = Calendar.getInstance();
        result = new MvResultado();
        selected.setValEdaddecimal((c.getTime().getTime() - cliente.getFechaNacimiento().getTime()) / (365.25 * 24 * 60 * 60 * 1000));
        result.setResSumatoria6pliegues(sumTSeSi() + selected.getValAbdominal() + selected.getValMusloanterior() + selected.getValMedialpierna());
        result.setResPesoideal((Math.pow(cliente.getTalla(), 2) * 22));
        result.setResPesototal((cliente.getPeso()));
        result.setResPorcentajegrasaideal(9.0);  //Constante - falta la formula
        result.setResPesograsoideal(((result.getResPesoideal() * result.getResPorcentajegrasaideal()) / 100.0));

        if (selected.getValPorcentajegrasaimpedanciometria() == null) {
            selected.setValPorcentajegrasaimpedanciometria(0.0);
        }
        if (cliente.getMetodo().equalsIgnoreCase("im")) {
            result.setResPorcentajegrasacorporaltotal(selected.getValPorcentajegrasaimpedanciometria());
        } else if (cliente.getMetodo().equalsIgnoreCase("pliegues") && cliente.getSedentario().equalsIgnoreCase("SI")) {
            result.setResPorcentajegrasacorporaltotal(Jackson(result));
        } else if (cliente.getMetodo().equalsIgnoreCase("pliegues") && cliente.getSedentario().equalsIgnoreCase("NO")) {
            result.setResPorcentajegrasacorporaltotal(Yuhanz(result));
        }

        result.setResPesograso((cliente.getPeso() * result.getResPorcentajegrasacorporaltotal()) / 100);

        result.setResPesooseo((3.02 * Math.pow((Math.pow(cliente.getTalla(), 2) * (selected.getValRadiocubital() / 100) * (selected.getValBiepicondilarfemoral() / 100) * 400), 0.712)));

        result.setResPorcentajepesooseo((result.getResPesooseo() * 100) / cliente.getPeso());

        if (cliente.getGenero().equalsIgnoreCase("FEMENINO")) {
            result.setResPesoresidual(((result.getResPesototal() * 20.9) / 100));
        } else {
            result.setResPesoresidual(((result.getResPesototal() * 24.1) / 100));
        }

        result.setResPesomuscular((cliente.getPeso() - (result.getResPesograso() + result.getResPesooseo() + result.getResPesoresidual())));

        if (cliente.getGenero().equalsIgnoreCase("FEMENINO")) {
            result.setResPorcentajepesoresidual((20.9));
        } else {
            result.setResPorcentajepesoresidual((24.1));
        }

        result.setResPorcentajeoseoideal(((result.getResPesooseo() / result.getResPesoideal()) * 100.0));

        result.setResPesomuscularideal(result.getResPesoideal() - (result.getResPesograsoideal() + result.getResPesoresidual() + result.getResPesooseo()));

        result.setResPorcentajepesomuscular(((result.getResPesomuscular() * 100) / cliente.getPeso()));

        result.setResPorcentajepesomuscularideal(((((100.0 - result.getResPorcentajepesoresidual()) - result.getResPorcentajegrasaideal()) - result.getResPorcentajeoseoideal())));

        result.setResTmb1hora((((15.3 * result.getResPesoideal()) + 679.0) / 24.0));

        if (cliente.getGenero().equalsIgnoreCase("FEMENINO")) {
            result.setResTmb24horas(((655 + (11 * cliente.getPeso()) + (2.5 * cliente.getTalla()) - (5 * selected.getValEdaddecimal()))));
        } else {
            result.setResTmb24horas(((666 + (15 * cliente.getPeso()) + (5.5 * cliente.getTalla()) - (7 * selected.getValEdaddecimal()))));
        }

        result.setResPesoidealinferior((Math.pow(cliente.getTalla(), 2) * 19));

        result.setResMasacorporalmagra(result.getResPesototal() - result.getResPesograso());
        result.setResMasacorporalmagraideal((75.0)); //Constante - falta la formula
        result.setResRelacioncinturacadera(selected.getValAbdomeninferior() / selected.getValCadera());

        result.setResPesoideallimite((Math.pow(cliente.getTalla(), 2) * 24));

        result.setResEndomorfia((Endomorfia(result)));

        result.setResMesomorfia((Mesomorfia(result)));
        result.setResEctomorfia((Ectomorfia(result)));

        result.setResEjex(result.getResEctomorfia() - result.getResEndomorfia());
        result.setResEjey((2.0 * (result.getResMesomorfia()) - (result.getResEndomorfia() + result.getResEctomorfia())));

        if (cliente.getGenero().equalsIgnoreCase("FEMENINO")) {
            result.setResEstado(EstadoFemenino(result));
        } else {
            result.setResEstado(EstadoMasculino(result));
        }

        imc = cliente.getPeso() / (Math.pow(cliente.getTalla(), 2));
        if (imc <= 20) {
            result.setResContextura("Desnutricion");
        } else if (imc >= 20 && imc <= 25) {
            result.setResContextura("Saludable");
        } else if (imc >= 25 && imc <= 30) {
            result.setResContextura("Riesgo obeso");
        } else if (imc >= 30 && imc <= 35) {
            result.setResContextura("Obeso I");
        } else {
            result.setResContextura("Obeso II");
        }

        if (cliente.getGenero().equalsIgnoreCase("FEMENINO")) {
            result.setResEnfermedadcoronaria(EnfermedadCoronariaFemenino(result));
        } else {
            result.setResEnfermedadcoronaria(EnfermedadCoronariaMasculino(result));
        }
        if (selected.getValPorcentajeaguacorporal() == null) {
            selected.setValPorcentajeaguacorporal((0.0));
        }
        if (selected.getValPorcentajeaguacorporal() > 0.64) {
            result.setResHidratacion("Optimo");
        } else if (selected.getValPorcentajeaguacorporal() < 0.65 && selected.getValPorcentajeaguacorporal() > 59.0) {
            result.setResHidratacion("Aceptable");
        } else if (selected.getValPorcentajeaguacorporal() < 0.60 && selected.getValPorcentajeaguacorporal() > 56.0) {
            result.setResHidratacion("Deficiente");
        } else {
            result.setResHidratacion("Deshidratado");
        }

        double sedentarioHombre = 57.8 - (0.445 * selected.getValEdaddecimal());
        double acondicionadoHombre = 69.7 - (0.612 * selected.getValEdaddecimal());
        double sedentarioMujer = 42.3 - (0.351 * selected.getValEdaddecimal());
        double aconicionadoMujer = 52.9 - (0.312 * selected.getValEdaddecimal());
        if (cliente.getSedentario().equalsIgnoreCase("SI") && cliente.getGenero().equalsIgnoreCase("MASCULINO")) {
            result.setResVo2maxextimado((sedentarioHombre));
        } else if (cliente.getSedentario().equalsIgnoreCase("NO") && cliente.getGenero().equalsIgnoreCase("MASCULINO")) {
            result.setResVo2maxextimado((acondicionadoHombre));
        } else if (cliente.getSedentario().equalsIgnoreCase("SI") && cliente.getGenero().equalsIgnoreCase("FEMENINO")) {
            result.setResVo2maxextimado((sedentarioMujer));
        } else if (cliente.getSedentario().equalsIgnoreCase("NO") && cliente.getGenero().equalsIgnoreCase("FEMENINO")) {
            result.setResVo2maxextimado((aconicionadoMujer));
        }
        result.setResDeficitaerobico((result.getResVo2maxextimado() - selected.getValVo2max()) / result.getResVo2maxextimado());
        if (result.getResDeficitaerobico() <= (-0.24)) {
            result.setResCapacidadfuncional("Excelente");
        } else if (result.getResDeficitaerobico() <= (-1) && result.getResDeficitaerobico() > (-0.24)) {
            result.setResCapacidadfuncional("Buena");
        } else if (result.getResDeficitaerobico() <= (-0.26) && result.getResDeficitaerobico() >= (-1)) {
            result.setResCapacidadfuncional("Promedio");
        } else {
            result.setResCapacidadfuncional("Mala");
        }

        if (cliente.getActividad().equalsIgnoreCase("LIGERA")) {
            result.setResCaloriasrequeridadieta(((result.getResTmb24horas() / 40) * result.getResPesoideal() * 0.9));
        } else if (cliente.getActividad().equalsIgnoreCase("MODERADA")) {
            result.setResCaloriasrequeridadieta((result.getResTmb24horas() / 40) * result.getResPesoideal() * 1);
        } else if (cliente.getActividad().equalsIgnoreCase("ALTA")) {
            result.setResCaloriasrequeridadieta(((result.getResTmb24horas() / 40) * result.getResPesoideal() * 1.17));
        } else if (cliente.getActividad().equalsIgnoreCase("EXCESIVA")) {
            result.setResCaloriasrequeridadieta(((result.getResTmb24horas() / 40) * result.getResPesoideal() * 1.34));
        }
        result.setResGramoproteinadia((result.getResMasacorporalmagra() * 2.0));
        result.setResSemanaprograma((5.0)); //Constante
        result.setResSesionessemana((4.0)); //Constante
        result.setResQuemacaloriassemana(((result.getResPesograso() - result.getResPesograsoideal()) * 7000) / result.getResSemanaprograma());
        result.setResQuemacaloriassesion(result.getResQuemacaloriassemana() / result.getResSesionessemana());
        if (cliente.getGenero().equalsIgnoreCase("FEMENINO")) {
            result.setResRangosfreccardquemagrasamin(((225 - selected.getValEdaddecimal()) * 0.65));
        } else {
            result.setResRangosfreccardquemagrasamin(((220 - selected.getValEdaddecimal()) * 0.65));
        }
        if (cliente.getGenero().equalsIgnoreCase("FEMENINO")) {
            result.setResRangosfreccardquemagrasamax((((225 - selected.getValEdaddecimal()) - selected.getValFrecuenciacardiacareposo()) * 0.8) + selected.getValFrecuenciacardiacareposo());
        } else {
            result.setResRangosfreccardquemagrasamax((((220 - selected.getValEdaddecimal()) - selected.getValFrecuenciacardiacareposo()) * 0.8) + selected.getValFrecuenciacardiacareposo());
        }

        /*txtPorcentajeAgua = textoPorcentajeAgua(selected);
        pesoCumplido = cumplidoPeso(result);
        porcentajeGrasaCumplido = cumplidoPorcentajeGrasa(result);
        masaCorporalCumplido = cumplidoMasaCorporal(result);
        somototipo = valorSomatotipo(result);*/
        result.setResId(null);
        result.setValId(selected);
        resultadoFacade.create(result);
        resultadoFacade.flush();
        return result.getResId();
    }

    private void initChart() {
        createLineModels();
        createPieModels();
        createBarModels();
    }

    public LineChartModel getLineModel() {
        result = selected.getResId();
        createLineModels();
        return lineModel;
    }

    private void createLineModels() {
        lineModel = initLinearModel();
        lineModel.setTitle("Somatocarta");
        Axis xAxis = lineModel.getAxis(AxisType.X);
        xAxis.setMin(-9);
        xAxis.setMax(9);
        xAxis.setTickInterval("1");
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setMin(-10);
        yAxis.setMax(14);
        yAxis.setTickInterval("2");
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries ejeY = new LineChartSeries();
        ejeY.set("0.00000", 11);
        ejeY.set("0.000001", -8.5);
        ejeY.setShowMarker(false);

        LineChartSeries ejeX = new LineChartSeries();
        ejeX.set(-5.5, 0.0);
        ejeX.set(5.5, 0.0);
        ejeX.setShowMarker(false);

        LineChartSeries punto = new LineChartSeries();
        punto.set(result.getResEjex(), result.getResEjey());

        LineChartSeries somot1 = new LineChartSeries();
        somot1.set(-6.0, -6.4);
        somot1.set(-5.5, 0.0);
        somot1.set(-4.0, 6.0);
        somot1.set(-2.0, 10.0);
        somot1.set(0.0, 11.0);
        somot1.set(2.0, 10.0);
        somot1.set(4.0, 6.0);
        somot1.set(5.5, 0.0);
        somot1.set(6.0, -6.4);
        somot1.setShowMarker(false);

        LineChartSeries somot2 = new LineChartSeries();
        somot2.set(-6.0, -6.4);
        somot2.set(0.0, 0.0);
        somot2.set(4.55, 4.0);
        somot2.setShowMarker(false);

        LineChartSeries somot3 = new LineChartSeries();
        somot3.set(6.0, -6.4);
        somot3.set(0.0, 0.0);
        somot3.set(-4.55, 4.0);
        somot3.setShowMarker(false);

        LineChartSeries somot4 = new LineChartSeries();
        somot4.set(-6.0, -6.4);
        somot4.set(-3.5, -8.0);
        somot4.set(0.0, -8.5);
        somot4.set(3.5, -8.0);
        somot4.set(6.0, -6.4);
        somot4.setShowMarker(false);

        /*LineChartSeries mesomorfo = new LineChartSeries();
        mesomorfo.set(0.0, 13.0);
        mesomorfo.setShowMarker(false);
        mesomorfo.setLabel("MESOMORFIA");*/
        model.addSeries(ejeY);
        model.addSeries(ejeX);
        model.addSeries(punto);
        model.addSeries(somot1);
        model.addSeries(somot2);
        model.addSeries(somot3);
        model.addSeries(somot4);
        //model.addSeries(mesomorfo);
        model.setSeriesColors("000000,000000,000000,000000,000000,000000,000000");
        model.setShadow(false);
        model.setShowDatatip(false);
        //model.setStacked(false);
        return model;
    }

    public PieChartModel getPieModel(int opc) {
        if (opc == 0) result = selected.getResId();
        else result = comparar.getResId();
        createPieModels();
        return pieModel;
    }

    private void createPieModels() {
        createPieModel();
    }

    private void createPieModel() {
        pieModel = new PieChartModel();
        pieModel.set("Porcentaje de grasa corporal total", result.getResPorcentajegrasacorporaltotal());
        pieModel.set("Porcentaje de peso oseo", result.getResPesooseo());
        pieModel.set("Porcentaje de peso muscular", result.getResPorcentajepesomuscular());
        pieModel.set("Porcentaje de peso residual", result.getResPorcentajepesoresidual());
        pieModel.setTitle("Composición corporal");
        pieModel.setLegendPosition("w");
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(150);
    }

    public BarChartModel getBarModel() {
        result = selected.getResId();
        createBarModels();
        return barModel;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        ChartSeries pesoTotal = new ChartSeries();
        pesoTotal.set("Objetivos", result.getResPesototal());
        pesoTotal.setLabel("Peso Total");
        ChartSeries pesoIdeal = new ChartSeries();
        pesoIdeal.set("Objetivos", result.getResPesoideal());
        pesoIdeal.setLabel("Peso ideal");
        ChartSeries masaMuscularTotal = new ChartSeries();
        masaMuscularTotal.set("Objetivos", result.getResPesomuscular());
        masaMuscularTotal.setLabel("Masa muscular total");
        ChartSeries masaMuscularIdeal = new ChartSeries();
        masaMuscularIdeal.set("Objetivos", result.getResPesomuscularideal());
        masaMuscularIdeal.setLabel("Masa muscular ideal");
        ChartSeries porcGrasaCorporal = new ChartSeries();
        porcGrasaCorporal.set("Objetivos", result.getResPorcentajegrasacorporaltotal());
        porcGrasaCorporal.setLabel("% de grasa corporal");
        ChartSeries porcGrasaIdeal = new ChartSeries();
        porcGrasaIdeal.set("Objetivos", result.getResPorcentajegrasaideal());
        porcGrasaIdeal.setLabel("% de grasa ideal");
        model.addSeries(pesoTotal);
        model.addSeries(pesoIdeal);
        model.addSeries(masaMuscularTotal);
        model.addSeries(masaMuscularIdeal);
        model.addSeries(porcGrasaCorporal);
        model.addSeries(porcGrasaIdeal);
        model.setShadow(false);
        model.setShowDatatip(false);
        model.setStacked(false);
        return model;
    }

    private void createBarModels() {
        createBarModel();
    }

    private void createBarModel() {
        barModel = initBarModel();
        barModel.setTitle("Objetivos");
        barModel.setLegendPosition("ne");
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(90);
        yAxis.setTickInterval("10");
    }

    public void guardarRecomendaciones() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "La recomendación ha sido guardada exitosamente."));
        result = selected.getResId();
        resultadoFacade.edit(result);
        resultadoFacade.flush();
    }
    
    private List<MvValoracion> fechasValoraciones() {
        valoracionesUsuario = ejbFacade.getAllByValCliente(selected.getValCliente());
        return valoracionesUsuario;
    }
    
    public List<MvValoracion> getValoracionesUsuario() {
        return fechasValoraciones();
    }

    public void setComparar(MvValoracion comparar) {
        this.comparar = comparar;
    }

    public MvValoracion getComparar() {
        return comparar;
    }
    
    public LineChartModel getLineModelComparation() {
        result = selected.getResId();
        createLineModelsComparation();
        return lineModelComparation;
    }
    
    private void createLineModelsComparation() {
        lineModelComparation = initLinearModelComparation();
        lineModelComparation.setTitle("Gráfica comparación %Masa y %Grasa");
        Axis yAxis = lineModelComparation.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(60);
        yAxis.setTickInterval("10");
        DateAxis axis = new DateAxis("Fecha valoración");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, 1);
        //axis.setMax(dateToString(c,0));
        axis.setTickFormat("%d %b %y");
        lineModelComparation.getAxes().put(AxisType.X, axis);
    }
    
    private String dateToString(Calendar c, int opc) {
        String anio = c.get(Calendar.YEAR) + "";
        String mes = (c.get(Calendar.MONTH)+1) + "";
        String dia = c.get(Calendar.DAY_OF_MONTH) + "";
        if(dia.length() == 1)
            dia = "0" + dia;
        if(mes.length() == 1)
            mes = "0" + mes;
        if(opc == 0)
            return anio + "-" + mes + "-" + dia;
        else
            return dia  + "-" + mes + "-" + anio;
    }

    private LineChartModel initLinearModelComparation() {
        LineChartModel model = new LineChartModel();
        LineChartSeries porcGrasa = new LineChartSeries();
        porcGrasa.setLabel("% Grasa");
        LineChartSeries porcMasa = new LineChartSeries();
        porcMasa.setLabel("% Masa");
        Calendar c = Calendar.getInstance();
        for (MvValoracion val : valoracionesUsuario) {
            c.setTime(val.getValFechavaloracion());
            porcGrasa.set(dateToString(c, 0), val.getResId().getResPorcentajegrasacorporaltotal());
            porcMasa.set(dateToString(c, 0), val.getResId().getResPorcentajepesomuscular());
        }
        porcGrasa.setShowMarker(true);
        model.addSeries(porcGrasa);
        model.addSeries(porcMasa);
        model.setShowDatatip(false);
        model.setStacked(false);
        model.setLegendPosition("ne");
        return model;
    }
}
