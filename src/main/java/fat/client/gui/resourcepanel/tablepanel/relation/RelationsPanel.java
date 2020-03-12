package fat.client.gui.resourcepanel.tablepanel.relation;

import fat.client.gui.Dialog;
import fat.client.gui.MainFrame;
import fat.client.gui.resourcepanel.tablepanel.table.Table;
import fat.client.gui.tree.Node;
import fat.client.gui.util.ComponentSizeCalculator;
import fat.client.resource.Entity;

import javax.swing.*;

public class RelationsPanel extends JTabbedPane {

    private Node selectedNode;

    public RelationsPanel() {
        Node lastSelectedNode = MainFrame.getInstance().getTree().getLastSelectedPathComponent();

        if (isEntitySelected(lastSelectedNode)) {
            this.selectedNode = lastSelectedNode;
            initialize();
        } else
            Dialog.error("Error!", "Select an entity first!");

        ComponentSizeCalculator.adjustResourcePanelDividerLocation();
    }

    private boolean isEntitySelected(Node lastSelected) {
        return lastSelected != null && lastSelected.getResource() instanceof Entity;
    }

    private void initialize() {
        removeAll();
        Entity entity = (Entity) selectedNode.getResource();
        entity.getRelations().forEach(this::addTableFor);
    }

    private void addTableFor(Entity relation) {
        Table table = new Table(relation);
        addTab(table.format(), table);
        setSelectedComponent(table);
    }
}
