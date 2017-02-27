/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.managedbeans;


import com.unicauca.gymadmdoc.entities.MuUsuario;
import com.unicauca.gymadmdoc.sessionbeans.MuUsuarioFacade;
import com.unicauca.gymadmdoc.utilidades.Utilidades;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author geovanny
 */
@ManagedBean
@SessionScoped
public class MostrarUsuariosController implements Serializable {

    @EJB
    private MuUsuarioFacade usuarioEJB;
    
    private List<String> listaTiposdeUsuario;
    private List<MuUsuario> listaUsuarios; 
    private boolean habilitarEstudiantes;    
    private boolean habilitarFuncionarios;
    private boolean habilitarFamiliares;
    private boolean habilitarTodos;
    private boolean habilitarTablaUsuarios;
    
    private String nombreUsuario;
    private String nombreOApellidos;
    
    
    public MostrarUsuariosController() 
    {
        
    }    
    @PostConstruct
    private void init()
    {
        this.cargarListaTiposUsuarios();
        this.InicializarValores();
    }   
    /**
     * Recupera la foto de la bd y la devuelve como un StreamedContent
     * @return flujo de la imagen
     */
    public StreamedContent getImagenFlujo() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String id = context.getExternalContext().getRequestParameterMap().get("id");
            MuUsuario usu = usuarioEJB.buscarPorIdUsuario(Long.valueOf(id)).get(0);
            if (usu.getUsuFoto()==null)
                return Utilidades.getImagenPorDefecto("foto");
            else
                return new DefaultStreamedContent(new ByteArrayInputStream(usu.getUsuFoto()));
        }
    } 

    public String getNombreOApellidos() {
        return nombreOApellidos;
    }

    public void setNombreOApellidos(String nombreOApellidos) {
        this.nombreOApellidos = nombreOApellidos;
    }
    
    public boolean isHabilitarTablaUsuarios()
    {
        return habilitarTablaUsuarios;
    }

    public void setHabilitarTablaUsuarios(boolean habilitarTablaUsuarios)
    {
        this.habilitarTablaUsuarios = habilitarTablaUsuarios;
    }
    
    public String getNombreUsuario() 
    {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) 
    {
        this.nombreUsuario = nombreUsuario;
    }

    public boolean isHabilitarTodos() {
        return habilitarTodos;
    }

    public void setHabilitarTodos(boolean habilitarTodos) {
        this.habilitarTodos = habilitarTodos;
    }
    
    public List<String> getListaTiposdeUsuario() 
    {
        return listaTiposdeUsuario;
    }

    public void setListaTiposdeUsuario(List<String> listaTiposdeUsuario) 
    {
        this.listaTiposdeUsuario = listaTiposdeUsuario;
    }
    
    public List<MuUsuario> getListaUsuarios() 
    {
        return listaUsuarios;
    }   
    
    public boolean isHabilitarEstudiantes()
    {
        return this.habilitarEstudiantes;
    }

    public void setHabilitarEstudiantes(boolean haibilitarEstudiantes) 
    {
        this.habilitarEstudiantes = haibilitarEstudiantes;
    }

    public boolean isHabilitarFuncionarios()
    {
        return this.habilitarFuncionarios;
    }

    public void setHabilitarFuncionarios(boolean habilitarFuncionarios) 
    {
        this.habilitarFuncionarios = habilitarFuncionarios;
    }

    public boolean isHabilitarFamiliares() 
    {
        return this.habilitarFamiliares;
    }

    public void setHabilitarFamiliares(boolean habilitarFamiliares) 
    {
        this.habilitarFamiliares = habilitarFamiliares;
    }
    
    private void cargarListaTiposUsuarios()
    {
        this.listaTiposdeUsuario=new ArrayList();
        this.listaTiposdeUsuario.add("Todos");
        this.listaTiposdeUsuario.add("Estudiantes");
        this.listaTiposdeUsuario.add("Funcionarios");
        this.listaTiposdeUsuario.add("Familiares");
    }
    
    private void InicializarValores()
    {
        this.habilitarEstudiantes=false;
        this.habilitarFamiliares=false;
        this.habilitarFuncionarios=false;
        this.habilitarTodos=false;
        this.habilitarTablaUsuarios=false;
    }
    
    public void cambiarTipoUsuario(ValueChangeEvent e)
    {
        String tipo=e.getNewValue().toString();
        this.habilitarEstudiantes=false;
        this.habilitarFuncionarios=false;
        this.habilitarFamiliares=false;
        this.habilitarTodos=false;
        this.habilitarTablaUsuarios=false;
        this.nombreUsuario=null;
        
        if(tipo.equals("Funcionarios"))
        {
            this.habilitarFuncionarios=true;
            this.habilitarTablaUsuarios=true;
            this.listaUsuarios=this.usuarioEJB.buscarPorFuncionario();
        }
        if(tipo.equals("Familiares"))
        {
            this.habilitarFamiliares=true;
            this.habilitarTablaUsuarios=true;
            this.listaUsuarios=this.usuarioEJB.buscarPorFamiliares();
        }
        if(tipo.equals("Estudiantes"))
        {
            this.habilitarEstudiantes=true;
            this.habilitarTablaUsuarios=true;
            this.listaUsuarios=this.usuarioEJB.buscarPorEstudiantes();
        }
        if(tipo.equals("Todos"))
        {
            this.habilitarTodos=true;
            this.habilitarTablaUsuarios=true;
            this.listaUsuarios=this.usuarioEJB.buscarTodos();
        }
    }    
    public void  buscarPorNombresOApellidos()
    {
        this.listaUsuarios=usuarioEJB.buscarPorNombresApellidos(this.nombreOApellidos.toLowerCase());
    }   
    public void  buscarPorNombresOApellidosFuncionarios()
    {
        this.listaUsuarios=usuarioEJB.buscarPorNombresApellidosFuncionarios(this.nombreOApellidos.toLowerCase());
    }   
    public void  buscarPorNombresOApellidosEstudiantes()
    {
        this.listaUsuarios=usuarioEJB.buscarPorNombresApellidosEstudiantes(this.nombreOApellidos.toLowerCase());
    }   
    public void  buscarPorNombresOApellidosFamiliares()
    {
        this.listaUsuarios=usuarioEJB.buscarPorNombresApellidosFamiliares(this.nombreOApellidos.toLowerCase());
    }   
    public void buscarPorNombreUsuario()
    {
        if(this.habilitarEstudiantes==true)
        {
            this.listaUsuarios=usuarioEJB.busacarPorNombreEstudiante(this.nombreUsuario);
        }
        else
        {
            if(this.habilitarFamiliares==true)
            {
                this.listaUsuarios=usuarioEJB.busacarPorNombreFamiliar(this.nombreUsuario);
 
            }
            else
            {
                this.listaUsuarios=usuarioEJB.busacarPorNombreFuncionario(this.nombreUsuario);
            }
        }
    }  
    
    
}
