package model;
import java.time.*;

public class registro {
	private LocalDateTime inicio;
	private String aCargoDe;
	private String actividadTrabajada;
	private String comentarios;
	private int minutosTranscurrido;
	private LocalDateTime tiempoFinalizado;

	
	public registro(integrante amigo, actividad act) {
		this.aCargoDe = amigo.getName();
		this.actividadTrabajada = act.getName();	
		this.inicio = LocalDateTime.now();
		}
	
	public String getAmigoName() {
		return aCargoDe;
	}
	
	public String getActivityName() {
		return actividadTrabajada;
	}
	
	public String createString() {
		String data = "";
		data += "Amigo: " + aCargoDe;
		data += "\nActividad: " + actividadTrabajada +"\n";
		data += "\nComentarios:\n";
		data += comentarios;
		
		return data;
		
	}
	
	public int terminarTurno(String comentario) {
		this.comentarios = comentario;
		tiempoFinalizado = LocalDateTime.now();
		this.minutosTranscurrido = (tiempoFinalizado.getSecond() - inicio.getSecond())/60;
		
		return  minutosTranscurrido;
		
	}
}
