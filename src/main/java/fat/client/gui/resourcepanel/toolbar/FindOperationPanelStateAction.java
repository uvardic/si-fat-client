package fat.client.gui.resourcepanel.toolbar;

import fat.client.gui.Action;
import fat.client.gui.MainFrame;
import fat.client.gui.util.IconLoader;

import java.awt.event.ActionEvent;

class FindOperationPanelStateAction extends Action {

    FindOperationPanelStateAction() {
        super("Find State", "Find State", IconLoader.FIND_OPERATION);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        MainFrame.getInstance()
                .getResourcePanel()
                .getOperationPanelStateManager()
                .startFindResourcePanelState();
    }

}
