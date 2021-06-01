package practico5.gui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import practico5.modelo.Mensaje;
import practico5.modelo.Participante;
import practico5.modelo.Participantes;
import practico5.modelo.Tablero;
import practico5.net.Enviar;
import practico5.net.Recibir;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class NetFrameServidor extends JFrame {
    private Logger logger = LogManager.getLogger();

    private Tablero tablero;
    private PanelTableroChat panelTableroChat;

    public static Participantes participantes;;
    public static PanelTableroParticipantes panelTableroParticipantes;

    public static Enviar conexion;
    public static Recibir recibir;

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public NetFrameServidor() {
        super("Servidor Chat");
        init();
    }

    private void init() {
        setSize(800, 400);

        tablero = new Tablero();//////
        panelTableroChat = new PanelTableroChat(tablero); //////
        tablero.addObserver(panelTableroChat); //////

        participantes = new Participantes();
        panelTableroParticipantes = new PanelTableroParticipantes(participantes);
        participantes.addObserver(panelTableroParticipantes);

        JMenuBar bar = new JMenuBar();
        JMenu mnu = new JMenu("Archivo");

        JMenuItem conexión = new JMenuItem("Esperar conexión");
        //JMenuItem conectar = new JMenuItem("Conectar");
        JMenuItem salir = new JMenuItem("Salir");

        conexión.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                mnuArchivo_EsperarConexion();
            }

        });
        /*conectar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                mnuArchivo_Conectar();
            }

        });*/
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                mnuArchivo_Salir();
            }
        });

        mnu.add(conexión);
        //mnu.add(conectar);
        mnu.addSeparator();
        mnu.add(salir);
        bar.add(mnu);
        setJMenuBar(bar);


        JScrollPane scroller = new JScrollPane(panelTableroChat);
        JScrollPane scroller2 = new JScrollPane(panelTableroParticipantes);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(scroller, BorderLayout.CENTER);
        this.getContentPane().add(scroller2, BorderLayout.EAST);

        //this.pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public void mnuArchivo_EsperarConexion() {
        logger.info("Colocar en qué puerto escuchará:");
        String puertoo = JOptionPane.showInputDialog("Colocar en qué puerto escuchará:");
        if (puertoo == null)
            return;

        int puerto = leerPuerto(puertoo);
        if (puerto == 0) {
            JOptionPane.showMessageDialog(this, "El puerto debe ser un numero mayor a 1024");
            return;
        }

        try {
            recibir = new Recibir(puerto, tablero);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hubo un error de conexión, trate con otro puerto");
            return;
        }

        try {
            conexion = new Enviar(recibir.getClt());
            conexion.setTablero(tablero);
        } catch (IOException e) {
            logger.error("Error de conexion con el otro programa", e);
            JOptionPane.showMessageDialog(this, "Hubo un error al crear el objeto para enviar");
            return;
        }

        Thread recibiendo = new Thread(recibir);
        recibiendo.start();
        anadirObservador();
    }



    /*private void mnuArchivo_Conectar() {
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
            conexion.setTablero(tablero);
            //panelTableroChat.setEnviar(conexion);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar al servidor " + ip + ":" + puerto);
            return;
        }

        try {
            recibir = new Recibir(conexion.getSck(), tablero);
            //panelTableroChat.setRecibo(recibir);
        } catch (IOException e) {
            logger.error("Error de conexion con el otro programa", e);
            JOptionPane.showMessageDialog(this, "Hubo un error al crear el objeto para recibir");
            return;
        }

        Thread recibiendo = new Thread(recibir);
        recibiendo.start();
        anadirObservador();
    }*/

    private void anadirObservador() {
        tablero.addObserver(conexion);
    }

    protected void mnuArchivo_Salir() {
        if (conexion != null) {
            conexion.cerrarConexion();
        }
        System.exit(0);
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

    public Enviar getConexion() {
        return conexion;
    }

    public void setConexion(Enviar conexion) {
        this.conexion = conexion;
    }

    public Recibir getRecibir() {
        return recibir;
    }

    public void setRecibir(Recibir recibir) {
        this.recibir = recibir;
    }
}

