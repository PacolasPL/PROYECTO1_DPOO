package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import model.*;

public class controladorProyecto {
	
	private proyecto Proy;
	private HashMap<String, integrante> usuarios ;
	private actividad ActividadActual;
	private registro registroActual;
	
	
	public void agregarProyecto(proyecto Proy) {
		this.Proy= Proy;
	}
	
	private void  cargarUsuarios() throws FileNotFoundException, IOException {
		this.usuarios = loaderProyect.getUserList();
		
	}
	
	public actividad getAmigoActividad(integrante amigo, int option) {
		return amigo.getActividad(option-1);
	}
	
	public void iniciarActividad(integrante amigo, actividad act) {
		this.ActividadActual = act;
		this.registroActual = new registro(amigo, act);
	}
	
	public void finalizarTurno(integrante amigo) {
		
		int timeToAdd = registroActual.terminarTurno("Termine");
		ActividadActual.actualizarTiempo(timeToAdd);
		Proy.addLog(registroActual);
	}
	
	public void addProyectsOfAmi(integrante amigo, String name, boolean isLider ) {
		
		if (isLider) {
			amigo.addProyect(name, "true");
		}
		else {
			amigo.addProyect(name, "false");
		}
		
	}
	public String getProyectsOfAmi(integrante amigo ) {
		
		String temp = amigo.getProyect();
		if (temp == null) {
			return temp;
		}
		else {
			temp =  temp.replace("[", "").replace("]", "");
			return temp;
		}
		
	}
	
	public String getProjectInfo() {
		
		String data = "Bienvenido a " + Proy.getName()+".\n\n";
		data += "Fecha de inicio: " + Proy.getFechaInicial()+ "\n";
		data += "Tiempo transcurrido desde el inicio: " + Proy.getTiempo() + "\n";
		data += "Creador del proyecto: " + Proy.getLiderName() + "\n";
		return data;
		
	}
	
	public String getActividades(integrante Amigo)
	
	{
		return Amigo.mostrarPendientes();
	}
	
	public void agregarActividad(String aCargoDe, String name, String tipoActividad) 
	
	{
		actividad act = new actividad(name, tipoActividad, usuarios.get(aCargoDe));
		Proy.agregarActividad(act);
		usuarios.get(aCargoDe).setActivities(act);;
	}
	
	
	public integrante iniciarSesion(String name, String pass) throws FileNotFoundException, IOException {
		cargarUsuarios();
		integrante amigo= usuarios.get(name);
		if ( amigo!=null ) {
			if (amigo.comparePass(pass)) {
				return amigo;
		}
		}
		return amigo;
		
		
		
	}
	
	public boolean iniciarSesionProyecto(String name) {

		integrante amigo= Proy.getIntegrante(name);
		if (amigo != null ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isNullProy() {
		if (Proy == null) {
			return true;
		}
		else {
			return false;
		}
	}
	

}
