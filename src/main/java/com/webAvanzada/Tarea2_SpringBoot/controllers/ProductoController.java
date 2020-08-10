package com.webAvanzada.Tarea2_SpringBoot.controllers;

import com.webAvanzada.Tarea2_SpringBoot.entities.Producto;
import com.webAvanzada.Tarea2_SpringBoot.entities.UserInfo;
import com.webAvanzada.Tarea2_SpringBoot.entities.Usuario;
import com.webAvanzada.Tarea2_SpringBoot.services.ProductoServices;
import com.webAvanzada.Tarea2_SpringBoot.services.SubFamiliaServices;
import com.webAvanzada.Tarea2_SpringBoot.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
        Cookie[] cookies = request.getCookies();
        String idUser = "";
        if(cookies != null){
            for (int i = 0; i < cookies.length; i++) {
                idUser = cookies[i].getValue();
            }
        }

        model.addAttribute("idUsuario", Integer.parseInt(idUser));*/


@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoServices productoServices;

    @Autowired
    private SubFamiliaServices subFamiliaServices;

    @Autowired
    private UsuarioServices usuarioServices;

    public static UserInfo userInfo = new UserInfo();

    //GET ALL PRODUCTOS DISPONIBLES*********************************<<<<<<<<<<<
    @GetMapping("/{idUsuario}/disponibles")
    public String getAllProductosDisponibles(Model model, @PathVariable("idUsuario") int idUser){
        List<Producto> productos= productoServices.allProductosDisponibles();

        model.addAttribute("getAllProductos", productos);
        model.addAttribute("usuario", userInfo.getIdUserInfo());
        return "listProducto";
    }

    //GET ALL PRODUCTOS*********************************<<<<<<<<<<<
    //@GetMapping("/all")
    @RequestMapping(path = "/{idUsuario}/all", method = RequestMethod.GET)
    public String getAllProductos(Model model, @PathVariable("idUsuario") int idUsuario){
        List<Producto> productos= productoServices.allProductos();
        model.addAttribute("getAllProductos", productos);

        Usuario usuario = usuarioServices.getUsuario(idUsuario);
        userInfo.setIdUserInfo(usuario.getIdUsuario());
        userInfo.setNombreUserInfo(usuario.getUsername());

        model.addAttribute("usuario", userInfo.getIdUserInfo());
        return "listProducto";
    }

    //GO TO CREATE NEW PRODUCT PAGE
    @GetMapping("/{idUsuario}/createProducto")
    public String createProducto(Model model, @PathVariable("idUsuario") int idUser){
        List<String> subFamiliaList = subFamiliaServices.allSubFamiliasNames();
        if(subFamiliaList == null){
            model.addAttribute("subFamilias", "No Familias Yet.");
        } else {
            model.addAttribute("subFamilias", subFamiliaList);
        }
        model.addAttribute("usuario", userInfo.getIdUserInfo());

        return "createProducto";
    }

    //CREATE A NEW PRODUCTO - BUTTON
    @PostMapping("/{idUsuario}/newProducto")
    public String newProducto(@PathVariable("idUsuario") int idUsuario,
                              @RequestParam String nombreProducto, @RequestParam double precio,
                              @RequestParam int cantidadActual, @RequestParam String subFamilia,
                              Model model)/*,
                              @RequestParam ("imageProducto") MultipartFile imgProducto) throws IOException*/ {
        //byte[] imageBytes = imgProducto.getBytes();
        Producto producto = new Producto(nombreProducto, precio, cantidadActual,
                true/*, imageBytes*/, subFamilia);
        productoServices.createOrUpdateProducto(producto);
        model.addAttribute("usuario", userInfo.getIdUserInfo());
        return "redirect:/productos/" + idUsuario + "/createProducto";
    }

    //UPDATE A NEW PRODUCTO - BUTTON
        @PostMapping("/{idUsuario}/updateProducto/{idProducto}")
        public String updateProducto(@PathVariable("idUsuario") int idUsuario,
                                     @PathVariable("idProducto") int idProducto,
                                     @RequestParam String nombreProducto,
                                     @RequestParam double precio, @RequestParam int cantidadActual,
                                     @RequestParam String subFamilia, Model model){
            Producto producto = productoServices.getProducto(idProducto);
            producto.setNombreProducto(nombreProducto);
            producto.setPrecio(precio);
            producto.setCantidadActual(cantidadActual);
            producto.setSubFamilia(subFamilia);
            productoServices.createOrUpdateProducto(producto);
            model.addAttribute("usuario", userInfo.getIdUserInfo());
            return "redirect:/productos/" + idUsuario + "/all";
        }

    //GET ONE PRODUCTO* GO TO EDIT A PRODUCT PAGE********************************<<<<<<<<<<<
    @GetMapping("/{idUsuario}/{id}")
    public String getProducto(Model model, @PathVariable("id") int idProducto){
        Producto producto= productoServices.getProducto(idProducto);
        model.addAttribute("getProducto", producto);

        List<String> subFamiliaList = subFamiliaServices.allSubFamiliasNames();
        if(subFamiliaList == null){
            model.addAttribute("subFamilias", "No Familias Yet.");
        } else {
            model.addAttribute("subFamilias", subFamiliaList);
        }
        model.addAttribute("usuario", userInfo.getIdUserInfo());
        return "createProducto";
    }

    //DELETE PRODUCTO
    @GetMapping("/{idUsuario}/deleteproducto/{id}")
    public String deleteProducto(@PathVariable("id") int idProducto){
        productoServices.deleteProducto(idProducto);
        return "redirect:/productos/" + userInfo.getIdUserInfo() + "/all";
    }
}
