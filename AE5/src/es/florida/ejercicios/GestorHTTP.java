package es.florida.ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GestorHTTP implements HttpHandler{
	public int temperaturaActual = 15;
	public int temperaturaTermostato = 15;
	public int tiempo = 5;
	
	
	@Override
	public void handle(HttpExchange httpExchange) throws IOException{
		String requestParamValue = null;
		if("GET".equals(httpExchange.getRequestMethod())) {
			// Sin request param porque en el ejemplo "http://localhost:7777/estufa?temperaturaActual"
			handleGETResponse(httpExchange);
		} else if ("POST".equals(httpExchange.getRequestMethod())) {
			requestParamValue = handlePostRequest(httpExchange);
			handlePOSTResponse(httpExchange, requestParamValue);
		}
	}

	// Bloque POST
	private void handlePOSTResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
		// Respuesta a la petición post
		OutputStream outputStream = httpExchange.getResponseBody();
		
		String htmlResponse = "";
		
		// setTemperatura [/estufa?temperaturaActual]
		htmlResponse = "Parametro/s POST: " + requestParamValue + " => Se procesara por parte del servidor.";
		if(requestParamValue.contains("setTemperatura")) {
			// Html sencillo para mostrar lo que se procesa del servidor junto a la response de que esta ok
			System.out.println(htmlResponse);
			int temperatura = Integer.parseInt(requestParamValue.split("=")[1]);
			
			// Setear la temperatura y regularla
			setTemperaturaTermostato(temperatura);
			htmlResponse = "Parametro/s POST: " + requestParamValue + " => Temperatura seteada";
			httpExchange.sendResponseHeaders(200, htmlResponse.length());
		}
		
		// regularTemperatura [/estufa => En body raw text "regularTemperatura"]
		if(requestParamValue.contains("regularTemperatura")) {
			System.out.println(htmlResponse);
			regularTemperatura();
			htmlResponse = "Parametro/s POST: " + requestParamValue + " => Temperatura regulada";
			httpExchange.sendResponseHeaders(200, htmlResponse.length());
		}
		
		// notificarAveria [/estufa => En body raw text "notificarAveria:email_remitente=EMAIL;pass_remitentePASSWORD"]
		// Aqui donde pone EMAIL en el post pon tu direccion de correo y en la contraseña la respectiva contraseña
		if(requestParamValue.contains("notificarAveria")) {
			System.out.println(htmlResponse);
			String email = requestParamValue.split(";")[0].split("=")[1];
			String password = requestParamValue.split(";")[1].split("=")[1];
			
			// Descomentar para ver como llegan los parametros por el post
			// System.out.println(email + " " + password);

			try {
				// No lo envía pero para la entrega no me da tiempo, no se que falla exactamente ahora mismo pero la teoría la tengo clara
				NotificacionCorreo.envioMail(ServidorHTTP.mensaje, ServidorHTTP.asunto, email, password, ServidorHTTP.portEmail, ServidorHTTP.hostEmail, ServidorHTTP.emailDestino, null);
				System.out.println("Envio de correo realizado con éxito");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				System.out.println("Error de envío");
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				System.out.println("Error de envío");
				e.printStackTrace();
			}
			httpExchange.sendResponseHeaders(200, htmlResponse.length());
		}
		
		
		// Escribe el output 
		outputStream.write(htmlResponse.getBytes());
		outputStream.flush();
		outputStream.close();
		System.out.println("Devuelve respuesta HTML: " + htmlResponse);
	}

	private String handlePostRequest(HttpExchange httpExchange) {
		// Procesar la informacion que nos mandan
		System.out.println("Recibida URI tipo POST: " + httpExchange.getRequestBody().toString());
		InputStream inputStream = httpExchange.getRequestBody();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		StringBuilder stringBuilder = new StringBuilder();
		String line;
		try {
			while((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		String postRequest = stringBuilder.toString();
		return postRequest;
	}

	
	// Bloque GET
	private void handleGETResponse(HttpExchange httpExchange) throws IOException {
		// Generar una respuesta que le vamos a mandar al cliente
		// Generar os para mandarle la respuesta
		System.out.println("Recibida URI tipo GET: " + httpExchange.getRequestBody().toString());
		OutputStream outputStream = httpExchange.getResponseBody();
		
		String htmlResponse = "";
		
		String key = httpExchange.getRequestURI().toString().split("\\?")[1];
		System.out.println(key);
		if (key.contains("temperaturaActual")) {
			// Generamos la respuesta con un html con el codigo 200 para confirmar el ok
			htmlResponse = "<html><body>Temperatura actual = " + getTemperaturaActual() + "<br>Temperatura termostato = " + getTemperaturaTermostato() + "</body></html>";
			httpExchange.sendResponseHeaders(200, htmlResponse.length());
		} else {
			htmlResponse = "<html><body>Prueba con la url: http://localhost:7777/estufa?temperaturaActual</body></html>";
			httpExchange.sendResponseHeaders(400, htmlResponse.length());
		}

		
		// Se envia con el os el html
		outputStream.write(htmlResponse.getBytes());
		outputStream.flush();
		outputStream.close();
		System.out.println("Devuelve respuesta HTML: " + htmlResponse);
	}

	private String handleGetRequest(HttpExchange httpExchange) {
		// Obtener la información que nos están mandando por el get
		System.out.println("Recibida URI tipo GET: " + httpExchange.getRequestURI().toString());
		return httpExchange.getRequestURI().toString().split("\\?")[1];
	}

	public int getTemperaturaActual() {
		return temperaturaActual;
	}

	public void setTemperaturaActual(int temperaturaActual) {
		this.temperaturaActual = temperaturaActual;
	}

	public int getTemperaturaTermostato() {
		return temperaturaTermostato;
	}

	public void setTemperaturaTermostato(int temperaturaTermostato) {
		this.temperaturaTermostato = temperaturaTermostato;
	}
	
	// Regular la temperatura para que se iguale
	public void regularTemperatura() {
		
		int cambio = temperaturaActual;
		
		try {
			
			while (getTemperaturaActual() != getTemperaturaTermostato()){
				
			    if(getTemperaturaActual() < getTemperaturaTermostato()) {
			    	cambio++;
			    	System.out.println("Subiendo la temperatura actual a =>  " + getTemperaturaTermostato() + " Temperatura actual ("+ getTemperaturaActual()+")");
			    	setTemperaturaActual(cambio);
			    } else if (getTemperaturaActual() > getTemperaturaTermostato()){
			    	cambio--;
			    	System.out.println("Bajando la temperatura actual a => " + getTemperaturaTermostato() + " Temperatura actual ("+ getTemperaturaActual()+")");
			    	setTemperaturaActual(cambio);
			    }
			    
			    Thread.sleep(5000);
			}
			
			System.out.println("Temperatura regulada:  Termperatura Actual = " + getTemperaturaActual() + ", Temperatura Termostato = " + getTemperaturaTermostato());
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
