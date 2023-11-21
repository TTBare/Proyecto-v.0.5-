package com.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SeguridadAdmin {

    static Scanner scanner = new Scanner(System.in);
    
    public static boolean requestLogin() {
        System.out.print("Ingrese el usuario: ");
        String usuario = scanner.nextLine();

        System.out.print("Ingrese la contraseña: ");
        String clave = scanner.nextLine();

        // Intentar leer las combinaciones de usuario y contraseña desde el archivo JSON
        try (FileReader reader = new FileReader("claves.json")) {
            // Parsear el archivo JSON
            JsonObject jsonUsuarios = new Gson().fromJson(reader, JsonObject.class);

            // Obtener el array de usuarios
            JsonArray usuarios = jsonUsuarios.getAsJsonArray("usuarios");

            // Verificar las combinaciones de usuario y contraseña
            for (int i = 0; i < usuarios.size(); i++) {
                JsonObject usuarioObj = usuarios.get(i).getAsJsonObject();
                String usuarioGuardado = usuarioObj.get("usuario").getAsString();
                String claveGuardada = usuarioObj.get("clave").getAsString();

                if (usuario.equals(usuarioGuardado) && clave.equals(claveGuardada)) {
                    return true; // Usuario y contraseña correctos
                }
            }

            // Si no se encontró una combinación válida
            return false;
        } catch (IOException | JsonParseException e) {
            // Manejar errores de lectura o parseo del JSON
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
            return false; // En caso de error, considerar la combinación como incorrecta
        }
    }}
