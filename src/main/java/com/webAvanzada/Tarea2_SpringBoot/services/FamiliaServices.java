package com.webAvanzada.Tarea2_SpringBoot.services;

import com.webAvanzada.Tarea2_SpringBoot.entities.Familia;
import com.webAvanzada.Tarea2_SpringBoot.repositories.FamiliaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FamiliaServices {

    @Autowired
    FamiliaRepository familiaRepository;

    //CREATE AND UPDATE
    public void createOrUpdateFamilia(Familia familia){
        familiaRepository.save(familia);
    }

    //GET ALL FAMILIAS
    public List<Familia> allFamilias(){
        List<Familia> familiasList = new ArrayList<Familia>();
        familiaRepository.findAll().forEach(familia -> familiasList.add(familia));
        return familiasList;
    }

    //GET ONE SPECIFIC FAMILIA BY ID
    public Familia getFamiliaById(int idFamilia){
        for (Familia familia : allFamilias()){
            if(familia.getIdFamilia() == idFamilia){
                return familia;
            }
        }
        return null;
    }

    //GET ALL FAMILIAS NAMES
    public List<String> allFamiliasNames(){
        List<String> familiasList = new ArrayList<String>();
        familiaRepository.findAll().forEach(familia -> familiasList.add(familia.getNombreFamilia()));
        return familiasList;
    }

    //GET ONE SPECIFIC FAMILIA BY NAME
    public Familia getFamiliaByName(String nombreFamilia){
        for (Familia familia : allFamilias()){
            if(familia.getNombreFamilia() == nombreFamilia){
                return familia;
            }
        }
        return null;
    }

    //DELETE FAMILIA
    public boolean deleteFamilia(int idFamilia){
        for (Familia familia : allFamilias()){
            if(familia.getIdFamilia() == idFamilia){
                familiaRepository.delete(familia);
                return true;
            }
        }
        return false;
    }

}
