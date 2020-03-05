package fat.client.gui.panel;

import fat.client.gui.panel.state.OperationPanelStateManager;
import fat.client.gui.util.ComponentSizeCalculator;
import fat.client.resource.Resource;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class ResourcePanel extends JPanel {

    private final JSplitPane contentPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

    private final TablePanel tablePanel = new TablePanel(this);

    private final OperationPanelStateManager operationPanelStateManager = new OperationPanelStateManager(this);

    public ResourcePanel() {
        super(new BorderLayout());
        initialize();
    }

    private void initialize() {
        add(new Toolbar(), BorderLayout.NORTH);

        contentPanel.setTopComponent(tablePanel);
        contentPanel.setBottomComponent(new JPanel());
        contentPanel.setDividerLocation(ComponentSizeCalculator.calculateResourcePanelDividerLocation());

        add(contentPanel, BorderLayout.CENTER);
    }

    void initializeOperationPanel() {
        operationPanelStateManager.getCurrentState().initializePanel();
    }

    public void addTable(Resource resource) {
        tablePanel.addTable(resource);
    }

}
