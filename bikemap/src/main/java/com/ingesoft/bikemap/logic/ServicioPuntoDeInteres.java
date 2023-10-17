package com.ingesoft.bikemap.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingesoft.bikemap.dataAccess.RepositorioCalificacion_Punto;
import com.ingesoft.bikemap.dataAccess.RepositorioPunto_Interes;
import com.ingesoft.bikemap.dataAccess.RepositorioUsuario;
import com.ingesoft.bikemap.dominio.Calificacion_Punto;
import com.ingesoft.bikemap.dominio.Punto_Interes;
import com.ingesoft.bikemap.dominio.Usuario;

@Service
public class ServicioPuntoDeInteres {
    
    @Autowired
    private RepositorioPunto_Interes repositorioPunto_Interes;
    @Autowired
    private RepositorioUsuario repositorioUsuario;
    @Autowired
    private RepositorioCalificacion_Punto repositorioCalificacion_Punto;

    public void CalificarPuntoDeInteres (
        String nombrePunto,
        String calificacion, //1
        String reseña,
        String loginCreador
    ) throws Exception{

        //2. Validar calificacion

        if (Float.parseFloat(calificacion) % (Integer.parseInt(calificacion)) != 0){
            throw new Exception("La calificación no puede ser deciman. Ingrese un número entero");
        }

        if (Integer.parseInt(calificacion) < 0 && Integer.parseInt(calificacion) > 5){
            throw new Exception("La calificación debe estar entre 0 y 5");
        }

        //4. Validar reseña vacía

        boolean reseñaVacia = false;
        
        if (reseña.length() != 0){
            reseñaVacia = true;
        }

        //5. Validar tamaño de reseña

        if (reseña.length() > 1000){
            throw new Exception("La reseña no puede superar los 1000 caracteres");
        }

        //6. Validar existencia de calificacion del usuario

        List<Calificacion_Punto> calificacionesUsuario = repositorioCalificacion_Punto.findByCreador_Login(loginCreador);

        Punto_Interes punto = new Punto_Interes();
        
        for (Calificacion_Punto cali: calificacionesUsuario){
            punto = cali.getPuntoCalificado();
            if (nombrePunto.equals(punto.getNombre())){
                repositorioCalificacion_Punto.delete(cali);
            }
        }

        //7. Actualizar calificación del punto de interes

        Punto_Interes actual = repositorioPunto_Interes.findByNombre(nombrePunto);
        repositorioPunto_Interes.delete(actual);

        List<Calificacion_Punto> calificacionesPunto = actual.getCalificaciones();
        int temp = 0;
        
        for (Calificacion_Punto cali: calificacionesPunto){
            temp += cali.getCalificacion();
        }

        actual.setCalificacionPromedio((temp/(calificacionesPunto.size())));

        repositorioPunto_Interes.save(actual);

        //8. Registrar calificacion a BD

        Calificacion_Punto nueva = new Calificacion_Punto();

        nueva.setCalificacion(Short.parseShort(calificacion));
        
        if (reseñaVacia){
            nueva.setReseña("Sin reseña");
        }
        else {
            nueva.setReseña(reseña);
        }
        
        Usuario u = new Usuario();
        u = repositorioUsuario.findByLogin(loginCreador);
        
        nueva.setCreador(u);
        nueva.setPuntoCalificado(actual);
        
        repositorioCalificacion_Punto.save(nueva);

    }

    public void CrearPuntoDeInteres(){

    }

    public void VisualizarPuntoDeInteres(){

    }

}
