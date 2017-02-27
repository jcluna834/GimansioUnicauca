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
public class ClienteJulio implements IControladorUsuarioCliente{
    
    
    @Override
    public String getGenero() {
        return "masculino";
    }

    @Override
    public Date getFechaNacimiento() {
        Calendar c = Calendar.getInstance();
        c.set(1988, 9, 22);
        return c.getTime();
    }

    @Override
    public double getPeso() {
        return 77;
    }

    @Override
    public double getTalla() {
        return 1.81;
    }

    @Override
    public String getSedentario() {
        return "no";
    }

    @Override
    public String getActividad() {
        return "moderada";
    }

    @Override
    public String getDeporte() {
        return "futsala";
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
        return "cluna@unicauca.edu.co";
    }

    @Override
    public String getNombre() {
        return "carlos arturo cabrera";
    }

    @Override
    public long getIdentificacion() {
        return 1234567;
    }

    @Override
    public String getTelefono() {
        return "3132442342";
    }
}
