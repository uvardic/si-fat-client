package fat.client.gui.menu;

import fat.client.gui.Action;
import fat.client.gui.MainFrame;
import fat.client.metascheme.MetaSchemeWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SaveResourceAction extends Action {

    SaveResourceAction() {
        super("Save Resource", "Save Resource");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));

        int returnValue = fileChooser.showSaveDialog(MainFrame.getInstance());

        if (returnValue != JFileChooser.APPROVE_OPTION)
            return;

        MetaSchemeWriter.write(addExtensionToFile(fileChooser.getSelectedFile().getAbsolutePath()));
    }

    private String addExtensionToFile(String filePath) {
        return filePath.endsWith(".json") ? filePath : filePath + ".json";
    }
}
