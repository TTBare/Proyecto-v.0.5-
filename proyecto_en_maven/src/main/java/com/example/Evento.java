package com.example;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
// import java.util.Stack;
public class Evento {
    public String fecha;             // Fecha del evento
    public String hora;              // Hora del evento
    public String lugar;             // Lugar del evento
    public String tdp;               // partido a ocurrir
    private List<Entrada> entradas;   // Lista de entradas disponibles para el evento
    private int numerodelaultimaentrada;
    private Scanner scanner = new Scanner(System.in);  
    public Evento(String fecha, String hora, String lugar, String tdp) {
        this.fecha = fecha;
        this.hora = hora;
        this.lugar = lugar;
        this.tdp = tdp;
        this.entradas = new ArrayList<>();   // Inicializar la lista de entradas vacía
    }
    
    public void agregarEntrada(Entrada entrada) {
        entradas.add(entrada);   // Agregar una entrada a la lista de entradas disponibles
    }
    
    public List<Entrada> getEntradasDisponibles() {
        List<Entrada> disponibles = new ArrayList<>();
        for (Entrada entrada : entradas) {
            if (!entrada.isOcupada()) {
                disponibles.add(entrada);   // Agregar a la lista de entradas disponibles si no está ocupada
            }
        }
        return disponibles;
    }

    public List<Entrada> getEntradasOcupadas() {
        List<Entrada> ocupadas = new ArrayList<>();
        for (Entrada entrada : entradas) {
            if (entrada.isOcupada()) {
                ocupadas.add(entrada);   // Agregar a la lista de entradas ocupadas si está ocupada
            }
        }
        return ocupadas;
    }

    public void mostrarOcuapadas(){
        List<Entrada> ocupadas = getEntradasOcupadas();
        ConsoleUtils.clearConsole();
        System.out.println("Entradas ocupadas:");
        int cantidad_de_entradas_ocupadas = ocupadas.size();
        if(cantidad_de_entradas_ocupadas == 0){
            System.out.println("No se vendio ninguna entrada");
            System.out.println("Para continuar apretar la tecla enter..."); 
            }
        else{
            System.out.println("Se vendieron un total de : " + cantidad_de_entradas_ocupadas + " entradas");
            for (Entrada entrada : ocupadas) 
                {System.out.println(entrada.getNumero() + " - " + entrada.getUbicacion()+" - $"+ entrada.getPrecio()+ " de "+ entrada.getCorreoComprador());}
            
                System.out.println("Para continuar apretar la tecla enter...");
                scanner.nextLine(); 
            }
    }

    public Entrada comprarEntrada(int numeroEntrada, String nombreComprador, String correoComprador, long telefonoComprador) {
        for (Entrada entrada : entradas) {
            if (entrada.getNumero() == numeroEntrada && !entrada.isOcupada()) {
                entrada.setOcupada(true);
                entrada.setNombreComprador(nombreComprador);
                entrada.setCorreoComprador(correoComprador);
                entrada.setTelefonoComprador(telefonoComprador);
                return entrada;   // Devolver la entrada comprada
            }
        }
    
        return null;   // No hay entradas disponibles o el número de entrada es inválido
    }

    public int geLastNum(){
        List<Entrada> disponibles = getEntradasDisponibles();
        int cantidad_de_entradas_disponibles = disponibles.size();
        if (cantidad_de_entradas_disponibles==0)
        {numerodelaultimaentrada = 0;}
        else{
            Entrada ultimoElemento = disponibles.get((cantidad_de_entradas_disponibles-1));
            numerodelaultimaentrada= ultimoElemento.getNumero();}
        return numerodelaultimaentrada;
    }    

    public boolean getAvalibles(){
        if(getEntradasDisponibles().size()!=0)
            return true;
        else 
            return false;
    }   

    public void realizarCompra() {

        if (!getAvalibles()) {
            System.out.println("Lo sentimos, no hay entradas disponibles.");
            System.out.println("Para continuar apretar la tecla enter...");
            scanner.nextLine();
        } else {
            System.out.println("Entradas disponibles:");
            for (Entrada entrada : getEntradasDisponibles()) {
                System.out.println(entrada.getNumero() + " - " + entrada.getUbicacion() + "- $" + entrada.getPrecio());
            }
            Entrada entradaComprada = comprarEntrada(obtenerNumeroEntrada(), obtenerNombre(), obtenerCorreoElectronico(), obtenerNumeroTelefono()); // Realiza la compra de la entrada
            if (entradaComprada != null) {
                ConsoleUtils.clearConsole();
                System.out.println("¡Compra realizada con éxito!");
                System.out.println("Datos de la entrada:");
                System.out.println("Número de entrada: " + entradaComprada.getNumero());
                System.out.println("Ubicación: " + entradaComprada.getUbicacion());
                System.out.println("Precio: " + entradaComprada.getPrecio());
                System.out.println("Nombre del comprador: " + entradaComprada.getNombreComprador());
                System.out.println("Datos del Evento: " +tdp+" (Amistoso) " +lugar+" Fecha: "+fecha+" Horario: "+hora+("hs."));
                System.out.println("Correo electrónico del comprador: " + entradaComprada.getCorreoComprador());
                System.out.println("Número de teléfono del comprador: " + entradaComprada.getTelefonoComprador());
                System.out.println("Para continuar apretar la tecla enter...");
                scanner.nextLine();
            } else {
                System.out.println("El número de entrada seleccionado no está disponible.");
                System.out.println("Para continuar apretar la tecla enter...");
                scanner.nextLine();
            }
        }
    }

    private String obtenerNombre() {
        String nombre = "";
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Ingrese su nombre: ");
            nombre = scanner.nextLine(); // Lee el nombre ingresado por el comprador
            if (nombre.matches(".*\\d.*")) {// verifica que el nombre del comprador no contenga numeros
                System.out.println("Error: El nombre no puede contener números.");
            } else {
                validInput = true;
            }
        }
        return nombre;
    }

    private String obtenerCorreoElectronico() {
        String correoElectronico = "";
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Ingrese su correo electrónico: ");
            correoElectronico = scanner.nextLine(); // Lee el correo electrónico ingresado por el comprador
            if (!correoElectronico.contains("@")) { //verifica que el correo al menos contenga el @
                System.out.println("Error: El correo electrónico debe contener al menos el símbolo @.");
            } else {
                validInput = true;
            }
        }
        return correoElectronico;
    }

    private long obtenerNumeroTelefono() {
        long numeroTelefono = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Ingrese su número de teléfono móvil:");
                String inputTelefono = scanner.nextLine(); // Lee el número de teléfono ingresado por el comprador
                if (inputTelefono.length() < 10) {  //verifica que el numero sea valido a un numero argentino
                    throw new InputMismatchException();
                }
                numeroTelefono = Long.parseLong(inputTelefono);
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número de teléfono válido ");
            }
        }
        return numeroTelefono;
    }

    private int obtenerNumeroEntrada() {
        int numeroEntrada = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Seleccione el número de entrada que desea comprar: ");
                numeroEntrada = scanner.nextInt(); // Lee el número de entrada seleccionado por el comprador
                scanner.nextLine(); // Limpiar Buffer
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número de entrada válido.");
                scanner.nextLine(); // Limpiar el Buffer
            }
        }
        return numeroEntrada;
    }

    private String obtenerPrecio(){
        System.out.println("Ingrese el valor de las entradas:");
        String a =scanner.nextLine();
        return a;
    }

    private String obtenerUbicacion(){
            System.out.println("Ingrese la platea donde se van a ubicar");
            String b = scanner.nextLine();
            return b;
    }

    private int obtenerNuevas(){
        System.out.println("Ingrese el numero de entradas que desea ingresar (tenga en cuenta que el valor que les asigne sera asi para toda esa cantidad)");
        return scanner.nextInt();
    }

    public void setDatosEntradas(){
            scanner.nextLine();
            ConsoleUtils.clearConsole();
            String precio = obtenerPrecio();
            ConsoleUtils.clearConsole();
            String ubicacion = obtenerUbicacion();
            ConsoleUtils.clearConsole();
            int nuevas = obtenerNuevas();
            ConsoleUtils.clearConsole();
            crearVariasEntradas(nuevas, ubicacion, precio);
            ConsoleUtils.clearConsole();
            System.out.print("Creando");
            ConsoleUtils.puncarga(3);
            ConsoleUtils.clearConsole();
            System.out.println("Creadas con exito");
    }
        
    public Entrada fabricarEntrada(String platea,String precioentrada,int i) {   
        numerodelaultimaentrada=geLastNum();
        int nuevonumero=(numerodelaultimaentrada+1);
        Entrada u = new Entrada();
        u.setNumero(nuevonumero);
        u.setUbicacion(platea,nuevonumero);
        u.setPrecio(precioentrada);
        agregarEntrada(u);
        return u;
    }
    
    private void crearVariasEntradas(int nuevas,String ubicacion, String precio){
        
        numerodelaultimaentrada=geLastNum();
        if(nuevas==0){
            return;
        }
        else{
            fabricarEntrada(ubicacion, precio, nuevas);
            crearVariasEntradas(nuevas-1, ubicacion, precio);
        }

    }
    public List<Entrada> getEntradasDisponiblesOrdenadas() {
        List<Entrada> disponibles = new ArrayList<>();
        for (Entrada entrada : entradas) {
            if (!entrada.isOcupada()) {
                disponibles.add(entrada);
            }
        }

        // Ordenar las entradas por número utilizando el algoritmo de burbuja
        int n = disponibles.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (disponibles.get(j).getNumero() > disponibles.get(j + 1).getNumero()) {
                    // Intercambiar las entradas si están en el orden incorrecto
                    Entrada temp = disponibles.get(j);
                    disponibles.set(j, disponibles.get(j + 1));
                    disponibles.set(j + 1, temp);
                }
            }
        }

        return disponibles;
    }

    public void mostrarEntradasDisponiblesOrdenadas() {
        List<Entrada> disponiblesOrdenadas = getEntradasDisponiblesOrdenadas();
        if (disponiblesOrdenadas.isEmpty()) {
            System.out.println("No hay entradas disponibles.");
        } else {
            System.out.println("Entradas disponibles (ordenadas por número):");
            for (Entrada entrada : disponiblesOrdenadas) {
                System.out.println(entrada.getNumero() + " - " + entrada.getUbicacion() + " - $" + entrada.getPrecio());
            }
        }
        System.out.println("Para continuar apretar la tecla enter...");
        scanner.nextLine();
    }
    
}

