package fat.client.gui.panel.component;

import fat.client.resource.Resource;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class SaveOperationPanel extends OperationPanel {

    public SaveOperationPanel() {
        super(new MigLayout());
    }

    @Override
    public void initialize() {
        getResource().getChildren().forEach(this::initializeComponents);
        add(new JButton("Save"));
    }

    private void initializeComponents(Resource child) {
        add(new JLabel(String.format("%s:", child.getName())), "wrap");
        add(initializeTextField(child.getName()), "wrap");
    }

}
