package fat.client.gui.resourcepanel.toolbar;

import javax.swing.*;

public class ResourcePanelToolbar extends JToolBar {

    public ResourcePanelToolbar() {
        setFloatable(false);
        
        addButton(new JButton(new DeleteOperationPanelStateAction()));
        addButton(new JButton(new FindOperationPanelStateAction()));
        addButton(new JButton(new SaveOperationPanelStateAction()));
        addButton(new JButton(new UpdateOperationPanelStateAction()));
    }

    private void addButton(JButton button) {
        button.setHideActionText(true);
        add(button);
    }

}
