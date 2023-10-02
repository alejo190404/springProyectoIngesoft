package com.ingesoft.bikemap.api;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ingesoft.bikemap.dominio.Usuario;

@RepositoryRestResource(collectionResourceRel="usuarios", path="usuarios")
public interface ApiUsuario extends PagingAndSortingRepository<Usuario, Long>{
    
}
