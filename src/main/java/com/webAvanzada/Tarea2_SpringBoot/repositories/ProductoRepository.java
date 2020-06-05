package com.webAvanzada.Tarea2_SpringBoot.repositories;

import com.webAvanzada.Tarea2_SpringBoot.entities.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto, Integer> {
}
