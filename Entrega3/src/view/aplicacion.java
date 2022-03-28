package view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.*;
import model.*;

public class aplicacion {
	
	private controladorProyecto controlador = new controladorProyecto();;
	private integrante usuario;
	

	
	
	private aplicacion() {
		
	}
	
	public void ejecutarAplicacion() throws FileNotFoundException, IOException {
		boolean continuar = true;
		
		while (continuar) {
			
			System.out.println("---Project Manager---\n");
			System.out.println("Bienvenido\nPara iniciar, elija una de las siguietes opciones:");
			System.out.println("1. Ya tengo cuenta.\n2. Voy a crear un proyecto.\n0. Deseo salir de la aplicacion.");
			
			
			int option = 100;
			boolean seguir  = true;
			if (usuario != null) { 
				while (seguir) {
						
						
						System.out.println("---Project Manager---\n");
						System.out.println("Bienvenido, " + usuario.getName() + ".\n\n¿Que deseas hacer el dia de hoy?");
						System.out.println("1. Crear proyecto nuevo. \n2. Abrir proyecto existente. \n3. Consultar mis proyectos.\n0. Cerrar Sesion.");
						int eleccion = Integer.parseInt(input("Seleccione su opcion"));
						if (eleccion ==1 ) {
							crearProyecto();
						}
						
						else if (eleccion ==3) {
							System.out.println("Se han encontrado los siguientes proyectos\n");
							System.out.println(controlador.getProyectsOfAmi(usuario)+ "\n");
							System.out.println("¿A que proyecto quieres ingresar?\n");
							ejecutarCargarDatos();
							
							
						}
						else if (eleccion ==0) {
							seguir = false;
							
							System.out.println("Cerrando sesion...");
							fileWriter actualizar = new fileWriter();
							actualizar.actualizarUsuario(usuario);
							option = 0;
							this.usuario = null;
							}
						
						else if (eleccion ==3) {
							System.out.println("Se han encontrado los siguientes proyectos\n");
							System.out.println(controlador.getProyectsOfAmi(usuario)+ "\n");
							
						}
					
				
				
			
			}
			}
			else {
				option = Integer.parseInt(input("-"));
			}
			
			if (option == 1) {
				
				boolean continuar2= true;
				while (continuar2) {
					
					
					System.out.println("¿Ya eres parte de nosotros?\nIngresa a tu cuenta:");
					System.out.println("1. Iniciar sesion.\n0.Volver.");
					
					int option2 =  Integer.parseInt(input("-"));
					if (option2 == 1) {
	

						System.out.println("\nDigite sus datos para iniciar sesion.");
						System.out.println("\nNombre: \n");
						String nombre = input("-");
						String pass = input("\nPassword:\n-");
						integrante logrado = controlador.iniciarSesion(nombre, pass);
						if (logrado != null) {
							this.usuario = logrado;
							System.out.println("Inicio de sesion completo");
							continuar2 = false;
						}
						else {
							System.out.println("No se pudo iniciar sesion...");
						}
						
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
				fileWriter escritor = new fileWriter();
				
				System.out.println("¿Eres nuevo? Registrate ya.\n");
				System.out.println("Digite sus datos:\n");
				System.out.println("Nombre: ");
				String nombre = input("");
				String correo = input("Correo:\n");
				String pass = input("Password:\n");
				
				escritor.writeUser(nombre, correo, pass);
				System.out.println("Registrado con exito\n\nAhora, inice sesion para continuar.");
				
				
				
			}
			
			else if (option ==0) {
				continuar = false;		
				
			}
			else {
				System.out.println("Seleccione una opcion valida.");
			}
	
			
		}
		}
	
	private boolean crearProyecto() {
		
		boolean logrado = false;
		System.out.println("Iniciando creacion de proyecto...\n");
		
		String nombre = input("Digite el nombre de su proyecto:\n");
		fileWriter escritor = new fileWriter();
		escritor.writeProy(nombre, usuario);
		controlador.addProyectsOfAmi(usuario, nombre, true);
		
		
		
		return logrado;
		
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