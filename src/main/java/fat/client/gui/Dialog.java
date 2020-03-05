package fat.client.gui;

import javax.swing.*;

public class Dialog {

    private Dialog() {}

    public static void message(String title, String message) {
        JOptionPane.showMessageDialog(MainFrame.getInstance(), message, title, JOptionPane.PLAIN_MESSAGE);
    }

    public static void error(String title, String message) {
        JOptionPane.showMessageDialog(MainFrame.getInstance(), message, title, JOptionPane.ERROR_MESSAGE);
    }

}
