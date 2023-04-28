package conexiondb;

import java.text.ParseException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConexionMySQL {

	public static void main(String[] args) throws ParseException, SQLException {

		Connection conexion = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// URL MySQL
			// "jdbc:mysql://" + host + ":" + puerto + "/"+baseDeDatos;
			String urlConexion = "jdbc:mysql://localhost:3306/utn";
			conexion = DriverManager.getConnection(urlConexion, "root", "123456");

			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("select * from factura");
			while (rs.next()) { 
				//fila
			    System.out.println(rs.getString("letraComprobante"));
			    System.out.println(rs.getLong("nroComprobante"));
			    System.out.println(rs.getDouble("montoNeto"));
			    System.out.println("----------------");
			}
			
			rs = s.executeQuery("select * from producto");
			while (rs.next()) { 
				//fila
			    System.out.println(rs.getLong("id"));
			    System.out.println(rs.getString("codigo"));
			    System.out.println(rs.getString("denominacion"));
			    System.out.println(rs.getDouble("precio"));
			    
			    System.out.println("----------------");
			}
			//insert
			insertarProducto(conexion);
			System.out.println("-TERMINO INSERT-");
			//update
			actualizarProducto(conexion, "777111", 159.65, 24239);
			System.out.println("-TERMINO UPDATE-");
			//delete
			eliminarProducto(conexion, 24239);
			System.out.println("-TERMINO DELETE-");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conexion != null && !conexion.isClosed())
				conexion.close();
		}
	}

	public static void insertarProducto(Connection conexion, String codigo, String denominacion, Double precio, Long idrubro) {
		try {
			
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO producto(codigo, denominacion, precio, idrubro) VALUES (?, ?, ?, ?)");
			ps.setString(1, codigo);
			ps.setString(2, denominacion);
			ps.setDouble(3, precio);
			ps.setLong(4, idrubro);
			
			ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}
	
	public static void insertarProducto(Connection conexion) {
		try {
			
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO producto(codigo, denominacion, precio, idrubro) VALUES ('888999', 'insert de prueba', 50.25, 4)");
			//ps.setString(1, nombre);
			
			ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}
	
	public static void actualizarProducto(Connection conexion, String codigo, double precio, long id) {
		try {
			PreparedStatement ps = conexion.prepareStatement("UPDATE producto SET codigo = ?, precio = ? WHERE id = ?");
			ps.setString(1, codigo);
			ps.setDouble(2, precio);
			ps.setLong(3, id);
			
			ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void eliminarProducto(Connection conexion, long id) {
		try {
			Statement st = conexion.createStatement();
			String sql = "DELETE FROM producto WHERE id="+id;
			int delete = st.executeUpdate(sql);

			if (delete == 1) {
				System.out.println("Producto Borrado");
			} else {
				System.out.println("Producto no Borrado");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}