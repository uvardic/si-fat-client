package fat.client.gui.panel;

import fat.client.gui.MainFrame;
import fat.client.gui.panel.state.OperationPanelStateManager;
import fat.client.gui.table.Table;
import fat.client.gui.util.ComponentSizeCalculator;
import fat.client.resource.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class ResourcePanel extends JPanel {

    private final JSplitPane contentPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

    private final JTabbedPane tablePanel = new JTabbedPane();

    private final OperationPanelStateManager operationPanelStateManager = new OperationPanelStateManager(this);

    public ResourcePanel() {
        super(new BorderLayout());
        initialize();
    }

    private void initialize() {
        add(new Toolbar(), BorderLayout.NORTH);
        tablePanel.addMouseListener(new TablePanelController());
        contentPanel.setTopComponent(tablePanel);
        contentPanel.setBottomComponent(new JPanel());
        contentPanel.setDividerLocation(ComponentSizeCalculator.calculateResourcePanelDividerLocation());
        add(contentPanel, BorderLayout.CENTER);
    }

    public void addTable(Resource resource) {
        Table table = new Table(resource);

        if (tableNotFound(table))
            tablePanel.addTab(table.format(), table);

        tablePanel.setSelectedComponent(table);
        operationPanelStateManager.getCurrentState().initializePanel();
    }

    private boolean tableNotFound(Table table) {
        return Arrays.stream(tablePanel.getComponents())
                .map(component -> (Table) component)
                .noneMatch(existingTable -> existingTable.equals(table));
    }

    public void removeTable() {

    }

    public void removeAllTables() {
        tablePanel.removeAll();
    }

    public JSplitPane getContentPanel() {
        return contentPanel;
    }

    public OperationPanelStateManager getOperationPanelStateManager() {
        return operationPanelStateManager;
    }

    private Table getSelectedTable() {
        return (Table) tablePanel.getComponentAt(tablePanel.getSelectedIndex());
    }

    private class TablePanelController extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            MainFrame.getInstance().getTree().selectNodeFor(getSelectedTable().getResource());
            operationPanelStateManager.getCurrentState().initializePanel();
        }

    }

}
