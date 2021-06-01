package practico3;

import practico3.gui.ImagenNetFrame;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Consola1 {
    public static void main(String[] args) {
        ImagenNetFrame winImagen = new ImagenNetFrame();
        winImagen.setVisible(true);
        /*LocalDateTime myObj = LocalDateTime.now();
        System.out.println(myObj);*/
        LocalTime horaActual = LocalTime.now();
        System.out.println("Hora actual: " + horaActual);
        LocalDate fechaActual = LocalDate.now();
        System.out.println("Fecha actual: " + fechaActual);
    }
}
