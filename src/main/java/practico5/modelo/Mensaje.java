package practico5.modelo;

import practico5.lista.Lista;

import java.awt.*;
import java.time.DateTimeException;
import java.util.Date;


public class Mensaje {

    private String texto;
    private int distaciaEntreLineas = 15;
    private boolean usuarioRemoto;
    private boolean enviado;

    public static final int ANCHO_MENSAJE = 275;

    public Mensaje(String texto, boolean usuarioRemoto){
        this.texto = texto;
        this.usuarioRemoto = usuarioRemoto;
        enviado = false;
    }

    public String toString(){
        return texto;
    }

    public Lista cortarMensaje(){
        int numLineas = texto.length() / 40;
        Lista<String> lineas = new Lista<>();
        for (int i = 0; i <= numLineas; i++){
            if(i == numLineas){
                lineas.add(texto.substring(i*40));
                break;
            }
            lineas.add(texto.substring(i*40, i*40 + 40));
        }
        return lineas;
    }

    public int getAlto(){
        Lista<String> lineas = cortarMensaje();
        return lineas.tamano() * distaciaEntreLineas;
    }

    public void dibujar(Graphics g, int posY){
        Lista<String> lineas = cortarMensaje();
        int alto = getAlto();
        int izquierdaPosX = 10;
        int derechaPosX = 315;
        if (usuarioRemoto) {
            g.setColor(Color.yellow);
            g.fillRoundRect(izquierdaPosX, posY, ANCHO_MENSAJE, alto, 20, 20);

            drawTextos(g, lineas, izquierdaPosX, posY + 10);
        } else {
            g.setColor(Color.green);
            g.fillRoundRect(derechaPosX, posY, ANCHO_MENSAJE, alto, 20, 20);

            drawTextos(g, lineas, derechaPosX, posY + 10);
        }
    }
    private void drawTextos(Graphics g, Lista<String> lineas, int x, int y) {
        g.setColor(Color.black);
        int i = 0;
        for(String s : lineas) {
            g.drawString(s, x, y + i*distaciaEntreLineas);
            i++;
        }
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getDistaciaEntreLineas() {
        return distaciaEntreLineas;
    }

    public void setDistaciaEntreLineas(int distaciaEntreLineas) {
        this.distaciaEntreLineas = distaciaEntreLineas;
    }

    public boolean isUsuarioRemoto() {
        return usuarioRemoto;
    }

    public void setUsuarioRemoto(boolean usuarioRemoto) {
        this.usuarioRemoto = usuarioRemoto;
    }
    public boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }
}
