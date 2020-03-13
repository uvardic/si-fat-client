package fat.client.gui.resourcepanel.operationpanel.component;

import fat.client.gui.MainFrame;
import fat.client.gui.resourcepanel.tablepanel.table.Table;
import fat.client.resource.Entity;
import fat.client.resource.Resource;
import fat.client.resource.persistence.Persistence;
import fat.client.resource.persistence.PersistenceImpl;
import fat.client.resource.persistence.implementor.MySQLPersistenceImplementor;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
        persistence.save(lastSelectedResource, createRequest());

        JScrollPane tableScrollPane = (JScrollPane) MainFrame.getInstance()
                .getResourcePanel()
                .getTablePanel()
                .getSelectedComponent();
        Table table = (Table) tableScrollPane.getViewport().getView();
        table.updateTable();
    }

    private Map<String, Object> createRequest() {
        return Arrays.stream(getComponents())
                .filter(component -> component instanceof JTextField)
                .map(component -> (JTextField) component)
                .collect(Collectors.toMap(Component::getName, JTextComponent::getText, (a, b) -> b, LinkedHashMap::new));
    }

}
