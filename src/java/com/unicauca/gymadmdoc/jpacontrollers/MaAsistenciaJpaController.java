/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.jpacontrollers;

import com.unicauca.gymadmdoc.entities.MaAsistencia;
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
public class MaAsistenciaJpaController implements Serializable {

   public MaAsistenciaJpaController(UserTransaction utx, EntityManagerFactory emf) {
      this.utx = utx;
      this.emf = emf;
   }
   private UserTransaction utx = null;
   private EntityManagerFactory emf = null;

   public EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public void create(MaAsistencia maAsistencia) throws PreexistingEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         em.persist(maAsistencia);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         if (findMaAsistencia(maAsistencia.getAsiid()) != null) {
            throw new PreexistingEntityException("MaAsistencia " + maAsistencia + " already exists.", ex);
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void edit(MaAsistencia maAsistencia) throws NonexistentEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         maAsistencia = em.merge(maAsistencia);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         String msg = ex.getLocalizedMessage();
         if (msg == null || msg.length() == 0) {
            Long id = maAsistencia.getAsiid();
            if (findMaAsistencia(id) == null) {
               throw new NonexistentEntityException("The maAsistencia with id " + id + " no longer exists.");
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
         MaAsistencia maAsistencia;
         try {
            maAsistencia = em.getReference(MaAsistencia.class, id);
            maAsistencia.getAsiid();
         } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The maAsistencia with id " + id + " no longer exists.", enfe);
         }
         em.remove(maAsistencia);
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

   public List<MaAsistencia> findMaAsistenciaEntities() {
      return findMaAsistenciaEntities(true, -1, -1);
   }

   public List<MaAsistencia> findMaAsistenciaEntities(int maxResults, int firstResult) {
      return findMaAsistenciaEntities(false, maxResults, firstResult);
   }

   private List<MaAsistencia> findMaAsistenciaEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(MaAsistencia.class));
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

   public MaAsistencia findMaAsistencia(Long id) {
      EntityManager em = getEntityManager();
      try {
         return em.find(MaAsistencia.class, id);
      } finally {
         em.close();
      }
   }

   public int getMaAsistenciaCount() {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root<MaAsistencia> rt = cq.from(MaAsistencia.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
      } finally {
         em.close();
      }
   }
   
}
