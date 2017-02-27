/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.sessionbeans;

import com.unicauca.gymadmdoc.entities.MruRutina;
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
public class RutinasDeUsuarioBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName="Gym_Adm_DocPU")
    private EntityManager em;

    public List<MruRutina> consultarRutinasPorUsuario(Long usuId){
       
        TypedQuery<MruRutina> consulta = (TypedQuery<MruRutina>) getEm().createNamedQuery("MruRutina.findByUsuId");
        consulta.setParameter("usuIdentificacion", usuId);
        List<MruRutina> resultList = consulta.getResultList();
        
        return resultList;
    }

    private EntityManager getEm() {
        return em;
    }
}
