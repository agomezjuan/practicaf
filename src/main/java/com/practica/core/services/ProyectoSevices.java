package com.practica.core.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.practica.core.entitys.Proyecto;

import com.practica.core.repositorys.ProyectoRepository;

@Service
public class ProyectoSevices {
	
	@Autowired 
	ProyectoRepository proyectoRepository;
	public ArrayList <Proyecto> ObtenerProyecto(){
		
		return ( ArrayList <Proyecto> ) proyectoRepository.findAll();
		
	}

}
