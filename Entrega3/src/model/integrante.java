package model;

public class integrante {
	private String name;
	private String correo;
	private String password;
	
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
}
