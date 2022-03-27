package controller;

import java.io.*;
import java.io.IOException;

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
			try(BufferedWriter escritor= new BufferedWriter(new FileWriter("./data/usuarios.txt"))){
				escritor.write(datos);
				escritor.append(name + ";" + correo + ";"+ password);
			}
				
			
		}
		catch(IOException e) {
			
		}
		
	}
}
