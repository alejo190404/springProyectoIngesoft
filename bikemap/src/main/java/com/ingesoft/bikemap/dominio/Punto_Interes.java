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
public class Punto_Interes {
    @Id
    @GeneratedValue
    private long id;

    private String nombre;
    private String descripcion;
    private String latitud;
    private String longitud;
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @ManyToOne //Preguntar
    private Tipo_Punto categoria; //Preguntar

    @ManyToOne
    private Usuario creador;

    @OneToMany(mappedBy="puntoCalificado")
    private List<Calificacion_Punto> calificaciones;
    
    @OneToMany(mappedBy="punto") //Preguntar
    private List<Favorito_Punto> favoritos; //Preguntar
}
