package fat.client.model;

import fat.client.gui.tree.Tree;
import fat.client.gui.tree.node.Node;
import fat.client.gui.tree.node.RepositoryNode;
import fat.client.observer.Observer;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Workspace implements Resource<Object, Repository> {

    private final String name;

    private final List<Repository> children = new ArrayList<>();

    @Override
    public void addChild(Repository child) {
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
        return null;
    }

    @Override
    public List<Node> getChildrenAsNodes() {
        return children.stream()
                .map(RepositoryNode::new)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("Workspace{name='%s'}", name);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Workspace) {
            Workspace other = (Workspace) o;

            return Objects.equals(this.name, other.name);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
