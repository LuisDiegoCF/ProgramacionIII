package ListasPro.primero;

public class Lista {

    private Contenedor raiz;

    public Lista(){
        raiz = null;
    }

    public void insertar(Object objeto) {
        Contenedor nuevo = new Contenedor(objeto);
        nuevo.setSiguiente(raiz);
        raiz = nuevo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Contenedor actual = raiz;
        while (actual != null){
            sb.append("[" + actual.getContenido().toString() + "]---");
            actual = actual.getSiguiente();
        }
        return sb.toString();
    }
}
