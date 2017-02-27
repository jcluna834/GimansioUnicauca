/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.jpacontrollers;

import com.unicauca.gymadmdoc.entities.MuFuerzaMuscular;
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
public class MuFuerzaMuscularJpaController implements Serializable {

   public MuFuerzaMuscularJpaController(UserTransaction utx, EntityManagerFactory emf) {
      this.utx = utx;
      this.emf = emf;
   }
   private UserTransaction utx = null;
   private EntityManagerFactory emf = null;

   public EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public void create(MuFuerzaMuscular muFuerzaMuscular) throws RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         em.persist(muFuerzaMuscular);
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

   public void edit(MuFuerzaMuscular muFuerzaMuscular) throws NonexistentEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         muFuerzaMuscular = em.merge(muFuerzaMuscular);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         String msg = ex.getLocalizedMessage();
         if (msg == null || msg.length() == 0) {
            Integer id = muFuerzaMuscular.getFumusId();
            if (findMuFuerzaMuscular(id) == null) {
               throw new NonexistentEntityException("The muFuerzaMuscular with id " + id + " no longer exists.");
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
         MuFuerzaMuscular muFuerzaMuscular;
         try {
            muFuerzaMuscular = em.getReference(MuFuerzaMuscular.class, id);
            muFuerzaMuscular.getFumusId();
         } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The muFuerzaMuscular with id " + id + " no longer exists.", enfe);
         }
         em.remove(muFuerzaMuscular);
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

   public List<MuFuerzaMuscular> findMuFuerzaMuscularEntities() {
      return findMuFuerzaMuscularEntities(true, -1, -1);
   }

   public List<MuFuerzaMuscular> findMuFuerzaMuscularEntities(int maxResults, int firstResult) {
      return findMuFuerzaMuscularEntities(false, maxResults, firstResult);
   }

   private List<MuFuerzaMuscular> findMuFuerzaMuscularEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(MuFuerzaMuscular.class));
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

   public MuFuerzaMuscular findMuFuerzaMuscular(Integer id) {
      EntityManager em = getEntityManager();
      try {
         return em.find(MuFuerzaMuscular.class, id);
      } finally {
         em.close();
      }
   }

   public int getMuFuerzaMuscularCount() {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root<MuFuerzaMuscular> rt = cq.from(MuFuerzaMuscular.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
      } finally {
         em.close();
      }
   }
   
}
