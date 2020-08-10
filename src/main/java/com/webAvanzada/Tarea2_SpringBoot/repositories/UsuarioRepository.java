package com.webAvanzada.Tarea2_SpringBoot.repositories;

import com.webAvanzada.Tarea2_SpringBoot.entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    /*@Modifying
    @Query(value = "update Equip u set u.stock = ?1 where u.id = ?2", nativeQuery = true)
    void setStock(Integer stock, Integer id);*/

    /*@Query("SELECT u FROM Usuario u WHERE u.username = :username")
    public Usuario getUserByUsername(@Param("username") String username);*/
}
