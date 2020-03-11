package fat.client.gui.resourcepanel.tablepanel;

import fat.client.gui.MainFrame;
import fat.client.gui.tree.Node;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class TablePanelController extends MouseAdapter {

    private final TablePanel tablePanel;

    TablePanelController(TablePanel tablePanel) {
        this.tablePanel = tablePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        MainFrame.getInstance()
                .getTree()
                .selectNode(new Node(tablePanel.getSelectedTable().getResource()));
    }

}
