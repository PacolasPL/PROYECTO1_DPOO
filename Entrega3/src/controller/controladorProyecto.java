package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;


import model.*;

public class controladorProyecto {
	
	private proyecto Proy;
	private HashMap<String, integrante> usuarios;
	private actividad ActividadActual;
	private registro registroActual;
	
	
	public void agregarProyecto(proyecto Proy) throws FileNotFoundException, IOException {
		cargarUsuarios();
		this.Proy= Proy;
	}
	
	public String getCurrentLog() {
		return registroActual.getAmigoName();
	}
	
	public void acabarActividad(String comentario, integrante usuario) {
        int minutosTranscurridos = Math.abs(registroActual.terminarTurno(comentario));
        this.ActividadActual.actualizarTiempo(minutosTranscurridos);
        Proy.actualizarTiempo(minutosTranscurridos);
        System.out.println(registroActual.createString());
        fileWriter actualizacion = new fileWriter();
        try {
			actualizacion.actualizarUsuario(usuario);
		} catch (IOException e) {
		}
    }
	
	private void  cargarUsuarios() throws FileNotFoundException, IOException {
		this.usuarios = loaderProyect.getUserList();
		
	}
	
	public actividad getAmigoActividad(integrante amigo, int option) {
		return amigo.getActividad(option-1);
	}
	
	public void iniciarActividad(integrante amigo, actividad act) {
		this.ActividadActual = act;
		registro registroNuevo = new registro(amigo, act);
		this.registroActual = registroNuevo;
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
	
	public int getMinutes() {
		return Proy.getTiempo() ;
	}
	
	public String getName() {
		return Proy.getName() ;
	}
	
	
	public String getStartTime() {
		return Proy.getFechaInicial();
	}
	
	public integrante getLider()
	{
		String liderName =  Proy.getLiderName();
		integrante amigo =  usuarios.get(liderName);
		return amigo ;
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
