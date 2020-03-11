package fat.client.gui.resourcepanel.toolbar;

import fat.client.gui.Action;
import fat.client.gui.MainFrame;
import fat.client.gui.util.IconLoader;

import java.awt.event.ActionEvent;

class SaveOperationPanelStateAction extends Action {

    SaveOperationPanelStateAction() {
        super("Save State", "Save State", IconLoader.SAVE_OPERATION);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance()
                .getResourcePanel()
                .getOperationPanelStateManager()
                .startSaveResourcePanelState();
    }

}
