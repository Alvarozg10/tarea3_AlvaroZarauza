package com.luisdbb.tarea3AD2024base.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luisdbb.tarea3AD2024base.modelo.Numero;
import com.luisdbb.tarea3AD2024base.modelo.Espectaculo;

import java.util.List;

@Repository
public interface NumeroRepository extends JpaRepository<Numero, Long> {

    boolean existsByEspectaculoAndOrden(Espectaculo espectaculo, int orden);
    long countByEspectaculo(Espectaculo espectaculo);

    List<Numero> findByEspectaculo(Espectaculo espectaculo);
}