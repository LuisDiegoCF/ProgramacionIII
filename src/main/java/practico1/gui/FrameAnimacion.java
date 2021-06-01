package practico1.gui;

import practico1.modelo.Linea;
import practico1.vista.DibujoLinea;
import practico1.vista.IDibujo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrameAnimacion extends JFrame {

    private ArrayList<Linea> lineas;

    private PanelAnimacion panelAnimacion;
    private PanelDatos panelDatos;

    public FrameAnimacion() {
        lineas = new ArrayList<>();
        init();
    }

    private void init() {
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Archivo");

        JMenuItem item = new JMenuItem("Nuevo Conjunto");
        JMenuItem item2 = new JMenuItem("Menor a Mayor");
        JMenuItem item3 = new JMenuItem("Mayor a Menor");

        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarLineas();
            }
        });
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        menorMayor(lineas);
                    }
                });
                thread.start();
            }
        });
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mayorMenor(lineas);
                    }
                });
                thread.start();
            }
        });
        menu.add(item);
        menu.add(item2);
        menu.add(item3);
        bar.add(menu);
        this.setJMenuBar(bar);


        IDibujo dibujo = new DibujoLinea(lineas);
        panelAnimacion = new PanelAnimacion(dibujo);
        panelDatos = new PanelDatos(lineas);

        cargarLineas();
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panelAnimacion, BorderLayout.CENTER);
        this.getContentPane().add(panelDatos, BorderLayout.SOUTH);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void mayorMenor(ArrayList<Linea> lineas) {
        for (int i = 0; i < lineas.size(); i++) {

            for (int j = i + 1; j < lineas.size(); j++) {
                if (lineas.get(i).getAlto() < lineas.get(j).getAlto()) {
                    int aux = lineas.get(i).getAlto();
                    lineas.get(i).setAlto(lineas.get(j).getAlto());
                    lineas.get(j).setAlto(aux);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        this.lineas = lineas;
    }


    private void menorMayor(ArrayList<Linea> lineas) {
        for (int i = 0; i < lineas.size(); i++) {

            lineas.get(i).addObserver(panelDatos);

            for (int j = i + 1; j < lineas.size(); j++) {

                if (lineas.get(i).getAlto() > lineas.get(j).getAlto()) {
                    int aux = lineas.get(i).getAlto();
                    lineas.get(i).setAlto(lineas.get(j).getAlto());
                    lineas.get(j).setAlto(aux);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        this.lineas = lineas;
    }

    private void cargarLineas() {
        lineas.clear();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    Linea linea = new Linea();
                    linea.addObserver(panelAnimacion);
                    linea.setX((i + 1) * 2);
                    lineas.add(linea);
                    linea.setNum(i + 1);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
}
