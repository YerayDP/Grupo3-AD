package models;

import java.sql.Date;

public class Reservas {

	private int id_hotel, id_cliente, id_habitacion;
	private Date fecha_entrada, fecha_salida;
	public Reservas(int id_hotel, int id_cliente, int id_habitacion, Date fecha_entrada, Date fecha_salida) {
		super();
		this.id_hotel = id_hotel;
		this.id_cliente = id_cliente;
		this.id_habitacion = id_habitacion;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
	}
	public int getId_hotel() {
		return id_hotel;
	}
	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public int getId_Habitacion() {
		return id_habitacion;
	}
	public void setId_Habitacion(int id_habitacion) {
		this.id_habitacion = id_habitacion;
	}
	public Date getFecha_entrada() {
		return fecha_entrada;
	}
	public void setFecha_entrada(Date fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}
	public Date getFecha_salida() {
		return fecha_salida;
	}
	public void setFecha_salida(Date fecha_salida) {
		this.fecha_salida = fecha_salida;
	}
	
	
	
}
