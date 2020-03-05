package fat.client.gui.util;

import javax.swing.*;
import java.net.URL;

public enum IconLoader {

    DELETE_OPERATION("/icon/operation/deleteIcon.png"),
    FIND_OPERATION("/icon/operation/findIcon.png"),
    SAVE_OPERATION("/icon/operation/saveIcon.png"),
    UPDATE_OPERATION("/icon/operation/updateIcon.png");

    final String iconPath;

    IconLoader(String iconPath) {
        this.iconPath = iconPath;
    }

    public Icon loadIcon() {
        URL iconURL = IconLoader.class.getResource(iconPath);

        return new ImageIcon(iconURL);
    }

}
