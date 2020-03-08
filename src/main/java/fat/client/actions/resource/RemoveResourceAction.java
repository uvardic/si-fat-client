package fat.client.actions.resource;

import fat.client.gui.MainFrame;
import fat.client.resource.visitor.RemoveResourceVisitor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RemoveResourceAction extends AbstractAction {

    public RemoveResourceAction() {
        putValue(NAME, "Remove Resource");
        putValue(SHORT_DESCRIPTION, "Remove Resource");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        MainFrame.getInstance().getTree()
                .getLastSelectedPathComponent()
                .getResource()
                .acceptVisitor(new RemoveResourceVisitor());
    }

}
