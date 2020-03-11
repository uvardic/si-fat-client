package fat.client.gui.resourcepanel;

import fat.client.gui.resourcepanel.operationpanel.component.OperationPanel;
import fat.client.gui.resourcepanel.operationpanel.state.OperationPanelStateManager;
import fat.client.gui.resourcepanel.tablepanel.TablePanel;
import fat.client.gui.resourcepanel.toolbar.ResourcePanelToolbar;
import fat.client.gui.util.ComponentSizeCalculator;

import javax.swing.*;
import java.awt.*;

public class ResourcePanel extends JPanel {

    private final JSplitPane contentPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

    private final TablePanel tablePanel = new TablePanel(this);

    private final OperationPanelStateManager operationPanelStateManager = new OperationPanelStateManager(this);

    public ResourcePanel() {
        super(new BorderLayout());
        initialize();
    }

    private void initialize() {
        add(new ResourcePanelToolbar(), BorderLayout.NORTH);
        contentPanel.setTopComponent(tablePanel);
        contentPanel.setBottomComponent(new JPanel());
        contentPanel.setDividerLocation(ComponentSizeCalculator.calculateResourcePanelDividerLocation());
        add(contentPanel, BorderLayout.CENTER);
    }

    public JSplitPane getContentPanel() {
        return contentPanel;
    }

    public TablePanel getTablePanel() {
        return tablePanel;
    }

    public OperationPanelStateManager getOperationPanelStateManager() {
        return operationPanelStateManager;
    }

    public void initializeOperationPanel() {
        operationPanelStateManager.getCurrentState().initializePanel();
    }

    public void clearOperationPanel() {
        Component component = getContentPanel().getBottomComponent();

        if (!(component instanceof OperationPanel))
            throw new IllegalStateException();

        ((OperationPanel) component).removeAll();
    }
}
