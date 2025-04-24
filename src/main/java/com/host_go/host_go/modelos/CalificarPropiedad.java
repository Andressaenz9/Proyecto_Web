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
@SQLDelete(sql = "UPDATE calificacion SET status = 'DELETED' WHERE caliPropiedad_id = ?") // Soft delete
public class CaliPropiedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long caliPropiedad_id;
    private int estrellas;
    private String comentario;
=======
@SQLDelete(sql = "UPDATE calificacion SET status = 'DELETED' WHERE cedula = ?") // Soft delete
public class CaliPropiedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long CaliPropiedad_id;
    private int Estrellas;
    private String Comentario;
>>>>>>> Andres

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "propiedad_id")
=======
    @JoinColumn(name = "Propiedad_id")
>>>>>>> Andres
    private Propiedad propiedad;
}
