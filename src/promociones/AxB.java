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
		return "AxB [atraccionesGratisList=" + atraccionesGratisList + "]";
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
	
}
