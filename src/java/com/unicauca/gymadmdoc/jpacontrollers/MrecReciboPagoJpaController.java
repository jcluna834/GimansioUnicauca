/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.jpacontrollers;

import com.unicauca.gymadmdoc.entities.MrecReciboPago;
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
public class MrecReciboPagoJpaController implements Serializable {

   public MrecReciboPagoJpaController(UserTransaction utx, EntityManagerFactory emf) {
      this.utx = utx;
      this.emf = emf;
   }
   private UserTransaction utx = null;
   private EntityManagerFactory emf = null;

   public EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public void create(MrecReciboPago mrecReciboPago) throws PreexistingEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         em.persist(mrecReciboPago);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         if (findMrecReciboPago(mrecReciboPago.getRpagReferencia()) != null) {
            throw new PreexistingEntityException("MrecReciboPago " + mrecReciboPago + " already exists.", ex);
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void edit(MrecReciboPago mrecReciboPago) throws NonexistentEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         mrecReciboPago = em.merge(mrecReciboPago);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         String msg = ex.getLocalizedMessage();
         if (msg == null || msg.length() == 0) {
            Long id = mrecReciboPago.getRpagReferencia();
            if (findMrecReciboPago(id) == null) {
               throw new NonexistentEntityException("The mrecReciboPago with id " + id + " no longer exists.");
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
         MrecReciboPago mrecReciboPago;
         try {
            mrecReciboPago = em.getReference(MrecReciboPago.class, id);
            mrecReciboPago.getRpagReferencia();
         } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The mrecReciboPago with id " + id + " no longer exists.", enfe);
         }
         em.remove(mrecReciboPago);
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

   public List<MrecReciboPago> findMrecReciboPagoEntities() {
      return findMrecReciboPagoEntities(true, -1, -1);
   }

   public List<MrecReciboPago> findMrecReciboPagoEntities(int maxResults, int firstResult) {
      return findMrecReciboPagoEntities(false, maxResults, firstResult);
   }

   private List<MrecReciboPago> findMrecReciboPagoEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(MrecReciboPago.class));
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

   public MrecReciboPago findMrecReciboPago(Long id) {
      EntityManager em = getEntityManager();
      try {
         return em.find(MrecReciboPago.class, id);
      } finally {
         em.close();
      }
   }

   public int getMrecReciboPagoCount() {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root<MrecReciboPago> rt = cq.from(MrecReciboPago.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
      } finally {
         em.close();
      }
   }
   
}
