package ListasPro.oficial;

public class ListaOrdenada<T extends Comparable<T>> extends Lista<T>{

    public ListaOrdenada(){
        super();
    }

    @Override
    public void insertar(T objeto) {
        if(raiz == null){
            super.insertar(objeto);
            return;
        }
        Comparable<T> comparable = (Comparable) objeto;
        if(comparable.compareTo(raiz.getContenido()) <= 0){
            super.insertar(objeto);
            return;
        }
        Contenedor<T> nuevo = new Contenedor(objeto);
        Contenedor<T> actual = raiz;
        while (actual.getSiguiente() != null && comparable.compareTo(actual.getSiguiente().getContenido()) > 0){
            actual = actual.getSiguiente();
        }

        // Aqui actual es el nodo anterior al lugar donde debe ir objeto (nuevo )
        nuevo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(nuevo);
        tamano++;
    }
}
