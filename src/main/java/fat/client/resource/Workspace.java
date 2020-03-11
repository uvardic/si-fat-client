package fat.client.resource;

import fat.client.resource.visitor.ResourceVisitor;

public class Workspace extends Resource {

    public Workspace(String name) {
        super(name, null);
    }

    @Override
    public void acceptVisitor(ResourceVisitor visitor) {
        visitor.visit(this);
    }
}
