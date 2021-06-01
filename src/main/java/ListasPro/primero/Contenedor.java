package ListasPro.primero;

public class Contenedor {

    private Object contenido;
    private Contenedor siguiente;

    public Contenedor(Object objeto){
        contenido = objeto;
        siguiente = null;
    }

    public Object getContenido() {
        return contenido;
    }

    public void setContenido(Object contenido) {
        this.contenido = contenido;
    }

    public Contenedor getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Contenedor siguiente) {
        this.siguiente = siguiente;
    }
}
