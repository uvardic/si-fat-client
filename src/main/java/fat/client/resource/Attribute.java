package fat.client.resource;

import lombok.Getter;

@Getter
public class Attribute extends Resource {

    private boolean isKey;

    public Attribute(String name) {
        super(name);
    }

    public Attribute(String name, boolean isKey) {
        super(name);
        this.isKey = isKey;
    }

}
