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

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public ArrayList<Promocion> getPromociones() {
		return promociones;
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
			// OFRESCO PROMOCIONES
			this.usuarios.get(i).mostrarUsuario();
			for (int j = 0; j < this.promociones.size(); j++) {

				int presupuestoUser = this.usuarios.get(i).getPresupuesto();
				double tiempoUser = this.usuarios.get(i).getTiempo();

				int precioFinal = this.promociones.get(j).precioFinal();
				double duracion = this.promociones.get(j).tiempoTotalRequerido();
				
				if ((presupuestoUser > precioFinal) && (tiempoUser > duracion)
						&& (this.promociones.get(j).capacidadPromocion() > 0)
						&& !this.promociones.get(j).buscarAtraccion(this.usuarios.get(i).getAtraccionList())) {

					String respuesta = "";
					boolean respuestaInvalida = true;
					
					this.promociones.get(j).mostrarPromocion();

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
						this.usuarios.get(i).cobrarDinero(precioFinal);
						this.usuarios.get(i).cobrarTiempo(duracion);
						// ATRACCION
						for (int k = 0; k < this.promociones.get(j).getAtraccionList().size(); k++) {
							this.promociones.get(j).getAtraccionList(k).cobrarCupo(1);
						}
						this.ordenarPromociones();
						this.ordenarAtracciones();
						this.usuarios.get(i).mostrarUsuario();
					}
				} else {
					
					
				}
			} // CIERRE FOR PROMOCIONES

			// OFRESCO ATRACCIONES
			for (int j = 0; j < this.atracciones.size(); j++) {
				int presupuestoUser = this.usuarios.get(i).getPresupuesto();
				double tiempoUser = this.usuarios.get(i).getTiempo();

				int costo = this.atracciones.get(j).getCosto();
				double duracion = this.atracciones.get(j).getTiempo();
				

				if (presupuestoUser > costo && tiempoUser > duracion && this.atracciones.get(j).getCupo() > 0
						&& !(this.usuarios.get(i).getAtraccionList().indexOf(this.atracciones.get(j)) > -1)) {
					String respuesta = "";
					boolean respuestaInvalida = true;
					
					this.atracciones.get(j).mostrarAtraccion();
					
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
						this.usuarios.get(i).getAtraccionList().add(this.atracciones.get(j));
						this.usuarios.get(i).cobrarDinero(costo);
						this.usuarios.get(i).cobrarTiempo(duracion);
						// ATRACCION
						this.atracciones.get(j).cobrarCupo(1);

						this.ordenarPromociones();
						this.ordenarAtracciones();
						this.usuarios.get(i).mostrarUsuario();
					}
				} 
			} // CIERRE FOR ATRACCIONES
			System.out.println(this.usuarios.get(i).getNombre() + "Se a quedado sin dinero o tiempo");
		} // CIERRE FOR USUARIOS
	} // CIERRE FUNCION

}
