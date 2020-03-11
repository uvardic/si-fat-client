package fat.client.gui.tree;

import fat.client.observer.Observer;
import fat.client.resource.Attribute;
import fat.client.resource.Entity;
import fat.client.resource.Repository;
import fat.client.resource.Workspace;

import javax.swing.*;

public class Tree extends JTree implements Observer {

//    private final Node root = new Node(new Workspace("Workspace"));

    private final TreeModel model;

    public Tree() {
        this.model = new TreeModel(testRoot());
        setModel(model);
        setCellRenderer(new TreeCellRenderer());
        initializeController();
    }

    private void initializeController() {
        TreeController controller = new TreeController(this);

        addMouseListener(controller);
        addMouseWheelListener(controller);
        addMouseMotionListener(controller);
        addTreeSelectionListener(controller);
    }

    private Node testRoot() {
        Workspace root = new Workspace("Workspace");
        root.addObserver(this);

        Repository repository = new Repository("Repo", root);
        repository.addObserver(this);

        Entity entity = new Entity("Entity", repository);
        entity.addObserver(this);

        Entity entity1 = new Entity("Entity1", repository);
        entity1.addObserver(this);

        new Attribute("At12t", entity, true);
        new Attribute("Att", entity);
        new Attribute("At132t", entity, true);

        new Attribute("At12t", entity1, true);
        new Attribute("Att", entity1);
        new Attribute("At132t", entity1);

        return new Node(root);
    }

    public void selectNode(Node node) {
        selectionModel.setSelectionPath(model.getPathFor(node));
    }

    @Override
    public void update(Object notification) {
        System.out.println("Tree updated by " + notification);
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
