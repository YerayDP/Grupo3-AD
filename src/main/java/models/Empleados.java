package models;

import java.sql.Date;

public class Empleados extends Users{

	private String poblacion;
	
	

	public Empleados(String dni, String nombre, String apellidos, String poblacion, String rol,
			String username, String password, String fecha_nacimiento) {
		super(dni, nombre, apellidos, poblacion, rol, username, password, fecha_nacimiento);
	}

	public Empleados(String dni, String nombre, String apellidos, String poblacion, String rol,
			String username, String password, String fecha_nacimiento, String poblacion2) {
		super(dni, nombre, apellidos, poblacion, rol, username, password, fecha_nacimiento);
		poblacion = poblacion2;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	
	
	
}
