package com.ingesoft.bikemap;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ingesoft.bikemap.dataAccess.RepositorioCalificacion_Punto;
import com.ingesoft.bikemap.dataAccess.RepositorioPunto_Interes;
import com.ingesoft.bikemap.dataAccess.RepositorioTipo_Punto;
import com.ingesoft.bikemap.dataAccess.RepositorioUsuario;
import com.ingesoft.bikemap.dominio.Calificacion_Punto;
import com.ingesoft.bikemap.dominio.Punto_Interes;
import com.ingesoft.bikemap.dominio.Tipo_Punto;
import com.ingesoft.bikemap.dominio.Usuario;
import com.ingesoft.bikemap.logic.ServicioPuntoDeInteres;

@SpringBootTest
public class CUPuntoDeInteresTest {

    @Autowired
    ServicioPuntoDeInteres servicio;
    @Autowired
    private RepositorioPunto_Interes repositorioPunto_Interes;
    @Autowired
    private RepositorioUsuario repositorioUsuario;
    @Autowired
    private RepositorioCalificacion_Punto repositorioCalificacion_Punto;
    @Autowired
    private RepositorioTipo_Punto repositorioTipo_Punto;

    @BeforeEach
    void resetear() {
        repositorioCalificacion_Punto.deleteAll();
        repositorioPunto_Interes.deleteAll();
        repositorioTipo_Punto.deleteAll();
        repositorioUsuario.deleteAll();

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

        Tipo_Punto tp = new Tipo_Punto();

        tp.setNombre("Tienda");

        repositorioTipo_Punto.save(tp);

        Punto_Interes p = new Punto_Interes();

        p.setNombre("Tienda Bicis");
        p.setDescripcion("Una linda tienda de bicicletas");
        p.setLatitud("80");
        p.setLongitud("2");
        p.setCalificacionPromedio(Float.parseFloat("4.7"));

        Date fecha = Date.valueOf("2023-10-17");
        p.setFechaCreacion(fecha);
        p.setCategoria(tp);
        p.setCreador(u);

        repositorioPunto_Interes.save(p);
    }

    // CU Calificar punto de interes

    @Test
    void CalificarPuntoExitoso() throws Exception {
        try {
            // Arrange

            // Act

            servicio.CalificarPuntoDeInteres(
                    "Tienda",
                    "5",
                    "Muy buen sitio. Parchadito para comprar",
                    "Prueba");

            // Assert

        } catch (Exception e) {
            // Assert
            fail("Error en el sistema al calificar el punto de interes");
        }
    }

    @Test
    void CalificarPuntoCalificacionNoDecimal() throws Exception {
        try {

            // Arrange

            // Act

            servicio.CalificarPuntoDeInteres(
                    "Tienda",
                    "4.7",
                    "Muy buen sitio. Parchadito para comprar",
                    "Prueba");

            // Assert

            fail("El sistema registró una calificacion con numero deicmal");

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    void CalificarPuntoCalificacionFueraLimite1() throws Exception {
        try {

            // Arrange

            // Act

            servicio.CalificarPuntoDeInteres(
                    "Tienda",
                    "7",
                    "Muy buen sitio. Parchadito para comprar",
                    "Prueba");

            // Assert

            fail("El sistema registró una calificacion con valor superior al permitido");

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    void CalificarPuntoCalificacionFueraLimite2() throws Exception {
        try {

            // Arrange

            // Act

            servicio.CalificarPuntoDeInteres(
                    "Tienda",
                    "-2",
                    "Muy buen sitio. Parchadito para comprar",
                    "Prueba");

            // Assert

            fail("El sistema registró una calificacion con valor inferior al permitido");

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    void CalificarPuntoReseñaVacia() throws Exception {
        try {

            // Arrange

            // Act

            servicio.CalificarPuntoDeInteres(
                    "Tienda",
                    "-2",
                    "Muy buen sitio. Parchadito para comprar",
                    "Prueba");

            // Assert

            List<Calificacion_Punto> cps = repositorioCalificacion_Punto.findByCreador_Login("Prueba");
            for (Calificacion_Punto cali : cps) {
                if (cali.getPuntoCalificado().getNombre() == "Tienda Bicis") {
                    if (cali.getReseña() != "Sin reseña") {
                        fail("El sistema registró erróneamente la reseña de una calificación");
                    }
                }
            }

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    void CalificarPuntoReseñaLarga() throws Exception {
        try {

            // Arrange

            String texto1000caracteres = "Este es un cuento de más de 1000 caracteres. Ernesto Gurrián se escondió tras el coche. Joaquín a su lado empuñó el arma reglamentaria. Desde allí podían ver la puerta abierta por donde saldrían. Los de dentro se habían cargado, hace tres días a Tomás Ojibe un compañero encantador con una hija de meses, tanto Joaquín como Ernesto habían estado en el bautizo. El coordinador de la operación lo dijo claramente en la reunión previa: “en caso de duda disparen, estos hijos de puta no se nos pueden escapar”. Joaquín oía su propio sobrealiento, sentía la boca seca, tenía ganas de ver la cara de mierda de aquellos tipejos a los que no conocía ni en fotos. Se fue abriendo la puerta. Ernesto tenía mejor ángulo y desde allí, fue el primero en ver y reconocer los zapatos de la chica. Joaquín le miró de reojo. Vio como de repente le empezó a temblar la mano aferrada a la pistola. Pasaron tres segundos. Vio sus ojos encharcados con una pena de padre pero una angustia de ira. Vio a Ojibe con su hija en brazos junto al baptisterio. Vio a su hija, a la suya en la calle. Joaquín grito “!Alto policía tirad las armas!” luego tres disparos. “¿Qué haces Ernesto?” le gritó. Solo murió la chica y Ernesto vivió muerto para siempre.";

            // Act

            servicio.CalificarPuntoDeInteres(
                    "Tienda",
                    "5",
                    texto1000caracteres,
                    "Prueba");

            // Assert

            fail("El sistema regsitra una calificación con reseña con más de 1000 caracters");

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    void CalificarPuntoActualizarCalificacionExistente() throws Exception {
        try {

            // Arrange

            Calificacion_Punto cp = new Calificacion_Punto();

            cp.setReseña("Parchadito");
            cp.setCalificacion(Short.parseShort("5"));
            cp.setCreador(repositorioUsuario.findByLogin("parcero"));
            cp.setPuntoCalificado(repositorioPunto_Interes.findByNombre("Tienda Bicis"));

            repositorioCalificacion_Punto.save(cp);

            // Act

            servicio.CalificarPuntoDeInteres(
                "Tienda Bicis",
                "3",
                "No tan parchadito",
                "parcero"
            );

            // Assert

            List<Calificacion_Punto> lcp = repositorioCalificacion_Punto.findByCreador_Login("parcero");

            if (lcp.size() != 1){
                fail("El sistema no actualizó correctamente una calificación");
            }

        } catch (Exception e) {
            // Assert
        }
    }

    // CU Crear punto

    @Test
    void CrearPuntoExitoso() throws Exception {
        try {

            // Arrange

            // Act

            // Assert

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    void CrearPuntoLatitudErronea1() throws Exception {
        try {

            // Arrange

            // Act

            // Assert

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    void CrearPuntoLatitudErronea2() throws Exception {
        try {

            // Arrange

            // Act

            // Assert

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    void CrearPuntoLongitudErronea1() throws Exception {
        try {

            // Arrange

            // Act

            // Assert

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    void CrearPuntoLongitudErronea2() throws Exception {
        try {

            // Arrange

            // Act

            // Assert

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    void CrearPuntoCoordenadasExistentes() throws Exception {
        try {

            // Arrange

            // Act

            // Assert

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    void CrearPuntoCategoriaOtro() throws Exception {
        try {

            // Arrange

            // Act

            // Assert

        } catch (Exception e) {
            // Assert
        }
    }

    // CU Visualizar punto

    @Test
    void VisualizarPuntoExitoso() throws Exception {
        try {

            // Arrange

            // Act

            // Assert

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    void VisualizarPuntoCoordenadasVacias() throws Exception {
        try {

            // Arrange

            // Act

            // Assert

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    void VisualizarPuntoSinCalificaciones() throws Exception {
        try {

            // Arrange

            // Act

            // Assert

        } catch (Exception e) {
            // Assert
        }
    }

}
