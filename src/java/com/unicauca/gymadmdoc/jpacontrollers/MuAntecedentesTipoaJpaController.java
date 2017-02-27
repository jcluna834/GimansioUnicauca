/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.jpacontrollers;

import com.unicauca.gymadmdoc.entities.MuAntecedentesTipoa;
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
public class MuAntecedentesTipoaJpaController implements Serializable {

   public MuAntecedentesTipoaJpaController(UserTransaction utx, EntityManagerFactory emf) {
      this.utx = utx;
      this.emf = emf;
   }
   private UserTransaction utx = null;
   private EntityManagerFactory emf = null;

   public EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public void create(MuAntecedentesTipoa muAntecedentesTipoa) throws RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         em.persist(muAntecedentesTipoa);
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

   public void edit(MuAntecedentesTipoa muAntecedentesTipoa) throws NonexistentEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         muAntecedentesTipoa = em.merge(muAntecedentesTipoa);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         String msg = ex.getLocalizedMessage();
         if (msg == null || msg.length() == 0) {
            Integer id = muAntecedentesTipoa.getAntaId();
            if (findMuAntecedentesTipoa(id) == null) {
               throw new NonexistentEntityException("The muAntecedentesTipoa with id " + id + " no longer exists.");
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
         MuAntecedentesTipoa muAntecedentesTipoa;
         try {
            muAntecedentesTipoa = em.getReference(MuAntecedentesTipoa.class, id);
            muAntecedentesTipoa.getAntaId();
         } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The muAntecedentesTipoa with id " + id + " no longer exists.", enfe);
         }
         em.remove(muAntecedentesTipoa);
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

   public List<MuAntecedentesTipoa> findMuAntecedentesTipoaEntities() {
      return findMuAntecedentesTipoaEntities(true, -1, -1);
   }

   public List<MuAntecedentesTipoa> findMuAntecedentesTipoaEntities(int maxResults, int firstResult) {
      return findMuAntecedentesTipoaEntities(false, maxResults, firstResult);
   }

   private List<MuAntecedentesTipoa> findMuAntecedentesTipoaEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(MuAntecedentesTipoa.class));
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

   public MuAntecedentesTipoa findMuAntecedentesTipoa(Integer id) {
      EntityManager em = getEntityManager();
      try {
         return em.find(MuAntecedentesTipoa.class, id);
      } finally {
         em.close();
      }
   }

   public int getMuAntecedentesTipoaCount() {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root<MuAntecedentesTipoa> rt = cq.from(MuAntecedentesTipoa.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
      } finally {
         em.close();
      }
   }
   
}
