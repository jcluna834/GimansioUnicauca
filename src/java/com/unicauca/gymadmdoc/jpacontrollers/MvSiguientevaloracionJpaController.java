/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.jpacontrollers;

import com.unicauca.gymadmdoc.entities.MvSiguientevaloracion;
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
public class MvSiguientevaloracionJpaController implements Serializable {

   public MvSiguientevaloracionJpaController(UserTransaction utx, EntityManagerFactory emf) {
      this.utx = utx;
      this.emf = emf;
   }
   private UserTransaction utx = null;
   private EntityManagerFactory emf = null;

   public EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public void create(MvSiguientevaloracion mvSiguientevaloracion) throws RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         em.persist(mvSiguientevaloracion);
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

   public void edit(MvSiguientevaloracion mvSiguientevaloracion) throws NonexistentEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         mvSiguientevaloracion = em.merge(mvSiguientevaloracion);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         String msg = ex.getLocalizedMessage();
         if (msg == null || msg.length() == 0) {
            Integer id = mvSiguientevaloracion.getSigId();
            if (findMvSiguientevaloracion(id) == null) {
               throw new NonexistentEntityException("The mvSiguientevaloracion with id " + id + " no longer exists.");
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
         MvSiguientevaloracion mvSiguientevaloracion;
         try {
            mvSiguientevaloracion = em.getReference(MvSiguientevaloracion.class, id);
            mvSiguientevaloracion.getSigId();
         } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The mvSiguientevaloracion with id " + id + " no longer exists.", enfe);
         }
         em.remove(mvSiguientevaloracion);
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

   public List<MvSiguientevaloracion> findMvSiguientevaloracionEntities() {
      return findMvSiguientevaloracionEntities(true, -1, -1);
   }

   public List<MvSiguientevaloracion> findMvSiguientevaloracionEntities(int maxResults, int firstResult) {
      return findMvSiguientevaloracionEntities(false, maxResults, firstResult);
   }

   private List<MvSiguientevaloracion> findMvSiguientevaloracionEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(MvSiguientevaloracion.class));
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

   public MvSiguientevaloracion findMvSiguientevaloracion(Integer id) {
      EntityManager em = getEntityManager();
      try {
         return em.find(MvSiguientevaloracion.class, id);
      } finally {
         em.close();
      }
   }

   public int getMvSiguientevaloracionCount() {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root<MvSiguientevaloracion> rt = cq.from(MvSiguientevaloracion.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
      } finally {
         em.close();
      }
   }
   
}
