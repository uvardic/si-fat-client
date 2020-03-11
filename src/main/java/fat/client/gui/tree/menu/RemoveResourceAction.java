package fat.client.gui.tree.menu;

import fat.client.gui.Action;
import fat.client.gui.MainFrame;
import fat.client.resource.visitor.RemoveResourceVisitor;

import java.awt.event.ActionEvent;

public class RemoveResourceAction extends Action {

    public RemoveResourceAction() {
        super("Remove Resource", "Remove Resource");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        MainFrame.getInstance()
                .getTree()
                .getLastSelectedPathComponent()
                .getResource()
                .acceptVisitor(new RemoveResourceVisitor());
    }

}
