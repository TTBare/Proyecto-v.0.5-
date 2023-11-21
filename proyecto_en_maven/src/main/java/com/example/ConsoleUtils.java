package com.example;
import java.lang.Thread;
import java.util.Random;
public class ConsoleUtils {
    
    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime();//.exec("clear");
            }
        } catch (final Exception e) {
            // Manejo de excepciones
        }
    }
    
    public static void sleep(int time){
        try{Thread.sleep(time);}
        catch(InterruptedException e){
            e.printStackTrace();
        }

    }
    
    public static void puncarga(int puntos){
        for (int i=0;i<puntos;i++){
            sleep(1000);
            System.out.print(".");}
        sleep(1000);
    }
    
    public int Rand(int min,int max){
            Random random = new Random();
            return random.nextInt(max - min + 1) + min;
    }
    }

