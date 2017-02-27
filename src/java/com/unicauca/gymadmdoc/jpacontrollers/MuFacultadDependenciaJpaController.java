/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.jpacontrollers;

import com.unicauca.gymadmdoc.entities.MuFacultadDependencia;
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
public class MuFacultadDependenciaJpaController implements Serializable {

   public MuFacultadDependenciaJpaController(UserTransaction utx, EntityManagerFactory emf) {
      this.utx = utx;
      this.emf = emf;
   }
   private UserTransaction utx = null;
   private EntityManagerFactory emf = null;

   public EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public void create(MuFacultadDependencia muFacultadDependencia) throws PreexistingEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         em.persist(muFacultadDependencia);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         if (findMuFacultadDependencia(muFacultadDependencia.getFacDepId()) != null) {
            throw new PreexistingEntityException("MuFacultadDependencia " + muFacultadDependencia + " already exists.", ex);
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void edit(MuFacultadDependencia muFacultadDependencia) throws NonexistentEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         muFacultadDependencia = em.merge(muFacultadDependencia);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         String msg = ex.getLocalizedMessage();
         if (msg == null || msg.length() == 0) {
            Integer id = muFacultadDependencia.getFacDepId();
            if (findMuFacultadDependencia(id) == null) {
               throw new NonexistentEntityException("The muFacultadDependencia with id " + id + " no longer exists.");
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
         MuFacultadDependencia muFacultadDependencia;
         try {
            muFacultadDependencia = em.getReference(MuFacultadDependencia.class, id);
            muFacultadDependencia.getFacDepId();
         } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The muFacultadDependencia with id " + id + " no longer exists.", enfe);
         }
         em.remove(muFacultadDependencia);
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

   public List<MuFacultadDependencia> findMuFacultadDependenciaEntities() {
      return findMuFacultadDependenciaEntities(true, -1, -1);
   }

   public List<MuFacultadDependencia> findMuFacultadDependenciaEntities(int maxResults, int firstResult) {
      return findMuFacultadDependenciaEntities(false, maxResults, firstResult);
   }

   private List<MuFacultadDependencia> findMuFacultadDependenciaEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(MuFacultadDependencia.class));
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

   public MuFacultadDependencia findMuFacultadDependencia(Integer id) {
      EntityManager em = getEntityManager();
      try {
         return em.find(MuFacultadDependencia.class, id);
      } finally {
         em.close();
      }
   }

   public int getMuFacultadDependenciaCount() {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root<MuFacultadDependencia> rt = cq.from(MuFacultadDependencia.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
      } finally {
         em.close();
      }
   }
   
}
