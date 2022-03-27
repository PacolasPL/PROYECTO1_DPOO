package controller;
import java.time.LocalDateTime;
import java.util.HashMap;

import model.*;

public class controladorProyecto {
	
	private integrante usuarioActual;
	private proyecto Proy;
	
	public controladorProyecto(proyecto Proy) {
		this.Proy= Proy;
	}
	
	public String iniciarSesion(String name, String pass) {
		integrante amigo= Proy.getIntegrante(name);
		if (amigo.comparePass(pass)) {
			return "Sesion Iniciada";
		}
		else {
			return "Contrasenia Incorrecta.";
		}
	}
	

}
