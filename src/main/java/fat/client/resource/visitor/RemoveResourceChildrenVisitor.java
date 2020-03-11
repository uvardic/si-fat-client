package fat.client.resource.visitor;

import fat.client.gui.MainFrame;
import fat.client.gui.resourcepanel.tablepanel.TablePanel;
import fat.client.resource.Attribute;
import fat.client.resource.Entity;
import fat.client.resource.Repository;
import fat.client.resource.Workspace;

public class RemoveResourceChildrenVisitor implements ResourceVisitor {

    @Override
    public void visit(Workspace workspace) {
        TablePanel tablePanel = MainFrame.getInstance().getResourcePanel().getTablePanel();
        workspace.getChildren().forEach(repository -> repository.getChildren().forEach(tablePanel::removeTableFor));
        workspace.removeChildren();
    }

    @Override
    public void visit(Repository repository) {
        TablePanel tablePanel = MainFrame.getInstance().getResourcePanel().getTablePanel();
        repository.getChildren().forEach(tablePanel::removeTableFor);
        repository.removeChildren();
    }

    @Override
    public void visit(Entity entity) {
        entity.removeChildren();
    }

    @Override
    public void visit(Attribute attribute) {}

}
