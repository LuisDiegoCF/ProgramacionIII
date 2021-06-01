package recurrencia;

public class AlgoritmoPeterson {

    public static void main(String[] args) {
        boolean bandera[] = new boolean[2];

        bandera[0] = false;
        bandera[1] = false;
        int turno = 0;
        proceso0(bandera, turno);
        proceso1(bandera, turno);
    }

    public static void proceso0(boolean bandera[], int turno){

        //proceso 0
        bandera[0] = true;
        turno = 1;
        while (bandera[1] && turno == 1){
            // Proceso 0 espera
        }
        seccionCritica();
        bandera[0] = false;

    }

    public static void proceso1(boolean bandera[], int turno){

        //proceso 1
        bandera[1] = true;
        turno = 0;
        while (bandera[0] && turno == 0){
            //Proceso 1 espera
        }
        seccionCritica();
        bandera[1] = false;

    }
    public static void seccionCritica(){
        System.out.println("iniciando");
    }
}
