package fat.client.gui.tree;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

class TreeCellRenderer extends DefaultTreeCellRenderer {

    TreeCellRenderer() {}

    @Override
    public Component getTreeCellRendererComponent(
            JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus
    ) {
        super.getTreeCellRendererComponent(
                tree, ((Node) value).format(), sel, expanded, leaf, row, hasFocus
        );

        return this;
    }

}
