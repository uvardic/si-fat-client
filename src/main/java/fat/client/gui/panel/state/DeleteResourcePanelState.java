package fat.client.gui.panel.state;

import fat.client.gui.panel.ResourcePanel;
import fat.client.gui.panel.component.DeleteResourcePanel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteResourcePanelState implements PanelState {

    private final ResourcePanel resourcePanel;

    @Override
    public void initializePanel() {
        resourcePanel.getContentPanel().setBottomComponent(new DeleteResourcePanel());
    }

}
