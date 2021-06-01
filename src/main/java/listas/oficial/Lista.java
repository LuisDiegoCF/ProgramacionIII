package listas.oficial;

import java.util.Iterator;

public class Lista<T> implements Iterable<T> {
    protected Contenedor<T> raiz;
    protected int tamano;

    public Lista() {
        raiz = null;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public Contenedor<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(Contenedor<T> raiz) {
        this.raiz = raiz;
    }

    public void insertar(T o) {
        /*if(!(o instanceof Comparable)) {*/
        Contenedor<T> nuevo = new Contenedor<>(o);
        nuevo.setSiguiente(raiz);
        raiz = nuevo;
        tamano++;
        /*} else {
            Contenedor<T> nuevo  = new Contenedor<T>(o);
            Contenedor<T> actual = raiz;
            Comparable<T> comparable = (Comparable) o;
            while (actual != null && comparable.compareTo(actual.getContenido()) > 0){

            }
        }*/
    }

    public int tamano() {
        return tamano;
        /*int resultado = 0;
        Contenedor<T> actual = raiz;
        while (actual != null){
            resultado++;
            actual = actual.getSiguiente();
        }
        return resultado;*/
    }

    public void add(T o) {
        if (raiz == null) {
            insertar(o);
            return;
        }
        Contenedor<T> actual = raiz;
        Contenedor<T> nuevo = new Contenedor<>(o);

        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }

        //Aqui tenemos al ultimo
        actual.setSiguiente(nuevo);
        tamano++;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Contenedor<T> actual = raiz;
        while (actual != null) {
            sb.append("[").append(actual.getContenido().toString()).append("]---");
            actual = actual.getSiguiente();
        }

        return sb.toString();
    }

    /*public void intercambiar(int a1, int a2){

    }*/

    @Override
    public Iterator<T> iterator() {
        return new IteratorLista<T>(raiz);
    }

    class IteratorLista<T> implements Iterator<T> {

        private Contenedor<T> actual;

        public IteratorLista(Contenedor<T> inicio) {
            actual = inicio;
        }

        @Override
        public boolean hasNext() {
            return actual != null;
        }

        @Override
        public T next() {
            T result = actual.getContenido();
            actual = actual.getSiguiente();
            return result;
        }
    }

    class Contenedor<T> {
        private T contenido;
        private Contenedor<T> siguiente;

        public Contenedor(T c) {
            contenido = c;
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
