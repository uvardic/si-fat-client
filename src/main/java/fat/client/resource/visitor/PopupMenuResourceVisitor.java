package fat.client.resource.visitor;

import fat.client.actions.ActionManager;
import fat.client.gui.MainFrame;
import fat.client.resource.Attribute;
import fat.client.resource.Entity;
import fat.client.resource.Repository;
import fat.client.resource.Workspace;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class PopupMenuResourceVisitor implements ResourceVisitor {

    private final JPopupMenu popupMenu = new JPopupMenu();

    private final ActionManager actionManager = MainFrame.getInstance().getActionManager();

    public void showMenu(MouseEvent event) {
        popupMenu.removeAll();
        popupMenu.show(event.getComponent(), event.getX(), event.getY());
    }

    @Override
    public void visit(Workspace workspace) {
    }

    @Override
    public void visit(Repository repository) {
        popupMenu.add(actionManager.getRemoveResourceAction());
    }

    @Override
    public void visit(Entity entity) {
        popupMenu.add(actionManager.getRemoveResourceAction());
    }

    @Override
    public void visit(Attribute attribute) {
        popupMenu.add(actionManager.getRemoveResourceAction());
    }
}
