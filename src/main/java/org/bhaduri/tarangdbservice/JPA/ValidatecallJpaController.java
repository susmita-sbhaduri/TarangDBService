/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.bhaduri.tarangdbservice.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.bhaduri.tarangdbservice.JPA.exceptions.NonexistentEntityException;
import org.bhaduri.tarangdbservice.JPA.exceptions.PreexistingEntityException;
import org.bhaduri.tarangdbservice.entities.Validatecall;
import org.bhaduri.tarangdbservice.entities.ValidatecallPK;

/**
 *
 * @author sb
 */
public class ValidatecallJpaController implements Serializable {

    public ValidatecallJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Validatecall validatecall) throws PreexistingEntityException, Exception {
        if (validatecall.getValidatecallPK() == null) {
            validatecall.setValidatecallPK(new ValidatecallPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(validatecall);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findValidatecall(validatecall.getValidatecallPK()) != null) {
                throw new PreexistingEntityException("Validatecall " + validatecall + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Validatecall validatecall) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            validatecall = em.merge(validatecall);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ValidatecallPK id = validatecall.getValidatecallPK();
                if (findValidatecall(id) == null) {
                    throw new NonexistentEntityException("The validatecall with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ValidatecallPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Validatecall validatecall;
            try {
                validatecall = em.getReference(Validatecall.class, id);
                validatecall.getValidatecallPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The validatecall with id " + id + " no longer exists.", enfe);
            }
            em.remove(validatecall);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Validatecall> findValidatecallEntities() {
        return findValidatecallEntities(true, -1, -1);
    }

    public List<Validatecall> findValidatecallEntities(int maxResults, int firstResult) {
        return findValidatecallEntities(false, maxResults, firstResult);
    }

    private List<Validatecall> findValidatecallEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Validatecall.class));
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

    public Validatecall findValidatecall(ValidatecallPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Validatecall.class, id);
        } finally {
            em.close();
        }
    }

    public int getValidatecallCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Validatecall> rt = cq.from(Validatecall.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
