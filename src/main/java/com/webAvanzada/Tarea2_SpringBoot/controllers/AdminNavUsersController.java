package com.webAvanzada.Tarea2_SpringBoot.controllers;

import com.webAvanzada.Tarea2_SpringBoot.entities.*;
import com.webAvanzada.Tarea2_SpringBoot.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
///manageusuarios/{idUsuario}
@Controller
@RequestMapping("/manageusuarios")
public class AdminNavUsersController {

    @Autowired
    private UsuarioServices usuarioServices;

    @Autowired
    private FamiliaServices familiaServices;

    @Autowired
    private SubFamiliaServices subFamiliaServices;

    @Autowired
    private AlquilerServices alquilerServices;

    @Autowired
    private ProductoServices productoServices;

    public static UserInfo userInfo = new UserInfo();


    //COOKIES
    @PostMapping("/login")
    public String setCookie(HttpServletResponse response, @RequestParam String username,
                            @RequestParam String password, HttpServletRequest request){

        Usuario usuario = usuarioServices.isRegistered(username, password);
        String template = null;
        if(usuario != null){
            Cookie cookie = new Cookie("idUser", "" + usuario.getIdUsuario());
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            response.addCookie(cookie);

            Cookie cookie2 = new Cookie("username", "" + usuario.getUsername());
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            response.addCookie(cookie2);

            /*Cookie[] cookies = request.getCookies();
            String idUser = "";
            if(cookies != null){
                for (int i = 0; i < cookies.length; i++) {
                    idUser = cookies[i].getValue();
                }
            }*/

            template = "redirect:/productos/" + usuario.getIdUsuario()  + "/all";
        } else {
            template = "login";
        }

        return template;

    }

    @GetMapping("/logout")
    public String killCookie(HttpServletResponse response){
        Cookie cookie = new Cookie("idUser", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        Cookie cookie2 = new Cookie("username", null);
        cookie2.setMaxAge(0);
        response.addCookie(cookie2);

        return "login";

    }

    //GET ALL USERS*********************************<<<<<<<<<<<
    @RequestMapping(value="/{idUsuario}/all", method=RequestMethod.GET)
    public String getAllUsuarios(Model model, @PathVariable("idUsuario") int idUsuario){
        List<byte[]> images = null;
        List<Usuario> usuarios= usuarioServices.allUsuarios();

        if(images == null){
            model.addAttribute("images", "No image");
        } else {
            model.addAttribute("images", images);
        }

        if(usuarios == null){
            model.addAttribute("getAllUsuarios", "No user registered yet");
        } else {
            model.addAttribute("getAllUsuarios", usuarios);
        }

        Usuario usuario = usuarioServices.getUsuario(idUsuario);
        userInfo.setIdUserInfo(usuario.getIdUsuario());
        userInfo.setNombreUserInfo(usuario.getUsername());

        model.addAttribute("usuario", userInfo.getIdUserInfo());

        return "listUsuario";
    }
    //GET ONE USER*********************************<<<<<<<<<<<
    @GetMapping("/{id}")
    public String getUsuario(Model model, @PathVariable("id") int idUsuario){

        Usuario usuario= usuarioServices.getUsuario(idUsuario);
        model.addAttribute("getUsuario", usuario);

        model.addAttribute("usuario", userInfo.getIdUserInfo());
        return "signUp";
    }

    //DELETE USER
    @GetMapping("/deleteuser/{idUsuario}")
    public String deleteUsuario(Model model, @PathVariable("idUsuario") int idUsuario){
        usuarioServices.deleteUsuario(idUsuario);
        return "redirect:/manageusuarios/" + userInfo.getIdUserInfo() + "all";
    }


    //GO TO SIGN UP PAGE
    @GetMapping("/newUsuario")
    public String signUp(Model model) {
        //model.addAttribute("usuario", userInfo.getIdUserInfo());

        return "signUp";//NOMBRE DE EJEMPLO!! PUEDE CAMBIAR.
    }

    //----------
    /* UPLOADING PICTURES - HTML
      <form methods="post" enctype="multipart/form-data" action="/newCliente">
            <input type="file" name="imageUsuario"/>
            <input> type="submit" value="Upload"/>
      </form>
     * */
    //CREATE NEW USUARIO ACCOUNT
    @RequestMapping(path = "/newUsuario", method = RequestMethod.POST)
    public String newCliente(@RequestParam String nombreUsuario, @RequestParam String apellidoUsuario,
                             @RequestParam String username, @RequestParam String password,
                             @RequestParam String cedulaUsuario){
        /*,
                             @RequestParam ("imageUsuario") MultipartFile imageUsuario) throws IOException*/
        //byte[] imageBytes = imageUsuario.getBytes();
        Usuario user = new Usuario(nombreUsuario, apellidoUsuario, username, password, cedulaUsuario,
                true); /*imageBytes,*/
        usuarioServices.createOrUpdateUsuario(user);
        return "login";//GOING TO LOGIN PAGE --
    }

    //                     @RequestParam ("imageUsuario") MultipartFile imageUsuario) throws IOException
    //byte[] imageBytes = imageUsuario.getBytes();
    //
    @RequestMapping(path = "/{idUsuarioLoggedIn}/updateUsuario/{idUsuario}", method = RequestMethod.POST)
    public String updateUsuario(@PathVariable("idUsuarioLoggedIn") int idUsuarioLoggedIn,
                                @PathVariable("idUsuario") int idUsuario,
                                @RequestParam String nombreUsuario,
                                @RequestParam String apellidoUsuario,
                             @RequestParam String username, @RequestParam String password,
                             @RequestParam String cedulaUsuario, Model model){

        Usuario user = usuarioServices.getUsuario(idUsuario);
        user.setNombreUsuario(nombreUsuario);
        user.setApellidoUsuario(apellidoUsuario);
        user.setUsername(username);
        user.setPassword(password);
        user.setCedulaUsuario(cedulaUsuario);
        usuarioServices.createOrUpdateUsuario(user);
        model.addAttribute("usuario", userInfo.getIdUserInfo());
        return "redirect:/manageusuarios/" + userInfo.getIdUserInfo() + "/all";//GOING TO MAIN ACCOUNT PAGE --redirect:/" + user.getIdUsuario() + "/home
    }

    //SEE CLIENT HISTORY AND GRAPHICS
    @GetMapping("{idUsuario}/historialCliente/{idUsuario}")
    public String historialCliente(@PathVariable("idUsuario") int idCliente, Model model){

        //Usuario cliente = usuarioServices.getUsuario(idCliente);
        List<Alquiler> todosAlquileresCliente = alquilerServices.allProductosAlquilados(
                idCliente);
        List<Integer> alquileresFamiliasCounter = new ArrayList<>();
        List<Integer> alquileresSubFamiliasCounter = new ArrayList<>();
        List<Familia> familiaList = familiaServices.allFamilias();
        List<SubFamilia> subFamiliaList = subFamiliaServices.allSubFamilias();
        int amountDiasAlquileresFamilias = 0;
        int amountDiasAlquileresSubFamilias = 0;

        for(int x = 0; x < familiaList.size(); x++ ) {
            for (int i = 0; i < todosAlquileresCliente.size(); i++) {
                Producto producto = productoServices.getProducto(
                        todosAlquileresCliente.get(i).getIdProductoAlquiler());
                String productoFamilia = subFamiliaServices.getSubFamiliaBySubFamiliaName(
                        producto.getSubFamilia());
                if (familiaList.get(x).getNombreFamilia().equals(productoFamilia)) {
                    amountDiasAlquileresFamilias += todosAlquileresCliente.get(i).getDiasAlquilado();
                }
            }
            alquileresFamiliasCounter.add(amountDiasAlquileresFamilias / familiaList.size());
            amountDiasAlquileresFamilias = 0;
        }

        for(int x = 0; x < subFamiliaList.size(); x++ ) {
            for (int i = 0; i < todosAlquileresCliente.size(); i++) {
                Producto producto = productoServices.getProducto(
                        todosAlquileresCliente.get(i).getIdProductoAlquiler());
                String productoFamilia = subFamiliaServices.getSubFamiliaBySubFamiliaName(
                        producto.getSubFamilia());
                if (subFamiliaList.get(x).getNameSubFamily().equals(productoFamilia)) {
                    amountDiasAlquileresSubFamilias += todosAlquileresCliente.get(i).getDiasAlquilado();
                }
            }
            alquileresSubFamiliasCounter.add(amountDiasAlquileresSubFamilias / subFamiliaList.size());
            amountDiasAlquileresSubFamilias = 0;
        }

        Map<String, Integer> graphDataFamilia = new TreeMap<>();
        for(int i = 0; i < alquileresFamiliasCounter.size(); i++){
            graphDataFamilia.put(familiaList.get(i).getNombreFamilia(), alquileresFamiliasCounter.get(i).intValue());
        }

        Map<String, Integer> graphDataSubFamilia = new TreeMap<>();
        for(int i = 0; i < alquileresSubFamiliasCounter.size(); i++){
            graphDataSubFamilia.put(subFamiliaList.get(i).getNameSubFamily(), alquileresSubFamiliasCounter.get(i).intValue());
        }
        model.addAttribute("chartDataFamilia", graphDataFamilia);
        model.addAttribute("chartDataSubFamilia", graphDataSubFamilia);

        model.addAttribute("usuario", userInfo.getIdUserInfo());
        return "showGraphs";
    }

}
