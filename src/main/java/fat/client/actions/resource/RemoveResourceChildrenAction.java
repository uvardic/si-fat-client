package fat.client.actions.resource;

import fat.client.gui.MainFrame;
import fat.client.resource.visitor.RemoveResourceChildrenVisitor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RemoveResourceChildrenAction extends AbstractAction {

    public RemoveResourceChildrenAction() {
        putValue(NAME, "Remove Resource Children");
        putValue(SHORT_DESCRIPTION, "Remove Resource Children");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        MainFrame.getInstance().getTree()
                .getLastSelectedPathComponent()
                .getResource()
                .acceptVisitor(new RemoveResourceChildrenVisitor());
    }
}
