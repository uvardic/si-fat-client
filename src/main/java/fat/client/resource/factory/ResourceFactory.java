package fat.client.resource.factory;

import fat.client.resource.*;

public class ResourceFactory {

    public Resource getResource(ResourceType type, String name, Resource parent) {
        switch (type) {
            case WORKSPACE:
                return new Workspace(name);
            case REPOSITORY:
                return new Repository(name, parent);
            case ENTITY:
                return new Entity(name, parent);
            case ATTRIBUTE:
                return new Attribute(name, parent);
            default:
                throw new IllegalStateException(String.format("Resource %s not found!", type));
        }
    }

}
