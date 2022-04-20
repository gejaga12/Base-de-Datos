package com.empresa.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.empresa.Conexion;
import com.empresa.modelo.PersonaVO;

//Data access object
//CRUD
//Cuando utilizamos estas clases, aplicamos el patrón Data Access Object o DAO,
//básicamente este patrón consiste en centralizar los procesos de acceso a la base de datos
//evitando inconsistencias y posibles problemáticas cuando esto se realiza a lo largo de la aplicación.
//Con este patrón independizamos la lógica de negocio de la lógica de acceso a datos obteniendo mayor 
//organización y flexibilidad en el sistema.

public class PersonaDao {

	public void registrarPersona(PersonaVO miPErsona) {
		// me conecto a la base de datos
		Conexion conex = new Conexion();

		try {

			Statement estatuto = conex.getConnection().createStatement();
//			INSERT INTO `persona`(`id`, `nombre`, `edad`, `profesion`, `telefono`) VALUES ('5','daniela','47','CEO','45636544');
			estatuto.executeUpdate("INSERT INTO persona (`nombre`, `edad`, `profesion`, `telefono`) VALUES ('"
					+ miPErsona.getNombrePersona() + "',' " + miPErsona.getEdadPersona() + "',' "
					+ miPErsona.getProfesionPersona() + "',' " + miPErsona.getTelefonoPersona() + "')");

			JOptionPane.showMessageDialog(null, "Se ah registrado Exitosamente", "Informacion",
					JOptionPane.INFORMATION_MESSAGE);

			// liberamos recursos
			estatuto.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println("no se logro crear el Statement contra la DB ");
			e.printStackTrace();
		}

	}

	public PersonaVO buscarPersona(int codigo) {
		// me conecto a la base de datos
		Conexion conex = new Conexion();
		PersonaVO persona = new PersonaVO();
		boolean existe = false;

		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM persona where id = ?");
			consulta.setInt(1, codigo);

			ResultSet res = consulta.executeQuery(); // ejecutamos la consulta

			while (res.next()) {
				existe = true;

				// cargo el objeto persona con la data de la base
				persona.setIdPersona(Integer.parseInt(res.getString("id")));
				persona.setNombrePersona(res.getString("nombre"));
				persona.setEdadPersona(Integer.parseInt(res.getString("edad")));
				persona.setProfesionPersona(res.getString("profesion"));
				persona.setTelefonoPersona(Integer.parseInt(res.getString("telefono")));

			}

			// liberamos recursos
			consulta.close();
			conex.desconectar();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erroooor", "Informacion", JOptionPane.INFORMATION_MESSAGE);

			System.out.println("no se logro crear el Prepared Statement contra la DB ");
			e.printStackTrace();
		}

		if (existe) {
			return persona;
		} else {
			return null;
		}

	}

	public void modificarPersona(PersonaVO miPErsona) {
		// me conecto a la base de datos
		Conexion conex = new Conexion();

		try {
			String consulta = "UPDATE persona SET nombre = ? ,edad = ? , profesion = ? ,telefono = ?  WHERE id = ? ";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);

			estatuto.setString(1, miPErsona.getNombrePersona());
			estatuto.setInt(2, miPErsona.getEdadPersona());
			estatuto.setString(3, miPErsona.getProfesionPersona());
			estatuto.setInt(4, miPErsona.getTelefonoPersona());
			estatuto.setInt(5, miPErsona.getIdPersona());

			estatuto.executeUpdate();

			JOptionPane.showMessageDialog(null, "Se ah modificado Exitosamente el registro ", "Informacion",
					JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erroooor", "Informacion", JOptionPane.INFORMATION_MESSAGE);

			System.out.println("no se logro crear el Prepared Statement contra la DB ");
			e.printStackTrace();

		} finally {
			conex.desconectar();
		}
	}

public void borrarPersona(String codigo) {

		if (buscarPersona(Integer.parseInt(codigo)) != null) { // valido que exista ese id en la base

			// me conecto a la base de datos
			Conexion conex = new Conexion();

			try {
				Statement estatuto = conex.getConnection().createStatement();

				estatuto.executeUpdate("DELETE FROM persona WHERE id = '" + codigo + "'");
				JOptionPane.showMessageDialog(null, "se ah eliminado Correctamente", "Infromacion",
						JOptionPane.INFORMATION_MESSAGE);
				estatuto.close();
				conex.desconectar();

			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "No se elimino el registro");
			}
		} else {
			JOptionPane.showMessageDialog(null, "No Existia el registro con id " + codigo);
		}
	}

}