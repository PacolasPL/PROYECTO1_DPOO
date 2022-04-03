package model;

import java.util.*;
import java.time.*;

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
	
	
	public proyecto(integrante Lider, String name) {
		
		this.Terminado = false;
		this.Name = name; 
		this.Lider = Lider;
		LocalDateTime fechaInicio = LocalDateTime.now();
		this.fechaInicio =  fechaInicio;
		this.registros = new registroActividad(Lider);
		integrantes.put(Lider.getName(), Lider);
		
	}
	
	public void addLog(registro Log) {
		registros.addLog(Log);
	}
	
	public registroActividad getRegistros() {
		return this.registros;
	}
	
	public void putStartdate(String fecha) {
		
	}
	
	public boolean estaTerminado() {
		return Terminado;
	}
	
	public void putIntegrantes(HashMap <String, integrante> integrantes) {
		this.integrantes = integrantes ;
	}
	
	public void agregarIntegrante(integrante amigo) {
		integrantes.put(amigo.getName(), amigo);
		calcularTiempo();
	}
	
	
	public void agregarActividad(actividad act) {
		String tipo = act.getTipoActividad();
		if (actividades.get(tipo) == null) {
			actividades.put(tipo, new ArrayList<actividad>());
		}
		actividades.get(tipo).add(act);
		calcularTiempo();
	}
	

	
	public int getTiempo() {
		return tiempoTranscurrido;
	}
	
	public String getFechaInicial() {
		return fechaInicio.toString();
		
	}
	
	public String getLiderName() {
		String name = Lider.getName();
		return name;
	}
	
	public integrante getIntegrante(String name) {
		integrante amigo = integrantes.get(name);
		return amigo;
		
	}
	
	public String getIntegrantData(String name) {
		integrante amigo = integrantes.get(name);
		String data = "Nombre: " + amigo.getName() + "Correo: " + amigo.getCorreo(); 
		return data;
		
	}
	
	public String getName() {
		return Name;
	}
	
	private void calcularTiempo(){
		LocalDateTime fechaActual= LocalDateTime.now();
		int inicio = fechaInicio.getSecond();
		int finalTime = fechaActual.getSecond();
		int hours = (finalTime - inicio)/3600; 
		this.tiempoTranscurrido = hours;
	}
	
	public void finalizarActividad(actividad act) {

		String tipo =  act.getTipoActividad();
		System.out.println(tipo);
		if (actividadesFinalizadas.get(tipo) == null)	
			actividadesFinalizadas.put(tipo,  new ArrayList<actividad>());
		actividadesFinalizadas.get(tipo).add(act);
		calcularTiempo();

	}
	
	public void finalizarProyecto(String comentario) {
		this.Terminado = true;
		actividad acabar = new actividad("Proyecto finalizado","Administrativo", Lider);
		registro finalAct = new registro(Lider, acabar);
		registros.addLog(finalAct);
		calcularTiempo();
	}
}
