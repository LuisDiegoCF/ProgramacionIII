package practico3.net;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Enviar implements PropertyChangeListener {

    private static final Logger logger = LogManager.getLogger();

    private Socket sck;
    private PrintWriter out;

    private int posX;
    private int posY;
    private boolean x;
    private boolean y;

    public Enviar(String ip, int puerto) throws IOException {
        try {
            sck = new Socket(ip, puerto);
        } catch (IOException e) {
            logger.error("Error no se pudo crear la conexion", e);
            throw e;
        }

        logger.info("Se conectó a " + ip + ":" + puerto);

        try {
            out = new PrintWriter(new java.io.OutputStreamWriter(sck.getOutputStream()));
        } catch (IOException e) {
            logger.error("Error al crear el writer para el socket");
            throw e;
        }
    }

    public Enviar(Socket sck) throws IOException {
        this.sck = sck;
        try {
            out = new PrintWriter(new java.io.OutputStreamWriter(sck.getOutputStream()));
        } catch (IOException e) {
            logger.error("Error al crear el writer para el socket");
            throw e;
        }

        logger.info("Se creo el objeto para enviar a partir del socket");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (x && y) {
            out.println(posX + "," + posY);
            out.flush();
            x = false;
            y = false;
        }
    }

    public void cerrarConexion() {
        if (sck != null) {
            try {
                sck.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Socket getSck() {
        return sck;
    }

    public void setSck(Socket sck) {
        this.sck = sck;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public void setPosX(int posX) {
        this.posX = posX;
        x = true;
    }

    public void setPosY(int posY) {
        this.posY = posY;
        y = true;
    }


}
