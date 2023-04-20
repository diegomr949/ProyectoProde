package Prode;

public class Pronostico {
	//Declaración variables
	private Partido partido;
	private Equipo equipo;
	private ResultadoEnum Resultado;
	
	//Getters and Setters
	public int puntos () {
		return 0;
	}
	public Partido getPartido() {
		return partido;
	}
	public void setPartido(Partido partido) {
		this.partido = partido;
	}
	public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	public ResultadoEnum getResultado() {
		return Resultado;
	}
	public void setResultado(ResultadoEnum resultado) {
		Resultado = resultado;
	}
	
}
