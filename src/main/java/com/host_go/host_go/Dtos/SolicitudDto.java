package com.host_go.host_go.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDto {
<<<<<<< HEAD
    private long solicitud_id;
    private String fechaInicio;
    private String fechaFin;
    private int cantidadPer;
    private int costoTotal;
=======
private long Solicitud_id;
    private String FechaInicio;
    private String FechaFin;
    private int CantidadPer;
    private int CostoTotal;
>>>>>>> Andres

    private PropiedadDto propiedad;
    private ArrendatarioDto arrendatario;
}
