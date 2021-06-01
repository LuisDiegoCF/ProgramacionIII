package practico3.gui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import practico3.imagenes.Imagen;
import practico3.imagenes.ImagenModelo;
import practico3.net.Enviar;
import practico3.net.Recibir;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ImagenNetFrame extends JFrame {

    private final static Logger logger = LogManager.getLogger();
    private Imagen modelo;
    private Enviar conexion;
    private Recibir recibir;
    private PanelImagen panelImagen;


    public ImagenNetFrame() {
        super("Fill Attack");
        init();
    }

    private void init() {

        modelo = new Imagen(600, 400);
        panelImagen = new PanelImagen(modelo);
        modelo.addObserver(panelImagen);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panelImagen, BorderLayout.CENTER);


        JMenuBar menuBar = new JMenuBar();
        JMenu mnuImagen = new JMenu("Menu");

        JMenuItem item1 = new JMenuItem("Esperar Conexion");
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                esperarConexion();
            }
        });
        item1.setEnabled(false);


        JMenuItem item2 = new JMenuItem("Conectar");
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conectar();
            }
        });
        item2.setEnabled(false);


        JMenuItem item3 = new JMenuItem("Cargar Imagen");
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarImagen();
                item1.setEnabled(true);
                item2.setEnabled(true);
            }
        });


        JMenuItem item4 = new JMenuItem("Salir");
        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });


        mnuImagen.add(item1);
        mnuImagen.add(item2);
        mnuImagen.add(item3);
        mnuImagen.add(item4);

        menuBar.add(mnuImagen);

        this.setJMenuBar(menuBar);
        this.pack();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void cargarImagen() {
        ImagenModelo imagen = new ImagenModelo(modelo);
        imagen.hacer();
    }
    private void conectar() {
        String ipPuertoInput = JOptionPane.showInputDialog("Ingrese ip:puerto");

        if (ipPuertoInput == null)
            return;

        String[] ipPuerto = ipPuertoInput.split(":");
        if (ipPuerto.length != 2) {
            JOptionPane.showMessageDialog(this, "Debe colocar ip y puerto separado por :");
            return;
        }

        String ip = leerIP(ipPuerto[0]);
        if (ip.equals("ERROR")) {
            JOptionPane.showMessageDialog(this, "La ip deben ser 4 numeros enteros menores a 255");
            return;
        }

        int puerto = leerPuerto(ipPuerto[1]);
        if (puerto == 0) {
            JOptionPane.showMessageDialog(this, "El puerto debe ser un entero mas de 1024");
            return;
        }

        try {
            conexion = new Enviar(ip, puerto);
            panelImagen.setEnviar(conexion);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar al servidor " + ip + ":" + puerto);
            return;
        }

        try {
            recibir = new Recibir(conexion.getSck(), modelo);
            panelImagen.setRecibo(recibir);
        } catch (IOException e) {
            logger.error("Error de conexion con el otro programa", e);
            JOptionPane.showMessageDialog(this, "Hubo un error al crear el objeto para recibir");
            return;
        }

        Thread recibiendo = new Thread(recibir);
        recibiendo.start();
        añadirObservador();
    }

    private void esperarConexion() {
        String puertoo = JOptionPane.showInputDialog("Colocar en qué puerto escuchará:");

        if (puertoo == null)
            return;

        int puerto = leerPuerto(puertoo);
        if (puerto == 0) {
            JOptionPane.showMessageDialog(this, "El puerto debe ser un numero mayor a 1024");
            return;
        }

        try {
            recibir = new Recibir(puerto, modelo);
            panelImagen.setRecibo(recibir);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hubo un error de conexión, trate con otro puerto");
            return;
        }

        try {
            conexion = new Enviar(recibir.getClt());
            panelImagen.setEnviar(conexion);
        } catch (IOException e) {
            logger.error("Error de conexion con el otro programa", e);
            JOptionPane.showMessageDialog(this, "Hubo un error al crear el objeto para enviar");
            return;
        }

        Thread recibiendo = new Thread(recibir);
        recibiendo.start();
        añadirObservador();
    }

    private String leerIP(String ip) {
        String[] numeros = ip.split("\\.");
        StringBuilder respuesta = new StringBuilder();

        if (numeros.length != 4)
            return "ERROR";

        for (String unNumero : numeros) {
            int n = -1;
            try {
                n = Integer.parseInt(unNumero);

                if (n < 0 || n > 255)
                    return "ERROR";
            } catch (Exception e) {
                return "ERROR";
            }

            respuesta.append("." + n);
        }
        return respuesta.substring(1);
    }

    private int leerPuerto(String puerto) {
        int n = -1;
        try {
            n = Integer.parseInt(puerto);

            if (n < 1024 || n > 65000)
                return 0;
        } catch (Exception e) {
            logger.error("Ingrese solo numeros: " + puerto, e);
            return 0;
        }
        return n;
    }

    private void salir() {
        if (conexion != null) {
            conexion.cerrarConexion();
        }
        System.exit(0);
    }

    private void añadirObservador() {
        modelo.addObserver(conexion);
    }
}
