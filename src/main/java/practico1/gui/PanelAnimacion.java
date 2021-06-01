package practico1.gui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import practico1.vista.IDibujo;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PanelAnimacion extends JPanel implements PropertyChangeListener {

    private IDibujo dibujo;

    private  final static Logger logger = LogManager.getLogger();

    public PanelAnimacion(IDibujo dibujo){
        this.dibujo = dibujo;

        logger.info("Se ha añadido el PanelAnimacion");
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(601, 416);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(dibujo != null) {
            dibujo.dibujar(g);
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
