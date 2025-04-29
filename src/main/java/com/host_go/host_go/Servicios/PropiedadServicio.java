package com.host_go.host_go.Servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.host_go.host_go.Dtos.PropiedadDto;
import com.host_go.host_go.Repositorios.PropiedadRepositorio;
import com.host_go.host_go.modelos.Propiedad;
import com.host_go.host_go.modelos.Status;

@Service
public class PropiedadServicio {

    @Autowired
    PropiedadRepositorio PropiedadRepositorio;
    @Autowired
    ModelMapper modelMapper;

    public PropiedadDto get(Long id) {
        Propiedad propiedad = PropiedadRepositorio.findById(id).orElseThrow(() -> new IllegalArgumentException("Propiedad no encontrada"));
        return modelMapper.map(propiedad, PropiedadDto.class);
    }

    public List<PropiedadDto> get() {
        List<Propiedad> propiedades = (List<Propiedad>) PropiedadRepositorio.findAll();
        return propiedades.stream()
                .map(propiedad -> modelMapper.map(propiedad, PropiedadDto.class))
                .collect(Collectors.toList());
    }

    public PropiedadDto save(PropiedadDto propiedadDto) {
        Propiedad propiedad = modelMapper.map(propiedadDto, Propiedad.class);
        propiedad.setStatus(Status.ACTIVE);
        propiedad = PropiedadRepositorio.save(propiedad);
        propiedadDto.setPropiedad_id(propiedad.getPropiedad_id());
        return propiedadDto;
    }

    public PropiedadDto update(PropiedadDto propiedadDto) {
        Propiedad propiedad = PropiedadRepositorio.findById(propiedadDto.getPropiedad_id())
                .orElseThrow(() -> new IllegalArgumentException("Propiedad no encontrada"));
        propiedad.setStatus(Status.ACTIVE);
        propiedad = PropiedadRepositorio.save(propiedad);
        return modelMapper.map(propiedad, PropiedadDto.class);
    }

    public void delete(Long id) {
        PropiedadRepositorio.deleteById(id);
    }
    
    public List<PropiedadDto> buscarPropiedades(String nombre, String ubicacion, int capacidad) {
        List<Propiedad> propiedades = PropiedadRepositorio
            .findByNombreContainingIgnoreCaseAndUbicacionContainingIgnoreCaseAndCapacidadGreaterThanEqual(
                nombre != null ? nombre : "", 
                ubicacion != null ? ubicacion : "", 
                capacidad
            );
        
        return propiedades.stream()
            .map(propiedad -> modelMapper.map(propiedad, PropiedadDto.class))
            .collect(Collectors.toList());
    }
}
