/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.sessionbeans;

import com.unicauca.gymadmdoc.entities.MruDia;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Sebastian V
 */
@Stateless
public class DiasDeRutinaBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "Gym_Adm_DocPU")
    private EntityManager em;

    public List<MruDia> consultarDiaPorRutina(Integer ruId) {

        TypedQuery<MruDia> consulta = (TypedQuery<MruDia>) getEm().createNamedQuery("MruDia.findByRuId");
        consulta.setParameter("ruId", ruId);
        List<MruDia> resultList = consulta.getResultList();

        return resultList;
    }

    private EntityManager getEm() {
        return em;
    }
}
