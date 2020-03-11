package fat.client.resource;

import fat.client.resource.visitor.ResourceVisitor;

public class Attribute extends Resource {

    private boolean isKey;

    public Attribute(String name, Resource parent) {
        super(name, parent);
        getParent().addChild(this);
    }

    public Attribute(String name, Resource parent, boolean isKey) {
        super(name, parent);
        this.isKey = isKey;
        getParent().addChild(this);
    }

    public boolean isKey() {
        return isKey;
    }

    @Override
    public void acceptVisitor(ResourceVisitor visitor) {
        visitor.visit(this);
    }

}
