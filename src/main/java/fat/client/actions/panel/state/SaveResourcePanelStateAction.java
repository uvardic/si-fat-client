package fat.client.actions.panel.state;

import fat.client.gui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SaveResourcePanelStateAction extends AbstractAction {

    public SaveResourcePanelStateAction() {
        putValue(NAME, "Save Resource Panel");
        putValue(SHORT_DESCRIPTION, "Save Resource Panel");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance()
                .getResourcePanel()
                .getPanelStateManager()
                .startAddResourcePanelState();
    }

}
