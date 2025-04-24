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
@SQLDelete(sql = "UPDATE solicitud SET status = 'DELETED' WHERE solicitud_id = ?") // Soft delete
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long solicitud_id;
    private String fechaInicio;
    private String fechaFin;
    private int cantidadPer;
    private int costoTotal;
=======
@SQLDelete(sql = "UPDATE solicitud SET status = 'DELETED' WHERE cedula = ?") // Soft delete
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Solicitud_id;
    private String FechaInicio;
    private String FechaFin;
    private int CantidadPer;
    private int CostoTotal;
>>>>>>> Andres

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "propiedad_id")
    private Propiedad propiedad;
    
    @ManyToOne
    @JoinColumn(name = "arrendatario_id")
=======
    @JoinColumn(name = "Propiedad_id")
    private Propiedad propiedad;
    
    @ManyToOne
    @JoinColumn(name = "Cedula")
>>>>>>> Andres
    private Arrendatario arrendatario;
}
