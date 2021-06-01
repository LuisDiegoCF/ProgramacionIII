package practico3.imagenes;

public class Pixel {

    private int red;
    private int green;
    private int blue;
    private int color;

    public Pixel(int pixel) {
        this.color = pixel;
        red  = getR();
        green = getG();
        blue = getB();
        setColor(red, green, blue);
    }

    public boolean margenPermitido(int color){
        if(getR() - 30 <= color || color <= getR() + 30){
            if(getG() - 30 <= color || color <= getG() + 30){
                if(getB() - 30 <= color || color <= getB() + 30){
                    return true;
                }
            }
        }
        return false;
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
