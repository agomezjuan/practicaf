package com.practica.core.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practica.core.entitys.Empleado;
import com.practica.core.repositorys.EmpleadoRepository;

@Service
public class EmpleadoServices {
	
	@Autowired 
	EmpleadoRepository empleadoRepository;
	public ArrayList <Empleado> obtenerEmpleados(){
		
		return ( ArrayList <Empleado> ) empleadoRepository.findAll();
		
	}
	
	public Empleado guardarEmpleado (Empleado empleado) {
		
		return empleadoRepository.save(empleado);
	}

	public Empleado buscarEmpleadoPorNif(String nif) {
		
		return empleadoRepository.findByNif(nif);
	}

}
