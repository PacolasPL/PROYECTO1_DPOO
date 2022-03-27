package model;

import java.util.*;

public class registroActividad {
	
	private ArrayList<registro> registros;
	
	public registroActividad(integrante Lider) {
		actividad creacion = new actividad("Creacion proyecto", "Administrativa" ,Lider);
		registro inicial = new registro(Lider, creacion);
		registros.add(inicial);
		
	}
	public void addLog(registro log ) {
		registros.add(log);
	}
	
	

}
