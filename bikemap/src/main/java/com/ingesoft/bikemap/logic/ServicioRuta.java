package com.ingesoft.bikemap.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingesoft.bikemap.dataAccess.RepositorioRuta;

@Service
public class ServicioRuta {
    
    @Autowired
    private RepositorioRuta repositorioRuta;
}
