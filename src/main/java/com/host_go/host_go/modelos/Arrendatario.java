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
@SQLDelete(sql = "UPDATE arrendatario SET status = 'DELETED' WHERE arrendatario_id = ?") // Soft delete
public class Arrendatario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long arrendatario_id;
    private Integer cedula;
    private String nombre;
    private String apellido;
    private String correo;
    private long telefono;
=======
@SQLDelete(sql = "UPDATE arrendatario SET status = 'DELETED' WHERE cedula = ?") // Soft delete
public class Arrendatario {
    @Id
    private Integer Cedula;
    private String Nombre;
    private String Apellido;
    private String Correo;
    private int Telefono;
>>>>>>> Andres

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @OneToOne
<<<<<<< HEAD
    @JoinColumn(name = "cuenta_id")
=======
    @JoinColumn(name = "Cuenta_id")
>>>>>>> Andres
    private Cuenta cuenta;
}
