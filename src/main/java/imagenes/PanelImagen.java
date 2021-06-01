package imagenes;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PanelImagen extends JPanel implements PropertyChangeListener {

    private Imagen imagen;
    public PanelImagen(Imagen img){
        imagen = img;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(imagen != null){
            imagen.dibujar(g);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
