package model;
import java.time.LocalDateTime;
import java.util.*;

	// La clase actividad posee los siguientes atributos:
	// String name; <<String con el nombre de la actividad>>
	// String tipoActividad; <<String con el tipo de la actividad>>
	// String principal; <<Nombre del integrante principal a cargo de esta actividad>>
	// ArrayList<integrante> aCargoDe; <<ArrayList con los integrantes a cargo de la actividad>>
	// int tiempoTranscurrido; <<Entero con el tiempoTranscurrido de la actividad>>

public class actividad {
	private String name;
	private String tipoActividad;
	private String principal;
	private ArrayList<integrante> aCargoDe = new ArrayList<integrante>();
	private int tiempoTranscurrido;

	public actividad (String name, String tipoActividad, integrante amigo ) {
		this.name = name;
		this.tipoActividad = tipoActividad;
		this.principal = amigo.getName();
		aCargoDe.add(amigo);
	}
	
	// Se agrega un tiempo en entero a la variable de tiempo transcurrido en la actividad.
	
	public void  actualizarTiempo(int agregar) {
		tiempoTranscurrido += agregar;
	}
	
	// Se retorna un String con el nombre del integrante principal a cargo de la actividad.
	
	public String getPrincipal(){
		return principal;
	}
	
	public void setTiempo(int tiempo) {
		this.tiempoTranscurrido = tiempo;
	}
	
	// Se retorna un entero con 
	
	public int trabajar(integrante amigo) {
		LocalDateTime inicio =  LocalDateTime.now();
		int inicioT =  inicio.getSecond();
		return inicioT;
		}
	
	// Se retorna el tipo de actividad en un String.
	
	public String getTipoActividad() {
		return tipoActividad;
	}
	
	// Se retorna el nombre de la actividad.
	
	public String getName() {
		return name;
	}
	
	// Se agrega un integrante a la lista de integrantes a cargo de la actividad.
	
	public void agregarIntegrante(integrante amigo) {
		aCargoDe.add(amigo);
	}
	
	// Se retorna la variable de tiempo transcurrido en la actividad.
	
	public int getTiempoTranscurrido() {
		return tiempoTranscurrido;
	}
	
	// Se retorna un String con la informaci�n de esta actividad.
	
	public String consultarActividad() {
		String datos = "";
		datos += "Actividad: "+ name  + "\n";
		for (int i =0 ; i< aCargoDe.size() ; i++) {
			aCargoDe.get(i);
		}
		
		return datos; 
	}

}
