package com.webAvanzada.Tarea2_SpringBoot.repositories;

import com.webAvanzada.Tarea2_SpringBoot.entities.Familia;
import org.springframework.data.repository.CrudRepository;

public interface FamiliaRepository extends CrudRepository<Familia, Integer> {
}
