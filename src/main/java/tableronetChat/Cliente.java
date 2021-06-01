package tableronetChat;

import tableronetChat.gui.TableroNetChatFrame;

import java.awt.*;

public class Cliente {
    public static void main(String[] args) {
        /*String resource =
                "/auditoria.properties";
        URL configFileResource;
        configFileResource = TableroNetChat.class.getResource(resource);
        PropertyConfigurator.configure(configFileResource);*/

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TableroNetChatFrame frame = new TableroNetChatFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
