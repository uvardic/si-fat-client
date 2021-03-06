package fat.client.gui.resourcepanel.operationpanel.state;

import fat.client.gui.resourcepanel.ResourcePanel;
import fat.client.gui.resourcepanel.operationpanel.component.DeleteOperationPanel;

import javax.swing.*;

public class DeleteOperationPanelState implements OperationPanelState {

    private final ResourcePanel resourcePanel;

    public DeleteOperationPanelState(ResourcePanel resourcePanel) {
        this.resourcePanel = resourcePanel;
    }

    @Override
    public void initializePanel() {
        resourcePanel.getContentPanel().setBottomComponent(new JScrollPane(new DeleteOperationPanel()));
    }

}
