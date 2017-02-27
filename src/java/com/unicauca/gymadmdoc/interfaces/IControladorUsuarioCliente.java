package com.unicauca.gymadmdoc.interfaces;

import java.util.Date;

/**
 *
 * @author JulioCésar
 */
public interface IControladorUsuarioCliente extends IControladorUsuario{
    public String getGenero();
    public Date getFechaNacimiento();
    public double getPeso();
    public double getTalla();
    public String getSedentario();
    public String getActividad();
    public String getDeporte();
    public String getMetodo();
    public String getPeriodoEntrenamiento();
}