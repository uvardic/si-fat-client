package fat.client.gui;

import fat.client.gui.util.IconLoader;

import javax.swing.*;

public abstract class Action extends AbstractAction {

    protected Action(String name, String description) {
        putValue(NAME, name);
        putValue(SHORT_DESCRIPTION, description);
    }

    protected Action(String name, String description, IconLoader iconLoader) {
        putValue(NAME, name);
        putValue(SHORT_DESCRIPTION, description);
        putValue(SMALL_ICON, iconLoader.loadIcon());
    }

}
