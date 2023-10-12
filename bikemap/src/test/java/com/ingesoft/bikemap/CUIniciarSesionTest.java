package com.ingesoft.bikemap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ingesoft.bikemap.dataAccess.RepositorioUsuario;
import com.ingesoft.bikemap.logic.ServicioUsuario;

@SpringBootTest
public class CUIniciarSesionTest {
    @Autowired
    ServicioUsuario servicio;

    @Autowired
    RepositorioUsuario repoUsuario;

    @Test
    void probarIniciarSesionExitoso() throws Exception{
        try {
            servicio.RegistrarUsuario("prueba",
                                "prueba",
                                "secret",
                                "secret",
                                "prueba@gmail.com",
                                "prueba@gmail.com");

            servicio.IniciarSesion("prueba", "secret");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void probarIniciarSesionUsuarioNoExiste() throws Exception{
        try {
            servicio.IniciarSesion("pruebaaa", "1290432");
            fail();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    void probarIniciarSesion
}
