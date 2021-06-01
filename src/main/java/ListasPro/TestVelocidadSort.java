package ListasPro;

import ListasPro.oficial.Lista;
import ListasPro.oficial.ListaOrdenada;

import java.util.Random;

public class TestVelocidadSort {
    public static void main(String[] args) {
        Lista<String> lista1 = new Lista<>();
        long start = System.nanoTime();
        Random rnd = new Random();
        for (int i = 0; i < 20000; i++){
            int n = rnd.nextInt(1000000);
            lista1.insertar(String.valueOf(n));
        }

        lista1.sort();

        long end = System.nanoTime();

        long elapsed = (end - start)/1000000;
        System.out.println("Ordenado con sort en " + elapsed + "ms");

        // LISTA 2
        start = System.nanoTime();
        ListaOrdenada<String> lista2 = new ListaOrdenada<>();
        rnd = new Random();
        for (int i = 0; i < 20000; i++){
            int n = rnd.nextInt(1000000);
            lista2.insertar(String.valueOf(n));
        }
        end = System.nanoTime();
        elapsed = (end - start)/1000000;
        System.out.println("Ordenado con listaOrdenada en " + elapsed + "ms");
    }
}
