package es.florida.ejercicios;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class Probability {
    
    public static void main(String[] args) {
        Probability prob = new Probability();
        double posEarth = Float.parseFloat(args[0]);
        double velEarth = Float.parseFloat(args[1]);
        writeProbabilityNeoCollision(prob, posEarth, velEarth, args);
        
    }
    
    static void writeProbabilityNeoCollision(Probability prob, double posEarth, double velEarth, String[] args) {
    	FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(args[2]);
            pw = new PrintWriter(fw);
            double result = prob.probabilityColision(posEarth, velEarth);
            pw.println(result + "%");
            System.out.println("The probability that it will collide with the earth in the next 50 years is " + result  + "%");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fw)
                    fw.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    public double probabilityColision(double posNEO, double velNEO) {
        double posEarth = 1;
        double velEarth = 100;
        
        long start_time = System.currentTimeMillis();
        
        for (int i = 0; i < (50 * 365 * 24 * 60 * 60); i++) {
            posNEO = posNEO + velNEO * i;
            posEarth = posEarth + velEarth * i;
        }

        double result = 100 * Math.random() * Math.pow(((posNEO - posEarth) / (posNEO + posEarth)), 2);
        BigDecimal bigDecimal = new BigDecimal(result).setScale(2, RoundingMode.UP);

        long end_time = System.currentTimeMillis();
        double miliseconds = (double)(end_time - start_time) ;
        
        System.out.println("Time consumed of the calculation execution: " + (miliseconds/1000) + " seconds");
        if (result > 10) {
        	 System.err.println("GLOBAL ALERT, POSSIBLE NEO COLLISION WITH EARTH!!");
        } else {
            System.out.println("Everything is going well, there is no risk of NEO collision with the Earth.");
        }
        
        return bigDecimal.doubleValue();
    }
}