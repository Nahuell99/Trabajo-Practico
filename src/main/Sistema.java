package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import promociones.Absoluta;
import promociones.AxB;
import promociones.Porcentual;
import promociones.Promocion;

public class Sistema {
	private ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private ArrayList<Promocion> promociones = new ArrayList<Promocion>();
	
	public Sistema() {
		this.atracciones = cargaAtracciones();
		this.usuarios = cargarUsuarios();
		this.promociones = cargarPromociones(atracciones);
	}
	
	public ArrayList<Atraccion> getAtracciones() {
		return atracciones;
	}

	public void setAtracciones(ArrayList<Atraccion> atracciones) {
		this.atracciones = atracciones;
	}
	
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public ArrayList<Promocion> getPromociones() {
		return promociones;
	}

	public void setPromociones(ArrayList<Promocion> promociones) {
		this.promociones = promociones;
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