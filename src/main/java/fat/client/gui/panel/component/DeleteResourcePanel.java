package fat.client.gui.panel.component;

import fat.client.resource.Attribute;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class DeleteResourcePanel extends ResourcePanelComponent {

    public DeleteResourcePanel() {
        super(new MigLayout());
    }

    @Override
    void initialize() {
        getResource().getChildren().stream()
                .map(child -> (Attribute) child)
                .filter(Attribute::isKey)
                .forEach(this::initializeComponents);

        add(new JButton("Delete"));
    }

    private void initializeComponents(Attribute attribute) {
        add(new JLabel(String.format("%s:", attribute.getName())), "wrap");
        add(initializeTextField(attribute.getName()), "wrap");
    }

}
