package practico4.objetos;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Comparator;

public class Persona implements Comparable<Persona>{
    private String nombre;
    private String genero;
    private int edad;
    private double altura;
    private PropertyChangeSupport observed;
    private Comparator<Persona> comparador;

    public Persona(String nombre, String genero, int edad, double altura) {
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
        this.altura = altura;
        this.comparador = new ComparatorPersonaNormal();

        observed = new PropertyChangeSupport(this);
    }

    @Override
    public int compareTo(Persona o) {
        // Si persona o es IGUAL a nosotros entonces devuelve 0
        // Si persona o viene ANTES que nosotros entonces devuelve -1
        // Si persona o viene DESPUES que nosotros entonces devuelve 1
        return comparador.compare(this, o);
    }

    public void addObserver(PropertyChangeListener observador){
        observed.addPropertyChangeListener(observador);

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void cambioOk(){
        observed.firePropertyChange("CAMBIO", 1, 2);
    }
}
