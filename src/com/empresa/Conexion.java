package com.empresa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//dentro de esta clase guardamos todos los datos pertinentes q la conexion contra la base de datos 
public class Conexion {

	// static : cuando algo es static pertenece a la clase y NO a las instancias

//	nombre de la base 
	static String db = "personas";
	// credenciales de acceso a la base
	static String login = "root";
	static String password = "";
	// string de conexion a la base
	static String url = "jdbc:mysql://localhost/" + db;

	// dentroi de este objeto se persiste todo el estado de nuestra conexion a la
	// base
	Connection conn = null;

	public Conexion() {

		try {
			// obtenemos el driver
			Class.forName("com.mysql.jdbc.Driver");

			// Obtengo un objeto Connection mediante el administrador de conexiones
			conn = DriverManager.getConnection(url, login, password);

			if (conn != null) {
				System.out.println("Se Logro conectar Exitosamente a la Base " + db);
			}

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	// metodo para obtener el objeto con la conexion a la db
	public Connection getConnection() {
		return conn;
	}

	public void desconectar() {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("Se cerro la conexion a la base " + db + "exitosamente!");
			} catch (SQLException e) {
				System.out.println("Sucedio un  error al intentar cerrar la conexion con la db " + db);
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Conexion connMySql = new Conexion();
		connMySql.desconectar();
	}

}
