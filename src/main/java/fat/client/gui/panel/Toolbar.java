package fat.client.gui.panel;

import fat.client.actions.ActionManager;
import fat.client.gui.MainFrame;

import javax.swing.*;
import java.util.Arrays;

class Toolbar extends JToolBar {

    Toolbar() {
        setFloatable(false);

        ActionManager actionManager = MainFrame.getInstance().getActionManager();

        Arrays.stream(actionManager.getPanelStateActions())
                .map(JButton::new)
                .forEach(this::addButton);
    }

    private void addButton(JButton button) {
        button.setHideActionText(true);
        add(button);
    }

}
