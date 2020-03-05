package fat.client.gui.panel.state;

import fat.client.gui.panel.ResourcePanel;
import fat.client.gui.panel.component.UpdateResourcePanel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateResourcePanelState implements PanelState {

    private final ResourcePanel resourcePanel;

    @Override
    public void initializePanel() {
        resourcePanel.getContentPanel().setBottomComponent(new UpdateResourcePanel());
    }

}
