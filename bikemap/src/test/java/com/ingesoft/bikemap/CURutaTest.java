package com.ingesoft.bikemap;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ingesoft.bikemap.dataAccess.RepositorioCalificacion_Ruta;
import com.ingesoft.bikemap.dataAccess.RepositorioRuta;
import com.ingesoft.bikemap.dataAccess.RepositorioUsuario;
import com.ingesoft.bikemap.dominio.Calificacion_Ruta;
import com.ingesoft.bikemap.dominio.Ruta;
import com.ingesoft.bikemap.dominio.Usuario;
import com.ingesoft.bikemap.logic.ServicioRuta;

import javafx.util.Pair;

@SpringBootTest
public class CURutaTest {

    @Autowired
    ServicioRuta servicio;
    @Autowired
    private RepositorioRuta repositorioRuta;
    @Autowired
    private RepositorioUsuario repositorioUsuario;
    @Autowired
    private RepositorioCalificacion_Ruta repositorioCalificacion_Ruta;

    @BeforeEach
    void resetear() {
        repositorioRuta.deleteAll();
        repositorioUsuario.deleteAll();
        repositorioCalificacion_Ruta.deleteAll();
    }

    // CU Crear Ruta

    @Test
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
            Pair<String, String> p1 = new Pair<String, String>("4", "6");
            Pair<String, String> p2 = new Pair<String, String>("5", "7");
            Pair<String, String> p3 = new Pair<String, String>("6", "8");
            List<Pair<String, String>> puntos = new ArrayList<>();
            puntos.add(0, p1);
            puntos.add(1, p2);
            puntos.add(2, p3);

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

            fail("El sistema no registró un usuario que debió");
        }
    }

    @Test
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
            Pair<String, String> p1 = new Pair<String, String>("4", "6");
            Pair<String, String> p2 = new Pair<String, String>("5", "7");
            Pair<String, String> p3 = new Pair<String, String>("91", "8");
            List<Pair<String, String>> puntos = new ArrayList<>();
            puntos.add(0, p1);
            puntos.add(1, p2);
            puntos.add(2, p3);

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
            Pair<String, String> p1 = new Pair<String, String>("4", "6");
            Pair<String, String> p2 = new Pair<String, String>("5", "7");
            Pair<String, String> p3 = new Pair<String, String>("-100", "8");
            List<Pair<String, String>> puntos = new ArrayList<>();
            puntos.add(0, p1);
            puntos.add(1, p2);
            puntos.add(2, p3);

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
            Pair<String, String> p1 = new Pair<String, String>("4", "6");
            Pair<String, String> p2 = new Pair<String, String>("5", "7");
            Pair<String, String> p3 = new Pair<String, String>("6", "-195");
            List<Pair<String, String>> puntos = new ArrayList<>();
            puntos.add(0, p1);
            puntos.add(1, p2);
            puntos.add(2, p3);

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
            Pair<String, String> p1 = new Pair<String, String>("4", "6");
            Pair<String, String> p2 = new Pair<String, String>("5", "7");
            Pair<String, String> p3 = new Pair<String, String>("6", "235");
            List<Pair<String, String>> puntos = new ArrayList<>();
            puntos.add(0, p1);
            puntos.add(1, p2);
            puntos.add(2, p3);

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
            Pair<String, String> p1 = new Pair<String, String>("4", "6");
            Pair<String, String> p2 = new Pair<String, String>("5", "7");
            Pair<String, String> p3 = new Pair<String, String>("6", "8");
            List<Pair<String, String>> puntos = new ArrayList<>();
            puntos.add(0, p1);
            puntos.add(1, p2);
            puntos.add(2, p3);

            r.setPuntos(puntos);
            r.setFechaCreacion(fecha);
            r.setCreador(u);
            
            repositorioRuta.save(r);

            // Act

            Pair<String, String> p4 = new Pair<String, String>("6", "8");
            puntos.add(3, p4);

            servicio.CrearRuta(
                    "Unviersidades",
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
            Pair<String, String> p1 = new Pair<String, String>("4", "6");
            Pair<String, String> p2 = new Pair<String, String>("5", "7");
            Pair<String, String> p3 = new Pair<String, String>("6", "8");
            List<Pair<String, String>> puntos = new ArrayList<>();
            puntos.add(0, p1);
            puntos.add(1, p2);
            puntos.add(2, p3);

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
            Pair<String, String> p1 = new Pair<String, String>("4", "6");
            Pair<String, String> p2 = new Pair<String, String>("5", "7");
            Pair<String, String> p3 = new Pair<String, String>("6", "8");
            List<Pair<String, String>> puntos = new ArrayList<>();
            puntos.add(0, p1);
            puntos.add(1, p2);
            puntos.add(2, p3);

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
            Pair<String, String> p1 = new Pair<String, String>("4", "6");
            Pair<String, String> p2 = new Pair<String, String>("5", "7");
            Pair<String, String> p3 = new Pair<String, String>("6", "8");
            List<Pair<String, String>> puntos = new ArrayList<>();
            puntos.add(0, p1);
            puntos.add(1, p2);
            puntos.add(2, p3);

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
    void CrearRutaUsuarioNoExiste() throws Exception {
        try {
            // Arrange

            Date fecha = Date.valueOf("2023-10-17");
            Pair<String, String> p1 = new Pair<String, String>("4", "6");
            Pair<String, String> p2 = new Pair<String, String>("5", "7");
            Pair<String, String> p3 = new Pair<String, String>("6", "8");
            List<Pair<String, String>> puntos = new ArrayList<>();
            puntos.add(0, p1);
            puntos.add(1, p2);
            puntos.add(2, p3);

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
            Pair<String, String> p1 = new Pair<String, String>("4", "6");
            Pair<String, String> p2 = new Pair<String, String>("5", "7");
            Pair<String, String> p3 = new Pair<String, String>("6", "8");
            List<Pair<String, String>> puntos = new ArrayList<>();
            puntos.add(0, p1);
            puntos.add(1, p2);
            puntos.add(2, p3);

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
            Pair<String, String> p1 = new Pair<String, String>("4", "6");
            Pair<String, String> p2 = new Pair<String, String>("5", "7");
            Pair<String, String> p3 = new Pair<String, String>("6", "8");
            List<Pair<String, String>> puntos = new ArrayList<>();
            puntos.add(0, p1);
            puntos.add(1, p2);
            puntos.add(2, p3);

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
                    if (cali.getRutaCalificada().getDescripcion() != "Sin reseña"){
                        fail("El sistema registró erróneamente la reseña de una calificación");
                    }
                }
            }

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
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
            Pair<String, String> p1 = new Pair<String, String>("4", "6");
            Pair<String, String> p2 = new Pair<String, String>("5", "7");
            Pair<String, String> p3 = new Pair<String, String>("6", "8");
            List<Pair<String, String>> puntos = new ArrayList<>();
            puntos.add(0, p1);
            puntos.add(1, p2);
            puntos.add(2, p3);

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
            Pair<String, String> p1 = new Pair<String, String>("4", "6");
            Pair<String, String> p2 = new Pair<String, String>("5", "7");
            Pair<String, String> p3 = new Pair<String, String>("6", "8");
            List<Pair<String, String>> puntos = new ArrayList<>();
            puntos.add(0, p1);
            puntos.add(1, p2);
            puntos.add(2, p3);

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
                    if (cali.getRutaCalificada().getDescripcion() != reseña){
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
