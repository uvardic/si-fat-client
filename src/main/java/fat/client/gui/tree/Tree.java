package fat.client.gui.tree;

import fat.client.observer.Observer;

import javax.swing.*;

public class Tree extends JTree implements Observer {

    private final Node root;

    private final TreeModel model;

    public Tree(Node root) {
        this.root = root;
        this.model = new TreeModel(root);

        setModel(model);
        setCellRenderer(new TreeCellRenderer());
        initializeController();
        root.getResource().addObserver(this);
    }

    private void initializeController() {
        TreeController controller = new TreeController(this);

        addMouseListener(controller);
        addMouseWheelListener(controller);
        addMouseMotionListener(controller);
        addTreeSelectionListener(controller);
    }

    public Node getRoot() {
        return root;
    }

    public void selectNode(Node node) {
        selectionModel.setSelectionPath(model.getPathFor(node));
    }

    @Override
    public void update(Object notification) {
        updateUI();
    }

    @Override
    public Node getLastSelectedPathComponent() {
        return (Node) super.getLastSelectedPathComponent();
    }

    @Override
    public TreeModel getModel() {
        return model;
    }
}
