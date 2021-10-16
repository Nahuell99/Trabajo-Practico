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

	public static ArrayList<Atraccion> cargaAtracciones() {
		ArrayList<Atraccion> atraccionesList = new ArrayList<Atraccion>();
		try {
			FileReader input = new FileReader(new File("atracciones.txt"));
			BufferedReader bufInput = new BufferedReader(input);

			String line;

			line = bufInput.readLine();
			String[] datos;

			while (line != null) {

				datos = line.split(";");

				final int idAtraccion = Integer.parseInt(datos[0]);
				String nombre = datos[1];
				int costo = Integer.parseInt(datos[2]);
				double tiempo = Double.parseDouble(datos[3]);
				int cupo = Integer.parseInt(datos[4]);
				atraccionesList.add(new Atraccion(idAtraccion, nombre, costo, tiempo, cupo));
				line = bufInput.readLine();
			}
			bufInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return atraccionesList;
	}

	public static ArrayList<Usuario> cargarUsuarios() {
		ArrayList<Usuario> usuarioList = new ArrayList<Usuario>();
		try {
			FileReader input = new FileReader(new File("usuarios.txt"));
			BufferedReader bufInput = new BufferedReader(input);

			String line;

			line = bufInput.readLine();
			String[] datos;

			while (line != null) {

				datos = line.split(";");

				final int DNI = Integer.parseInt(datos[0]);
				String nombre = datos[1];
				int presupuesto = Integer.parseInt(datos[2]);
				double tiempo = Double.parseDouble(datos[3]);
				usuarioList.add(new Usuario(DNI, nombre, presupuesto, tiempo));
				line = bufInput.readLine();
			}
			bufInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return usuarioList;
	}

	public static ArrayList<Promocion> cargarPromociones(ArrayList<Atraccion> atraccionList) {
		ArrayList<Promocion> promocionesList = new ArrayList<Promocion>();

		try {
			FileReader input = new FileReader(new File("promociones.txt"));
			BufferedReader bufInput = new BufferedReader(input);
			int porcentaje = 0;
			int valorTotal = 0;
			String line;
			line = bufInput.readLine();
			String[] datos;

			while (line != null) {
				
				ArrayList<Atraccion> atraccionesListInsert = new ArrayList<Atraccion>();
				ArrayList<Atraccion> atraccionesGratis = new ArrayList<Atraccion>();

				datos = line.split(";");
				//   EL PRIMER NUMERO DE CADA LINEA DEL ARCHIVO "promociones.txt" DEFINE QUE TIPO DE PROMOCION ES   //
				//   PORCENTAJE  = 0                                                  //
				//   ABSOLUTO    = 1                                                  // 
				//   aXb         = 2                                                  //
				
				
				switch (Integer.parseInt(datos[0])) {
				case 0:
					porcentaje = Integer.parseInt(datos[datos.length - 1]);
					for (int i = 1; i < datos.length - 1; i++) {
						atraccionesListInsert.add(
								atraccionList.get(atraccionList.indexOf(new Atraccion(Integer.parseInt(datos[i])))));

					}
					promocionesList.add(new Porcentual(atraccionesListInsert, porcentaje));

					break;
				case 1:
					valorTotal = Integer.parseInt(datos[datos.length - 1]);
					for (int i = 1; i < datos.length - 1; i++) {
						atraccionesListInsert.add(
								atraccionList.get(atraccionList.indexOf(new Atraccion(Integer.parseInt(datos[i])))));
					}
					promocionesList.add(new Absoluta(atraccionesListInsert, valorTotal));
					break;

				case 2:
					for (int i = 1; i < datos.length; i++) {
						if (datos[i].equals(":")) {
							for (int j = i + 1; j < datos.length; j++) {
								atraccionesGratis.add(atraccionList
										.get(atraccionList.indexOf(new Atraccion(Integer.parseInt(datos[j])))));
							}
							break;
						}
						atraccionesListInsert.add(
								atraccionList.get(atraccionList.indexOf(new Atraccion(Integer.parseInt(datos[i])))));
					}
					promocionesList.add(new AxB(atraccionesListInsert, atraccionesGratis));
					break;
				}
				line = bufInput.readLine();
			}

			bufInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return promocionesList;

	}

}
