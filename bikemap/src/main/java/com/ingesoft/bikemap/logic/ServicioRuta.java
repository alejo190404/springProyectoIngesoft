package com.ingesoft.bikemap.logic;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javafx.util.Pair;

import com.ingesoft.bikemap.dataAccess.RepositorioRuta;
import com.ingesoft.bikemap.dataAccess.RepositorioUsuario;
import com.ingesoft.bikemap.dominio.Ruta;
import com.ingesoft.bikemap.dominio.Usuario;

@Service
public class ServicioRuta {

    @Autowired
    private RepositorioRuta repositorioRuta;
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    public void CrearRuta(
            String nombre, // 2
            String descripcion, // 4
            List<Pair<String, String>> puntosRecorridos, // <Latitud, Longitud>
            Date fecha,
            String nombreCreador) throws Exception {

        Ruta rPrueba = new Ruta();

        // 1. Validar coordenadas

        for (Pair<String, String> pair : puntosRecorridos) {
            if (Float.parseFloat(pair.getKey()) > (90) && Float.parseFloat(pair.getKey()) < (-90)) {
                throw new Exception("Error al recibir alguno de los puntos. Sus coordenadas no cumplen los requisitos");
            }

            if (Float.parseFloat(pair.getValue()) > (180) && Float.parseFloat(pair.getKey()) < (-180)) {
                throw new Exception("Error al recibir alguno de los puntos. Sus coordenadas no cumplen los requisitos");
            }
        }

        // 4. Validar nombre no existe

        rPrueba = repositorioRuta.findByNombre(nombre);

        if (rPrueba != null) {
            throw new Exception("El nombre de ruta ya existe en el sistema");
        }

        // 4. Validar longitud nombre

        if (nombre.length() > 40) {
            throw new Exception("El nombre de ruta no puede contener más de 40 caracteres");
        }

        // 6. Validar descrripción vacía

        boolean descripcionVacia = false;

        if (descripcion.length() == 0) {
            descripcionVacia = true;
        }

        // 7. Validar longitud descripción

        if (descripcion.length() > 1000) {
            throw new Exception("La descripcion no puede contener más de 1000 caracteres");
        }

        // 8. Validar usuario existente

        Usuario u = new Usuario();

        u = repositorioUsuario.findByLogin(nombreCreador);

        if (u != null) {
            throw new Exception("Este nombre de usuario ya existe. Prueba con otro diferente");
        }

        // 9. Registrar ruta

        Ruta r = new Ruta();

        r.setNombre(nombre);
        if (descripcionVacia) {
            r.setDescripcion("Sin descripción");
        } else {
            r.setDescripcion(descripcion);
        }
        r.setPuntos(puntosRecorridos);
        r.setFechaCreacion(fecha);
        r.setCreador(u);
        r.setCalificaciones(null);
        r.setFavoritos(null);

        repositorioRuta.save(r);
    }

    public void CalificarRuta(String nombreRuta, short calificacion) {

    }

}
