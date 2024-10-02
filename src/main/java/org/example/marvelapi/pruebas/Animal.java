package org.example.marvelapi.pruebas;

public class Animal extends SerVivo{
    public Animal(String n) {
        super(n);
    }

    @Override
    public String toString() {
        return "Animal: " + nombre;
    }


}
