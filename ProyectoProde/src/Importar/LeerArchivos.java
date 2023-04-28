package Importar;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entidades.Equipo;
import Entidades.Partido;
import Entidades.Pronostico;
import Entidades.ResultadoEnum;
import Entidades.Ronda;

public class LeerArchivos {

	public static void main(String[] args) throws IOException, SQLException {
		leerArchivos();
		
		leerPronosticosDB();
	}
	
	public static void leerArchivos() throws IOException {

		BufferedReader br = null;

		String path = "C:\\Users\\Diego Moreno Rico\\Desktop\\Diego\\Facultad\\2023\\Argentina programa UTN\\Integrador\\ProyectoProde\\ProyectoProde\\resultados.txt";
		br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));

		Ronda ronda = new Ronda();
		// Lectura
		
		String linea;
		while ((linea = br.readLine()) != null){
			String [] campos = linea.split("-"); //separacion por -
			//asignacion de campos
			String nroRonda = campos[0];	
			String nombreEquipo1 = campos[1];
			String cantGolesEquipo1 = campos[2];
			String cantGolesEquipo2 = campos[3];
			String nombreEquipo2 = campos[4];
			

			ronda.setNro(nroRonda);
			
			Partido partido = new Partido();
			partido.setGolesEquipo1(Integer.valueOf(cantGolesEquipo1));
			partido.setGolesEquipo2(Integer.valueOf(cantGolesEquipo2));
			Equipo equipo1 = new Equipo();
			equipo1.setNombre(nombreEquipo1);
			partido.setEquipo1(equipo1);
			Equipo equipo2 = new Equipo();
			equipo2.setNombre(nombreEquipo2);
			partido.setEquipo2(equipo2);
			
			ronda.addPartido(partido);
			
		}
		
		System.out.println("----- RESULTADOS -----");
		for(Partido p : ronda.getPartidos()){
			System.out.println(p.getEquipo1().getNombre() + " " + p.getGolesEquipo1() + " - " + p.getGolesEquipo2() + " " + p.getEquipo2().getNombre());
		}
		
		
		//pronostico
		path = "C:\\Users\\Diego Moreno Rico\\Desktop\\Diego\\Facultad\\2023\\Argentina programa UTN\\Integrador\\ProyectoProde\\ProyectoProde\\pronosticos.txt";
		br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));

		// Lectura
		List<Pronostico> pronosticos = new ArrayList<Pronostico>();
		while ((linea = br.readLine()) != null){
			String [] campos = linea.split("-");
			String nombreParticipante = campos[0];
			String nombreEquipo1 = campos[1];
			String gano1 = campos[2];
			String empato = campos[3];
			String gano2 = campos[4];
			String nombreEquipo2 = campos[5];
			
			Pronostico pronostico = new Pronostico();
			pronostico.setNombrePersona(nombreParticipante);
			Partido partido = ronda.getPartidoXEquipos(nombreEquipo1, nombreEquipo2);
			pronostico.setPartido(partido);
			if(gano1.equals("X")){
				pronostico.setEquipo(partido.getEquipo1());
				pronostico.setResultado(ResultadoEnum.ganador);
			}else if(gano2.equals("X")){
				pronostico.setEquipo(partido.getEquipo2());
				pronostico.setResultado(ResultadoEnum.ganador);
			}else{
				pronostico.setEquipo(null);
				pronostico.setResultado(ResultadoEnum.empate);
			}
			pronosticos.add(pronostico);
		}
		
		System.out.println("----- PRONOSTICOS -----");
		for(Pronostico prono : pronosticos){
			System.out.println(prono.getNombrePersona());
			System.out.println(prono.getResultado().toString());
			System.out.println(prono.getEquipo() != null ? prono.getEquipo().getNombre() : "");
		}

	}
	
	public static void leerPronosticosDB() throws SQLException{
		Connection conexion = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// URL MySQL
			// "jdbc:mysql://" + host + ":" + puerto + "/"+baseDeDatos;
			String urlConexion = "jdbc:mysql://localhost:3306/argentina_programa_db";
			conexion = DriverManager.getConnection(urlConexion, "root", "123456");

			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("select * from pronosticos");
			while (rs.next()) { 
				//fila
			    System.out.println(rs.getString("participante"));
			    System.out.println(rs.getString("equipo1"));
			    System.out.println(rs.getString("gana1"));
			    System.out.println(rs.getString("empata"));
			    System.out.println(rs.getString("gana2"));
			    System.out.println(rs.getString("equipo2"));
			    System.out.println("----------------");
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conexion != null && !conexion.isClosed())
				conexion.close();
		}
	}
	
}
