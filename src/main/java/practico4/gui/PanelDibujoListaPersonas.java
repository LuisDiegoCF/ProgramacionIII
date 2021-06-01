package practico4.gui;

//import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;
import practico4.vista.IDibujo;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PanelDibujoListaPersonas extends JPanel implements PropertyChangeListener {

    private IDibujo dibujo;
    //private Image imagen;


    public PanelDibujoListaPersonas(IDibujo dibujo){
        this.dibujo = dibujo;
        //imagen = new ImageIcon("C:\\Users\\usuario}\\IdeaProjects\\Programacion III proyectos\\src\\main\\java\\practico4\\playa.jpg").getImage();
        setBackground(Color.BLACK);
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(800, 400);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawImage(imagen, 0, 0, this.getWidth(), this.getHeight(), this);
        if(dibujo != null){
            dibujo.dibujar(g);
        }
    }

    public void setDibujo(IDibujo dibujo) {
        this.dibujo = dibujo;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
