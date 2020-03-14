package fat.client.gui.resourcepanel.operationpanel.component;

import fat.client.gui.MainFrame;
import fat.client.resource.Entity;
import fat.client.resource.Resource;
import fat.client.resource.persistence.Persistence;
import fat.client.resource.persistence.PersistenceImpl;
import fat.client.resource.persistence.implementor.MySQLPersistenceImplementor;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SaveOperationPanel extends OperationPanel {

    public SaveOperationPanel() {
        super(new MigLayout());
    }

    @Override
    public void initialize() {
        getSelectedNode().getResource().getChildren().forEach(this::initializeComponents);
        JButton button = new JButton("Save");
        button.addActionListener(this::saveAction);
        add(button);
    }

    private void initializeComponents(Resource child) {
        add(new JLabel(String.format("%s:", child.getName())), "wrap");
        add(initializeTextField(child.getName()), "wrap");
    }

    private void saveAction(ActionEvent event) {
        Resource lastSelectedResource = MainFrame.getInstance().getTree().getLastSelectedPathComponent().getResource();

        if (!(lastSelectedResource instanceof Entity))
            throw new IllegalStateException("Entity must be selected!");

        Persistence persistence = new PersistenceImpl(new MySQLPersistenceImplementor());
        persistence.save(lastSelectedResource, createMap(getComponents()));
        updateCurrentTable();
    }

}
