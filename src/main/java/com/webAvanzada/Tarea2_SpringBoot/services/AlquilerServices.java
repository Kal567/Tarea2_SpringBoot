package com.webAvanzada.Tarea2_SpringBoot.services;

import com.webAvanzada.Tarea2_SpringBoot.entities.Alquiler;
import com.webAvanzada.Tarea2_SpringBoot.repositories.AlquilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlquilerServices {
    @Autowired
    AlquilerRepository alquilerRepository;

    //CREATE AND UPDATE
    public void createOrUpdateAlquiler(Alquiler alquiler){
        alquilerRepository.save(alquiler);
    }

    //GET ALL ALQUILERES
    public List<Alquiler> allAlquileres(){
        List<Alquiler> alquilerList = new ArrayList<Alquiler>();
        alquilerRepository.findAll().forEach(alquiler -> alquilerList.add(alquiler));
        return alquilerList;
    }

    //GET ONE SPECIFIC ALQUILER BY ID
    public Alquiler getAlquiler(int idAlquiler){
        for (Alquiler alquiler : allAlquileres()){
            if(alquiler.getIdAlquiler() == idAlquiler){
                return alquiler;
            }
        }
        return null;
    }

    //DELETE ALQUILER BY ID
    public boolean deleteAlquiler(int idAlquiler){
        for (Alquiler alquiler : allAlquileres()){
            if(alquiler.getIdAlquiler() == idAlquiler){
                alquilerRepository.delete(alquiler);
                return true;
            }
        }
        return false;
    }
}
