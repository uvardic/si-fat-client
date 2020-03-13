package fat.client.resource;

import fat.client.resource.visitor.ResourceVisitor;

public class Attribute extends Resource {

    public Attribute(String name, Resource parent) {
        super(name, parent);
        getParent().addChild(this);
    }

    public boolean isKey() {
        return getChildren().stream()
                .map(child -> (AttributeDescription) child)
                .filter(this::isPrimaryKeyDescription)
                .findFirst()
                .filter(attributeDescription -> Boolean.parseBoolean(attributeDescription.getNameValue()))
                .isPresent();
    }

    private boolean isPrimaryKeyDescription(AttributeDescription attributeDescription) {
        return attributeDescription.getName().contains(AttributeDescriptionType.PRIMARY_KEY.name());
    }

    @Override
    public void acceptVisitor(ResourceVisitor visitor) {
        visitor.visit(this);
    }

}
