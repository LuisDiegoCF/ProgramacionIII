package quicksortPersona.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import quicksortPersona.objects.ListaPersonas;


public class QuicksortPersonaPanel extends JPanel implements Observer {

    private final static Logger logger = LogManager.getLogger();
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ListaPersonas theList;

    /**
     * Create the panel.
     */
    public QuicksortPersonaPanel() {
        theList = null;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        if (arg0 instanceof ListaPersonas)
            theList = (ListaPersonas) arg0;

        this.repaint();
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);

        if (theList == null) {
            logger.warn("No list to draw");
            return;
        }

        theList.draw(gc);
    }

    public Dimension getPreferredSize() {
        return new Dimension(900, 200);
    }
}