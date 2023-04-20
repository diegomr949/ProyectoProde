package Prode;

import java.util.List;

public class Equipo {
	
	//Declaración variables
	private String nombre;
	private String descripcion;
	private List<Partido> partidos;
	
	//Getters and Setters
	public List<Partido> getPartidos() {
		return partidos;
	}
	public void setPartidos(List<Partido> partidos) {
		this.partidos = partidos;
	}
	private String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
