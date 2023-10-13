package com.ingesoft.bikemap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ingesoft.bikemap.dataAccess.RepositorioUsuario;
import com.ingesoft.bikemap.dominio.Usuario;
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
            //Arrange

            repoUsuario.deleteAll();

            Usuario u = new Usuario();
            u.setNombreCompleto("prueba");
            u.setLogin("prueba");
            u.setContraseña("secret");
            u.setCorreoRecuperacion("prueba@gmail.com");
            repoUsuario.save(u);

            //Act

            servicio.IniciarSesion("prueba", "secret");

            //Assert
            
            
            
        } catch (Exception e) {
            //Assert
            fail();
        }
    }

    @Test
    void probarIniciarSesionUsuarioNoExiste() throws Exception{
        try {
            //Arrange

            repoUsuario.deleteAll();

            //Act

            servicio.IniciarSesion("prueba", "prueba");

            //Assert

            fail();
        } catch (Exception e) {
            //Assert
            
            
        }
    }

    @Test
    void probarIniciarSesionContraseñaIncorrecta() throws Exception{
        try {
            //Arrange

            repoUsuario.deleteAll();

            Usuario u = new Usuario();
            u.setNombreCompleto("prueba");
            u.setLogin("prueba");
            u.setContraseña("secret");
            u.setCorreoRecuperacion("prueba@gmail.com");
            repoUsuario.save(u);

            //Act

            servicio.IniciarSesion("prueba", "secretito");

            //Assert

            fail();
        } catch (Exception e) {
            //Assert

            
        }
    }

    
}
