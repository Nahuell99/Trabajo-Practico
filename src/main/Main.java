package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import promociones.Absoluta;
import promociones.AxB;
import promociones.Porcentual;
import promociones.Promocion;

public class Main {
	public static void main(String args[]) {
		
		Sistema sistema = new Sistema();		
				
		//ArrayList<Atraccion> a = cargaAtracciones();
		//ArrayList<Usuario> u = cargarUsuarios();
		//ArrayList<Promocion> p = cargarPromociones(a);

		//System.out.println(sistema.getAtracciones());
		//System.out.println(sistema.getUsuarios());
	
		for(int i = 0; i<sistema.getPromociones().size();i++) {
			System.out.println( "/////////////" + i + "/////////////");
			System.out.println( sistema.getPromociones().get(i).precioFinal());
			System.out.println( sistema.getPromociones().get(i).capacidadPromocion());
		}

	}

}
