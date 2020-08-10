package com.webAvanzada.Tarea2_SpringBoot.repositories;

import com.webAvanzada.Tarea2_SpringBoot.entities.Alquiler;
import com.webAvanzada.Tarea2_SpringBoot.entities.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlquilerRepository extends CrudRepository<Alquiler, Integer> {

    @Query(value = "select * from Alquiler order by FECHA_ALQUILER desc", nativeQuery = true)
    List<Alquiler> getAllAlquileresPendientes();
}
