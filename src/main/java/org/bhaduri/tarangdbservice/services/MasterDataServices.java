/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.bhaduri.tarangdbservice.services;

import java.util.List;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.bhaduri.tarangdbservice.DA.CalltableDA;
import org.bhaduri.tarangdbservice.DA.MinutedataDA;
import org.bhaduri.tarangdbservice.DA.ScripsDA;
import org.bhaduri.tarangdbservice.JPA.exceptions.PreexistingEntityException;
import org.bhaduri.tarangdbservice.entities.Calltable;
import org.bhaduri.tarangdbservice.entities.CalltablePK;
import org.bhaduri.tarangdbservice.entities.Minutedata;
import org.bhaduri.tarangdbservice.entities.Scrips;
import org.bhaduri.tarangdto.CallResults;
import org.bhaduri.tarangdto.CallResultsIntermediate;
import org.bhaduri.tarangdto.LastTransactionPrice;
import org.bhaduri.tarangdto.LastTransactionVolume;
import org.bhaduri.tarangdto.ScripsDTO;
import org.bhaduri.tarangdto.VolumeIntermediate;

/**
 *
 * @author sb
 */
public class MasterDataServices {

    private EntityManagerFactory emf;

    public MasterDataServices() {
        emf = Persistence.createEntityManagerFactory("org.bhaduri_TarangDBService_jar_1.0-SNAPSHOTPU");
    }

    public CallResultsIntermediate getLastTransactionPriceList(String scripid) {
        MinutedataDA minutedataDA = new MinutedataDA(emf);

        List<Minutedata> minutedatas = minutedataDA.listByScripid(scripid);
        List<LastTransactionPrice> lastTransactrionPriceList = IntStream
                .range(0, minutedatas.size())
                .mapToObj(m -> new LastTransactionPrice(m, minutedatas.get(m).getDaylastprice()))
                .collect(Collectors.toList());
        Date callGenerationTimeStamp = minutedatas.getLast().getMinutedataPK().getLastupdateminute();
        Double callGenerationPrice = minutedatas.getLast().getDaylastprice();
        CallResultsIntermediate callResultsInermediate = new CallResultsIntermediate(scripid, lastTransactrionPriceList, callGenerationTimeStamp, callGenerationPrice);
        return callResultsInermediate;
    }
    
    public VolumeIntermediate getLastTransactionVolumeList(String scripid) {
        MinutedataDA minutedataDA = new MinutedataDA(emf);

        List<Minutedata> minutedatas = minutedataDA.listByScripid(scripid);
        List<LastTransactionVolume> lastTransactrionVolumeList = IntStream
                .range(0, minutedatas.size())
                .mapToObj(m -> new LastTransactionVolume(m, (int)minutedatas.get(m).getTotaltradedvolume()))
                .collect(Collectors.toList());
//        Date callGenerationTimeStamp = minutedatas.getLast().getMinutedataPK().getLastupdateminute();
//        Double callGenerationPrice = minutedatas.getLast().getDaylastprice();
        VolumeIntermediate volumeIntermediate = new VolumeIntermediate(lastTransactrionVolumeList, 0);
        return volumeIntermediate;
    }

    public List<ScripsDTO> getScripsList() {
        ScripsDA scripsDA = new ScripsDA(emf);
        List<Scrips> scripses = scripsDA.findScripsEntities();
        List<ScripsDTO> scripsDTOList = scripses.stream().map(s -> new ScripsDTO(s.getScripid())).collect(Collectors.toList());
        return scripsDTOList;
    }

    public void insterCallsInTable(CallResults callResults) {
        CalltableDA calltableDA = new CalltableDA(emf);

        CalltablePK calltablePK = new CalltablePK();
        Calltable calltable = new Calltable();
        calltablePK.setScripid(callResults.getScripId());
        calltablePK.setLastupdateminute(callResults.getCallGenerationTimeStamp());
        calltable.setCalltablePK(calltablePK);
        calltable.getCalltablePK().setLastupdateminute(callResults.getCallGenerationTimeStamp());
        calltable.setCallone(callResults.getCallVersionOne());
        calltable.setCalltwo(callResults.getCallVersionTwo());
        calltable.setPrice(callResults.getCallGenerationPrice());
        try {
            calltableDA.create(calltable);
        } catch (PreexistingEntityException pe) {
            System.out.println("Duplicate" + callResults.getScripId());
        } catch (Exception ex) {
            Logger.getLogger(MasterDataServices.class.getName()).log(Level.WARNING, null, ex);
        }
    }
}

