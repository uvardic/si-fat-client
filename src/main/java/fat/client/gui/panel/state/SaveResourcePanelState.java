package fat.client.gui.panel.state;

import fat.client.gui.panel.ResourcePanel;
import fat.client.gui.panel.component.SaveResourcePanel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaveResourcePanelState implements PanelState {

    private final ResourcePanel resourcePanel;

    @Override
    public void initializePanel() {
        resourcePanel.getContentPanel().setBottomComponent(new SaveResourcePanel());
    }

}
