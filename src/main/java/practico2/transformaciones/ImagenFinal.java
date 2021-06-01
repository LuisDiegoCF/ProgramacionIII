package practico2.transformaciones;

import practico2.imagenes.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import practico2.imagenes.Pixel;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class ImagenFinal extends Transformacion {

    private static Logger logger = LogManager.getLogger();

    private BufferedImage bufferedimagen1;
    private BufferedImage bufferedimagen2;

    public ImagenFinal(Imagen img) {
        imagenBase = img;
    }

    public void hacer() {
        if (bufferedimagen1 != null && bufferedimagen2 != null) {
            int ancho = 600;
            int alto = 400;

            for (int i = 0; i < ancho - 400; i++) {
                for (int j = 0; j < alto; j++) {
                    int color = bufferedimagen1.getRGB(i, j);
                    imagenBase.setPixel(color, i, j);
                }
            }
            for (int i = ancho - 400; i < ancho - 200; i++) {
                for (int j = 0; j < alto; j++) {
                    int color = bufferedimagen1.getRGB(i, j);
                    int color2 = bufferedimagen2.getRGB(i, j);

                    Pixel pixel = new Pixel(color);
                    Pixel pixel2 = new Pixel(color2);
                    int colores = pixel.getIntColor() + pixel2.getIntColor();

                    imagenBase.setPixel(colores, i, j);
                }
            }
            for (int i = ancho - 200; i < ancho; i++) {
                for (int j = 0; j < alto; j++) {
                    int color = bufferedimagen2.getRGB(i, j);
                    imagenBase.setPixel(color, i, j);
                }
            }
            logger.debug("Imagen resultante 100% - (50% + 50%) - 100%");
        } else {
            JOptionPane.showMessageDialog(null, "Debe cargar 2 imagenes");
        }
        imagenBase.cambioOk();
    }

    public void setBufferedimagen1(BufferedImage bufferedimagen1) {
        this.bufferedimagen1 = bufferedimagen1;
    }

    public void setBufferedimagen2(BufferedImage bufferedimagen2) {
        this.bufferedimagen2 = bufferedimagen2;
    }
}
