/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.bhaduri.tarangdbservice.DA;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import org.bhaduri.minutedataaccess.JPA.CalltableJpaController;
import org.bhaduri.minutedataaccess.entities.Calltable;

/**
 *
 * @author sb
 */
public class CalltableDA extends CalltableJpaController{

    public CalltableDA(EntityManagerFactory emf) {
        super(emf);
    }
    public List<Calltable> calllistSorted() {
        EntityManager em = getEntityManager();
        TypedQuery<Calltable> query = em.createNamedQuery("Calltable.calllistSorted", Calltable.class);              
        List<Calltable> listofscripdata = query.getResultList();
        return listofscripdata;
    }
    
    public List<Calltable> callPerScripid(String scripid) {
        EntityManager em = getEntityManager();
        TypedQuery<Calltable> query = em.createNamedQuery("Calltable.callPerScripid", Calltable.class);
        query.setParameter("scripid", scripid);
        List<Calltable> listofscripdata = query.getResultList();
        return listofscripdata;
    }
    
    public List<Calltable> getReverseCalls(String scripid, Date lastupdDate, String callString) {
        EntityManager em = getEntityManager();
        TypedQuery<Calltable> query = em.createNamedQuery("Calltable.getReverseCalls", Calltable.class);
        query.setParameter("scripid", scripid); 
        query.setParameter("lastupddate", lastupdDate);
        query.setParameter("calltwo", callString);
        List<Calltable> listofscripdata = query.getResultList();
        return listofscripdata;
    }
    
}
