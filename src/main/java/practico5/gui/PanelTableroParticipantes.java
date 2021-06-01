package practico5.gui;

import practico5.modelo.Participantes;
import practico5.modelo.Tablero;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PanelTableroParticipantes extends JPanel implements PropertyChangeListener {

    private Participantes participantes;
    private int ancho = 200;
    private int alto = 2000;
    private Image imagen;


    public PanelTableroParticipantes(Participantes participantes){
        imagen = new ImageIcon("C:\\Users\\usuario}\\IdeaProjects\\ProgramacionIII\\src\\main\\java\\practico5\\fondo\\fondo.jpg").getImage();
        this.participantes = participantes;
        this.participantes.cambioOk();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(ancho, alto);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagen, 0, 0, this.getWidth(), this.getHeight(), this);

        if(participantes != null){
            participantes.dibujar(g);
            repaint();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
