package view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import controller.*;
import model.*;

public class aplicacion {
	
	private controladorProyecto controlador;
	integrante usuario;
	
	
	private aplicacion() {
		
	}
	
	public void ejecutarAplicacion() {
		
		boolean continuar = true;
		
		while (continuar) {
			
		System.out.println("---Project Manager---\n");
		System.out.println("Bienvenido\nPara iniciar, elija una de las siguietes opciones:");
		System.out.println("1. Ya pertenezco a un proyecto\n2. Voy a crear un proyecto.\n0. Deseo salir de la aplicacion.");
		
		
		int option = Integer.parseInt(input("-"));
		
		if (option == 1) {
			
			boolean continuar2= true;
			while (continuar2) {
				
				
				
				System.out.println("1. Iniciar sesion. \n2. Registrarse. \n 0.Volver.");
				int option2 =  Integer.parseInt(input("-"));
				if (option2 == 1) {

					String proNa = ejecutarCargarDatos();
					System.out.println("\nDigite sus datos para iniciar sesion en " + proNa);
					System.out.println("\nNombre: \n");
					String nombre = input("-");
					String pass = input("\nPassword:\n-");
					String logrado = controlador.iniciarSesion(nombre, pass);
					System.out.println(logrado);
					
				}
				else if (option2 ==2) {
					fileWriter escritor = new fileWriter();
					
					System.out.println("¿Eres nuevo? Registrate ya.\n");
					System.out.println("Digite sus datos:\n ");
					System.out.println("Nombre: ");
					String nombre = input("-");
					String correo = input("Correo:\n-");
					String pass = input("Password:\n-");
					
					escritor.writeUser(nombre, correo, pass);
					
				}
				else if (option2 == 0 ) {
					continuar2 = false;		
					
				}
				else {
					System.out.println("Seleccione una opcion valida.");
				}
			}
		}
		
		else if (option == 2) {
			
		}
		
		else if (option ==0) {
			continuar = false;		
			
		}
		else {
			System.out.println("Seleccione una opcion valida.");
		}

		
	}
		}
	
	
	private String ejecutarCargarDatos( ){
		
		String archivo = input("¿A que proyecto pertenece?\n-");
		try
		{
			controlador = loaderProyect.cargarArchivo(archivo);
			System.out.println("Se cargó el archivo " + archivo + " con información del proyecto.");
		}
		catch(FileNotFoundException e) {
			System.out.println("ERROR: el archivo indicado no se encontró.");
			
		} 
		catch (IOException e) {
			System.out.println("ERROR: hubo un problema leyendo el archivo.");
			System.out.println(e.getMessage());
			
		}
		return archivo;
		
		
		
	}
	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje );
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		aplicacion app = new aplicacion();
		app.ejecutarAplicacion();
		
	}
}