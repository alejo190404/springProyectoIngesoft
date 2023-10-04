package com.ingesoft.bikemap.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ingesoft.bikemap.logic.ServicioUsuario;

@RestController
public class ApiUsuario {
    
    @Autowired
    private ServicioUsuario servicioUsuario;

    @PostMapping("/api/usuarios")
    public void crearUsuario(
        String nombreCompleto,
        String nombreUsuario,
        String contraseña,
        String confirmacionContraseña,
        String correoRecuperacion,
        String confirmacionCorreoRecuperacion ) throws Exception{


        servicioUsuario.RegistrarUsuario(nombreCompleto, nombreUsuario, contraseña, confirmacionContraseña, correoRecuperacion, confirmacionCorreoRecuperacion);

    }

}
