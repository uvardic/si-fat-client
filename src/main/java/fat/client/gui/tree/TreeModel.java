package fat.client.gui.tree;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

class TreeModel extends DefaultTreeModel {

    TreeModel(Node root) {
        super(root);
    }

    TreePath getPathFor(TreeNode node) {
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(node);

        node = node.getParent();

        while (node != null) {
            nodes.add(0, node);
            node = node.getParent();
        }

        if (nodes.isEmpty())
            return new TreePath(getRoot());

        return new TreePath(nodes.toArray());
    }

}
