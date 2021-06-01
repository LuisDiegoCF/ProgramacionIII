package practico5.modelo;

import practico5.lista.Lista;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Participantes {
    private Lista<Participante> participantes;
    private PropertyChangeSupport observed;

    public Participantes(){
        participantes = new Lista<>();
        observed = new PropertyChangeSupport(this);
    }

    public void addObserver(PropertyChangeListener panel){
        observed.addPropertyChangeListener(panel);
    }

    public void insertarParticipante(Participante t) {
        participantes.insertar(t);
        observed.firePropertyChange("CAMBIO", 1, 2);
    }

    public void dibujar(Graphics g) {
        int y = 10;

        for (Participante m : participantes){
            m.dibujar(g, y);
            y = y + m.getDistaciaEntreLineas() + 10;
        }
    }

    public void cambioOk() {
        observed.firePropertyChange("CAMBIO", 1, 2);
    }
}
