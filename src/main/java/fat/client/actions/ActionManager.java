package fat.client.actions;

import fat.client.actions.panel.state.DeleteOperationPanelStateAction;
import fat.client.actions.panel.state.FindOperationPanelStateAction;
import fat.client.actions.panel.state.SaveOperationPanelStateAction;
import fat.client.actions.panel.state.UpdateOperationPanelStateAction;
import fat.client.actions.resource.RemoveResourceAction;
import lombok.Getter;

import javax.swing.*;

@Getter
public final class ActionManager {

    private final AbstractAction[] panelStateActions = {
            new DeleteOperationPanelStateAction(), new FindOperationPanelStateAction(),
            new SaveOperationPanelStateAction(), new UpdateOperationPanelStateAction()
    };

    private final RemoveResourceAction removeResourceAction = new RemoveResourceAction();

}
