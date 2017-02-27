package com.unicauca.gymadmdoc.sessionbeans;

import com.unicauca.gymadmdoc.entities.MvResultado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author CristianCamilo
 */
@Stateless
public class MvResultadoFacade extends AbstractFacade<MvResultado> {

    @PersistenceContext(unitName = "Gym_Adm_DocPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MvResultadoFacade() {
        super(MvResultado.class);
    }
    
}
