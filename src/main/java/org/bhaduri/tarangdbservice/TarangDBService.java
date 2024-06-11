/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.bhaduri.tarangdbservice;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import org.bhaduri.tarangdbservice.DA.MinutedataDA;
import org.bhaduri.tarangdbservice.entities.Minutedata;

/**
 *
 * @author bhaduri
 */
public class TarangDBService {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.bhaduri_TarangDBService_jar_1.0-SNAPSHOTPU");
        MinutedataDA minutedataDA = new MinutedataDA(emf);
        List<Minutedata> minutedatas  = minutedataDA.listByScripid("ADANIENT");
        minutedatas.forEach(m-> System.out.println(Double.toString(m.getDaylastprice())));
        
    }
}
