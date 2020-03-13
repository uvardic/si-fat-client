package fat.client.resource.visitor;

import fat.client.gui.MainFrame;
import fat.client.gui.resourcepanel.tablepanel.TablePanel;
import fat.client.resource.Attribute;
import fat.client.resource.AttributeDescription;
import fat.client.resource.Entity;
import fat.client.resource.Repository;

public class RemoveResourceChildrenVisitor implements ResourceVisitor {

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
    public void visit(Attribute attribute) {
        attribute.removeChildren();
    }

    @Override
    public void visit(AttributeDescription attributeDescription) {}
}
