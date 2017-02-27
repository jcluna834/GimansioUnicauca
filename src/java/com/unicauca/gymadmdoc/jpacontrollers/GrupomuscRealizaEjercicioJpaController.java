/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.jpacontrollers;

import com.unicauca.gymadmdoc.entities.GrupomuscRealizaEjercicio;
import com.unicauca.gymadmdoc.entities.GrupomuscRealizaEjercicioPK;
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
public class GrupomuscRealizaEjercicioJpaController implements Serializable {

   public GrupomuscRealizaEjercicioJpaController(UserTransaction utx, EntityManagerFactory emf) {
      this.utx = utx;
      this.emf = emf;
   }
   private UserTransaction utx = null;
   private EntityManagerFactory emf = null;

   public EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public void create(GrupomuscRealizaEjercicio grupomuscRealizaEjercicio) throws PreexistingEntityException, RollbackFailureException, Exception {
      if (grupomuscRealizaEjercicio.getGrupomuscRealizaEjercicioPK() == null) {
         grupomuscRealizaEjercicio.setGrupomuscRealizaEjercicioPK(new GrupomuscRealizaEjercicioPK());
      }
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         em.persist(grupomuscRealizaEjercicio);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         if (findGrupomuscRealizaEjercicio(grupomuscRealizaEjercicio.getGrupomuscRealizaEjercicioPK()) != null) {
            throw new PreexistingEntityException("GrupomuscRealizaEjercicio " + grupomuscRealizaEjercicio + " already exists.", ex);
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void edit(GrupomuscRealizaEjercicio grupomuscRealizaEjercicio) throws NonexistentEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         grupomuscRealizaEjercicio = em.merge(grupomuscRealizaEjercicio);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         String msg = ex.getLocalizedMessage();
         if (msg == null || msg.length() == 0) {
            GrupomuscRealizaEjercicioPK id = grupomuscRealizaEjercicio.getGrupomuscRealizaEjercicioPK();
            if (findGrupomuscRealizaEjercicio(id) == null) {
               throw new NonexistentEntityException("The grupomuscRealizaEjercicio with id " + id + " no longer exists.");
            }
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void destroy(GrupomuscRealizaEjercicioPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         GrupomuscRealizaEjercicio grupomuscRealizaEjercicio;
         try {
            grupomuscRealizaEjercicio = em.getReference(GrupomuscRealizaEjercicio.class, id);
            grupomuscRealizaEjercicio.getGrupomuscRealizaEjercicioPK();
         } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The grupomuscRealizaEjercicio with id " + id + " no longer exists.", enfe);
         }
         em.remove(grupomuscRealizaEjercicio);
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

   public List<GrupomuscRealizaEjercicio> findGrupomuscRealizaEjercicioEntities() {
      return findGrupomuscRealizaEjercicioEntities(true, -1, -1);
   }

   public List<GrupomuscRealizaEjercicio> findGrupomuscRealizaEjercicioEntities(int maxResults, int firstResult) {
      return findGrupomuscRealizaEjercicioEntities(false, maxResults, firstResult);
   }

   private List<GrupomuscRealizaEjercicio> findGrupomuscRealizaEjercicioEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(GrupomuscRealizaEjercicio.class));
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

   public GrupomuscRealizaEjercicio findGrupomuscRealizaEjercicio(GrupomuscRealizaEjercicioPK id) {
      EntityManager em = getEntityManager();
      try {
         return em.find(GrupomuscRealizaEjercicio.class, id);
      } finally {
         em.close();
      }
   }

   public int getGrupomuscRealizaEjercicioCount() {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root<GrupomuscRealizaEjercicio> rt = cq.from(GrupomuscRealizaEjercicio.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
      } finally {
         em.close();
      }
   }
   
}
