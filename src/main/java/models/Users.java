package models;

import java.sql.Date;

public class Users {

	private String dni,nombre,apellidos,rol,username,password;
	private Date fecha_nacimiento;
	public Users(String dni, String nombre, String apellidos, String rol,
			String username, String password, Date fecha_nacimiento2)
	{
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.rol = rol;
		this.username = username;
		this.password = password;
		this.fecha_nacimiento = fecha_nacimiento2;
	}

	public Users() {
		// TODO Auto-generated constructor stub
	}

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	@Override
	public String toString() {
		return "Users [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", poblacion=" + ", rol=" + rol + ", username=" + username + ", password=" + password
				+ ", fecha_nacimiento=" + fecha_nacimiento + "]";
	}
	
}
