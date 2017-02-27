package com.unicauca.gymadmdoc.sessionbeans;

import com.unicauca.gymadmdoc.entities.MvValoracion;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author CristianCamilo
 */
@Stateless
public class MvValoracionFacade extends AbstractFacade<MvValoracion> {

    @PersistenceContext(unitName = "Gym_Adm_DocPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MvValoracionFacade() {
        super(MvValoracion.class);
    }
    
    public List<MvValoracion> getAllByValCliente(long idCLiente){
        TypedQuery<MvValoracion> nq = getEntityManager().createNamedQuery("MvValoracion.findByValCliente", MvValoracion.class);
        nq.setParameter("idCliente", idCLiente);
        List<MvValoracion> resultados = nq.getResultList();
        if(resultados == null || resultados.isEmpty())
            return new ArrayList<>();
        if(resultados.size() >= 6) return resultados.subList(0, 6);
        else return resultados.subList(0, resultados.size());
    }
    
    public Integer getIdValComparar(long idCLiente, Date fechaComparar){
        TypedQuery<Integer> nq = getEntityManager().createNamedQuery("MvValoracion.findIdFechaComparar", Integer.class);
        nq.setParameter("idCliente", idCLiente);
        nq.setParameter("fechaValorar", fechaComparar);
        nq.setMaxResults(1);
        return nq.getSingleResult();
    }
    
}
