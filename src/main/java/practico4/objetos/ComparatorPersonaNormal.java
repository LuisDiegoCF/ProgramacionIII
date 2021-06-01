package practico4.objetos;

import java.util.Comparator;

public class ComparatorPersonaNormal implements Comparator<Persona> {
    @Override
    public int compare(Persona o1, Persona o2) {
        if (o2.getAltura() == o1.getAltura())
            return 0;
        if (o2.getAltura() < o1.getAltura())
            return -1;
        if (o2.getAltura() > o1.getAltura())
            return 1;
        return o1.getNombre().compareTo(o2.getNombre());
    }
}
