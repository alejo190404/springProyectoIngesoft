package com.ingesoft.bikemap;

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
import com.ingesoft.bikemap.dominio.Ruta;
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
    void resetear(){
        repositorioRuta.deleteAll();
        repositorioUsuario.deleteAll();
        repositorioCalificacion_Ruta.deleteAll();
    }

    // CU Crear Ruta

    @Test
    void CrearRutaExistoso() throws Exception{
        try {
            //Arrange

            Usuario u = new Usuario();

            u.set
            
            repositorioUsuario.save(u);

            Date fecha = Date.valueOf("2023-10-17");
            Pair p1 = new Pair("4", "6");
            Pair p2 = new Pair("5", "7");
            Pair p3 = new Pair("6", "8");
            List<Pair<String, String>> puntos = new ArrayList<>();
            puntos.add(0, p1);
            puntos.add(1, p2);
            puntos.add(2, p3);

            //Act

            servicio.CrearRuta(
                "Unviersidades",
                "Recorrido por las principales universidades de Bogotá",
                puntos,
                fecha,
                "null"
            );

            //Assert


        } catch (Exception e) {
            //Assert
        }
    }

    @Test
    void CrearRutaCoordenadasLatitudIncorrecta() throws Exception{
        try {
            //Arrange



            //Act



            //Assert


        } catch (Exception e) {
            //Assert
        }
    }

    @Test
    void CrearRutaCoordenadasLongitudIncorrecta() throws Exception{
        try {
            //Arrange



            //Act



            //Assert


        } catch (Exception e) {
            //Assert
        }
    }

    @Test
    void CrearRutaNombreExistente() throws Exception{
        try {
            //Arrange



            //Act



            //Assert


        } catch (Exception e) {
            //Assert
        }
    }

    @Test
    void CrearRutaNombreLargo() throws Exception{
        try {
            //Arrange



            //Act



            //Assert


        } catch (Exception e) {
            //Assert
        }
    }

    @Test
    void CrearRutaDescripcionVacia() throws Exception{
        try {
            //Arrange



            //Act



            //Assert


        } catch (Exception e) {
            //Assert
        }
    }

    @Test
    void CrearRutaDescripcionLarga() throws Exception{
        try {
            //Arrange



            //Act



            //Assert


        } catch (Exception e) {
            //Assert
        }
    }

    @Test
    void CrearRutaUsuarioNoExiste() throws Exception{
        try {
            //Arrange



            //Act



            //Assert


        } catch (Exception e) {
            //Assert
        }
    }

    //CU Calificar Ruta

    @Test
    void CalificarRutaExitoso() throws Exception{
        try {
            //Arrange



            //Act



            //Assert


        } catch (Exception e) {
            //Assert
        }
    }

    @Test
    void CalificarRutaCoordenadaLatitudIncorrecta() throws Exception{
        try {
            //Arrange



            //Act



            //Assert


        } catch (Exception e) {
            //Assert
        }
    }

    @Test
    void CalificarRutaCoordenadaLongitudIncorrecta() throws Exception{
        try {
            //Arrange



            //Act



            //Assert


        } catch (Exception e) {
            //Assert
        }
    }

    @Test
    void CalificarRutaReseñaVacia() throws Exception{
        try {
            //Arrange



            //Act



            //Assert


        } catch (Exception e) {
            //Assert
        }
    }

    @Test
    void CalificarRutaReseñaLarga() throws Exception{
        try {
            //Arrange



            //Act



            //Assert


        } catch (Exception e) {
            //Assert
        }
    }

    @Test
    void CalificarRutaCalificacionExistente() throws Exception{
        try {
            //Arrange



            //Act



            //Assert


        } catch (Exception e) {
            //Assert
        }
    }

}
