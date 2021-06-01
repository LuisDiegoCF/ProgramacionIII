package practico2.transformaciones;

import practico2.imagenes.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import practico2.imagenes.Pixel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Imagen1 extends Transformacion {

    private final static Logger logger = LogManager.getLogger();
    private JFileChooser chooser = new JFileChooser();
    private BufferedImage bufferedImageimagen1;
    private int ancho;
    private int alto;
    private boolean validacion;

    public Imagen1(Imagen img) {
        imagenBase = img;
    }

    @Override
    public void hacer() {
        try {
            chooser.setDialogTitle("Selecciona la imagen");
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagen Tipo", "png", "jpg", "jfif");
            chooser.setFileFilter(filtro);
            int imagenSeleccionada = chooser.showOpenDialog(null);
            if (imagenSeleccionada == JFileChooser.APPROVE_OPTION) {
                bufferedImageimagen1 = ImageIO.read(chooser.getSelectedFile());
                ancho = bufferedImageimagen1.getWidth();
                alto = bufferedImageimagen1.getHeight();
                if (ancho == 600 && alto == 400) {
                    validacion = true;
                    logger.debug("Cargando ImagenModelo, Dimensiones: " + ancho + " x " + alto);
                } else {
                    JOptionPane.showMessageDialog(null, "Las dimensiones deben ser 600 x 400");
                }
            }
            if (validacion) {
                for (int i = 0; i < ancho - 400; i++) {
                    for (int j = 0; j < alto; j++) {
                        int color = bufferedImageimagen1.getRGB(i, j);
                        imagenBase.setPixel(color, i, j);
                    }
                }
                for (int i = ancho - 400; i < ancho - 200; i++) {
                    for (int j = 0; j < alto; j++) {
                        int color = bufferedImageimagen1.getRGB(i, j);

                        Pixel pixel = new Pixel(color);
                        imagenBase.setPixel(pixel.getIntColor(), i, j);
                    }
                }
                for (int i = ancho - 200; i < ancho; i++) {
                    for (int j = 0; j < alto; j++) {
                        imagenBase.setPixel(0, i, j);
                    }
                }
                validacion = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        imagenBase.cambioOk();
    }

    public BufferedImage getBufferedImage() {
        return bufferedImageimagen1;
    }
}
