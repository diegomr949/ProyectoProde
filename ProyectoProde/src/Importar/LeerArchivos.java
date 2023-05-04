package Importar;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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

	private static final String SQLException = null;
	private static final String UnsupportedEncodingException = null;
	private static final String FileNotFoundException = null;
	private static final String[] String = null;


	public static void main(String[] args) throws IOException, SQLException {
		leerArchivos();
		
		leerPronosticosDB_mariana();
		leerPronosticosDB_pedro();
	}
	
	public static void leerArchivos() throws IOException {

		BufferedReader br = null;

		String path = "C:\\Users\\Diego Moreno Rico\\Desktop\\Diego\\Facultad\\2023\\Argentina programa UTN\\Integrador\\ProyectoProde\\ProyectoProde\\resultados.txt";
		BufferedReader bufferedReader = br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));

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
		
		
	}
	//Pronosticos de Mariana
	
	public static void leerPronosticosDB_mariana() throws SQLException, UnsupportedEncodingException, FileNotFoundException, IOException{
		Connection conexion = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// URL MySQL
			// "jdbc:mysql://" + host + ":" + puerto + "/"+baseDeDatos;
			String urlConexion = "jdbc:mysql://localhost:3306/integrador";
			conexion = DriverManager.getConnection(urlConexion, "root", "123456");

			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("select * from pronosticos_mariana");
			System.out.println("Los pronosticos de Mariana son:");
			while (rs.next()) { 
			
		
			    System.out.println("Partido numero " + rs.getString("partido"));
			    System.out.println("Resultado es " + rs.getString("resultado"));
			  
					
			}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conexion != null && !conexion.isClosed())
				conexion.close();
		}
		}
		
	
	
	
	
	//Pronosticos de Pedro
		
		public static void leerPronosticosDB_pedro() throws SQLException, UnsupportedEncodingException, FileNotFoundException, IOException{
			Connection conexion = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				// URL MySQL
				// "jdbc:mysql://" + host + ":" + puerto + "/"+baseDeDatos;
				String urlConexion = "jdbc:mysql://localhost:3306/integrador";
				conexion = DriverManager.getConnection(urlConexion, "root", "123456");

				Statement s = conexion.createStatement();
				ResultSet rs = s.executeQuery("select * from pronosticos_pedro");
				System.out.println("Los pronosticos de Pedro son:");
				while (rs.next()) { 
					//fila
					
				    System.out.println("Partido numero " + rs.getString("partido"));
				    System.out.println("Resultado es " + rs.getString("resultado"));
				}
				
					
			
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (conexion != null && !conexion.isClosed())
					conexion.close();
			}
			
			
			
			int puntosmariana;
			int puntospedro;
			
			
		
	}
	
	
}
