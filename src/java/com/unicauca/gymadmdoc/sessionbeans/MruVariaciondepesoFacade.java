/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.sessionbeans;

import com.unicauca.gymadmdoc.entities.MruVariaciondepeso;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Migdress
 */
@Stateless
public class MruVariaciondepesoFacade extends AbstractFacade<MruVariaciondepeso> {

    @PersistenceContext(unitName = "Gym_Adm_DocPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MruVariaciondepesoFacade() {
        super(MruVariaciondepeso.class);
    }
    
}
