package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

			boolean desordenado = true;
			Atraccion intermediario;
			while (desordenado) {
				desordenado = false;
				for (int i = 0; i < atraccionesList.size() - 1; i++) {
					if (atraccionesList.get(i).getCosto() < atraccionesList.get(i + 1).getCosto()) {
						intermediario = atraccionesList.get(i);
						atraccionesList.set(i, atraccionesList.get(i + 1));
						atraccionesList.set(i + 1, intermediario);
						desordenado = true;
					} else if (atraccionesList.get(i).getCosto() == atraccionesList.get(i + 1).getCosto()) {
						if (atraccionesList.get(i).getTiempo() < atraccionesList.get(i + 1).getTiempo()) {
							intermediario = atraccionesList.get(i);
							atraccionesList.set(i, atraccionesList.get(i + 1));
							atraccionesList.set(i + 1, intermediario);
							desordenado = true;
						}
					}
				}
			}

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
				// EL PRIMER NUMERO DE CADA LINEA DEL ARCHIVO "promociones.txt" DEFINE QUE TIPO
				// DE PROMOCION ES //
				// PORCENTAJE = 0 //
				// ABSOLUTO = 1 //
				// aXb = 2 //

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

		boolean desordenado = true;
		Promocion intermediario;
		while (desordenado) {
			desordenado = false;
			for (int i = 0; i < promocionesList.size() - 1; i++) {
				if (promocionesList.get(i).precioFinal() < promocionesList.get(i + 1).precioFinal()) {
					intermediario = promocionesList.get(i);
					promocionesList.set(i, promocionesList.get(i + 1));
					promocionesList.set(i + 1, intermediario);
					desordenado = true;
				} else if (promocionesList.get(i).precioFinal() == promocionesList.get(i + 1).precioFinal()) {
					if (promocionesList.get(i).tiempoTotalRequerido() < promocionesList.get(i + 1)
							.tiempoTotalRequerido()) {
						intermediario = promocionesList.get(i);
						promocionesList.set(i, promocionesList.get(i + 1));
						promocionesList.set(i + 1, intermediario);
						desordenado = true;
					}
				}
			}
		}
		return promocionesList;
	}

	// ORDENAR POR LAS MAS CARAS Y EN SEGUNDA INSTANCIA LAS QUE REQUEIRAN MAS TIMEPO
	public void ordenarPromociones() {
		boolean desordenado = true;
		Promocion intermediario;
		while (desordenado) {
			desordenado = false;
			for (int i = 0; i < this.promociones.size() - 1; i++) {
				if (this.promociones.get(i).precioFinal() < this.promociones.get(i + 1).precioFinal()) {
					intermediario = this.promociones.get(i);
					this.promociones.set(i, this.promociones.get(i + 1));
					this.promociones.set(i + 1, intermediario);
					desordenado = true;
				} else if (this.promociones.get(i).precioFinal() == this.promociones.get(i + 1).precioFinal()) {
					if (this.promociones.get(i).tiempoTotalRequerido() < this.promociones.get(i + 1)
							.tiempoTotalRequerido()) {
						intermediario = this.promociones.get(i);
						this.promociones.set(i, this.promociones.get(i + 1));
						this.promociones.set(i + 1, intermediario);
						desordenado = true;
					}
				}
			}
		}
	}

	public void ordenarAtracciones() {
		boolean desordenado = true;
		Atraccion intermediario;
		while (desordenado) {
			desordenado = false;
			for (int i = 0; i < this.atracciones.size() - 1; i++) {
				if (this.atracciones.get(i).getCosto() < this.atracciones.get(i + 1).getCosto()) {
					intermediario = this.atracciones.get(i);
					this.atracciones.set(i, this.atracciones.get(i + 1));
					this.atracciones.set(i + 1, intermediario);
					desordenado = true;
				} else if (this.atracciones.get(i).getCosto() == this.atracciones.get(i + 1).getCosto()) {
					if (this.atracciones.get(i).getTiempo() < this.atracciones.get(i + 1).getTiempo()) {
						intermediario = this.atracciones.get(i);
						this.atracciones.set(i, this.atracciones.get(i + 1));
						this.atracciones.set(i + 1, intermediario);
						desordenado = true;
					}
				}
			}
		}
	}

	public void ventaParaUsuarios() {
		Scanner entradaTeclado = new Scanner(System.in); // Creación de un objeto Scanner
		System.out.println("\n     Bienvenido/a a la Guerra de las Galaxias");
		System.out.println("-----------------------------------------------------");
		for (int i = 0; i < this.usuarios.size(); i++) {
			for (int j = 0; j < this.promociones.size(); j++) {
				
				int presupuestoUser = this.usuarios.get(i).getPresupuesto();
				double tiempoUser = this.usuarios.get(i).getTiempo();
				
				int precioFinal = this.promociones.get(j).precioFinal();
				double duracion = this.promociones.get(j).tiempoTotalRequerido();
				
				if (presupuestoUser>precioFinal && tiempoUser>duracion) {

					String respuesta = "";
					boolean respuestaInvalida = true;

					System.out.println("\nNombre de visitante: " + this.usuarios.get(i).getNombre());
					System.out.println("\nPresupuesto: " + presupuestoUser);
					System.out.println("\nTiempo: " + tiempoUser);

					System.out.println("\nPromocion: ");
					System.out.println("-Atracciones incluidas: " + this.promociones.get(j).getAtraccionList());
					System.out.println("-Duración: " + duracion + " horas");
					System.out.println("-Precio original: $" + this.promociones.get(j).precioOriginal());
					System.out.println("-Precio con descuento: $" + precioFinal);

					System.out.println("\nAcepta sugerencia? Ingrese S o N");
					// VALIDO QUE LA RESPUESTA SEA "S" O "N"
					while (respuestaInvalida) {
						respuesta = entradaTeclado.nextLine();
						if (respuesta.equals("S") || respuesta.equals("N")) {
							respuestaInvalida = false;
							break;
						}
						System.out.println("Caracter invalido. Ingrese S o N");
					}

					if (respuesta.equals("S")) {
						// USUARIO
						this.usuarios.get(i).getAtraccionList().addAll(this.promociones.get(j).getAtraccionList());
						this.usuarios.get(i).cobrarDinero(this.promociones.get(j).precioFinal());
						this.usuarios.get(i).cobrarTiempo(this.promociones.get(j).tiempoTotalRequerido());
						// ATRACCION
						for (int k = 0; k < this.promociones.get(j).getAtraccionList().size(); k++) {
							this.promociones.get(j).getAtraccionList(k).cobrarCupo(1);
						}
						this.ordenarPromociones();
						this.ordenarAtracciones();
					}
				}else {
					System.out.println("No hay tiempo o plata");
				}
			}
		}
	}
}
