package imagenes;

import imagenes.transformaciones.FranjaCafeClaro;
import imagenes.transformaciones.FranjaRoja;
import imagenes.transformaciones.PintarEnXY;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinImagen extends JFrame {

    private Imagen modelo;

    public WinImagen(){
        init();
    }

    private void init() {

        modelo = new Imagen(1000, 350);

        PanelImagen panel = new PanelImagen(modelo);

        modelo.addObserver(panel);

        this.getContentPane().setLayout(new BorderLayout());

        JScrollPane scroller = new JScrollPane(panel);
        //JEditorPane
        //java jscrollpane auto scroll down
        //java jscrollpane automatically scroll all the way down
        this.getContentPane().add(scroller, BorderLayout.CENTER);
        JMenuBar menuBar = new JMenuBar();

        JMenu mnuImagen = new JMenu("Imagen");

        JMenuItem item = new JMenuItem("Franja roja");

        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mnuImagen_franjaRoja();
            }
        });

        mnuImagen.add(item);

        item = new JMenuItem("Franja cafe claro");

        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mnuImagen_franjaCafeClaro();
            }
        });

        mnuImagen.add(item);

        item = new JMenuItem("Pinta en X Y");

        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mnuImagen_pintar100x100();
            }
        });

        mnuImagen.add(item);

        menuBar.add(mnuImagen);

        this.setJMenuBar(menuBar);
        this.pack();
    }

    private void mnuImagen_franjaCafeClaro() {
        FranjaCafeClaro f = new FranjaCafeClaro(modelo);
        f.hacer();
    }

    private void mnuImagen_franjaRoja() {
        FranjaRoja f = new FranjaRoja(modelo);
        f.hacer();
    }

    private void mnuImagen_pintar100x100() {
        PintarEnXY fue = new PintarEnXY(modelo, 100,100);
        fue.hacer();
    }



}
