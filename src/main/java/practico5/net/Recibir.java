package practico5.net;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import practico3.imagenes.Imagen;
import practico3.imagenes.PintarEnXY;
import practico5.modelo.Tablero;
import practico5.modelo.Mensaje;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;

public class Recibir implements Runnable {


    private static final Logger logger = LogManager.getLogger();

    private Tablero tablero;
    private Socket clt;
    private BufferedReader in;

    public Recibir(int port, Tablero tablero) throws IOException {
        ServerSocket srv = null;
        clt = null;
        try {
            srv = new ServerSocket(port);
        } catch (IOException e) {
            logger.error("Error no se pudo crear la conexion", e);
            throw e;
        }

        logger.info("Esperando conexion puerto: " + port);

        try {
            clt = srv.accept();
        } catch (Exception e) {
            logger.error("Error en la espera de conexión", e);
        } finally {
            try {
                srv.close();
            } catch (IOException e) {
                logger.error("Error al cerrar el servidor");
            }
        }

        logger.info("Conexion a servidor exitosa");

        in = new BufferedReader(new InputStreamReader(clt.getInputStream()));
        this.tablero = tablero;
    }

    public Recibir(Socket sck, Tablero imagen) throws IOException {
        clt = sck;
        in = new BufferedReader(new InputStreamReader(clt.getInputStream()));
        this.tablero = imagen;
    }

    @Override
    public void run() {
        try {
            while (!clt.isClosed()) {
                String nuevoMensaje = in.readLine();
                LocalTime horaActual = LocalTime.now();
                LocalDate fechaActual = LocalDate.now();
                nuevoMensaje = nuevoMensaje +" "+ horaActual.toString().substring(0,5) + " / " + fechaActual;
                if (nuevoMensaje.contains("Mensaje:")) {
                    logger.info("MENSAJE " + nuevoMensaje);
                    Mensaje t = new Mensaje(nuevoMensaje, true);
                    tablero.insertarEnMensajes(t);
                }
                if (nuevoMensaje.contains("Imagen:")) {
                    logger.info("IMAGEN " + nuevoMensaje);
                    Mensaje t = new Mensaje(nuevoMensaje, true);
                    tablero.insertarEnMensajes(t);
                }
            }
        } catch (IOException e) {
            logger.error("Error", e);
        }
    }

    public Socket getClt() {
        return clt;
    }

    public void setClt(Socket clt) {
        this.clt = clt;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }
}

