package practico5.gui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import practico5.modelo.Participante;
import practico5.modelo.Participantes;
import practico5.net.Enviar;
import practico5.net.Recibir;
import practico5.modelo.Tablero;
import practico5.modelo.Mensaje;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class NetFrameChat extends JFrame {
    private Logger logger = LogManager.getLogger();

    private JTextField txtMensaje;

    private Tablero tablero;
    private PanelTableroChat panelTableroChat;

    public static Participantes participantes;
    public static PanelTableroParticipantes panelTableroParticipantes;

    private Enviar conexion = NetFrameServidor.conexion;
    private Recibir recibir = NetFrameServidor.recibir;

    public NetFrameChat() {
        super("Cliente Chat");
        init();
    }

    private void init() {
        setSize(640, 400);

        tablero = new Tablero();
        panelTableroChat = new PanelTableroChat(tablero);
        tablero.addObserver(panelTableroChat);

        participantes = new Participantes();
        panelTableroParticipantes = new PanelTableroParticipantes(participantes);
        participantes.addObserver(panelTableroParticipantes);

        JMenuBar bar = new JMenuBar();
        JMenu mnu = new JMenu("Menu");

        //JMenuItem conexión = new JMenuItem("Esperar conexión");
        JMenuItem conectar = new JMenuItem("Conectar");
        JMenuItem salir = new JMenuItem("Salir");

        /*conexión.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                mnuArchivo_EsperarConexion();
            }

        });*/
        conectar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                mnuArchivo_Conectar();
            }

        });
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                mnuArchivo_Salir();
            }
        });

        //mnu.add(conexión);
        mnu.add(conectar);
        mnu.addSeparator();
        mnu.add(salir);
        bar.add(mnu);
        setJMenuBar(bar);

        JPanel pnlAbajo = new JPanel();
        pnlAbajo.setLayout(new BorderLayout());

        txtMensaje = new JTextField();
        pnlAbajo.add(txtMensaje, BorderLayout.CENTER);

        JButton btnMensaje = new JButton("Enviar");
        JButton btnSubir = new JButton(" ↑ ");

        btnMensaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                btnMensaje_Click();
            }
        });
        btnSubir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                btnSubir_Click();
            }
        });

        pnlAbajo.add(btnMensaje, BorderLayout.EAST);
        pnlAbajo.add(btnSubir, BorderLayout.WEST);

        JScrollPane scroller = new JScrollPane(panelTableroChat);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(scroller, BorderLayout.CENTER);
        this.getContentPane().add(pnlAbajo, BorderLayout.SOUTH);

        //this.pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /*private void mnuArchivo_EsperarConexion() {
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
            //panelTableroChat.setRecibo(recibir);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hubo un error de conexión, trate con otro puerto");
            return;
        }

        try {
            conexion = new Enviar(recibir.getClt());
            conexion.setTablero(tablero);
            //panelTableroChat.setEnviar(conexion);
        } catch (IOException e) {
            logger.error("Error de conexion con el otro programa", e);
            JOptionPane.showMessageDialog(this, "Hubo un error al crear el objeto para enviar");
            return;
        }

        Thread recibiendo = new Thread(recibir);
        recibiendo.start();
        anadirObservador();
    }*/

    private void mnuArchivo_Conectar() {

        String ipPuertoInput = JOptionPane.showInputDialog("Ingrese su nombre:ip:puerto");

        if (ipPuertoInput == null)
            return;

        String[] ipPuerto = ipPuertoInput.split(":");

        if (ipPuerto.length != 3) {
            JOptionPane.showMessageDialog(this, "Debe colocar nombre, ip y puerto separado por :");
            return;
        }

        String ip = leerIP(ipPuerto[1]);
        if (ip.equals("ERROR")) {
            JOptionPane.showMessageDialog(this, "La ip deben ser 4 numeros enteros menores a 255");
            return;
        }

        int puerto = leerPuerto(ipPuerto[2]);
        if (puerto == 0) {
            JOptionPane.showMessageDialog(this, "El puerto debe ser un entero mas de 1024");
            return;
        }

        try {
            conexion = new Enviar(ip, puerto);
            conexion.setTablero(tablero);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar al servidor " + ip + ":" + puerto);
            return;
        }

        try {
            recibir = new Recibir(conexion.getSck(), tablero);
        } catch (IOException e) {
            logger.error("Error de conexion con el otro programa", e);
            JOptionPane.showMessageDialog(this, "Hubo un error al crear el objeto para recibir");
            return;
        }

        Participante nuevo = new Participante(ipPuerto[0]);
        participantes.insertarParticipante(nuevo);
        logger.info("Se añadio cliente");

        Thread recibiendo = new Thread(recibir);
        recibiendo.start();
        anadirObservador();
    }

    private void btnSubir_Click() {

    }

    public void btnMensaje_Click() {
        String mensajeAEnviar = txtMensaje.getText();
        LocalTime horaActual = LocalTime.now();
        LocalDate fechaActual = LocalDate.now();
        if (mensajeAEnviar.isEmpty())
            return;
        mensajeAEnviar = mensajeAEnviar +" "+ horaActual.toString().substring(0,5) + " / " + fechaActual;
        Mensaje m = new Mensaje(mensajeAEnviar, false);

        if (mensajeAEnviar.contains("Mensaje:")) {
            logger.debug("TEXTO: " + mensajeAEnviar);
            tablero.insertarEnMensajes(m);
        }
        if (mensajeAEnviar.contains("Imagen:")) {
            logger.info("TEXTO: " + mensajeAEnviar);
            tablero.insertarEnMensajes(m);
        }
        txtMensaje.setText("");
    }

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
