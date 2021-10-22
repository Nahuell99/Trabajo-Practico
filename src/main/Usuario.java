package main;

import java.util.ArrayList;
import java.util.Objects;

public class Usuario {
	private final int DNI;
	private String nombre;
	private int presupuesto;
	private final int presupuestoOriginal;
	private double tiempo;
	private final double tiempoOriginal;
	private ArrayList<Atraccion> atraccionList;

	public Usuario(int DNI, String nombre, int presupuesto, double tiempo) {
		super();
		this.DNI = DNI;
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.presupuestoOriginal = presupuesto;
		this.tiempo = tiempo;
		this.tiempoOriginal = tiempo;
		this.atraccionList = new ArrayList<Atraccion>();
	}

	public int getDNI() {
		return DNI;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public void cobrarDinero(int cobro) {
		this.presupuesto -= cobro;
	}

	public double getTiempo() {
		return tiempo;
	}

	public void cobrarTiempo(double cobro) {
		this.tiempo -= cobro;
	}

	public ArrayList<Atraccion> getAtraccionList() {
		return atraccionList;
	}

	@Override
	public String toString() {
		return "Usuario [DNI=" + DNI + ", nombre=" + nombre + ", presupuesto=" + presupuesto + ", tiempo=" + tiempo
				+ ", atraccionList=" + atraccionList + "]\n";
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

	public void mostrarUsuario() {
		System.out.println("\nNombre de visitante: " + this.nombre);
		System.out.println("Tiempo: " + this.tiempo + " horas");
		System.out.println("Presupuesto: $" + this.presupuesto);
	}
	
	public double tiempoOcupado() {
		double tiempo = 0;
		for (Atraccion atraccion : atraccionList) {
			tiempo += atraccion.getTiempo();
		}
		return tiempo;
	}
	
	public double dineroPagar() {
		double tiempo = 0;
		for (Atraccion atraccion : atraccionList) {
			tiempo += atraccion.getTiempo();
		}
		return tiempo;
	}

	public String retornarUsuario() {
		return    "Nombre de visitante: " + this.nombre
				+ "\nDNI: " + this.DNI 
				+ "\n\nTiempo libre original: " + this.tiempoOriginal + " horas"
				+ "\nTiempo libre final: " + this.tiempo + " horas" 
				+ "\nPresupuesto original: $" + this.presupuestoOriginal
				+ "\nPresupuesto final: $" + this.presupuesto 
				+ "\nAtracciones compradas:" + atraccionList 
				+ "\n\nTotal a Pagar:" + (this.presupuestoOriginal - this.presupuesto)
				+ "\nTiempo a invertir:" + tiempoOcupado();
	}
}
