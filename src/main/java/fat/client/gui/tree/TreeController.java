package fat.client.gui.tree;

import fat.client.gui.MainFrame;
import fat.client.resource.Entity;
import fat.client.resource.Resource;
import fat.client.resource.visitor.PopupMenuResourceVisitor;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class TreeController extends MouseAdapter implements TreeSelectionListener {

    private final Tree tree;

    TreeController(Tree tree) {
        this.tree = tree;
    }

    @Override
    public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
        Resource lastSelectedResource = tree.getLastSelectedPathComponent().getResource();

        if (lastSelectedResource instanceof Entity)
            MainFrame.getInstance()
                    .getResourcePanel()
                    .getTablePanel()
                    .addTableFor(lastSelectedResource);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if (event.getButton() != MouseEvent.BUTTON3)
            return;

        tree.setSelectionPath(tree.getClosestPathForLocation(event.getX(), event.getY()));

        PopupMenuResourceVisitor popupMenuVisitor = new PopupMenuResourceVisitor();
        tree.getLastSelectedPathComponent().getResource().acceptVisitor(popupMenuVisitor);
        popupMenuVisitor.showMenu(event);
    }

}
