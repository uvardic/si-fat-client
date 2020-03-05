package fat.client.gui.tree;

import fat.client.gui.MainFrame;
import fat.client.observer.Observer;
import fat.client.resource.Attribute;
import fat.client.resource.Entity;
import fat.client.resource.Repository;
import fat.client.resource.Resource;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

        TreeController treeController = new TreeController();

        addMouseListener(treeController);
        addMouseWheelListener(treeController);
        addMouseMotionListener(treeController);
        addTreeSelectionListener(treeController);
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

    @Override
    public Node getLastSelectedPathComponent() {
        return (Node) super.getLastSelectedPathComponent();
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

    private class TreeController extends MouseAdapter implements TreeSelectionListener {

        @Override
        public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
            Resource lastSelectedResource = getLastSelectedPathComponent().getResource();

            // Replace with visitor pattern if more cases occur
            if (lastSelectedResource instanceof Entity)
                MainFrame.getInstance().getResourcePanel().addTable(lastSelectedResource);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }
    }

}
