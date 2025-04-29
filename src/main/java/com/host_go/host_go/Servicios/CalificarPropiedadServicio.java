package com.host_go.host_go.Servicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.host_go.host_go.Dtos.CalificarPropiedadDto;
import com.host_go.host_go.Repositorios.CalificarPropiedadRepositorio;
import com.host_go.host_go.modelos.CalificarPropiedad;
import com.host_go.host_go.modelos.Status;

@Service
public class CalificarPropiedadServicio {

    @Autowired
    private CalificarPropiedadRepositorio calificarPropiedadRepositorio;

    @Autowired
    private ModelMapper modelMapper;

    public CalificarPropiedadDto get(Long id) {
        Optional<CalificarPropiedad> optional = calificarPropiedadRepositorio.findById(id);
        return optional.map(p -> modelMapper.map(p, CalificarPropiedadDto.class)).orElse(null);
    }

    public List<CalificarPropiedadDto> get() {
        return calificarPropiedadRepositorio.findAll().stream()
                .map(p -> modelMapper.map(p, CalificarPropiedadDto.class))
                .collect(Collectors.toList());
    }

    public CalificarPropiedadDto save(CalificarPropiedadDto dto) {
        CalificarPropiedad calificacion = modelMapper.map(dto, CalificarPropiedad.class);
        calificacion.setStatus(Status.ACTIVE);
        calificacion = calificarPropiedadRepositorio.save(calificacion);
        return modelMapper.map(calificacion, CalificarPropiedadDto.class);
    }

    public CalificarPropiedadDto update(CalificarPropiedadDto dto) {
        Optional<CalificarPropiedad> optional = calificarPropiedadRepositorio.findById(dto.getCalificarPropiedad_id());
        if (!optional.isPresent()) {
            throw new IllegalArgumentException("Calificación de propiedad no encontrada");
        }

        CalificarPropiedad calificacion = modelMapper.map(dto, CalificarPropiedad.class);
        calificacion.setStatus(Status.ACTIVE);
        calificacion = calificarPropiedadRepositorio.save(calificacion);
        return modelMapper.map(calificacion, CalificarPropiedadDto.class);
    }

    public void delete(Long id) {
        calificarPropiedadRepositorio.deleteById(id);
    }
}
