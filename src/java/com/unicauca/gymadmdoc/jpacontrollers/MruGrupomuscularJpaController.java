/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.jpacontrollers;

import com.unicauca.gymadmdoc.entities.MruGrupomuscular;
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
public class MruGrupomuscularJpaController implements Serializable {

   public MruGrupomuscularJpaController(UserTransaction utx, EntityManagerFactory emf) {
      this.utx = utx;
      this.emf = emf;
   }
   private UserTransaction utx = null;
   private EntityManagerFactory emf = null;

   public EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public void create(MruGrupomuscular mruGrupomuscular) throws PreexistingEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         em.persist(mruGrupomuscular);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         if (findMruGrupomuscular(mruGrupomuscular.getGmId()) != null) {
            throw new PreexistingEntityException("MruGrupomuscular " + mruGrupomuscular + " already exists.", ex);
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void edit(MruGrupomuscular mruGrupomuscular) throws NonexistentEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         mruGrupomuscular = em.merge(mruGrupomuscular);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         String msg = ex.getLocalizedMessage();
         if (msg == null || msg.length() == 0) {
            Integer id = mruGrupomuscular.getGmId();
            if (findMruGrupomuscular(id) == null) {
               throw new NonexistentEntityException("The mruGrupomuscular with id " + id + " no longer exists.");
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
         MruGrupomuscular mruGrupomuscular;
         try {
            mruGrupomuscular = em.getReference(MruGrupomuscular.class, id);
            mruGrupomuscular.getGmId();
         } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The mruGrupomuscular with id " + id + " no longer exists.", enfe);
         }
         em.remove(mruGrupomuscular);
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

   public List<MruGrupomuscular> findMruGrupomuscularEntities() {
      return findMruGrupomuscularEntities(true, -1, -1);
   }

   public List<MruGrupomuscular> findMruGrupomuscularEntities(int maxResults, int firstResult) {
      return findMruGrupomuscularEntities(false, maxResults, firstResult);
   }

   private List<MruGrupomuscular> findMruGrupomuscularEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(MruGrupomuscular.class));
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

   public MruGrupomuscular findMruGrupomuscular(Integer id) {
      EntityManager em = getEntityManager();
      try {
         return em.find(MruGrupomuscular.class, id);
      } finally {
         em.close();
      }
   }

   public int getMruGrupomuscularCount() {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root<MruGrupomuscular> rt = cq.from(MruGrupomuscular.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
      } finally {
         em.close();
      }
   }
   
}
