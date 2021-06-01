package organigramaArbol;

import java.awt.EventQueue;

import organigramaArbol.gui.OrganigramaArbolFrame;

public class OrganigramaArbol {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    OrganigramaArbolFrame frame = new OrganigramaArbolFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}