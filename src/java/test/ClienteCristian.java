/*

 */
package test;

import com.unicauca.gymadmdoc.interfaces.IControladorUsuarioCliente;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author CristianCamilo
 */
public class ClienteCristian implements IControladorUsuarioCliente{

    @Override
    public String getGenero() {
        return "masculino";
    }

    @Override
    public Date getFechaNacimiento() {
        Calendar c = Calendar.getInstance();
        c.set(1990, 11, 7);
        return c.getTime();
    }

    @Override
    public double getPeso() {
        return 70.0;
    }

    @Override
    public double getTalla() {
        return 1.70;
    }

    @Override
    public String getSedentario() {
        return "no";
    }

    @Override
    public String getActividad() {
        return "alta";
    }

    @Override
    public String getDeporte() {
        return "ciclismo";
    }

    @Override
    public String getMetodo() {
        return "pliegues";
    }

    @Override
    public String getPeriodoEntrenamiento() {
        return "gral";
    }

    @Override
    public String getEMail() {
        return "camiloerazo@unicauca.edu.co";
    }

    @Override
    public String getNombre() {
        return "cristian camilo erazo agredo";
    }

    @Override
    public long getIdentificacion() {
        return new Long(1061733703);
    }

    @Override
    public String getTelefono() {
        return "3137446095";
    }
    
}
