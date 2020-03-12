package fat.client.resource.factory;

import fat.client.gui.MainFrame;
import fat.client.resource.Attribute;
import fat.client.resource.Entity;
import fat.client.resource.Resource;

public class ResourceFactory {

    public Resource getResource(ResourceType type, String name, Resource parent) {
        switch (type) {
            case ENTITY:
                return initializeEntity(name, parent);
            case ATTRIBUTE:
                return initializeAttribute(name, parent);
            default:
                throw new IllegalStateException(String.format("Resource %s not found!", type));
        }
    }

    private Resource initializeEntity(String name, Resource parent) {
        Resource entity = new Entity(name, parent);
        entity.addObserver(MainFrame.getInstance().getTree());
        return entity;
    }

    private Resource initializeAttribute(String name, Resource parent) {
        Resource attribute = new Attribute(name, parent);
        attribute.addObserver(MainFrame.getInstance().getTree());
        return attribute;
    }

}
