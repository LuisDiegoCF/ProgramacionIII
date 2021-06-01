package practico5.modelo;

import practico5.lista.Lista;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Iterator;
import java.util.List;

public class Tablero {

    private Lista<Mensaje> mensajes;
    private PropertyChangeSupport observed;

    public Tablero(){
        mensajes = new Lista<>();
        observed = new PropertyChangeSupport(this);
    }

    public void addObserver(PropertyChangeListener panel){
        observed.addPropertyChangeListener(panel);
    }

    public void insertarEnMensajes(Mensaje t) {
        mensajes.insertar(t);
        observed.firePropertyChange("CAMBIO", 1, 2);
    }

    public void dibujar(Graphics g) {
        int y = 10;

        for (Mensaje m : mensajes){
            m.dibujar(g, y);
            y = y + m.getAlto() + 10;
        }
    }

    public Mensaje getUltimoMensajeMio() {

        for (Mensaje m : mensajes){
            if (!m.isUsuarioRemoto())
                return m;
        }
        return null;
    }
}
