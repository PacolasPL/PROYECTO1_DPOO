package model;
import java.time.*;

public class registro {
	private int inicio;
	private String aCargoDe;
	private String actividadTrabajada;
	private String comentarios;
	private int minutosTranscurrido;

	
	public registro(integrante amigo, actividad act) {
		this.aCargoDe = amigo.getName();
		this.actividadTrabajada = act.getName();	
		this.inicio = LocalTime.now().getSecond();
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
		int tiempoFinal = LocalTime.now().getSecond();
		this.minutosTranscurrido = (tiempoFinal - inicio)/60;
		return  minutosTranscurrido;
		
	}
}
