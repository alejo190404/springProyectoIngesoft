package com.ingesoft.bikemap;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ingesoft.bikemap.dataAccess.RepositorioCalificacion_Ruta;
import com.ingesoft.bikemap.dataAccess.RepositorioPunto_Interes;
import com.ingesoft.bikemap.dataAccess.RepositorioRuta;
import com.ingesoft.bikemap.dataAccess.RepositorioUsuario;
import com.ingesoft.bikemap.dominio.Calificacion_Ruta;
import com.ingesoft.bikemap.dominio.Ruta;
import com.ingesoft.bikemap.dominio.Usuario;
import com.ingesoft.bikemap.logic.ServicioRuta;

@SpringBootTest
public class CURutaTest {

    @Autowired
    ServicioRuta servicio;

    @Autowired
    RepositorioRuta repositorioRuta;

    @Autowired
    RepositorioUsuario repositorioUsuario;

    @Autowired
    RepositorioCalificacion_Ruta repositorioCalificacion_Ruta;

    @Autowired
    RepositorioPunto_Interes repoPunto;

    @Autowired
    JdbcTemplate simpleJdbcTemplate;
    

    @BeforeEach
    void resetear() {
        simpleJdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE;");
        
        repoPunto.deleteAll();
        repositorioUsuario.deleteAll();
        repositorioRuta.deleteAll();
        repositorioCalificacion_Ruta.deleteAll();

    }

    @AfterEach
    void resetearOtraVez(){
        simpleJdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE;");
    }

    // CU Crear Ruta

    @Test
    @Tag("Crear")
    @Tag("Ruta")
    void CrearRutaExistoso() throws Exception {
        try {
            // Arrange

            Usuario u = new Usuario();

            u.setNombreCompleto("Alvaro Leyva");
            u.setLogin("Prueba");
            u.setContraseña("secret");
            u.setCorreoRecuperacion("alvaro@gmail.com");

            repositorioUsuario.save(u);

            Date fecha = Date.valueOf("2023-10-17");
            String p1lt = "4";
            String p1lg = "6";
            String p2lt = "5";
            String p2lg = "7";
            String p3lt = "6";
            String p3lg = "8";
            
            List<String> puntos = new ArrayList<>();
            puntos.add(p1lt);
            puntos.add(p1lg);
            puntos.add(p2lt);
            puntos.add(p2lg);
            puntos.add(p3lt);
            puntos.add(p3lg);

            // Act

            servicio.CrearRuta(
                    "Unviersidades",
                    "Recorrido por las principales universidades de Bogotá",
                    puntos,
                    fecha,
                    "Prueba");

            // Assert

        } catch (Exception e) {
            // Assert

            fail("El sistema registró una ruta que no debia porque " + e.getMessage());
        }
    }

    @Test
    @Tag("Crear")
    @Tag("Ruta")
    void CrearRutaCoordenadasLatitudIncorrecta1() throws Exception {
        try {
            // Arrange

            Usuario u = new Usuario();

            u.setNombreCompleto("Alvaro Leyva");
            u.setLogin("Prueba");
            u.setContraseña("secret");
            u.setCorreoRecuperacion("alvaro@gmail.com");

            repositorioUsuario.save(u);

            Date fecha = Date.valueOf("2023-10-17");
            String p1lt = "4";
            String p1lg = "6";
            String p2lt = "5";
            String p2lg = "7";
            String p3lt = "91";
            String p3lg = "8";
            
            List<String> puntos = new ArrayList<>();
            puntos.add(p1lt);
            puntos.add(p1lg);
            puntos.add(p2lt);
            puntos.add(p2lg);
            puntos.add(p3lt);
            puntos.add(p3lg);

            // Act

            servicio.CrearRuta(
                    "Unviersidades",
                    "Recorrido por las principales universidades de Bogotá",
                    puntos,
                    fecha,
                    "Prueba");

            // Assert

            fail("El sistema registró una ruta con latitud inválida");

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    @Tag("Crear")
    @Tag("Ruta")
    void CrearRutaCoordenadasLatitudIncorrecta2() throws Exception {
        try {
            // Arrange

            Usuario u = new Usuario();

            u.setNombreCompleto("Alvaro Leyva");
            u.setLogin("Prueba");
            u.setContraseña("secret");
            u.setCorreoRecuperacion("alvaro@gmail.com");

            repositorioUsuario.save(u);

            Date fecha = Date.valueOf("2023-10-17");

            String p1lt = "4";
            String p1lg = "6";
            String p2lt = "5";
            String p2lg = "7";
            String p3lt = "-100";
            String p3lg = "8";
            
            List<String> puntos = new ArrayList<>();
            puntos.add(p1lt);
            puntos.add(p1lg);
            puntos.add(p2lt);
            puntos.add(p2lg);
            puntos.add(p3lt);
            puntos.add(p3lg);

            // Act

            servicio.CrearRuta(
                    "Unviersidades",
                    "Recorrido por las principales universidades de Bogotá",
                    puntos,
                    fecha,
                    "Prueba");

            // Assert

            fail("El sistema registró una ruta con latitud inválida");

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    @Tag("Crear")
    @Tag("Ruta")
    void CrearRutaCoordenadasLongitudIncorrecta1() throws Exception {
        try {

            // Arrange

            Usuario u = new Usuario();

            u.setNombreCompleto("Alvaro Leyva");
            u.setLogin("Prueba");
            u.setContraseña("secret");
            u.setCorreoRecuperacion("alvaro@gmail.com");

            repositorioUsuario.save(u);

            Date fecha = Date.valueOf("2023-10-17");

            String p1lt = "4";
            String p1lg = "6";
            String p2lt = "5";
            String p2lg = "7";
            String p3lt = "6";
            String p3lg = "-195";
            
            List<String> puntos = new ArrayList<>();
            puntos.add(p1lt);
            puntos.add(p1lg);
            puntos.add(p2lt);
            puntos.add(p2lg);
            puntos.add(p3lt);
            puntos.add(p3lg);

            // Act

            servicio.CrearRuta(
                    "Unviersidades",
                    "Recorrido por las principales universidades de Bogotá",
                    puntos,
                    fecha,
                    "Prueba");

            // Assert

            fail("El sistema registró una ruta con longitud inválida");

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    @Tag("Crear")
    @Tag("Ruta")
    void CrearRutaCoordenadasLongitudIncorrecta2() throws Exception {
        try {

            // Arrange

            Usuario u = new Usuario();

            u.setNombreCompleto("Alvaro Leyva");
            u.setLogin("Prueba");
            u.setContraseña("secret");
            u.setCorreoRecuperacion("alvaro@gmail.com");

            repositorioUsuario.save(u);

            Date fecha = Date.valueOf("2023-10-17");

            String p1lt = "4";
            String p1lg = "6";
            String p2lt = "5";
            String p2lg = "7";
            String p3lt = "6";
            String p3lg = "235";
            
            List<String> puntos = new ArrayList<>();
            puntos.add(p1lt);
            puntos.add(p1lg);
            puntos.add(p2lt);
            puntos.add(p2lg);
            puntos.add(p3lt);
            puntos.add(p3lg);

            // Act

            servicio.CrearRuta(
                    "Unviersidades",
                    "Recorrido por las principales universidades de Bogotá",
                    puntos,
                    fecha,
                    "Prueba");

            // Assert

            fail("El sistema registró una ruta con longitud inválida");

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    @Tag("Crear")
    @Tag("Ruta")
    void CrearRutaNombreExistente() throws Exception {
        try {
            // Arrange

            Usuario u = new Usuario();

            u.setNombreCompleto("Alvaro Leyva");
            u.setLogin("Prueba");
            u.setContraseña("secret");
            u.setCorreoRecuperacion("alvaro@gmail.com");

            repositorioUsuario.save(u);

            Usuario uOtro = new Usuario();

            uOtro.setNombreCompleto("Karim Benzema");
            uOtro.setLogin("parcero");
            uOtro.setContraseña("secret");
            uOtro.setCorreoRecuperacion("parcerooos@gmail.com");

            repositorioUsuario.save(uOtro);

            Ruta r = new Ruta();

            r.setNombre("Universidades");
            r.setDescripcion("Ruta por las principales universidades");
            r.setCalificacionPromedio(Float.parseFloat("0.0"));

            Date fecha = Date.valueOf("2023-10-17");

            String p1lt = "4";
            String p1lg = "6";
            String p2lt = "5";
            String p2lg = "7";
            String p3lt = "6";
            String p3lg = "8";
            
            List<String> puntos = new ArrayList<>();
            puntos.add(p1lt);
            puntos.add(p1lg);
            puntos.add(p2lt);
            puntos.add(p2lg);
            puntos.add(p3lt);
            puntos.add(p3lg);

            //r.setPuntos(puntos);
            r.setFechaCreacion(fecha);
            r.setCreador(u);
            
            repositorioRuta.save(r);

            // Act

            servicio.CrearRuta(
                    "Universidades",
                    "Otro recorrido",
                    puntos,
                    fecha,
                    "parcero");

            // Assert

            fail("El sistema creó una ruta con nombre repetido");

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    @Tag("Crear")
    @Tag("Ruta")
    void CrearRutaNombreLargo() throws Exception {
        try {
            // Arrange

            Usuario u = new Usuario();

            u.setNombreCompleto("Alvaro Leyva");
            u.setLogin("Prueba");
            u.setContraseña("secret");
            u.setCorreoRecuperacion("alvaro@gmail.com");

            repositorioUsuario.save(u);

            Date fecha = Date.valueOf("2023-10-17");
            String p1lt = "4";
            String p1lg = "6";
            String p2lt = "5";
            String p2lg = "7";
            String p3lt = "6";
            String p3lg = "8";
            
            List<String> puntos = new ArrayList<>();
            puntos.add(p1lt);
            puntos.add(p1lg);
            puntos.add(p2lt);
            puntos.add(p2lg);
            puntos.add(p3lt);
            puntos.add(p3lg);

            // Act

            servicio.CrearRuta(
                    "Unviersidades de bogota a traves de una bicicleta",
                    "Recorrido por las principales universidades de Bogotá",
                    puntos,
                    fecha,
                    "Prueba");

            // Assert

            fail("El sistema creó una ruta con nombre inválido");

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    @Tag("Crear")
    @Tag("Ruta")
    void CrearRutaDescripcionVacia() throws Exception {
        try {
            // Arrange

            Usuario u = new Usuario();

            u.setNombreCompleto("Alvaro Leyva");
            u.setLogin("Prueba");
            u.setContraseña("secret");
            u.setCorreoRecuperacion("alvaro@gmail.com");

            repositorioUsuario.save(u);

            Date fecha = Date.valueOf("2023-10-17");
            String p1lt = "4";
            String p1lg = "6";
            String p2lt = "5";
            String p2lg = "7";
            String p3lt = "6";
            String p3lg = "8";
            
            List<String> puntos = new ArrayList<>();
            puntos.add(p1lt);
            puntos.add(p1lg);
            puntos.add(p2lt);
            puntos.add(p2lg);
            puntos.add(p3lt);
            puntos.add(p3lg);

            // Act

            servicio.CrearRuta(
                    "Unviersidades",
                    "",
                    puntos,
                    fecha,
                    "Prueba");

            // Assert

            Ruta prueba = repositorioRuta.findByNombre("Universidades");

            if (prueba.getDescripcion() != "Sin descripción"){
                fail("El sistema guardó una ruta con descripción vacía de forma errónea");
            }

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    @Tag("Crear")
    @Tag("Ruta")
    void CrearRutaDescripcionLarga() throws Exception {
        try {
            // Arrange

            Usuario u = new Usuario();

            u.setNombreCompleto("Alvaro Leyva");
            u.setLogin("Prueba");
            u.setContraseña("secret");
            u.setCorreoRecuperacion("alvaro@gmail.com");

            repositorioUsuario.save(u);

            Date fecha = Date.valueOf("2023-10-17");
            String p1lt = "4";
            String p1lg = "6";
            String p2lt = "5";
            String p2lg = "7";
            String p3lt = "6";
            String p3lg = "8";
            
            List<String> puntos = new ArrayList<>();
            puntos.add(p1lt);
            puntos.add(p1lg);
            puntos.add(p2lt);
            puntos.add(p2lg);
            puntos.add(p3lt);
            puntos.add(p3lg);

            String texto1000caracteres = "Este es un cuento de más de 1000 caracteres. Ernesto Gurrián se escondió tras el coche. Joaquín a su lado empuñó el arma reglamentaria. Desde allí podían ver la puerta abierta por donde saldrían. Los de dentro se habían cargado, hace tres días a Tomás Ojibe un compañero encantador con una hija de meses, tanto Joaquín como Ernesto habían estado en el bautizo. El coordinador de la operación lo dijo claramente en la reunión previa: “en caso de duda disparen, estos hijos de puta no se nos pueden escapar”. Joaquín oía su propio sobrealiento, sentía la boca seca, tenía ganas de ver la cara de mierda de aquellos tipejos a los que no conocía ni en fotos. Se fue abriendo la puerta. Ernesto tenía mejor ángulo y desde allí, fue el primero en ver y reconocer los zapatos de la chica. Joaquín le miró de reojo. Vio como de repente le empezó a temblar la mano aferrada a la pistola. Pasaron tres segundos. Vio sus ojos encharcados con una pena de padre pero una angustia de ira. Vio a Ojibe con su hija en brazos junto al baptisterio. Vio a su hija, a la suya en la calle. Joaquín grito “!Alto policía tirad las armas!” luego tres disparos. “¿Qué haces Ernesto?” le gritó. Solo murió la chica y Ernesto vivió muerto para siempre.";

            // Act

            servicio.CrearRuta(
                    "Unviersidades",
                    texto1000caracteres,
                    puntos,
                    fecha,
                    "Prueba");

            // Assert

            fail("El sistema registró una ruta con descripcion de más de 1000 caracteres");

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    @Tag("Crear")
    @Tag("Ruta")
    void CrearRutaUsuarioNoExiste() throws Exception {
        try {
            // Arrange

            Date fecha = Date.valueOf("2023-10-17");
            String p1lt = "4";
            String p1lg = "6";
            String p2lt = "5";
            String p2lg = "7";
            String p3lt = "6";
            String p3lg = "8";
            
            List<String> puntos = new ArrayList<>();
            puntos.add(p1lt);
            puntos.add(p1lg);
            puntos.add(p2lt);
            puntos.add(p2lg);
            puntos.add(p3lt);
            puntos.add(p3lg);

            // Act

            servicio.CrearRuta(
                    "Unviersidades",
                    "Recorrido por las principales universidades de Bogotá",
                    puntos,
                    fecha,
                    "Prueba");

            // Assert

            fail("El sistema registró una ruta con un usuario que no existe");

        } catch (Exception e) {
            // Assert
        }
    }

    // CU Calificar Ruta

    @Test
    @Tag("Calificar")
    @Tag("Ruta")
    void CalificarRutaExitoso() throws Exception {
        try {
            // Arrange

            Usuario u = new Usuario();

            u.setNombreCompleto("Alvaro Leyva");
            u.setLogin("Prueba");
            u.setContraseña("secret");
            u.setCorreoRecuperacion("alvaro@gmail.com");

            repositorioUsuario.save(u);

            Usuario uOtro = new Usuario();

            uOtro.setNombreCompleto("Karim Benzema");
            uOtro.setLogin("parcero");
            uOtro.setContraseña("secret");
            uOtro.setCorreoRecuperacion("parcerooos@gmail.com");

            repositorioUsuario.save(uOtro);

            Ruta r = new Ruta();

            r.setNombre("Universidades");
            r.setDescripcion("Ruta por las principales universidades");
            r.setCalificacionPromedio(Float.parseFloat("4.3"));

            Date fecha = Date.valueOf("2023-10-17");
            String p1lt = "4";
            String p1lg = "6";
            String p2lt = "5";
            String p2lg = "7";
            String p3lt = "6";
            String p3lg = "8";
            
            List<String> puntos = new ArrayList<>();
            puntos.add(p1lt);
            puntos.add(p1lg);
            puntos.add(p2lt);
            puntos.add(p2lg);
            puntos.add(p3lt);
            puntos.add(p3lg);

            r.setPuntos(puntos);
            r.setFechaCreacion(fecha);
            r.setCreador(u);

            repositorioRuta.save(r);

            // Act

            servicio.CalificarRuta(
                "Universidades",
                "3",
                "Buenas ubicaciones, sin embargo, el orden no ayuda a navegarla bien",
                "parcero"
            );

            // Assert

        } catch (Exception e) {
            // Assert
            fail("El sistema no calificó apropiadamente la ruta");
        }
    }

    @Test
    @Tag("Calificar")
    @Tag("Ruta")
    void CalificarRutaReseñaVacia() throws Exception {
        try {
            // Arrange

            Usuario u = new Usuario();

            u.setNombreCompleto("Alvaro Leyva");
            u.setLogin("Prueba");
            u.setContraseña("secret");
            u.setCorreoRecuperacion("alvaro@gmail.com");

            repositorioUsuario.save(u);

            Usuario uOtro = new Usuario();

            uOtro.setNombreCompleto("Karim Benzema");
            uOtro.setLogin("parcero");
            uOtro.setContraseña("secret");
            uOtro.setCorreoRecuperacion("parcerooos@gmail.com");

            repositorioUsuario.save(uOtro);

            Ruta r = new Ruta();

            r.setNombre("Universidades");
            r.setDescripcion("Ruta por las principales universidades");
            r.setCalificacionPromedio(Float.parseFloat("4.3"));

            Date fecha = Date.valueOf("2023-10-17");
            String p1lt = "4";
            String p1lg = "6";
            String p2lt = "5";
            String p2lg = "7";
            String p3lt = "6";
            String p3lg = "8";
            
            List<String> puntos = new ArrayList<>();
            puntos.add(p1lt);
            puntos.add(p1lg);
            puntos.add(p2lt);
            puntos.add(p2lg);
            puntos.add(p3lt);
            puntos.add(p3lg);

            r.setPuntos(puntos);
            r.setFechaCreacion(fecha);
            r.setCreador(u);

            repositorioRuta.save(r);

            // Act

            servicio.CalificarRuta(
                "Universidades",
                "3",
                "",
                "parcero"
            );

            // Assert

            List<Calificacion_Ruta> crs = repositorioCalificacion_Ruta.findByCreador_Login("parcero");
            for (Calificacion_Ruta cali: crs){
                if (cali.getRutaCalificada().getNombre() == "Universidades"){
                    if (cali.getReseña() != "Sin reseña"){
                        fail("El sistema registró erróneamente la reseña de una calificación porque quedo como " + cali.getReseña());
                    }
                }
            }

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    @Tag("Calificar")
    @Tag("Ruta")
    void CalificarRutaReseñaLarga() throws Exception {
        try {
            // Arrange

            Usuario u = new Usuario();

            u.setNombreCompleto("Alvaro Leyva");
            u.setLogin("Prueba");
            u.setContraseña("secret");
            u.setCorreoRecuperacion("alvaro@gmail.com");

            repositorioUsuario.save(u);

            Usuario uOtro = new Usuario();

            uOtro.setNombreCompleto("Karim Benzema");
            uOtro.setLogin("parcero");
            uOtro.setContraseña("secret");
            uOtro.setCorreoRecuperacion("parcerooos@gmail.com");

            repositorioUsuario.save(uOtro);

            Ruta r = new Ruta();

            r.setNombre("Universidades");
            r.setDescripcion("Ruta por las principales universidades");
            r.setCalificacionPromedio(Float.parseFloat("4.3"));

            Date fecha = Date.valueOf("2023-10-17");
            String p1lt = "4";
            String p1lg = "6";
            String p2lt = "5";
            String p2lg = "7";
            String p3lt = "6";
            String p3lg = "8";
            
            List<String> puntos = new ArrayList<>();
            puntos.add(p1lt);
            puntos.add(p1lg);
            puntos.add(p2lt);
            puntos.add(p2lg);
            puntos.add(p3lt);
            puntos.add(p3lg);

            r.setPuntos(puntos);
            r.setFechaCreacion(fecha);
            r.setCreador(u);

            repositorioRuta.save(r);

            String texto1000caracteres = "Este es un cuento de más de 1000 caracteres. Ernesto Gurrián se escondió tras el coche. Joaquín a su lado empuñó el arma reglamentaria. Desde allí podían ver la puerta abierta por donde saldrían. Los de dentro se habían cargado, hace tres días a Tomás Ojibe un compañero encantador con una hija de meses, tanto Joaquín como Ernesto habían estado en el bautizo. El coordinador de la operación lo dijo claramente en la reunión previa: “en caso de duda disparen, estos hijos de puta no se nos pueden escapar”. Joaquín oía su propio sobrealiento, sentía la boca seca, tenía ganas de ver la cara de mierda de aquellos tipejos a los que no conocía ni en fotos. Se fue abriendo la puerta. Ernesto tenía mejor ángulo y desde allí, fue el primero en ver y reconocer los zapatos de la chica. Joaquín le miró de reojo. Vio como de repente le empezó a temblar la mano aferrada a la pistola. Pasaron tres segundos. Vio sus ojos encharcados con una pena de padre pero una angustia de ira. Vio a Ojibe con su hija en brazos junto al baptisterio. Vio a su hija, a la suya en la calle. Joaquín grito “!Alto policía tirad las armas!” luego tres disparos. “¿Qué haces Ernesto?” le gritó. Solo murió la chica y Ernesto vivió muerto para siempre.";

            // Act

            servicio.CalificarRuta(
                "Universidades",
                "3",
                texto1000caracteres,
                "parcero"
            );

            // Assert

            fail("El sistema registró una calificación con reseña de más de 1000 caracteres");

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    @Tag("Calificar")
    @Tag("Ruta")
    void CalificarRutaCalificacionExistente() throws Exception {
        try {
            // Arrange

            Usuario u = new Usuario();

            u.setNombreCompleto("Alvaro Leyva");
            u.setLogin("Prueba");
            u.setContraseña("secret");
            u.setCorreoRecuperacion("alvaro@gmail.com");

            repositorioUsuario.save(u);

            Usuario uOtro = new Usuario();

            uOtro.setNombreCompleto("Karim Benzema");
            uOtro.setLogin("parcero");
            uOtro.setContraseña("secret");
            uOtro.setCorreoRecuperacion("parcerooos@gmail.com");

            repositorioUsuario.save(uOtro);

            Ruta r = new Ruta();

            r.setNombre("Universidades");
            r.setDescripcion("Ruta por las principales universidades");
            r.setCalificacionPromedio(Float.parseFloat("4.3"));

            Date fecha = Date.valueOf("2023-10-17");
            String p1lt = "4";
            String p1lg = "6";
            String p2lt = "5";
            String p2lg = "7";
            String p3lt = "6";
            String p3lg = "8";
            
            List<String> puntos = new ArrayList<>();
            puntos.add(p1lt);
            puntos.add(p1lg);
            puntos.add(p2lt);
            puntos.add(p2lg);
            puntos.add(p3lt);
            puntos.add(p3lg);

            r.setPuntos(puntos);
            r.setFechaCreacion(fecha);
            r.setCreador(u);

            repositorioRuta.save(r);

            Calificacion_Ruta cr = new Calificacion_Ruta();

            cr.setReseña("Buena ruta crack");
            cr.setCalificacion(Short.parseShort("4"));
            cr.setCreador(uOtro);
            cr.setRutaCalificada(r);

            // Act

            String reseña = "No me gusta tanto la ruta";

            servicio.CalificarRuta(
                "Universidades",
                "3",
                reseña,
                "parcero"
            );

            // Assert

            List<Calificacion_Ruta> crs = repositorioCalificacion_Ruta.findByCreador_Login("parcero");
            for (Calificacion_Ruta cali: crs){
                if (cali.getRutaCalificada().getNombre() == "Universidades"){
                    if (cali.getReseña() != reseña){
                        fail("El sistema registró erróneamente la nueva reseña de un usuario");
                    }
                }
            }

        } catch (Exception e) {
            // Assert
            fail("El sistema arrojó una excepción cuando no debia");
        }
    }

}
