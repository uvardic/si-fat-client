package fat.client.gui.panel.component;

import fat.client.gui.Dialog;
import fat.client.gui.MainFrame;
import fat.client.gui.tree.Node;
import fat.client.gui.util.ComponentSizeCalculator;
import fat.client.resource.Entity;
import fat.client.resource.Resource;

import javax.swing.*;
import java.awt.*;

public abstract class ResourcePanelComponent extends JPanel {

    private Resource resource;

    ResourcePanelComponent(LayoutManager layout) {
        super(layout);

        Node lastSelectedNode = MainFrame.getInstance().getTree().getLastSelectedPathComponent();

        if (isEntitySelected(lastSelectedNode)) {
            this.resource = lastSelectedNode.getResource();
            initialize();
        } else
            Dialog.error("Error!", "Select an entity first!");

        ComponentSizeCalculator.adjustResourcePanelDividerLocation();
    }

    private boolean isEntitySelected(Node lastSelected) {
        return lastSelected != null && lastSelected.getResource() instanceof Entity;
    }

    abstract void initialize();

    Resource getResource() {
        return resource;
    }

    JTextField initializeTextField(String name) {
        JTextField textField = new JTextField();
        textField.setName(name);
        textField.setPreferredSize(ComponentSizeCalculator.calculateTextFieldSize());
        return textField;
    }

}
