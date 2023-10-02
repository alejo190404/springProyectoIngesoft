package com.ingesoft.bikemap.dominio;

import jakarta.persistence.OneToMany;

public class Tipo_Punto {
    Long id;

    String nombre;

    @OneToMany(mappedBy = "categoria") //Preguntar
    private Punto_Interes punto; //Preguntar
}
