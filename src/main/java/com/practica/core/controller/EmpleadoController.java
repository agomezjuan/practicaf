package com.practica.core.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practica.core.entitys.Empleado;
import com.practica.core.entitys.dto.EmpleadoDTO;
import com.practica.core.services.EmpleadoServices;

@RestController
@RequestMapping ("/empleados")
public class EmpleadoController {

	@Autowired
	EmpleadoServices empleadoServices;
	
	@GetMapping()
	public ArrayList <Empleado> obtenerEmpleados (){
		return empleadoServices.obtenerEmpleados();
	}
	
	@PostMapping()
	public ResponseEntity<EmpleadoDTO> crearEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate fechaNacimiento = LocalDate.parse(empleadoDTO.getFechaNacimiento(), formatter);
		LocalDate fechaAlta = LocalDate.parse(empleadoDTO.getFechaAlta(), formatter);

		Empleado empleado = new Empleado();
		empleado.setIdEmpleado(empleadoDTO.getIdEmpleado());
		empleado.setNif(empleadoDTO.getNif());
		empleado.setNombre(empleadoDTO.getNombre());
		empleado.setApellido1(empleadoDTO.getApellido1());
		empleado.setApellido2(empleadoDTO.getApellido2());
		empleado.setFechaNacimiento(fechaNacimiento);
		empleado.setTelefono1(empleadoDTO.getTelefono1());
		empleado.setTelefono2(empleadoDTO.getTelefono2());
		empleado.setEmail(empleadoDTO.getEmail());
		empleado.setFechaAlta(fechaAlta);
		empleado.setEstadoCivil(empleadoDTO.getEstadoCivil());
		empleado.setServicioMilitar(empleadoDTO.getServicioMilitar());

		Empleado empleadoGuardado = empleadoServices.guardarEmpleado(empleado);

		EmpleadoDTO empleadoGuardadoDTO = new EmpleadoDTO();
		empleadoGuardadoDTO.setNif(empleadoGuardado.getNif());
		empleadoGuardadoDTO.setNombre(empleadoGuardado.getNombre());
		empleadoGuardadoDTO.setApellido1(empleadoGuardado.getApellido1());
		empleadoGuardadoDTO.setApellido2(empleadoGuardado.getApellido2());
		empleadoGuardadoDTO.setFechaNacimiento(empleadoGuardado.getFechaNacimiento().format(formatter));
		empleadoGuardadoDTO.setTelefono1(empleadoGuardado.getTelefono1());
		empleadoGuardadoDTO.setTelefono2(empleadoGuardado.getTelefono2());
		empleadoGuardadoDTO.setEmail(empleadoGuardado.getEmail());
		empleadoGuardadoDTO.setEstadoCivil(empleadoGuardado.getEstadoCivil());
		empleadoGuardadoDTO.setServicioMilitar(empleadoGuardado.getServicioMilitar());

		return ResponseEntity.ok(empleadoGuardadoDTO);
	}
	

	@GetMapping("/{nif}")
	public ResponseEntity<EmpleadoDTO> buscarEmpleadoPorNif(@PathVariable String nif) {
		Empleado empleado = empleadoServices.buscarEmpleadoPorNif(nif);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		EmpleadoDTO empleadoDTO = new EmpleadoDTO();
		empleadoDTO.setIdEmpleado(empleado.getIdEmpleado());
		empleadoDTO.setNif(empleado.getNif());
		empleadoDTO.setNombre(empleado.getNombre());
		empleadoDTO.setApellido1(empleado.getApellido1());
		empleadoDTO.setApellido2(empleado.getApellido2());
		empleadoDTO.setFechaNacimiento(empleado.getFechaNacimiento().format(formatter).toString());
		empleadoDTO.setFechaAlta(empleado.getFechaAlta().format(formatter).toString());
		if (empleado.getFechaBaja() != null) {
			empleadoDTO.setFechaBaja(empleado.getFechaBaja().format(formatter).toString());
		} else {
			empleadoDTO.setFechaBaja(null);
		}
		empleadoDTO.setTelefono1(empleado.getTelefono1());
		empleadoDTO.setTelefono2(empleado.getTelefono2());
		empleadoDTO.setEmail(empleado.getEmail());
		empleadoDTO.setEstadoCivil(empleado.getEstadoCivil());
		empleadoDTO.setServicioMilitar(empleado.getServicioMilitar());

		return ResponseEntity.ok(empleadoDTO);
	}
	

	@PutMapping("/{nif}")
	public ResponseEntity<EmpleadoDTO> actualizarEmpleado(@PathVariable String nif, @RequestBody EmpleadoDTO empleadoDTO) {
		Empleado empleado = empleadoServices.buscarEmpleadoPorNif(nif);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate fechaNacimiento = LocalDate.parse(empleadoDTO.getFechaNacimiento(), formatter);
		LocalDate fechaAlta = LocalDate.parse(empleadoDTO.getFechaAlta(), formatter);

		empleado.setNif(empleadoDTO.getNif());
		empleado.setNombre(empleadoDTO.getNombre());
		empleado.setApellido1(empleadoDTO.getApellido1());
		empleado.setApellido2(empleadoDTO.getApellido2());
		empleado.setFechaNacimiento(fechaNacimiento);
		empleado.setTelefono1(empleadoDTO.getTelefono1());
		empleado.setTelefono2(empleadoDTO.getTelefono2());
		empleado.setEmail(empleadoDTO.getEmail());
		empleado.setFechaAlta(fechaAlta);
		empleado.setEstadoCivil(empleadoDTO.getEstadoCivil());
		empleado.setServicioMilitar(empleadoDTO.getServicioMilitar());

		Empleado empleadoGuardado = empleadoServices.guardarEmpleado(empleado);

		EmpleadoDTO empleadoGuardadoDTO = new EmpleadoDTO();
		empleadoGuardadoDTO.setNif(empleadoGuardado.getNif());
		empleadoGuardadoDTO.setNombre(empleadoGuardado.getNombre());
		empleadoGuardadoDTO.setApellido1(empleadoGuardado.getApellido1());
		empleadoGuardadoDTO.setApellido2(empleadoGuardado.getApellido2());
		empleadoGuardadoDTO.setFechaNacimiento(empleadoGuardado.getFechaNacimiento().format(formatter));
		empleadoGuardadoDTO.setTelefono1(empleadoGuardado.getTelefono1());
		empleadoGuardadoDTO.setTelefono2(empleadoGuardado.getTelefono2());
		empleadoGuardadoDTO.setEmail(empleadoGuardado.getEmail());
		empleadoGuardadoDTO.setEstadoCivil(empleadoGuardado.getEstadoCivil());
		empleadoGuardadoDTO.setServicioMilitar(empleadoGuardado.getServicioMilitar());

		return ResponseEntity.ok(empleadoGuardadoDTO);
	}
}
