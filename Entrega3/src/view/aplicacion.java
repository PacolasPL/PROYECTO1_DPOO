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
		
		/*Primer while, aqui esta las opciones de registro e inicio de sesion.
		  */
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
						
						else if (eleccion ==2) {
							
							String info = controlador.getProyectsOfAmi(usuario);
							if (info == null) {
								System.out.println( "\nUsted no tiene proyectos..." + "\n");
							}
							else {
								System.out.println("\nSe han encontrado los siguientes proyectos\n");
								System.out.println(info);
							}
							
							ejecutarCargarDatos();
							boolean ingreso = false;
							
							if (controlador.isNullProy()) {
								System.out.println("\nPor favor, cree el proyecto");
							}
							else {
								ingreso = controlador.iniciarSesionProyecto(usuario.getName());
							}
							if (ingreso) {
								menuInicial();
							}
							else {
								System.out.println("\nNo perteneces a este proyecto. \nVolviendo a la pagina inicial...\n...");
							}
							
							
							
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
							String info = controlador.getProyectsOfAmi(usuario);
							if (info == null) {
								System.out.println( "\nUsted no tiene proyectos..." + "\n");
							}
							else {
								System.out.println("\nSe han encontrado los siguientes proyectos\n");
								System.out.println(info);
							}
							
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
	
	private boolean crearProyecto() throws IOException {
		
		boolean logrado = false;
		System.out.println("Iniciando creacion de proyecto...\n");
		
		String nombre = input("Digite el nombre de su proyecto:\n");
		fileWriter escritor = new fileWriter();
		escritor.writeProy(nombre, usuario);
		controlador.addProyectsOfAmi(usuario, nombre, true);
		
		
		
		return logrado;
		
	}
	
	private void menuInicial() throws IOException 
	{
		System.out.println("Sesion iniciada...\n\n");
		System.out.println("\n" + controlador.getProjectInfo());
		String actividades = controlador.getActividades(usuario);
		
		boolean cont =  true;
		while (cont) {
			showGeneralMenu();
			boolean isJefe = usuario.getName().equals(controlador.getLider().getName());
			if (isJefe) {
				showBossMenu();
			}
			
			int option  = Integer.parseInt( input("\nElige una opcion.\n- "));
			
			
			if (option == 1) {
				System.out.println(actividades);
			}
			else if(option == 2) {
				System.out.println(actividades);
				iniciarActividad();
			}
			else if(option == 3) {
				acabarActividad();
			}
			
			else if (option == 4) {
				String nombre = input("Escriba quien realizo la actividad:\n- ");
				String nombreAct = input("\nEscriba que actividad se realizo:\n- ");
				String fecha1 = input("\nEscribe la fecha de inicio en este formato: (yyyy-MM-dd HH:mm:ss)\n- " );
				String fecha2 = input("\nEscribe la fecha de inicio en este formato: (yyyy-MM-dd HH:mm:ss)\n- " );
				
				controlador.addLog(nombre, nombreAct, fecha1, fecha2);
				
			}
			
			else if (option == 6 && isJefe) {
				System.out.println(controlador.getIntegrantes()+ "\n");
				String name = input("\nPor favor, escriba el nombre del usuario...");
				if (controlador.isIntegrante(name)) {
					String actividad = input("\nDigite el nombre de la nueva actividad:\n- ").strip();
					System.out.println("\nLos tipos de actividades son los siguientes:\n"+ controlador.getActividades());
					String tipoActividad = input("\nDigite el nombre del tipo de actividad:\n");
					controlador.agregarActividad(name, actividad, tipoActividad);
					
					System.out.println(controlador.getActividad(actividad, tipoActividad));
					
					
				}
				
				
			
				
		
		}
			else if (option == 5 && isJefe) {
				String name = input("Por favor, escriba el nombre del usuario que desea agregar: \n-.");
				if (controlador.getUsuario(name) == null) {
					System.out.println("\nEse nombre aun no esta registrado en nuestra base de datos:\n");
				}
				else {
					controlador.agregarIntegrante(controlador.getUsuario(name));
					System.out.println("\nIntegrante agregado con exito\n");
					System.out.println("\nEsta es la nueva lista de integrantes: \n-" + controlador.getIntegrantes()+ "\n");
				}
				
				
			
			}
			
			else if (option == 5 && isJefe) {
				String name = input("Por favor, escriba el nombre del usuario que desea agregar: \n-.");
				if (controlador.getUsuario(name) == null) {
					System.out.println("\nEse nombre aun no esta registrado en nuestra base de datos:\n");
				}
				else {
					controlador.agregarIntegrante(controlador.getUsuario(name));
					System.out.println("\nIntegrante agregado con exito\n");
					System.out.println("\nEsta es la nueva lista de integrantes: \n-" + controlador.getIntegrantes()+ "\n");
				}
			}
				
			else if (option ==0) {
				fileWriter actualizador = new fileWriter();
				System.out.print("\nOpcion No Valida\n");
				try {
					actualizador.actualizarProy(controlador.getName() , controlador.getLider() , controlador.getStartTime(), controlador.getMinutes());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				controlador.guardarActividades();
				controlador.actualizarIntegrantes();
				
				System.out.println("YA TERMINAMOS....\nSALIENDO DE LA APLICACION...");
				cont = false;
			}
			}
		
	}
	
	
	private void showGeneralMenu() {
		System.out.println("Iniciemos el trabajo.\n¿Como pretendes iniciar?");
		System.out.println("1. Ver tareas pendientes.");
		System.out.println("2. Iniciar a trabajar en una actividad.");
		System.out.println("3. Dar por finalizada una actividad.");
		System.out.println("4. Registrar trabajos de una persona offline.");
		
	
	}
	
	private void showBossMenu() {
		System.out.println("----OPCIONES ADMINISTRATIVAS----");
		System.out.println("6. Agregar y asignar actividad.");
		System.out.println("5. Agregar miembros al equipo.");
		System.out.println("7 Dar proyecto por finalizado.");
		
	}
	
	private String ejecutarCargarDatos(){
		
		String archivo = input("-");
		try
		{
			controladorProyecto control = loaderProyect.cargarArchivo(archivo);
			if (control != null) {
				controlador = loaderProyect.cargarArchivo(archivo);
				System.out.println("Se cargó el archivo " + archivo + " con información del proyecto.");
			}
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
	
	private void iniciarActividad() {
		
		int option = Integer.parseInt(input("Seleccione una la actividad en la que trabajara: "));
		actividad act = controlador.getAmigoActividad(usuario, option);
		controlador.iniciarActividad(this.usuario, act);
		System.out.println("\nACTIVIDAD INICIADA...\n\nIniciando: \n" + act.getName());
		
	}
	
	private void acabarActividad() {
        String comentarios = input("Escriba los comentarios de finalizado de la actividad:\n ");
        controlador.acabarActividad(comentarios, this.usuario);
        System.out.println("\nACTIVIDAD FINALIZADA");

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