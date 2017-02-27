package com.unicauca.gymadmdoc.sessionbeans;

import com.unicauca.gymadmdoc.entities.MvRegistrofotografico;
import com.unicauca.gymadmdoc.entities.MvValoracion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author CristianCamilo
 */
@Stateless
public class MvRegistrofotograficoFacade extends AbstractFacade<MvRegistrofotografico> {

    @PersistenceContext(unitName = "Gym_Adm_DocPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MvRegistrofotograficoFacade() {
        super(MvRegistrofotografico.class);
    }
    
    public MvRegistrofotografico getRegistro(MvValoracion valoracion) {
        TypedQuery<MvRegistrofotografico> nq = getEntityManager().createNamedQuery("MvRegistrofotografico.findByValId", MvRegistrofotografico.class);
        nq.setParameter("valId", valoracion);
        try {
            return nq.getSingleResult();
        }catch(Exception e){return null;}
    }
}
