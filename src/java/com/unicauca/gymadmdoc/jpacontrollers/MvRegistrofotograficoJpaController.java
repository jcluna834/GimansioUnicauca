/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.jpacontrollers;

import com.unicauca.gymadmdoc.entities.MvRegistrofotografico;
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
public class MvRegistrofotograficoJpaController implements Serializable {

   public MvRegistrofotograficoJpaController(UserTransaction utx, EntityManagerFactory emf) {
      this.utx = utx;
      this.emf = emf;
   }
   private UserTransaction utx = null;
   private EntityManagerFactory emf = null;

   public EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public void create(MvRegistrofotografico mvRegistrofotografico) throws RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         em.persist(mvRegistrofotografico);
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

   public void edit(MvRegistrofotografico mvRegistrofotografico) throws NonexistentEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         mvRegistrofotografico = em.merge(mvRegistrofotografico);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         String msg = ex.getLocalizedMessage();
         if (msg == null || msg.length() == 0) {
            Integer id = mvRegistrofotografico.getImgId();
            if (findMvRegistrofotografico(id) == null) {
               throw new NonexistentEntityException("The mvRegistrofotografico with id " + id + " no longer exists.");
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
         MvRegistrofotografico mvRegistrofotografico;
         try {
            mvRegistrofotografico = em.getReference(MvRegistrofotografico.class, id);
            mvRegistrofotografico.getImgId();
         } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The mvRegistrofotografico with id " + id + " no longer exists.", enfe);
         }
         em.remove(mvRegistrofotografico);
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

   public List<MvRegistrofotografico> findMvRegistrofotograficoEntities() {
      return findMvRegistrofotograficoEntities(true, -1, -1);
   }

   public List<MvRegistrofotografico> findMvRegistrofotograficoEntities(int maxResults, int firstResult) {
      return findMvRegistrofotograficoEntities(false, maxResults, firstResult);
   }

   private List<MvRegistrofotografico> findMvRegistrofotograficoEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(MvRegistrofotografico.class));
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

   public MvRegistrofotografico findMvRegistrofotografico(Integer id) {
      EntityManager em = getEntityManager();
      try {
         return em.find(MvRegistrofotografico.class, id);
      } finally {
         em.close();
      }
   }

   public int getMvRegistrofotograficoCount() {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root<MvRegistrofotografico> rt = cq.from(MvRegistrofotografico.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
      } finally {
         em.close();
      }
   }
   
}
