package com.ingesoft.bikemap.logic;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingesoft.bikemap.dataAccess.RepositorioCalificacion_Ruta;
import com.ingesoft.bikemap.dataAccess.RepositorioRuta;
import com.ingesoft.bikemap.dataAccess.RepositorioUsuario;
import com.ingesoft.bikemap.dominio.Calificacion_Ruta;
import com.ingesoft.bikemap.dominio.Ruta;
import com.ingesoft.bikemap.dominio.Usuario;

import javafx.util.Pair;

@Service
public class ServicioRuta {

    @Autowired
    private RepositorioRuta repositorioRuta;
    @Autowired
    private RepositorioUsuario repositorioUsuario;
    @Autowired
    private RepositorioCalificacion_Ruta repositorioCalificacion_Ruta;

    public void CrearRuta(
            String nombre, // 2
            String descripcion, // 4
            List<Pair<String, String>> puntosRecorridos, // <Latitud, Longitud>
            Date fecha,
            String loginCreador) throws Exception {

        Ruta rPrueba = new Ruta();

        // 1. Validar coordenadas

        for (Pair<String, String> pair : puntosRecorridos) {
            if (Float.parseFloat(pair.getKey()) > (90) || Float.parseFloat(pair.getKey()) < (-90)) { // Latitud
                throw new Exception("Error al recibir alguno de los puntos. Sus coordenadas no cumplen los requisitos");
            }

            if ((Float.parseFloat(pair.getValue()) > (180)) || (Float.parseFloat(pair.getValue()) < (-180))) { // Longitud
                throw new Exception("Error al recibir alguno de los puntos. Sus coordenadas no cumplen los requisitos");
            }
        }

        // 4. Validar nombre no existe

        rPrueba = repositorioRuta.findByNombre(nombre);

        if (!rPrueba.equals(null)) {
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

        u = repositorioUsuario.findByLogin(loginCreador);

        if (u == null) {
            throw new Exception("El usuario que crea la ruta no existe en el sistema");
        }

        // 9. Registrar ruta

        Ruta r = new Ruta();

        r.setNombre(nombre);
        if (descripcionVacia) {
            r.setDescripcion("Sin descripción");
        } else {
            r.setDescripcion(descripcion);
        }
        //r.setPuntos(puntosRecorridos);
        r.setFechaCreacion(fecha);
        r.setCreador(u);
        r.setCalificaciones(null);
        r.setFavoritos(null);

        repositorioRuta.save(r);
    }

    public void CalificarRuta(
            String nombreRuta,
            String calificacion, // 1
            String reseña, // 3
            String loginCreador) throws Exception {

        // 2. Validar calificación

        if ((Float.parseFloat(calificacion) % (Integer.parseInt(calificacion))) != 0) {
            throw new Exception("La calificación no puede ser decimal. Ingrese un número entero");
        }

        if (Integer.parseInt(calificacion) < 0 || Integer.parseInt(calificacion) > 5) {
            throw new Exception("La calificación debe estar entre 0 y 5");
        }

        // 4. Validar reseña vacía

        boolean reseñaVacia = false;

        if (reseña.length() == 0) {
            reseñaVacia = true;
        }

        // 5. Validar tamaño de reseña

        if (reseña.length() > 1000) {
            throw new Exception("La reseña no puede superar los 1000 caracteres");
        }

        // 6. Validar existencia de calificacion del usuario

        List<Calificacion_Ruta> calificacionesUsuario = repositorioCalificacion_Ruta.findByCreador_Login(loginCreador);

        Ruta ruta = new Ruta();

        for (Calificacion_Ruta cali : calificacionesUsuario) {
            ruta = cali.getRutaCalificada();
            if (nombreRuta.equals(ruta.getNombre())) {
                repositorioCalificacion_Ruta.delete(cali);
            }
        }

        // 7. Actualizar calificación de la ruta

        Ruta actual = repositorioRuta.findByNombre(nombreRuta);
        repositorioRuta.delete(actual);

        List<Calificacion_Ruta> calificacionesRuta = actual.getCalificaciones();

        if (calificacionesRuta.isEmpty()) {
            actual.setCalificacionPromedio(Float.parseFloat(calificacion));
        }

        else {
            int temp = 0;

            for (Calificacion_Ruta cali : calificacionesRuta) {
                temp += cali.getCalificacion();

            }
            actual.setCalificacionPromedio((temp / (calificacionesRuta.size())));
        }

        repositorioRuta.save(actual);

        // 8. Registrar calificacion a BD

        Calificacion_Ruta nueva = new Calificacion_Ruta();

        nueva.setCalificacion(Short.parseShort(calificacion));

        if (reseñaVacia) {
            nueva.setReseña("Sin reseña");
        } else {
            nueva.setReseña(reseña);
        }

        Usuario u = new Usuario();
        u = repositorioUsuario.findByLogin(loginCreador);

        nueva.setCreador(u);
        nueva.setRutaCalificada(actual);

        repositorioCalificacion_Ruta.save(nueva);
    }

}
