package Entidades;

public class Pronostico {
	//Declaración variables

		
		private String nombrePersona;
		private Partido partido;
		private Equipo equipo;
		
		private ResultadoEnum resultado;
		
		//Getters and Setters
		

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
			return resultado;
		}

		public void setResultado(ResultadoEnum resultado) {
			this.resultado = resultado;
		}
		
		public int puntos(){
			return 0;
		}
		
		public String getNombrePersona() {
			return nombrePersona;
		}

		public void setNombrePersona(String nombrePersona) {
			this.nombrePersona = nombrePersona;
		}

	}
	
	
	
