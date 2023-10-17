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
                    "Prueba"
                );

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
                    "Prueba"
                );

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
                    "Prueba"
                );

            // Assert

            List<Calificacion_Punto> cps = repositorioCalificacion_Punto.findByCreador_Login("Prueba");
            for (Calificacion_Punto cali: cps){
                if (cali.getPuntoCalificado().getNombre() == "Tienda Bicis"){
                    if (cali.getReseña() != "Sin reseña"){
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

            // Act

            // Assert

        } catch (Exception e) {
            // Assert
        }
    }

    @Test
    void CalificarPuntoActualizarCalificacionExistente() throws Exception {
        try {

            // Arrange

            // Act

            // Assert

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
