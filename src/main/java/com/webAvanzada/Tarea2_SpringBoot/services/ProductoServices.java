package com.webAvanzada.Tarea2_SpringBoot.services;

import com.webAvanzada.Tarea2_SpringBoot.entities.Producto;
import com.webAvanzada.Tarea2_SpringBoot.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoServices {

    @Autowired
    ProductoRepository productoRepository;

    //CREATE AND UPDATE
    public void createOrUpdateProducto(Producto producto){
        productoRepository.save(producto);
    }

    //GET ALL PRODUCTOS DISPONIBLES
    public List<Producto> allProductosDisponibles(){
        List<Producto> productoList = new ArrayList<Producto>();
        productoRepository.getAllProductosDisponibles().forEach(producto -> productoList.add(producto));
        return productoList;
    }

    //GET ALL PRODUCTOS
    public List<Producto> allProductos(){
        List<Producto> productoList = new ArrayList<Producto>();
        productoRepository.findAll().forEach(producto -> productoList.add(producto));
        return productoList;
    }

    //GET ONE SPECIFIC PRODUCTO BY ID
    public Producto getProducto(int idProducto){
        for (Producto product : allProductos()){
            if(product.getIdProducto() == idProducto){
                return product;
            }
        }
        return null;
    }

    //DELETE PRODUCTO BY ID
    public boolean deleteProducto(int idProducto){
        for (Producto product : allProductos()){
            if(product.getIdProducto() == idProducto){
                productoRepository.delete(product);
                return true;
            }
        }
        return false;
    }
}
