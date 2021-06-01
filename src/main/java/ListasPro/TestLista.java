package ListasPro;

import ListasPro.primero.Lista;

import java.util.Iterator;

public class TestLista {
    public static void main(String[] args) {
        Lista testLista = new Lista();
        testLista.insertar("hugo");
        testLista.insertar("Paco");
        testLista.insertar("Luis");
        System.out.println("Lista con Contenedor: " + testLista);

        ListasPro.oficial.Lista<String> a = new ListasPro.oficial.Lista<>();
        a.insertar("hugo");
        a.insertar("Paco");
        a.insertar("Luis");
        System.out.println("Nuestra Lista: " + a);
        System.out.println("Tamano: " + a.tamano());

        ListasPro.oficial.Lista<String> b = new ListasPro.oficial.Lista<>();
        b.add("hugo");
        b.add("Paco");
        b.add("Luis");
        System.out.println("Nuestra Lista: " + b);
        System.out.println("Tamano: " + b.tamano());
        // Esta es la forma de aplicar el patron de Iterator
        Iterator<String> iterator = b.iterator();
        while (iterator.hasNext()){
            String s = iterator.next();
            System.out.println("Desde while: " + s);
        }
        // Java nos permite hacer ForEach si es que nuestra
        // lista es iterable
        for (String s : b){
            System.out.println("Desde for abreviado: " + s);
        }
    }
}
