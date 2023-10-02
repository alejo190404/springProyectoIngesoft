package com.ingesoft.bikemap.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingesoft.bikemap.dominio.Usuario;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Long>{
    
}
