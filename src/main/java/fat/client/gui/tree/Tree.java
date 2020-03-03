package fat.client.gui.tree;

import fat.client.gui.tree.node.Node;
import fat.client.gui.tree.node.WorkspaceNode;
import fat.client.model.Attribute;
import fat.client.model.Entity;
import fat.client.model.Repository;
import fat.client.model.Workspace;
import fat.client.observer.Observer;
import fat.client.observer.Subject;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class Tree extends JTree implements Subject {

    private static Tree instance;

    private Tree() {}

    public static Tree getInstance() {
        if (instance != null)
            return instance;

        return initialize();
    }

    private static Tree initialize() {
        instance = new Tree();

        instance.setModel(new DefaultTreeModel(testRoot()));
        instance.setCellRenderer(new TreeCellRenderer());

        Observer.addSubject(instance);

        return instance;
    }

    private static WorkspaceNode testRoot() {
        Workspace workspace = new Workspace("Workspace");
        Repository repository = new Repository("Repo", workspace);
        Entity entity = new Entity("Entity", repository);
        new Attribute("At12t", entity);
        new Attribute("Attt", entity);
        new Attribute("Att", entity);

        return new WorkspaceNode(workspace);
    }

    @Override
    public void update() {
        updateUI();
    }

    private static class TreeCellRenderer extends DefaultTreeCellRenderer {

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

}
