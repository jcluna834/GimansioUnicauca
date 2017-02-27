package com.unicauca.gymadmdoc.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author JulioCésar
 */
public interface IControladorValoracion extends Serializable {
    public List<IControladorUsuarioCliente> listarUsuariosNombre();
    public List<IControladorUsuarioCliente> listarUsuariosIdentificacion();
    
}