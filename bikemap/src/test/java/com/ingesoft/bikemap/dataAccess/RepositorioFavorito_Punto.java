package com.ingesoft.bikemap.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingesoft.bikemap.dominio.Favorito_Punto;

@Repository
public interface RepositorioFavorito_Punto extends JpaRepository<Favorito_Punto, Long>{
    
}
