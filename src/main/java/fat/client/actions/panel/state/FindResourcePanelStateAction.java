package fat.client.actions.panel.state;

import fat.client.gui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FindResourcePanelStateAction extends AbstractAction {

    public FindResourcePanelStateAction() {
        putValue(NAME, "Find Resource Panel");
        putValue(SHORT_DESCRIPTION, "Find Resource Panel");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        MainFrame.getInstance()
                .getResourcePanel()
                .getPanelStateManager()
                .startFindResourcePanelState();
    }

}
