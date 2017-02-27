/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.sessionbeans;

import com.unicauca.gymadmdoc.entities.MrecInformacionRecaudo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ricardo
 */
@Stateless
public class MrecInformacionRecaudoFacade extends AbstractFacade<MrecInformacionRecaudo> {

   @PersistenceContext(unitName = "Gym_Adm_DocPU")
   private EntityManager em;

   @Override
   protected EntityManager getEntityManager() {
      return em;
   }

   public MrecInformacionRecaudoFacade() {
      super(MrecInformacionRecaudo.class);
   }
   
}
