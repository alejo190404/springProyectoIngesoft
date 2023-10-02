package com.ingesoft.bikemap.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@EqualsAndHashCode
public class Favorito_Punto {
    @Id
    @GeneratedValue
    Long id;


    @ManyToOne //Preguntar
    private Usuario usuario; //Preguntar
    
    
    @ManyToOne //Preguntar
    private Punto_Interes punto; //Preguntar
}
