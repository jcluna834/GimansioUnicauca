package com.unicauca.gymadmdoc.sessionbeans;

import com.unicauca.gymadmdoc.entities.MvSiguientevaloracion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author CristianCamilo
 */
@Stateless
public class MvSiguientevaloracionFacade extends AbstractFacade<MvSiguientevaloracion> {

    @PersistenceContext(unitName = "Gym_Adm_DocPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MvSiguientevaloracionFacade() {
        super(MvSiguientevaloracion.class);
    }
    
}
