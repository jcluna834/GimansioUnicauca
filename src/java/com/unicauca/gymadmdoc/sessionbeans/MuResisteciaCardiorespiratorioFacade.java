/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.sessionbeans;

import com.unicauca.gymadmdoc.entities.MuResisteciaCardiorespiratorio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ROED26
 */
@Stateless
public class MuResisteciaCardiorespiratorioFacade extends AbstractFacade<MuResisteciaCardiorespiratorio> {

    @PersistenceContext(unitName = "Gym_Adm_DocPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MuResisteciaCardiorespiratorioFacade() {
        super(MuResisteciaCardiorespiratorio.class);
    }
    
}
