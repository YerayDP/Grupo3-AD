package models;

public class Habitaciones {

	private int numHabitacion, precioNoche;
	private String tipo, extras;
	
	public Habitaciones() {
		
		super();
	}
	
	public Habitaciones(int numHabitacion, int precioNoche, String tipo, String extras) {
		super();
		this.numHabitacion = numHabitacion;
		this.precioNoche = precioNoche;
		this.tipo = tipo;
		this.extras = extras;
	}
	public int getNumHabitacion() {
		return numHabitacion;
	}
	public void setNumHabitacion(int numHabitacion) {
		this.numHabitacion = numHabitacion;
	}
	public int getPrecioNoche() {
		return precioNoche;
	}
	public void setPrecioNoche(int precioNoche) {
		this.precioNoche = precioNoche;
	}
	@Override
	public String toString() {
		return "Habitaciones [numHabitacion=" + numHabitacion + ", precioNoche=" + precioNoche + ", tipo=" + tipo
				+ ", extras=" + extras + "]";
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getExtras() {
		return extras;
	}
	public void setExtras(String extras) {
		this.extras = extras;
	}
	
}
