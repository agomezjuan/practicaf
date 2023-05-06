package com.practica.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.practica.core.entitys.Proyecto;
import com.practica.core.repositorys.ProyectoRepository;

import java.util.List;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {
    
    @Autowired
    private ProyectoRepository proyectoRepository;
    
    @GetMapping
    public ResponseEntity<List<Proyecto>> listarProyectos() {
        List<Proyecto> proyectos = proyectoRepository.findAll();
        return new ResponseEntity<>(proyectos, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Proyecto> crearProyecto(@RequestBody Proyecto proyecto) {
        Proyecto proyectoGuardado = proyectoRepository.save(proyecto);
        return new ResponseEntity<>(proyectoGuardado, HttpStatus.CREATED);
    }
   
    
}
