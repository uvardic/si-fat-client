package fat.client.resource.visitor;

import fat.client.resource.Attribute;
import fat.client.resource.Entity;
import fat.client.resource.Repository;

public interface ResourceVisitor {

    void visit(Repository repository);

    void visit(Entity entity);

    void visit(Attribute attribute);

}
