package fat.client.resource;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Entity extends Resource {

    private final List<Entity> relations = new ArrayList<>();

    public Entity(String name) {
        super(name);
    }

    public void addRelation(Entity relation) {
        if (relation == null)
            throw new NullPointerException("Relation can't be null!");

        relations.add(relation);
    }

    public List<Entity> getRelations() {
        return unmodifiableList(relations);
    }

}
