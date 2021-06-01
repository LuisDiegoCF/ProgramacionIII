package practico5.gui;

import practico5.net.Enviar;
import practico5.net.Recibir;
import practico5.modelo.Tablero;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PanelTableroChat extends JPanel implements PropertyChangeListener {

    private Tablero tablero;
    private int ancho = 600;
    private int alto = 2000;
    private Image imagen;


    public PanelTableroChat(Tablero tablero){
        imagen = new ImageIcon("C:\\Users\\usuario}\\IdeaProjects\\ProgramacionIII\\src\\main\\java\\practico5\\fondo\\fondo.jpg").getImage();
        this.tablero = tablero;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(ancho, alto);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagen, 0, 0, this.getWidth(), this.getHeight(), this);

        if(tablero != null){
            tablero.dibujar(g);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
