package model;

import java.util.*;

import controller.fileWriter;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;

	// La clase "proyecto" posee los siguientes atributos:
	// integrante Lider <<una instancia de la clase integrante que representa al lider del proyecto>>
	// String Name <<un String con el nombre del proyecto>>
	// HashMap<String, integrante> integrantes <<un HashMap con los integrantes del proyecto>>
	// LocalDateTime fechaInicio <<una instancia de LocalDateTime con la fecha del inicio del proyecto>>
	// int tiempoTranscurrido <<la cantidad de tiempo transcurrido en segundos (tipo entero)>>
	// boolean Terminado <<boolean que define si el proyecto ha sido terminado>>
	// HashMap<String, ArrayList<actividad>> actividades <<un HashMap con las actividades que faltan por acabar en el proyecto>>
	// HashMap<String, ArrayList<actividad>> actividadesFinalizadas <<un HashMap con las actividades acabadas del proyecto>>
	// registroActividad registros <<una instancia de la clase registroActividad con los registros del proyecto>>

public class proyecto {
	private integrante Lider;
	private String Name;
	private HashMap<String, integrante> integrantes = new HashMap<String, integrante>();
	private LocalDateTime fechaInicio;
	private int tiempoTranscurrido;
	private boolean Terminado;
	private HashMap<String, ArrayList<actividad>> actividades = new HashMap<String, ArrayList<actividad>>();
	private HashMap<String, ArrayList<actividad>> actividadesFinalizadas = new HashMap<String, ArrayList<actividad>>();
	private registroActividad registros;
	
	// Crea un nuevo proyecto en base a un integrante (lider) 
	// y el nombre del proyecto a crear.
	
	public proyecto(integrante Lider, String name) {
		
		this.Terminado = false;
		this.Name = name; 
		this.Lider = Lider;
		LocalDateTime fechaInicio = LocalDateTime.now();
		this.fechaInicio =  fechaInicio;
		this.registros = new registroActividad(Lider);
		integrantes.put(Lider.getName(), Lider);
		
	}
	
	// A�ade un registro a los registros (clase registroActividad)
	// dentro del proyecto.
	
	public void addLog(registro Log) {
		registros.addLog(Log);
	}
	
	// Cambia la fechaInicio a una ingresada
	// a trav�s de un String.
	
	public void putStartdate(String fecha) {
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") ;
		LocalDateTime inicio = LocalDateTime.parse(fecha, format);
		System.out.println(inicio.toString());
		this.fechaInicio = inicio;
	}
	
	// Cambia el tiempoTranscurrido a la cantidad (entera)
	// ingresada.
	
	public void putCurrentTime(String seconds) {
		this.tiempoTranscurrido = Integer.parseInt(seconds);
	}
	
	public void actualizarIntegrantes() throws IOException {
		String[] names = integrantes.keySet().toString().replace("[","").replace("]" ,"").split(",");
		
		fileWriter neeF =  new fileWriter( );
		neeF.actualizarIntegrantes(Name, names);
		
	}
	// Devuelve el valor true/false de la variable
	// Terminado (en boolean).
	
	public boolean estaTerminado() {
		return Terminado;
	}
	
	// Se a�ade una cantidad entera de segundos al tiempo
	// transcurrido (tiempoTranscurrido).
	
	public void actualizarTiempo(int adicion) {
		this.tiempoTranscurrido += adicion;
	}
	
	// Los integrantes de un proyecto se pasan como un HashMap<String, integrante>
	// y se convierten en la variable "integrantes" del proyecto.
	
	public void putIntegrantes(HashMap <String, integrante> integrantes) {
		this.integrantes = integrantes;
	}
	
	// A�ade un integrante (amigo) a la lista de los integrantes del
	// proyecto.
	
	public void agregarIntegrante(integrante amigo) {
		integrantes.put(amigo.getName(), amigo);
	}
	
	public String getActividades() {
		return actividades.keySet().toString()  ;
		}
	// Se ingresa una actividad (act) a la lista de
	// actividades por realizar del proyecto.
	
	public void agregarActividad(actividad act) {
		String tipo = act.getTipoActividad();
		if (actividades.get(tipo) == null) {
			actividades.put(tipo, new ArrayList<actividad>());
		}
		actividades.get(tipo).add(act);
	}
	
	public String getActividad(String act, String tipo) {
		if (actividades.get(tipo) == null) {
			return "\nNO SE LOGRO AGREGAR.\n";
		}
		else {
			
			for (int i = 0; i < actividades.get(tipo).size(); i++){
				
				if (actividades.get(tipo).get(i).getName() ==  act) {
					return "\nSI SE LOGROOOO\n";
				}
			
			}
			return "NO SE LOGRO";
			
		}
	}
	
	public actividad buscarActividad(String act, String tipo) {
		int punt = 0;
		if (actividades.get(tipo) != null) {
			
			for (int i = 0; i < actividades.get(tipo).size(); i++){
				punt= i;
				if (actividades.get(tipo).get(i).getName() ==  act) {
					return actividades.get(tipo).get(i);
				}
			
			}
			
		}
		return actividades.get(tipo).get(punt);
	}
	public void guardarActividades() throws IOException {
		fileWriter escritor = new fileWriter();
		String [] temp =actividadesFinalizadas.keySet().toString().replace("[","").replace("]", "").split(",") ;

		boolean pass = (temp.length > 0);
		String [] temp2 =actividades.keySet().toString().replace("[","").replace("]", "").split(",") ;
		boolean pas = (temp2.length > 0);
		escritor.updateActivities(Name, actividades, actividadesFinalizadas, pas, pass);
		
		
		
	}
	
	public String getIntegrantes() {
		String temp = "";
		String[] ints = integrantes.keySet().toString().replace("[", "").replace("]", "").split(",");
		for (int i = 0; i< ints.length; i++ ) {
			temp += "\n- "+ ints[i].strip();
		}
		return temp;
	}
	// Retorna el tiempo transcurrido durante la creaci�n del proyecto.
	
	public int getTiempo() {
		return tiempoTranscurrido;
	}
	
	// Retorna la fecha en que se inici� el proyecto en un String.
	
	public String getFechaInicial() {
		return fechaInicio.toString();	
	}
	
	// Retorna el nombre del integrante lider del proyecto.
	
	public String getLiderName() {
		String name = Lider.getName();
		return name;
	}
	
	// Ingresa el nombre de un integrante del proyecto y retorna su respectivo
	// integrante.
	
	public integrante getIntegrante(String name) {
		integrante amigo = integrantes.get(name);
		return amigo;
	}
	
	// Retorna el nombre y el correo de un integrante del proyecto en base al
	// nombre de este mismo.
	
	public String getIntegrantData(String name) {
		integrante amigo = integrantes.get(name);
		String data = "Nombre: " + amigo.getName() + "Correo: " + amigo.getCorreo(); 
		return data;
	}
	
	// Retorna el nombre del proyecto.
	
	public String getName() {
		return Name;
	}
/*
	
	// Calcula en base a dos fechas (inicial y final) la cantidad de tiempo que
	// ha transcurrido entre estas dos.
	
	private void calcularTiempoDesdeInicio(){
		LocalDateTime fechaActual= LocalDateTime.now();
		int inicio = fechaInicio.getSecond();
		int finalTime = fechaActual.getSecond();
		int hours = (finalTime - inicio)/3600; 
		this.tiempoTranscurrido = hours;
	}*/
	
	// Realiza el procedimiento para finalizar una actividad, el cual es a�adir
	// la actividad que se quiere finalizar en la lista de actividades finalizadas.
	
	public void finalizarActividad(actividad act) {

		String tipo =  act.getTipoActividad();

		if (actividadesFinalizadas.get(tipo) == null)	
			actividadesFinalizadas.put(tipo,  new ArrayList<actividad>());
		actividadesFinalizadas.get(tipo).add(act);


	}
	
	// Realiza el procedimiento para finalizar un proyecto, el cual es cambiar su valor de Terminado
	// a true, se crea una actividad llamada "Proyecto finalizado", se crea un registro en base a esta
	// actividad y se a�ade el registro a los registros del proyecto.
	
	public void finalizarProyecto(String comentario) {
		this.Terminado = true;
		actividad acabar = new actividad("Proyecto finalizado","Administrativo", Lider);
		registro finalAct = new registro(Lider, acabar);
		registros.addLog(finalAct);

	}
}
