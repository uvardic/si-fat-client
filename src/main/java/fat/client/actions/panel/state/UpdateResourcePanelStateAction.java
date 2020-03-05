package fat.client.actions.panel.state;

import fat.client.gui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UpdateResourcePanelStateAction extends AbstractAction {

    public UpdateResourcePanelStateAction() {
        putValue(NAME, "Update Resource Panel");
        putValue(SHORT_DESCRIPTION, "Update Resource Panel");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        MainFrame.getInstance()
                .getResourcePanel()
                .getPanelStateManager()
                .startEditResourcePanelState();
    }
}
