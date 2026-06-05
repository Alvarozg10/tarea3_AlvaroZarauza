package com.luisdbb.tarea3AD2024base.repositorios.objectdb;

import org.springframework.stereotype.Repository;

import com.luisdbb.tarea3AD2024base.objectdb.ResolucionIncidencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Repository
public class ResolucionIncidenciaRepository {

    private final EntityManagerFactory emf =

            Persistence.createEntityManagerFactory(
                    "objectdb");
    
	public List<ResolucionIncidencia> findByIncidenciaId(
	        Long incidenciaId) {

	    EntityManager em =
	            emf.createEntityManager();

	    try {

	        return em.createQuery(

	                "SELECT r FROM ResolucionIncidencia r "
	                + "WHERE r.incidencia.id = :id",

	                ResolucionIncidencia.class)

	                .setParameter(
	                        "id",
	                        incidenciaId)

	                .getResultList();

	    } finally {

	        em.close();
	    }
	}

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