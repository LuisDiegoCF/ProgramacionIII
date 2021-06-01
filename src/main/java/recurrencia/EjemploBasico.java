package recurrencia;

public class EjemploBasico {

    public void m(){
        m();
    }

    public void imprimirNVeces(int n){
        System.out.println("Imprimir");
        if(n > 0)
            imprimirNVeces(n - 1);
    }

    public int suma(int n){
        if(n == 1)
            return n;
        System.out.println(n);
        System.out.println("---");
        return (n + suma(n - 1));

        // return (n == 1) ? 1 : n + suma(n - 1));
    }
}
