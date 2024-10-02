package org.example.marvelapi.pruebas;

public class SerVivo{
    public String nombre;

    public SerVivo(String n) {
        nombre = n;
    }

    @Override
    public String toString() {
        return "Ser vivo: " + nombre;
    }
}
