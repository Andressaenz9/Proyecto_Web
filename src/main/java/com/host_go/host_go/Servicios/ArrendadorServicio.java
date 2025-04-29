package com.host_go.host_go.Servicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.host_go.host_go.Dtos.ArrendadorCreateDto;
import com.host_go.host_go.Dtos.ArrendadorDto;
import com.host_go.host_go.Repositorios.ArrendadorRepositorio;
import com.host_go.host_go.Repositorios.CuentaRepositorio;
import com.host_go.host_go.modelos.Arrendador;
import com.host_go.host_go.modelos.Cuenta;
import com.host_go.host_go.modelos.Status;

@Service
public class ArrendadorServicio {

    @Autowired
    private ArrendadorRepositorio arrendadorRepositorio;

    @Autowired
    private CuentaRepositorio cuentaRepositorio;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ArrendadorDto get(Integer id) {
        Optional<Arrendador> optional = arrendadorRepositorio.findById(id);
        return optional.map(arr -> modelMapper.map(arr, ArrendadorDto.class)).orElse(null);
    }

    public List<ArrendadorDto> get() {
        return arrendadorRepositorio.findAll().stream()
                .map(arr -> modelMapper.map(arr, ArrendadorDto.class))
                .collect(Collectors.toList());
    }

    public ArrendadorDto save(ArrendadorCreateDto dto) {
        if (arrendadorRepositorio.existsByCorreo(dto.getCorreo())) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }

        if (!dto.getCorreo().endsWith("@javeriana.edu.co")) {
            throw new IllegalArgumentException("Correo no cumple con el formato corporativo");
        }

        if (dto.getContrasena().length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }

        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario(dto.getCorreo());
        cuenta.setContrasena(passwordEncoder.encode(dto.getContrasena()));
        cuenta.setStatus(Status.ACTIVE);
        cuenta = cuentaRepositorio.save(cuenta);

        Arrendador arrendador = modelMapper.map(dto, Arrendador.class);
        arrendador.setCuenta(cuenta);
        arrendador.setStatus(Status.ACTIVE);
        arrendador = arrendadorRepositorio.save(arrendador);

        return modelMapper.map(arrendador, ArrendadorDto.class);
    }

    public ArrendadorDto update(ArrendadorDto dto) {
        Optional<Arrendador> optional = arrendadorRepositorio.findById(dto.getCedula());
        if (!optional.isPresent()) {
            throw new IllegalArgumentException("Arrendador no encontrado");
        }

        Arrendador arrendador = modelMapper.map(dto, Arrendador.class);
        arrendador.setStatus(Status.ACTIVE);
        arrendador = arrendadorRepositorio.save(arrendador);

        return modelMapper.map(arrendador, ArrendadorDto.class);
    }

    public void delete(Integer id) {
        arrendadorRepositorio.deleteById(id);
    }
}
