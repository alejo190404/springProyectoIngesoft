package com.ingesoft.bikemap.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingesoft.bikemap.dominio.Punto_Interes;

@Repository
public interface RepositorioPunto_Interes extends JpaRepository<Punto_Interes, Long>{
    Punto_Interes findByNombre(String nombre);
    List<Punto_Interes> findByLatitud(String latitud);
}
