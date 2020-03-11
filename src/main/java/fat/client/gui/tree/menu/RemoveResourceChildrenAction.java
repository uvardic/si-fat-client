package fat.client.gui.tree.menu;

import fat.client.gui.Action;
import fat.client.gui.MainFrame;
import fat.client.resource.visitor.RemoveResourceChildrenVisitor;

import java.awt.event.ActionEvent;

public class RemoveResourceChildrenAction extends Action {

    public RemoveResourceChildrenAction() {
        super("Remove Children", "Remove Children");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        MainFrame.getInstance()
                .getTree()
                .getLastSelectedPathComponent()
                .getResource()
                .acceptVisitor(new RemoveResourceChildrenVisitor());
    }
}
