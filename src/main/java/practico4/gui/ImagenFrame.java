package practico4.gui;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import practico4.Lista.ListaOrdenada;
import practico4.objetos.Persona;
import practico4.vista.DibujoPersona;
import practico4.vista.IDibujo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImagenFrame extends JFrame {

    private final static Logger logger = LogManager.getLogger();
    private ListaOrdenada<Persona> personas;
    private IDibujo dibujos;
    private PanelDibujoListaPersonas panelDibujoListaPersonas;

    public ImagenFrame() {
        super("Lista Ordenada");
        personas = new ListaOrdenada<Persona>();
        init();
    }

    private void init() {
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        JMenuItem item = new JMenuItem("Nueva Persona");
        JMenuItem item2 = new JMenuItem("Eliminar todo");
        JMenuItem item3 = new JMenuItem("Salir");

        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevaPersona();
            }
        });

        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaciarLista();
            }
        });

        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });

        menu.add(item);
        menu.add(item2);
        menu.add(item3);
        bar.add(menu);
        this.setJMenuBar(bar);

        personas.addObserver(panelDibujoListaPersonas);
        dibujos = new DibujoPersona(personas);
        panelDibujoListaPersonas = new PanelDibujoListaPersonas(dibujos);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panelDibujoListaPersonas, BorderLayout.CENTER);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void salir() {
        System.exit(EXIT_ON_CLOSE);
        logger.info("Salir");
    }

    private void vaciarLista() {
        personas = new ListaOrdenada<>();
        personas.addObserver(panelDibujoListaPersonas);
        dibujos = new DibujoPersona(personas);
        panelDibujoListaPersonas.setDibujo(dibujos);
        personas.cambioOk();
        logger.info("Lista Vacia");
    }

    private void nuevaPersona() {
        JTextField nombreField = new JTextField();
        JTextField edadField = new JTextField();
        JTextField alturaField = new JTextField();
        JTextField generoField = new JTextField();

        Object[] message = {
                "Nombre: ", nombreField,
                "Edad: ", edadField,
                "Altura: ", alturaField,
                "Genero: ", generoField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Nueva persona", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {

            if (validarCampos(edadField.getText(), alturaField.getText(), generoField.getText()) == 1) {
                int edad = Integer.parseInt(edadField.getText());
                int altura = Integer.parseInt(alturaField.getText());
                String mensaje = "";
                if (edad > 100 || altura > 250) {
                    mensaje += edad < 101 ? "" : "La edad debe ser menor a 100 años.\n";
                    mensaje += altura < 250 ? "" : "La altura debe ser menor a 250 cm.";
                    JOptionPane.showMessageDialog(panelDibujoListaPersonas, mensaje);
                    logger.info(mensaje);
                    return;
                }

                Persona p = new Persona(nombreField.getText(), generoField.getText(), edad, altura);
                personas.insertar(p);
                p.addObserver(panelDibujoListaPersonas);
                p.cambioOk();
                logger.debug("Se añadio a : " + "Nombre: " + nombreField.getText() + " Edad: " + edad + " Altura: " + altura + " Genero: " + generoField.getText());
            }
        }
    }

    public int validarCampos(String e, String a, String genero) {
        String mensaje = "";
        int validado = 1;
        if (!e.matches("[0-9]{1,3}")) {
            mensaje += "El campo de texto edad solo permite numeros positivos\n";
            logger.info("El campo de texto edad solo permite numeros positivos");
            validado = -1;
        }

        if (!a.matches("[0-9]{1,3}")) {
            mensaje += "El campo de texto altura solo permite numeros positivos\n";
            logger.info("El campo de texto altura solo permite numeros positivos");
            validado = -1;
        }

        if (!genero.toLowerCase().matches("masculino") && !genero.toLowerCase().matches("femenino")) {
            mensaje += "El campo de texto genero solo adminite Masculino o Femenino\n";
            logger.info("El campo de texto genero solo adminite Masculino o Femenino");
            validado = -1;
        }

        if (validado == -1) {
            JOptionPane.showMessageDialog(panelDibujoListaPersonas, mensaje);
        }
        return validado;
    }
}
