package fat.client.gui.resourcepanel.operationpanel.component;

import fat.client.gui.Dialog;
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
import java.util.Map;

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

        JButton findButton = new JButton("Find");
        findButton.addActionListener(this::findAction);
        add(findButton);

        JButton findAllButton = new JButton("Find All");
        findAllButton.addActionListener(e -> updateCurrentTable());
        add(findAllButton);
    }

    private void initializeComponents(Attribute attribute) {
        add(new JLabel(String.format("%s:", attribute.getName())), "wrap");
        add(initializeTextField(attribute.getName()), "wrap");
    }

    private void findAction(ActionEvent event) {
        Resource lastSelectedResource = MainFrame.getInstance().getTree().getLastSelectedPathComponent().getResource();

        if (!(lastSelectedResource instanceof Entity)) {
            Dialog.error("", "Select an entity first");
            return;
        }

        Persistence persistence = new PersistenceImpl(new MySQLPersistenceImplementor());
        Map<String, Object> object = persistence.findById(lastSelectedResource, createMap(getComponents()));
        getCurrentTable().showRow(object);
    }

}
