package fat.client.gui.panel.state;

import fat.client.gui.panel.ResourcePanel;
import fat.client.gui.panel.component.SaveOperationPanel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaveOperationPanelState implements OperationPanelState {

    private final ResourcePanel resourcePanel;

    @Override
    public void initializePanel() {
        resourcePanel.getContentPanel().setBottomComponent(new SaveOperationPanel());
    }

}
