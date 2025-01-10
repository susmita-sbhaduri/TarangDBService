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
import org.bhaduri.tarangdbservice.entities.CommodityTest;
import org.bhaduri.tarangdbservice.entities.CommodityTestPK;

/**
 *
 * @author sb
 */
public class CommodityTestJpaController implements Serializable {

    public CommodityTestJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CommodityTest commodityTest) throws PreexistingEntityException, Exception {
        if (commodityTest.getCommodityTestPK() == null) {
            commodityTest.setCommodityTestPK(new CommodityTestPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(commodityTest);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCommodityTest(commodityTest.getCommodityTestPK()) != null) {
                throw new PreexistingEntityException("CommodityTest " + commodityTest + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CommodityTest commodityTest) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            commodityTest = em.merge(commodityTest);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CommodityTestPK id = commodityTest.getCommodityTestPK();
                if (findCommodityTest(id) == null) {
                    throw new NonexistentEntityException("The commodityTest with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CommodityTestPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CommodityTest commodityTest;
            try {
                commodityTest = em.getReference(CommodityTest.class, id);
                commodityTest.getCommodityTestPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The commodityTest with id " + id + " no longer exists.", enfe);
            }
            em.remove(commodityTest);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CommodityTest> findCommodityTestEntities() {
        return findCommodityTestEntities(true, -1, -1);
    }

    public List<CommodityTest> findCommodityTestEntities(int maxResults, int firstResult) {
        return findCommodityTestEntities(false, maxResults, firstResult);
    }

    private List<CommodityTest> findCommodityTestEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CommodityTest.class));
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

    public CommodityTest findCommodityTest(CommodityTestPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CommodityTest.class, id);
        } finally {
            em.close();
        }
    }

    public int getCommodityTestCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CommodityTest> rt = cq.from(CommodityTest.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
