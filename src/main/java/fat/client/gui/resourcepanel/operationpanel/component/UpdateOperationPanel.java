package fat.client.gui.resourcepanel.operationpanel.component;

import fat.client.gui.util.ComponentSizeCalculator;
import fat.client.resource.Attribute;

import javax.swing.*;

public class UpdateOperationPanel extends OperationPanel {

    public UpdateOperationPanel() {
        super(ComponentSizeCalculator.calculateLayoutForEditResourcePanel());
    }

    @Override
    void initialize() {
        initializeFind();
        initializeEdit();
    }

    private void initializeFind() {
        getSelectedNode().getResource().getChildren()
                .stream()
                .map(child -> (Attribute) child)
                .filter(Attribute::isKey)
                .forEach(this::initializeFindComponents);

        add(new JButton("Find"));
    }

    private void initializeFindComponents(Attribute attribute) {
        add(new JLabel(String.format("%s:", attribute.getName())), "wrap");
        add(initializeTextField(attribute.getName()), "wrap");
    }

    private int row = 0;

    private void initializeEdit() {
        getSelectedNode().getResource().getChildren()
                .stream()
                .map(child -> (Attribute) child)
                .filter(attribute -> !attribute.isKey())
                .forEach(this::initializeEditComponents);

        add(new JButton("Update"), "cell 1 " + row);
    }

    private void initializeEditComponents(Attribute attribute) {
        add(new JLabel(String.format("%s:", attribute.getName())), "cell 1 " + row++);
        add(initializeTextField(attribute.getName()), "cell 1 " + row++);
    }

}
