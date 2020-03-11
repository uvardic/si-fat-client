package fat.client.gui.resourcepanel.operationpanel.component;

import fat.client.resource.Attribute;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class FindOperationPanel extends OperationPanel {

    public FindOperationPanel() {
        super(new MigLayout());
    }

    @Override
    void initialize() {
        getSelectedNode().getResource().getChildren()
                .stream()
                .map(child -> (Attribute) child)
                .filter(Attribute::isKey)
                .forEach(this::initializeComponents);

        add(new JButton("Find"));
    }

    private void initializeComponents(Attribute attribute) {
        add(new JLabel(String.format("%s:", attribute.getName())), "wrap");
        add(initializeTextField(attribute.getName()), "wrap");
    }
}
