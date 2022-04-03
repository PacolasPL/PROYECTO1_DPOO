package model;
import java.time.LocalDateTime;
import java.util.*;

public class actividad {
	private String name;
	private String tipoActividad;
	private String principal;
	private ArrayList<integrante> aCargoDe= new ArrayList<integrante>();
	private int tiempoTranscurrido;

	public actividad (String name, String tipoActividad, integrante amigo ) {
		this.name = name;
		this.tipoActividad = tipoActividad;
		this.principal = amigo.getName();
		aCargoDe.add(amigo);
	}
	
	public void  actualizarTiempo(int agregar) {
		tiempoTranscurrido += agregar;
	}
	
	public String getPrincipal(){
		return principal;
	}
	
	

	
	public int trabajar(integrante amigo) {
		LocalDateTime inicio =  LocalDateTime.now();
		int inicioT =  inicio.getSecond();
		return inicioT;
		}
	
	public String getTipoActividad() {
		return tipoActividad;
	}
	
	public String getName() {
		return name;
	}
	
	public void agregarIntegrante(integrante amigo) {
		aCargoDe.add(amigo);
	}
	
	public int getTiempoTranscurrido() {
		return tiempoTranscurrido;
	}
	
	public String consultarActividad() {
		String datos = "";
		datos += "Actividad: "+ name  + "\n";
		for (int i =0 ; i< aCargoDe.size() ; i++) {
			aCargoDe.get(i);
		}
		
		return datos; 
	}

}
