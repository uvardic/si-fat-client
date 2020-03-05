package fat.client.actions;

import fat.client.actions.panel.state.DeleteResourcePanelStateAction;
import fat.client.actions.panel.state.FindResourcePanelStateAction;
import fat.client.actions.panel.state.SaveResourcePanelStateAction;
import fat.client.actions.panel.state.UpdateResourcePanelStateAction;

import javax.swing.*;

public final class ActionManager {

    private final AbstractAction[] panelStateActions = {
            new DeleteResourcePanelStateAction(), new FindResourcePanelStateAction(),
            new SaveResourcePanelStateAction(), new UpdateResourcePanelStateAction()
    };

    public AbstractAction[] getPanelStateActions() {
        return panelStateActions;
    }
}
