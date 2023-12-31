package com.ingesoft.bikemap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ingesoft.bikemap.dataAccess.RepositorioPunto_Interes;
import com.ingesoft.bikemap.dataAccess.RepositorioRuta;
import com.ingesoft.bikemap.dataAccess.RepositorioUsuario;
import com.ingesoft.bikemap.dominio.Usuario;
import com.ingesoft.bikemap.logic.ServicioUsuario;

import jakarta.transaction.Transactional;

@SpringBootTest
public class CUUsuarioTest {
    @Autowired
    ServicioUsuario servicio;

    @Autowired
    RepositorioUsuario repoUsuario;

    @Autowired
    RepositorioPunto_Interes repoPunto;

    @Autowired
    RepositorioRuta repoRuta;

    @Autowired
    JdbcTemplate simpleJdbcTemplate;

    @BeforeEach
    void resetear() {
        simpleJdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE;");


        repoPunto.deleteAll();
        repoRuta.deleteAll();
        repoUsuario.deleteAll();
    }

    @AfterEach
    void resetearOtraVez(){
        simpleJdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE;");
    }

    // CU Iniciar Sesión

    @Test
    @Transactional
    @Tag("Usuario")
    void IniciarSesionExitoso() throws Exception {
        try {
            // Arrange

            Usuario u = new Usuario();
            u.setNombreCompleto("prueba");
            u.setLogin("prueba");
            u.setContraseña("secret");
            u.setCorreoRecuperacion("prueba@gmail.com");
            repoUsuario.save(u);

            // Act

            servicio.IniciarSesion("prueba", "secret");

            // Assert}

        } catch (Exception e) {
            // Assert
            fail("El inicio de sesión no fue exitoso porque " + e.getMessage());
        }
    }

    @Test
    @Transactional
    @Tag("Usuario")
    void IniciarSesionUsuarioNoExiste() throws Exception {
        try {
            // Arrange

            // Act

            servicio.IniciarSesion("prueba", "prueba");

            // Assert

            fail("Se encontró un usuario que no había sido registrado");

        } catch (Exception e) {
            // Assert

        }
    }

    @Test
    @Transactional
    @Tag("Usuario")
    void IniciarSesionContraseñaIncorrecta() throws Exception {
        try {
            // Arrange

            Usuario u = new Usuario();
            u.setNombreCompleto("prueba");
            u.setLogin("prueba");
            u.setContraseña("secret");
            u.setCorreoRecuperacion("prueba@gmail.com");
            repoUsuario.save(u);

            // Act

            servicio.IniciarSesion("prueba", "secretito");

            // Assert

            fail("El sistema ingresó a un usuario con contraseña incorrecta");
        } catch (Exception e) {
            // Assert

        }
    }

    // CU Registrar Usuario

    @Test
    @Transactional
    @Tag("Usuario")
    @Tag("Crear")
    void RegistrarUsuarioExitoso() throws Exception {

        try {

            // Arrange

            // Act

            servicio.RegistrarUsuario(
                    "prueba",
                    "prueba",
                    "secret", // Todas las contraseñas de prueba serán secret
                    "secret",
                    "prueba@gmail.com",
                    "prueba@gmail.com");

            // Assert

            Usuario u = repoUsuario.findByLogin("prueba");

            assertEquals("prueba", u.getNombreCompleto(), "El sistema no guardó correctamente el nombre completo");
            assertEquals("secret", u.getContraseña(), "El sistema no guardó correctamente la contraseña");
            assertEquals("prueba@gmail.com", u.getCorreoRecuperacion(),
                    "El sistema no guardó correctamente el nombre completo");
            assertNotNull(u, "El objeto no se guardó correctamente");

        } catch (Exception e) {
            // Assert

            fail("El mp guardó al usuario de forma exitosa porque " + e.getMessage());
        }

    }

    @Test
    @Transactional
    @Tag("Usuario")
    @Tag("Crear")
    void probarRegistroNombreExistente() throws Exception {
        try {
            // Arrange

            Usuario u = new Usuario();

            u.setNombreCompleto("Jorge Vargas");
            u.setLogin("jorgeVargas");
            u.setContraseña("secret");
            u.setCorreoRecuperacion("info@paisasclub.co");

            repoUsuario.save(u);

            // Act

            servicio.RegistrarUsuario(
                    "prueba",
                    "jorgeVargas",
                    "secret",
                    "secret",
                    "prueba2@gmail.com",
                    "prueba2@gmail.com");

            // Assert

            fail("El sistema registró un usario con nombre de usuario existente");

        } catch (Exception e) {
            // Assert

        }
    }

    @Test
    @Transactional
    @Tag("Usuario")
    @Tag("Crear")
    void probarRegistroContraseniaCorta() throws Exception {
        try {
            // Arrange

            // Act

            servicio.RegistrarUsuario(
                    "prueba3",
                    "prueba3",
                    "sret",
                    "sret",
                    "prueba3@gmail.com",
                    "prueba3@gmail.com");

            // Assert

            fail("El sistema registró un usuario con contraseña inválida (menos de 6 caracteres)");

        } catch (Exception e) {
            // Assert

        }
    }

    @Test
    @Transactional
    @Tag("Usuario")
    @Tag("Crear")
    void probarRegistroContraseniaDiferente() throws Exception {
        try {
            // Arrange

            // Act

            servicio.RegistrarUsuario(
                    "prueba4",
                    "prueba4",
                    "secretito",
                    "sret",
                    "prueba4@gmail.com",
                    "prueba4@gmail.com");

            // Assert

            fail("El sistema registró un usaurio con contraseña de confirmacion que no coincide");

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    @Transactional
    @Tag("Usuario")
    @Tag("Crear")
    void probarRegistroCorreoSinSintaxisUno() throws Exception {
        try {

            // Arrange

            // Act

            servicio.RegistrarUsuario(
                    "prueba5",
                    "prueba5",
                    "secret",
                    "secret",
                    "pruebagmail.com",
                    "pruebagmail.com");

            // Assert

            fail("El sistema registra un usuario con correo inválido");

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    @Transactional
    @Tag("Usuario")
    @Tag("Crear")
    void probarRegistroCorreoSinSintaxisDos() throws Exception {

        try {

            // Arrange

            // Act

            servicio.RegistrarUsuario(
                    "prueba5",
                    "prueba5",
                    "secret", // Todas las contraseñas de prueba serán secret
                    "secret",
                    "prueba@gmail.",
                    "prueba@gmail.");

            // Assert

            fail("El sistema registra un usuario con correo inválido");

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    @Transactional
    @Tag("Usuario")
    @Tag("Crear")
    void probarRegistroCorreoExistente() throws Exception {
        try {
            // Arrange

            servicio.RegistrarUsuario(
                    "prueba7",
                    "prueba7",
                    "secret", // Todas las contraseñas de prueba serán secret
                    "secret",
                    "prueba@gmail.com",
                    "prueba@gmail.com");

            // Act

            servicio.RegistrarUsuario(
                    "Jorgito",
                    "giorgio",
                    "secret", // Todas las contraseñas de prueba serán secret
                    "secret",
                    "prueba@gmail.com",
                    "prueba@gmail.com");

            // Assert

            fail("El sistema registró un usuario con correo de recuperacion existente");

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    @Transactional
    @Tag("Usuario")
    @Tag("Crear")
    void probarRegsistroCorreosDiferentes() throws Exception {
        try {
            // Arrange

            // Act

            servicio.RegistrarUsuario(
                    "prueba8",
                    "prueba8",
                    "secret", // Todas las contraseñas de prueba serán secret
                    "secret",
                    "prueba8@gmail.com",
                    "prueba88@gmail.com");

            // Assert

            fail("El sistema registra un usuario con confirmacion de correo diferente");

        } catch (Exception e) {
            // Assert
        }
    }
}
