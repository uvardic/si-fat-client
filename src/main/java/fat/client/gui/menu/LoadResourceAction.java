package fat.client.gui.menu;

import fat.client.gui.Action;
import fat.client.gui.MainFrame;
import fat.client.metascheme.MetaSchemeParser;

import javax.swing.*;
import java.awt.event.ActionEvent;

class LoadResourceAction extends Action {

    LoadResourceAction() {
        super("Load Resource", "Load Resource");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));

        int returnValue = fileChooser.showOpenDialog(MainFrame.getInstance());

        if (returnValue != JFileChooser.APPROVE_OPTION)
            return;

        MetaSchemeParser.parseSchema(fileChooser.getSelectedFile().getAbsolutePath());
    }
}
