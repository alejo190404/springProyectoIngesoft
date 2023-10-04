package com.ingesoft.bikemap.dominio;



import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@EqualsAndHashCode
public class Tipo_Punto {
    @Id
    @GeneratedValue
    Long id;

    String nombre;

    @OneToMany(mappedBy = "categoria") //Preguntar
    private List<Punto_Interes> punto; //Preguntar
}
