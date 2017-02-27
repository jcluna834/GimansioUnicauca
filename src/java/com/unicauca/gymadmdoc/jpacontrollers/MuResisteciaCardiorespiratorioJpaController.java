/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.jpacontrollers;

import com.unicauca.gymadmdoc.entities.MuResisteciaCardiorespiratorio;
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
public class MuResisteciaCardiorespiratorioJpaController implements Serializable {

   public MuResisteciaCardiorespiratorioJpaController(UserTransaction utx, EntityManagerFactory emf) {
      this.utx = utx;
      this.emf = emf;
   }
   private UserTransaction utx = null;
   private EntityManagerFactory emf = null;

   public EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public void create(MuResisteciaCardiorespiratorio muResisteciaCardiorespiratorio) throws PreexistingEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         em.persist(muResisteciaCardiorespiratorio);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         if (findMuResisteciaCardiorespiratorio(muResisteciaCardiorespiratorio.getRescaId()) != null) {
            throw new PreexistingEntityException("MuResisteciaCardiorespiratorio " + muResisteciaCardiorespiratorio + " already exists.", ex);
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void edit(MuResisteciaCardiorespiratorio muResisteciaCardiorespiratorio) throws NonexistentEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         muResisteciaCardiorespiratorio = em.merge(muResisteciaCardiorespiratorio);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         String msg = ex.getLocalizedMessage();
         if (msg == null || msg.length() == 0) {
            Integer id = muResisteciaCardiorespiratorio.getRescaId();
            if (findMuResisteciaCardiorespiratorio(id) == null) {
               throw new NonexistentEntityException("The muResisteciaCardiorespiratorio with id " + id + " no longer exists.");
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
         MuResisteciaCardiorespiratorio muResisteciaCardiorespiratorio;
         try {
            muResisteciaCardiorespiratorio = em.getReference(MuResisteciaCardiorespiratorio.class, id);
            muResisteciaCardiorespiratorio.getRescaId();
         } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The muResisteciaCardiorespiratorio with id " + id + " no longer exists.", enfe);
         }
         em.remove(muResisteciaCardiorespiratorio);
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

   public List<MuResisteciaCardiorespiratorio> findMuResisteciaCardiorespiratorioEntities() {
      return findMuResisteciaCardiorespiratorioEntities(true, -1, -1);
   }

   public List<MuResisteciaCardiorespiratorio> findMuResisteciaCardiorespiratorioEntities(int maxResults, int firstResult) {
      return findMuResisteciaCardiorespiratorioEntities(false, maxResults, firstResult);
   }

   private List<MuResisteciaCardiorespiratorio> findMuResisteciaCardiorespiratorioEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(MuResisteciaCardiorespiratorio.class));
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

   public MuResisteciaCardiorespiratorio findMuResisteciaCardiorespiratorio(Integer id) {
      EntityManager em = getEntityManager();
      try {
         return em.find(MuResisteciaCardiorespiratorio.class, id);
      } finally {
         em.close();
      }
   }

   public int getMuResisteciaCardiorespiratorioCount() {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root<MuResisteciaCardiorespiratorio> rt = cq.from(MuResisteciaCardiorespiratorio.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
      } finally {
         em.close();
      }
   }
   
}
