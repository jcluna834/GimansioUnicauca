package com.unicauca.gymadmdoc.sessionbeans;

import com.unicauca.gymadmdoc.entities.MvFestivos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author CristianCamilo
 */
@Stateless
public class MvFestivosFacade extends AbstractFacade<MvFestivos> {

    @PersistenceContext(unitName = "Gym_Adm_DocPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MvFestivosFacade() {
        super(MvFestivos.class);
    }
    
}
