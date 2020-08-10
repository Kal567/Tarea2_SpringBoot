package com.webAvanzada.Tarea2_SpringBoot.controllers;

import com.webAvanzada.Tarea2_SpringBoot.entities.*;
import com.webAvanzada.Tarea2_SpringBoot.services.AlquilerServices;
import com.webAvanzada.Tarea2_SpringBoot.services.FacturaServices;
import com.webAvanzada.Tarea2_SpringBoot.services.ProductoServices;
import com.webAvanzada.Tarea2_SpringBoot.services.UsuarioServices;
import jdk.vm.ci.meta.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.time.temporal.ChronoUnit.DAYS;

//
@org.springframework.stereotype.Controller
@RequestMapping("/usuario/{idUsuario}")
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;

    @Autowired
    private ProductoServices productoServices;

    @Autowired
    private AlquilerServices alquilerServices;

    @Autowired
    private FacturaServices facturaServices;
/*
    //PARA QUE DIRIJA DIRECTAMENTE AL LOGIN, AL INICIO
    //BLANK: A CAMBIAR.
    //@RequestMapping(path = "/", method = RequestMethod.GET)
    @GetMapping("/login")
    public String loginPage() {
        return "login";//NOMBRE DE EJEMPLO!! PUEDE CAMBIAR.
    }

    //BOTON LOGIN PARA ACCEDER A LA CUENTA
    //@RequestMapping(path = "/login", method = RequestMethod.POST)
    @PostMapping("/login")
    public String userLogin(@RequestParam String username, @RequestParam String password) {
        Usuario user = usuarioServices.isRegistered(username, password);
        if(user != null){
            return "redirect:/{" + user.getIdUsuario() + "}/home";//GOING TO MAIN ACCOUNT PAGE
        }
        return "redirect:/";//STAYING IN LOGIN PAGE
    }
*/

    //GOING TO THE HOME PAGE ONCE LOGGED IN - THE PAGE SHOWING THE PRODUCTS AVAILABLE
    @GetMapping("/home")
    public String home(Model model, @PathVariable String idUser){
        if(productoServices.allProductos() == null){
            model.addAttribute("productos", "empty");
        }else{
            model.addAttribute("productos", productoServices.allProductos());
        }

        return "home";//template 'home'
    }

    //SHOW 'misProductos' PAGE - THE CLIENT'S RENTED PRODUCTS
    @GetMapping("/misProductos")
    public String showMisProductos(Model model, @PathVariable int idUser) {
        if(alquilerServices.allProductosAlquilados(idUser) == null){
            model.addAttribute("misProductos", "empty");
        }else {
            model.addAttribute("misProductos", alquilerServices.allProductosAlquilados(idUser));
        }
        return "misProductos"; //template
    }

    //ALQUILAR PRODUCTO
    @PostMapping("/alquilarProducto")
    public String alquilarProductos(@PathVariable("idUsuario") int idUser, @RequestParam int idProducto,
                                   @RequestParam String nombreProducto,
                                   @RequestParam String fechaAlquiler, @RequestParam String fechaEntregaEstablecida,
                                    Model model) throws ParseException {
       Usuario usuario = usuarioServices.getUsuario(idUser);

        Producto producto = productoServices.getProducto(idProducto);

        //productoServices.createOrUpdateProducto(producto);//making producto no longer available to others

        if(producto.getCantidadActual() > 0){
            producto.setCantidadActual(producto.getCantidadActual() - 1);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaInicio = LocalDate.parse(fechaAlquiler, formatter);
        LocalDate fechaFinal = LocalDate.parse(fechaEntregaEstablecida, formatter);

        //registrar alquiler de este producto
        int diasAlquilado = (int) DAYS.between(fechaInicio,fechaFinal);
        double precioPorDias = alquilerServices.
                calcularPrecioPorDias(diasAlquilado, producto.getPrecio());
        Alquiler alquiler = new Alquiler(producto.getIdProducto(), nombreProducto, idUser,
                fechaInicio, fechaFinal,
                fechaFinal, diasAlquilado, false,
                producto.getPrecio(), precioPorDias, 0);
        alquilerServices.createOrUpdateAlquiler(alquiler);//save this alquiler

        //updating product stock
        if(producto.getCantidadActual() > 0){
            producto.setCantidadActual(producto.getCantidadActual() - 1);
            productoServices.createOrUpdateProducto(producto);
        } else {
            producto.setDisponible(false);
        }


        if(producto.getCantidadActual() <= 0){
            producto.setDisponible(false);//setting producto como no disponible
        } else {
            producto.setDisponible(true);
        }

        model.addAttribute("usuario", idUser);
        return "redirect:/productos/" + idUser + "/all";
    }

    //ENTREGAR PRODUCTOS - FACTURAR LOS ENTREGADOS (SELECCIONADOS EN MIS PRODUCTOS)
    @GetMapping("/entregarProductos/{idAlquiler}")
    public String entregarProductos(@PathVariable("idUsuario") int idUser,
                                    Model model, @PathVariable("idAlquiler") int idAlquiler){
        Usuario usuario = usuarioServices.getUsuario(idUser);

            Alquiler alquiler = alquilerServices.getAlquiler(idAlquiler);

           /* if(alquiler.getFechaRealEntregado() != LocalDate.now()) {
                alquiler.setFechaRealEntregado(LocalDate.now());
                //alquilerServices.createOrUpdateAlquiler(alquiler); //UPDATE AQUI

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaInicio = LocalDate.parse(alquiler.getFechaEntregaEstablecida().toString(),
                        formatter);
                LocalDate fechaFinal = LocalDate.parse(alquiler.getFechaRealEntregado().toString(),
                        formatter);

                //registrar alquiler de este producto
                int diasExtrasAlquilados = (int) DAYS.between(fechaInicio, fechaFinal);
                alquiler.setDiasAlquilado(alquiler.getDiasAlquilado() + diasExtrasAlquilados);
                alquiler.setPrecioPorDias(alquiler.getPrecioPorDias() + alquilerServices.
                        calcularPrecioPorDias(diasExtrasAlquilados, alquiler.getPrecioProducto()));
            }*/

            Producto producto = productoServices.getProducto(alquiler.getIdProductoAlquiler());
            producto.setCantidadActual(producto.getCantidadActual() + 1);
            if(producto.getCantidadActual() > 0){
                producto.setDisponible(true);//setting producto nuevamente como disponible
            } else {
                producto.setDisponible(false);
            }
            ////UPDATE AQUI
            //productoServices.createOrUpdateProducto(producto);//making producto available again to others


        //add precios de los alquileres
      /*  double precioTotalAlquileres = 0;
        for(int i = 0; i < alquileres.size(); i++){
            precioTotalAlquileres =+ alquileres.get(i).getPrecioPorDias();
        }*/

        //double precioTotalAlquileres = alquiler.getPrecioPorDias();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechahoy = LocalDate.parse(LocalDate.now().toString(),
                formatter);

        //save new factura
        Factura factura = new Factura(usuario.getIdUsuario(), alquiler.getPrecioPorDias(), alquiler.getIdAlquiler());
        //facturaServices.createOrUpdateFactura(factura);

      /*  for(int i = 0; i < alquileres.size(); i++){
            alquileres.get(i).setIdFactura(factura.getIdFactura());
            //alquilerServices.createOrUpdateAlquiler(alquileres.get(i));//UPDATE AQUI
        }*/

        alquiler.setIdFactura(factura.getIdFactura());

        model.addAttribute("alquileres", alquiler);
        model.addAttribute("factura", factura);
        model.addAttribute("usuario", idUser);

        return "showFactura";//To the home page
    }

    @GetMapping("/facturarEntrega/{idAlquiler}")
    public String facturarEntrega(@PathVariable("idUsuario") int idUser,
                                    Model model, @PathVariable("idAlquiler") int idAlquiler){
        Usuario usuario = usuarioServices.getUsuario(idUser);

        Alquiler alquiler = alquilerServices.getAlquiler(idAlquiler);

        Producto producto = productoServices.getProducto(alquiler.getIdProductoAlquiler());
        producto.setCantidadActual(producto.getCantidadActual() + 1);

        productoServices.createOrUpdateProducto(producto);//making producto available again to others

        //save new factura
        Factura factura = new Factura(usuario.getIdUsuario(), alquiler.getPrecioPorDias(), alquiler.getIdAlquiler());
        facturaServices.createOrUpdateFactura(factura);

        alquiler.setEntregado(true);
        alquiler.setIdFactura(factura.getIdFactura());
        alquilerServices.createOrUpdateAlquiler(alquiler);
        model.addAttribute("idAlquiler", idAlquiler);
        model.addAttribute("alquileres", alquiler);
        model.addAttribute("factura", factura);
        model.addAttribute("usuario", idUser);

        return "redirect:/alquileres/" + idUser + "/alquileresList";//To the home page
    }

    //SHOW 'misFacturas' PAGE - THE CLIENT'S RECEIPTS
    @GetMapping("/misFacturas")
    public String showMisFacturas(Model model, @PathVariable("idUsuario") int idUser) {
        List<Factura> factura = facturaServices.allFacturaByCliente(idUser);
        if(factura == null){
            model.addAttribute("getAllFacturas", "empty");
        }else {
            model.addAttribute("getAllFacturas", factura);
        }
        model.addAttribute("usuario", idUser);
        return "listFacturas";//template
    }

    //INFO PAGE OF A SPECIFIC FACTURA
    @GetMapping("/misFacturas/{idFactura}")
    public String showFactura(Model model, @PathVariable int idFactura){
        model.addAttribute("factura", facturaServices.getFactura(idFactura));
        return "misFacturas";
    }

}
