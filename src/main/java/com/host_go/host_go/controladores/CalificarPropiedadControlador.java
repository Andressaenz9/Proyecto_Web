package com.host_go.host_go.controladores;

import java.util.List;

import org.modelmapper.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.host_go.host_go.Dtos.CalificarPropiedadDto;
import com.host_go.host_go.Servicios.CalificarPropiedadServicio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping(value = "/CalificarPropiedad")
public class CalificarPropiedadControlador {

    @Autowired
    private CalificarPropiedadServicio CalificarPropiedadServicio;

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CalificarPropiedadDto> get (){
        return CalificarPropiedadServicio.get();
    }

    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CalificarPropiedadDto get(@PathVariable Long id){
        return CalificarPropiedadServicio.get(id);
    }

    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CalificarPropiedadDto save(@RequestBody CalificarPropiedadDto CalificarPropiedadDto) throws ValidationException{
        return CalificarPropiedadServicio.save(CalificarPropiedadDto);
    }

    @CrossOrigin
    @PutMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public CalificarPropiedadDto update(@RequestBody CalificarPropiedadDto CalificarPropiedadDto) throws ValidationException{
        return CalificarPropiedadServicio.update(CalificarPropiedadDto);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id){
        CalificarPropiedadServicio.delete(id);
    }
    
}
