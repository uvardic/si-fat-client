package fat.client.gui.panel;

import fat.client.gui.panel.state.PanelStateManager;
import fat.client.gui.table.Table;
import fat.client.gui.util.ComponentSizeCalculator;
import fat.client.resource.Resource;

import javax.swing.*;
import java.awt.*;

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

        tablePanels.addTab(table.format(), table);
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
