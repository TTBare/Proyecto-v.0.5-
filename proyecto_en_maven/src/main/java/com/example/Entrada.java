package com.example;
public class Entrada {
    private int numero;                   // Número de la entrada
    private String ubicacion;             // Ubicación de la entrada
    private String precio;                // Precio de la entrada
    private boolean ocupada;              // Indicador de si la entrada está ocupada o no
    private String nombreComprador;       // Nombre del comprador de la entrada
    private String correoComprador;       // Correo electrónico del comprador de la entrada
    private long telefonoComprador;     // Número de teléfono del comprador de la entrada

    public Entrada() {
        this.numero = 0;
        this.ubicacion = "";
        this.precio = "";
        this.ocupada = false;           // Inicialmente la entrada no está ocupada
        this.nombreComprador = "";      // Inicialmente no se ha asignado un nombre de comprador
        this.correoComprador = "";      // Inicialmente no se ha asignado un correo electrónico de comprador
        this.telefonoComprador = 0;    // Inicialmente no se ha asignado un número de teléfono de comprador
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int num){
        this.numero=num;
    }
   
    public String getUbicacion() {
        return ubicacion;
    }
    
    public void setUbicacion(String ubi,int num){
        this.ubicacion = ubi +" - Asiento "+ num; 

    }
    
    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String prec){
       this.precio = prec;
    }   

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public String getCorreoComprador() {
        return correoComprador;
    }

    public void setCorreoComprador(String correoComprador) {
        this.correoComprador = correoComprador;
    }

    public long getTelefonoComprador() {
        return telefonoComprador;
    }

    public void setTelefonoComprador(long telefonoComprador) {
        this.telefonoComprador = telefonoComprador;
    }
}
