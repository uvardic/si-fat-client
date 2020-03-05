package fat.client.gui.panel;

import fat.client.gui.MainFrame;
import fat.client.gui.table.Table;
import fat.client.resource.Resource;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

class TablePanel extends JTabbedPane {

    private final ResourcePanel resourcePanel;

    TablePanel(ResourcePanel resourcePanel) {
        this.resourcePanel = resourcePanel;
        addMouseListener(new TablePanelController());
    }

    void addTableFor(Resource resource) {
        Table table = new Table(resource);

        if (tableNotFound(table))
            addTab(table.format(), table);

        setSelectedComponent(table);
        resourcePanel.initializeOperationPanel();
    }

    private boolean tableNotFound(Table table) {
        return Arrays.stream(getComponents())
                .map(component -> (Table) component)
                .noneMatch(existingTable -> existingTable.equals(table));
    }

    void removeTableFor(Resource resource) {
        Table unluckyTable = Arrays.stream(getComponents())
                .map(component -> (Table) component)
                .filter(table -> table.getResource().equals(resource))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Resource: %s wasn't found!", resource)
                ));

        remove(unluckyTable);

        if (getComponentCount() == 0)
            resourcePanel.clearOperationPanel();
        else
            selectFirstTable();
    }

    private void selectFirstTable() {
        Table firstTable = (Table) getComponentAt(0);
        MainFrame.getInstance().getTree().selectNodeFor(firstTable.getResource());
    }

    private Table getSelectedTable() {
        return (Table) getComponentAt(getSelectedIndex());
    }

    private class TablePanelController extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            MainFrame.getInstance().getTree().selectNodeFor(getSelectedTable().getResource());
            resourcePanel.initializeOperationPanel();
        }

    }

}
