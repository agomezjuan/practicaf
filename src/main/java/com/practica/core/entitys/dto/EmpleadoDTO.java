package com.practica.core.entitys.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class EmpleadoDTO implements Serializable {
    
    private Integer idEmpleado;
    private String nif;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String fechaNacimiento;

    private String telefono1;
    private String telefono2;
    private String email;

    private String fechaAlta;

    private String fechaBaja;

    private String estadoCivil;
    private Boolean servicioMilitar;

}
