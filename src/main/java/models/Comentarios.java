package models;

public class Comentarios {

	private int id_cliente,id_hotel;
	private String comentario;
	
	
	public Comentarios() {
	}
	
	public Comentarios(int id_cliente, int id_hotel, String comentario) {
		this.id_cliente = id_cliente;
		this.id_hotel = id_hotel;
		this.comentario = comentario;
		
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public int getId_hotel() {
		return id_hotel;
	}
	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	@Override
	public String toString() {
		return "Comentarios [id_cliente=" + id_cliente + ", id_hotel=" + id_hotel + ", comentario=" + comentario + "]";
	}
	
	
	
}
