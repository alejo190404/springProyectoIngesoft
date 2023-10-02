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
public class Usuario {
    @Id
    @GeneratedValue
    private long id;

    private String nombreCompleto;
    private String login;
    private String contraseña;
    private String correoRecuperacion;

    
    @OneToMany(mappedBy="usuario") //Preguntar
    private List<Favorito_Ruta> rutasFavoritas; //Preguntar
    
    @OneToMany(mappedBy="creador")
    private List<Ruta> rutasCreadas;

    
    @OneToMany(mappedBy="usuario") //Preguntar
    private List<Favorito_Punto> puntosInteresFavoritos; //Preguntar
    
    @OneToMany(mappedBy="creador")
    private List<Punto_Interes> puntosInteresCreados;

    
    @OneToMany(mappedBy="creador")
    private List<Calificacion_Punto> calificacionesPuntos;
    
    @OneToMany(mappedBy="")
    private List<Calificacion_Ruta> calificacionesRutas;

}
