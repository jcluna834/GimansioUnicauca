/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.managedbeans;


import com.unicauca.gymadmdoc.entities.MuUsuario;
import com.unicauca.gymadmdoc.entities.MuUsuariogrupo;
import com.unicauca.gymadmdoc.sessionbeans.MuUsuarioFacade;
import com.unicauca.gymadmdoc.sessionbeans.MuUsuariogrupoFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author geovanny
 */
@ManagedBean
@ViewScoped
public class EliminarUsuario implements Serializable
{
    private MuUsuario usuarioSeleccionado;
    private MostrarUsuariosController managebUsuarios;
    @EJB
    private MuUsuarioFacade usuarioEJB; 
    @EJB
    private MuUsuariogrupoFacade usuarioGrupoEJB;
    
    public EliminarUsuario() 
    {
    }
    
    public void ventanaEliminarUsuario(MuUsuario usuario, MostrarUsuariosController mgb)
    {        
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.usuarioSeleccionado=usuario;
        this.managebUsuarios=mgb;
        requestContext.execute("PF('eliminarUsuario').show()");
    }

    public MuUsuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(MuUsuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }
    
    public void eliminarUsuario()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if(this.usuarioSeleccionado!=null)
        {
            if(true)
            {
                MuUsuariogrupo usuarioGrupo=this.usuarioGrupoEJB.buscarPorNombreUsuario(this.usuarioSeleccionado.getUsuNombreUsuario()).get(0);
                this.usuarioGrupoEJB.remove(usuarioGrupo);
                this.usuarioEJB.remove(this.usuarioSeleccionado);
                this.managebUsuarios.getListaUsuarios().remove(this.usuarioSeleccionado);
                requestContext.update("tablasUsuarios");
                requestContext.execute("PF('eliminarUsuario').hide()");  
                requestContext.execute("PF('eliminacionCorrecta').show()");
            }
            else
            {
                requestContext.execute("PF('eliminarUsuario').hide()");
                requestContext.execute("PF('noSePuedeEliminar').show()");
            }
        }
    }
    
    
}
