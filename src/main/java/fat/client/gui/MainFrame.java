package fat.client.gui;

import fat.client.actions.ActionManager;
import fat.client.gui.panel.ResourcePanel;
import fat.client.gui.tree.Tree;
import fat.client.gui.util.ComponentSizeCalculator;
import fat.client.resource.Workspace;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
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

        instance.initializeSingletons();
        instance.initializeFrame();
        instance.initializeComponents();

        return instance;
    }

    private ActionManager actionManager;

    private Tree tree;

    private ResourcePanel resourcePanel;

    private void initializeSingletons() {
        actionManager = new ActionManager();
        tree = new Tree(new Workspace("Workspace"));
        resourcePanel = new ResourcePanel();
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

        splitPane.setLeftComponent(new JScrollPane(tree));
        splitPane.setRightComponent(new JScrollPane(resourcePanel));
        splitPane.setDividerLocation(ComponentSizeCalculator.calculateSplitPaneDividerLocation());

        add(splitPane, BorderLayout.CENTER);
    }

}
