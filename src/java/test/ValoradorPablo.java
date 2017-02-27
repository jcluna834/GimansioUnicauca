/*

 */
package test;

import com.unicauca.gymadmdoc.interfaces.IControladorUsuarioValorador;

/**
 *
 * @author CristianCamilo
 */
public class ValoradorPablo implements IControladorUsuarioValorador{

    @Override
    public String getEMail() {
        return "pmage@unicauca.edu.co";
    }

    @Override
    public String getNombre() {
        return "Pablo Mage";
    }

    @Override
    public long getIdentificacion() {
        return new Long(10535094);
    }

    @Override
    public String getTelefono() {
        return "123456";
    }
    
}
