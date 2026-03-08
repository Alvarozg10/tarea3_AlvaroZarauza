package com.luisdbb.tarea3AD2024base.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisdbb.tarea3AD2024base.modelo.Credenciales;
import com.luisdbb.tarea3AD2024base.repositorios.CredencialesRepository;

@Service
public class AuthService {

    @Autowired
    private CredencialesRepository credencialesRepository;

    public Credenciales login(String username, String password) {

        Credenciales credenciales = credencialesRepository.findByUsername(username);

        if (credenciales == null) {
            return null;
        }

        if (!credenciales.getPassword().equals(password)) {
            return null;
        }

        return credenciales;
    }

    public Credenciales buscarPorUsername(String username) {
        return credencialesRepository.findByUsername(username);
    }
}