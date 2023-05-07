package com.practica.core.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.practica.core.entitys.Empleado;

@Repository
public interface EmpleadoRepository extends CrudRepository <Empleado, Integer> {

	public abstract Empleado findByNif (String nif);
	public abstract Empleado findByIdEmpleado (Long idEmpleado);
	public abstract void deleteByNif (String nif);
	
}
