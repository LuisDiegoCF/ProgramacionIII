package red;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorImitador {

    public static final int PUERTO = 6850;

    public static void main(String[] args) throws IOException {
        ServerSocket srv = new ServerSocket(PUERTO);
        //Espera una conexion
        Socket cltSocket = srv.accept();
        // acepta la conexion
        // y obtiene un socket de cliente
        PrintWriter out = new PrintWriter(cltSocket.getOutputStream());
        // para poder leer y escribir a partir de mi socket
        out.flush();
        //envio todo al momento

        BufferedReader inReader = new BufferedReader(new InputStreamReader(cltSocket.getInputStream()));
        // sirve para leer algo que escribamos que esta en el socket

        while(!cltSocket.isClosed()){
            //Recibe
            String solicitud = inReader.readLine();
            // inReader.readLine(); leemos el texto que nos mando el cliente
            if(solicitud == null)
                break;
            System.out.println("Recibe de cliente: " + solicitud);
            //Envia
            out.println("[Respondido: " + solicitud + "]");
            out.flush();
        }
        inReader.close();
        out.close();
        cltSocket.close();
        srv.close();
    }
}
