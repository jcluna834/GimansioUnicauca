package com.unicauca.gymadmdoc.interfaces;

import java.io.Serializable;

/**
 *
 * @author JulioCésar
 */
public interface IControladorUsuario extends Serializable {
    public String getEMail();
    public String getNombre();
    public long getIdentificacion();
    public String getTelefono();
}