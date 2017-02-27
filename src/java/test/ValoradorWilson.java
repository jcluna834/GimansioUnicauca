/*

 */
package test;

import com.unicauca.gymadmdoc.interfaces.IControladorUsuarioValorador;

/**
 *
 * @author CristianCamilo
 */
public class ValoradorWilson implements IControladorUsuarioValorador{
    @Override
    public String getEMail() {
        return "wpantoja@gmail.com";
    }

    @Override
    public String getNombre() {
        return "Wilson Pantoja";
    }

    @Override
    public long getIdentificacion() {
        return new Long(10535093);
    }

    @Override
    public String getTelefono() {
        return "123456";
    }
}
