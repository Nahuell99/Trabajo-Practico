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
	
	public Atraccion(int idAtraccion) {
		super();
		this.idAtraccion = idAtraccion;
		this.nombre = null;
		this.costo = 0;
		this.tiempo = 0;
		this.cupo = 0;
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
	public void cobrarCupo(int cobro) {
		this.cupo -= cobro;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idAtraccion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return idAtraccion == other.idAtraccion;
	}

		
	
}
