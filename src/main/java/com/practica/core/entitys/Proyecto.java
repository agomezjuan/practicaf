package com.practica.core.entitys;

import java.sql.Date;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PR_PROYECTO")
public class Proyecto {
    
    @Id
    @Column(name = "ID_PROYECTO")
    private Integer idProyecto;
    
    @Column(name = "TX_DESCRIPCION")
    private String descripcion;
    
    @Column(name = "F_INICIO")
    private Date fechaInicio;
    
    @Column(name = "F_FIN")
    private Date fechaFin;
    
    @Column(name = "F_BAJA")
    private Date fechaBaja;
    
    @Column(name = "TX_LUGAR")
    private String lugar;
    
    @Column(name = "TX_OBSERVACIONES")
    private String observaciones;
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    private ArrayList <Empleado> empleados;

	public Integer getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Proyecto(Integer idProyecto, String descripcion, Date fechaInicio, Date fechaFin, Date fechaBaja,
			String lugar, String observaciones) {
		super();
		this.idProyecto = idProyecto;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaBaja = fechaBaja;
		this.lugar = lugar;
		this.observaciones = observaciones;
	}

    
}
