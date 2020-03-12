package fat.client.gui.menu;

import javax.swing.*;

public class Menu extends JMenuBar {

    public Menu() {
        add(createFileMenu());
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");

        fileMenu.add(new SaveResourceAction());
        fileMenu.add(new LoadResourceAction());

        return fileMenu;
    }

}
