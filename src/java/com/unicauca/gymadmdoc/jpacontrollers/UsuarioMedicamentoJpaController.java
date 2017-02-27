/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.jpacontrollers;

import com.unicauca.gymadmdoc.entities.UsuarioMedicamento;
import com.unicauca.gymadmdoc.entities.UsuarioMedicamentoPK;
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
public class UsuarioMedicamentoJpaController implements Serializable {

   public UsuarioMedicamentoJpaController(UserTransaction utx, EntityManagerFactory emf) {
      this.utx = utx;
      this.emf = emf;
   }
   private UserTransaction utx = null;
   private EntityManagerFactory emf = null;

   public EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public void create(UsuarioMedicamento usuarioMedicamento) throws PreexistingEntityException, RollbackFailureException, Exception {
      if (usuarioMedicamento.getUsuarioMedicamentoPK() == null) {
         usuarioMedicamento.setUsuarioMedicamentoPK(new UsuarioMedicamentoPK());
      }
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         em.persist(usuarioMedicamento);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         if (findUsuarioMedicamento(usuarioMedicamento.getUsuarioMedicamentoPK()) != null) {
            throw new PreexistingEntityException("UsuarioMedicamento " + usuarioMedicamento + " already exists.", ex);
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void edit(UsuarioMedicamento usuarioMedicamento) throws NonexistentEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         usuarioMedicamento = em.merge(usuarioMedicamento);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         String msg = ex.getLocalizedMessage();
         if (msg == null || msg.length() == 0) {
            UsuarioMedicamentoPK id = usuarioMedicamento.getUsuarioMedicamentoPK();
            if (findUsuarioMedicamento(id) == null) {
               throw new NonexistentEntityException("The usuarioMedicamento with id " + id + " no longer exists.");
            }
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void destroy(UsuarioMedicamentoPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         UsuarioMedicamento usuarioMedicamento;
         try {
            usuarioMedicamento = em.getReference(UsuarioMedicamento.class, id);
            usuarioMedicamento.getUsuarioMedicamentoPK();
         } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The usuarioMedicamento with id " + id + " no longer exists.", enfe);
         }
         em.remove(usuarioMedicamento);
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

   public List<UsuarioMedicamento> findUsuarioMedicamentoEntities() {
      return findUsuarioMedicamentoEntities(true, -1, -1);
   }

   public List<UsuarioMedicamento> findUsuarioMedicamentoEntities(int maxResults, int firstResult) {
      return findUsuarioMedicamentoEntities(false, maxResults, firstResult);
   }

   private List<UsuarioMedicamento> findUsuarioMedicamentoEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(UsuarioMedicamento.class));
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

   public UsuarioMedicamento findUsuarioMedicamento(UsuarioMedicamentoPK id) {
      EntityManager em = getEntityManager();
      try {
         return em.find(UsuarioMedicamento.class, id);
      } finally {
         em.close();
      }
   }

   public int getUsuarioMedicamentoCount() {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root<UsuarioMedicamento> rt = cq.from(UsuarioMedicamento.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
      } finally {
         em.close();
      }
   }
   
}
