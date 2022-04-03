package model;
import java.util.*;

public class integrante {
	private String name;
	private String correo;
	private String password;
	private HashMap<String, actividad> actividades = new HashMap<String, actividad>();
	private HashMap<String, actividad>actividadesHechas = new HashMap<String, actividad>();
	private HashMap<String, String> proyectos;
	
	public integrante(String name, String correo, String password) {
		this.name = name;
		this.correo =  correo;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	
	public void setActivities(actividad activity) {
		
		actividades.put(activity.getName(), activity);
	}
	
	
	public void setActivitiesFinal(actividad activity) {
		
		actividadesHechas.put(activity.getName(), activity);
		
	}
	
	public actividad getActividad(int opt) {
		String[] toUse = actividades.keySet().toString().replace("[", "").replace("]", "").split(",");
		actividad act = actividades.get(toUse[opt].strip());
		return act ;
	}
	
	public String mostrarPendientes()
	{
		String[] toUse = actividades.keySet().toString().replace("[", "").replace("]", "").split(",");
		String show = "";
		for (int i = 0 ; i< actividades.size(); i++) {
			show += Integer.toString(i+1)+ ". "+ actividades.get(toUse[i].strip()).getName() +", la cuales es de tipo " + actividades.get(toUse[i].strip()).getTipoActividad();
			System.out.println("\n" + toUse[i].strip());
			show += "\n";
		}
		return show;
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
		if (proyectos == null) {
			return null;
		}
		else {
			String Names = proyectos.keySet().toString();
			return Names;
		}
		
	}
	public boolean isLider(String proyecto) {
		boolean Lider =  false;
		if (proyectos.get(proyecto).equals("true")) {
			Lider = true;
		}
		return Lider;
	}
}
