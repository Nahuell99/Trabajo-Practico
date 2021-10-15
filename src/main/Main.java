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

public class Main implements Lectura{
	public static void main(String args[]){
		ArrayList<Atraccion> a = cargaAtracciones();
		ArrayList<Usuario> u = cargarUsuarios();
		System.out.println(a);
		System.out.println(u);
		
	}

	
	
	public static ArrayList<Atraccion> cargaAtracciones() {
		ArrayList<Atraccion> atraccionesList = new ArrayList<Atraccion>();
		try{
			FileReader input = new FileReader(new File("atracciones.txt"));
			BufferedReader bufInput = new BufferedReader(input);
			
			String line;
			
			line = bufInput.readLine();
			String [] datos;
			
			while(line != null){
				
				datos = line.split(";");
							
				final int idAtraccion = Integer.parseInt(datos[0]);
				String nombre = datos[1];
				int costo = Integer.parseInt(datos[2]);
				double tiempo = Double.parseDouble(datos[3]);
				int cupo = Integer.parseInt(datos[4]);
				atraccionesList.add(new Atraccion(idAtraccion,nombre,costo,tiempo,cupo));		
				line = bufInput.readLine();
			}
			bufInput.close();
		} catch (IOException e){
			e.printStackTrace();
		}
		return atraccionesList;
	}
	
	public static ArrayList<Usuario> cargarUsuarios() {
		ArrayList<Usuario> usuarioList = new ArrayList<Usuario>();
		try{
			FileReader input = new FileReader(new File("usuarios.txt"));
			BufferedReader bufInput = new BufferedReader(input);
			
			String line;
			
			line = bufInput.readLine();
			String [] datos;
			
			while(line != null){
				
				datos = line.split(";");
				
				final int DNI = Integer.parseInt(datos[0]);
				String nombre = datos[1];
				int presupuesto = Integer.parseInt(datos[2]);
				double tiempo = Double.parseDouble(datos[3]);
				usuarioList.add(new Usuario(DNI, nombre,presupuesto,tiempo));		
				line = bufInput.readLine();
			}
			bufInput.close();
		} catch (IOException e){
			e.printStackTrace();
		}
		return usuarioList;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public static ArrayList<Promocion> cargarPromocionesPRUEBA(ArrayList<Atraccion> atraccionList) {
		ArrayList<Promocion> promocionesList = new ArrayList<Promocion>();
		ArrayList<Atraccion> atraccionesListInsert = new ArrayList<Atraccion>();
		
		try{
			FileReader input = new FileReader(new File("promociones.txt"));
			BufferedReader bufInput = new BufferedReader(input);
			int tipo = 0;
			String line;
			line = bufInput.readLine();
			String [] datos;
			
			while(line != null){
				
				int porcentaje = 0; // VALOR 0
				int valorTotal = 0; // VALOR 1
				ArrayList<String> atraccionesGratis = new ArrayList<String>(); // VALOR 2
				ArrayList<String> atracciones = new ArrayList<String>();
				
				
				datos = line.split(";");
				for(int i=0;i<datos.length;i++) {
					if(datos[i].equals("porcentual")) {
						porcentaje = Integer.parseInt(datos[i+1]);
						tipo = 0;
						break;
					}else if(datos[i].equals("absoluto")) {
						valorTotal = Integer.parseInt(datos[i+1]);
						tipo = 1;
						break;
					}else if(datos[i].equals("aXb")){
						
						for(int j=i+1;j<datos.length;j++) {
							atraccionesGratis.add(datos[j]);
						}
						tipo = 2;
						break;
					}else {
						atracciones.add(datos[i]);
					}
				}
				
				
				for(int i=0;i<atracciones.size();i++) {
					atraccionesListInsert.add(atraccionList.get(atraccionList.indexOf( atracciones.get(i) ) ));	
				}
				
				
				switch(tipo) {
				case 0:
					promocionesList.add(new Porcentual(atraccionesListInsert, porcentaje));
				case 1:
					promocionesList.add(new Absoluta(atraccionesListInsert, valorTotal));
				case 2:
					ArrayList<Atraccion> atraccionesListGratisInsert = new ArrayList<Atraccion>();
					for(int i=0;i<atracciones.size();i++) {
						atraccionesListGratisInsert.add(atraccionList.get(atraccionList.indexOf( atraccionesGratis.get(i) ) ));	
					}
					promocionesList.add(new AxB(atraccionesListInsert, atraccionesListGratisInsert));
				}
				
				line = bufInput.readLine();
			}
			
			
			bufInput.close();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		
		return promocionesList;
		
	}
	
	public static ArrayList<Promocion> cargarPromociones(ArrayList<Atraccion> atraccionList) {
		ArrayList<Promocion> promocionesList = new ArrayList<Promocion>();
		ArrayList<Atraccion> atraccionesListInsert = new ArrayList<Atraccion>();
		
		try{
			FileReader input = new FileReader(new File("promociones.txt"));
			BufferedReader bufInput = new BufferedReader(input);
			int tipo = 0;
			String line;
			line = bufInput.readLine();
			String [] datos;
			
			while(line != null){
				
				int porcentaje = 0; // VALOR 0
				int valorTotal = 0; // VALOR 1
				ArrayList<String> atraccionesGratis = new ArrayList<String>(); // VALOR 2
				ArrayList<String> atracciones = new ArrayList<String>();
				
				
				datos = line.split(";");
				for(int i=0;i<datos.length;i++) {
					if(datos[i].equals("porcentual")) {
						porcentaje = Integer.parseInt(datos[i+1]);
						tipo = 0;
						break;
					}else if(datos[i].equals("absoluto")) {
						valorTotal = Integer.parseInt(datos[i+1]);
						tipo = 1;
						break;
					}else if(datos[i].equals("aXb")){
						
						for(int j=i+1;j<datos.length;j++) {
							atraccionesGratis.add(datos[j]);
						}
						tipo = 2;
						break;
					}else {
						atracciones.add(datos[i]);
					}
				}
				
				
				for(int i=0;i<atracciones.size();i++) {
					atraccionesListInsert.add(atraccionList.get(atraccionList.indexOf( atracciones.get(i) ) ));	
				}
				
				
				switch(tipo) {
				case 0:
					promocionesList.add(new Porcentual(atraccionesListInsert, porcentaje));
				case 1:
					promocionesList.add(new Absoluta(atraccionesListInsert, valorTotal));
				case 2:
					ArrayList<Atraccion> atraccionesListGratisInsert = new ArrayList<Atraccion>();
					for(int i=0;i<atracciones.size();i++) {
						atraccionesListGratisInsert.add(atraccionList.get(atraccionList.indexOf( atraccionesGratis.get(i) ) ));	
					}
					promocionesList.add(new AxB(atraccionesListInsert, atraccionesListGratisInsert));
				}
				
				line = bufInput.readLine();
			}
			
			
			bufInput.close();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		
		return promocionesList;
		
	}
	
}

