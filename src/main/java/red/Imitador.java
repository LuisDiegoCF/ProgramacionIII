package red;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Imitador {

    public static int PUERTO = 6849;
    public static void main(String[] args) throws IOException {

        ServerSocket srv = new ServerSocket(PUERTO);
        // ServerSocket es algo que espera una conexion

        Socket cltSocket = srv.accept();
        // srv.accept(); Voy acepta una conexion, al aceptar la conexion
        // obtengo un socket de cliente

        // Socket cltSocket - cuando alguien se conecta obtengo
        // a un socket de cliente (es una linea de conexion con esa persona)


        PrintWriter out = new PrintWriter(cltSocket.getOutputStream());
        // Quiero poder escribir y leer texto
        // PrintWriter escribir a partir de mi clSocket
        // lo bueno de tener PrintWriter es que al tiro puedo enviar texto
        // y devolver por ese canal de comunicacion

        out.println("Peji");
        out.flush(); // envia lo que esta en el tubo de inmediato
        //BufferedReader para leer
        BufferedReader inReader = new BufferedReader(new InputStreamReader(cltSocket.getInputStream()));
        // BufferedReader: este sirve para leer va leer algo que escribamos
        // y un enter(hasta el enter lo va leer)
        String linea = inReader.readLine();

        while(!linea.equals("FIN")){
            out.println("Imitando: " + linea);
            out.flush();
            linea = inReader.readLine();
            // inReader.readLine(); pide que ingrese texto
        }
        // inReader.close(); cierra el lector
        inReader.close();
        out.close(); // cierra el tubo
        cltSocket.close(); // cierra el canal
        srv.close(); // cierra la conexion
    }
}
