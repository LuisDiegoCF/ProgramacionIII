package listas;

import listas.oficial.Lista;

import java.util.ArrayList;
import java.util.Iterator;

public class TestArrayList {
    public static void main(String[] args) {

        ArrayList<String> palabras = new ArrayList();
        palabras.add("Hugo");
        palabras.add("Paco");
        palabras.add("Luis");

        System.out.println(palabras);

        Lista testLista = new Lista();
        testLista.insertar("hugo");
        testLista.insertar("Paco");
        testLista.insertar("Luis");
        System.out.println("Lista2 con Contenedor: " + testLista);

        Lista<String> a = new Lista<>();
        a.insertar("hugo");
        a.insertar("Paco");
        a.insertar("Luis");
        System.out.println("Nuestra Lista2: " + a);
        System.out.println("Tamano: " + a.getTamano());

        Lista<String> b = new Lista<>();
        b.add("Hugo");
        b.add("Paco");
        b.add("Luis");
        b.add("Sebastian");
        b.add("Jose");
        System.out.println("Nuestra Lista2: " + b);
        System.out.println("Tamano: " + b.getTamano());

        Iterator<String> iterador = b.iterator();
        while(iterador.hasNext()){
            String s = iterador.next();

            System.out.println("Desde while: " + s);
        }

        for (String s : b){
            System.out.println("Desde for abreviado: " + s);
        }
    }
}
