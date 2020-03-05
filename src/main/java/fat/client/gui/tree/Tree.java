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
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

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
        Repository repository = new Repository("Repo", root);
        repository.addObserver(this);

        Entity entity = new Entity("Entity", repository);
        entity.addObserver(this);

        Entity entity1 = new Entity("Entity1", repository);
        entity.addObserver(this);

        new Attribute("At12t", entity, true);
        new Attribute("Att", entity);
        new Attribute("At132t", entity, true);

        new Attribute("At12t", entity1, true);
        new Attribute("Att", entity1);
        new Attribute("At132t", entity1);

        return new Node(root);
    }

    public void selectNodeFor(Resource resource) {
        selectionModel.setSelectionPath(getPathFor(new Node(resource)));
    }

    private TreePath getPathFor(TreeNode node) {
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(node);

        node = node.getParent();

        while (node != null) {
            nodes.add(0, node);
            node = node.getParent();
        }

        if (nodes.isEmpty())
            return new TreePath(getModel().getRoot());

        return new TreePath(nodes.toArray());
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
