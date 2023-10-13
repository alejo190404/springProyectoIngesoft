package com.ingesoft.bikemap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ingesoft.bikemap.dataAccess.RepositorioUsuario;
import com.ingesoft.bikemap.dominio.Usuario;
import com.ingesoft.bikemap.logic.ServicioUsuario;

@SpringBootTest
public class CUUsuarioTest {
    @Autowired
    ServicioUsuario servicio;

    @Autowired
    RepositorioUsuario repoUsuario;

    @BeforeEach
    void resetear(){
        repoUsuario.deleteAll();
    }

    //CU Iniciar Sesión

    @Test
    void probarIniciarSesionExitoso() throws Exception{
        try {
            //Arrange

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

    //CU Registrar Usuario

    @Test
    void probarRegistro() throws Exception{
        
        try {
            servicio.RegistrarUsuario("prueba",
                                "prueba",
                                "secret", //Todas las contraseñas de prueba serán secret
                                "secret",
                                "prueba@gmail.com",
                                "prueba@gmail.com");
        
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    void probarRegistroNombreExistente() throws Exception {
        try {
            servicio.RegistrarUsuario("prueba",
                                    "prueba",
                                    "secret",
                                    "secret",
                                    "prueba2@gmail.com",
                                    "prueba2@gmail.com");
            fail();
        } catch (Exception e) {
            
        }
    }

    @Test
    void probarRegistroContraseniaCorta() throws Exception{
        try {
            servicio.RegistrarUsuario("prueba3",
                                    "prueba3",
                                    "sret",
                                    "sret",
                                    "prueba3@gmail.com",
                                    "prueba3@gmail.com");
            fail();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    void probarRegistroContraseniaDiferente() throws Exception{
        try {
            servicio.RegistrarUsuario("prueba4",
                                    "prueba4",
                                    "secretito",
                                    "sret",
                                    "prueba4@gmail.com",
                                    "prueba4@gmail.com");
            fail();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    void probarRegistroCorreoSinSintaxisUno() throws Exception{
        try {
            servicio.RegistrarUsuario("prueba5",
                                "prueba5",
                                "secret", //Todas las contraseñas de prueba serán secret
                                "secret",
                                "pruebagmail.com",
                                "pruebagmail.com");
            fail();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /*@Test
    void probarRegistroCorreoSinSintaxisDos() throws Exception{
        try {
            servicio.RegistrarUsuario("prueba5",
                                "prueba5",
                                "secret", //Todas las contraseñas de prueba serán secret
                                "secret",
                                "prueba@gmail.",
                                "prueba@gmail.");
            fail();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
*/
    @Test
    void probarRegistroCorreoExistente() throws Exception{
        try {
            servicio.RegistrarUsuario("prueba7",
                                "prueba7",
                                "secret", //Todas las contraseñas de prueba serán secret
                                "secret",
                                "prueba@gmail.com",
                                "prueba@gmail.com");
            fail();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    void probarRegsistroCorreosDiferentes() throws Exception{
        try {
            servicio.RegistrarUsuario("prueba8",
                                "prueba8",
                                "secret", //Todas las contraseñas de prueba serán secret
                                "secret",
                                "prueba8@gmail.com",
                                "prueba88@gmail.com");
            fail();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    void probarRegistroExistente() throws Exception{
        try {
            servicio.RegistrarUsuario("prueba9",
                                "prueba9",
                                "secret", //Todas las contraseñas de prueba serán secret
                                "secret",
                                "prueba9@gmail.com",
                                "prueba9@gmail.com");

            Usuario u = repoUsuario.findByLogin("prueba9");
            
            if (u == null){
                fail();
            }

        } catch (Exception e) {
            fail();
        }
    }
}
