package com.practica.core.entitys;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.micrometer.common.lang.NonNull;
// import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "EM_EMPLEADOS")
public class Empleado {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	@Column (name = "ID_EMPLEADO", nullable = false)
	private Integer idEmpleado;
	
	
	@Column(name = "TX_NIF")
	private String nif;
	
	@NonNull
	@Column(name = "TX_NOMBRE")
	private String nombre;
	
	@NonNull
    @Column(name = "TX_APELLIDO1")
    private String apellido1;
    
	@NonNull
    @Column(name = "TX_APELLIDO2")
    private String apellido2;
    
	@NonNull
    @Column(name = "F_NACIMIENTO")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate fechaNacimiento ;
    
	@NonNull
    @Column(name = "N_TELEFONO1")
    private String telefono1;
    
	@NonNull
    @Column(name = "N_TELEFONO2")
    private String telefono2;
    
	@NonNull
    @Column(name = "TX_EMAIL")
    private String email;
    
	@JoinTable(name = "pr_proyecto_empleados",
		joinColumns = @JoinColumn(name = "F_ALTA")
		)
	@NonNull
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "F_ALTA")
    private LocalDate fechaAlta;
    
    
    @Column(name = "F_BAJA")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate fechaBaja;
    
    @NonNull
    @Column(name = "CX_EDOCIVIL")
    private String estadoCivil;
    
    @NonNull
    @Column(name = "B_SERVMILITAR")
    private Boolean servicioMilitar;
    
    
   	@ManyToMany
	@JoinTable(name = "empleado_proyecto", 
		joinColumns = @JoinColumn(name = "empleado_id"), 
		inverseJoinColumns = @JoinColumn(name = "proyecto_id"))
    private Set<Proyecto> proyectos = new HashSet<>();

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public LocalDate getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(LocalDate fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Boolean getServicioMilitar() {
		return servicioMilitar;
	}

	public void setServicioMilitar(Boolean servicioMilitar) {
		this.servicioMilitar = servicioMilitar;
	}

	public Empleado(Integer idEmpleado, String nif, String nombre, String apellido1, String apellido2,
			LocalDate fechaNacimiento, String telefono1, String telefono2, String email, 
			LocalDate fechaAlta,
			LocalDate fechaBaja, String estadoCivil, Boolean servicioMilitar) {
		super();
		this.idEmpleado = idEmpleado;
		this.nif = nif;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.email = email;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fechaBaja;
		this.estadoCivil = estadoCivil;
		this.servicioMilitar = servicioMilitar;
	}

							
    

}