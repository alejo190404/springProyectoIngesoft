package com.ingesoft.bikemap.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingesoft.bikemap.dominio.Ruta;

@Repository
public interface RepositorioRuta extends JpaRepository<Ruta, Long>{
    Ruta findByNombre(String nombre);
}
