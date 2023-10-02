package com.ingesoft.bikemap.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingesoft.bikemap.dataAccess.RepositorioUsuario;

@Service
public class ServicioUsuario {
    
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    public void CrearUsuario(
        String nombreCompleto, //1
        String nombreUsuario, //2
        String contraseña, //4
        String confirmacionContraseña, //6
        String correoRecuperacion, //8
        String confirmacionCorreoRecuperacion /*11*/ ){

        //repositorioUsuario.findById(null)


    }

}
