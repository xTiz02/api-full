package org.example.marvelapi.pruebas;

public class Gato extends Animal{
    public Gato(String n) {
        super(n);
    }

    @Override
    public String toString() {
        return "Gato: " + nombre;
    }
}
