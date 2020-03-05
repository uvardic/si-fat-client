package fat.client.resource;

import lombok.Getter;

@Getter
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

}
