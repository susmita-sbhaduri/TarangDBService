/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.bhaduri.tarangdbservice.services;

import java.util.List;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.bhaduri.tarangdbservice.DA.MinutedataDA;
import org.bhaduri.tarangdbservice.entities.Minutedata;
import org.bhaduri.tarangdto.LastTransactionPrice;

/**
 *
 * @author sb
 */
public class MasterDataServices {

    private EntityManagerFactory emf;

    public MasterDataServices() {
        emf = Persistence.createEntityManagerFactory("org.bhaduri_TarangDBService_jar_1.0-SNAPSHOTPU");
    }

    public List<LastTransactionPrice> getLastTransactionPriceList(String scripid) {
        MinutedataDA minutedataDA = new MinutedataDA(emf);

        List<Minutedata> minutedatas = minutedataDA.listByScripid(scripid);
        List<LastTransactionPrice> lastTransactrionPriceList = IntStream
                .range(0, minutedatas.size())
                .mapToObj(m-> new LastTransactionPrice(m, minutedatas.get(m).getDaylastprice()))
                .collect(Collectors.toList());
        return lastTransactrionPriceList;
    }

}
