package fat.client.actions.panel.state;

import fat.client.gui.MainFrame;
import fat.client.gui.util.IconLoader;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DeleteOperationPanelStateAction extends AbstractAction {

    public DeleteOperationPanelStateAction() {
        putValue(NAME, "Delete Resource Panel");
        putValue(SHORT_DESCRIPTION, "Delete Resource Panel");
        putValue(SMALL_ICON, IconLoader.DELETE_OPERATION.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance()
                .getResourcePanel()
                .getOperationPanelStateManager()
                .startDeleteResourcePanelState();
    }

}
