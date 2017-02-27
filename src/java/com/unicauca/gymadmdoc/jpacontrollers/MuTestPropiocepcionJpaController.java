/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.jpacontrollers;

import com.unicauca.gymadmdoc.entities.MuTestPropiocepcion;
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
public class MuTestPropiocepcionJpaController implements Serializable {

   public MuTestPropiocepcionJpaController(UserTransaction utx, EntityManagerFactory emf) {
      this.utx = utx;
      this.emf = emf;
   }
   private UserTransaction utx = null;
   private EntityManagerFactory emf = null;

   public EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public void create(MuTestPropiocepcion muTestPropiocepcion) throws RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         em.persist(muTestPropiocepcion);
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

   public void edit(MuTestPropiocepcion muTestPropiocepcion) throws NonexistentEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         muTestPropiocepcion = em.merge(muTestPropiocepcion);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         String msg = ex.getLocalizedMessage();
         if (msg == null || msg.length() == 0) {
            Integer id = muTestPropiocepcion.getTesproId();
            if (findMuTestPropiocepcion(id) == null) {
               throw new NonexistentEntityException("The muTestPropiocepcion with id " + id + " no longer exists.");
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
         MuTestPropiocepcion muTestPropiocepcion;
         try {
            muTestPropiocepcion = em.getReference(MuTestPropiocepcion.class, id);
            muTestPropiocepcion.getTesproId();
         } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The muTestPropiocepcion with id " + id + " no longer exists.", enfe);
         }
         em.remove(muTestPropiocepcion);
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

   public List<MuTestPropiocepcion> findMuTestPropiocepcionEntities() {
      return findMuTestPropiocepcionEntities(true, -1, -1);
   }

   public List<MuTestPropiocepcion> findMuTestPropiocepcionEntities(int maxResults, int firstResult) {
      return findMuTestPropiocepcionEntities(false, maxResults, firstResult);
   }

   private List<MuTestPropiocepcion> findMuTestPropiocepcionEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(MuTestPropiocepcion.class));
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

   public MuTestPropiocepcion findMuTestPropiocepcion(Integer id) {
      EntityManager em = getEntityManager();
      try {
         return em.find(MuTestPropiocepcion.class, id);
      } finally {
         em.close();
      }
   }

   public int getMuTestPropiocepcionCount() {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root<MuTestPropiocepcion> rt = cq.from(MuTestPropiocepcion.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
      } finally {
         em.close();
      }
   }
   
}
