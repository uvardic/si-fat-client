package fat.client.gui.resourcepanel.operationpanel.state;

import fat.client.gui.resourcepanel.ResourcePanel;
import fat.client.gui.resourcepanel.tablepanel.relation.RelationsPanel;

public class RelationsOperationPanelState implements OperationPanelState {

    private final ResourcePanel resourcePanel;

    public RelationsOperationPanelState(ResourcePanel resourcePanel) {
        this.resourcePanel = resourcePanel;
    }

    @Override
    public void initializePanel() {
        resourcePanel.getContentPanel().setBottomComponent(new RelationsPanel());
    }
}
