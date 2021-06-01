package practico5.modelo;

import practico5.lista.Lista;

import java.awt.*;

public class Participante {
    private String nombre;
    private boolean enviado;
    private int distaciaEntreLineas = 15;
    public static final int ANCHO_USUARIO = 175;

    public Participante(String nombre) {
        this.nombre = nombre;
        enviado = false;
    }

    public String toString() {
        return nombre;
    }

    public void dibujar(Graphics g, int posY) {
        int alto = 15;
        int izquierdaPosX = 10;
        g.setColor(Color.green);
        g.fillRoundRect(izquierdaPosX, posY, ANCHO_USUARIO, alto, 20, 20);

        g.setColor(Color.black);
        g.drawString(nombre, izquierdaPosX, posY + 10 * distaciaEntreLineas);
    }

    public int getDistaciaEntreLineas() {
        return distaciaEntreLineas;
    }

    public void setDistaciaEntreLineas(int distaciaEntreLineas) {
        this.distaciaEntreLineas = distaciaEntreLineas;
    }
}
