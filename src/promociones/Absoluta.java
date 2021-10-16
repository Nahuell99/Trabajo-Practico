package promociones;

import java.util.ArrayList;
import java.util.Objects;

import main.Atraccion;

public class Absoluta extends Promocion {
	private int precioAbsoluto;

	public Absoluta(ArrayList<Atraccion> atraccionList, int precioAbsoluto) {
		super(atraccionList);
		this.precioAbsoluto = precioAbsoluto;
	}

	public int getPrecioAbsoluto() {
		return precioAbsoluto;
	}

	public void setPrecioAbsoluto(int precioAbsoluto) {
		this.precioAbsoluto = precioAbsoluto;
	}

	@Override
	public String toString() {
		return "Absoluta [precioAbsoluto=" + precioAbsoluto + ", Atracciones" + super.getAtraccionList() + "]\n\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(precioAbsoluto);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Absoluta other = (Absoluta) obj;
		return precioAbsoluto == other.precioAbsoluto;
	}

	public int precioFinal(){
		return precioAbsoluto;
	}
	
	public int capacidadPromocion() {
		int cupoMinimo = super.getAtraccionList().get(0).getCupo();
		for (int i = 1;i<super.getAtraccionList().size();i++) {
			if(super.getAtraccionList().get(i).getCupo()<cupoMinimo) {
				cupoMinimo = super.getAtraccionList().get(i).getCupo();
			}
		}
		return cupoMinimo;
	}
	
	public double tiempoTotalRequerido() {
		double sumaTiempoAtracciones = 0;
		for (int i = 0;i<super.getAtraccionList().size();i++) {
			sumaTiempoAtracciones += super.getAtraccionList(i).getTiempo();
		}
		return sumaTiempoAtracciones;
	}
	
}
