package Prode;

import java.util.ArrayList;
import java.util.List;

public class Ronda {
	
	//Declaración variables
	private String nro;
	private List<Partido> partidos;
	
	
	//Getters and Setters
	public String getNro() {
		return nro;
	}
	public void setNro(String nro) {
		this.nro = nro;
	}
	
	public List<Partido> getPartidos() {
		return partidos;
	}
	public void setPartidos(List<Partido> partidos) {
		this.partidos = partidos;
	}
	public void addPartido(Partido partido){
		if(this.partidos == null){
			this.partidos = new ArrayList<Partido>();
		}
		this.partidos.add(partido);
	}
	
	
	
	public int puntos(){
		
		return 0;
	}
}
