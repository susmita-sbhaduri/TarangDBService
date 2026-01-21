/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.bhaduri.tarangdbservice.DA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.Date;
import org.bhaduri.tarangdbservice.JPA.ValidatecallJpaController;
import org.bhaduri.tarangdbservice.entities.Validatecall;

/**
 *
 * @author sb
 */
public class ValidatecallDA extends ValidatecallJpaController{
    public ValidatecallDA(EntityManagerFactory emf) {
        super(emf);
    }
    public Validatecall oldestRecord(String scripid) {
        EntityManager em = getEntityManager();
        TypedQuery<Validatecall> query = em.createNamedQuery("Validatecall.getOldestRecAcrossScrip", Validatecall.class);        
        query.setParameter("scripid", scripid);
        query.setMaxResults(1);  // Equivalent to LIMIT 1
        return query.getSingleResult();
    }
    


}
