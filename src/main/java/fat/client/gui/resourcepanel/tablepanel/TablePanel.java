package fat.client.gui.resourcepanel.tablepanel;

import fat.client.gui.MainFrame;
import fat.client.gui.resourcepanel.ResourcePanel;
import fat.client.gui.resourcepanel.tablepanel.table.Table;
import fat.client.gui.tree.Node;
import fat.client.resource.Resource;

import javax.swing.*;
import java.util.Arrays;

public class TablePanel extends JTabbedPane {

    private final ResourcePanel resourcePanel;

    public TablePanel(ResourcePanel resourcePanel) {
        this.resourcePanel = resourcePanel;
        initializeController();
    }

    private void initializeController() {
        TablePanelController controller = new TablePanelController(this);
        addMouseListener(controller);
        addMouseWheelListener(controller);
        addMouseMotionListener(controller);
    }

    Table getSelectedTable() {
        JScrollPane scrollPane = (JScrollPane) getComponentAt(getSelectedIndex());
        return (Table) scrollPane.getViewport().getView();
    }

    public void addTableFor(Resource resource) {
        if (resource == null)
            throw new NullPointerException("Resource can't be null!");

        Table table = new Table(resource);

        if (tableNotFound(table))
            addTab(table.format(), new JScrollPane(table));

        selectTable(table);
        resourcePanel.initializeOperationPanel();
    }

    private boolean tableNotFound(Table table) {
        return Arrays.stream(getComponents())
                .map(component -> (JScrollPane) component)
                .map(component -> (Table) component.getViewport().getView())
                .noneMatch(existingTable -> existingTable.equals(table));
    }

    private void selectTable(Table table) {
        for (int i = 0; i < getComponentCount(); i++) {
            JScrollPane scrollPane = (JScrollPane) getComponentAt(i);
            Table presentTable = (Table) scrollPane.getViewport().getView();
            if (table.equals(presentTable))
                setSelectedIndex(i);
        }
    }

    public void removeTableFor(Resource resource) {
        Arrays.stream(getComponents())
                .map(component -> (Table) component)
                .filter(table -> table.getResource().equals(resource))
                .findFirst()
                .ifPresent(this::remove);

        if (tablesExists())
            selectFirstTable();
        else
            selectParent(resource.getParent());
    }

    private boolean tablesExists() {
        return getComponentCount() != 0;
    }

    private void selectFirstTable() {
        Table firstTable = (Table) getComponentAt(0);
        MainFrame.getInstance().getTree().selectNode(new Node(firstTable.getResource()));
    }

    private void selectParent(Resource parent) {
        resourcePanel.clearOperationPanel();
        MainFrame.getInstance().getTree().selectNode(new Node(parent));
    }

}
