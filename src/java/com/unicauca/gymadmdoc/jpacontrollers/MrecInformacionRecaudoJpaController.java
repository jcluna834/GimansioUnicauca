/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.jpacontrollers;

import com.unicauca.gymadmdoc.entities.MrecInformacionRecaudo;
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
public class MrecInformacionRecaudoJpaController implements Serializable {

   public MrecInformacionRecaudoJpaController(UserTransaction utx, EntityManagerFactory emf) {
      this.utx = utx;
      this.emf = emf;
   }
   private UserTransaction utx = null;
   private EntityManagerFactory emf = null;

   public EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public void create(MrecInformacionRecaudo mrecInformacionRecaudo) throws RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         em.persist(mrecInformacionRecaudo);
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

   public void edit(MrecInformacionRecaudo mrecInformacionRecaudo) throws NonexistentEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         mrecInformacionRecaudo = em.merge(mrecInformacionRecaudo);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         String msg = ex.getLocalizedMessage();
         if (msg == null || msg.length() == 0) {
            Long id = mrecInformacionRecaudo.getIrecId();
            if (findMrecInformacionRecaudo(id) == null) {
               throw new NonexistentEntityException("The mrecInformacionRecaudo with id " + id + " no longer exists.");
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
         MrecInformacionRecaudo mrecInformacionRecaudo;
         try {
            mrecInformacionRecaudo = em.getReference(MrecInformacionRecaudo.class, id);
            mrecInformacionRecaudo.getIrecId();
         } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The mrecInformacionRecaudo with id " + id + " no longer exists.", enfe);
         }
         em.remove(mrecInformacionRecaudo);
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

   public List<MrecInformacionRecaudo> findMrecInformacionRecaudoEntities() {
      return findMrecInformacionRecaudoEntities(true, -1, -1);
   }

   public List<MrecInformacionRecaudo> findMrecInformacionRecaudoEntities(int maxResults, int firstResult) {
      return findMrecInformacionRecaudoEntities(false, maxResults, firstResult);
   }

   private List<MrecInformacionRecaudo> findMrecInformacionRecaudoEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(MrecInformacionRecaudo.class));
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

   public MrecInformacionRecaudo findMrecInformacionRecaudo(Long id) {
      EntityManager em = getEntityManager();
      try {
         return em.find(MrecInformacionRecaudo.class, id);
      } finally {
         em.close();
      }
   }

   public int getMrecInformacionRecaudoCount() {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root<MrecInformacionRecaudo> rt = cq.from(MrecInformacionRecaudo.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
      } finally {
         em.close();
      }
   }
   
}
