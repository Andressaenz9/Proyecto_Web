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
@Where(clause = "status = 'ACTIVE'") // Solo registros activos
@SQLDelete(sql = "UPDATE propiedad SET status = 'DELETED' WHERE propiedad_id = ?") // Soft delete
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
    private int estado;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "arrendador_id")
    private Arrendador arrendador;
}
