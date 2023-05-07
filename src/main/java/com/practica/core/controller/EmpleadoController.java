package com.practica.core.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practica.core.entitys.Empleado;
import com.practica.core.entitys.dto.EmpleadoDTO;
import com.practica.core.exceptions.ExistingResourceException;
import com.practica.core.exceptions.NotNullException;
import com.practica.core.exceptions.ResourceNotFoundException;
import com.practica.core.services.EmpleadoServices;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

	@Autowired
	EmpleadoServices empleadoServices;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping()
	public ArrayList<EmpleadoDTO> obtenerEmpleados() {
		// Convertir todos los empleados a DTO
		ArrayList<Empleado> empleados = empleadoServices.obtenerEmpleados();
		ArrayList<EmpleadoDTO> empleadosDTO = new ArrayList<EmpleadoDTO>();
		for (Empleado empleado : empleados) {
			EmpleadoDTO empleadoDTO = modelMapper.map(empleado, EmpleadoDTO.class);
			if (empleado.getFechaBaja() == null) {
				empleadosDTO.add(empleadoDTO);
			}
		}

		return empleadosDTO;
	}

	@PostMapping()
	public ResponseEntity<EmpleadoDTO> crearEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {

		try {
			String nif = empleadoDTO.getNif();
			Empleado empleadoExistente = empleadoServices.buscarEmpleadoPorNif(nif);

			if (empleadoExistente != null) {
				throw new ExistingResourceException("El empleado con nif " + nif + " ya existe");
			}

			Empleado empleado = modelMapper.map(empleadoDTO, Empleado.class);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			LocalDate fechaNacimiento = LocalDate.parse(empleadoDTO.getFechaNacimiento(), formatter);
			LocalDate fechaAlta = LocalDate.parse(empleadoDTO.getFechaAlta(), formatter);

			empleado.setFechaNacimiento(fechaNacimiento);
			empleado.setFechaAlta(fechaAlta);

			Empleado empleadoGuardado = empleadoServices.guardarEmpleado(empleado);
			EmpleadoDTO empleadoGuardadoDTO = modelMapper.map(empleadoGuardado, EmpleadoDTO.class);
			return ResponseEntity.ok(empleadoGuardadoDTO);
		} catch (DataIntegrityViolationException e) {
			throw new NotNullException(
					"No se ha podido guardar el empleado. Alguno de los campos obligatorios es nulo");
		}

	}

	@ExceptionHandler(NotNullException.class)
	public ResponseEntity<String> handleNotNullException(NotNullException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	@ExceptionHandler(ExistingResourceException.class)
	public ResponseEntity<String> handleExistingResourceException(ExistingResourceException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
	}

	@GetMapping("/{nif}")
	public ResponseEntity<EmpleadoDTO> buscarEmpleadoPorNif(@PathVariable String nif) {

		try {
			Empleado empleado = empleadoServices.buscarEmpleadoPorNif(nif);

			if (empleado == null) {
				throw new ResourceNotFoundException("No se ha encontrado el empleado con NIF: " + nif);
			}

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	@PutMapping("/{nif}")
	public ResponseEntity<EmpleadoDTO> actualizarEmpleado(@PathVariable String nif,
			@RequestBody EmpleadoDTO empleadoDTO) {

		Empleado empleado = empleadoServices.buscarEmpleadoPorNif(nif);

		if (empleado == null) {
			throw new ResourceNotFoundException("Empleado no encontrado con el NIF: " + nif);
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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

		if (empleadoDTO.getFechaBaja() != null) {
			LocalDate fechaBaja = LocalDate.parse(empleadoDTO.getFechaBaja(), formatter);
			empleado.setFechaBaja(fechaBaja);
		} else if (empleadoDTO.getFechaBaja() == null) {
			empleado.setFechaBaja(null);
			empleado.setFechaAlta(LocalDate.now());
		}

		// Actualizamos el empleado en la base de datos
		empleadoServices.actualizarEmpleado(empleado);

		// Utilizamos ModelMapper para mapear los datos de empleado a empleadoDTO
		EmpleadoDTO empleadoActualizadoDTO = modelMapper.map(empleado, EmpleadoDTO.class);

		return ResponseEntity.ok().body(empleadoActualizadoDTO);

	}

	@DeleteMapping("/{nif}")
	public ResponseEntity<String> borrarEmpleado(@PathVariable String nif) {

		try {
			Empleado empleado = empleadoServices.buscarEmpleadoPorNif(nif);

			empleado.setFechaBaja(LocalDate.now());

			empleadoServices.actualizarEmpleado(empleado); // Guarda el empleado actualizado en la base de datos

			return ResponseEntity.ok("Empleado dado de baja con éxito.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
