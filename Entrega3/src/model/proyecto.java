package model;

import java.util.*;
import java.time.*;

public class proyecto {
	private integrante Lider;
	private HashMap<String, integrante> integrantes;
	private LocalDateTime fechaInicio;
	private int tiempoTranscurrido;
	private boolean Terminado;
	private HashMap<String, actividad> actividades;
	private HashMap<String, actividad> actividadesFinalizadas;
	
	
	public proyecto(integrante Lider) {
		this.Terminado = false;
		this.Lider = Lider;
		LocalDateTime fechaInicio = LocalDateTime.now();
		this.fechaInicio =  fechaInicio;
		integrantes.put(Lider.getName(), Lider);
		
	}
	
	public void agregarIntegrante(integrante amigo) {
		integrantes.put(amigo.getName(), amigo);
	}
	
	
	public void agregarActividad(actividad act) {
		actividades.put(act.getName(), act);
	}
	
	public boolean finalizarActividad(actividad act) {
		
		boolean logrado= false;
		String nameAct =  act.getName();
		if (actividades.get(nameAct) != null)
			actividadesFinalizadas.put(nameAct,  act);
			actividades.remove(nameAct);
			logrado = true;
		return logrado;
	}
	
	private void calcularTiempo(){
		LocalDateTime fechaActual= LocalDateTime.now();
		int inicio = fechaInicio.getSecond();
		int finalTime = fechaActual.getSecond();
		int hours = (finalTime - inicio)/3600; 
		this.tiempoTranscurrido = hours;
	}
}
