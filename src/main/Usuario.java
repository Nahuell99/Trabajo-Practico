package main;

import java.util.ArrayList;
import java.util.Objects;

public class Usuario {
	private final int DNI;
	private String nombre;
	private int presupuesto;
	private double tiempo;
	private ArrayList<Atraccion> atraccionList;
	
	public Usuario(int DNI,String nombre, int presupuesto, double tiempo) {
		super();
		this.DNI = DNI;
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempo = tiempo;
		this.atraccionList = new ArrayList<Atraccion>();
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPresupuesto() {
		return presupuesto;
	}
	public void setPresupuesto(int presupuesto) {
		this.presupuesto = presupuesto;
	}
	public void cobrarDinero(int cobro) {
		this.presupuesto -= cobro;
	}
	public double getTiempo() {
		return tiempo;
	}
	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}
	public void cobrarTiempo(double cobro) {
		this.tiempo -= cobro;
	}
	public ArrayList<Atraccion> getAtraccionList() {
		return atraccionList;
	}
	public void setAtraccionList(ArrayList<Atraccion> atraccionList) {
		this.atraccionList = atraccionList;
	}

	@Override
	public String toString() {
		return "Usuario [DNI=" + DNI + ", nombre=" + nombre + ", presupuesto=" + presupuesto + ", tiempo=" + tiempo + ", atraccionList="
				+ atraccionList + "]\n";
	}

	@Override
	public int hashCode() {
		return Objects.hash(atraccionList, nombre, presupuesto, tiempo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(atraccionList, other.atraccionList) && Objects.equals(nombre, other.nombre)
				&& presupuesto == other.presupuesto
				&& Double.doubleToLongBits(tiempo) == Double.doubleToLongBits(other.tiempo);
	}
	
}
