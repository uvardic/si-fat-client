package fat.client;

import fat.client.gui.MainFrame;

import java.awt.*;

public class FatApp {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> MainFrame.getInstance().setVisible(true));
    }

}
