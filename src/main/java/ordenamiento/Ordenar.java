package ordenamiento;

/**
 * Complejidad de ordenar Simple es n^2
 * O(n)n
 */
public class Ordenar {
    public static void main(String[] args) {
        int[] a = new int[100];
        llenarArreglo(a);

        imprimir(a);
        Quiksort qs = new Quiksort();
        qs.quickSort(a, 0, 99);

        imprimir(a);

        System.out.println(qs.getCantidadOperaciones());
    }

    private static void llenarArreglo(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = 1 + (int) (Math.random() * 10000.0);
        }
    }

    private static void imprimir(int[] a) {
        for (int i : a) {
            System.out.print(String.valueOf(i) + " ");
        }
        System.out.println();
    }
}
