package com.host_go.host_go.modelos;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("deprecation")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "status = 'ACTIVE'") // Filtra solo los activos
<<<<<<< HEAD
@SQLDelete(sql = "UPDATE propiedad SET status = 'DELETED' WHERE propiedad_id  = ?") // Soft delete
public class Propiedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long propiedad_id;
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private int precio;
    private String tipo;
    private int capacidad;
=======
@SQLDelete(sql = "UPDATE propiedad SET status = 'DELETED' WHERE cedula = ?") // Soft delete
public class Propiedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Propiedad_id;
    private String Nombre;
    private String Descripcion;
    private String Ubicacion;
    private int Precio;
    private String Tipo;
    private int Capacidad;
    private int Estado;
>>>>>>> Andres

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
    
    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "arrendador_id")
=======
    @JoinColumn(name = "Cedula")
>>>>>>> Andres
    private Arrendador arrendador;
}
