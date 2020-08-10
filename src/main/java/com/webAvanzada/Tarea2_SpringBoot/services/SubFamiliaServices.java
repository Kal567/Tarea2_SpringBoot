package com.webAvanzada.Tarea2_SpringBoot.services;

import com.webAvanzada.Tarea2_SpringBoot.entities.Familia;
import com.webAvanzada.Tarea2_SpringBoot.entities.SubFamilia;
import com.webAvanzada.Tarea2_SpringBoot.repositories.FamiliaRepository;
import com.webAvanzada.Tarea2_SpringBoot.repositories.SubFamiliaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubFamiliaServices {

    @Autowired
    SubFamiliaRepository subFamiliaRepository;

    //CREATE AND UPDATE
    public void createOrUpdateSubFamilia(SubFamilia subFamilia){
        subFamiliaRepository.save(subFamilia);
    }

    //GET ALL FAMILIAS
    public List<SubFamilia> allSubFamilias(){
        List<SubFamilia> subFamiliasList = new ArrayList<SubFamilia>();
        subFamiliaRepository.findAll().forEach(subFamilia -> subFamiliasList.add(subFamilia));
        return subFamiliasList;
    }

    //GET ONE SPECIFIC FAMILIA BY SUB-FAMILIA NAME
    public String getSubFamiliaBySubFamiliaName(String nombreFamilia){
        for (SubFamilia subFamilia : allSubFamilias()){
            if(subFamilia.getFamilia() == nombreFamilia){
                return subFamilia.getFamilia();
            }
        }
        return null;
    }

    //GET ALL SUB-FAMILIAS NAMES
    public List<String> allSubFamiliasNames(){
        List<String> SubFamiliasList = new ArrayList<String>();
        subFamiliaRepository.findAll().forEach(subFamilia -> SubFamiliasList.add(subFamilia.getNameSubFamily()));
        return SubFamiliasList;
    }

    //GET ONE SPECIFIC FAMILIA BY ID
    public SubFamilia getSubFamilia(int idSubFamilia){
        for (SubFamilia subFamilia : allSubFamilias()){
            if(subFamilia.getIdSubFamilia() == idSubFamilia){
                return subFamilia;
            }
        }
        return null;
    }

    //DELETE FAMILIA
    public boolean deleteSubFamilia(int idSubFamilia){
        for (SubFamilia subFamilia : allSubFamilias()){
            if(subFamilia.getIdSubFamilia() == idSubFamilia){
                subFamiliaRepository.delete(subFamilia);
                return true;
            }
        }
        return false;
    }
}
