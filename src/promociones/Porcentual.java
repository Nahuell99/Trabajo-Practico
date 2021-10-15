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

	public void setProcentaje(int procentaje) {
		this.procentaje = procentaje;
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
	
}
