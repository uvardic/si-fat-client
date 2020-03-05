package fat.client.resource.visitor;

import fat.client.gui.MainFrame;
import fat.client.gui.panel.ResourcePanel;
import fat.client.resource.Attribute;
import fat.client.resource.Entity;
import fat.client.resource.Repository;
import fat.client.resource.Workspace;

public class RemoveResourceVisitor implements ResourceVisitor {

    @Override
    public void visit(Workspace workspace) {}

    @Override
    public void visit(Repository repository) {
        ResourcePanel resourcePanel = MainFrame.getInstance().getResourcePanel();
        repository.getChildren().forEach(resourcePanel::removeTableFor);
        repository.getParent().removeChild(repository);
    }

    @Override
    public void visit(Entity entity) {
        MainFrame.getInstance().getResourcePanel().removeTableFor(entity);
        entity.getParent().removeChild(entity);
    }

    @Override
    public void visit(Attribute attribute) {
        attribute.getParent().removeChild(attribute);
    }
}
