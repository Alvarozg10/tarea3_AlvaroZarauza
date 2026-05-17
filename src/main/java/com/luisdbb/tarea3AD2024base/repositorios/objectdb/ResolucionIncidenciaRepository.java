package com.luisdbb.tarea3AD2024base.repositorios.objectdb;

import org.springframework.stereotype.Repository;

import com.luisdbb.tarea3AD2024base.objectdb.ResolucionIncidencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Repository
public class ResolucionIncidenciaRepository {

    private final EntityManagerFactory emf =

            Persistence.createEntityManagerFactory(
                    "objectdb");

    public void save(
            ResolucionIncidencia resolucion) {

        EntityManager em =
                emf.createEntityManager();

        try {

            em.getTransaction().begin();

            if (resolucion.getId() == null) {

                em.persist(resolucion);

            } else {

                em.merge(resolucion);
            }

            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();

            throw e;

        } finally {

            em.close();
        }
    }
}