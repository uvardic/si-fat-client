package fat.client.resource;

import fat.client.resource.visitor.ResourceVisitor;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Entity extends Resource {

    private final List<Entity> relations = new ArrayList<>();

    public Entity(String name, Resource parent) {
        super(name, parent);
        getParent().addChild(this);
    }

    public void addRelation(Entity relation) {
        if (relation == null)
            throw new NullPointerException("Relation can't be null!");

        if (relations.contains(relation))
            return;

        relations.add(relation);
    }

    public List<Entity> getRelations() {
        return unmodifiableList(relations);
    }

    @Override
    public void acceptVisitor(ResourceVisitor visitor) {
        visitor.visit(this);
    }

}
