/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.jpacontrollers;

import com.unicauca.gymadmdoc.entities.MuFuerzaAbdominal;
import com.unicauca.gymadmdoc.jpacontrollers.exceptions.NonexistentEntityException;
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
public class MuFuerzaAbdominalJpaController implements Serializable {

   public MuFuerzaAbdominalJpaController(UserTransaction utx, EntityManagerFactory emf) {
      this.utx = utx;
      this.emf = emf;
   }
   private UserTransaction utx = null;
   private EntityManagerFactory emf = null;

   public EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public void create(MuFuerzaAbdominal muFuerzaAbdominal) throws RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         em.persist(muFuerzaAbdominal);
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

   public void edit(MuFuerzaAbdominal muFuerzaAbdominal) throws NonexistentEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         muFuerzaAbdominal = em.merge(muFuerzaAbdominal);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         String msg = ex.getLocalizedMessage();
         if (msg == null || msg.length() == 0) {
            Integer id = muFuerzaAbdominal.getFuabId();
            if (findMuFuerzaAbdominal(id) == null) {
               throw new NonexistentEntityException("The muFuerzaAbdominal with id " + id + " no longer exists.");
            }
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         MuFuerzaAbdominal muFuerzaAbdominal;
         try {
            muFuerzaAbdominal = em.getReference(MuFuerzaAbdominal.class, id);
            muFuerzaAbdominal.getFuabId();
         } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The muFuerzaAbdominal with id " + id + " no longer exists.", enfe);
         }
         em.remove(muFuerzaAbdominal);
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

   public List<MuFuerzaAbdominal> findMuFuerzaAbdominalEntities() {
      return findMuFuerzaAbdominalEntities(true, -1, -1);
   }

   public List<MuFuerzaAbdominal> findMuFuerzaAbdominalEntities(int maxResults, int firstResult) {
      return findMuFuerzaAbdominalEntities(false, maxResults, firstResult);
   }

   private List<MuFuerzaAbdominal> findMuFuerzaAbdominalEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(MuFuerzaAbdominal.class));
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

   public MuFuerzaAbdominal findMuFuerzaAbdominal(Integer id) {
      EntityManager em = getEntityManager();
      try {
         return em.find(MuFuerzaAbdominal.class, id);
      } finally {
         em.close();
      }
   }

   public int getMuFuerzaAbdominalCount() {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root<MuFuerzaAbdominal> rt = cq.from(MuFuerzaAbdominal.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
      } finally {
         em.close();
      }
   }
   
}
