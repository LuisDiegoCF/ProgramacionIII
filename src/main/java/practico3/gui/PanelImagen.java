package practico3.gui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import practico3.imagenes.Imagen;
import practico3.net.Enviar;
import practico3.net.Recibir;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PanelImagen extends JPanel implements PropertyChangeListener, MouseListener {

    private final static Logger logger = LogManager.getLogger();
    private Imagen imagen;
    private Enviar enviar;
    private Recibir recibo;
    private boolean repaint = true;
    int x;
    int y;

    public PanelImagen(Imagen img) {
        imagen = img;
        addMouseListener(this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            imagen.dibujar(g);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (repaint) {
            if (recibo != null) {
                if (recibo.isPerdedor()) {
                    JOptionPane.showMessageDialog(this, "Perdiste");
                    recibo.setPerdedor(false);
                    repaint = false;
                    System.exit(0);
                }
            }
            repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        if (enviar != null) {
            enviar.setPosX(x);
            enviar.setPosY(y);
            imagen.cambioOk();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setEnviar(Enviar enviar) {
        this.enviar = enviar;
    }

    public void setRecibo(Recibir recibo) {
        this.recibo = recibo;
    }
}
