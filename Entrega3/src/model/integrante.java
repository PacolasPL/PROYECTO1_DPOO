package model;

public class integrante {
	private String name;
	private String correo;
	private String password;
	
	public void integrante(String name, String correo, String password) {
		this.name = name;
		this.correo =  correo;
		this.password = password;
	}
	public String getName() {
		return name;
	}
}
