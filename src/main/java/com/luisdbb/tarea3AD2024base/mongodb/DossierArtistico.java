package com.luisdbb.tarea3AD2024base.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dossiers")
public class DossierArtistico {

    @Id
    private String id;

    private Long idArtista;

    private String nombre;

    private String nacionalidad;

    private String email;

    private List<String> especialidades =
            new ArrayList<>();

    private String apodo;

    private List<TrayectoriaDossier> trayectoria = 
    		new ArrayList<>();

    private List<EvaluacionDossier> evaluaciones =
    		new ArrayList<>();

    private List<ObservacionDossier> observaciones =
    		new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(Long idArtista) {
        this.idArtista = idArtista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public List<String> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<String> especialidades) {
		this.especialidades = especialidades;
	}

	public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

	public List<TrayectoriaDossier> getTrayectoria() {
		return trayectoria;
	}

	public void setTrayectoria(List<TrayectoriaDossier> trayectoria) {
		this.trayectoria = trayectoria;
	}

	public List<EvaluacionDossier> getEvaluaciones() {
		return evaluaciones;
	}

	public void setEvaluaciones(List<EvaluacionDossier> evaluaciones) {
		this.evaluaciones = evaluaciones;
	}

	public List<ObservacionDossier> getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(List<ObservacionDossier> observaciones) {
		this.observaciones = observaciones;
	}

    
}