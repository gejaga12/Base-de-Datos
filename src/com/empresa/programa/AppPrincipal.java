package com.empresa.programa;

import javax.swing.JOptionPane;

import com.empresa.daos.PersonaDao;
import com.empresa.modelo.PersonaVO;

public class AppPrincipal {
//tarea crear metodo para emprolijar esta clase !
	public static void main(String[] args) {
		System.out.println("Inicio de la app");
		PersonaDao pDao = new PersonaDao();

		
		// solicitamos los datos al user
		String idaBorrar = JOptionPane.showInputDialog("Ingrese la id de la persona que quiere borrar en el sistema");
		
		
		pDao.borrarPersona(idaBorrar);
		
		
		
		
		
		// solicitamos los datos al user
		Integer idaBuscar = Integer
				.parseInt(JOptionPane.showInputDialog("Ingrese la id de la persona que quiere buscar en el sistema"));

		PersonaVO personita = pDao.buscarPersona(idaBuscar);

		if (personita != null) {
			JOptionPane.showMessageDialog(null, personita.toString());
		} else {
			JOptionPane.showMessageDialog(null, "no existe persona con " + idaBuscar + " en el sistema");
		}

		// solicitamos los datos al user
		Integer id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la id de la persona"));
		String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la persona");
		Integer edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad de la persona"));
		String profesion = JOptionPane.showInputDialog("Ingrese la profesion nombre de la persona");
		Integer telefono = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el telefono de la persona"));

		PersonaVO pvo = new PersonaVO(id, nombre, edad, profesion, telefono);

		// pDao.registrarPersona(pvo);
		pDao.modificarPersona(pvo);

		
		
		
		
		System.out.println("Fin de la app");
	}

}

