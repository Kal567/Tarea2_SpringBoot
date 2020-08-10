package com.webAvanzada.Tarea2_SpringBoot.controllers;

import com.webAvanzada.Tarea2_SpringBoot.entities.Alquiler;
import com.webAvanzada.Tarea2_SpringBoot.entities.Producto;
import com.webAvanzada.Tarea2_SpringBoot.entities.UserInfo;
import com.webAvanzada.Tarea2_SpringBoot.entities.Usuario;
import com.webAvanzada.Tarea2_SpringBoot.services.AlquilerServices;
import com.webAvanzada.Tarea2_SpringBoot.services.ProductoServices;
import com.webAvanzada.Tarea2_SpringBoot.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/alquileres/{idUsuario}")
public class AlquilerController {

    @Autowired
    private AlquilerServices alquilerServices;

    @Autowired
    private UsuarioServices usuarioServices;

    @Autowired
    private ProductoServices productoServices;

    public static UserInfo userInfo = new UserInfo();

    //NEW ALQUILER
    @GetMapping("/newAlquiler/{idProducto}")
    public String newAlquiler(@PathVariable("idUsuario") int idUsuario,
                              @PathVariable("idProducto") int idProducto,
                              Model model){
        Usuario usuario = usuarioServices.getUsuario(idUsuario);
        Producto producto = productoServices.getProducto(idProducto);
        model.addAttribute("usuario", usuario);
        model.addAttribute("idProducto", idProducto);
        model.addAttribute("nombreProducto", producto.getNombreProducto());
        model.addAttribute("precio", producto.getPrecio());
        model.addAttribute("cantidadActual", producto.getCantidadActual());

        model.addAttribute("idUsuario", userInfo.getIdUserInfo());

        return "createAlquiler";
    }

    @GetMapping("/alquileresList")
    public String getAlquileresList(Model model, @PathVariable("idUsuario") int idUsuario){

        ArrayList<String> productosPendientes = new ArrayList<>();
        ArrayList<String> productosAll = new ArrayList<>();

        for (Alquiler alquiler: alquilerServices.allAlquileresPendientes(idUsuario)
             ) {
            Producto producto  = productoServices.getProducto(alquiler.getIdProductoAlquiler());
            productosPendientes.add(producto.getNombreProducto());
        }

        for (Alquiler alquiler: alquilerServices.allAlquileresUser(idUsuario)
        ) {
            Producto producto  = productoServices.getProducto(alquiler.getIdProductoAlquiler());
            productosAll.add(producto.getNombreProducto());
        }

        model.addAttribute("getAllAlquileresPendientes", alquilerServices.allAlquileresPendientes(idUsuario));
        model.addAttribute("getAllAlquileres", alquilerServices.allAlquileresUser(idUsuario));
        model.addAttribute("productoPendiente", productosPendientes);
        model.addAttribute("productosAll", productosAll);
        model.addAttribute("usuario", userInfo.getIdUserInfo());


        return "listAlquileres";
    }

    //GET ALL ALQUILERES
    @GetMapping("/pendientes")
    public String getAllAlquileresPendientes(Model model, @PathVariable("idUsuario") int idUsuario){
        model.addAttribute("getAllAlquileresPendientes", alquilerServices.allAlquileresPendientes(idUsuario));
        Usuario usuario = usuarioServices.getUsuario(idUsuario);
        userInfo.setIdUserInfo(usuario.getIdUsuario());
        userInfo.setNombreUserInfo(usuario.getUsername());

        model.addAttribute("usuario", userInfo.getIdUserInfo());
        return "misAlquileres";
    }

    //GET ALL ALQUILERES
    @GetMapping("/all")
    public String getAllAlquileres(Model model){
        model.addAttribute("getAllAlquileres", alquilerServices.allAlquileresUser(userInfo.getIdUserInfo()));
        model.addAttribute("usuario", userInfo.getIdUserInfo());
        return "misAlquileres";
    }

    //GET ONE ALQUILER
    @GetMapping("/{id}")
    public String getAlquiler(Model model, @PathVariable int idAlquiler){
        model.addAttribute("getAlquiler", alquilerServices.getAlquiler(idAlquiler));
        model.addAttribute("usuario", userInfo.getIdUserInfo());
        return "showAlquiler";
    }

    //DELETE ALQUILER
    @GetMapping("/deletealquileres/{id}")
    public String deleteAlquiler(@PathVariable("id") int idAlquiler){
        alquilerServices.deleteAlquiler(idAlquiler);
        return "redirect:/alquileres/" + userInfo.getIdUserInfo() + "/alquileresList";
    }

}
