package model;
import java.util.*;

public class integrante {
	private String name;
	private String correo;
	private String password;
	private ArrayList<actividad> actividades = new ArrayList<actividad>();
	private ArrayList<actividad> actividadesHechas = new ArrayList<actividad>();
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
		actividades.add(activity);
	}
	
	
	public void setActivitiesFinal(actividad activity) {
		actividadesHechas.add(activity);
	}
	
	public String mostrarPendientes()
	{
		String show = "";
		for (int i = 0 ; i< actividades.size(); i++) {
			show += Integer.toString(i+1)+ ". "+ actividades.get(i).getName() +", la cuales es de tipo " + actividades.get(i).getTipoActividad();
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
