package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Atraccion {
	private final int idAtraccion;
	private String nombre;
	private int costo;
	private double tiempo;
	private int cupo;
	
	public Atraccion(int idAtraccion,String nombre, int costo, double tiempo, int cupo) {
		super();
		this.idAtraccion = idAtraccion;
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.cupo = cupo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public double getTiempo() {
		return tiempo;
	}

	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}

	public int getCupo() {
		return cupo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	@Override
	public String toString() {
		return "Atraccion [idAtraccion=" + idAtraccion + ", nombre=" + nombre + ", costo=" + costo + ", tiempo=" + tiempo + ", cupo=" + cupo + "]\n";
	}

	@Override
	public int hashCode() {
		return Objects.hash(costo, cupo, nombre, tiempo);
	}

	public boolean equals(String nombreBuscado) {
		return Objects.equals(nombre, nombreBuscado);
	}
	
	
}
