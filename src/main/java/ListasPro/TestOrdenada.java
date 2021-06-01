package ListasPro;

import ListasPro.oficial.ListaOrdenada;
import ListasPro.oficial.Perro;

public class TestOrdenada {
    public static void main(String[] args) {
        ListaOrdenada<Perro> gatoListaOrdenada = new ListaOrdenada<>();
        ListaOrdenada<String> a = new ListaOrdenada<>();
        a.insertar("zoto");
        a.insertar("hugo");
        a.insertar("paco");
        a.insertar("luis");
        a.insertar("maria");
        System.out.println(a);

        ListaOrdenada<Perro> b = new ListaOrdenada<>();
        b.insertar(new Perro(4, "hugo"));
        b.insertar(new Perro(8, "paco"));
        b.insertar(new Perro(10, "luis"));
        b.insertar(new Perro(8, "luis"));
        b.insertar(new Perro(13, "pablo"));
        System.out.println(b);
    }
}
