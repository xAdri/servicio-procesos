package es.florida.ejercicios;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

// EXAMEN
public class Hilo implements Runnable {

Socket cliente;
	
	public Hilo(Socket cliente){
		this.cliente = cliente;
	}
	
	public String comprobarUsuario(Usuario user) throws NoSuchAlgorithmException {
		// Contrasenya texto plano y encriptada para trabajar con ella
		String usuario = user.getNombre();
		String pass = user.getContrasenya();
		String respuesta = "";
		
		if ("admin".equals(usuario) && "admin123".equals(pass)) {
			respuesta = "200 OK";
			System.out.println("OK");
			return respuesta;
		} else {
			respuesta = "ERROR";
			System.out.println("ERROR");
			return respuesta;
		}	
	}
	
	public void run() {
		try {
			InputStream is = cliente.getInputStream();
			
			// 
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >>> Recibe el objeto usuario");
			ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
			Usuario usuarioMod = (Usuario) inObjeto.readObject();
			System.err.println("Comprobacion usuario: " + usuarioMod);
			
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >>> Comprobacion si el usuario esta en la lista con la pass correcta");
			String comprobacion = comprobarUsuario(usuarioMod);
			
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >>> Envio de Response");
			ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
			outObjeto.writeObject(comprobacion);
			PrintWriter pw = new PrintWriter(outObjeto);
			pw.write(comprobacion + "\n");
			pw.flush();

			
			
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >>> Recibe el numero indicando las lineas que va a enviar el cliente");
			
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >>> Crear fichero de escritura contenido.txt");
			// Enviar al cliente PREPARADO
			
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >>> Fin del hilo");
			
		} catch (IOException e) {
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >>> Error.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
