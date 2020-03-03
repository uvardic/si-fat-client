package fat.client.model;

import fat.client.gui.tree.Tree;
import fat.client.gui.tree.node.AttributeNode;
import fat.client.gui.tree.node.Node;
import fat.client.gui.tree.node.RepositoryNode;
import fat.client.observer.Observer;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class Entity implements Resource<Repository, Attribute> {

    private final String name;

    private final Repository parent;

    private final List<Attribute> children = new ArrayList<>();

    private final List<Entity> relations = new ArrayList<>();

    public Entity(String name, Repository parent) {
        this.name = name;
        this.parent = parent;
        this.parent.addChild(this);
    }

    public void addRelation(Entity relation) {
        if (relation == null)
            throw new NullPointerException("Relation can't be null!");

        if (relations.contains(relation))
            throw new IllegalArgumentException(String.format("Child: %s is already present!", relation));

        relations.add(relation);
    }

    @Override
    public void addChild(Attribute child) {
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
        return new RepositoryNode(parent);
    }

    @Override
    public List<Node> getChildrenAsNodes() {
        return children.stream()
                .map(AttributeNode::new)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("Entity{name='%s', parent=%s}", name, parent.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Entity) {
            Entity other = (Entity) o;

            return Objects.equals(this.name, other.name)
                    && Objects.equals(this.parent, other.parent);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
