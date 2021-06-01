package practico4.vista;

import practico4.Lista.ListaOrdenada;
import practico4.objetos.Persona;

import java.awt.*;

public class DibujoPersona implements IDibujo {

    private ListaOrdenada<Persona> personas;
    private int puntoX = 30;
    private int puntoY = 400;

    public DibujoPersona(ListaOrdenada<Persona> personas) {
        this.personas = personas;
    }

    public void dibujar(Graphics g) {
        puntoX = 30;
        if (personas != null) {
            for (Persona persona : personas) {
                int anchoBrazo;
                int anchoCabeza;
                int largoDePiernas = (int) ((persona.getAltura() * 40) / 100);
                int largoDeTorso = (int) ((persona.getAltura() * 35) / 100);
                int largoDeCabeza = (int) (persona.getAltura() * 25) / 100;
                anchoBrazo = largoDeCabeza;
                anchoCabeza = largoDeCabeza;

                if (persona.getEdad() > 0 && persona.getEdad() <= 12) {
                    g.setColor(Color.green);
                }

                if (persona.getEdad() > 11 && persona.getEdad() <= 60) {
                    int verde = Color.green.getRGB();
                    int amarillo = Color.yellow.getRed();
                    Color resultante = new Color((verde + amarillo) / 2);
                    g.setColor(resultante);
                }

                if (persona.getEdad() > 60) {
                    g.setColor(Color.yellow);
                }

                if (persona.getGenero().toLowerCase().equals("femenino")) {
                    g.fillRect(puntoX + (anchoCabeza / 3) * 2, puntoY - largoDePiernas - largoDeTorso - largoDeCabeza + (largoDeCabeza / 3), (largoDeCabeza / 3), largoDeCabeza - 7);
                    //Dibujo la piernas de mujer
                    g.drawLine(puntoX, puntoY - (largoDePiernas / 2), puntoX + (anchoBrazo / 2), puntoY - largoDePiernas);
                    g.drawLine(puntoX + (anchoBrazo / 2), puntoY - largoDePiernas, puntoX + anchoBrazo, puntoY - (largoDePiernas / 2));
                    g.drawLine(puntoX, puntoY - (largoDePiernas / 2), puntoX + anchoBrazo, puntoY - (largoDePiernas / 2));
                    g.drawLine(puntoX + (anchoBrazo / 3), puntoY, puntoX + (anchoBrazo / 3), puntoY - (largoDePiernas / 2));
                    g.drawLine(puntoX + (anchoBrazo / 3) * 2, puntoY, puntoX + (anchoBrazo / 3) * 2, puntoY - (largoDePiernas / 2));
                }
                if (persona.getGenero().toLowerCase().equals("masculino")) {
                    //Dibujo la piernas del hombre
                    g.drawLine(puntoX, puntoY, puntoX + (anchoBrazo / 2), puntoY - largoDePiernas);
                    g.drawLine(puntoX + (anchoBrazo / 2), puntoY - largoDePiernas, puntoX + anchoBrazo, puntoY);
                }


                //Dibujo el torso
                g.drawLine(puntoX + (anchoBrazo / 2), puntoY - largoDePiernas, puntoX + (anchoBrazo / 2), puntoY - largoDePiernas - largoDeTorso);

                //Dibujo los brazos
                g.drawLine(puntoX, puntoY - largoDePiernas - largoDeTorso + ((largoDeTorso * 10) / 35), puntoX + anchoBrazo, puntoY - largoDePiernas - largoDeTorso + ((largoDeTorso * 10) / 35));

                //Dibujo la cabeza
                g.drawOval(puntoX, puntoY - largoDePiernas - largoDeTorso - largoDeCabeza, anchoCabeza, largoDeCabeza);
                puntoX += anchoBrazo + 30;
            }
        }
    }
}
