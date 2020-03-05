package fat.client.resource;

public class Repository extends Resource {

    public Repository(String name, Resource parent) {
        super(name, parent);
        getParent().addChild(this);
    }

}
