/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.bhaduri.tarangdbservice.DA;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.bhaduri.tarangdbservice.JPA.ScripsJpaController;
import org.bhaduri.tarangdbservice.entities.Scrips;


/**
 *
 * @author sb
 */
public class ScripsDA extends ScripsJpaController{
    
    public ScripsDA(EntityManagerFactory emf) {
        super(emf);
    }
//    public List<Scrips> listAllScripid() {
//        EntityManager em = getEntityManager();
//        TypedQuery<Scrips> query = em.createNamedQuery("Scrips.listAllScripid", Scrips.class);             
//        List<Scrips> listofscrips = query.getResultList();
//        return listofscrips;
//    }
}
