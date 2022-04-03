package model;

import java.util.*;
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
	
	// Añade un registro a los registros (clase registroActividad)
	// dentro del proyecto.
	
	public void addLog(registro Log) {
		registros.addLog(Log);
	}
	
	// Cambia la fechaInicio a una ingresada
	// a través de un String.
	
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
	
	// Devuelve el valor true/false de la variable
	// Terminado (en boolean).
	
	public boolean estaTerminado() {
		return Terminado;
	}
	
	// Se añade una cantidad entera de segundos al tiempo
	// transcurrido (tiempoTranscurrido).
	
	public void actualizarTiempo(int adicion) {
		this.tiempoTranscurrido += adicion;
	}
	
	// Los integrantes de un proyecto se pasan como un HashMap<String, integrante>
	// y se convierten en la variable "integrantes" del proyecto.
	
	public void putIntegrantes(HashMap <String, integrante> integrantes) {
		this.integrantes = integrantes;
	}
	
	// Añade un integrante (amigo) a la lista de los integrantes del
	// proyecto.
	
	public void agregarIntegrante(integrante amigo) {
		integrantes.put(amigo.getName(), amigo);
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
	
	// Retorna el tiempo transcurrido durante la creación del proyecto.
	
	public int getTiempo() {
		return tiempoTranscurrido;
	}
	
	// Retorna la fecha en que se inició el proyecto en un String.
	
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
	
	// Calcula en base a dos fechas (inicial y final) la cantidad de tiempo que
	// ha transcurrido entre estas dos.
	
	private void calcularTiempoDesdeInicio(){
		LocalDateTime fechaActual= LocalDateTime.now();
		int inicio = fechaInicio.getSecond();
		int finalTime = fechaActual.getSecond();
		int hours = (finalTime - inicio)/3600; 
		this.tiempoTranscurrido = hours;
	}
	
	// Realiza el procedimiento para finalizar una actividad, el cual es añadir
	// la actividad que se quiere finalizar en la lista de actividades finalizadas.
	
	public void finalizarActividad(actividad act) {

		String tipo =  act.getTipoActividad();
		System.out.println(tipo);
		if (actividadesFinalizadas.get(tipo) == null)	
			actividadesFinalizadas.put(tipo,  new ArrayList<actividad>());
		actividadesFinalizadas.get(tipo).add(act);


	}
	
	// Realiza el procedimiento para finalizar un proyecto, el cual es cambiar su valor de Terminado
	// a true, se crea una actividad llamada "Proyecto finalizado", se crea un registro en base a esta
	// actividad y se añade el registro a los registros del proyecto.
	
	public void finalizarProyecto(String comentario) {
		this.Terminado = true;
		actividad acabar = new actividad("Proyecto finalizado","Administrativo", Lider);
		registro finalAct = new registro(Lider, acabar);
		registros.addLog(finalAct);

	}
}
