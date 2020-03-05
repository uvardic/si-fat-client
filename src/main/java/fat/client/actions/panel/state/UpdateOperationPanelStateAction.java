package fat.client.actions.panel.state;

import fat.client.gui.MainFrame;
import fat.client.gui.util.IconLoader;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UpdateOperationPanelStateAction extends AbstractAction {

    public UpdateOperationPanelStateAction() {
        putValue(NAME, "Update Resource Panel");
        putValue(SHORT_DESCRIPTION, "Update Resource Panel");
        putValue(SMALL_ICON, IconLoader.UPDATE_OPERATION.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        MainFrame.getInstance()
                .getResourcePanel()
                .getOperationPanelStateManager()
                .startEditResourcePanelState();
    }
}
