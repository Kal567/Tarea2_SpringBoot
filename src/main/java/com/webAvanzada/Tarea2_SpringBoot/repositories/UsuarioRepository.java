package com.webAvanzada.Tarea2_SpringBoot.repositories;

import com.webAvanzada.Tarea2_SpringBoot.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
}
