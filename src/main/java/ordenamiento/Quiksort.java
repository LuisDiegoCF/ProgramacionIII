package ordenamiento;

/**
 * Complejidad de quicksort
 * O(n) = n * log_2(n) (en base 2 de log)
 */
public class Quiksort {

    private int cantidadOperaciones;

    public Quiksort(){
        cantidadOperaciones = 0;
    }

    public void quickSort(int arr[], int begin, int end){
        if(begin < end){
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }

    private int partition(int arr[], int begin, int end){
        int pivot = arr[end];
        int i = (begin-1);

        for (int j = begin; j < end; j++){
            if(arr[j] <= pivot){
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;

                cantidadOperaciones++;
            }
        }
        int swapTemp = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = swapTemp;

        cantidadOperaciones++;

        return i+1;
    }

    public int getCantidadOperaciones(){
        return cantidadOperaciones;
    }
}
