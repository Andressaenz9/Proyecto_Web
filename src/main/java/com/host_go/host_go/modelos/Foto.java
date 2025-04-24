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
@SQLDelete(sql = "UPDATE foto SET status = 'DELETED' WHERE foto_id = ?") // Soft delete
public class Foto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long foto_id;
    private String url;
=======
@SQLDelete(sql = "UPDATE foto SET status = 'DELETED' WHERE cedula = ?") // Soft delete
public class Foto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Foto_id;
    private String Url;
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
