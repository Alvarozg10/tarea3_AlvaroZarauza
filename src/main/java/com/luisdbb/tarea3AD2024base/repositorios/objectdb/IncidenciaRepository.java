package com.luisdbb.tarea3AD2024base.repositorios.objectdb;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.luisdbb.tarea3AD2024base.objectdb.Incidencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Repository
public class IncidenciaRepository {

    private final EntityManagerFactory emf =

            Persistence.createEntityManagerFactory(
                    "objectdb");

    public void save(Incidencia incidencia) {

        EntityManager em =
                emf.createEntityManager();

        try {

            em.getTransaction().begin();

            if (incidencia.getId() == null) {

                em.persist(incidencia);

            } else {

                em.merge(incidencia);
            }

            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();

            throw e;

        } finally {

            em.close();
        }
    }

    public Incidencia findById(Long id) {

        EntityManager em =
                emf.createEntityManager();

        try {

            return em.find(
                    Incidencia.class,
                    id);

        } finally {

            em.close();
        }
    }

    public List<Incidencia> findAll() {

        EntityManager em =
                emf.createEntityManager();

        try {

            return em.createQuery(

                    "SELECT i FROM Incidencia i",

                    Incidencia.class)

                    .getResultList();

        } finally {

            em.close();
        }
    }

    public List<Incidencia> findByResueltaFalse() {

        EntityManager em =
                emf.createEntityManager();

        try {

            return em.createQuery(

                    "SELECT i FROM Incidencia i WHERE i.resuelta = false",

                    Incidencia.class)

                    .getResultList();

        } finally {

            em.close();
        }
    }
    
    public List<Incidencia> buscarIncidencias(

            com.luisdbb.tarea3AD2024base.objectdb.TipoIncidencia tipo,

            Boolean resuelta,

            Long idEspectaculo,

            Long idNumero,

            LocalDateTime fechaInicio,

            LocalDateTime fechaFin) {

        EntityManager em =
                emf.createEntityManager();

        try {

            String jpql =
                    "SELECT i FROM Incidencia i WHERE 1=1";

            if (tipo != null) {

                jpql +=
                        " AND i.tipo = :tipo";
            }

            if (resuelta != null) {

                jpql +=
                        " AND i.resuelta = :resuelta";
            }

            if (idEspectaculo != null) {

                jpql +=
                        " AND i.idEspectaculo = :idEspectaculo";
            }

            if (idNumero != null) {

                jpql +=
                        " AND i.idNumero = :idNumero";
            }

            if (fechaInicio != null) {

                jpql +=
                        " AND i.fechaHora >= :fechaInicio";
            }

            if (fechaFin != null) {

                jpql +=
                        " AND i.fechaHora <= :fechaFin";
            }

            var query =

                    em.createQuery(
                            jpql,
                            Incidencia.class);

            if (tipo != null) {

                query.setParameter(
                        "tipo",
                        tipo);
            }

            if (resuelta != null) {

                query.setParameter(
                        "resuelta",
                        resuelta);
            }

            if (idEspectaculo != null) {

                query.setParameter(
                        "idEspectaculo",
                        idEspectaculo);
            }

            if (idNumero != null) {

                query.setParameter(
                        "idNumero",
                        idNumero);
            }

            if (fechaInicio != null) {

                query.setParameter(
                        "fechaInicio",
                        fechaInicio);
            }

            if (fechaFin != null) {

                query.setParameter(
                        "fechaFin",
                        fechaFin);
            }

            return query.getResultList();

        } finally {

            em.close();
        }
    }
}