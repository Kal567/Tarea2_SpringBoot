package com.webAvanzada.Tarea2_SpringBoot.services;

import com.webAvanzada.Tarea2_SpringBoot.entities.Alquiler;
import com.webAvanzada.Tarea2_SpringBoot.repositories.AlquilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.temporal.ChronoUnit;
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
        alquilerRepository.findAll().forEach(Alquiler -> alquilerList.add(Alquiler));
        return alquilerList;
    }

    //GET ALL ALQUILERES OF AN USER
    public List<Alquiler> allAlquileresUser(int idUsuario){
        List<Alquiler> alquilerList = new ArrayList<Alquiler>();
        for (Alquiler alquiler : allAlquileres()){
            if(alquiler.getIdUsuarioAlquiler() == idUsuario ){
                alquilerList.add(alquiler);
            }
        }
        return alquilerList;
    }

    //GET ALL ALQUILERES PENDIENTES OF AM USER
    public List<Alquiler> allAlquileresPendientes(int idUsuario){
        List<Alquiler> alquilerList = new ArrayList<Alquiler>();
        for (Alquiler alquiler : allAlquileres()){
            if(alquiler.getIdUsuarioAlquiler() == idUsuario && !(alquiler.isEntregado())){
                alquilerList.add(alquiler);
            }
        }
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

    //LIST ALL PRODUCTS RENTED BY ONE CLIENT
    public List<Alquiler> allProductosAlquilados(int idCliente){
        List<Alquiler> alquilerList = new ArrayList<Alquiler>();
        for (Alquiler alquiler : allAlquileres()){
            if(alquiler.getIdUsuarioAlquiler() == idCliente){
                alquilerList.add(alquiler);
            }
        }
        return alquilerList;
    }

    //LIST ALL PRODUCTS RENTED BY ONE CLIENT
    public Integer cantidadTotalDiasAlquiladosCliente(int idCliente){
        int totalDias = 0;
        for (Alquiler alquiler : allAlquileres()){
            if(alquiler.getIdUsuarioAlquiler() == idCliente) {
                totalDias += alquiler.getDiasAlquilado();
            }
        }
        return totalDias;
    }

    public double calcularPrecioPorDias(int diasAlquilado, double precioProducto){
        double total = (double) diasAlquilado * precioProducto;
        return total;
    }
}
