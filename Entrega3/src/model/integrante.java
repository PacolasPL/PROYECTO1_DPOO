package model;
import java.util.*;

public class integrante {
	private String name;
	private String correo;
	private String password;
	private HashMap<String, String> proyectos;
	
	public integrante(String name, String correo, String password) {
		this.name = name;
		this.correo =  correo;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public String getCorreo() {
		return correo;
	}
	public boolean comparePass(String pass) {
		if(pass.equals( password)) {
			return true;
		}
		else {
			return false;
		}
			
	}
	public void addProyect(String nameProy , String isLider) {
		if (proyectos == null) {
			proyectos = new HashMap<String, String>();
		}
		
		System.out.println(name + " " + nameProy+ " <- Esto se imprime");
		proyectos.put(nameProy, isLider);
	}
	
	public String getProyect() {
		String Names = proyectos.keySet().toString();
		return Names;
		
	}
	public boolean isLider(String proyecto) {
		boolean Lider =  false;
		if (proyectos.get(proyecto).equals("true")) {
			Lider = true;
		}
		return Lider;
	}
}
