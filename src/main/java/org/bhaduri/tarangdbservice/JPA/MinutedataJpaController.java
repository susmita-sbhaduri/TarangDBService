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
import org.bhaduri.tarangdbservice.entities.Minutedata;
import org.bhaduri.tarangdbservice.entities.MinutedataPK;

/**
 *
 * @author sb
 */
public class MinutedataJpaController implements Serializable {

    public MinutedataJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Minutedata minutedata) throws PreexistingEntityException, Exception {
        if (minutedata.getMinutedataPK() == null) {
            minutedata.setMinutedataPK(new MinutedataPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(minutedata);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMinutedata(minutedata.getMinutedataPK()) != null) {
                throw new PreexistingEntityException("Minutedata " + minutedata + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Minutedata minutedata) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            minutedata = em.merge(minutedata);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MinutedataPK id = minutedata.getMinutedataPK();
                if (findMinutedata(id) == null) {
                    throw new NonexistentEntityException("The minutedata with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MinutedataPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Minutedata minutedata;
            try {
                minutedata = em.getReference(Minutedata.class, id);
                minutedata.getMinutedataPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The minutedata with id " + id + " no longer exists.", enfe);
            }
            em.remove(minutedata);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Minutedata> findMinutedataEntities() {
        return findMinutedataEntities(true, -1, -1);
    }

    public List<Minutedata> findMinutedataEntities(int maxResults, int firstResult) {
        return findMinutedataEntities(false, maxResults, firstResult);
    }

    private List<Minutedata> findMinutedataEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Minutedata.class));
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

    public Minutedata findMinutedata(MinutedataPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Minutedata.class, id);
        } finally {
            em.close();
        }
    }

    public int getMinutedataCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Minutedata> rt = cq.from(Minutedata.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
