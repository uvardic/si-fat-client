package fat.client.gui.resourcepanel.toolbar;

import fat.client.gui.Action;
import fat.client.gui.MainFrame;
import fat.client.gui.util.IconLoader;

import java.awt.event.ActionEvent;

class UpdateOperationPanelStateAction extends Action {

    UpdateOperationPanelStateAction() {
        super("Update State", "Update State", IconLoader.UPDATE_OPERATION);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        MainFrame.getInstance()
                .getResourcePanel()
                .getOperationPanelStateManager()
                .startUpdateResourcePanelState();
    }
}
