package com.unicauca.gymadmdoc.managedbeans;


import com.unicauca.gymadmdoc.entities.MuDiagnosticoMedico;
import com.unicauca.gymadmdoc.entities.MuExamenFisico;
import com.unicauca.gymadmdoc.entities.MuUsuario;
import com.unicauca.gymadmdoc.sessionbeans.MuDiagnosticoMedicoFacade;
import com.unicauca.gymadmdoc.sessionbeans.MuExamenFisicoFacade;
import com.unicauca.gymadmdoc.sessionbeans.MuUsuarioFacade;
import com.unicauca.gymadmdoc.utilidades.Utilidades;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;



@ManagedBean
@SessionScoped
public class VerRegistrosIngresoController implements Serializable {

    @EJB
    private MuUsuarioFacade usuarioEJB;
    @EJB
    private MuDiagnosticoMedicoFacade diagnosticoMedicoEJB;
    @EJB
    private MuExamenFisicoFacade examenFisicoEJB;
    
    private MuUsuario usuario;
    private String descripcionDiagnosticoMedico;
    private String medicamentos;
    private float peso;
    private float talla;
    private float fc;
    private float fcm;
    private float ta;
    private float fr;
    private float imc;
    private float icc;
    private String fecha;
    private MuDiagnosticoMedico diagnosticoMedico;
    private MuExamenFisico examenFisico;
    private SimpleDateFormat sdf;
    public VerRegistrosIngresoController() {
        this.sdf = new SimpleDateFormat("yyyy-MM-dd");
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

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getTalla() {
        return talla;
    }

    public void setTalla(float talla) {
        this.talla = talla;
    }

    public float getFc() {
        return fc;
    }

    public void setFc(float fc) {
        this.fc = fc;
    }

    public float getFcm() {
        return fcm;
    }

    public void setFcm(float fcm) {
        this.fcm = fcm;
    }

    public float getTa() {
        return ta;
    }

    public void setTa(float ta) {
        this.ta = ta;
    }

    public float getFr() {
        return fr;
    }

    public void setFr(float fr) {
        this.fr = fr;
    }

    public float getImc() {
        return imc;
    }

    public void setImc(float imc) {
        this.imc = imc;
    }

    public float getIcc() {
        return icc;
    }

    public void setIcc(float icc) {
        this.icc = icc;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    public void verDiagnostico(MuUsuario usuario) {
        this.usuario = usuario;
        
        RequestContext requestContext = RequestContext.getCurrentInstance();
        
        diagnosticoMedico= this.diagnosticoMedicoEJB.buscarDiagnosticoMedico(usuario).get(0);
        if(diagnosticoMedico.getDimedMedicamentos()==null){
            medicamentos="No consume medicamentos";
        }else{
            medicamentos=diagnosticoMedico.getDimedMedicamentos();
        }
        descripcionDiagnosticoMedico=diagnosticoMedico.getDimedDescripcion();
        requestContext.update("Diagnostico");
        requestContext.execute("PF('verDiagnosticoMedico').show()");

    }   
    
    public void verExamenFisico(MuUsuario usuario) {
        this.usuario = usuario;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.examenFisico= this.examenFisicoEJB.buscarExamenFisico(usuario).get(0);
        this.fecha=sdf.format(this.examenFisico.getEfisFecha());
        this.peso=this.examenFisico.getEfisPeso();
        this.talla=this.examenFisico.getEfisTalla();
        this.fc=this.examenFisico.getEfisFc();
        this.fcm=this.examenFisico.getEfisFcm();
        this.ta=this.examenFisico.getEfisTa();    
        this.fr=this.examenFisico.getEfisFr();    
        this.icc=this.examenFisico.getEfisIcc();
        this.imc= this.examenFisico.getEfisImc();
        requestContext.update("ExamenFisico");
        requestContext.execute("PF('verExamenFisico').show()");

    }

    public StreamedContent getFotoFrente() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            if (examenFisico.getEfisFotoCuerpoCompletoFrente()== null) {
                return Utilidades.getImagenPorDefecto("foto");
            } else {
                return new DefaultStreamedContent(new ByteArrayInputStream(examenFisico.getEfisFotoCuerpoCompletoFrente()));
            }
        }
    }
    public StreamedContent getFotoEspalda() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {

            if (examenFisico.getEfisFotoCuerpoCompletoEspalda()== null) {
                return Utilidades.getImagenPorDefecto("foto");
            } else {
                return new DefaultStreamedContent(new ByteArrayInputStream(examenFisico.getEfisFotoCuerpoCompletoEspalda()));
            }
        }
    }
    public StreamedContent getFotoPerfilDer() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {

            if (examenFisico.getEfisFotoCuerpoCompletoPerfilDer()== null) {
                return Utilidades.getImagenPorDefecto("foto");
            } else {
                return new DefaultStreamedContent(new ByteArrayInputStream(examenFisico.getEfisFotoCuerpoCompletoPerfilDer()));
            }
        }
    }
    public StreamedContent getFotoPerfilIzq() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {

            if (examenFisico.getEfisFotoCuerpoCompletoPerfilIzq()== null) {
                return Utilidades.getImagenPorDefecto("foto");
            } else {
                return new DefaultStreamedContent(new ByteArrayInputStream(examenFisico.getEfisFotoCuerpoCompletoPerfilIzq()));
            }
        }
    }


}
