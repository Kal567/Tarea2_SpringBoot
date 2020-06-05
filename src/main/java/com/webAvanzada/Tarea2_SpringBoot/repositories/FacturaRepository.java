package com.webAvanzada.Tarea2_SpringBoot.repositories;

import com.webAvanzada.Tarea2_SpringBoot.entities.Factura;
import org.springframework.data.repository.CrudRepository;

public interface FacturaRepository extends CrudRepository<Factura, Integer> {
}
