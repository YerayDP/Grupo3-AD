package models;

import java.sql.Date;

public class Clientes extends Users{
	
	private String imagen;

	public Clientes(String dni, String nombre, String apellidos, String rol, String username,
			String password, Date fecha_nacimiento2, String imagen) {
		super(dni, nombre, apellidos, rol, username, password, fecha_nacimiento2);
		this.imagen = imagen;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
	
	

}
