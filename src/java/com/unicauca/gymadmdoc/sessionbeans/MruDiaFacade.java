/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.sessionbeans;

import com.unicauca.gymadmdoc.entities.MruDia;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Migdress
 */
@Stateless
public class MruDiaFacade extends AbstractFacade<MruDia> {

    @PersistenceContext(unitName = "Gym_Adm_DocPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MruDiaFacade() {
        super(MruDia.class);
    }
    
}
