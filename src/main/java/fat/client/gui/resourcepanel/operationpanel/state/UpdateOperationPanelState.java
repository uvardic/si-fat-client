package fat.client.gui.resourcepanel.operationpanel.state;

import fat.client.gui.resourcepanel.ResourcePanel;
import fat.client.gui.resourcepanel.operationpanel.component.UpdateOperationPanel;

import javax.swing.*;

public class UpdateOperationPanelState implements OperationPanelState {

    private final ResourcePanel resourcePanel;

    public UpdateOperationPanelState(ResourcePanel resourcePanel) {
        this.resourcePanel = resourcePanel;
    }

    @Override
    public void initializePanel() {
        resourcePanel.getContentPanel().setBottomComponent(new JScrollPane(new UpdateOperationPanel()));
    }

}
