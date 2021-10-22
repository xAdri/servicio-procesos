package es.florida.ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Launcher {

    public static void main(String[] args) throws IOException {
    	System.out.println("Starting NEO Help!");
    	System.out.println("[Processors available from this pc: " + getCores() + "]");
        
        long start_time = System.currentTimeMillis();
        readNeosLaunchProbability();
        long end_time = System.currentTimeMillis();
        double miliseconds = (double)(end_time - start_time);
        System.out.println("Total execution time of the program: " + (miliseconds/1000) + " seconds.");
    	System.out.println("Thanks for use NEO Help!");
        

    }
    
    static void readNeosLaunchProbability() throws IOException {
    	File f = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            f = new File("NEOs.txt");
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String line;

            for (int i = 0; i < getCores(); i++) {
                line = br.readLine();
                System.out.println("==> Doing the calculations for: " + line);
                String[] neoData = line.split(",");
                launchProbability(neoData[0], neoData[1], neoData[2]);
            }

        } catch (Exception e) {

        } finally {
            fr.close();
        }
    }
    
    static int getCores() {
    	return Runtime.getRuntime().availableProcessors();
    }
    
    static void launchProbability(String fRes, String posNEO, String velNEO) {
        String cName = "es.florida.ejercicios.Probability";
        
        try {

            String javaHome = System.getProperty("java.home");
            String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
            String classpath = System.getProperty("java.class.path");
            String className = cName;

            List < String > command = new ArrayList < > ();
            command.add(javaBin);
            command.add("-cp");
            command.add(classpath);
            command.add(className);
            command.add(posNEO.toString());
            command.add(velNEO.toString());
            command.add(fRes.toString());

            ProcessBuilder builder = new ProcessBuilder(command);
            Process process = builder.inheritIO().start();
            process.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}