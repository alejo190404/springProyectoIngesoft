package com.ingesoft.bikemap.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@EqualsAndHashCode
public class Calificacion_Punto {
    @Id
    @GeneratedValue
    private Long id;

    private String reseña;
    private short calificacion;

    @ManyToOne
    private Usuario creador;

    @ManyToOne(fetch = FetchType.EAGER)
    private Punto_Interes puntoCalificado;
}
