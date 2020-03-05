package fat.client.gui.panel.state;

import fat.client.gui.panel.ResourcePanel;
import fat.client.gui.panel.component.UpdateOperationPanel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateOperationPanelState implements OperationPanelState {

    private final ResourcePanel resourcePanel;

    @Override
    public void initializePanel() {
        resourcePanel.getContentPanel().setBottomComponent(new UpdateOperationPanel());
    }

}
