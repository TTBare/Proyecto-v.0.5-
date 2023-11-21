package com.example;
import java.util.Scanner;

public class Main {
    static Scanner scanner= new Scanner(System.in);
    public static void main(String[] args) {
        // Crear un nuevo evento
        Evento evento = new Evento("2023-07-01", "20:00", "Estadio: Monumental", "Argentina-Ecuador");
   
        // Crear el menú y mostrarlo
        Menu menu = new Menu(evento);
        ConsoleUtils.clearConsole();
        System.out.println("Bienvenido a Truchantikeck Partido: "+evento.tdp+" (Amistoso) " +evento.lugar+" Fecha: "+evento.fecha+" Horario: "+evento.hora+"hs.");
        System.out.println("Precione enter para ingresar");
        scanner.nextLine(); 
        menu.mostrarMenu();
    }
}
