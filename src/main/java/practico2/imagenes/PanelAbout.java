package practico2.imagenes;

import javax.swing.*;

public class PanelAbout extends JPanel {

    PanelAbout(){
        init();
    }

    private void init() {
        JLabel texto = new JLabel("<html><br><strong>INFORMACIÓN</strong><br><br>"
                + "1. El programa permite la carga de 2 imagenes<br>"
                + "2. Debe cargar 2 imagenes de 600 x 400<br>"
                + "3. La primera imagen se mostrará de la siguiente forma (100% - 50% - 0%)<br>"
                + "4. La segunda imagen se mostrará de la siguiente forma (0% - 50% - 100%)<br>"
                + "5. Tendra una opcion que le permitira ver el merge de las 2 imagenes.</html>");
        this.add(texto);
    }
}
