package fat.client.actions.resource;

import fat.client.gui.MainFrame;
import fat.client.resource.visitor.RemoveResourceVisitor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RemoveResourceAction extends AbstractAction {

    public RemoveResourceAction() {
        putValue(NAME, "Remove Resource");
        putValue(SHORT_DESCRIPTION, "Remove Resource");
        putValue(ACCELERATOR_KEY, getKeyStroke());
    }

    private KeyStroke getKeyStroke() {
        return KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        MainFrame.getInstance().getTree()
                .getLastSelectedPathComponent()
                .getResource()
                .acceptVisitor(new RemoveResourceVisitor());
    }

}
