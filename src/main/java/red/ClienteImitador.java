package red;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteImitador {
    public static void main(String[] args) throws IOException {

        Socket cltSocket = new Socket("127.0.0.1", ServidorImitador.PUERTO);

        PrintWriter out = new PrintWriter(cltSocket.getOutputStream());
        // para poder leer y escribir a partir de mi socket
        out.flush();

        BufferedReader inReader = new BufferedReader(new InputStreamReader(cltSocket.getInputStream()));

        BufferedReader entradaReader = new BufferedReader(new InputStreamReader(System.in));

        String entrada = entradaReader.readLine();

        while(!entrada.equals("FIN")){
            //Envia
            out.println(entrada);
            out.flush();

            //Recibe
            String respuesta = inReader.readLine();
            System.out.println("Nos respondio: " + respuesta);

            entrada = entradaReader.readLine();
        }
        inReader.close();
        out.close();
        cltSocket.close();
    }
}
