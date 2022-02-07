package es.florida.ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

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
			// no le pasa ningún valor y he asumido que no es necesario confirmar la key de temperaturaActual
			handleGETResponse(httpExchange);
		} else if ("POST".equals(httpExchange.getRequestMethod())) {
			requestParamValue = handlePostRequest(httpExchange);
			handlePOSTResponse(httpExchange, requestParamValue);
		}
	}

	private void handlePOSTResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
		// Respuesta a la petición post
		OutputStream outputStream = httpExchange.getResponseBody();
		
		// Html sencillo para mostrar lo que se procesa del servidor junto a la response de que esta ok
		String htmlResponse = "Parametro/s POST: " + requestParamValue + " => Se procesara por parte del servidor.";
		setTemperaturaTermostato(Integer.parseInt(requestParamValue));
		httpExchange.sendResponseHeaders(200, htmlResponse.length());
		
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

	private void handleGETResponse(HttpExchange httpExchange) throws IOException {
		// Generar una respuesta que le vamos a mandar al cliente
		// Generar os para mandarle la respuesta
		System.out.println("Recibida URI tipo GET: " + httpExchange.getRequestBody().toString());
		OutputStream outputStream = httpExchange.getResponseBody();
		
		// Generamos la respuesta con un html con el codigo 200 para confirmar el ok
		String htmlResponse = "<html><body>Temperatura actual = " + getTemperaturaActual() + "<br>Temperatura termostato = " + getTemperaturaTermostato() + "</body></html>";
		httpExchange.sendResponseHeaders(200, htmlResponse.length());
		
		// Se envia con el os el html
		outputStream.write(htmlResponse.getBytes());
		outputStream.flush();
		outputStream.close();
		System.out.println("Devuelve respuesta HTML: " + htmlResponse);
	}

	private String handleGetRequest(HttpExchange httpExchange) {
		// Obtener la información que nos están mandando por el get
		System.out.println("Recibida URI tipo GET: " + httpExchange.getRequestURI().toString());
		return httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1];
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
	
	// Regular la temperatura
    public void regularTemperatura() throws InterruptedException {
        while (true) {
            synchronized(this) {
                while (getTemperaturaActual() == getTemperaturaTermostato())
                    wait();
                System.out.println("Regulando temperatura: " + tiempo / 1000 + " segundos");
                Thread.sleep(tiempo);
                notify();
            }
        }
    }

}
