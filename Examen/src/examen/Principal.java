package examen;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Principal {

	public static void main(String[] args) {
		int personas = 200;
		
			String clase = "examen.Subproceso";
			String javaHome = System.getProperty("java.home");
			String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
			String classpath = System.getProperty("java.class.path");
			System.out.println(classpath);
			String className = clase;

			List<String> command = new ArrayList<>();
			command.add(javaBin);
			command.add("-cp");
			command.add(classpath);
			command.add(className);
			command.add(String.valueOf(personas));

			 System.out.println("Comando que se pasa a ProcessBuilder: " + command);
			 //System.out.println("Comando a ejecutar en cmd.exe: " + command.toString().replace(",",""));

			ProcessBuilder builder = new ProcessBuilder(command);
			try {
				builder.inheritIO().start();
				
			}catch (Exception e) {
	            e.printStackTrace();
			}

	}
}
