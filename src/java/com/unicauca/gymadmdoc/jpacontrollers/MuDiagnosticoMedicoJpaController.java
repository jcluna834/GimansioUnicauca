/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.jpacontrollers;

import com.unicauca.gymadmdoc.entities.MuDiagnosticoMedico;
import com.unicauca.gymadmdoc.jpacontrollers.exceptions.NonexistentEntityException;
import com.unicauca.gymadmdoc.jpacontrollers.exceptions.PreexistingEntityException;
import com.unicauca.gymadmdoc.jpacontrollers.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author Ricardo
 */
public class MuDiagnosticoMedicoJpaController implements Serializable {

   public MuDiagnosticoMedicoJpaController(UserTransaction utx, EntityManagerFactory emf) {
      this.utx = utx;
      this.emf = emf;
   }
   private UserTransaction utx = null;
   private EntityManagerFactory emf = null;

   public EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public void create(MuDiagnosticoMedico muDiagnosticoMedico) throws PreexistingEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         em.persist(muDiagnosticoMedico);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         if (findMuDiagnosticoMedico(muDiagnosticoMedico.getDimedId()) != null) {
            throw new PreexistingEntityException("MuDiagnosticoMedico " + muDiagnosticoMedico + " already exists.", ex);
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void edit(MuDiagnosticoMedico muDiagnosticoMedico) throws NonexistentEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         muDiagnosticoMedico = em.merge(muDiagnosticoMedico);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         String msg = ex.getLocalizedMessage();
         if (msg == null || msg.length() == 0) {
            Long id = muDiagnosticoMedico.getDimedId();
            if (findMuDiagnosticoMedico(id) == null) {
               throw new NonexistentEntityException("The muDiagnosticoMedico with id " + id + " no longer exists.");
            }
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         MuDiagnosticoMedico muDiagnosticoMedico;
         try {
            muDiagnosticoMedico = em.getReference(MuDiagnosticoMedico.class, id);
            muDiagnosticoMedico.getDimedId();
         } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The muDiagnosticoMedico with id " + id + " no longer exists.", enfe);
         }
         em.remove(muDiagnosticoMedico);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public List<MuDiagnosticoMedico> findMuDiagnosticoMedicoEntities() {
      return findMuDiagnosticoMedicoEntities(true, -1, -1);
   }

   public List<MuDiagnosticoMedico> findMuDiagnosticoMedicoEntities(int maxResults, int firstResult) {
      return findMuDiagnosticoMedicoEntities(false, maxResults, firstResult);
   }

   private List<MuDiagnosticoMedico> findMuDiagnosticoMedicoEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(MuDiagnosticoMedico.class));
         Query q = em.createQuery(cq);
         if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
         }
         return q.getResultList();
      } finally {
         em.close();
      }
   }

   public MuDiagnosticoMedico findMuDiagnosticoMedico(Long id) {
      EntityManager em = getEntityManager();
      try {
         return em.find(MuDiagnosticoMedico.class, id);
      } finally {
         em.close();
      }
   }

   public int getMuDiagnosticoMedicoCount() {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root<MuDiagnosticoMedico> rt = cq.from(MuDiagnosticoMedico.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
      } finally {
         em.close();
      }
   }
   
}
