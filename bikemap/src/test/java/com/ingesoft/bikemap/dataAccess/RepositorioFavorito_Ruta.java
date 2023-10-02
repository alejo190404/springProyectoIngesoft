package com.ingesoft.bikemap.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingesoft.bikemap.dominio.Favorito_Ruta;

@Repository
public interface RepositorioFavorito_Ruta extends JpaRepository<Favorito_Ruta, Long>{
    
}
