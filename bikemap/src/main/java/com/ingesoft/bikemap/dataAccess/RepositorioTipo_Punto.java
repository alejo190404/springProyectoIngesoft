package com.ingesoft.bikemap.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingesoft.bikemap.dominio.Tipo_Punto;

@Repository
public interface RepositorioTipo_Punto extends JpaRepository<Tipo_Punto, Long>{
    
}
