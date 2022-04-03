package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import static java.time.temporal.ChronoUnit.SECONDS;

import model.*;

public class controladorProyecto {
	
	// La clase "controladorProyecto" posee los siguientes atributos:
	// proyecto Proy <<una instancia de la clase proyecto que representa el proyecto que se est� trabajando>>
	// HashMap<String, integrante> usuarios <<un HashMap con los usuarios del proyecto>>
	// actividad ActividadActual <<una instancia de la clase actividad que representa la actividad que se est� trabajando>>
	// registro registroActual <<una instancia de la clase registro que representa el registro que se est� trabajando>>
	
	private proyecto Proy;
	private HashMap<String, integrante> usuarios;
	private actividad ActividadActual;
	private registro registroActual;
	
	// Se ingresa un proyecto, se cargan los usuarios en base a este proyecto y
	// se establece como la variable "Proy" del controlador.
	
	public void agregarProyecto(proyecto Proy) throws FileNotFoundException, IOException {
		cargarUsuarios();
		this.Proy= Proy;
	}
	
	// Retorna un String con el nombre del integrante al que se atribuye el registro.
	
	public String getCurrentLog() {
		return registroActual.getAmigoName();
	}
	
	// Se ingresa un String con el comentario de un registro y un integrante "usuario"
	// y se lleva a cabo el protocolo de acabar una actividad:
	//
	// Se crea un registro con el comentario dado, y con los minutos transcurridos
	// se actualiza el tiempo transcurrido de la actividad actual, se imprime la informaci�n
	// sobre el registro que se cre� y se actualiza el archivo de usuarios con la nueva
	// informaci�n recibida.
	
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
	
	// Se crea un HashMap con los usuarios del proyecto y se establece en la variable
	// "usuarios" del proyecto.
	
	private void  cargarUsuarios() throws FileNotFoundException, IOException {
		this.usuarios = loaderProyect.getUserList();
	}
	
	public void actualizarIntegrantes() throws IOException {
		Proy.actualizarIntegrantes();
	}
	// Retorna una actividad de un integrante ingresado, junto con su index
	// respectivo.
	
	public actividad getAmigoActividad(integrante amigo, int option) {
		return amigo.getActividad(option-1);
	}
	
	
	public boolean isIntegrante(String name) {
		if (Proy.getIntegrante(name) == null) {
			return false;
		}
		else {
			return true;
		}
		
	}
	
	public integrante getUsuario(String name) {
		return usuarios.get(name);
	}
	
	public String getActividad(String act, String tipo) {
		String mensaje = Proy.getActividad(act, tipo);
		return mensaje;
	}
	// Realiza el protocolo de iniciar una actividad en el proyecto:
	// 
	// Se establece la actividad ingresada como la actividadActual del proyecto, adem�s
	// de crear un nuevo registro de la actividad y convertirlo en el registroActual.
	
	public void iniciarActividad(integrante amigo, actividad act) {
		this.ActividadActual = act;
		registro registroNuevo = new registro(amigo, act);
		this.registroActual = registroNuevo;
	}
	
	// Realiza el protocolo de finalizar una actividad en el proyecto:
	//
	// El registroActual se registra como terminado, se actualiza el tiempo de la actividadActual
	// y se a�ade el registroActual al proyecto.
	
	public void finalizarTurno(integrante amigo) {
		
		int timeToAdd = registroActual.terminarTurno("Termine");
		String act = ActividadActual.getName();
		String tipo = ActividadActual.getTipoActividad();
		
		actividad activity = Proy.buscarActividad(act, tipo);
		
		activity.actualizarTiempo(timeToAdd);
		
		Proy.addLog(registroActual);
	}
	
	// A�ade a un usuario para que tenga el permiso de ingresar al proyecto desde
	// su cuenta.
	
	public void addProyectsOfAmi(integrante amigo, String name, boolean isLider ) {
		
		if (isLider) {
			amigo.addProyect(name, "true");
		}
		else {
			amigo.addProyect(name, "false");
		}
	}
	
	// Retorna el tiempo transcurrido del proyecto.
	
	public int getMinutes() {
		return Proy.getTiempo() ;
	}
	
	// Retorna el nombre del proyecto.
	
	public String getName() {
		return Proy.getName() ;
	}
	
	// Retorna la fecha en que se inici� el proyecto.
	
	public String getStartTime() {
		return Proy.getFechaInicial();
	}
	
	// Retorna el integrante lider del proyecto.
	
	public integrante getLider()
	{
		String liderName =  Proy.getLiderName();
		integrante amigo =  usuarios.get(liderName);
		return amigo ;
	}
	
	// Retorna un String con los proyectos de un integrante ingresado.
	
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
	
	// Retorna un String con un mensaje para imprimir en base a la informaci�n
	// del proyecto actual.
	
	public String getProjectInfo() {
		
		String data = "Bienvenido a " + Proy.getName()+".\n\n";
		data += "Fecha de inicio: " + Proy.getFechaInicial()+ "\n";
		data += "Tiempo transcurrido desde el inicio: " + Proy.getTiempo() + "\n";
		data += "Creador del proyecto: " + Proy.getLiderName() + "\n";
		return data;
	}
	
	// Retorna un String con la lista de actividades pendientes de un integrante ingresado.
	
	public String getActividades(integrante Amigo)
	
	{
		return Amigo.mostrarPendientes();
	}
	
	// Agrega una actividad nueva a la lista de actividades por desarrollar de un estudiante
	// cuyo nombre es ingresado y tambi�n el nombre de la actividad.
	
	public void agregarActividad(String aCargoDe, String name, String tipoActividad) 
	
	{
		actividad act = new actividad(name, tipoActividad, usuarios.get(aCargoDe));
		Proy.agregarActividad(act); 
		usuarios.get(aCargoDe).setActivities(act);;
	}
	
	public String getIntegrantes() {
		String list = Proy.getIntegrantes();
		return list ;
	}
	// Realiza el protocolo de intentar iniciar sesi�n en el proyecto:
	//
	// Se consigue el integrante seg�n el nombre ingresado y se compara su contrase�a
	// con la ingresada. Retorna el usuario seg�n el nombre ingresado.
	
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
	
	public void agregarIntegrante(integrante amigo) {
		Proy.agregarIntegrante(amigo);
	}
	
	public String getActividades() {
		return Proy.getActividades();
	}
	// Retorna un boolean en base a si un integrante pertenece al proyecto actual.
	
	public boolean iniciarSesionProyecto(String name) {

		integrante amigo= Proy.getIntegrante(name);
		if (amigo != null ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Retorna un boolean en base a si el atributo Proy es igual a null o no.
	
	public void addLog(String name,String act,  String fecha1, String fecha2) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") ;
		LocalDateTime tiempo1 = LocalDateTime.parse(fecha1, format);
		LocalDateTime tiempo2 = LocalDateTime.parse(fecha2, format);
		
		long time = SECONDS.between(tiempo1, tiempo2);
		int timeFinal = (int)time;
		integrante amigo = usuarios.get(name);
		actividad acti = new actividad(act, "Fuera de tiempo", amigo);
		registro log = new registro(amigo, acti);
		log.putAll( tiempo1, tiempo2, timeFinal);
		Proy.addLog(log);
		
		
	}
	
	public boolean isNullProy() {
		if (Proy == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public void guardarActividades() throws IOException {
		Proy.guardarActividades();
	}

}
