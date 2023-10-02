package com.ingesoft.bikemap.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingesoft.bikemap.dominio.Calificacion_Punto;

@Repository
public interface RepositorioCalificacion_Punto extends JpaRepository<Calificacion_Punto, Long>{
    
}
