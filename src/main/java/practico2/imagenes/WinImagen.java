package practico2.imagenes;

import practico2.transformaciones.Imagen1;
import practico2.transformaciones.Imagen2;
import practico2.transformaciones.ImagenFinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinImagen extends JFrame {

    private Imagen modelo;
    private Imagen1 imagen1;
    private Imagen2 imagen2;
    private PanelImagen panelImagen;

    public WinImagen() {
        super("Merge de Imagenes");
        //setTitle("Mezclador");
        init();
    }

    private void init() {

        modelo = new Imagen(600, 400);

        panelImagen = new PanelImagen(modelo);

        modelo.addObserver(panelImagen);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panelImagen, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();

        JMenu mnuImagen = new JMenu("Opciones");

        JMenuItem item;

        item = new JMenuItem("Cargar imagen 1");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imagen1();
            }
        });
        mnuImagen.add(item);

        item = new JMenuItem("Cargar imagen 2");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imagen2();
            }
        });
        mnuImagen.add(item);

        item = new JMenuItem("imagen resultante");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imagenResultante();
            }
        });
        mnuImagen.add(item);

        item = new JMenuItem("About");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                informacion();
            }
        });
        mnuImagen.add(item);

        item = new JMenuItem("Salir");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnuImagen.add(item);

        menuBar.add(mnuImagen);

        this.setJMenuBar(menuBar);
        this.pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void imagenResultante() {
        ImagenFinal f = new ImagenFinal(modelo);
        if(imagen1 != null && imagen2 != null) {
            f.setBufferedimagen1(imagen1.getBufferedImage());
            f.setBufferedimagen2(imagen2.getBufferedImage());
        }
        f.hacer();
    }

    private void imagen1() {
        imagen1 = new Imagen1(modelo);
        imagen1.hacer();
    }

    private void imagen2() {
        imagen2 = new Imagen2(modelo);
        imagen2.hacer();
    }

    private void informacion() {
        this.remove(panelImagen);
        PanelAbout panelAbout = new PanelAbout();
        add(panelAbout, BorderLayout.CENTER);
        JButton volver = new JButton("Volver");
        add(volver, BorderLayout.NORTH);
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panelAbout);
                remove(volver);
                init();
                validate();
            }
        });
        this.validate();
    }


}
