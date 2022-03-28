package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import model.*;

public class controladorProyecto {
	
	private proyecto Proy;
	private HashMap<String, integrante> usuarios ;
	
	public void agregarProyecto(proyecto Proy) {
		this.Proy= Proy;
	}
	
	private void  cargarUsuarios() throws FileNotFoundException, IOException {
		this.usuarios = loaderProyect.getUserList();
		
	}
	
	public String getProyectsOfAmi(integrante amigo ) {
		String proyectos = amigo.getProyect().replace("[", "").replace("]", "");
		return proyectos;
		
	}
	
	public void addProyectsOfAmi(integrante amigo, String name, boolean isLider ) {
		if (isLider) {
			amigo.addProyect(name, "true");
		}
		else {
			amigo.addProyect(name, "false");
		}
		
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
	
	public integrante iniciarSesionProyecto(String name, String pass) {
		integrante amigo= Proy.getIntegrante(name);
		if (amigo.comparePass(pass)) {
			return amigo;
		}
		else {
			return null;
		}
	}
	

}
