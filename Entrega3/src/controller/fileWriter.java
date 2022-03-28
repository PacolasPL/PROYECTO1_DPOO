package controller;

import java.io.*;
import java.io.IOException;
import java.time.*;

import model.integrante;

public class fileWriter {

	
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
	
	
	public void writeProy(String name, integrante Lider) {
			LocalDateTime creacionTime = LocalDateTime.now();
			try (
				BufferedReader lector= new BufferedReader(new FileReader("./data/proyectos.txt"));
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
				try(BufferedWriter escritor= new BufferedWriter(new FileWriter("./data/proyectos.txt"))){
					escritor.write(datos);
					escritor.append(name + ";" + Lider.getName() + ";"+ true+";" + creacionTime.toString() +";"+ "0");
				}
					
				
			}
			catch(IOException e) {
				
			}
			
		}
}
