package fat.client.resource.visitor;

import fat.client.resource.Attribute;
import fat.client.resource.Entity;
import fat.client.resource.Repository;
import fat.client.resource.Workspace;

public interface ResourceVisitor {

    void visit(Workspace workspace);

    void visit(Repository repository);

    void visit(Entity entity);

    void visit(Attribute attribute);

}
