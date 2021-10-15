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
	
}
