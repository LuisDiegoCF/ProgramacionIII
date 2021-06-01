package practico1.modelo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Linea {
    private int alto;

    private int x;
    private int y;
    private int num;

    private final static Logger logger = LogManager.getLogger();

    private PropertyChangeSupport observed;

    public Linea() {
        x = 0;
        y = 400;
        alto = ((int) (Math.random() * 400) + 1);

        observed = new PropertyChangeSupport(this);
        logger.info(num + ": Se a creado el objeto Linea, Altura: " + alto + " Posicion X: " + x);
    }

    public void addObserver(PropertyChangeListener observador){
        observed.addPropertyChangeListener(observador);
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        int oldValue = this.alto;
        this.alto = alto;
        int newValue = this.alto;
        observed.firePropertyChange("CAMBIO", oldValue, newValue);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        int oldValue = this.x;
        this.x = x;
        int newValue = this.x;
        observed.firePropertyChange("CAMBIO", oldValue, newValue);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
