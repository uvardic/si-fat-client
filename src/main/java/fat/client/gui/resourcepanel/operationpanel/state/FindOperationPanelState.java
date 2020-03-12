package fat.client.gui.resourcepanel.operationpanel.state;

import fat.client.gui.resourcepanel.ResourcePanel;
import fat.client.gui.resourcepanel.operationpanel.component.FindOperationPanel;

import javax.swing.*;

public class FindOperationPanelState implements OperationPanelState {

    private final ResourcePanel resourcePanel;

    public FindOperationPanelState(ResourcePanel resourcePanel) {
        this.resourcePanel = resourcePanel;
    }

    @Override
    public void initializePanel() {
        resourcePanel.getContentPanel().setBottomComponent(new JScrollPane(new FindOperationPanel()));
    }

}
