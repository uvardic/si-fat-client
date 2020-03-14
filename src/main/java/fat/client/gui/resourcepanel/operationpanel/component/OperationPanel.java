package fat.client.gui.resourcepanel.operationpanel.component;

import fat.client.gui.Dialog;
import fat.client.gui.MainFrame;
import fat.client.gui.resourcepanel.tablepanel.table.Table;
import fat.client.gui.tree.Node;
import fat.client.gui.util.ComponentSizeCalculator;
import fat.client.resource.Entity;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    Map<String, Object> createMap(Component... components) {
        return Arrays.stream(components)
                .filter(component -> component instanceof JTextField)
                .map(component -> (JTextField) component)
                .collect(Collectors.toMap(Component::getName, JTextComponent::getText, (a, b) -> b, LinkedHashMap::new));
    }

    void updateCurrentTable() {
        getCurrentTable().updateTable();
    }

    Table getCurrentTable() {
        JScrollPane tableScrollPane = (JScrollPane) MainFrame.getInstance()
                .getResourcePanel()
                .getTablePanel()
                .getSelectedComponent();
        return (Table) tableScrollPane.getViewport().getView();
    }

}
