package fat.client.gui.panel.state;

import fat.client.gui.panel.ResourcePanel;
import fat.client.gui.panel.component.FindResourcePanel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindResourcePanelState implements PanelState {

    private final ResourcePanel resourcePanel;

    @Override
    public void initializePanel() {
        resourcePanel.getContentPanel().setBottomComponent(new FindResourcePanel());
    }

}
