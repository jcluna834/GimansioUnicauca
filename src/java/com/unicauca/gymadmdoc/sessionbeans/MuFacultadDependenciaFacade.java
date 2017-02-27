/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.sessionbeans;

import com.unicauca.gymadmdoc.entities.MuFacultadDependencia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ROED26
 */
@Stateless
public class MuFacultadDependenciaFacade extends AbstractFacade<MuFacultadDependencia> {

    @PersistenceContext(unitName = "Gym_Adm_DocPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MuFacultadDependenciaFacade() {
        super(MuFacultadDependencia.class);
    }
 
    public List<MuFacultadDependencia> buscarPorId(Long facDepId)
    {
        Query query = getEntityManager().createNamedQuery("MuFacultadDependencia.findByFacDepId");
        query.setParameter("facDepId", facDepId);
        List<MuFacultadDependencia> resultList = query.getResultList();
        
        return resultList;
    }
    

    public List<Object[]> retornarFacultadesYDependencias() {
        Query query = getEntityManager().createNamedQuery("MuFacultadDependencia.retornarFacultades");
        List<Object[]> resultList = query.getResultList();

        return resultList;
    }
}
