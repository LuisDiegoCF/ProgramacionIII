package ListasPro.oficial;

import java.util.Comparator;
import java.util.Iterator;

public class Lista<T> implements Iterable<T> {
    protected Contenedor<T> raiz;
    protected int tamano;

    public Lista() {
        raiz = null;
        tamano = 0;
    }

    public Contenedor<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(Contenedor<T> raiz) {
        this.raiz = raiz;
    }

    public void insertar(T objeto) {
        Contenedor<T> nuevo = new Contenedor(objeto);
        nuevo.setSiguiente(raiz);
        raiz = nuevo;
        tamano++;
    }

    public void add(T objeto) {
        if (raiz == null) {
            insertar(objeto);
            return;
        }
        Contenedor<T> actual = raiz;
        Contenedor<T> nuevo = new Contenedor(objeto);
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        // Aqui tenemos al ultimo
        actual.setSiguiente(nuevo);
        tamano++;
    }

    public int tamano() {
        /*int resultado = 0;
        Contenedor<T> actual = raiz;
        while (actual != null) {
            resultado++;
            actual = actual.getSiguiente();
        }
        return resultado;*/
        return tamano;
    }

    public void sort() {
        Contenedor<T> actual1 = raiz;
        int i = 0;
        int maxwhile = tamano - 1;

        while (maxwhile > 0) {
            i = 0;
            actual1 = raiz;

            while (i < maxwhile) {

                T obj1 = actual1.getContenido();
                T obj2 = actual1.getSiguiente().getContenido();

                Comparable<T> cobj2 = (Comparable) obj2;

                if (cobj2.compareTo(obj1) <= 0) {
                    actual1.setContenido(obj2);
                    actual1.getSiguiente().setContenido(obj1);
                }

                actual1 = actual1.getSiguiente();
                i++;
            }
            maxwhile--;
        }
    }

    public void vaciar(){
        raiz = null;
    }

    public void eliminar(int pos) {

        if(pos < 0)
            throw new IndexOutOfBoundsException();
        if(pos >= tamano)
            throw new IndexOutOfBoundsException();

        if (pos == 0){
            raiz = raiz.getSiguiente();
            tamano--;
            return;
        }
        Contenedor<T> actual = raiz;
        int i = 0;
        while ((i + 1) < pos && actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
            i++;
        }
        actual.setSiguiente(actual.getSiguiente().getSiguiente());
        tamano--;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Contenedor<T> actual = raiz;
        while (actual != null) {
            sb.append("[" + actual.getContenido().toString() + "]---");
            actual = actual.getSiguiente();
        }
        return sb.toString();
    }


    @Override
    public Iterator<T> iterator() {
        /*
        Iterator es un patron de diseño es una tecnica que dice
        que en vez de irse a saltar al arreglo para ver cual es la
        posicion correcta nos ayuda a mantener una variable interna
        al valor actual donde esta haciendo la buble
        */
        return new IteradorLista<T>(raiz);
    }

    class IteradorLista<T> implements Iterator<T> {

        private Contenedor<T> actual;

        public IteradorLista(Contenedor<T> inicio) {
            actual = inicio;
        }

        @Override
        public boolean hasNext() {
            return actual != null;
        }

        @Override
        public T next() {
            T resultado = actual.getContenido();
            actual = actual.getSiguiente();
            return resultado;
        }
    }

    class Contenedor<T> {
        private T contenido;
        private Contenedor<T> siguiente;

        public Contenedor(T objeto) {
            contenido = objeto;
            siguiente = null;
        }

        public T getContenido() {
            return contenido;
        }

        public void setContenido(T contenido) {
            this.contenido = contenido;
        }

        public Contenedor<T> getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Contenedor<T> siguiente) {
            this.siguiente = siguiente;
        }
    }
}
