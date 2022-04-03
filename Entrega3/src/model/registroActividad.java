package model;

import java.util.*;

public class registroActividad {
	
	private ArrayList<registro> registros = new ArrayList<registro>();
	
	public registroActividad(integrante Lider) {
		actividad creacion = new actividad("Creacion proyecto", "Administrativa" ,Lider);
		registro inicial = new registro(Lider, creacion);
		registros.add(inicial);
		
	}
	
	public void addLog(registro log ) {
		registros.add(log);
	}
	
	// Función para traer una lista basada en arreglo con los registros
	// de un usuario dado
	
	public void getLogsFromUser(integrante usuario) {
		ListIterator<registro> regIt = registros.listIterator();
		ArrayList<registro> delUsuario = new ArrayList<registro>();
		int logs = 0;
		while(regIt.hasNext()) {
			if(registros.get(regIt.nextIndex()-1).getAmigoName() == usuario.getName());
				logs += 1;
				delUsuario.add(registros.get(regIt.nextIndex()-1));
		}
		System.out.println("Se consiguieron " + logs + " registros.");
	}

}
