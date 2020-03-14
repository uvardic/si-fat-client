package fat.client.gui.resourcepanel.operationpanel.component;

import fat.client.gui.MainFrame;
import fat.client.resource.Attribute;
import fat.client.resource.Entity;
import fat.client.resource.Resource;
import fat.client.resource.persistence.Persistence;
import fat.client.resource.persistence.PersistenceImpl;
import fat.client.resource.persistence.implementor.MySQLPersistenceImplementor;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DeleteOperationPanel extends OperationPanel {

    public DeleteOperationPanel() {
        super(new MigLayout());
    }

    @Override
    void initialize() {
        getSelectedNode().getResource().getChildren()
                .stream()
                .map(child -> (Attribute) child)
                .filter(Attribute::isKey)
                .forEach(this::initializeComponents);

        JButton button = new JButton("Delete");
        button.addActionListener(this::deleteAction);
        add(button);
    }

    private void initializeComponents(Attribute attribute) {
        add(new JLabel(String.format("%s:", attribute.getName())), "wrap");
        add(initializeTextField(attribute.getName()), "wrap");
    }

    private void deleteAction(ActionEvent event) {
        Resource lastSelectedResource = MainFrame.getInstance().getTree().getLastSelectedPathComponent().getResource();

        if (!(lastSelectedResource instanceof Entity))
            throw new IllegalStateException("Entity must be selected!");

        Persistence persistence = new PersistenceImpl(new MySQLPersistenceImplementor());
        persistence.deleteById(lastSelectedResource, createMap(getComponents()));
        updateCurrentTable();
    }

}
