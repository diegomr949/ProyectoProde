package Entidades;

import java.util.ArrayList;
import java.util.List;

public class Ronda {
	
	//Declaración variables

	private List<Partido> partidos;
	private String nro;
	
	
	
	//Getters and Setters
	public List<Partido> getPartidos() {
		return partidos;
	}
	public void setPartidos(List<Partido> partidos) {
		this.partidos = partidos;
	}

	
	public String getNro() {
		return nro;
	}
	public void setNro(String nro) {
		this.nro = nro;
	}
	
	public int puntos(){
		//codigo
		return 0;
	}
	
	//Agregar partidos
	public void addPartido(Partido partido){
		if(this.partidos == null){
			this.partidos = new ArrayList<Partido>();
		}
		this.partidos.add(partido);
	}
	
	
	public Partido getPartidoXEquipos(String equipo1, String equipo2){
		if(this.getPartidos() != null){
			for(Partido p : this.getPartidos()){
				if(p.getEquipo1().getNombre().equals(equipo1) 
						&& p.getEquipo2().getNombre().equals(equipo2)){
					return p;
				}
			}
		}
		return null;
	}
}
	
	
	
