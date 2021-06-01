package listas.oficial;

public class Perro implements Comparable<Perro> {

    private int edad;
    private String nombre;

    public Perro(int edad, String nombre) {
        this.edad = edad;
        this.nombre = nombre;
    }

    @Override
    public int compareTo(Perro o) {
        // Si perro o es IGUAL a nosotros entonces devuelve 0
        // Si perro o viene ANTES que nosotros entonces devuelve -1
        // Si perro o viene DESPUES que nosotros entonces devuelve 1
        if (getNombre().equals(nombre)) {
            if (o.getEdad() == edad)
                return 0;
            if (o.getEdad() < edad)
                return -1;
            if (o.getEdad() > edad)
                return 1;
        }
        return o.getNombre().compareTo(nombre);
    }

    @Override
    public String toString() {
        return nombre + " (" + edad + ")";
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
