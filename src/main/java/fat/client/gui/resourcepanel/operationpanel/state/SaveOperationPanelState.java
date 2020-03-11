package fat.client.gui.resourcepanel.operationpanel.state;


import fat.client.gui.resourcepanel.ResourcePanel;
import fat.client.gui.resourcepanel.operationpanel.component.SaveOperationPanel;

public class SaveOperationPanelState implements OperationPanelState {

    private final ResourcePanel resourcePanel;

    public SaveOperationPanelState(ResourcePanel resourcePanel) {
        this.resourcePanel = resourcePanel;
    }

    @Override
    public void initializePanel() {
        resourcePanel.getContentPanel().setBottomComponent(new SaveOperationPanel());
    }

}
