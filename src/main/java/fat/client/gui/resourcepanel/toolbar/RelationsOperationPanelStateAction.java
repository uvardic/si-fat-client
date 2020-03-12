package fat.client.gui.resourcepanel.toolbar;

import fat.client.gui.Action;
import fat.client.gui.MainFrame;
import fat.client.gui.util.IconLoader;

import java.awt.event.ActionEvent;

class RelationsOperationPanelStateAction extends Action {

    RelationsOperationPanelStateAction() {
        super("Relations state", "Relations state", IconLoader.RELATION_OPERATION);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        MainFrame.getInstance()
                .getResourcePanel()
                .getOperationPanelStateManager()
                .startRelationsPanelState();
    }
}
