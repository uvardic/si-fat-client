package fat.client.gui.resourcepanel.operationpanel.component;

import fat.client.gui.Dialog;
import fat.client.gui.MainFrame;
import fat.client.gui.util.ComponentSizeCalculator;
import fat.client.resource.Attribute;
import fat.client.resource.Entity;
import fat.client.resource.Resource;
import fat.client.resource.persistence.Persistence;
import fat.client.resource.persistence.PersistenceImpl;
import fat.client.resource.persistence.implementor.MySQLPersistenceImplementor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UpdateOperationPanel extends OperationPanel {

    private List<Component> findFields;

    private List<Component> updateFields;

    public UpdateOperationPanel() {
        super(ComponentSizeCalculator.calculateLayoutForEditResourcePanel());
    }

    @Override
    void initialize() {
        findFields = new ArrayList<>();
        updateFields = new ArrayList<>();
        initializeFind();
        initializeEdit();
    }

    private void initializeFind() {
        getSelectedNode().getResource().getChildren()
                .stream()
                .map(child -> (Attribute) child)
                .filter(Attribute::isKey)
                .forEach(this::initializeFindComponents);

        JButton button = new JButton("Find");
        button.addActionListener(this::findAction);
        add(button);
    }

    private void initializeFindComponents(Attribute attribute) {
        add(new JLabel(String.format("%s:", attribute.getName())), "wrap");
        JTextField textField = initializeTextField(attribute.getName());
        findFields.add(textField);
        add(textField, "wrap");
    }

    private void findAction(ActionEvent event) {
        Resource lastSelectedResource = MainFrame.getInstance().getTree().getLastSelectedPathComponent().getResource();

        if (!(lastSelectedResource instanceof Entity))
            throw new IllegalStateException("Entity must be selected!");

        Persistence persistence = new PersistenceImpl(new MySQLPersistenceImplementor());
        Component[] components = new Component[findFields.size()];
        Map<String, Object> object = persistence.findById(
                lastSelectedResource, createMap(findFields.toArray(components))
        );

        if (object.isEmpty()) {
            Dialog.error("Find Error", "Data not found!");
            return;
        }

        updateFields.stream()
                .map(component -> (JTextField) component)
                .forEach(textField -> textField.setText(object.get(textField.getName()).toString()));
    }

    private int row = 0;

    private void initializeEdit() {
        getSelectedNode().getResource().getChildren()
                .stream()
                .map(child -> (Attribute) child)
                .filter(attribute -> !attribute.isKey())
                .forEach(this::initializeEditComponents);

        JButton button = new JButton("Update");
        button.addActionListener(this::updateAction);
        add(button, "cell 1 " + row);
    }

    private void initializeEditComponents(Attribute attribute) {
        add(new JLabel(String.format("%s:", attribute.getName())), "cell 1 " + row++);
        JTextField textField = initializeTextField(attribute.getName());
        updateFields.add(textField);
        add(textField, "cell 1 " + row++);
    }

    private void updateAction(ActionEvent event) {
        Resource lastSelectedResource = MainFrame.getInstance().getTree().getLastSelectedPathComponent().getResource();

        if (!(lastSelectedResource instanceof Entity))
            throw new IllegalStateException("Entity must be selected!");

        Persistence persistence = new PersistenceImpl(new MySQLPersistenceImplementor());
        Component[] idComponents = new Component[findFields.size()];
        Component[] requestComponents = new Component[updateFields.size()];
        persistence.update(
                lastSelectedResource, createMap(findFields.toArray(idComponents)),
                createMap(updateFields.toArray(requestComponents))
        );
        updateCurrentTable();
    }


}
