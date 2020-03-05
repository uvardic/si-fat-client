package fat.client.resource;

import fat.client.resource.visitor.ResourceVisitor;

public class Repository extends Resource {

    public Repository(String name, Resource parent) {
        super(name, parent);
        getParent().addChild(this);
    }

    @Override
    public void acceptVisitor(ResourceVisitor visitor) {
        visitor.visit(this);
    }

}
