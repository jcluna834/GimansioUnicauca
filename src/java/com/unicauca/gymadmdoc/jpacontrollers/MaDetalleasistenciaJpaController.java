/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.jpacontrollers;

import com.unicauca.gymadmdoc.entities.MaDetalleasistencia;
import com.unicauca.gymadmdoc.entities.MaDetalleasistenciaPK;
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
public class MaDetalleasistenciaJpaController implements Serializable {

   public MaDetalleasistenciaJpaController(UserTransaction utx, EntityManagerFactory emf) {
      this.utx = utx;
      this.emf = emf;
   }
   private UserTransaction utx = null;
   private EntityManagerFactory emf = null;

   public EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public void create(MaDetalleasistencia maDetalleasistencia) throws PreexistingEntityException, RollbackFailureException, Exception {
      if (maDetalleasistencia.getMaDetalleasistenciaPK() == null) {
         maDetalleasistencia.setMaDetalleasistenciaPK(new MaDetalleasistenciaPK());
      }
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         em.persist(maDetalleasistencia);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         if (findMaDetalleasistencia(maDetalleasistencia.getMaDetalleasistenciaPK()) != null) {
            throw new PreexistingEntityException("MaDetalleasistencia " + maDetalleasistencia + " already exists.", ex);
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void edit(MaDetalleasistencia maDetalleasistencia) throws NonexistentEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         maDetalleasistencia = em.merge(maDetalleasistencia);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         String msg = ex.getLocalizedMessage();
         if (msg == null || msg.length() == 0) {
            MaDetalleasistenciaPK id = maDetalleasistencia.getMaDetalleasistenciaPK();
            if (findMaDetalleasistencia(id) == null) {
               throw new NonexistentEntityException("The maDetalleasistencia with id " + id + " no longer exists.");
            }
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void destroy(MaDetalleasistenciaPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         MaDetalleasistencia maDetalleasistencia;
         try {
            maDetalleasistencia = em.getReference(MaDetalleasistencia.class, id);
            maDetalleasistencia.getMaDetalleasistenciaPK();
         } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The maDetalleasistencia with id " + id + " no longer exists.", enfe);
         }
         em.remove(maDetalleasistencia);
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

   public List<MaDetalleasistencia> findMaDetalleasistenciaEntities() {
      return findMaDetalleasistenciaEntities(true, -1, -1);
   }

   public List<MaDetalleasistencia> findMaDetalleasistenciaEntities(int maxResults, int firstResult) {
      return findMaDetalleasistenciaEntities(false, maxResults, firstResult);
   }

   private List<MaDetalleasistencia> findMaDetalleasistenciaEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(MaDetalleasistencia.class));
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

   public MaDetalleasistencia findMaDetalleasistencia(MaDetalleasistenciaPK id) {
      EntityManager em = getEntityManager();
      try {
         return em.find(MaDetalleasistencia.class, id);
      } finally {
         em.close();
      }
   }

   public int getMaDetalleasistenciaCount() {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root<MaDetalleasistencia> rt = cq.from(MaDetalleasistencia.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
      } finally {
         em.close();
      }
   }
   
}
