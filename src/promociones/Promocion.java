package promociones;

import java.util.ArrayList;
import java.util.Objects;

import main.Atraccion;

public abstract class Promocion {
	private ArrayList<Atraccion> atraccionList;

	public Promocion(ArrayList<Atraccion> atraccionList) {
		this.atraccionList = atraccionList;
	}

	public ArrayList<Atraccion> getAtraccionList() {
		return atraccionList;
	}
	
	public Atraccion getAtraccionList(int index) {
		return atraccionList.get(index);
	}

	public void setAtraccionList(ArrayList<Atraccion> atraccionList) {
		this.atraccionList = atraccionList;
	}

	

	@Override
	public String toString() {
		return "Promocion [atraccionList=" + atraccionList + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(atraccionList);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		return Objects.equals(atraccionList, other.atraccionList);
	}
	
	//RETORNA EL CUPO MAS CHICO DEL LISTADO DE ATRACCIONES QUE POSEE LA PROMOCION
	//EN CASO DE SER aXb, EL LISTADO ANALIZADO INCLUYE LAS GRATUITAS
	public abstract int capacidadPromocion();
	
	public abstract double tiempoTotalRequerido();
	
	public abstract int precioOriginal();
	
	public abstract int precioFinal();
	
	public boolean buscarAtraccion(ArrayList<Atraccion> atraccionesUsuario){
		boolean existe = false;
		for(int i = 0;i<atraccionesUsuario.size();i++) {
			if(this.atraccionList.indexOf(atraccionesUsuario.get(i)) > -1) {
				existe = true ;
			}
		}
		return existe;
	}
	
	public void mostrarPromocion() {
		System.out.println("\nPromocion: ");
		System.out.println("-Atracciones incluidas: " + this.atraccionList);
		System.out.println("-Duración: " + this.tiempoTotalRequerido() + " horas");
		System.out.println("-Precio original: $" + this.precioOriginal());
		System.out.println("-Precio con descuento: $" + this.precioFinal());
		System.out.println("-Precio cupo: " + this.capacidadPromocion());
	}
	
}
