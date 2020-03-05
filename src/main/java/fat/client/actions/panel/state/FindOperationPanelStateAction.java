package fat.client.actions.panel.state;

import fat.client.gui.MainFrame;
import fat.client.gui.util.IconLoader;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FindOperationPanelStateAction extends AbstractAction {

    public FindOperationPanelStateAction() {
        putValue(NAME, "Find Resource Panel");
        putValue(SHORT_DESCRIPTION, "Find Resource Panel");
        putValue(SMALL_ICON, IconLoader.FIND_OPERATION.loadIcon());

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        MainFrame.getInstance()
                .getResourcePanel()
                .getOperationPanelStateManager()
                .startFindResourcePanelState();
    }

}
