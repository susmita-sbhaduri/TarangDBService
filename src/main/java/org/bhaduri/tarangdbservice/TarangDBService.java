/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.bhaduri.tarangdbservice;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import org.bhaduri.tarangdbservice.JPA.ScripsJpaController;
import org.bhaduri.tarangdbservice.entities.Scrips;

/**
 *
 * @author bhaduri
 */
public class TarangDBService {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.bhaduri_TarangDBService_jar_1.0-SNAPSHOTPU");
        ScripsJpaController scripsJpaController = new ScripsJpaController(emf);
        List<Scrips> list =  scripsJpaController.findScripsEntities();
        for (int i = 0; i < list.size();i++) {
            System.out.println(list.get(i).getScripid());
        }
        
    }
}
