package model;
import java.time.LocalDateTime;
import java.util.*;

public class actividad {
	private boolean enProceso;
	private String name;
	private String tipoActividad;
	private ArrayList<integrante> aCargoDe;
	private int tiempoTranscurrido;

	public actividad (String name, String tipoActividad, ArrayList<integrante> integrantes ) {
		this.name = name;
		this.tipoActividad = tipoActividad;
		this.aCargoDe = integrantes;
	}
	
	public int trabajar(integrante amigo) {
		LocalDateTime inicio =  LocalDateTime.now();
		int inicioT =  inicio.getSecond();
		return inicioT;
		}
	
	public String getName() {
		return name;
	}
	
	public void agregarIntegrante(integrante amigo) {
		aCargoDe.add(amigo);
	}
	

}
