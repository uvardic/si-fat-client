package fat.client.gui.panel;

import fat.client.gui.panel.state.PanelStateManager;
import fat.client.gui.table.Table;
import fat.client.gui.util.ComponentSizeCalculator;
import fat.client.resource.Resource;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ResourcePanel extends JPanel {

    private final JSplitPane contentPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

    private final JTabbedPane tablePanels = new JTabbedPane();

    private final PanelStateManager panelStateManager = new PanelStateManager(this);

    public ResourcePanel() {
        super(new BorderLayout());

        initialize();
    }

    private void initialize() {
        add(new Toolbar(), BorderLayout.NORTH);
        contentPanel.setTopComponent(tablePanels);
        contentPanel.setBottomComponent(new JPanel());
        contentPanel.setDividerLocation(ComponentSizeCalculator.calculateResourcePanelDividerLocation());
        add(contentPanel, BorderLayout.CENTER);
    }

    public void addTable(Resource resource) {
        Table table = new Table(resource);

        if (tableExists(table))
            tablePanels.setSelectedComponent(table);
        else
            tablePanels.addTab(table.format(), table);
    }

    private boolean tableExists(Table table) {
        return Arrays.stream(tablePanels.getComponents())
                .map(component -> (Table) component)
                .anyMatch(existingTable -> existingTable.equals(table));
    }

    public void removeTable() {

    }

    public void removeAllTables() {
        tablePanels.removeAll();
    }

    public JSplitPane getContentPanel() {
        return contentPanel;
    }

    public PanelStateManager getPanelStateManager() {
        return panelStateManager;
    }

}
