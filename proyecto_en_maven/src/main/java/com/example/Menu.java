package com.example;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu {
    private Evento evento;
    private Scanner scanner;
    private String opcion;
    public Menu(Evento evento) {
        this.evento = evento;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        do {
            try {
            ConsoleUtils.clearConsole();
                    System.out.println("=== MENÚ ===");
                    System.out.println("1. Mostrar entradas disponibles");
                    System.out.println("2. Entrar en el menu empresa");
                    System.out.println("3. Comprar entrada");
                    System.out.println("4. Salir");
                    System.out.print("Seleccione una opción: ");
                opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                        ConsoleUtils.clearConsole();
                            System.out.println("Entradas disponibles:");
                    if(evento.getAvalibles()){
                        for (Entrada entrada : evento.getEntradasDisponiblesOrdenadas()) {
                            System.out.println(entrada.getNumero() + " - " + entrada.getUbicacion()+" - $"+ entrada.getPrecio());
                            }
                            System.out.println("Para continuar apretar la tecla enter...");
                                scanner.nextLine(); }
                    else{
                            System.out.println("Las entradas estan agotadas");
                            System.out.println("Para continuar apretar la tecla enter...");
                                scanner.nextLine(); 
                    }
                    break;
                case "2":
                    ConsoleUtils.clearConsole();
                    Empresa menuEmpresa = new Empresa(evento);
                    if (SeguridadAdmin.requestLogin()) {
                        ConsoleUtils.clearConsole();
                        System.out.println("Clave correcta. Acceso concedido.");
                        menuEmpresa.mostrarMenuEmpresa();
                        ConsoleUtils.puncarga(3);       
                    }   
                    else {
                            System.out.println("Clave incorrecta. Acceso denegado.");
                            System.out.println("Para continuar apretar la tecla enter...");
                            scanner.nextLine(); 
                        }
                       break;
                case "3":
                ConsoleUtils.clearConsole();
                evento.realizarCompra();
                break;
                case "4":
                    ConsoleUtils.clearConsole();
                        System.out.println("¡Hasta luego!");
                        opcion = "4";
                        break;
                default:
                    ConsoleUtils.clearConsole();
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    scanner.nextLine();
                    break;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Error: No se encontró ninguna línea.");
                
            }
            } while (!opcion.equals("4")); 
    }}
