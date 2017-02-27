package com.unicauca.gymadmdoc.managedbeans;


import com.unicauca.gymadmdoc.entities.MuDiagnosticoMedico;
import com.unicauca.gymadmdoc.entities.MuUsuario;
import com.unicauca.gymadmdoc.sessionbeans.MuDiagnosticoMedicoFacade;
import com.unicauca.gymadmdoc.sessionbeans.MuUsuarioFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;



@ManagedBean
@SessionScoped
public class editarRegistrosIngresoController implements Serializable {

    @EJB
    private MuUsuarioFacade usuarioEJB;
    @EJB
    private MuDiagnosticoMedicoFacade diagnosticoMedicoEJB;
   
    private MuUsuario usuario;
    private String descripcionDiagnosticoMedico;
    private String medicamentos;
    private MuDiagnosticoMedico diagnosticoMedico;
    
    public editarRegistrosIngresoController() {
    
    }

    public String getDescripcionDiagnosticoMedico() {
        return descripcionDiagnosticoMedico;
    }

    public void setDescripcionDiagnosticoMedico(String descripcionDiagnosticoMedico) {
        this.descripcionDiagnosticoMedico = descripcionDiagnosticoMedico;
    }
    
    public MuUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(MuUsuario usuario) {
        this.usuario = usuario;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }
    
    public void editarDiagnostico(MuUsuario usuario) {
        this.usuario = usuario;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        diagnosticoMedico= this.diagnosticoMedicoEJB.buscarDiagnosticoMedico(usuario).get(0);
        if(diagnosticoMedico.getDimedMedicamentos()!=null){
            medicamentos=diagnosticoMedico.getDimedMedicamentos();
        }
        descripcionDiagnosticoMedico=diagnosticoMedico.getDimedDescripcion();
        requestContext.update("editarDiagnostico");
        requestContext.execute("PF('editarDiagnosticoMedico').show()");

    }
    
    public void actualizarDatos(){
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if(medicamentos.equalsIgnoreCase("")){
            medicamentos=null;
        }
        this.diagnosticoMedico.setDimedMedicamentos(medicamentos);
        this.diagnosticoMedico.setDimedDescripcion(descripcionDiagnosticoMedico);
        this.diagnosticoMedicoEJB.edit(this.diagnosticoMedico);
        requestContext.update("editarDiagnosticoMedico");
        requestContext.update("panelDiagnostico");
        requestContext.execute("PF('editarDiagnosticoMedico').hide()");
    }

    


}
