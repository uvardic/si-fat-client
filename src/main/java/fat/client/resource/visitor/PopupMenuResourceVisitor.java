package fat.client.resource.visitor;

import fat.client.gui.tree.menu.RemoveResourceAction;
import fat.client.gui.tree.menu.RemoveResourceChildrenAction;
import fat.client.gui.tree.menu.TreeMenu;
import fat.client.resource.Attribute;
import fat.client.resource.Entity;
import fat.client.resource.Repository;

import java.awt.event.MouseEvent;

public class PopupMenuResourceVisitor implements ResourceVisitor {

    private final TreeMenu treeMenu = new TreeMenu();

    public void showMenu(MouseEvent event) {
        treeMenu.showActions();
        treeMenu.show(event.getComponent(), event.getX(), event.getY());
    }

    @Override
    public void visit(Repository repository) {
        treeMenu.addActions(new RemoveResourceAction(), new RemoveResourceChildrenAction());
    }

    @Override
    public void visit(Entity entity) {
        treeMenu.addActions(new RemoveResourceAction(), new RemoveResourceChildrenAction());
    }

    @Override
    public void visit(Attribute attribute) {
        treeMenu.addActions(new RemoveResourceAction(), new RemoveResourceChildrenAction());
    }
}
