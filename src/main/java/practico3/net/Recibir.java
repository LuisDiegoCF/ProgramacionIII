package practico3.net;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import practico3.imagenes.Imagen;
import practico3.imagenes.PintarEnXY;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Recibir implements Runnable {


    private static final Logger logger = LogManager.getLogger();

    private Imagen imagen;
    private Socket clt;
    private BufferedReader in;
    private boolean perdedor;

    public Recibir(int port, Imagen imagen) throws IOException {
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
        this.imagen = imagen;
    }

    public Recibir(Socket sck, Imagen imagen) throws IOException {
        clt = sck;
        in = new BufferedReader(new InputStreamReader(clt.getInputStream()));
        this.imagen = imagen;
    }

    @Override
    public void run() {
        try {
            while (!clt.isClosed()) {
                String coordenadas = in.readLine();
                String[] posiciones = coordenadas.split(",");
                int x = Integer.parseInt(posiciones[0]);
                int y = Integer.parseInt(posiciones[1]);
                logger.info("Coordenadas en: X = " + x + " Y = " + y);
                PintarEnXY pinta = new PintarEnXY(imagen, x, y);
                pinta.hacer();
                derrota();
            }
        } catch (IOException e) {
            logger.error("Error", e);
        }
    }

    public void derrota(){
        int derrota = (240000 * 51) / 100;
        int contador = 0;
        for (int i = 0; i < imagen.getAncho(); i++){
            for (int j = 0; j < imagen.getAlto(); j++){
                int pixel = imagen.getPixel(i, j);
                if(pixel == 0X00ffffff){
                    contador++;
                    if(contador == derrota){
                        perdedor = true;
                    }
                }
            }
        }
    }

    public boolean isPerdedor() {
        return perdedor;
    }

    public void setPerdedor(boolean perdedor) {
        this.perdedor = perdedor;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
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
