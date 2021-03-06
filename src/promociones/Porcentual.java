package promociones;

import java.util.ArrayList;
import java.util.Objects;
import main.Atraccion;

public class Porcentual extends Promocion{
	private int procentaje;

	public Porcentual(ArrayList<Atraccion> atraccionList, int procentaje) {
		super(atraccionList);
		this.procentaje = procentaje;
	}

	public int getProcentaje() {
		return procentaje;
	}

	@Override
	public String toString() {
		return "Porcentual [procentaje=" + procentaje + ", Atracciones" + super.getAtraccionList() + "]\n\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(procentaje);
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
		Porcentual other = (Porcentual) obj;
		return procentaje == other.procentaje;
	}
	
	public int precioFinal(){
		int sumaCostosAtracciones = precioOriginal();
		return sumaCostosAtracciones-(procentaje*sumaCostosAtracciones)/100;
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

	@Override
	public double tiempoTotalRequerido() {
		double sumaTiempoAtracciones = 0;
		for (int i = 0;i<super.getAtraccionList().size();i++) {
			sumaTiempoAtracciones += super.getAtraccionList(i).getTiempo();
		}
		return sumaTiempoAtracciones;
	}

	@Override
	public int precioOriginal() {
		int sumaCostosAtracciones = 0;
		for (int i = 0;i<super.getAtraccionList().size();i++) {
			sumaCostosAtracciones += super.getAtraccionList(i).getCosto();
		}
		return sumaCostosAtracciones;
	}
	
	
}
