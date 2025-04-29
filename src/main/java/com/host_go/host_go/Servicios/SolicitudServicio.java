package com.host_go.host_go.Servicios;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.host_go.host_go.Dtos.SolicitudDto;
import com.host_go.host_go.Repositorios.ArrendatarioRepositorio;
import com.host_go.host_go.Repositorios.PropiedadRepositorio;
import com.host_go.host_go.Repositorios.SolicitudRepositorio;
import com.host_go.host_go.modelos.Propiedad;
import com.host_go.host_go.modelos.Solicitud;
import com.host_go.host_go.modelos.Status;

@Service
public class SolicitudServicio {

    @Autowired
    SolicitudRepositorio SolicitudRepositorio;
    @Autowired
    private PropiedadRepositorio propiedadRepositorio;
    @Autowired
    private ArrendatarioRepositorio arrendatarioRepositorio;
    @Autowired
    ModelMapper modelMapper;

    public SolicitudDto get(Long id) {
        Solicitud solicitud = SolicitudRepositorio.findById(id).orElseThrow(() -> new IllegalArgumentException("Solicitud no encontrada"));
        return modelMapper.map(solicitud, SolicitudDto.class);
    }

    public List<SolicitudDto> get() {
        List<Solicitud> solicitudes = (List<Solicitud>) SolicitudRepositorio.findAll();
        return solicitudes.stream()
                .map(solicitud -> modelMapper.map(solicitud, SolicitudDto.class))
                .collect(Collectors.toList());
    }

    public SolicitudDto save(SolicitudDto solicitudDto) {
        validarFechas(solicitudDto.getFechaInicio(), solicitudDto.getFechaFin());
        Propiedad propiedad = validarPropiedad(solicitudDto.getPropiedad().getPropiedad_id());
        validarArrendatario(solicitudDto.getArrendatario().getCedula());
        validarCapacidad(propiedad, solicitudDto.getCantidadPer());
        solicitudDto.setCostoTotal(calcularCostoTotal(propiedad, solicitudDto.getFechaInicio(), solicitudDto.getFechaFin()));
        Solicitud solicitud = modelMapper.map(solicitudDto, Solicitud.class);
        solicitud.setStatus(Status.ACTIVE);
        solicitud = SolicitudRepositorio.save(solicitud);
        return modelMapper.map(solicitud, SolicitudDto.class);
    }

    public SolicitudDto update(SolicitudDto solicitudDto) {
        Solicitud solicitud = SolicitudRepositorio.findById(solicitudDto.getSolicitud_id())
                .orElseThrow(() -> new IllegalArgumentException("Solicitud no encontrada"));
        solicitud.setStatus(Status.ACTIVE);
        solicitud = SolicitudRepositorio.save(solicitud);
        return modelMapper.map(solicitud, SolicitudDto.class);
    }

    public void delete(Long id) {
        SolicitudRepositorio.deleteById(id);
    }

    // Método para buscar solicitudes con parámetros opcionales
    public List<SolicitudDto> buscarSolicitudes(Long propiedadId, String cedulaArrendatario, String fechaInicio, String fechaFin) {
        List<Solicitud> solicitudes = (List<Solicitud>) SolicitudRepositorio.findAll(); // Aquí puedes agregar la lógica para filtrar
        
        // Filtramos las solicitudes en función de los parámetros
        return solicitudes.stream()
                .filter(solicitud -> (propiedadId == null || solicitud.getPropiedad().getPropiedad_id() == propiedadId) &&
                                     (cedulaArrendatario == null || solicitud.getArrendatario().getCedula().equals(cedulaArrendatario)) &&
                                     (fechaInicio == null || solicitud.getFechaInicio().equals(fechaInicio)) &&
                                     (fechaFin == null || solicitud.getFechaFin().equals(fechaFin)))
                .map(solicitud -> modelMapper.map(solicitud, SolicitudDto.class)) // Usamos ModelMapper
                .collect(Collectors.toList());
    }

    // Métodos de validación
    private void validarArrendatario(Integer cedula) {
        arrendatarioRepositorio.findById(cedula)
            .orElseThrow(() -> new IllegalArgumentException("Arrendatario no encontrado"));
    }

    private void validarFechas(String fechaInicio, String fechaFin) {
        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);
        
        if (fin.isBefore(inicio)) {
            throw new IllegalArgumentException("La fecha fin no puede ser anterior a la fecha inicio");
        }
    }

    private Propiedad validarPropiedad(Long propiedadId) {
        return propiedadRepositorio.findById(propiedadId)
            .orElseThrow(() -> new IllegalArgumentException("Propiedad no encontrada"));
    }

    private int calcularCostoTotal(Propiedad propiedad, String fechaInicio, String fechaFin) {
        long dias = ChronoUnit.DAYS.between(
            LocalDate.parse(fechaInicio),
            LocalDate.parse(fechaFin)
        );
        return propiedad.getPrecio() * (int) dias;
    }

    private void validarCapacidad(Propiedad propiedad, int cantidadPersonas) {
        if (cantidadPersonas > propiedad.getCapacidad()) {
            throw new IllegalArgumentException("La cantidad de personas excede la capacidad de la propiedad");
        }
    }
}
