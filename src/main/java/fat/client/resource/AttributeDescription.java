package fat.client.resource;

import fat.client.resource.visitor.ResourceVisitor;
import org.apache.commons.lang3.StringUtils;

public class AttributeDescription extends Resource {

    public AttributeDescription(String name, Resource parent) {
        super(name, parent);
        getParent().addChild(this);
    }

    public String getNameValue() {
        if (!getName().contains("(") || !getName().contains(")"))
            throw new IllegalStateException("Invalid name format!");

        return StringUtils.substringBetween(getName(), "(", ")");
    }

    @Override
    public void acceptVisitor(ResourceVisitor visitor) {
        visitor.visit(this);
    }

}
