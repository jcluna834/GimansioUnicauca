/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.jpacontrollers;

import com.unicauca.gymadmdoc.entities.MruVariaciondepeso;
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
public class MruVariaciondepesoJpaController implements Serializable {

   public MruVariaciondepesoJpaController(UserTransaction utx, EntityManagerFactory emf) {
      this.utx = utx;
      this.emf = emf;
   }
   private UserTransaction utx = null;
   private EntityManagerFactory emf = null;

   public EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public void create(MruVariaciondepeso mruVariaciondepeso) throws PreexistingEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         em.persist(mruVariaciondepeso);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         if (findMruVariaciondepeso(mruVariaciondepeso.getVpId()) != null) {
            throw new PreexistingEntityException("MruVariaciondepeso " + mruVariaciondepeso + " already exists.", ex);
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void edit(MruVariaciondepeso mruVariaciondepeso) throws NonexistentEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         mruVariaciondepeso = em.merge(mruVariaciondepeso);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         String msg = ex.getLocalizedMessage();
         if (msg == null || msg.length() == 0) {
            Integer id = mruVariaciondepeso.getVpId();
            if (findMruVariaciondepeso(id) == null) {
               throw new NonexistentEntityException("The mruVariaciondepeso with id " + id + " no longer exists.");
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
         MruVariaciondepeso mruVariaciondepeso;
         try {
            mruVariaciondepeso = em.getReference(MruVariaciondepeso.class, id);
            mruVariaciondepeso.getVpId();
         } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The mruVariaciondepeso with id " + id + " no longer exists.", enfe);
         }
         em.remove(mruVariaciondepeso);
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

   public List<MruVariaciondepeso> findMruVariaciondepesoEntities() {
      return findMruVariaciondepesoEntities(true, -1, -1);
   }

   public List<MruVariaciondepeso> findMruVariaciondepesoEntities(int maxResults, int firstResult) {
      return findMruVariaciondepesoEntities(false, maxResults, firstResult);
   }

   private List<MruVariaciondepeso> findMruVariaciondepesoEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(MruVariaciondepeso.class));
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

   public MruVariaciondepeso findMruVariaciondepeso(Integer id) {
      EntityManager em = getEntityManager();
      try {
         return em.find(MruVariaciondepeso.class, id);
      } finally {
         em.close();
      }
   }

   public int getMruVariaciondepesoCount() {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root<MruVariaciondepeso> rt = cq.from(MruVariaciondepeso.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
      } finally {
         em.close();
      }
   }
   
}
