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
import org.bhaduri.tarangdbservice.entities.Commodity;
import org.bhaduri.tarangdbservice.entities.CommodityPK;

/**
 *
 * @author bhaduri
 */
public class CommodityJpaController implements Serializable {

    public CommodityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Commodity commodity) throws PreexistingEntityException, Exception {
        if (commodity.getCommodityPK() == null) {
            commodity.setCommodityPK(new CommodityPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(commodity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCommodity(commodity.getCommodityPK()) != null) {
                throw new PreexistingEntityException("Commodity " + commodity + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Commodity commodity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            commodity = em.merge(commodity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CommodityPK id = commodity.getCommodityPK();
                if (findCommodity(id) == null) {
                    throw new NonexistentEntityException("The commodity with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CommodityPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Commodity commodity;
            try {
                commodity = em.getReference(Commodity.class, id);
                commodity.getCommodityPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The commodity with id " + id + " no longer exists.", enfe);
            }
            em.remove(commodity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Commodity> findCommodityEntities() {
        return findCommodityEntities(true, -1, -1);
    }

    public List<Commodity> findCommodityEntities(int maxResults, int firstResult) {
        return findCommodityEntities(false, maxResults, firstResult);
    }

    private List<Commodity> findCommodityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Commodity.class));
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

    public Commodity findCommodity(CommodityPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Commodity.class, id);
        } finally {
            em.close();
        }
    }

    public int getCommodityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Commodity> rt = cq.from(Commodity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
