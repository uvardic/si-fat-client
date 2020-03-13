package fat.client.resource.visitor;

import fat.client.gui.MainFrame;
import fat.client.gui.resourcepanel.tablepanel.TablePanel;
import fat.client.resource.Attribute;
import fat.client.resource.AttributeDescription;
import fat.client.resource.Entity;
import fat.client.resource.Repository;

public class RemoveResourceVisitor implements ResourceVisitor {

    @Override
    public void visit(Repository repository) {
        TablePanel tablePanel = MainFrame.getInstance().getResourcePanel().getTablePanel();
        repository.getChildren().forEach(tablePanel::removeTableFor);
        repository.getParent().removeChild(repository);
    }

    @Override
    public void visit(Entity entity) {
        MainFrame.getInstance().getResourcePanel().getTablePanel().removeTableFor(entity);
        entity.getParent().removeChild(entity);
    }

    @Override
    public void visit(Attribute attribute) {
        attribute.getParent().removeChild(attribute);
    }

    @Override
    public void visit(AttributeDescription attributeDescription) {
        attributeDescription.getParent().removeChild(attributeDescription);
    }
}
