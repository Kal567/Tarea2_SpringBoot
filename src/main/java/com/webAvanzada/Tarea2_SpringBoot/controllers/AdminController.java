package com.webAvanzada.Tarea2_SpringBoot.controllers;

import com.webAvanzada.Tarea2_SpringBoot.entities.Usuario;
import com.webAvanzada.Tarea2_SpringBoot.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AdminController {

    @Autowired
    private UsuarioServices usuarioServices;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goMainPage(){
        return "login";
    }

}
