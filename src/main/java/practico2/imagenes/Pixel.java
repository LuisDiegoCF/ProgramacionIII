package practico2.imagenes;

import java.awt.*;

public class Pixel {

    private int red;
    private int green;
    private int blue;
    private int color;
    private WinImagen win;

    public Pixel(int pixel) {
        this.color = pixel;
        red  = getR() / 2;
        green = getG() / 2;
        blue = getB() / 2;
        setColor(red, green, blue);
    }

    public void setColor(int r, int g, int b) {
        color = (r << 16) | (g << 8) | b;
    }

    public int getR() {
        return (0x00ff0000 & color) >> 16;
    }

    public int getG() {
        return (0x0000ff00 & color) >> 8;
    }

    public int getB() {
        return (0x000000ff & color);
    }

    public int getIntColor() {
        return color;
    }
}
