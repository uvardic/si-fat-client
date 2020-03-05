package fat.client.actions.panel.state;

import fat.client.gui.MainFrame;
import fat.client.gui.util.IconLoader;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SaveOperationPanelStateAction extends AbstractAction {

    public SaveOperationPanelStateAction() {
        putValue(NAME, "Save Resource Panel");
        putValue(SHORT_DESCRIPTION, "Save Resource Panel");
        putValue(SMALL_ICON, IconLoader.SAVE_OPERATION.loadIcon());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance()
                .getResourcePanel()
                .getOperationPanelStateManager()
                .startAddResourcePanelState();
    }

}
