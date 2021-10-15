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
		return "Absoluta [precioAbsoluto=" + precioAbsoluto + "]";
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
	
}
