package practico1.vista;

import practico1.modelo.Linea;

import java.awt.*;
import java.util.ArrayList;

public class DibujoLinea implements IDibujo {

    private ArrayList<Linea> lineas;


    public DibujoLinea(ArrayList<Linea> lineas) {
        this.lineas = lineas;
    }

    int puntoX = 30;

    public void dibujar(Graphics g) {
        if (lineas != null) {
            /*for (Linea linea : lineas) {
                g.drawLine(linea.getX(), linea.getY(), linea.getX(), 400 - linea.getAlto());
            }*/
            for (int i = 0; i < 5; i++){
                int puntoY = 420;
                int altura = (int)(Math.random() * 200) + 50;

                int anchoBrazo;
                int anchoCabeza;
                int largoDePiernas = ((altura * 40) / 100);
                int largoDeTorso = ((altura * 35) / 100);
                int largoDeCabeza = (altura * 25) / 100;
                anchoBrazo = largoDeCabeza;
                anchoCabeza = largoDeCabeza;
                g.drawLine(puntoX, puntoY, puntoX + (anchoBrazo / 2), puntoY - largoDePiernas);
                g.drawLine(puntoX + (anchoBrazo / 2), puntoY - largoDePiernas, puntoX + anchoBrazo, puntoY);
                g.drawLine(puntoX + (anchoBrazo / 2), puntoY - largoDePiernas, puntoX + (anchoBrazo / 2), puntoY - largoDePiernas - largoDeTorso);
                g.drawLine(puntoX, puntoY - largoDePiernas - largoDeTorso + ((largoDeTorso * 10) / 35), puntoX + anchoBrazo, puntoY - largoDePiernas - largoDeTorso + ((largoDeTorso * 10) / 35));
                g.drawOval(puntoX, puntoY - largoDePiernas - largoDeTorso - largoDeCabeza, anchoCabeza, largoDeCabeza);
                puntoX += anchoBrazo + 40;
            }
        //}
        }
    }
}

