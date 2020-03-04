package fat.client.gui;

import fat.client.gui.tree.Tree;
import fat.client.gui.util.ComponentSizeCalculator;
import fat.client.resource.Workspace;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static MainFrame instance;

    private MainFrame() {}

    public static MainFrame getInstance() {
        if (instance != null)
            return instance;

        return initialize();
    }

    private static MainFrame initialize() {
        instance = new MainFrame();

        instance.initializeFrame();
        instance.initializeComponents();

        return instance;
    }

    private void initializeFrame() {
        setTitle("Fat App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(ComponentSizeCalculator.calculateMainFrameSize());
        setMinimumSize(ComponentSizeCalculator.calculateMainFrameMinSize());
        setMaximumSize(ComponentSizeCalculator.calculateMainFrameMaxSize());
        setExtendedState(MAXIMIZED_BOTH);
    }

    private void initializeComponents() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        // to be replaced by tree and table
        splitPane.setLeftComponent(new JScrollPane(new Tree(new Workspace("Workspace"))));
        splitPane.setRightComponent(new JScrollPane(new JPanel()));
        splitPane.setDividerLocation(ComponentSizeCalculator.calculateSplitPaneDividerLocation());

        add(splitPane, BorderLayout.CENTER);
    }

}
