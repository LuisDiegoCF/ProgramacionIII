package practico1.gui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import practico1.modelo.Linea;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class PanelDatos extends JPanel implements PropertyChangeListener {

    private ArrayList<Linea> lineas;
    private JLabel posicionLabel;

    private int mayor;
    private int menor;

    private final static Logger logger = LogManager.getLogger();

    public PanelDatos(ArrayList<Linea> lineas) {
        this.lineas = lineas;

        init();
    }

    private void init() {
        posicionLabel = new JLabel();
        this.setLayout(new BorderLayout());
        this.add(posicionLabel, BorderLayout.CENTER);
        logger.info("Se ha añadido el PanelDatos: Mayor, Menor");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        numMayorMenor();
    }

    public void numMayorMenor() {
        for (int i = 1; i < lineas.size(); i++){
            mayor = lineas.get(0).getAlto();
            menor = lineas.get(0).getAlto();
            if(menor > lineas.get(i).getAlto()){
                menor = lineas.get(i).getAlto();
            }
            if(mayor < lineas.get(i).getAlto()){
                mayor = lineas.get(i).getAlto();
            }
            posicionLabel.setText("Mayor: " + mayor + " Menor: " + menor);
        }
    }

    public int getMayor() {
        return mayor;
    }

    public void setMayor(int mayor) {
        this.mayor = mayor;
    }

    public int getMenor() {
        return menor;
    }

    public void setMenor(int menor) {
        this.menor = menor;
    }
}
