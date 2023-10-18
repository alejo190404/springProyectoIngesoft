package com.ingesoft.bikemap.logic;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingesoft.bikemap.dataAccess.RepositorioCalificacion_Punto;
import com.ingesoft.bikemap.dataAccess.RepositorioPunto_Interes;
import com.ingesoft.bikemap.dataAccess.RepositorioTipo_Punto;
import com.ingesoft.bikemap.dataAccess.RepositorioUsuario;
import com.ingesoft.bikemap.dominio.Calificacion_Punto;
import com.ingesoft.bikemap.dominio.Punto_Interes;
import com.ingesoft.bikemap.dominio.Tipo_Punto;
import com.ingesoft.bikemap.dominio.Usuario;

@Service
public class ServicioPuntoDeInteres {

    @Autowired
    private RepositorioPunto_Interes repositorioPunto_Interes;
    @Autowired
    private RepositorioUsuario repositorioUsuario;
    @Autowired
    private RepositorioCalificacion_Punto repositorioCalificacion_Punto;
    @Autowired
    private RepositorioTipo_Punto repositorioTipo_Punto;

    public void CalificarPuntoDeInteres(
            String nombrePunto,
            String calificacion, // 1
            String reseña,
            String loginCreador) throws Exception {

        // 2. Validar calificacion

        if (Float.parseFloat(calificacion) % (Integer.parseInt(calificacion)) != 0) {
            throw new Exception("La calificación no puede ser deciman. Ingrese un número entero");
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

        List<Calificacion_Punto> calificacionesUsuario = repositorioCalificacion_Punto
                .findByCreador_Login(loginCreador);

        Punto_Interes punto = new Punto_Interes();

        for (Calificacion_Punto cali : calificacionesUsuario) {
            punto = cali.getPuntoCalificado();
            if (nombrePunto.equals(punto.getNombre())) {
                repositorioCalificacion_Punto.delete(cali);
            }
        }

        // 7. Actualizar calificación del punto de interes

        Punto_Interes actual = repositorioPunto_Interes.findByNombre(nombrePunto);
        repositorioPunto_Interes.delete(actual);

        List<Calificacion_Punto> calificacionesPunto = actual.getCalificaciones();

        if (calificacionesPunto.isEmpty()) {
            actual.setCalificacionPromedio(Float.parseFloat(calificacion));
        }

        else {
            int temp = 0;

            for (Calificacion_Punto cali : calificacionesPunto) {
                temp += cali.getCalificacion();

            }
            actual.setCalificacionPromedio((temp / (calificacionesPunto.size())));
        }

        repositorioPunto_Interes.save(actual);

        // 8. Registrar calificacion a BD

        Calificacion_Punto nueva = new Calificacion_Punto();

        nueva.setCalificacion(Short.parseShort(calificacion));

        if (reseñaVacia) {
            nueva.setReseña("Sin reseña");
        } else {
            nueva.setReseña(reseña);
        }

        Usuario u = new Usuario();
        u = repositorioUsuario.findByLogin(loginCreador);

        nueva.setCreador(u);
        nueva.setPuntoCalificado(actual);

        repositorioCalificacion_Punto.save(nueva);

    }

    public void CrearPuntoDeInteres(
            String nombre, // 1
            String descripcion, // 2
            String latitud, // 3
            String longitud, // 3
            String categoria, // 5
            Date fechaCreacion,
            String loginCreador) throws Exception {

        // 4. Validar coordenadas

        if (Float.parseFloat(latitud) < (-90) || Float.parseFloat(latitud) > 90) {
            throw new Exception(
                    "Error en los valores. Debe escribir: (latitud, longitud), latitud entre -90 a 90, longitud de -180 a 180");
        }

        if (Float.parseFloat(longitud) < (-180) || Float.parseFloat(longitud) > 180) {
            throw new Exception(
                    "Error en los valores. Debe escribir: (latitud, longitud), latitud entre -90 a 90, longitud de -180 a 180");
        }

        // 5. Validar que no existe otro punto con las mismas coordenadas

        List<Punto_Interes> puntosEnLatitud = repositorioPunto_Interes.findByLatitud(latitud);

        for (Punto_Interes punto : puntosEnLatitud) {
            if (longitud.equals(punto.getLongitud())) {
                throw new Exception("¡Ya existe un punto en esta ubicación exacta!");
            }
        }

        // 6. Categotria "Otro"

        List<Tipo_Punto> lstp = repositorioTipo_Punto.findAll();
        Tipo_Punto nuevaCategoria = new Tipo_Punto();

        boolean categoriaOtro = true;

        for (Tipo_Punto tipo: lstp){
            if ((tipo.getNombre()).equals(categoria)){
                categoriaOtro = false;
                break;
            }
        }

        if (categoriaOtro){
            nuevaCategoria.setNombre(categoria);
            nuevaCategoria.setPunto(null);

            repositorioTipo_Punto.save(nuevaCategoria);
        }

        // 7. Añadir a la BD

        Punto_Interes p = new Punto_Interes();

        p.setNombre(nombre);
        p.setDescripcion(descripcion);
        p.setLatitud(latitud);
        p.setLongitud(longitud);
        p.setCalificacionPromedio(0);
        p.setFechaCreacion(fechaCreacion);

        if (categoriaOtro) {
            p.setCategoria(nuevaCategoria);
        } else {
            Tipo_Punto cat = repositorioTipo_Punto.findByNombre(categoria);
            p.setCategoria(cat);
        }

        Usuario u = repositorioUsuario.findByLogin(loginCreador);
        p.setCreador(u);

        repositorioPunto_Interes.save(p);

    }

    public void VisualizarPuntoDeInteres(
            String latitud,
            String longitud) throws Exception {

        // 1. Validar que existe sitio en las coordenadas

        List<Punto_Interes> puntosEnLatitud = repositorioPunto_Interes.findByLatitud(latitud);
        Punto_Interes puntoAVisualizar = null;

        for (Punto_Interes punto : puntosEnLatitud) {
            if (longitud.equals(punto.getLongitud())) {
                puntoAVisualizar = punto;
            }
        }

        if (puntoAVisualizar == null) {
            throw new Exception("Oops, aún no hay un punto de interés en esta ubicación");
        }

        // 2. Validar que existen calificaciones

        boolean existenCalificaciones = true;

        if (puntoAVisualizar.getCalificaciones() == null) {
            existenCalificaciones = false;
        }

        // 3. Mostrar informacion del punto

        // Preguntar para mostrar mensajes de forma diferente
        System.out.println("Nombre del punto: " + puntoAVisualizar.getNombre());
        System.out.println("Descripción del punto: " + puntoAVisualizar.getDescripcion());
        System.out.println("Creador del punto: " + puntoAVisualizar.getCreador().getLogin());
        System.out.println("Calificacion promedio: " + puntoAVisualizar.getCalificacionPromedio());
        if (existenCalificaciones == true) {
            for (Calificacion_Punto cali : puntoAVisualizar.getCalificaciones()) {
                System.out.println("Puntaje calificación: " + cali.getCalificacion());
                System.out.println("Reseña calificación: " + cali.getReseña());
            }
        } else {
            System.out.println("Aún nadie ha calificado este punto. ¡Se la primera persona en hacerlo!");
        }
        System.out.println("Categoria: " + puntoAVisualizar.getCategoria().getNombre());
        System.out.println("Fecha de creación: " + puntoAVisualizar.getFechaCreacion());

    }

}
