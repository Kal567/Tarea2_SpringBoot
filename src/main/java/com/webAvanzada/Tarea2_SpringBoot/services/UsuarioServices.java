package com.webAvanzada.Tarea2_SpringBoot.services;

import com.webAvanzada.Tarea2_SpringBoot.entities.Usuario;
import com.webAvanzada.Tarea2_SpringBoot.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServices {
    @Autowired
    UsuarioRepository usuarioRepository;

    //CREATE AND UPDATE
    public void createOrUpdateUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }
    
    //GET ALL USUARIOS
    public List<Usuario> allUsuarios(){
        List<Usuario> usuariosList = new ArrayList<Usuario>();
        usuarioRepository.findAll().forEach(usuario -> usuariosList.add(usuario));
        return usuariosList;
    }

    //GET ONE SPECIFIC USUARIO BY ID
    public Usuario getUsuario(int idUsuario){
        for (Usuario user : allUsuarios()){
            if(user.getIdUsuario() == idUsuario){
                return user;
            }
        }
        return null;
    }

    //DELETE USUARIO
    public boolean deleteUsuario(int idUsuario){
        for (Usuario user : allUsuarios()){
            if(user.getIdUsuario() == idUsuario){
                usuarioRepository.delete(user);
                return true;
            }
        }
        return false;
    }

    //VERIFY USER
    public Usuario isRegistered(String username, String password){
        for (Usuario user : allUsuarios()){
            if(user.getUsername().equals(username)){
                if(user.getPassword().equals(password)) {
                    return user;
                }
            }
        }
        return null;
    }
}
