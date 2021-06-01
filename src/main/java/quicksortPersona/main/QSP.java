package quicksortPersona.main;

import quicksortPersona.gui.QuickSortPersonaFrame;

import java.awt.EventQueue;

public class QSP {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QuickSortPersonaFrame frame = new QuickSortPersonaFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}