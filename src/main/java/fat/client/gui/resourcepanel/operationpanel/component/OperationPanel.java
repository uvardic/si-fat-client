package fat.client.gui.resourcepanel.operationpanel.component;

import fat.client.gui.Dialog;
import fat.client.gui.MainFrame;
import fat.client.gui.tree.Node;
import fat.client.gui.util.ComponentSizeCalculator;
import fat.client.resource.Entity;

import javax.swing.*;
import java.awt.*;

public abstract class OperationPanel extends JPanel {

    private Node selectedNode;

    OperationPanel(LayoutManager layout) {
        super(layout);

        Node lastSelectedNode = MainFrame.getInstance().getTree().getLastSelectedPathComponent();

        if (isEntitySelected(lastSelectedNode)) {
            this.selectedNode = lastSelectedNode;
            initialize();
        } else
            Dialog.error("Error!", "Select an entity first!");

        ComponentSizeCalculator.adjustResourcePanelDividerLocation();
    }

    private boolean isEntitySelected(Node lastSelected) {
        return lastSelected != null && lastSelected.getResource() instanceof Entity;
    }

    abstract void initialize();

    Node getSelectedNode() {
        return selectedNode;
    }

    JTextField initializeTextField(String name) {
        JTextField textField = new JTextField();
        textField.setName(name);
        textField.setPreferredSize(ComponentSizeCalculator.calculateTextFieldSize());
        return textField;
    }

}
