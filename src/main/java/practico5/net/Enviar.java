package practico5.net;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import practico5.gui.PanelTableroParticipantes;
import practico5.modelo.Mensaje;
import practico5.modelo.Tablero;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Enviar implements PropertyChangeListener {

    private static final Logger logger = LogManager.getLogger();

    private Tablero tablero;
    private PanelTableroParticipantes panelTableroParticipantes;
    private Socket sck;
    private PrintWriter out;

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
        if (tablero != null) {
            Mensaje ultimo = tablero.getUltimoMensajeMio();
            if (ultimo == null)
                return;
            if (ultimo.isEnviado())
                return;
            out.println(ultimo.toString());
            out.flush();
            ultimo.setEnviado(true);
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

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public void setPanelTableroParticipantes(PanelTableroParticipantes panelTableroParticipantes) {
        this.panelTableroParticipantes = panelTableroParticipantes;
    }
}
