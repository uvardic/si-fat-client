package fat.client.gui.tree;

import fat.client.observer.Observer;
import fat.client.resource.Attribute;
import fat.client.resource.Entity;
import fat.client.resource.Repository;
import fat.client.resource.Resource;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class Tree extends JTree implements Observer {

    private final Resource root;

    public Tree(Resource root) {
        this.root = root;
        initialize();
    }

    private void initialize() {
        root.addObserver(this);

        setModel(new DefaultTreeModel(testRoot()));
        setCellRenderer(new TreeCellRenderer());
    }

    private Node testRoot() {
        Repository repository = new Repository("Repo");
        repository.addObserver(this);
        root.addChild(repository);

        Entity entity = new Entity("Entity");
        entity.addObserver(this);
        repository.addChild(entity);

        entity.addChild(new Attribute("At12t"));
        entity.addChild(new Attribute("At121t"));
        entity.addChild(new Attribute("At123t"));

        return new Node(root);
    }

    @Override
    public void update(Object notification) {
        System.out.println("Updated by " + notification);
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
