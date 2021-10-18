package promociones;

import java.util.ArrayList;
import java.util.Objects;

import main.Atraccion;

public class AxB extends Promocion{
	private ArrayList<Atraccion> atraccionesGratisList;

	public AxB(ArrayList<Atraccion> atraccionList, ArrayList<Atraccion> atraccionesGratisList) {
		super(atraccionList);
		this.atraccionesGratisList = atraccionesGratisList;
	}

	public ArrayList<Atraccion> getAtraccionesGratisList() {
		return atraccionesGratisList;
	}

	public void setAtraccionesGratisList(ArrayList<Atraccion> atraccionesGratisList) {
		this.atraccionesGratisList = atraccionesGratisList;
	}

	@Override
	public String toString() {
		return "AxB [atraccionesGratisList=" + atraccionesGratisList + ", Atracciones" + super.getAtraccionList() + "]\n\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(atraccionesGratisList);
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
		AxB other = (AxB) obj;
		return Objects.equals(atraccionesGratisList, other.atraccionesGratisList);
	}

	public int precioFinal(){
		int sumaCostosAtracciones = 0;
		for (int i = 0;i<super.getAtraccionList().size();i++) {
			sumaCostosAtracciones += super.getAtraccionList(i).getCosto();
		}
		return sumaCostosAtracciones;
	}
	
	public int capacidadPromocion() {
		int cupoMinimo = super.getAtraccionList().get(0).getCupo();
		for (int i = 1;i<super.getAtraccionList().size();i++) {
			if(super.getAtraccionList().get(i).getCupo()<cupoMinimo) {
				cupoMinimo = super.getAtraccionList().get(i).getCupo();
			}
		}
		for (int i = 0;i<this.atraccionesGratisList.size();i++) {
			if(this.atraccionesGratisList.get(i).getCupo()<cupoMinimo) {
				cupoMinimo = this.atraccionesGratisList.get(i).getCupo();
			}
		}
		return cupoMinimo;
	}
	
	public double tiempoTotalRequerido() {
		double sumaTiempoAtracciones = 0;
		for (int i = 0;i<super.getAtraccionList().size();i++) {
			sumaTiempoAtracciones += super.getAtraccionList(i).getTiempo();
		}
		
		for (int i = 0;i<this.atraccionesGratisList.size();i++) {
			sumaTiempoAtracciones += this.atraccionesGratisList.get(i).getTiempo();

		}
				
		return sumaTiempoAtracciones;
	}
	
	public int precioOriginal() {
		int sumaCostosAtracciones = 0;
		for (int i = 0;i<super.getAtraccionList().size();i++) {
			sumaCostosAtracciones += super.getAtraccionList(i).getCosto();
		}
		for (int i = 0;i<this.atraccionesGratisList.size();i++) {
			sumaCostosAtracciones += this.atraccionesGratisList.get(i).getCosto();
		}
		return sumaCostosAtracciones;
	}
	
}
