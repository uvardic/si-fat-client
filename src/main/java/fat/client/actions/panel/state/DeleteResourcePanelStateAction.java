package fat.client.actions.panel.state;

import fat.client.gui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DeleteResourcePanelStateAction extends AbstractAction {

    public DeleteResourcePanelStateAction() {
        putValue(NAME, "Delete Resource Panel");
        putValue(SHORT_DESCRIPTION, "Delete Resource Panel");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance()
                .getResourcePanel()
                .getPanelStateManager()
                .startDeleteResourcePanelState();
    }

}
