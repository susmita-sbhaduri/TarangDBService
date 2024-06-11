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
import org.bhaduri.tarangdbservice.entities.Calltable;
import org.bhaduri.tarangdbservice.entities.CalltablePK;

/**
 *
 * @author bhaduri
 */
public class CalltableJpaController implements Serializable {

    public CalltableJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Calltable calltable) throws PreexistingEntityException, Exception {
        if (calltable.getCalltablePK() == null) {
            calltable.setCalltablePK(new CalltablePK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(calltable);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCalltable(calltable.getCalltablePK()) != null) {
                throw new PreexistingEntityException("Calltable " + calltable + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Calltable calltable) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            calltable = em.merge(calltable);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CalltablePK id = calltable.getCalltablePK();
                if (findCalltable(id) == null) {
                    throw new NonexistentEntityException("The calltable with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CalltablePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calltable calltable;
            try {
                calltable = em.getReference(Calltable.class, id);
                calltable.getCalltablePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The calltable with id " + id + " no longer exists.", enfe);
            }
            em.remove(calltable);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Calltable> findCalltableEntities() {
        return findCalltableEntities(true, -1, -1);
    }

    public List<Calltable> findCalltableEntities(int maxResults, int firstResult) {
        return findCalltableEntities(false, maxResults, firstResult);
    }

    private List<Calltable> findCalltableEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Calltable.class));
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

    public Calltable findCalltable(CalltablePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Calltable.class, id);
        } finally {
            em.close();
        }
    }

    public int getCalltableCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Calltable> rt = cq.from(Calltable.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
