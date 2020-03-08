package fat.client.resource.visitor;

import fat.client.gui.MainFrame;
import fat.client.gui.panel.ResourcePanel;
import fat.client.resource.Attribute;
import fat.client.resource.Entity;
import fat.client.resource.Repository;
import fat.client.resource.Workspace;

public class RemoveResourceChildrenVisitor implements ResourceVisitor {

    @Override
    public void visit(Workspace workspace) {
        ResourcePanel resourcePanel = MainFrame.getInstance().getResourcePanel();
        workspace.getChildren().forEach(repository -> repository.getChildren().forEach(resourcePanel::removeTableFor));
        workspace.removeChildren();
    }

    @Override
    public void visit(Repository repository) {
        ResourcePanel resourcePanel = MainFrame.getInstance().getResourcePanel();
        repository.getChildren().forEach(resourcePanel::removeTableFor);
        repository.removeChildren();
    }

    @Override
    public void visit(Entity entity) {
        entity.removeChildren();
    }

    @Override
    public void visit(Attribute attribute) {}
}
