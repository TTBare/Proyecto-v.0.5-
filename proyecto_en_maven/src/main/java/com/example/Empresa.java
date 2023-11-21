package com.example;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Empresa {
    private Evento evento;
    private Scanner scanner;
    private String opcionMenuEmpresa;

    public Empresa(Evento evento) {
        this.evento = evento;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenuEmpresa() {   
        do {scanner.nextLine();
            ConsoleUtils.clearConsole();
            System.out.println("=== Menú Empresa ===");
            System.out.println("1. Ver entradas ocupadas");
            System.out.println("2. Agregar entradas");
            System.out.println("3. Crear super user");
            System.out.println("0. Volver");
            System.out.print("Ingrese una opción: ");
            opcionMenuEmpresa = scanner.nextLine();
            
    switch (opcionMenuEmpresa) {
        case "1":
        evento.mostrarOcuapadas();
           break;
        case "2":  
        evento.setDatosEntradas();
        break;
        
        case "3":
        crearUsuario();
        break;
        case "0":
        ConsoleUtils.clearConsole();
        System.out.print("Volviendo");
        opcionMenuEmpresa="0";
        break;
        //    scanner.nextLine(); 
        default:
        ConsoleUtils.clearConsole();
        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
        scanner.nextLine();
        break;
    }
} while (!opcionMenuEmpresa.equals("0"));
}





public void crearUsuario(){
    System.out.print("Ingrese un nombre de usuario: ");
    String nuevoUsuario = scanner.nextLine();
    
    System.out.print("Ingrese una contraseña: ");
    String nuevaClave = scanner.nextLine();

    try (FileReader reader = new FileReader("claves.json")) {
            // Parsear el archivo JSON
            JsonObject jsonUsuarios = new Gson().fromJson(reader, JsonObject.class);

            // Obtener el array de usuarios
            JsonArray usuarios = jsonUsuarios.getAsJsonArray("usuarios");

            // Verificar si el usuario ya existe
            for (int i = 0; i < usuarios.size(); i++) {
                JsonObject usuarioObj = usuarios.get(i).getAsJsonObject();
                String usuarioExistente = usuarioObj.get("usuario").getAsString();

                if (nuevoUsuario.equals(usuarioExistente)) {
                    System.out.println("El usuario ya existe. Por favor, elija otro nombre de usuario.");
                    return;
                }
            }
            JsonObject nuevoUsuarioObj = new JsonObject();
            nuevoUsuarioObj.addProperty("usuario", nuevoUsuario);
            nuevoUsuarioObj.addProperty("clave", nuevaClave);
            
            usuarios.add(nuevoUsuarioObj);
            
            // Guardar las actualizaciones en el archivo JSON
            try (FileWriter writer = new FileWriter("claves.json")) {
                new Gson().toJson(jsonUsuarios, writer);
                System.out.println("Usuario registrado exitosamente.");
            } catch (IOException e) {
                System.out.println("Error al escribir en el archivo JSON: " + e.getMessage());
            }
        
        } catch (IOException | JsonParseException e) {
            // Manejar errores de lectura o parseo del JSON
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        }
}
} 


