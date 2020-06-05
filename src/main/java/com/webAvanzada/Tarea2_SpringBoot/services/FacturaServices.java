package com.webAvanzada.Tarea2_SpringBoot.services;

import com.webAvanzada.Tarea2_SpringBoot.entities.Factura;
import com.webAvanzada.Tarea2_SpringBoot.repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacturaServices {
    @Autowired
    FacturaRepository facturaRepository;

    //CREATE AND UPDATE
    public void createOrUpdateFactura(Factura factura){
        facturaRepository.save(factura);
    }

    //GET ALL FACTURAS
    public List<Factura> allFacturas(){
        List<Factura> facturaList = new ArrayList<Factura>();
        facturaRepository.findAll().forEach(factura -> facturaList.add(factura));
        return facturaList;
    }

    //GET ONE SPECIFIC FACTURA BY ID
    public Factura getFactura(int idFactura){
        for (Factura factura : allFacturas()){
            if(factura.getIdFactura() == idFactura){
                return factura;
            }
        }
        return null;
    }

    //DELETE FACTURA BY ID
    public boolean deleteFactura(int idFactura){
        for (Factura factura : allFacturas()){
            if(factura.getIdFactura() == idFactura){
                facturaRepository.delete(factura);
                return true;
            }
        }
        return false;
    }
}
