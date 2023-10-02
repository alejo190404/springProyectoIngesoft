package com.ingesoft.bikemap.dominio;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@EqualsAndHashCode
public class Ruta {
    @Id
    @GeneratedValue
    long id;
    
    private String nombre;
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @ManyToOne
    private Usuario creador;

    @OneToMany(mappedBy="rutaCalificada")
    private List<Calificacion_Ruta> calificaciones;

    @OneToMany(mappedBy="ruta") //Preguntar
    private List<Favorito_Ruta> favoritos; //Preguntar
    
}