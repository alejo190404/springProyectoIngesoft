package com.ingesoft.bikemap.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingesoft.bikemap.dominio.Calificacion_Ruta;

@Repository
public interface RepositorioCalificacion_Ruta extends JpaRepository <Calificacion_Ruta, Long>{
    List<Calificacion_Ruta> findByCreador_Login(String login);
}
