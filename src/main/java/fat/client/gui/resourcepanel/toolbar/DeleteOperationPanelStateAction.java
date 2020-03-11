package fat.client.gui.resourcepanel.toolbar;

import fat.client.gui.Action;
import fat.client.gui.MainFrame;
import fat.client.gui.util.IconLoader;

import java.awt.event.ActionEvent;

class DeleteOperationPanelStateAction extends Action {

    DeleteOperationPanelStateAction() {
        super("Delete State", "Delete State", IconLoader.DELETE_OPERATION);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance()
                .getResourcePanel()
                .getOperationPanelStateManager()
                .startDeleteResourcePanelState();
    }

}
