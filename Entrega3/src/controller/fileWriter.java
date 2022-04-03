package controller;

import java.io.*;
import java.io.IOException;
import java.time.*;
import java.util.*;
import model.*;

import model.integrante;

import java.io.File;

public class fileWriter {

	// La clase fileWriter solo posee m�todos para operar la escritura en archivos.
	
	// Se escribe la informaci�n del nombre, correo y contrase�a de un usuario dentro del archivo de usuarios.
	
	public void writeUser(String name, String correo, String password) {
		
		try (
			BufferedReader lector= new BufferedReader(new FileReader("./data/usuarios.txt"));
		){
			String linea = lector.readLine();
			String datos = "";
			while (linea !=null) {
				datos+= linea + "\n";
				String[] partes = linea.split(";");
				
				if ( name.equals(partes[0])) {
					System.out.println("ERROR. Ese nombre ya existe.");
					return;
				}
				
				linea = lector.readLine();
			}
			lector.close();
			try(BufferedWriter escritor= new BufferedWriter(new FileWriter("./data/usuarios.txt"))){
				escritor.write(datos);
				escritor.append(name + ";" + correo + ";"+ password);
				escritor.close();
			}
				
			
		}
		catch(IOException e) {
			
		}
		
	}
	
	// Se actualiza la informaci�n de un integrante dado en el archivo de usuarios.
	
	public void actualizarUsuario(integrante amigo) throws IOException {
		
		String name =  amigo.getName();
		String[] proyectos = amigo.getProyect().replace("[","").replace("]","").split(",");
		String agregar = "";
		for (int i = 0 ; i < proyectos.length ; i ++) {
			if (amigo.isLider(proyectos[i].strip())) 
			{
				agregar += proyectos[i].strip()+ ":true";
			}
			else {
				agregar += proyectos[i].strip()+ ":false";
			}
			if (i+1 != proyectos.length) 
			{
				agregar += ",";
			}
			
		}
		BufferedReader lector= new BufferedReader(new FileReader("./data/usuarios.txt"));
		String linea = lector.readLine();
		String datos =  "";
		while(linea!= null) {
			
			String[] div =  linea.split(";"); 
			if (div[0].strip().equals(name)) {
				datos +=  div[0]+";" + div[1]+";" + div[2] + ";" +agregar  ;
			}
			else {
				datos += linea;
				
			}
			datos += "\n";
			linea = lector.readLine();
		}
		lector.close();
		BufferedWriter escritor =  new BufferedWriter(new FileWriter("./data/usuarios.txt"));
		escritor.write(datos);
		escritor.close();
		
	}
	
	// Se escribe la informaci�n de un proyecto en el archivo de proyectos.
	
	public void writeProy(String name, integrante Lider) throws IOException {
			LocalDateTime creacionTime = LocalDateTime.now();
			try (
				BufferedReader lector = new BufferedReader(new FileReader("./data/proyectos.txt"));
			){
				String linea = lector.readLine();
				String datos = "";
				
				while (linea != null) {
					datos+= linea + "\n";
					String[] partes = linea.split(";");
					
					if ( name.equals(partes[0])) {
						System.out.println("ERROR. Ese nombre ya existe.");
					}
					
					boolean result = crearArchivos(name);
					
					if(result) {
						System.out.println("Los archivos fueron creados con �xito.");
					}
					else {
						System.out.println("Alguno de los 3 archivos anteriores no pudo ser creado");
					}
					linea = lector.readLine();
				}
				
			try(BufferedWriter escritor= new BufferedWriter(new FileWriter("./data/proyectos.txt"))){
					escritor.write(datos);
					escritor.append(name + ";" + Lider.getName() + ";"+ true+";" + creacionTime.toString() +";"+ "0");
				}
					
				
			}
			
		}
	
	// Se actualiza la informaci�n de un proyecto dentro del archivo de proyectos.
	
	public void actualizarProy(String name, integrante Lider,String inicio, int TiempoTranscurrido ) throws IOException {

		try (
			BufferedReader lector = new BufferedReader(new FileReader("./data/proyectos.txt"));
		){
			String linea = lector.readLine();
			String datos = "";
			
			while (linea != null) {
				
				String[] partes = linea.split(";");
				
				if ( name.equals(partes[0])) {
					String temp = name +";" +  Lider.getName() + ";"+ "true"  +";" + inicio.toString() + ";" + Integer.toString(TiempoTranscurrido) + "\n";
					datos += temp;
				}
				else {
					datos+= linea + "\n";
				}
				linea = lector.readLine();
			}
			
		try(BufferedWriter escritor= new BufferedWriter(new FileWriter("./data/proyectos.txt"))){
				escritor.write(datos);
				
			}
				
			
		}
		
	}
	
	// Se crean los archivos de un proyecto espec�fico.
	
	public boolean crearArchivos(String name) throws IOException {
		try {
			String dir = System.getProperty("user.dir");
			File archivoPrincipal = new File(dir + "/data/" + name + ".txt");
			File archivoActividades = new File(dir + "/data/" + name + "_actividades.txt");
			File archivoIntegrantes = new File(dir + "/data/" + name + "_integrantes.txt");
			boolean seCreoP = archivoPrincipal.createNewFile();
			boolean seCreoA = archivoActividades.createNewFile();
			boolean seCreoI = archivoIntegrantes.createNewFile();
			return (seCreoP & seCreoA & seCreoI);
		}
		catch(IOException e) {
			System.out.println("ERROR. IOException");
			return false;
		}
	}
	
	// Se a�ade la informaci�n predeterminada a los archivos creados previamente.
	
	public void createFirstData(File aI, File aA, integrante usuario) throws IOException {
		String caminoI = aI.getAbsolutePath();
		String caminoA = aA.getAbsolutePath();
		
		BufferedWriter escritorI =  new BufferedWriter(new FileWriter(caminoI));
		escritorI.write(usuario.getName() + "\n");
		escritorI.close();
		
		BufferedWriter escritorA =  new BufferedWriter(new FileWriter(caminoA));
		escritorA.write("Creacion_proyecto;administrativo;" + usuario.getName() + ";0;false");
		escritorA.close();
	}
	
	public void updateActivities(String name, HashMap<String, ArrayList<actividad>> pendientes , HashMap<String,ArrayList<actividad>> actividades,boolean pass, boolean doNo) throws IOException {
		String toWrite = "";
		String[] parts= actividades.keySet().toString().replace("[","").replace("]" ,"").split(",");
		System.out.println( actividades.keySet().toString());
		if (pass) {
			for (int i = 0; i< parts.length; i++) {
				ArrayList<actividad> listTemp= actividades.get(parts[i].strip());
				if (listTemp != null) {
				for (int j = 0; j< listTemp.size(); j++) {
					actividad act = listTemp.get(j);
					System.out.println("\n Guardnado cambios en " + act.getName() +"\n");
					toWrite += act.getName() + ";"+ act.getTipoActividad()+ ";" + act.getPrincipal()+ ";" + Integer.toString(act.getTiempoTranscurrido()) + ";" + "true\n";
				}
				}
			}
		}
		parts= pendientes.keySet().toString().replace("[","").replace("]" ,"").split(",");
		if (doNo) {
			for (int i = 0; i< parts.length; i++) {
				ArrayList<actividad> listTemp= pendientes.get(parts[i].strip());
				if (listTemp != null) {
				for (int j = 0; j< listTemp.size(); j++) {
					actividad act = listTemp.get(j);
					System.out.println("\n Guardnado cambios en " + act.getName() +"\n");
					toWrite += act.getName() + ";"+ act.getTipoActividad()+ ";" + act.getPrincipal()+ ";" + Integer.toString(act.getTiempoTranscurrido()) + ";" + "false\n";
				}
				}
			}
		}
		
		BufferedWriter escritor  =  new BufferedWriter (new FileWriter("./data/" + name + "_actividades.txt"));
		
		escritor.write(toWrite);
		escritor.close();
		
		
		
	}
	
	public void actualizarIntegrantes(String name , String[] names) throws IOException {
		
		
		String temp = "";
		for (int i = 0;  i<names.length ; i++) {
			temp+= names[i].strip() + "\n";
			
		}
		BufferedWriter escritor  =  new BufferedWriter (new FileWriter("./data/" + name + "_integrantes.txt"));
		
		escritor.write(temp);
		escritor.close();
		
	}
	
}
