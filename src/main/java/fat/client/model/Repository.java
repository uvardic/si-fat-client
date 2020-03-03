package fat.client.model;

import fat.client.gui.tree.Tree;
import fat.client.gui.tree.node.EntityNode;
import fat.client.gui.tree.node.Node;
import fat.client.gui.tree.node.WorkspaceNode;
import fat.client.observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Repository implements Resource<Workspace, Entity> {

    private final String name;

    private final Workspace parent;

    private final List<Entity> children = new ArrayList<>();

    public Repository(String name, Workspace parent) {
        this.name = name;
        this.parent = parent;
        this.parent.addChild(this);
    }

    @Override
    public void addChild(Entity child) {
        if (child == null)
            throw new NullPointerException("Child resource can't be null!");

        if (children.contains(child))
            throw new IllegalArgumentException(String.format("Child: %s is already present!", child));

        children.add(child);
        Observer.updateSubject(Tree.class);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Node getParentAsNode() {
        return new WorkspaceNode(parent);
    }

    @Override
    public List<Node> getChildrenAsNodes() {
        return children.stream()
                .map(EntityNode::new)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("Repository{name='%s', parent=%s}", name, parent.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Repository) {
            Repository other = (Repository) o;

            return Objects.equals(this.name, other.name);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
