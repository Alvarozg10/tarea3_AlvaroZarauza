package com.luisdbb.tarea3AD2024base.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DossierArtisticoRepository

        extends MongoRepository<DossierArtistico, String> {

    DossierArtistico findByIdArtista(
            Long idArtista);
}