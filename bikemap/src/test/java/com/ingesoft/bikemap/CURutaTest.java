package com.ingesoft.bikemap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ingesoft.bikemap.dataAccess.RepositorioCalificacion_Ruta;
import com.ingesoft.bikemap.dataAccess.RepositorioRuta;
import com.ingesoft.bikemap.dataAccess.RepositorioUsuario;
import com.ingesoft.bikemap.logic.ServicioRuta;

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



            //Act



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
