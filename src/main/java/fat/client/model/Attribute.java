package fat.client.model;

import fat.client.gui.tree.node.EntityNode;
import fat.client.gui.tree.node.Node;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class Attribute implements Resource<Entity, Object> {

    private final String name;

    private final Entity parent;

    private boolean isPrimaryKey;

    public Attribute(String name, Entity parent) {
        this.name = name;
        this.parent = parent;
        this.parent.addChild(this);
    }

    @Override
    public void addChild(Object child) {}

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Node getParentAsNode() {
        return new EntityNode(parent);
    }

    @Override
    public List<Node> getChildrenAsNodes() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("Attribute{name='%s', parent=%s}", name, parent.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Attribute) {
            Attribute other = (Attribute) o;

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
