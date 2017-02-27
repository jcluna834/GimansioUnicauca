/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gymadmdoc.jpacontrollers;

import com.unicauca.gymadmdoc.entities.MuUsuariogrupo;
import com.unicauca.gymadmdoc.entities.MuUsuariogrupoPK;
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
public class MuUsuariogrupoJpaController implements Serializable {

   public MuUsuariogrupoJpaController(UserTransaction utx, EntityManagerFactory emf) {
      this.utx = utx;
      this.emf = emf;
   }
   private UserTransaction utx = null;
   private EntityManagerFactory emf = null;

   public EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public void create(MuUsuariogrupo muUsuariogrupo) throws PreexistingEntityException, RollbackFailureException, Exception {
      if (muUsuariogrupo.getMuUsuariogrupoPK() == null) {
         muUsuariogrupo.setMuUsuariogrupoPK(new MuUsuariogrupoPK());
      }
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         em.persist(muUsuariogrupo);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         if (findMuUsuariogrupo(muUsuariogrupo.getMuUsuariogrupoPK()) != null) {
            throw new PreexistingEntityException("MuUsuariogrupo " + muUsuariogrupo + " already exists.", ex);
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void edit(MuUsuariogrupo muUsuariogrupo) throws NonexistentEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         muUsuariogrupo = em.merge(muUsuariogrupo);
         utx.commit();
      } catch (Exception ex) {
         try {
            utx.rollback();
         } catch (Exception re) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
         }
         String msg = ex.getLocalizedMessage();
         if (msg == null || msg.length() == 0) {
            MuUsuariogrupoPK id = muUsuariogrupo.getMuUsuariogrupoPK();
            if (findMuUsuariogrupo(id) == null) {
               throw new NonexistentEntityException("The muUsuariogrupo with id " + id + " no longer exists.");
            }
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void destroy(MuUsuariogrupoPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
      EntityManager em = null;
      try {
         utx.begin();
         em = getEntityManager();
         MuUsuariogrupo muUsuariogrupo;
         try {
            muUsuariogrupo = em.getReference(MuUsuariogrupo.class, id);
            muUsuariogrupo.getMuUsuariogrupoPK();
         } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The muUsuariogrupo with id " + id + " no longer exists.", enfe);
         }
         em.remove(muUsuariogrupo);
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

   public List<MuUsuariogrupo> findMuUsuariogrupoEntities() {
      return findMuUsuariogrupoEntities(true, -1, -1);
   }

   public List<MuUsuariogrupo> findMuUsuariogrupoEntities(int maxResults, int firstResult) {
      return findMuUsuariogrupoEntities(false, maxResults, firstResult);
   }

   private List<MuUsuariogrupo> findMuUsuariogrupoEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(MuUsuariogrupo.class));
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

   public MuUsuariogrupo findMuUsuariogrupo(MuUsuariogrupoPK id) {
      EntityManager em = getEntityManager();
      try {
         return em.find(MuUsuariogrupo.class, id);
      } finally {
         em.close();
      }
   }

   public int getMuUsuariogrupoCount() {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root<MuUsuariogrupo> rt = cq.from(MuUsuariogrupo.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
      } finally {
         em.close();
      }
   }
   
}
