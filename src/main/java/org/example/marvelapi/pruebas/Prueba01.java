package org.example.marvelapi.pruebas;

import org.example.marvelapi.util.Mb5Encoder;

import java.util.List;

public class Prueba01 {
    public static void main(String[] args) {
        /*String n = "hola";

        System.out.println(n.getBytes());
        System.out.println(n.getBytes().length);
        System.out.println(n.getBytes().toString());
        System.out.println(n.getBytes().toString().length());
        for (byte b : n.getBytes()) {
            System.out.print(b + " :");
            //ver bits
            for (int i = 0; i < 8; i++) {
                System.out.print((b & 0x80) == 0 ? 0 : 1);
                b <<= 1;
            }
            System.out.println();
        }
        Mb5Encoder mb5Encoder = new Mb5Encoder();
        System.out.println(mb5Encoder.encode("hola"));*/

//        List<Animal> lista = List.of(new Animal("Perro"), new Gato("Gato"));
//        muestraElementos(lista);
//        List<Animal> lista2 = List.of(new Animal("Loro"), new Gato("Gato"));
//        List<SerVivo> lista3 = List.of(new Animal("Loro"), new Gato("Gato"));
//        List<Gato> lista4 = List.of(new Gato("Gato"), new Gato("Gato"));
//        cargarElementos(lista2);
//        muestraElementos(lista2);
//        cargarElementos(lista3);
        //cargarElementos(lista4);
        //muestraElementos(lista3);
    }

    public static void muestraElementos(List<? extends Animal> lista) {//solo se pasa lista de Animal o de sus hijos
        //porque no puedo agregar elementos a la lista: lista.add(new Animal("Perro")); no se puede agregar elementos a la lista  porque no se sabe el tipo de lista

        for (Animal a : lista) {
            System.out.println(a);
        }
    }
    public static void cargarElementos(List<? super Animal> lista) {//solo se pasa lista de Animal o de sus padres
        lista.add(new Animal("Perro"));
        lista.add(new Gato("Gato"));
        //se puede agregar elementos a la lista porque se sabe que es de tipo Animal o de sus padres
        //lista.add(new SerVivo("Gato")); //no se puede agregar porque no es de tipo Animal o de sus padres
    }
}
